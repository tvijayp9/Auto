/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.services;

import com.nexus.util.DR.traderoute.*;
import com.nexus.dao.DataResolutionDAO;
import com.nexus.dao.DataResolutionDAOImpl;
import com.nexus.domain.JQGridRow;
import com.nexus.domain.Partner;
import com.nexus.domain.Role;
import com.nexus.domain.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;

/**
 *
 * @author Sunil
 */
@Transactional
public class DataResolutionServiceImpl implements DataResolutionService {

    Logger log=Logger.getLogger(DataResolutionServiceImpl.class);
    private DataResolutionDAO dataResolutionDAO=new DataResolutionDAOImpl();
    Transaction_Manager transaction_Manager;

    public List getMyTransactionsList(String id,String userLoginId,String isSupplier, String search, String company, int start, int limit, String sidx, String sord,String type) throws SQLException {
        return dataResolutionDAO.findMyTransactionsList(id,userLoginId,isSupplier, search, company, start, limit, sidx, sord,type);
    }
    public int getMyTransactionsListCount(String id,String userLoginId,String isSupplier, String search, String company,String type) throws SQLException {
        return dataResolutionDAO.findMyTransactionsListCount(id,userLoginId,isSupplier, search, company,type);
    }
    public Integer unlockTransaction(String transId)  throws SQLException{
        return dataResolutionDAO.unlockTransaction(transId);
    }

    public byte[] getTransactionDocument(String transId) throws Exception {
        return dataResolutionDAO.getTransactionDocument(transId);
    }

    public boolean saveTransactionDocument(Document doc,String transId) throws Exception{
        return dataResolutionDAO.saveTransactionDocument(doc,transId);
    }

    public EdxBableBean getEdxBable(String xpath,String partnerid) throws Exception {
        return dataResolutionDAO.getEdxBable(xpath,partnerid);
    }

	public String checkValuePair(int edxid, String orig, String parent) throws Exception {
        return dataResolutionDAO.checkValuePair(edxid, orig, parent);
    }

	public void newValuePair(int edxid, String orig, String dest, String parent) throws Exception {
         dataResolutionDAO.newValuePair(edxid, orig, dest, parent);
    }

	public void updateValuePair(int edxid, String orig, String dest, String parent) throws Exception {
        dataResolutionDAO.updateValuePair(edxid, orig, dest, parent);
    }

	public void updateAlternateTransactionNumber(String transAckId, String altTransNumber) throws Exception {
        dataResolutionDAO.updateAlternateTransactionNumber(transAckId, altTransNumber);
    }

	public Vector getTransactionHeirarchyList(String transactionId) throws Exception {
        return dataResolutionDAO.getTransactionHeirarchyList(transactionId);
    }

	public void setTransactionLockedState(String transactionId, String lockedState) throws Exception {
        dataResolutionDAO.setTransactionLockedState(transactionId,lockedState);
    }

	public void updateTransactionState(String transId, String transState,String reason) throws Exception {
        dataResolutionDAO.updateTransactionState(transId, transState,reason);
    }

    public void addInternalComment(String transId, String comment) throws Exception{
        dataResolutionDAO.addInternalComment(transId, comment);
    }

	public void updateResolvedState(String transId, String resolvedState,Integer nexusId) throws Exception {
        dataResolutionDAO.updateResolvedState(transId, resolvedState,nexusId);
    }

    public TransactionBean getFullTransactionInfoId(String transId) throws Exception {
        return dataResolutionDAO.getFullTransactionInfoId(transId);
    }

    public void setTransactionHeirarchyState(String rootParentTransId, String state) throws Exception {
        dataResolutionDAO.setTransactionHeirarchyState(rootParentTransId, state);
    }

    public TransactionBean getLastTransactionInHeirarchyList(String transactionId) throws Exception {
        return dataResolutionDAO.getLastTransactionInHeirarchyList(transactionId);
    }

    public void updateUserList(String transactionId, String userName) throws Exception {
        dataResolutionDAO.updateUserList(transactionId, userName);
    }

    public Vector getTransactions(String NexusID) {
        return dataResolutionDAO.getTransactions(NexusID);
    }

	public String getDestValue(int edxid, String orig, String parent) throws Exception {
        return dataResolutionDAO.getDestValue(edxid, orig, parent);
    }

    public String getPartnerId(String transId) throws Exception {
        return dataResolutionDAO.getPartnerId(transId);
    }

