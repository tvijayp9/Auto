package com.nexus.struts.action;

import com.nexus.domain.User;
import com.nexus.services.ServiceFinder;
import com.nexus.services.AccountService;
import com.nexus.services.AdministrationService;
import com.nexus.services.CatalogueService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import org.apache.struts2.interceptor.ServletRequestAware;

import java.util.*;
import org.apache.log4j.Logger;


public class LoginAction extends ActionSupport implements ServletRequestAware {

    Logger log=Logger.getLogger(LoginAction.class);
    private AccountService accountService;
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
    private String password = "";
    private String email = "";
    private String address = "";
    private Collection col;
    private Collection col2;
    private Collection col1;
    private Collection messagecol;
    private String username = "";
    private String pass = "";
    int partnerid = 0;
    private HashMap partners;
    private Collection countries;
    Map industryNameMap = new HashMap();
    Boolean isEnabled = false;
    String delform = "";
    String recform = "";
    String inboundTrans;
    String outboundTrans;
    String loginpage;
    int customer_admin_user_type;
    private String redirectURL;
    private String errorLoginURL;
    private CatalogueService catalogueService;
    private AdministrationService administrationService;
    private String errorlogin;
    private String buyerCoockie;

    private String HOOK_URL;
    private String target;
    /**
     * @return the sectionService
     */
    public AccountService getAccountService() {
        return accountService;
    }

    /**
     * @param sectionService the sectionService to set
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

     /**
     * @return the sectionService
     */
    public AdministrationService getAdministrationService() {
        return administrationService;
    }

