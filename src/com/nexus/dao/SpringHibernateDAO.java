/*
 * Created on 07-Oct-2006
 *
 */
package com.nexus.dao;

import com.nexus.domain.Order;
import com.nexus.domain.Registration;
import com.nexus.domain.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * @author Sunil Gupta
 *
 */
public interface SpringHibernateDAO {

    /**
     * Retrieve all <code>true</code>/<code>false</code> from the datastore.
     * @return a <code>true</code> or  <code>fasel</code>.
     */
   public User checkUserLogin(String strUserName, String strPassword) throws DataAccessException, java.sql.SQLException;

    public boolean checkPMessageStatus(int buyerid, int supplierid, String partnertype) throws DataAccessException, java.sql.SQLException;

    /**
     * Saves Article object to the datastore.
     *
     */
    public void addUser(Registration registration) throws DataAccessException;

    /**
     * Update Article object ot the datastore.
     *
     */
    public void updateUser(Registration registration) throws DataAccessException;

    /**
     * Retrieve <code>Article</code> from the datastore.
     * @return Article.
     */
    public Registration loadUser(Integer id) throws DataAccessException;

    /**
     * Retrieve all <code>true</code>/<code>false</code> from the datastore.
     * @return a <code>true</code> or  <code>fasel</code>.
     */
    public boolean checkValidUserName(String strUserid) throws DataAccessException, java.sql.SQLException;

    /**
     * Retrieve <code>Country Name</code>s from the datastore.
     * @return a <code>Collection</code> of Country.
     */
    public Collection getUsersList() throws DataAccessException, java.sql.SQLException;

    /**
     * Retrieve all <code>true</code>/<code>false</code> from the datastore.
     * @return a <code>true</code> or  <code>fasel</code>.
     */
    public Collection getMessageList(String id) throws DataAccessException, java.sql.SQLException;

    public Collection getInBoundMessageList(String id) throws DataAccessException, java.sql.SQLException;

    public Collection getOutBoundMessageList(String id) throws DataAccessException, java.sql.SQLException;

    public HashMap getMembers() throws DataAccessException, java.sql.SQLException;

    public Collection getTxnTypes(String str1, String str2) throws DataAccessException, java.sql.SQLException;

    public HashMap getMyPartners(String str) throws DataAccessException, java.sql.SQLException;

    public Collection getCountryList() throws DataAccessException, java.sql.SQLException;

    public Collection getTransaction_plan_list() throws DataAccessException, java.sql.SQLException;

    public int getUserId(String strUserid, String pass) throws DataAccessException, java.sql.SQLException;

    public String getUploadFolder() throws DataAccessException, java.sql.SQLException;

    public Collection getPartnerMessage(String str1) throws DataAccessException, java.sql.SQLException;

    public Collection getMessage(String str1) throws DataAccessException, java.sql.SQLException;

    public Collection getPartnerMessageList(String id) throws DataAccessException, java.sql.SQLException;

    public List getIndustryGroupList() throws DataAccessException, java.sql.SQLException;

    public List getIndustryNameList(String str) throws DataAccessException, java.sql.SQLException;

    public Collection getMemberDetails(String strUserid) throws DataAccessException, java.sql.SQLException;

    public void addMapping(String str1, String str2, String str3, String str4, String str5, String str6) throws DataAccessException, java.sql.SQLException;

    public void addPartnerMessage(int str1, int str2, String str3, String str4, String str5) throws DataAccessException, java.sql.SQLException;

    public void addPartnerTxnFormat(int str1, int str2, String str3, String str4) throws DataAccessException, java.sql.SQLException;

    public void enablePartner(int str1, int str2, String str3, String str4) throws DataAccessException, java.sql.SQLException;

    /**
     * Retrieve all <code>true</code>/<code>false</code> from the datastore.
     * @return a <code>true</code> or  <code>fasel</code>.
     */
    public String[] retriveUserForgetPassword(String strUserName, String strEmail) throws DataAccessException, java.sql.SQLException;

    public int resetUserForgetPassword(String strUserName, String password, String oldpassword) throws DataAccessException, java.sql.SQLException;

    //CAtelouge    Menu
    //public Collection getMyCatelogueList(String str) throws DataAccessException, java.sql.SQLException;
    public Collection getCategoryList(String buyid, String supid) throws DataAccessException, java.sql.SQLException;

    public Collection getItemsByCategory(String cat) throws DataAccessException, java.sql.SQLException;

    public Collection getMyItemlist(String buyid, String supid) throws DataAccessException, java.sql.SQLException;
   
    public void updateOrderStatus(String orderid, String status) throws DataAccessException, java.sql.SQLException;
   
    Collection getOrderList(String buyid, String supid) throws DataAccessException, java.sql.SQLException;
    
    public Collection getArchivedOrderitemlinesdetails(String orderid) throws DataAccessException, java.sql.SQLException;
    
    public void cancelOrder(String str1, String str2) throws DataAccessException, java.sql.SQLException;
    
    // Admin Menu
    public Collection getMyUsersList(String str) throws DataAccessException, java.sql.SQLException;

    public Collection getDownloadList() throws DataAccessException, java.sql.SQLException;

    public void addUser(String nexus_id, String userid, String password, String name, int type, String email) throws DataAccessException;

    public void updateUserDetail(String userid, String name, String username, String password, String email) throws DataAccessException, java.sql.SQLException;

    public void updateUserLogin(int nexusId, String loginId, String password, String name,String email) throws DataAccessException, java.sql.SQLException;

    public void deleteUserDetail(String userid) throws DataAccessException, java.sql.SQLException;

    public String getSupplierName(String supid) throws DataAccessException, java.sql.SQLException;

    String getBannerNameByNexusId(String nexusId) throws DataAccessException, java.sql.SQLException;

    Order getOrderByOrderId(String orderid) throws DataAccessException, java.sql.SQLException;

    Order getArchivedOrderByOrderId(String orderid) throws DataAccessException, java.sql.SQLException;

    String findRuleValueMapping(String rule,int nexusId) throws SQLException;

    public Collection getUserDetail(String userid) throws DataAccessException, java.sql.SQLException;

    public ArrayList getPriceList(int supplierId) throws DataAccessException, java.sql.SQLException;

    public void insertLoginAttempts(int buyId,int supId,String username)throws DataAccessException, java.sql.SQLException;

    public String findProducttable(String rule, int supId,int buyId) throws SQLException;

    public void insertPunchoutSetup(String buyerCoockie,String fromURL) throws SQLException;

    public HashMap getPunchoutCoockie() throws SQLException;

    public String getFolderPath(String name) throws SQLException;
}

