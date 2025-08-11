/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import com.nexus.domain.Role;
import com.nexus.domain.User;
import com.nexus.util.DR.traderoute.EdxBableBean;
import com.nexus.util.DR.traderoute.TransactionBean;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import org.w3c.dom.Document;

/**
 *
 * @author Terry
 */
public interface DataResolutionDAO {

    List findMyTransactionsList(String id,String userLoginId,String isSupplier, String search, String company, int start, int limit, String sidx, String sord,String type) throws SQLException;

    int findMyTransactionsListCount(String id,String userLoginId,String isSupplier, String search, String company,String type) throws SQLException;

    Integer unlockTransaction(String transId)  throws SQLException;

    //from TradeRoute DBCommandsManager

    public byte[] getTransactionDocument(String transId) throws Exception ;

    public boolean saveTransactionDocument(Document doc,String transId) throws Exception ;

    public EdxBableBean getEdxBable(String xpath,String partnerid) throws Exception ;

	public String checkValuePair(int edxid, String orig, String parent) throws Exception ;

	public void newValuePair(int edxid, String orig, String dest, String parent) throws Exception ;

	public void updateValuePair(int edxid, String orig, String dest, String parent) throws Exception ;

	public void updateAlternateTransactionNumber(String transAckId, String altTransNumber) throws Exception ;

	public Vector getTransactionHeirarchyList(String transactionId) throws Exception ;

	public void setTransactionLockedState(String transactionId, String lockedState) throws Exception ;

	public void updateTransactionState(String transId, String transState,String reason) throws Exception ;

	public void updateResolvedState(String transId, String resolvedState,Integer nexusId) throws Exception ;

    public TransactionBean getFullTransactionInfoId(String transId) throws Exception ;

    public void setTransactionHeirarchyState(String rootParentTransId, String state) throws Exception ;

    public TransactionBean getLastTransactionInHeirarchyList(String transactionId) throws Exception ;

    public void updateUserList(String transactionId, String userName) throws Exception ;

    public Vector getTransactions(String NexusID) ;

	public String getDestValue(int edxid, String orig, String parent) throws Exception ;

    public String getPartnerId(String transId) throws Exception ;

    public String getTransactionId(String transkey) ;

    public String getTransactionType(String transkey);

    public String getDRStoreFolder() throws java.sql.SQLException;

    public List<Role> getUsers(int nexusId);

    public void assignPartner(int userId,String partner_id,String partner_name);

    public List<JQGridRow> getAssignedPartners(Integer userId);

    public int getUsersCountByNexusId(int nexusId) throws SQLException;

    public List<JQGridRow> getUsersByNexusId(int nexusId, int start, int limit, String sidx, String sord) throws SQLException;

    public User getUserById(int id) throws SQLException;

    public void deAssignPartner(int userId,String partnerName);

    public void addInternalComment(String transId, String comment) throws Exception;

    public List findProcessedTransactionsList(String id, String userLoginId, String isSupplier, String search, String transactionNumber, int start, int limit, String sidx, String sord) throws SQLException;

    public HashMap getTransactionId(Integer transactionkey);

    public String getTradeRouteFolderPath(String name);

    public HashMap getNexusConfig(Integer nexusId);
    
    public HashMap getNexusParameters(Integer nexusId);
}
