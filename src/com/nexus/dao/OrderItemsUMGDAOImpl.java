/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.InvoiceLineItem;
import com.nexus.domain.TemplateOrderItem;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

/**
 *
 * @author Terry
 */
public class OrderItemsUMGDAOImpl implements OrderItemsUMGDAO {

    Logger log=Logger.getLogger(OrderItemsUMGDAOImpl.class);
    private SessionFactory sessionFactory;

    public void insertOrderItems(int orderId, String productCode, int qty, BigDecimal price, String description) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertOrderItems..orderId.."+orderId+"..productCode.."+productCode+"..qty.."+qty+"..description.."+description);
        String insertStatement = "insert into xy_order_line_items_umg (order_id,qty,product_code,price,description) values(?,?,?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, orderId);
        ps.setInt(2, new Integer(qty).intValue());
        ps.setString(3, productCode);
        ps.setBigDecimal(4, price);
        ps.setString(5, description);
        ps.executeUpdate();
    }

    public void insertCXMLOrderItems(int orderId, String productCode, int qty, BigDecimal price, String description,String uom,int lineNo,String deliverydate,String linelevelcomment) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertCXMLOrderItems..orderId.."+orderId+"..productCode.."+productCode+"..qty.."+qty+"..description.."+description+"..uom=="+uom+"...deliverydate="+deliverydate+"....linelevelcomment=="+linelevelcomment);
        String insertStatement = "insert into xy_order_line_items_umg (order_id,qty,product_code,price,description,uom,lineNo,deliveryDate,lineComment) values(?,?,?,?,?,?,?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, orderId);
        ps.setInt(2, new Integer(qty).intValue());
        ps.setString(3, productCode);
        ps.setBigDecimal(4, price);
        ps.setString(5, description);
        ps.setString(6, uom);
        ps.setInt(7, lineNo);
        ps.setString(8, deliverydate);
        ps.setString(9, linelevelcomment);
        
        ps.executeUpdate();
    }
    
    public void insertInvoiceLineItems(InvoiceLineItem invoiceLineItem) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
         PreparedStatement ps = null;
          ResultSet rs = null;
          int counter=0;
       // log.info("lineitemid=="+invoiceLineItem.getLineItemId());
        String hql = "select id from xy_invoice_line_items WHERE lineItemId = ?";
        ps = connection.prepareStatement(hql);
        ps.setInt(1, invoiceLineItem.getLineItemId());
        rs=ps.executeQuery();
        while (rs.next()) {
            counter=rs.getInt("id");
        }
      //  log.info("counter=="+counter);
        if(counter==0){
            insertInvoiceDetails(invoiceLineItem);
        }else if(invoiceLineItem.isInvoiced())
                insertInvoiceDetails(invoiceLineItem);
        
//        else{ 
//            if((invoiceLineItem.getInvoiceNo().equals(null))&& id)
//                udateInvoiceDetails(invoiceLineItem);
//            else
//                udateInvoiceDetails(invoiceLineItem);
//        }
    }
    
    public void insertInvoiceDetails(InvoiceLineItem invoiceLineItem) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        String insertStatement = "INSERT INTO xy_invoice_line_items(ORDER_ID, ordered_qty,QTY, product_code, price, description, invoiceNo, lineItemId,lineNo) VALUES (?,?,?,?,?,?,?,?,?)";
        log.info("in orderitemsUMGDAOIMPL......"+invoiceLineItem.getOrderId()+"......."+invoiceLineItem.getOrderedQuantity()+"......."+invoiceLineItem.getQuantity()+"......."+invoiceLineItem.getProductCode()+"......."+invoiceLineItem.getUnitprice()+"......."+invoiceLineItem.getDescription()+"......."+invoiceLineItem.getInvoiceNo()+"......."+invoiceLineItem.getLineItemId()+"......."+invoiceLineItem.getLineNo()+"....."+invoiceLineItem.isInvoiced());
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, invoiceLineItem.getOrderId());
        ps.setInt(2, invoiceLineItem.getOrderedQuantity());
        ps.setInt(3, invoiceLineItem.getQuantity());
        ps.setString(4, invoiceLineItem.getProductCode());
        ps.setBigDecimal(5, invoiceLineItem.getUnitprice());
        ps.setString(6, invoiceLineItem.getDescription());
        ps.setString(7, invoiceLineItem.getInvoiceNo());
        ps.setInt(8, invoiceLineItem.getLineItemId());
        ps.setInt(9, invoiceLineItem.getLineNo());
        ps.executeUpdate();
    }
    
    public void udateInvoiceDetails(InvoiceLineItem invoiceLineItem) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        String insertStatement = "Update xy_invoice_line_items set QTY=?,price=? where lineItemId=?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, invoiceLineItem.getQuantity());
        ps.setBigDecimal(2, invoiceLineItem.getUnitprice());
        ps.setInt(3, invoiceLineItem.getId());
        ps.executeUpdate();
    }

    public void insertOrderQuoteItems(int orderId, String productCode, int qty, BigDecimal price, String description) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertOrderQuoteItems..orderId.."+orderId+"..productCode.."+productCode+"..qty.."+qty+"..description.."+description);
        String insertStatement = "insert into xy_order_quote_items (order_id,qty,product_code,price,description) values(?,?,?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, orderId);
        ps.setInt(2, new Integer(qty).intValue());
        ps.setString(3, productCode);
        ps.setBigDecimal(4, price);
        ps.setString(5, description);
       
        ps.executeUpdate();
    }

    public List<String> findOrderItemsByOrderNumber(String orderNumber) throws SQLException{
              Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> items = new ArrayList();
        log.info("findOrderItemsByOrderNumber..orderNumber.."+orderNumber);
        String selectStatement = "select oliu.product_code from xy_order o inner join xy_order_line_items_umg oliu on o.id=oliu.order_id where o.orderno=? order by oliu.id";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, orderNumber);
        rs = ps.executeQuery();
        while (rs.next()) {
            items.add(rs.getString("product_code"));
        }
        return items;
    }

    public List<TemplateOrderItem> findOrderItems(String orderNumber) throws SQLException{
              Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TemplateOrderItem> items = new ArrayList();
        log.info("findOrderItems..orderNumber.."+orderNumber);
        String selectStatement = "select oliu.qty,oliu.product_code,oliu.price,oliu.description from xy_order o inner join xy_order_line_items_umg oliu on o.id=oliu.order_id where o.orderno=? order by oliu.id";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, orderNumber);
        rs = ps.executeQuery();
        while (rs.next()) {
            items.add(new TemplateOrderItem(rs.getInt("qty"),rs.getString("product_code"),rs.getString("description"),rs.getBigDecimal("price")));
        }
        return items;
    }
    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