    public String getTransactionId(String transkey) {
        return dataResolutionDAO.getTransactionId(transkey);
    }

    public String getTransactionType(String transkey){
        return dataResolutionDAO.getTransactionType(transkey);
    }
    public List findProcessedTransactionsList(String id, String userLoginId, String isSupplier, String search, String transactionNumber, int start, int limit, String sidx, String sord) throws SQLException{
        return dataResolutionDAO.findProcessedTransactionsList(id,userLoginId, isSupplier, search, transactionNumber, start, limit, sidx, sord);
    }

    public HashMap getTransactionId(Integer transactionkey){
        return dataResolutionDAO.getTransactionId(transactionkey);
    }

    public String getDRStoreFolder() throws java.sql.SQLException{
         return dataResolutionDAO.getDRStoreFolder();
    }
    public List<Role> getUsers(int nexusId){
         return dataResolutionDAO.getUsers(nexusId);
    }
    public List<JQGridRow> getAssignedPartners(Integer userId){
        return dataResolutionDAO.getAssignedPartners(userId);
    }
    public int getUsersCountByNexusId(int nexusId) throws SQLException{
        return dataResolutionDAO.getUsersCountByNexusId(nexusId);
    }
    public List<JQGridRow> getUsersByNexusId(int nexusId, int start, int limit, String sidx, String sord) throws SQLException{
        return dataResolutionDAO.getUsersByNexusId(nexusId, start, limit, sidx, sord);
    }
    public User getUserById(int id) throws SQLException{
        return dataResolutionDAO.getUserById(id);
    }

    public HashMap getNexusConfig(Integer nexusId){
        return dataResolutionDAO.getNexusConfig(nexusId);
    }
    
    public HashMap getNexusParameters(Integer nexusId){
        return dataResolutionDAO.getNexusParameters(nexusId);
    }
    public void assignPartnersToUser(String loginId, int userId, String morePartners, String existingPartners,ArrayList partners,List assignedPartners) throws SQLException {
        //rolesDAO.updateRoleNameByRoleId(roleName, roleId);
        int moreid=0;
        int assignNumber=0;
        List partnerList=new ArrayList();
        Partner partner=new Partner();
        
        if (!morePartners.equals("")) {
            String[] tabId = morePartners.split("\\,");
            for (int i = 0; i < tabId.length; i++) {
                moreid=new Integer(tabId[i]).intValue();
                partner=(Partner)partners.get(moreid-1);
                log.info("partnerId="+partner.getPartnerId()+".name."+partner.getPartnerName());
                dataResolutionDAO.assignPartner(userId, partner.getPartnerId(),partner.getPartnerName());
            }
        }
        if(!existingPartners.equals("")) {
            String[] tabId = existingPartners.split("\\,");
            for (int i = 0; i < tabId.length; i++) {
                assignNumber=new Integer(tabId[i]).intValue();
                //log.info("assignNumber="+assignNumber);
                JQGridRow jQGridRow=(JQGridRow)assignedPartners.get(assignNumber-1);
                partnerList=(ArrayList)jQGridRow.getCell();
                log.info("assigned userId.."+partnerList.get(0).toString()+"..name.."+partnerList.get(1).toString());
                dataResolutionDAO.deAssignPartner(userId, partnerList.get(1).toString());
            }
        }
        else{
                log.info("There are no assigned records for this user.");
        }
    }
    public Trans_Disp_Bean createNewDisplayObject(String fileName, String transType, String uName, String uType)
	{
		transaction_Manager=new Transaction_Manager();
        return transaction_Manager.createNewDisplayObject(fileName, transType, uName, uType);
	}

    public void removeDisplayObject(String fileName)
	{
        transaction_Manager=new Transaction_Manager();
        transaction_Manager.removeDisplayObject(fileName);
	}

    public String getTradeRouteFolderPath(String name){

        return dataResolutionDAO.getTradeRouteFolderPath(name);
    }

     /**
     * @return the partnerLinkDAO
     */
    public DataResolutionDAO getDataResolutionDAO() {
        return dataResolutionDAO;
    }

    /**
     * @param partnerLinkDAO the partnerLinkDAO to set
     */
    public void setDataResolutionDAO(DataResolutionDAO dataResolutionDAO) {
        this.dataResolutionDAO = dataResolutionDAO;
    }
}