    /**
     * @param sectionService the sectionService to set
     */
    public void setAdministrationService(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }
    public String getRedirectURL() {
        return this.redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setPartnertype(String partnertype) {
        this.partnertype = partnertype;
    }

    public HashMap getPartners() {
        return partners;
    }

    public Collection getCountries() {
        return countries;
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

       String result = null;
       String logoName=null;
       String product_table_name=null;
       String strUserid =null;
       String strPassword =null;
       HashMap hm=null;
       HttpSession session;
        try {
             com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            strUserid = getUsername();
                strPassword = getPass();
                session=request.getSession(false);
                if (session != null) {
                session.invalidate();
            }
            User user = partnerDao.checkUserLogin(strUserid, strPassword);
            if (user != null) {
                session = request.getSession();
                log.info("buyerCookie="+session.getAttribute("buyercookie"));
                log.info("HOOK_URL="+getHOOK_URL()+"..getQueryString.."+request.getQueryString());
                 session.setAttribute("userLoginId",Integer.toString(user.getId()));
                 int supplierId=catalogueService.getSupplierIdById(user.getNexusId());
                 if(user.getType()<0){
                     log.info("you are a supplier");
                     session.setAttribute("isSupplier", "true");
                 }else{
                    log.info("you are not a supplier");
                    session.setAttribute("isSupplier", "false");
                 }
                if(supplierId!=0)
                    session.setAttribute("supplierid", new Integer(supplierId).toString());
                else
                    session.setAttribute("supplierid", new Integer(user.getNexusId()).toString());

                 supplierId=Integer.parseInt(session.getAttribute("supplierid").toString());
                 //fetching supplier login page
                 log.info("supplierId.before getting login page..."+supplierId);
                 loginpage=partnerDao.findRuleValueMapping("Supplier Login Page", supplierId);
                 session.setAttribute("loginpage", loginpage);
                 log.info("loginpage.."+loginpage);
                 String punchout=catalogueService.findRuleValueMapping("PUNCHOUT",user.getNexusId(),supplierId);
                 if(punchout.equalsIgnoreCase("punchout")){
                     log.info("login as punchout customer..");
                     session.setAttribute("hookUrl", getHOOK_URL());
                    session.setAttribute("punchout", punchout);
                 }else if(punchout.equalsIgnoreCase("cxmlpunchout")){
                     log.info("login as CXML punchout customer..");
                     hm=new HashMap();
                     hm=partnerDao.getPunchoutCoockie();
                     session.setAttribute("buyerCoockie", hm.get("buyerCoockie"));
                     session.setAttribute("fromURL",hm.get("fromURL") );
                    session.setAttribute("punchout", punchout);
                 }else{
                      session.setAttribute("punchout", "checkout");
                 }
                 //findout whether supplier needs microcat button.
                 String isMicrocat=catalogueService.findRuleValueMappingbySupId("ISMICROCAT",user.getNexusId());
                    session.setAttribute("isMicrocat", isMicrocat);
                //inserting login attempts in database.
                    partnerDao.insertLoginAttempts(user.getNexusId(), supplierId, username);

                 //fetching supplier's logo
                    logoName = accountService.getLogoName(user.getNexusId());

                    session.setAttribute("logoname", logoName);
                    log.info("supplierId.."+supplierId+"..roleType in action.."+user.getType());
//                    String defaultaction = accountService.getDefaultAction(strUserid);
                    String defaultaction = accountService.getDefaultAction(user.getType());
                    this.redirectURL = defaultaction + ".action";
                    Properties sec_list = new Properties();
                    log.info("..defaultaction.."+defaultaction);
                    //sec_list = accountService.getMySections(strUserid);
                    sec_list = accountService.getMySections(user.getType());
                    List tab_list = new ArrayList();
                    //tab_list = accountService.getMyTabs(strUserid);
                    tab_list = accountService.getMyTabs(user.getType());
                    session.setAttribute("mysections", sec_list);
                    session.setAttribute("mytabs", tab_list);
                    result = "userList";

                session.setAttribute("userID", strUserid);
                session.setAttribute("ID", Integer.toString(user.getNexusId()));
                product_table_name=partnerDao.findProducttable("PRODUCT TABLE NAME",supplierId, user.getNexusId());
                
                session.setAttribute("product_table_name", product_table_name);
                customer_admin_user_type = administrationService.getCustomerAdminRoleId(user.getNexusId());
                mycompany = partnerDao.getSupplierName(Integer.toString(user.getNexusId()));
                log.info("..customer_admin_user_type.."+customer_admin_user_type+"..mycompany.."+mycompany);
                session.setAttribute("myname", strUserid);
                session.setAttribute("mycompany", mycompany);
                session.setAttribute("usertype", Integer.toString(customer_admin_user_type));
                
                  String is_scania=catalogueService.findRuleValueMappingbySupId("IS_SCANIA",supplierId);
                  if (is_scania.equalsIgnoreCase("scania")) {
                    session.setAttribute("is_scania", is_scania);
//                    this.redirectURL = "scartlist.action";
//                    result = "scartlist";
                }
            } else {
                addActionError("Invalid username or password! Please try again.");
                this.errorLoginURL=errorlogin+".jsp";
                        result = "error";
            }
        } catch (Exception e) {
           log.error("Exception Message:", e);
        }
        return result;
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
     * @return the errorlogin
     */
    public String getErrorlogin() {
        return errorlogin;
    }

    /**
     * @param errorlogin the errorlogin to set
     */
    public void setErrorlogin(String errorlogin) {
        this.errorlogin = errorlogin;
    }

    /**
     * @return the errorLoginURL
     */
    public String getErrorLoginURL() {
        return errorLoginURL;
    }

    /**
     * @param errorLoginURL the errorLoginURL to set
     */
    public void setErrorLoginURL(String errorLoginURL) {
        this.errorLoginURL = errorLoginURL;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return the HOOK_URL
     */
    public String getHOOK_URL() {
        return HOOK_URL;
    }

    /**
     * @param HOOK_URL the HOOK_URL to set
     */
    public void setHOOK_URL(String HOOK_URL) {
        this.HOOK_URL = HOOK_URL;
    }

    /**
     * @return the buyerCoockie
     */
    public String getBuyerCoockie() {
        return buyerCoockie;
    }

    /**
     * @param buyerCoockie the buyerCoockie to set
     */
    public void setBuyerCoockie(String buyerCoockie) {
        this.buyerCoockie = buyerCoockie;
    }

}
