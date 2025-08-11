package com.nexus.struts.action;

import com.nexus.domain.Registration;
import com.nexus.services.CatalogueService;
import com.nexus.services.ServiceFinder;
import com.nexus.web.common.IndustryType;
import com.nexus.web.common.MapDocumet;
import com.nexus.web.common.PartnerMessagesList;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.ServletActionContext;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.struts2.interceptor.ServletRequestAware;

import org.springframework.dao.DataAccessException;
import org.apache.log4j.Logger;
public class PartnerAction extends ActionSupport implements ServletRequestAware {

    Logger log=Logger.getLogger(PartnerAction.class);
    private Collection showmessagecol;
    private String partnertype = "";
    private String partner = "";
    private String subject = "";
    private String message = "";
    private HttpServletRequest request;
    private String myname = "";
    private String mycompany = "";
    private String mem_type = "";
    private Integer id = 0;
    private String userid = "";
    private String password = "";
    private String email = "";
    private String address = "";
    private String phno1 = "";
    private String phno2 = "";
    private String phno3 = "";
    private String company = "";
    private String contact = "";
    private String city = "";
    private String fxno1 = "";
    private String fxno2 = "";
    private String fxno3 = "";
    private String state = "";
    private String country = "";
    private String postcode = "";
    private String address2 = "";
    private String company_url = "";
    private Collection col;
    private Collection col2;
    private Collection col1;
    private Collection messagecol;
    private String username = "";
    private String pass = "";
    private String BuyName = "";
    private String SupName = "";
    private String SupNo = "";
    private String BuyNo = "";
    private String messageid = "";
    int partnerid = 0;
    private HashMap partners;
    private Collection countries;
    private Collection pricetypes;
    private Collection transplans;
    private ArrayList industryTypeList;
    private ArrayList industryNameList;
    Map industryNameMap = new HashMap();
    Boolean isEnabled = false;
    String delform = "";
    String recform = "";
    String inboundTrans = "";
    String outboundTrans = "";
    String trplan = "";
    private int priceType = 0;
    private int microcatAccountAmount;
    private int newMemberId;
    private List amountList;
    private int nexusId;
    private CatalogueService catalogueService;
    public String getTrplan() {
        return trplan;
    }

    public void setTrplan(String trplan) {
        this.trplan = trplan;
    }

    public String getMem_type() {
        return mem_type;
    }

    public void setMem_type(String mem_type) {
        this.mem_type = mem_type;
    }

    public String getDelform() {
        return delform;
    }

    public void setDelform(String delform) {
        this.delform = delform;
    }

    public String getRecform() {
        return recform;
    }

