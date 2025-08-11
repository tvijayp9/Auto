/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.Order;
import com.nexus.domain.OrderAddressData;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface OrderDAO {

    List findOrderList(String id, String supId, String search,String orderNumber,String orderDate,String orderDate1,int start, int limit, String sidx, String sord) throws SQLException;

    List findArchivedOrderList(String id, String supId, String search,String orderNumber,String orderDate,String orderDate1,int start, int limit, String sidx, String sord) throws SQLException;

    int findOrderListCount(String id, String supId,String search,String orderNumber,String orderDate,String orderDate1) throws SQLException;

    int findArchivedOrderListCount(String id, String supId,String search,String orderNumber,String orderDate,String orderDate1) throws SQLException;

    List findFutureOrderList(String id, String supId, String formattedDate, String search,String orderNumber,String orderDate,int start, int limit, String sidx, String sord) throws SQLException;

    int findFutureOrderListCount(String id, String supId, String formattedDate,String search,String orderNumber,String orderDate) throws SQLException;

    void insertOrder(String orderNumber, String id, String supId, String deliveryDate, String comment) throws SQLException;

    Integer insertCXMLOrder(String orderNumber, String id, String supId, String deliveryDate, String comment,String quoteNo) throws SQLException;

    void insertOrderQuote(String orderNumber, String id, String supId, String deliveryDate, String comment) throws SQLException;

    void updateOrderStatus(String orderNumber, String autoOrderNumber, String id, String supId, String status) throws SQLException;

    void updateOrderStatusForShoppingCart(String orderNumber, String autoOrderNumber, String id, String supId, String status,String deliveryDate,String comment) throws SQLException;

    void updateOrderQuoteStatusForShoppingCart(String orderNumber, String autoOrderNumber, String id, String supId, String status,String deliveryDate,String comment) throws SQLException;

    int findOrderId(String orderNumber,String id,String supId) throws SQLException;

    int findOrderQuoteId(String orderNumber,String id,String supId) throws SQLException;

    Order findOrderByMessageId(String messageId) throws SQLException;

    Order findArchivedOrderByMessageId(String messageId) throws SQLException;

    public void insertCXMLOrderAddress(Integer orderId, OrderAddressData orderAddressData) throws SQLException;

    public OrderAddressData getCXMLOrderAddress(Integer messageId) throws SQLException;
    
    public void generateInvoice(String orderNo,String invoiceNo) throws SQLException;
    
     public Order findOrderByorderNo(String orderNo) throws SQLException;
     
     public Order findOrderByMsgId(String messageId) throws SQLException ;
}
