/*
 * Created on Aug 5, 2008
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nexus.dao;

import java.util.*;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.nexus.web.common.UsersList;
import com.nexus.web.common.MessagesList;
import com.nexus.web.common.PartnerMessagesList;
import com.nexus.web.common.IndustryName;
import com.nexus.web.common.IndustryType;
import com.nexus.web.common.MapDocumet;
import com.nexus.web.common.CountryBean;
import com.nexus.web.common.DataBean;
import com.nexus.web.common.ItemList;
import com.nexus.web.common.OrderList;
import java.sql.*;
import com.nexus.domain.*;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional
public class SpringHibernateDAOImpl extends HibernateDaoSupport implements SpringHibernateDAO {

    Logger log=Logger.getLogger(SpringHibernateDAOImpl.class);
    public void addMapping(String member_id, String header, String type, String direction, String file_name, String file_extname) {
        Connection conn = this.getSession().connection();
        Statement smt;
        try {
            smt = conn.createStatement();
            String query = "insert into xy_partner_mapping (Member_ID, Header,txn_TYPE,Direction,Stylesheet,ext_name) VALUES (" + member_id + "," + header + "," + type + "," + direction + ",'" + file_name + "','" + file_extname + "')";
            log.info("query addMapping = " + query);
            boolean result = smt.execute(query);
            log.info("result addMapping = " + result);
            smt.close();
            conn.close();
        } catch (SQLException ex) {
            log.error("Exception Message", ex);
        }
    }

    public void addUser(String nexus_id, String userid, String password, String name, int type, String email) {
        Connection conn = this.getSession().connection();
        Statement smt;
        try {
            smt = conn.createStatement();
            String query = "insert into user_login(Nexus_id,loginid,password,name,type,email) VALUES (" + nexus_id + ",'" + userid + "','" + password + "','" + name + "'," + type + ",'" + email + "')";
            log.info("query addUser= " + query);
            boolean result = smt.execute(query);
            log.info("result addUser= " + result);
            smt.close();
            conn.close();
        } catch (SQLException ex) {
            log.error("Exception Message", ex);
        }
    }

    public User checkUserLogin(String strUserName, String strPassword) throws DataAccessException, java.sql.SQLException {
        User user = null;
        Connection conn = this.getSession().connection();
        //Write jdbc code to validate the user against database
        Statement smt = conn.createStatement();
        ResultSet rs;

        //String query = "select id from user_login where loginid='" + strUserName + "' and password='" + strPassword + "'";
        // chnage because of chnagein register policy.
        String query = "";
        query = "select id,nexus_id,type from user_login where loginid='" + strUserName + "' and " +
                "password='" + strPassword + "'";
        log.info("query checkUserLogin= " + query);
        rs = smt.executeQuery(query);
        if (rs.next()) {
            user = new User(rs.getInt("id"),rs.getInt("nexus_id"), rs.getInt("type"));
        }
        smt.close();
        rs.close();
        conn.close();
        return user;
    }

    public boolean checkPMessageStatus(int buyerid, int supplierid, String partnertype) throws DataAccessException, java.sql.SQLException {
        boolean valid = false;
        Connection conn = this.getSession().connection();
        //Write jdbc code to validate the user against database
        Statement smt = conn.createStatement();
        ResultSet rs;
        String query = "";
        //write select query for checking password
        if (partnertype.equalsIgnoreCase("Buyer")) {
            query = "SELECT count(*) FROM xy_partner_link where buyer_id in (" + buyerid + ") and supplier_id in (" + supplierid + ") and status = 'enable'";
        } else if (partnertype.equalsIgnoreCase("Supplier")) {
            query = "SELECT count(*) FROM xy_partner_link where buyer_id in (" + buyerid + ") and supplier_id in (" + supplierid + ") and status = 'enable'";
        } else {
            query = "SELECT count(*) FROM xy_partner_link where buyer_id in (" + buyerid + "," + supplierid + ") and supplier_id in (" + buyerid + "," + supplierid + ") and status = 'enable'";
        }
        log.info("query checkPMessageStatus= " + query);
        rs = smt.executeQuery(query);
        int rcount = 0;
        while (rs.next()) {
            rcount = rs.getInt(1);
        }
        log.info("rcount = " + rcount);

        if (partnertype.equalsIgnoreCase("Buyer") && (rcount == 1)) {
            valid = true;
        } else if (partnertype.equalsIgnoreCase("Supplier") && (rcount == 1)) {
            valid = true;
        } else {
            if (rcount == 2) {
                valid = true;
            }
        }
        smt.close();
        rs.close();
        conn.close();
        return valid;
    }

    public void addUser(Registration registration) throws DataAccessException {
        getHibernateTemplate().save(registration);
    }

    public void updateUser(Registration registration) throws DataAccessException {
        getHibernateTemplate().update(registration);
    }

    public Registration loadUser(Integer id) throws DataAccessException {
        //return getHibernateTemplate().find("from roseindialocal.dao.hibernate.Article obj where obj.id = '" + id + "'");
        return (Registration) getHibernateTemplate().get(Registration.class, id);
    }

    public boolean checkValidUserName(String strUserid) throws DataAccessException, java.sql.SQLException {
        boolean valid = false;
        Connection conn = this.getSession().connection();
        //Write jdbc code to validate the user against database
        Statement smt = conn.createStatement();
        ResultSet rs;
        //write select query for checking password

        String query = "select id from user_login where loginid='" + strUserid + "'";
        log.info("query checkValidUserName= " + query);
        rs = smt.executeQuery(query);
        if (rs.next() == true) {
            valid = true;
        } else {
            valid = false;
        }
        smt.close();
        rs.close();
        conn.close();
        return valid;
    }

    public Collection getMemberDetails(String nexusid) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "select * from registration where id='" + nexusid + "'";
        log.info("query getMemberDetails= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            UsersList userslist = new UsersList();
            int jobid = rs.getInt("id");
            userslist.setId(jobid);
            String loginid = rs.getString("loginid");
            userslist.setLoginid(loginid);
            String company = rs.getString("company");
            userslist.setCompany(company);
            String contact = rs.getString("contact");
            userslist.setContact(contact);
            String email = rs.getString("email");
            userslist.setEmail(email);
            String phno = rs.getString("phno");
            userslist.setPhno(phno);
            String state = rs.getString("state");
            userslist.setState(state);
            String country = rs.getString("country");
            userslist.setCountry(country);
            String company_url = rs.getString("company_url");
            userslist.setCompany_url(company_url);
            //String mobile = rs.getString("mobile");
            //userslist.setMobile(mobile);
            String m_type = rs.getString("Member_Type");
            userslist.setMember_type(m_type);
            list.add(userslist);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public Collection getUsersList() throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "SELECT * FROM registration,us_country where registration.COUNTRY = us_country.N_COUNTRY_ID";
        log.info("query getUsersList= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            UsersList userslist = new UsersList();
            int jobid = rs.getInt("id");
            userslist.setId(jobid);
            String loginid = rs.getString("loginid");
            userslist.setLoginid(loginid);
            String company = rs.getString("company");
            userslist.setCompany(company);
            String contact = rs.getString("contact");
            userslist.setContact(contact);
            String email = rs.getString("email");
            userslist.setEmail(email);
            String phno = rs.getString("phno");
            userslist.setPhno(phno);
            String state = rs.getString("state");
            userslist.setState(state);
            String country = rs.getString("vch_country_name");
            userslist.setCountry(country);
            String company_url = rs.getString("company_url");
            userslist.setCompany_url(company_url);
            //String mobile = rs.getString("mobile");
            //userslist.setMobile(mobile);
            list.add(userslist);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public Collection getMyUsersList(String id) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "select * from user_login where nexus_id = '" + id + "' and type = 'user' ";
        log.info("query getMyUsersList=" + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            UsersList userslist = new UsersList();
            int userid = rs.getInt("id");
            userslist.setId(userid);
            String loginid = rs.getString("loginid");
            userslist.setLoginid(loginid);
            String company = rs.getString("password");
            userslist.setCompany(company);
            String contact = rs.getString("name");
            userslist.setContact(contact);
            String email = rs.getString("email");
            userslist.setEmail(email);
            list.add(userslist);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public Collection getDownloadList() throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "select name,link from xy_download_items";
        log.info("query getDownloadList=" + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            DataBean downloadlist = new DataBean();
            String name = rs.getString("name");
            downloadlist.setId(name);
            String link = rs.getString("link");
            downloadlist.setValue(link);
            list.add(downloadlist);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public void updateUserDetail(String userid, String name, String username, String password, String email) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        String query = "update user_login set name = '" + name + "', loginid = '" + username + "' , password = '" + password + "', email='" + email + "' where id = " + userid;
        log.info("query updateUserDetail=" + query);
        int result = smt.executeUpdate(query);
        log.info("The user details has been updated." + result);
        smt.close();
        conn.close();
    }

    public void updateUserLogin(int nexusId, String loginId, String password, String name,String email) throws DataAccessException, java.sql.SQLException{
            Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        String query = "update user_login set password = '" + password + "', name = '" + name + "', email='" + email + "' where nexus_id = '" + nexusId+"' and loginid='"+loginId+"'";
        log.info("query updateUserLogin=" + query);
        int result = smt.executeUpdate(query);
        log.info("The user details has been updated." + result);
        smt.close();
        conn.close();
    }

    public void deleteUserDetail(String userid) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        String query = "delete from user_login where id = " + userid;
        log.info("query deleteUserDetail=" + query);
        boolean result = smt.execute(query);
        log.info("The user has been deleted." + result);
        smt.close();
        conn.close();
    }

    public Collection getCategoryList(String buyid, String supid) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "SELECT categories.ID,categories.CategoryName  FROM categories_mapping,categories where categories_mapping.buyerid = '" + buyid + "' and categories_mapping.supplierid = '" + supid + "'and categories_mapping.CategoryID=categories.ID";
        log.info("query getCategoryList=" + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            int catid = rs.getInt("ID");
            String catname = rs.getString("CategoryName");
            log.info("id & cat " + catid + " " + catname);
            DataBean cat = new DataBean("" + catid, catname);
            list.add(cat);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public Collection getItemsByCategory(String catid) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        //String query = "SELECT products.Product_Code,products.gtin,products.Product_Name,products.UOM FROM products,categories where products.CategoryID=categories.ID and categories.ID = '" + catid + "'";
        String query = "SELECT bpm.buyer_item_number,p.gtin,p.Product_Name,p.UOM " +
                "FROM (products as p inner join buyer_products_mapping as bpm on p.gtin=bpm.productcode) " +
                "inner join categories as c on p.CategoryID=c.ID where c.ID = '" + catid + "'";
        log.info("query getItemsByCategory=" + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            String itemid = rs.getString("buyer_item_number");
            String itemname = rs.getString("Product_Name");
            String unit = rs.getString("uom");
            String gtin = rs.getString("gtin");
            ItemList item = new ItemList(itemid, itemname, unit, gtin);
            list.add(item);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public Collection getMyItemlist(String buyid, String supid) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "SELECT buyer_products_mapping.buyer_item_number,products.gtin,products.Product_Name,products.UOM FROM products,xy_partner_link,buyer_products_mapping where xy_partner_link.Buyer_Id = '" + buyid + "' and xy_partner_link.Supplier_Id = '" + supid + "' and xy_partner_link.ID = buyer_products_mapping.partner_link_id and buyer_products_mapping.productcode = products.gtin order by buyer_products_mapping.id";
        log.info("query getMyItemlist=" + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            String itemid = rs.getString("buyer_item_number");
            String itemname = rs.getString("Product_Name");
            String unit = rs.getString("uom");
            String gtin = rs.getString("gtin");
            ItemList item = new ItemList(itemid, itemname, unit, gtin);
            list.add(item);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    
    public List getIndustryGroupList() throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        //Write jdbc code
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        List industryTypeList = new ArrayList();
        String query = "SELECT distinct Group_Id, Group_Name FROM industry_codes order by Group_Id";
        log.info("query getIndustryGroupList=" + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("Group_Id");
            String group = rs.getString("Group_Name").trim();
//            log.info("group" + group);
            industryTypeList.add(new IndustryType(("" + id), ("SIC - " + id + " - " + group)));
        }
        rs.close();
        smt.close();
        conn.close();
        return industryTypeList;
    }

    public List getIndustryNameList(String groupid) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        //Write jdbc code
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        List list = new ArrayList();
        String query = " SELECT sic, industry_desc FROM industry_codes where group_id = '" + groupid + "' order by sic";
//        log.info("query getIndustryNameList=" + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("sic");
            String industry_desc = rs.getString("industry_desc").trim();
            list.add(new IndustryName("" + id, ("SIC - " + id + " - " + industry_desc)));
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public HashMap getMembers() throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();

        Statement smt = conn.createStatement();
        ResultSet rs = null;
        HashMap list = new HashMap();
        String query = "SELECT id,company FROM registration where Member_Type = 'Open' order by id";
        log.info("query getMembers=" + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("id");
            String company = rs.getString("company").trim();
            list.put(id, company);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public ArrayList getTxnTypes(String member, String direction) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();

        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "SELECT xy_txn_type.N_TYPE_ID,xy_txn_type.NAME, xy_txn_type.VCH_DESCRIPTION FROM xy_partner_mapping,xy_txn_type where xy_partner_mapping.direction = " + direction + " and xy_partner_mapping.member_id = " + member + " and  xy_txn_type.N_TYPE_ID =xy_partner_mapping.txn_type";
        log.info("query getTxnTypes= " + query);

        rs = smt.executeQuery(query);
        while (rs.next()) {
            MapDocumet typelist = new MapDocumet();
            String id = rs.getString("N_TYPE_ID");
            String name = rs.getString("NAME").trim();
            String desc = rs.getString("VCH_DESCRIPTION").trim();
            typelist.setKey(id);
            typelist.setName(name);
            typelist.setDisplayname(desc);
            list.add(typelist);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public HashMap getMyPartners(String id) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();

        Statement smt = conn.createStatement();
        ResultSet rs = null;
        HashMap list = new HashMap();
        String query = "SELECT distinct (registration.ID),registration.COMPANY FROM registration,xy_partner_link,us_country where  registration.COUNTRY = us_country.N_COUNTRY_ID and (xy_partner_link.Buyer_Id=registration.id or xy_partner_link.Supplier_Id=registration.id) and (xy_partner_link.Buyer_Id = '" + id + "' or xy_partner_link.Supplier_Id = '" + id + "') and (registration.id != '" + id + "')";
        log.info("query getMyPartners= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            int id1 = rs.getInt("id");
            String company = rs.getString("company").trim();
            log.info("company = " + company);
            list.put(id1, company);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

     public ArrayList getCountryList() throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        //Write jdbc code
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "SELECT N_COUNTRY_ID, VCH_COUNTRY_NAME FROM us_country order by VCH_COUNTRY_NAME asc";
        log.info("query getCountryList= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("N_COUNTRY_ID");
            String desc = rs.getString("VCH_COUNTRY_NAME").trim();
            list.add(new CountryBean("" + id, desc));
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public ArrayList getPriceList(int supplierId) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        //Write jdbc code
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "select priceType,priceTypeName from product_price_types where supplierId="+supplierId;
        log.info("query getPriceList= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("priceType");
            String name = rs.getString("priceTypeName").trim();
            list.add(new CountryBean("" + id, name));
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public ArrayList getTransaction_plan_list() throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        //Write jdbc code
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "SELECT ID, DESCRIPTION FROM transactions_volume_plans order by ID asc";
        log.info("query getTransaction_plan_list= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("ID");
            String desc = rs.getString("DESCRIPTION").trim();
            list.add(new DataBean("" + id, desc));
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public Collection getMessageList(String user) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String userid = user;
        String query = "SELECT xy_message.N_MESSAGE_ID,xy_message.N_SENDER_PARTNER_ID,sender_tp.company,xy_message.N_RECIPIENT_PARTNER_ID, recipient_tp.company,xy_message.dt_received,xy_txn_type.vch_description,xy_message.B_STATUS,xy_message.VCH_PATH,xy_message.N_QUEUE_ID,xy_txn_type.b_downloadable,xy_txn_type.b_viewable ,xy_message.vch_document_id FROM xy_message, xy_txn_type, registration sender_tp,registration recipient_tp WHERE xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and sender_tp.id = xy_message.N_SENDER_PARTNER_ID and recipient_tp.id = xy_message.N_RECIPIENT_PARTNER_ID and xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and (xy_message.N_RECIPIENT_PARTNER_ID= '" + userid + "' or xy_message.N_SENDER_PARTNER_ID= '" + userid + "')";
        log.info("query getMessageList= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            MessagesList messageslist = new MessagesList();
            int id = rs.getInt("N_MESSAGE_ID");
            messageslist.setId(id);
            String status = rs.getString("B_STATUS");
            messageslist.setStatus(status);
            String docid = rs.getString("vch_document_id");
            messageslist.setDocid(docid);
            String ttype = rs.getString("vch_description");
            messageslist.setType(ttype);
            String from = rs.getString("sender_tp.company");
            messageslist.setFrom(from);
            String to = rs.getString("recipient_tp.company");
            messageslist.setTo(to);
            int download = rs.getInt("b_downloadable");
            messageslist.setDownload(download);
            String date = rs.getString("dt_received");
            messageslist.setDate(date);
            list.add(messageslist);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public Collection getPartnerMessageList(String user) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String userid = user;
        String query = "SELECT xy_business_message.ID, xy_business_message.dt_received,xy_business_message.M_status,registration.COMPANY, xy_business_message.partner_type, xy_business_message.subject, xy_business_message.message FROM xy_business_message,registration where xy_business_message.from_partner_id = registration.ID and xy_business_message.partner_id = '" + userid + "'";
        log.info("query getPartnerMessageList= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            PartnerMessagesList pmessageslist = new PartnerMessagesList();
            int id = rs.getInt("ID");
            pmessageslist.setId(id);
            int status = rs.getInt("M_status");
            pmessageslist.setStatus(status);
            String date = rs.getString("dt_received");
            pmessageslist.setDate(date);
            String from = rs.getString("registration.company");
            pmessageslist.setFrom(from);
            String subject = rs.getString("subject");
            pmessageslist.setSubject(subject);
            String message = rs.getString("message");
            pmessageslist.setMessage(message);
            list.add(pmessageslist);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public Collection getInBoundMessageList(String user) throws DataAccessException, SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String userid = user;
        String query = "SELECT xy_message.N_MESSAGE_ID,xy_message.N_SENDER_PARTNER_ID,sender_tp.company,xy_message.N_RECIPIENT_PARTNER_ID, recipient_tp.company,xy_message.dt_received,xy_txn_type.vch_description,xy_message.B_STATUS,xy_message.VCH_PATH,xy_message.N_QUEUE_ID,xy_txn_type.b_downloadable,xy_txn_type.b_viewable ,xy_message.vch_document_id FROM xy_message, xy_txn_type, registration sender_tp,registration recipient_tp WHERE xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and sender_tp.id = xy_message.N_SENDER_PARTNER_ID and recipient_tp.id = xy_message.N_RECIPIENT_PARTNER_ID and xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and xy_message.b_status!='2' and xy_message.N_RECIPIENT_PARTNER_ID= '" + userid + "' order by dt_received desc";
        log.info("query getInBoundMessageList= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            MessagesList messageslist = new MessagesList();
            int id = rs.getInt("N_MESSAGE_ID");
            messageslist.setId(id);
            int senderId = rs.getInt("N_SENDER_PARTNER_ID");
            messageslist.setSenderId(senderId);
            String status = rs.getString("B_STATUS");
            messageslist.setStatus(status);
            String docid = rs.getString("vch_document_id");
            messageslist.setDocid(docid);
            String ttype = rs.getString("vch_description");
            messageslist.setType(ttype);
            String from = rs.getString("sender_tp.company");
            messageslist.setFrom(from);
            String to = rs.getString("recipient_tp.company");
            messageslist.setTo(to);
            int download = rs.getInt("b_downloadable");
            messageslist.setDownload(download);
            String date = rs.getString("dt_received");
            messageslist.setDate(date);
            String fname = rs.getString("vch_path");
            messageslist.setFilename(fname);
            list.add(messageslist);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public Collection getOutBoundMessageList(String user) throws DataAccessException, SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String userid = user;
        String query = "SELECT xy_message.N_MESSAGE_ID,xy_message.N_SENDER_PARTNER_ID,sender_tp.company,xy_message.N_RECIPIENT_PARTNER_ID, recipient_tp.company,xy_message.dt_received,xy_txn_type.vch_description,xy_message.B_STATUS,xy_message.VCH_PATH,xy_message.N_QUEUE_ID,xy_txn_type.b_downloadable,xy_txn_type.b_viewable ,xy_message.vch_document_id FROM xy_message, xy_txn_type, registration sender_tp,registration recipient_tp WHERE xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and sender_tp.id = xy_message.N_SENDER_PARTNER_ID and recipient_tp.id = xy_message.N_RECIPIENT_PARTNER_ID and xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and xy_message.N_SENDER_PARTNER_ID= '" + userid + "'";
        log.info("query getOutBoundMessageList= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            MessagesList messageslist = new MessagesList();
            int id = rs.getInt("N_MESSAGE_ID");
            messageslist.setId(id);
            String status = rs.getString("B_STATUS");
            messageslist.setStatus(status);
            String docid = rs.getString("vch_document_id");
            messageslist.setDocid(docid);
            String ttype = rs.getString("vch_description");
            messageslist.setType(ttype);
            String from = rs.getString("sender_tp.company");
            messageslist.setFrom(from);
            String to = rs.getString("recipient_tp.company");
            messageslist.setTo(to);
            int download = rs.getInt("b_downloadable");
            messageslist.setDownload(download);
            String date = rs.getString("dt_received");
            messageslist.setDate(date);
            list.add(messageslist);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

     public int getUserId(String strUserid, String password) throws DataAccessException, java.sql.SQLException {

        Connection conn = this.getSession().connection();
        //Write jdbc code to validate the user against database
        Statement smt = conn.createStatement();
        ResultSet rs;
        String query = "select id from registration where loginid='" + strUserid + "' and password ='" + password + "'";
        log.info("query getUserId= " + query);
        rs = smt.executeQuery(query);
        rs.next();
        int id = rs.getInt("id");
        smt.close();
        rs.close();
        conn.close();
        return id;
    }

   public String getUploadFolder() throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs;
        String result = "";
        String query = "SELECT VCH_CONTENT  FROM sk_parameters where vch_name = 'fileuploadfolder'";
        log.info("query getUploadFolder= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            result = rs.getString("VCH_CONTENT");
        }
        smt.close();
        rs.close();
        conn.close();
        return result;
    }

    
// retrive user forget password
    public String[] retriveUserForgetPassword(String strUserName, String strEmail) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs;
        String query;
        if (!strUserName.equalsIgnoreCase("")) {
            query = "select password,email,loginid from user_login where loginid='" + strUserName + "'";
        } else {
            query = "select password,email,loginid from user_login where email='" + strEmail + "'";
        }
        log.info("query retriveUserForgetPassword= " + query);
        rs = smt.executeQuery(query);
        String[] returnValues = new String[3];
        while (rs.next()) {
            returnValues[0] = rs.getString("password");
            returnValues[1] = rs.getString("email");
            returnValues[2] = rs.getString("loginid");
        }
        smt.close();
        rs.close();
        conn.close();
        if (returnValues[0] != null) {
            return returnValues;
        } else {
            String[] errorValues = new String[2];
            errorValues[0] = "error";
            return errorValues;
        }
    }

    public int resetUserForgetPassword(String strUserName, String password, String oldpassword) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        String query = "UPDATE registration SET password = '" + password + "' where loginid='" + strUserName + "' and password = '" + oldpassword + "'";
        log.info(" 1query resetUserForgetPassword= " + query);
        int result = smt.executeUpdate(query);
        log.info(" 1result resetUserForgetPassword = " + result);

        query = "UPDATE user_login SET password = '" + password + "' where loginid='" + strUserName + "' and password = '" + oldpassword + "'";
        log.info(" 2query resetUserForgetPassword= " + query);
        result = smt.executeUpdate(query);
        log.info(" 2result resetUserForgetPassword= " + result);
        smt.close();
        conn.close();
        return result;
    }

    public void addPartnerMessage(int str1, int str2, String str3, String str4, String str5) {
        Connection conn = this.getSession().connection();
        int from_id = 0;
        int to_id = 0;
        String ptype = "";
        String subject = "";
        String message = "";

        Statement smt;
        try {
            from_id = str1;
            to_id = str2;
            ptype = str3;
            subject = str4;
            message = str5;
            smt = conn.createStatement();
            String query = "insert into xy_business_message (from_partner_id,partner_id,partner_type,subject,message) VALUES (" + from_id + ", " + to_id + ", '" + ptype + "', '" + subject + "','" + message + "')";
            log.info("query addPartnerMessage= " + query);
            boolean result = smt.execute(query);
            log.info("result addPartnerMessage= " + result);
            smt.close();
            conn.close();
        } catch (SQLException ex) {
            log.error("Exception Message", ex);
        }
    }

    public void addPartnerTxnFormat(int str1, int str2, String str3, String str4) {
        Connection conn = this.getSession().connection();
        Statement smt;
        try {
            smt = conn.createStatement();
            String query = "insert into xy_partner_txn_format(sender_id,receiver_id,txn_type,format) VALUES (" + str1 + ", " + str2 + ", '" + str3 + "', '" + str4 + "')";
            log.info("query addPartnerTxnFormat= " + query);
            boolean result = smt.execute(query);
            log.info("result addPartnerTxnFormat= " + result);
            smt.close();
            conn.close();
        } catch (SQLException ex) {
            log.error("Exception Message", ex);
        }
    }

    public Collection getPartnerMessage(String id) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "SELECT xy_business_message.ID, xy_business_message.dt_received,xy_business_message.M_status,xy_business_message.from_partner_id,registration.COMPANY, xy_business_message.partner_type, xy_business_message.subject, xy_business_message.message FROM xy_business_message,registration where xy_business_message.from_partner_id = registration.ID and xy_business_message.id = '" + id + "'";
        log.info("query getPartnerMessage= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            PartnerMessagesList pmessageslist = new PartnerMessagesList();

            int messageid = rs.getInt("ID");
            pmessageslist.setId(messageid);
            int status = rs.getInt("M_status");
            pmessageslist.setStatus(status);
            String date = rs.getString("dt_received");
            pmessageslist.setDate(date);
            String from = rs.getString("registration.company");
            pmessageslist.setFrom(from);
            int fromid = rs.getInt("from_partner_id");
            pmessageslist.setFromid(fromid);
            String subject = rs.getString("subject");
            pmessageslist.setSubject(subject);
            String message = rs.getString("message");
            pmessageslist.setMessage(message);
            String ptype = rs.getString("partner_type");
            pmessageslist.setType(ptype);
            list.add(pmessageslist);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public Collection getMessage(String mid) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "SELECT xy_message.N_MESSAGE_ID,xy_message.N_SENDER_PARTNER_ID,sender_tp.company,xy_message.N_RECIPIENT_PARTNER_ID, recipient_tp.company,xy_message.dt_received,xy_txn_type.vch_description,xy_message.B_STATUS,xy_message.VCH_PATH,xy_message.N_QUEUE_ID,xy_txn_type.b_downloadable,xy_txn_type.b_viewable ,xy_message.vch_document_id FROM xy_message, xy_txn_type, registration sender_tp,registration recipient_tp WHERE xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and sender_tp.id = xy_message.N_SENDER_PARTNER_ID and recipient_tp.id = xy_message.N_RECIPIENT_PARTNER_ID and xy_txn_type.n_type_id = xy_message.N_TRANSACTION_TYPE and xy_message.N_MESSAGE_ID = '" + mid + "'";
        log.info("query getMessage= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            MessagesList messageslist = new MessagesList();
            int id = rs.getInt("N_MESSAGE_ID");
            messageslist.setId(id);
            String status = rs.getString("B_STATUS");
            messageslist.setStatus(status);
            String docid = rs.getString("vch_document_id");
            messageslist.setDocid(docid);
            String ttype = rs.getString("vch_description");
            messageslist.setType(ttype);
            String from = rs.getString("N_SENDER_PARTNER_ID");
            messageslist.setFrom(from);
            String to = rs.getString("N_RECIPIENT_PARTNER_ID");
            messageslist.setTo(to);
            int download = rs.getInt("b_downloadable");
            messageslist.setDownload(download);
            String date = rs.getString("dt_received");
            messageslist.setDate(date);
            String fname = rs.getString("vch_path");
            messageslist.setFilename(fname);
            list.add(messageslist);
        }
        log.info("list size = " + list.size());
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public void enablePartner(int buyid, int supid, String buy_sup_num, String buy_sup_name) {
        Connection conn = this.getSession().connection();
        Statement smt;
        try {
            smt = conn.createStatement();
            String query = "insert into xy_partner_link (buyer_id,supplier_id,buy_sup_no,buy_sup_name) VALUES (" + buyid + ", " + supid + ", '" + buy_sup_num + "', '" + buy_sup_name + "')";
            log.info("query enablePartner= " + query);
            boolean result = smt.execute(query);
            log.info("result enablePartner= " + result);
            smt.close();
            conn.close();
        } catch (SQLException ex) {
            log.error("Exception Message", ex);
        }
    }

     public Collection getOrderList(String buyid, String supid) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "SELECT xy_order.ID, xy_order.OrderNo,  registration.COMPANY, xy_order.order_date,xy_order.status,xy_order.delivery_date,xy_order.comment    FROM xy_order,registration where buyid = '" + buyid + "' and xy_order.supid=registration.ID and xy_order.supid='" + supid + "' order by xy_order.order_date desc";
        log.info("query getOrderList= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            OrderList orderslist = new OrderList();
            int id = rs.getInt("ID");
            orderslist.setId(id);
            String orderno = rs.getString("OrderNo");
            orderslist.setOrderno(orderno);
            String supname = rs.getString("company");
            orderslist.setSupname(supname);
            String date = rs.getString("order_date");
            orderslist.setOrderdate(date);
            String Status = rs.getString("status");
            orderslist.setStatus(Status);
            String deldate = rs.getString("delivery_date");
            orderslist.setDeldate(deldate);
            String comment = rs.getString("comment");
            orderslist.setComment(comment);
            list.add(orderslist);
        }
        log.info("list size getOrderList= " + list.size());
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

    public Collection getArchivedOrderitemlinesdetails(String orderid) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "select b.gtin, b.Product_Name,b.UOM,a.QTY, b.CategoryID," +
                "c.buyer_item_number,b.Product_Code1,b.Product_Description,c.price," +
                "b.Action,b.BaseProductNumber,b.ProductIDExtension," +
                "b.CatalogProviderIDRef,b.CatalogIDRef,b.ProductIDRef,b.ComparableUOM," +
                "b.ComparableUOMConversionFactor,b.Manufacturer,b.ManuPartNumber,b.LeadTime," +
                "b.LeadTimeUOM,b.ValidFrom,b.ValidUntil,b.CountryOfOrigin,b.MinOrder," +
                "b.LotSize,b.ShortDescription,b.LongDescriptionValue,b.LongDescriptionPurpose," +
                "b.CatalogContractID,b.CatalogContractItemID,b.AttachmentURL,b.AttachmentPurpose," +
                "b.AttachmentMIMEType,b.ProductAttachment_ShortDescription,b.ProductAttachment_LongDescription," +
                "b.ProductAttachment_LongDescriptionPurpose,b.RelatedProduct,b.AttributeID,b.AttributeUnit," +
                "b.AttributeValue,b.Type from xy_order_line_items_archive a,xy_order_items_details b,buyer_products_mapping c,xy_partner_link d,xy_order_archive e " +
                "where a.Item_history_ID = b.id " +
                "and c.productcode = b.gtin " +
                "and a.ORDER_ID=e.id " +
                "and e.buyid = d.Buyer_Id " +
                "and e.supid = d.Supplier_Id " +
                "and d.ID = c.partner_link_id " +
                "and a.ORDER_ID='" + orderid + "' order by c.id";


        log.info("query getArchivedOrderitemlinesdetails= " + query);
        rs = smt.executeQuery(query);
        int i = 1;
        while (rs.next()) {
            ItemList item = new ItemList();
            item.setGtin(rs.getString("gtin"));
            item.setName(rs.getString("Product_Name"));
            item.setUom(rs.getString("uom"));
            item.setQty(rs.getString("qty"));
            item.setCategoryID(rs.getString("CategoryID"));
            item.setProductid(rs.getString("buyer_item_number"));
            item.setProduct_Code1(rs.getString("Product_Code1"));
            item.setDesc(rs.getString("Product_Description"));
            item.setPrice(rs.getFloat("price"));
            item.setAction(rs.getString("Action"));
            item.setBaseProductNumber(rs.getString("BaseProductNumber"));
            item.setProductIDExtension(rs.getString("ProductIDExtension"));
            item.setCatalogProviderIDRef(rs.getString("CatalogProviderIDRef"));
            item.setCatalogIDRef(rs.getString("CatalogIDRef"));
            item.setProductIDRef(rs.getString("ProductIDRef"));
            item.setComparableUOM(rs.getString("ComparableUOM"));
            item.setComparableUOMConversionFactor(rs.getString("ComparableUOMConversionFactor"));
            item.setManufacturer(rs.getString("Manufacturer"));
            item.setManuPartNumber(rs.getString("ManuPartNumber"));
            item.setLeadTime(rs.getInt("LeadTime"));
            item.setLeadTimeUOM(rs.getString("LeadTimeUOM"));
            item.setValidFrom(rs.getString("ValidFrom"));
            item.setValidUntil(rs.getString("ValidUntil"));
            item.setCountryOfOrigin(rs.getString("CountryOfOrigin"));
            item.setMinOrder(rs.getInt("MinOrder"));
            item.setLotSize(rs.getInt("LotSize"));
            item.setShortDescription(rs.getString("ShortDescription"));
            item.setLongDescriptionValue(rs.getString("LongDescriptionValue"));
            item.setLongDescriptionPurpose(rs.getString("LongDescriptionPurpose"));
            item.setCatalogContractID(rs.getString("CatalogContractID"));
            item.setCatalogContractID(rs.getString("CatalogContractItemID"));
            item.setAttachmentURL(rs.getString("AttachmentURL"));
            item.setAttachmentPurpose(rs.getString("AttachmentPurpose"));
            item.setAttachmentMIMEType(rs.getString("AttachmentMIMEType"));
            item.setProductAttachment_ShortDescription(rs.getString("ProductAttachment_ShortDescription"));
            item.setProductAttachment_LongDescription(rs.getString("ProductAttachment_LongDescription"));
            item.setProductAttachment_LongDescriptionPurpose(rs.getString("ProductAttachment_LongDescriptionPurpose"));
            item.setRelatedProduct(rs.getString("RelatedProduct"));
            item.setAttributeID(rs.getString("AttributeID"));
            item.setAttributeUnit(rs.getString("AttributeUnit"));
            item.setAttributeValue(rs.getString("AttributeValue"));
            item.setType(rs.getString("Type"));
            item.setSequenceNumber(new Integer(i));
            list.add(item);
            i++;
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }


   
    public void cancelOrder(String ord_id, String reason) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt;

        try {
            smt = conn.createStatement();
            String query = "insert into xy_cancel_order (orderid,reason) values ('" + ord_id + "','" + reason + "')";
            log.info("1query cancelOrder= " + query);
            boolean result = smt.execute(query);
            log.info("1result cancelOrder= " + result);
            query =
                    "UPDATE xy_order SET status = 'Cancelled' WHERE id = '" + ord_id + "'";
            log.info("2query cancelOrder= " + query);
            result =
                    smt.execute(query);
            log.info("2result cancelOrder= " + result);
            smt.close();
            conn.close();
        } catch (SQLException ex) {
             log.error("Exception Message", ex);
        }

    }

    
    public void updateOrderStatus(String orderid, String status) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt;
        try {
            smt = conn.createStatement();
            String query = "UPDATE xy_order SET status = '" + status + "' where id = '" + orderid + "'";
            log.info("query updateOrderStatus= " + query);
            boolean result = smt.execute(query);
            log.info("result updateOrderStatus= " + result);
            smt.close();
            conn.close();
        } catch (SQLException ex) {
            log.error("Exception Message", ex);
        }
    }

    
     public String getSupplierName(String supid) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs;
        String result = "";
        String query = "select company from registration where id = '" + supid + "'";
        log.info("query getSupplierName= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            result = rs.getString("company");
        }
        smt.close();
        rs.close();
        conn.close();
        return result;
    }

    public String getBannerNameByNexusId(String nexusId) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs;
        String result = "";
        String query = "select banner_name from xy_member_logos where nexus_id = '" + nexusId + "'";
        log.info("query getBannerNameByNexusId= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            result = rs.getString("banner_name");
        }
        smt.close();
        rs.close();
        conn.close();
        return result;
    }

    public Order getOrderByOrderId(String orderid) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs;
        Order order = null;
        String query = "select o.orderno,o.delivery_date,o.comment from xy_order as o where o.id = '" + orderid + "'";
        log.info("query getOrderByOrderId= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            order = new Order(rs.getString("orderno"), rs.getString("delivery_date"), rs.getString("comment"));
        }
        smt.close();
        rs.close();
        conn.close();
        return order;
    }

    public Order getArchivedOrderByOrderId(String orderid) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs;
        Order order = null;
        String query = "select o.orderno,o.delivery_date,o.comment from xy_order_archive as o where o.id = '" + orderid + "'";
        log.info("query getArchivedOrderByOrderId= " + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            order = new Order(rs.getString("orderno"), rs.getString("delivery_date"), rs.getString("comment"));
        }
        smt.close();
        rs.close();
        conn.close();
        return order;
    }

    public String findRuleValueMapping(String rule, int nexusid) throws SQLException {
         log.info("findRuleValueMapping  rule:" + rule+"..nexusId..."+nexusid);
        Connection conn = this.getSession().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String desc = "";
        String selectStatement = "select a.VALUE from xy_rules_values a,xy_rules_values_mapping b,xy_rules d   " +
                "where a.RULE_ID = d.ID and d.DESCRIPTION = ? and a.ID = b.RULES_VALUES_ID and b.XY_PLINK_ID=?";
        ps = conn.prepareStatement(selectStatement);
        ps.setString(1, rule);
        ps.setInt(2, nexusid);
        rs = ps.executeQuery();
        log.info("selectStatement " + selectStatement);
        while (rs.next()) {
            desc = rs.getString("value");
        }
          ps.close();
        rs.close();
        conn.close();
        return desc;

    }
    public String findProducttable(String rule, int supId, int buyId) throws SQLException {
        log.info("findProducttable  rule:" + rule + "..nexusId..." + supId + "..buyId==" + buyId);
        Connection conn = this.getSession().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String desc = "";
        String selectStatement = "";

        selectStatement = "SELECT a.VALUE FROM xy_rules_values a, xy_rules_values_mapping b, xy_rules d,xy_partner_link c WHERE a.RULE_ID = d.ID AND d.DESCRIPTION = ? AND a.ID = b.RULES_VALUES_ID AND b.XY_PLINK_ID = c.id and c.supplier_Id=? and c.buyer_id=?";
        ps = conn.prepareStatement(selectStatement);
        ps.setString(1, rule);
        ps.setInt(2, supId);
        ps.setInt(3, buyId);

        rs = ps.executeQuery();
        log.info("selectStatement " + selectStatement);
        while (rs.next()) {
            desc = rs.getString("value");
        }
        ps.close();
        rs.close();
        conn.close();
        return desc;
    }

    public Collection getUserDetail(String userid) throws DataAccessException, java.sql.SQLException {
        Connection conn = this.getSession().connection();
        Statement smt = conn.createStatement();
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String query = "select * from user_login where id='" + userid + "'";
        log.info("query getUserDetail=" + query);
        rs = smt.executeQuery(query);
        while (rs.next()) {
            UsersList userslist = new UsersList();
            int id = rs.getInt("id");
            userslist.setId(id);
            String loginid = rs.getString("loginid");
            userslist.setLoginid(loginid);
            String company = rs.getString("password");
            userslist.setCompany(company);
            String contact = rs.getString("name");
            userslist.setContact(contact);
            String email = rs.getString("email");
            userslist.setEmail(email);
            list.add(userslist);
        }
        rs.close();
        smt.close();
        conn.close();
        return list;
    }

     public void insertLoginAttempts(int buyId,int supId,String username)throws DataAccessException, java.sql.SQLException {
         log.info("insertLoginAttempts  buyId:" + buyId+"..supId..."+supId+"...username="+username);
        Connection conn = this.getSession().connection();
        PreparedStatement ps = null;
        boolean flag=false;
        String selectStatement = "insert into xy_login_attempts(buyId,supId,username) values(?,?,?)";
        ps = conn.prepareStatement(selectStatement);
        ps.setInt(1, buyId);
        ps.setInt(2, supId);
        ps.setString(3,username);
        flag = ps.execute();
        log.info("selectStatement " + selectStatement+"...result.flag=="+flag);
        
        ps.close();
        conn.close();
     }

     public void insertPunchoutSetup(String buyerCoockie,String fromURL) throws SQLException{
         log.info("insertPunchoutSetup buyerCoockie=" + buyerCoockie);
         Connection conn = this.getSession().connection();
        PreparedStatement ps = null;
        String selectStatement = "insert into xy_punchout(buyerCoockie,fromURL) values(?,?)";
        ps = conn.prepareStatement(selectStatement);
        ps.setString(1,buyerCoockie);
        ps.setString(2,fromURL);
        ps.execute();

        ps.close();
        conn.close();
     }

    public HashMap getPunchoutCoockie() throws SQLException{
        
         ResultSet rs = null;
         Connection conn = this.getSession().connection();
        PreparedStatement ps = null;
        HashMap hm=new HashMap();
        log.info("getPunchout Coockie ==");
        String selectStatement = "select buyerCoockie,fromURL from xy_punchout order by requestDate desc limit 1";
        ps = conn.prepareStatement(selectStatement);
        rs=ps.executeQuery();
        if(rs.next())
        {
            hm.put("buyerCoockie", rs.getString("buyerCoockie"));
            hm.put("fromURL", rs.getString("fromURL"));
        }

        
        rs.close();
        ps.close();
        conn.close();
        return hm;
    }

     public String getFolderPath(String name) throws SQLException {
        Connection conn = this.getSession().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String folderPath = "";
        String selectStatement = "SELECT FolderPath FROM xy_txn_comm_type where name=?";
        try {
            ps = conn.prepareStatement(selectStatement);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                folderPath = rs.getString("FolderPath");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
        return folderPath;
    }
}