    public void setRecform(String recform) {
        this.recform = recform;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getBuyNo() {
        return BuyNo;
    }

    public void setBuyNo(String BuyNo) {
        this.BuyNo = BuyNo;
    }

    public String getBuyName() {
        return BuyName;
    }

    public void setBuyName(String BuyName) {
        this.BuyName = BuyName;
    }

    public String getSupName() {
        return SupName;
    }

    public void setSupName(String SupName) {
        this.SupName = SupName;
    }

    public String getSupNo() {
        return SupNo;
    }

    public void setSupNo(String SupNo) {
        this.SupNo = SupNo;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String value) {
        username = value;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String value) {
        pass = value;
    }

    public Collection getCol() {
        return col;
    }

    public Collection getMessagecol() {
        return messagecol;
    }

    public Collection getCol1() {
        return col1;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhno1() {
        return phno1;
    }

    public String getPhno2() {
        return phno2;
    }

    public String getPhno3() {
        return phno3;
    }

    public void setPhno1(String phno1) {
        this.phno1 = phno1;
    }

    public void setPhno2(String phno2) {
        this.phno2 = phno2;
    }

    public void setPhno3(String phno3) {
        this.phno3 = phno3;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFxno1() {
        return fxno1;
    }

    public String getFxno2() {
        return fxno2;
    }

    public String getFxno3() {
        return fxno3;
    }

    public void setFxno1(String fxno1) {
        this.fxno1 = fxno1;
    }

    public void setFxno2(String fxno2) {
        this.fxno2 = fxno2;
    }

    public void setFxno3(String fxno3) {
        this.fxno3 = fxno3;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCompany_url() {
        return this.company_url;
    }

    public void setCompany_url(String company_url) {
        this.company_url = company_url;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public Integer getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(Integer partnerid) {
        this.partnerid = partnerid;
    }

    public String getPartnertype() {
        return partnertype;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }

    public String getInboundTrans() {
        return inboundTrans;
    }

    public void setInboundTrans(String inboundTrans) {
        this.inboundTrans = inboundTrans;
    }

    public String getOutboundTrans() {
        return outboundTrans;
    }

    public void setOutboundTrans(String outboundTrans) {
        this.outboundTrans = outboundTrans;
    }

    public void setPartnertype(String partnertype) {
        this.partnertype = partnertype;
    }

    public HashMap getPartners() {
        return partners;
    }

    public Collection getCountries() {
        return countries;
    }

    public Collection getTransplans() {
        return transplans;
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    public String getMycompany() {
        return mycompany;
    }

    public void setMycompany(String mycompany) {
        this.mycompany = mycompany;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }

    // For Industry Group
    String industryType = "";
    String industryName = "";

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public List getIndustryTypeList() {
        return industryTypeList;
    }

    public List getIndustryNameList() {
        ValueStack stack = ServletActionContext.getValueStack(ServletActionContext.getRequest());
        Object IndustryType = stack.findValue("top");
        if (IndustryType != null && IndustryType instanceof IndustryType) {
            List l = (List) industryNameMap.get(IndustryType);
            return l;
        }
        return Collections.EMPTY_LIST;
    }

    public void sendRegisterationMail(String emailid, String contactname, String username, String pass, String nexusid) {
        try {
            //sending mail
            com.nexus.web.common.SendMail mailBean = (com.nexus.web.common.SendMail) ServiceFinder.getContext(request).getBean(com.nexus.web.common.ProjectConstants.MAIL_BEAN);
//            String[] reciepent = {emailid};
            String reciepent[] = emailid.split(",");
            String emailsubject = "Successful Regisitration on Nexus Business Network";
            String emailmessage = "Mr/Mrs " + contactname + ",";
            emailmessage += "\n\n Welcome and congratulations for registering your company on the Nexus B2B Network.  Your registration has been successful and your Nexus member ID number is " + nexusid + ".";
            emailmessage += "\n\n You have been given default system administrator rights to Nexus with the ";
            emailmessage += "\n\n Username = " + username;
            emailmessage += "\n\n Password = " + pass;
            emailmessage += "\n\n Email = " + emailid;
            emailmessage += "\n\n As System Administrator you have access to all relevant modules of Nexus including rights to create users and assign roles and functionality access to the users. You will be the contact point for notices sent by Nexus including invoices, planned maintenance outages etc. sent to the email address above.";
            emailmessage += "\n\n Please retain this email in order to remember your Nexus ID number, which is a unique identifier for your company used in several ways such as identifying messages between trading partners, tracing messages, logging support requests, problem resolution, etc.";
            emailmessage += "\n\n As a Nexus member organisation you will now be able to establish secure, reliable and integrated electronic B2B trading with other Nexus members, or with trading partners who already have B2B capability.";
            emailmessage += "\n\n Please visit the Frequently Asked Questions (FAQ) section of the Nexus Network after logging in to obtain information on how to set up Nexus users, connect with trading partners, map data for integration, and other important information about the Nexus B2B Network operations in order to get the most benefit from the system.";
            emailmessage += "\n\n For further information or assistance contact us by any of the following:";
            emailmessage += "\n\n Email: xmlyessales@xmlyes.com";
            emailmessage += "\n\n Phone (Australia): 1300 808 824		8am to 6pm AEST";
            emailmessage += "\n\n Phone (International): +61 2 9420 5678         8am to 6pm AEST";
            emailmessage += "\n\n We look forward to assisting your company to maximise your trading relationships, efficiency and your profitability while minimising errors, costs and frustrations.";
            emailmessage += "\n\n Again, welcome to the Nexus B2B Network, where companies connect to do more business in a better way!";
            emailmessage += "\n\n Best regards,";
            emailmessage += "\n\n Nexus Support Team";
            String from = com.nexus.web.common.ProjectConstants.FROM_MAIL;
            mailBean.sendMail(reciepent, emailsubject, emailmessage, from);
            log.info("registration mail sent ");
        } catch (MessagingException ex) {
            log.error("Exception Message:", ex);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String beforeCreatePartner() throws Exception {
        HttpSession session = request.getSession();
        Integer supplierId=Integer.parseInt((String)session.getAttribute("supplierid").toString());
        log.info("supplierid val="+supplierId);
        com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        intranstype = partnerDao.getTxnTypes(session.getAttribute("ID").toString(), "1");
        outtranstype = partnerDao.getTxnTypes(session.getAttribute("ID").toString(), "2");
        countries = partnerDao.getCountryList();
        pricetypes= partnerDao.getPriceList(supplierId);
        transplans = partnerDao.getTransaction_plan_list();
        industryTypeList = (ArrayList) partnerDao.getIndustryGroupList();
        for (int i = 0; i < industryTypeList.size(); i++) {
            IndustryType list = new IndustryType();
            list = (IndustryType) industryTypeList.get(i);
            String groupid = list.getKey().toString();
            industryNameList = (ArrayList) partnerDao.getIndustryNameList(groupid);
            industryNameMap.put(list, industryNameList);
        }
        return SUCCESS;
    }

    public String create() throws Exception {
        try {
            HttpSession session = request.getSession();
            com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            //validate user existing or not
            boolean ValidUsernameStatus = partnerDao.checkValidUserName(getUserid());
            log.info("ValidUsernameStatus = " + ValidUsernameStatus);
            log.info("getUserid() = " + getUserid());
            if ((ValidUsernameStatus == false) && (!getUserid().equalsIgnoreCase(""))) {
                Registration registration = new Registration();
                registration.setId(id);
                registration.setLoginId(userid);
                registration.setPassword(password);
                registration.setEmail(email);
                registration.setPhno(phno1 + phno2 + phno3);
                registration.setFxno(fxno1 + fxno2 + fxno3);
                registration.setAddress2(address2);
                registration.setCompany(company);
                registration.setContact(contact);
                registration.setCompanyUrl(company_url);
                registration.setAddress(address);
                registration.setCity(city);
                registration.setState(state);
                registration.setCountry(country);
                registration.setPostcode(postcode);
                registration.setIndustryCode(industryName);
                registration.setMemberType(new Integer(priceType).toString());
                registration.setStatus(new Integer(1));
                //register user
                partnerDao.addUser(registration);
//                partnerDao.getUserId(userid);
                newMemberId = (partnerDao.getUserId(userid, password));
                String id1 = String.valueOf(newMemberId);
                String sup_id = session.getAttribute("ID").toString();
                //getting the private member type
                String ruleid=partnerDao.findRuleValueMapping("Private Member Type", Integer.parseInt(sup_id));
                log.info("id1.."+id1+"private member type for.."+ruleid+"..sup_id.."+sup_id+"..userid.."+userid+"..contact.."+contact+"...email."+email);

                partnerDao.addUser(id1, userid, password, contact, Integer.parseInt(ruleid), email);
                //create member transaction plan
                //partnerDao.addTransactionPlan(id1, trplan);
                //create partner folder
                createPartnerFolders(id1);
                //send registeration email
                sendRegisterationMail(email, contact, userid, password, id1);
                
                /*intranstype = partnerDao.getTxnTypes(sup_id, "1");
                this.setFormat(intranstype, id1, sup_id);
                setMemeberTransTypes(intranstype, id1, "2");
                outtranstype = partnerDao.getTxnTypes(sup_id, "2");
                this.setFormat(outtranstype, sup_id, id1);
                setMemeberTransTypes(outtranstype, id1, "1");*/
                //partnerDao.insertMicrocatLogin(new Integer(id1).intValue(),microcatAccountNumber,microcatUsername,microcatPassword);
                log.info("getBuyNo..."+getBuyNo()+"..getBuyName.."+getBuyName());
                partnerDao.enablePartner(Integer.parseInt(id1), Integer.parseInt(sup_id), getBuyNo(), getBuyName());
                amountList = new ArrayList();
                for (int i = 1; i <= microcatAccountAmount; i++) {
                    amountList.add(new Integer(i));
                }
            } else {
                intranstype = partnerDao.getTxnTypes(session.getAttribute("ID").toString(), "1");
                outtranstype = partnerDao.getTxnTypes(session.getAttribute("ID").toString(), "2");
                log.info("intranstype..."+intranstype+"..outtranstype.."+outtranstype);
                countries = partnerDao.getCountryList();
                transplans = partnerDao.getTransaction_plan_list();
                industryTypeList = (ArrayList) partnerDao.getIndustryGroupList();
                for (int i = 0; i < industryTypeList.size(); i++) {
                    IndustryType list = new IndustryType();
                    list = (IndustryType) industryTypeList.get(i);
                    String groupid = list.getKey().toString();
                    industryNameList = (ArrayList) partnerDao.getIndustryNameList(groupid);
                    industryNameMap.put(list, industryNameList);
                }
                addActionMessage("This username is not available");
                return INPUT;
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error("Exception Messsge", e);
            return INPUT;
        }
        return SUCCESS;
    }

    public String edit() throws Exception {
        com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        Registration registration = partnerDao.loadUser(nexusId);
        userid = registration.getLoginId();
        password = registration.getPassword();
        company = registration.getCompany();
        contact = registration.getContact();
        company_url = registration.getCompanyUrl();
        industryName = registration.getIndustryCode();
        email = registration.getEmail();
        phno1 = registration.getPhno();
        fxno1 = registration.getFxno();
        address = registration.getAddress();
        address2 = registration.getAddress2();
        city = registration.getCity();
        state = registration.getState();
        postcode = registration.getPostcode();
        country = registration.getCountry();
        priceType = new Integer(registration.getMemberType()).intValue();
        countries = partnerDao.getCountryList();
        return SUCCESS;
    }

    public String update() throws Exception {
        com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        Registration registration = new Registration();
        registration.setId(nexusId);
        registration.setLoginId(userid);
        registration.setPassword(password);
        registration.setCompany(company);
        registration.setContact(contact);
        registration.setCompanyUrl(company_url);
        registration.setIndustryCode(industryName);
        registration.setEmail(email);
        registration.setPhno(phno1);
        registration.setFxno(fxno1);
        registration.setAddress(address);
        registration.setAddress2(address2);
        registration.setCity(city);
        registration.setState(state);
        registration.setCountry(country);
        registration.setPostcode(postcode);
        registration.setMemberType(new Integer(priceType).toString());
        registration.setStatus(new Integer(1));
        partnerDao.updateUser(registration);
        partnerDao.updateUserLogin(nexusId, userid, password, contact, email);
        return SUCCESS;
    }

    public String viewMyAccount() throws Exception {
        HttpSession session = request.getSession();
        nexusId = new Integer((String) session.getAttribute("ID")).intValue();
        com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        Registration registration = partnerDao.loadUser(nexusId);
        userid = registration.getLoginId();
        password = registration.getPassword();
        company = registration.getCompany();
        contact = registration.getContact();
        company_url = registration.getCompanyUrl();
        industryName = registration.getIndustryCode();
        email = registration.getEmail();
        phno1 = registration.getPhno();
        fxno1 = registration.getFxno();
        address = registration.getAddress();
        address2 = registration.getAddress2();
        city = registration.getCity();
        state = registration.getState();
        postcode = registration.getPostcode();
        country = registration.getCountry();
        priceType = new Integer(registration.getMemberType()).intValue();
        countries = partnerDao.getCountryList();
        return SUCCESS;
    }
    /*    public String list() throws Exception {
    try {
    com.nexus.dao.SpringHibernateDAO user = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
    col = null;
    col = user.getUsersList();
    log.info("col size:" + col.size());
    } catch (Exception e) {
    addActionError("Invalid user name or password! Please try again!");
    return ERROR;
    }
    return SUCCESS;
    }

    public String mylist() throws Exception {
    try {
    HttpSession session = request.getSession();
    String uid = session.getAttribute("ID").toString();
    com.nexus.dao.SpringHibernateDAO user = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
    col = null;
    col = user.getMyPartnersList(uid);
    log.info("col size:" + col.size());
    } catch (Exception e) {
    addActionError("Invalid user name or password! Please try again!");
    return ERROR;
    }
    return SUCCESS;
    }
     */

    public String sendrequest() throws Exception {
        HttpSession session = request.getSession();
        String id = "";
        try {
            id = session.getAttribute("ID").toString();
            com.nexus.dao.SpringHibernateDAO partnerDAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            partners = partnerDAO.getMembers();
            mycompany = session.getAttribute("mycompany").toString();
            intranstype = partnerDAO.getTxnTypes(id, "1");
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String sendMessage() throws Exception {
        HttpSession session = request.getSession();
        try {
            String uid = session.getAttribute("ID").toString();
            com.nexus.dao.SpringHibernateDAO partnerDAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            partners = partnerDAO.getMyPartners(uid);
            mycompany = session.getAttribute("mycompany").toString();
            log.info("uid.."+uid+"..mycompany..."+mycompany);
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String doSendRequest() throws Exception {
        HttpSession session = request.getSession();
        String id = "";
        try {
            id = session.getAttribute("ID").toString();
            log.info("id.."+id);
            com.nexus.dao.SpringHibernateDAO springHibernateDAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            springHibernateDAO.addPartnerMessage(Integer.parseInt(session.getAttribute("ID").toString()), Integer.parseInt(getPartner()), getPartnertype(), getSubject(), getMessage());
            // adding outbound txn format
            intranstype = springHibernateDAO.getTxnTypes(id, "1");
            setFormat(intranstype, getPartner(), id);
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
           log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    /*    public String messagelist() throws Exception {
    HttpSession session = request.getSession();
    try {
    String uid = session.getAttribute("ID").toString();
    com.nexus.dao.SpringHibernateDAO partnerDAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
    messagecol = partnerDAO.getPartnerMessageList(uid);
    } catch (Exception e) {
    addActionError("Invalid user name or password! Please try again!");
    e.printStackTrace();
    return ERROR;
    }
    return SUCCESS;
    }*/
    public String showmessage() throws Exception {
        HttpSession session = request.getSession();
        String messageid = request.getParameter("messageid");
        session.setAttribute("messageid", messageid);
        try {
            String uid = session.getAttribute("ID").toString();
             log.info("uid.."+uid+"..messageid.."+messageid);
            com.nexus.dao.SpringHibernateDAO partnerDAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            showmessagecol = partnerDAO.getPartnerMessage(messageid);
            ArrayList list = new ArrayList();
            list = (ArrayList) showmessagecol;
            PartnerMessagesList pmlist = new PartnerMessagesList();
            pmlist = (PartnerMessagesList) list.get(0);
            this.setMessageid("" + pmlist.getId());
            this.setPartnertype(pmlist.getType());
            this.setPartner(pmlist.getFrom());
            this.setPartnerid(pmlist.getFromid());
            if (getPartnertype().equalsIgnoreCase("Buyer")) {
                isEnabled = partnerDAO.checkPMessageStatus(getPartnerid(), Integer.parseInt(uid.toString()), partnertype);
            } else if (getPartnertype().equalsIgnoreCase("Supplier")) {
                isEnabled = partnerDAO.checkPMessageStatus(Integer.parseInt(uid.toString()), getPartnerid(), partnertype);
            } else {
                isEnabled = partnerDAO.checkPMessageStatus(getPartnerid(), Integer.parseInt(uid.toString()), partnertype);
            }
            messagecol = partnerDAO.getPartnerMessageList(uid);
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String enablePartner() throws Exception {
        HttpSession session = request.getSession();
        String messageid = (String) session.getAttribute("messageid");

        try {
            String id = session.getAttribute("ID").toString();
            log.info("messageid:" + messageid+"..id.."+id);
            com.nexus.dao.SpringHibernateDAO partnerDAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            messagecol = partnerDAO.getPartnerMessageList(id);
            showmessagecol = partnerDAO.getPartnerMessage(messageid);
            ArrayList list = new ArrayList();
            list = (ArrayList) showmessagecol;
            PartnerMessagesList pmlist = new PartnerMessagesList();
            pmlist = (PartnerMessagesList) list.get(0);
            this.setMessageid("" + pmlist.getId());
            this.setPartnertype(pmlist.getType());
            this.setPartner(pmlist.getFrom());
            intranstype = partnerDAO.getTxnTypes(id, "1");
//            outtranstype = partnerDAO.getTxnTypes(id, "2");
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String doenablePartner() throws Exception {
        HttpSession session = request.getSession();
        String id = "";
        String buyerno = getBuyNo();
        String suplierno = getSupNo();
        String messageid = session.getAttribute("messageid").toString();
        log.info("buyerno:" + buyerno+"...supplierno:" + suplierno+"...messageid:" + messageid);
        try {
            id = session.getAttribute("ID").toString();
            String uid = session.getAttribute("ID").toString();
            com.nexus.dao.SpringHibernateDAO partnerDAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            showmessagecol = partnerDAO.getPartnerMessage(messageid);
            ArrayList list = new ArrayList();
            list = (ArrayList) showmessagecol;
            PartnerMessagesList pmlist = new PartnerMessagesList();
            pmlist = (PartnerMessagesList) list.get(0);
            this.setMessageid("" + pmlist.getId());
            this.setPartnertype(pmlist.getType());
            this.setPartner(pmlist.getFrom());
            messagecol = partnerDAO.getPartnerMessageList(uid);
            log.info("messagecolcol size:" + messagecol.size());
            if (getPartnertype().equalsIgnoreCase("Buyer")) {
                partnerDAO.enablePartner(pmlist.getFromid(), Integer.parseInt(uid), getBuyNo(), getBuyName());
            } else if (getPartnertype().equalsIgnoreCase("Supplier")) {
                partnerDAO.enablePartner(Integer.parseInt(uid), pmlist.getFromid(), getSupNo(), getSupName());
            } else {
                partnerDAO.enablePartner(pmlist.getFromid(), Integer.parseInt(uid), getBuyNo(), getBuyName());
                partnerDAO.enablePartner(Integer.parseInt(uid), pmlist.getFromid(), getSupNo(), getSupName());
            }
            // adding inbound txn format
            intranstype = partnerDAO.getTxnTypes(id, "1");
            setFormat(intranstype, "" + pmlist.getFromid(), uid);
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }
    // get the transType from xy_txn_type
    HashMap transtype;
    Collection intranstype;
    Collection outtranstype;

    public HashMap getTranstype() {
        return transtype;
    }

    public void setTranstype(HashMap transtype) {
        this.transtype = transtype;
    }

    public Collection getIntranstype() {
        return intranstype;
    }

    public void setIntranstype(Collection intranstype) {
        this.intranstype = intranstype;
    }

    public Collection getOuttranstype() {
        return outtranstype;
    }

    public void setOuttranstype(Collection outtranstype) {
        this.outtranstype = outtranstype;
    }

    public String setTransTypes() throws Exception {
        HttpSession session = request.getSession();
        com.nexus.dao.SpringHibernateDAO dao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        try {
            String inbound = getInboundTrans();
            if (!(inbound.isEmpty())) {
                StringTokenizer st = new StringTokenizer(inbound, ",");
                while (st.hasMoreTokens()) {
                    String key = st.nextToken();
                    log.info(key.trim());
                    dao.addMapping(session.getAttribute("memberID").toString(), null, key.trim(), "1", "", "");
                }
            }
            String outbound = getOutboundTrans();
            if (!(outbound.isEmpty())) {
                StringTokenizer st1 = new StringTokenizer(outbound, ",");
                while (st1.hasMoreTokens()) {
                    String key = st1.nextToken();
                    log.info(key.trim());
                    dao.addMapping(session.getAttribute("memberID").toString(), null, key.trim(), "2", "", "");
                }
            }
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public Collection getShowmessagecol() {
        return showmessagecol;
    }

    /**
     * Desc: Create the partner folders on the client for a particular partner id
     *
     */
    public void createPartnerFolders(String tfPartnerID) {
        try {
            com.nexus.dao.SpringHibernateDAO dao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            String PARTNER_DIR = "";
            String nexus_DIR = "";
            String arch_DIR = "";
            nexus_DIR = dao.getUploadFolder();
            log.info("nexus_DIR..."+nexus_DIR);
            PARTNER_DIR = nexus_DIR + "partners/" + tfPartnerID;
            arch_DIR = nexus_DIR + "archive\\" + tfPartnerID;
            File tmp_File = null;

            String IN = PARTNER_DIR + "\\IN";
            tmp_File = new File(IN);
            tmp_File.mkdirs();

            String OUT = PARTNER_DIR + "\\OUT";
            tmp_File = new File(OUT);
            tmp_File.mkdirs();

            String interim = PARTNER_DIR + "\\OUT" + "\\interim";
            tmp_File = new File(interim);
            tmp_File.mkdirs();

            String nexus = PARTNER_DIR + "\\OUT" + "\\nexus";
            tmp_File = new File(nexus);
            tmp_File.mkdirs();

            String received = PARTNER_DIR + "\\others";
            tmp_File = new File(received);
            tmp_File.mkdirs();

            String sent = PARTNER_DIR + "\\temp";
            tmp_File = new File(sent);
            tmp_File.mkdirs();

            log.info("Folders have been created");
        } catch (DataAccessException dae) {
           log.error("Exception Message:", dae);
        } catch (SQLException sql) {
            log.error("Exception Message:", sql);
        }
    }

    public void setFormat(Collection collect, String str1, String str2) {
        com.nexus.dao.SpringHibernateDAO dao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        //intranstype = dao.getTxnTypes(session.getAttribute("ID").toString(), "1");
        ArrayList list1 = new ArrayList();
        try {
            list1 = (ArrayList) collect;
            for (int i = 0; i < list1.size(); i++) {
                MapDocumet txnmap = new MapDocumet();
                txnmap = (MapDocumet) list1.get(i);
                String name = txnmap.getName();
                String value = request.getParameter(name + "1");
                log.info(name + " = " + value);
                if ((value == null) || (value.equalsIgnoreCase(""))) {
                } else {
                    dao.addPartnerTxnFormat(Integer.parseInt(str1), Integer.parseInt(str2), name, value);
                }
            }
        } catch (Exception e) {
            addActionError("Invalid user name or password! Please try again!");
            log.error("Exception Message:", e);
        //return ERROR;
        }
    }

    public String setMemeberTransTypes(Collection collect, String memeberid, String dir) throws Exception {
        ArrayList list1 = new ArrayList();
        com.nexus.dao.SpringHibernateDAO dao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        try {
            list1 = (ArrayList) collect;
            for (int i = 0; i < list1.size(); i++) {
                MapDocumet txnmap = new MapDocumet();
                txnmap = (MapDocumet) list1.get(i);
                String name = txnmap.getName();
                String txntypeid = txnmap.getKey();
                dao.addMapping(memeberid, null, txntypeid, dir, "", "");
            }
        } catch (Exception e) {
            addActionError("try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * @return the priceType
     */
    public int getPriceType() {
        return priceType;
    }

    /**
     * @param priceType the priceType to set
     */
    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    /**
     * @return the microcatAccountAmount
     */
    public int getMicrocatAccountAmount() {
        return microcatAccountAmount;
    }

    /**
     * @param microcatAccountAmount the microcatAccountAmount to set
     */
    public void setMicrocatAccountAmount(int microcatAccountAmount) {
        this.microcatAccountAmount = microcatAccountAmount;
    }

    /**
     * @return the newMemberId
     */
    public int getNewMemberId() {
        return newMemberId;
    }

    /**
     * @param newMemberId the newMemberId to set
     */
    public void setNewMemberId(int newMemberId) {
        this.newMemberId = newMemberId;
    }

    /**
     * @return the amountList
     */
    public List getAmountList() {
        return amountList;
    }

    /**
     * @param amountList the amountList to set
     */
    public void setAmountList(List amountList) {
        this.amountList = amountList;
    }

    /**
     * @return the nexusId
     */
    public int getNexusId() {
        return nexusId;
    }

    /**
     * @param nexusId the nexusId to set
     */
    public void setNexusId(int nexusId) {
        this.nexusId = nexusId;
    }

    /**
     * @return the catalogueService
     */
    public CatalogueService getCatalogueService() {
        return catalogueService;
    }

    /**
     * @param catalogueService the catalogueService to set
     */
    public void setCatalogueService(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    /**
     * @return the pricetypes
     */
    public Collection getPricetypes() {
        return pricetypes;
    }

    /**
     * @param pricetypes the pricetypes to set
     */
    public void setPricetypes(Collection pricetypes) {
        this.pricetypes = pricetypes;
    }
}
