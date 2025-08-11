/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.services;

import com.nexus.domain.InvoiceLineItem;
import com.nexus.domain.OrderAddressData;
import com.nexus.domain.PurchaseOrder;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface TransactionService {

int getInBoundMessageListCount(String id,String search,String vch_document_id,String company,String dt_received,String dt1_received) throws SQLException;

List getInBoundMessageList(String id,String search,String vch_document_id,String company,String dt_received,String dt1_received,int start, int limit, String sidx, String sord) throws SQLException;

int getInBoundArchivedMessageListCount(String id,String search,String vch_document_id,String company,String dt_received,String dt1_received) throws SQLException;

List getInBoundArchivedMessageList(String id,String search,String vch_document_id,String company,String dt_received,String dt1_received,int start, int limit, String sidx, String sord) throws SQLException;


void updateMessageStatus(String id,int status) throws SQLException;

int getOutBoundMessageListCount(String id,String search,String vch_document_id,String company,String dt_received,String dt1_received) throws SQLException;

List getOutBoundMessageList(String id,String search,String vch_document_id,String company,String dt_received,String dt1_received, int start, int limit, String sidx, String sord) throws SQLException;

int getOutBoundArchivedMessageListCount(String id,String search,String vch_document_id,String company,String dt_received,String dt1_received) throws SQLException;

List getOutBoundArchivedMessageList(String id,String search,String vch_document_id,String company,String dt_received,String dt1_received, int start, int limit, String sidx, String sord) throws SQLException;

PurchaseOrder getPurchaseOrderFromInboundTransaction(String messageId) throws SQLException,ParseException;

PurchaseOrder getPurchaseOrderFromArchivedInboundTransaction(String messageId) throws SQLException,ParseException;

PurchaseOrder getPurchaseOrderFromOutboundTransaction(String messageId) throws SQLException,ParseException;

PurchaseOrder getPurchaseOrderFromArchivedOutboundTransaction(String messageId) throws SQLException,ParseException;

List getAllSelectedOrders(String id) throws SQLException,ParseException;

List getAllSelectedArchivedInboundOrders(String id) throws SQLException,ParseException;

List getAllNewOrders(int id) throws SQLException,ParseException;

int getAllNewOrdersCount(int id) throws SQLException;

public OrderAddressData getCXMLOrderAddress(Integer orderId) throws SQLException;

public void generateInvoice(String orderNo,String invoiceNo) throws SQLException;

public PurchaseOrder getPurchaseOrderInfo(String orderNo) throws SQLException, ParseException;

public PurchaseOrder getPurchaseOrder(String messageId) throws SQLException, ParseException ;

public PurchaseOrder getPurchaseOrderforInvoice(String messageId) throws SQLException, ParseException;
 
public List constructInvoiceLineItems(List<InvoiceLineItem> invoiceLineItems) throws SQLException;
}
