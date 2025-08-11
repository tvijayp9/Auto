/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.DR;
import com.nexus.domain.User;
import com.nexus.web.Constant;
import com.nexus.services.DataResolutionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import java.io.File;
import org.jdom.xpath.XPath;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
/**
 *
 * @author Administrator
 */
public class UserManagementAction extends ActionSupport{ //implements ServletRequestAware{
    Logger log=Logger.getLogger(UserManagementAction.class);
//    HttpServletRequest request;
//    HttpSession session;
//    private List<Role> userList=new LinkedList<Role>();
    private DataResolutionService dataResolutionService;
    Element transInfoNode = null;
    private Document transResDom;
    protected SAXBuilder xmlBuilder;
    private List partnerList=new ArrayList();
//    private String partner;
    //private Integer user;
    private Integer userId;
    private String loginId;
    private User user;
    private int existingCount;
    ActionContext ac;
//    public void setServletRequest(HttpServletRequest httpServletRequest) {
//        this.request = httpServletRequest;
//    }

    public String execute() throws IOException,JDOMException,SQLException {
         ac = ActionContext.getContext();
//         session = request.getSession();
        String id = (String) ac.getSession().get(Constant.ID);
//         String id = (String) session.getAttribute(Constant.ID);
        log.info("Id in UserManagementAction.."+id+"...userId="+userId);
        user=dataResolutionService.getUserById(userId);
        loginId=user.getUserId();
        existingCount=getPartners(id);
        log.info("selected user.."+loginId+"..existingCount.."+existingCount);
        //userList=dataResolutionService.getUsers(new Integer(id).intValue());
        //session.setAttribute("userList", userList);
        //log.info("size.."+userList.size());
        return SUCCESS;
    }

    public int getPartners(String id) throws IOException,JDOMException,SQLException {
        Element partnerspecificMapping = null;
        xmlBuilder = new SAXBuilder();
        xmlBuilder.setIgnoringElementContentWhitespace(true);
        String folder=dataResolutionService.getDRStoreFolder();
        log.info("folder in getPartners().."+folder);
        transResDom = xmlBuilder.build(new File(folder));
        transInfoNode = (Element) XPath.newInstance("TRANSACTION[ROOT_NAME = 'Order']").selectSingleNode(transResDom.getRootElement());
        Element partnerspecificMappings = transInfoNode.getChild("SUPPLIER_BUYER_SPECIFIC_MAPPINGS");
        //partnerspecificMapping = (Element) XPath.newInstance("PARTNER_SPECIFIC_MAPPINGS[contains(SUPPLIER_NEXUS_ID, '500000')]").selectSingleNode(partnerspecificMappings);
          partnerspecificMapping = (Element) XPath.newInstance("PARTNER_SPECIFIC_MAPPINGS[contains(SUPPLIER_NEXUS_ID, '"+id+"')]").selectSingleNode(partnerspecificMappings);
        List partnerMapping = (List) partnerspecificMapping.getChildren("PARTNER_MAPPING");
        Iterator i = partnerMapping.iterator();
        while (i.hasNext()) {
            Element element = (Element) i.next();
            String name = element.getChild("PARTNER_INFO").getChild("NAME").getTextTrim();
            partnerList.add(name);
        }
       return partnerList.size();
    }

//    public String assignPartner() {
//        session = request.getSession();
//        log.info("you have selected.partner."+getPartner()+"..and user.."+getUser());
//        dataResolutionService.assignPartner(getUser(), partner);
//        //log.info("before partnerrList id value.."+session.getAttribute("id"));
//        partnerList=(ArrayList)session.getAttribute("partnerList");
//        userList=(ArrayList)session.getAttribute("userList");
//        return SUCCESS;
//    }

     /**
     * @return the tradingPartnerService
     */
    public DataResolutionService getDataResolutionService() {
        return dataResolutionService;
    }

    /**
     * @param tradingPartnerService the tradingPartnerService to set
     */
    public void setDataResolutionService(DataResolutionService dataResolutionService) {
        this.dataResolutionService = dataResolutionService;
    }

//    /**
//     * @return the userList
//     */
//    public List<Role> getUserList() {
//        return userList;
//    }
//
//    /**
//     * @param userList the userList to set
//     */
//    public void setUserList(List<Role> userList) {
//        this.userList = userList;
//    }
//
//    /**
//     * @return the partnerList
//     */
//    public List getPartnerList() {
//        return partnerList;
//    }
//
//    /**
//     * @param partnerList the partnerList to set
//     */
//    public void setPartnerList(List partnerList) {
//        this.partnerList = partnerList;
//    }
//
//    /**
//     * @return the partner
//     */
//    public String getPartner() {
//        return partner;
//    }
//
//    /**
//     * @param partner the partner to set
//     */
//    public void setPartner(String partner) {
//        this.partner = partner;
//    }

//    /**
//     * @return the user
//     */
//    public Integer getUser() {
//        return user;
//    }
//
//    /**
//     * @param user the user to set
//     */
//    public void setUser(Integer user) {
//        this.user = user;
//    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the existingCount
     */
    public int getExistingCount() {
        return existingCount;
    }

    /**
     * @param existingCount the existingCount to set
     */
    public void setExistingCount(int existingCount) {
        this.existingCount = existingCount;
    }

}
