/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface MessageDAO {

    List findInBoundMessageList(String id, String search,String vch_document_id,String company,String dt_received,String dt1_received,int start, int limit, String sidx, String sord) throws SQLException;

    int findInBoundMessageListCount(String id,String search,String vch_document_id,String company,String dt_received,String dt1_received) throws SQLException;
    
    List findInBoundArchivedMessageList(String id, String search,String vch_document_id,String company,String dt_received,String dt1_received,int start, int limit, String sidx, String sord) throws SQLException;

    int findInBoundArchivedMessageListCount(String id,String search,String vch_document_id,String company,String dt_received,String dt_received1) throws SQLException;

    void updateMessageStatus(String id, int status) throws SQLException;

    List findOutBoundMessageList(String id, String search,String vch_document_id,String company,String dt_received,String dt1_received, int start, int limit, String sidx, String sord) throws SQLException;

    int findOutBoundMessageListCount(String id,String search,String vch_document_id,String company,String dt_received,String dt1_received) throws SQLException;

    List findOutBoundArchivedMessageList(String id, String search,String vch_document_id,String company,String dt_received,String dt1_received,int start, int limit, String sidx, String sord) throws SQLException;

    int findOutBoundArchivedMessageListCount(String id,String search,String vch_document_id,String company,String dt_received,String dt1_received) throws SQLException;

    List findInBoundMessageByStatus(int status,int id) throws SQLException;

    int findInBoundMessageCountByStatus(int status,int id) throws SQLException;

    void insertMessage(String orderNumber, int id, int transactionType, int supId, String fileName) throws SQLException;

    public void insertCXMLMessage(String orderNumber, int id, int transactionType, int supId, String fileName,int orderid) throws SQLException;
}
