/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.dao;

import com.nexus.domain.InvoiceLineItem;
import com.nexus.domain.TemplateOrderItem;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface OrderItemsUMGDAO {

    void insertOrderItems(int orderId,String productCode, int qty,BigDecimal price,String description) throws SQLException;

    void insertCXMLOrderItems(int orderId,String productCode, int qty,BigDecimal price,String description,String uom,int lineNo,String deliverydate,String linelevelcomment) throws SQLException;

    void insertOrderQuoteItems(int orderId,String productCode, int qty,BigDecimal price,String description) throws SQLException;

    List<String> findOrderItemsByOrderNumber(String orderNumber) throws SQLException;

    List<TemplateOrderItem> findOrderItems(String orderNumber) throws SQLException;
    
    public void insertInvoiceLineItems(InvoiceLineItem invoiceLineItem) throws SQLException;
}
