/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import com.nexus.domain.Partner;
import com.nexus.domain.PartnerLink;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Terry
 */
public class PartnerLinkDAOImpl implements PartnerLinkDAO {

    Logger log=Logger.getLogger(PartnerLinkDAOImpl.class);
    private SessionFactory sessionFactory;

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int findMyPartnersListCount(String id, String search, String company) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("1 findMyPartnersListCount..id.."+id+"..search.."+search+"...company.."+company);
        String selectStatement = "SELECT COUNT(distinct registration.ID) FROM registration,xy_partner_link,us_country where  registration.COUNTRY = us_country.N_COUNTRY_ID and (xy_partner_link.Buyer_Id=registration.id or xy_partner_link.Supplier_Id=registration.id) and (xy_partner_link.Buyer_Id = ? or xy_partner_link.Supplier_Id = ?) and (registration.id != ?)";
        if (search.equals("true")) {
            selectStatement += " and registration.company like ?";
        }
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        ps.setInt(2, new Integer(id).intValue());
        ps.setInt(3, new Integer(id).intValue());
        if (search.equals("true")) {
            ps.setString(4, "%" + company + "%");
        }
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List<Partner> findPartnersBySupplierId(int supllierId) {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Partner> partnerList = new ArrayList<Partner>();
        try {
            String sql = "SELECT distinct(r.ID),r.COMPANY FROM registration r,xy_partner_link x where r.id = x.Buyer_Id and x.Supplier_Id =?;";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, supllierId);
            rs = ps.executeQuery();
            while (rs.next()) {
                partnerList.add(new Partner(String.valueOf(rs.getInt("ID")), rs.getString("COMPANY")));
            }
        } catch (SQLException e) {
            log.error("Error while reading partners: " + e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return partnerList;
    }
    
    public List findMyPartnersList(String id, String search, String company, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("1 findMyPartnersList..id.."+id+"..search.."+search+"...company.."+company);
        //String selectStatement = "SELECT distinct(registration.ID),registration.COMPANY,registration.EMAIL,registration.PHNO,registration.STATE,registration.COMPANY_URL,us_country.vch_country_name FROM registration,xy_partner_link,us_country where  registration.COUNTRY = us_country.N_COUNTRY_ID and (xy_partner_link.Buyer_Id=registration.id or xy_partner_link.Supplier_Id=registration.id) and (xy_partner_link.Buyer_Id = ? or xy_partner_link.Supplier_Id = ?) and (registration.id != ?)";
        String selectStatement = "SELECT distinct(registration.ID),registration.COMPANY,registration.EMAIL,registration.PHNO,registration.STATE,product_price_types.priceTypeName,us_country.vch_country_name FROM registration,xy_partner_link,us_country,product_price_types where  registration.COUNTRY = us_country.N_COUNTRY_ID and (xy_partner_link.Buyer_Id=registration.id or xy_partner_link.Supplier_Id=registration.id) and (xy_partner_link.Buyer_Id = ? or xy_partner_link.Supplier_Id = ?) and (registration.id != ?) and product_price_types.supplierId=? and product_price_types.priceType=registration.Member_Type";
        if (search.equals("true")) {
            selectStatement += " and registration.company like ?";
        }
        selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        ps.setInt(2, new Integer(id).intValue());
        ps.setInt(3, new Integer(id).intValue());
        ps.setInt(4, new Integer(id).intValue());
        if (search.equals("true")) {
            ps.setString(5, "%" + company + "%");
        }
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("registration.ID"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("registration.company"));
            cell.add(rs.getString("registration.email"));
            cell.add(rs.getString("registration.phno"));
            cell.add(rs.getString("registration.state"));
            cell.add(rs.getString("vch_country_name"));
            //cell.add(rs.getString("registration.company_url"));
            cell.add(rs.getString("priceTypeName"));
            cell.add(new Integer(rs.getInt("registration.ID")).toString());
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public int findMyPartnersListCount(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("2 findMyPartnersListCount..id.."+id);
        String selectStatement = "SELECT COUNT(distinct registration.ID) FROM registration,xy_partner_link,us_country where  registration.COUNTRY = us_country.N_COUNTRY_ID and (xy_partner_link.Buyer_Id=registration.id or xy_partner_link.Supplier_Id=registration.id) and (xy_partner_link.Buyer_Id = ? or xy_partner_link.Supplier_Id = ?) and (registration.id != ?)";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setInt(2, id);
        ps.setInt(3, id);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List findMyPartnersList(int id, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("2 findMyPartnersList..id.."+id);
        //String selectStatement = "SELECT distinct(registration.ID),registration.COMPANY,registration.member_type FROM registration,xy_partner_link,us_country where  registration.COUNTRY = us_country.N_COUNTRY_ID and (xy_partner_link.Buyer_Id=registration.id or xy_partner_link.Supplier_Id=registration.id) and (xy_partner_link.Buyer_Id = ? or xy_partner_link.Supplier_Id = ?) and (registration.id != ?)";
        String selectStatement = "SELECT distinct(registration.ID),registration.COMPANY,product_price_types.priceTypeName FROM registration,xy_partner_link,us_country,product_price_types where  registration.COUNTRY = us_country.N_COUNTRY_ID and (xy_partner_link.Buyer_Id=registration.id or xy_partner_link.Supplier_Id=registration.id) and (xy_partner_link.Buyer_Id = ? or xy_partner_link.Supplier_Id = ?) and (registration.id != ?) and product_price_types.supplierId=? and product_price_types.priceType=registration.Member_Type";
        selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setInt(2, id);
        ps.setInt(3, id);
         ps.setInt(4, id);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("registration.ID"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("registration.company"));
            cell.add(rs.getString("priceTypeName"));
//            int priceType = new Integer(rs.getString("registration.member_type")).intValue();
//            switch (priceType) {
//                case 1:
//                    cell.add("Price Type 1");
//                    break;
//                case 2:
//                    cell.add("Price Type 2");
//                    break;
//                case 3:
//                    cell.add("Price Type 3");
//                    break;
//                case 4:
//                    cell.add("Price Type 4");
//                    break;
//                case 5:
//                    cell.add("Price Type 5");
//                    break;
//                case 6:
//                    cell.add("Price Type 6");
//                    break;
//                case 7:
//                    cell.add("Price Type 7");
//                    break;
//                case 8:
//                    cell.add("Price Type 8");
//                    break;
//                default:
//                    cell.add("");
//                    break;
//            }
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public PartnerLink findPartnerLinkByBuyerIdAndSupplierId(String buyerId, String supplierId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("2 findPartnerLinkByBuyerIdAndSupplierId..buyerId.."+buyerId+"..supplierId.."+supplierId);
        String selectStatement = "SELECT pl.Buy_Sup_No,pl.Buy_Sup_Name FROM xy_partner_link as pl where pl.Buyer_Id=? and pl.Supplier_Id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, buyerId);
        ps.setString(2, supplierId);
        rs = ps.executeQuery();
        rs.next();
        return new PartnerLink(rs.getString("Buy_Sup_No"), rs.getString("Buy_Sup_Name"));
    }

    public int findSupplierIdByBuyerId(int buyerId) throws SQLException {
        int supllier_id=0;
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findSupplierIdByBuyerId..buyerId.."+buyerId);
        String selectStatement = "select supplier_id from xy_partner_link where buyer_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, buyerId);
        rs = ps.executeQuery();
        if(rs.next())
            supllier_id=rs.getInt("supplier_id");
        return supllier_id;
    }
}
