/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.MicrocatOrderItems;
import com.nexus.domain.ShoppingCartItem;
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

/**
 *
 * @author Terry
 */
public class MicrocatOrderItemsDAOImpl implements MicrocatOrderItemsDAO {

    Logger log=Logger.getLogger(MicrocatOrderItemsDAOImpl.class);
    private SessionFactory sessionFactory;

    public List findMicrocatOrderItemsByOrderId(int orderId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<MicrocatOrderItems> list = new ArrayList();
        log.info("findMicrocatOrderItemsByOrderId..orderId.."+orderId);
        String selectStatement = "select PART_NUM,DESCRIPTION,QTY,abs(PRICE) from mcat_order_line_items where MCAT_ORDER_ID=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, orderId);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new MicrocatOrderItems(rs.getString("PART_NUM"), rs.getString("DESCRIPTION"), rs.getBigDecimal("abs(PRICE)"), rs.getInt("QTY")));
        }
        return list;
    }
    
     public List findScaniaOrderItemsByOrderId(int orderId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<MicrocatOrderItems> list = new ArrayList();
        log.info("findScaniaOrderItemsByOrderId..orderId.."+orderId);
        String selectStatement = "select PART_NUM,DESCRIPTION,QTY,abs(PRICE) from scania_order_line_items where SCANIA_ORDER_ID=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, orderId);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new MicrocatOrderItems(rs.getString("PART_NUM"), rs.getString("DESCRIPTION"), rs.getBigDecimal("abs(PRICE)"), rs.getInt("QTY")));
        }
        return list;
    }

    public TemplateOrderItem findProductDetailsForTemplate(String productCode) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        TemplateOrderItem toi = null;
        log.info("findProductDetailsForTemplate..productCode.."+productCode);
        String selectStatement = "SELECT description,abs(price),QTY from mcat_order_line_items where part_num=? order by id desc";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, productCode);
        rs = ps.executeQuery();
        if (rs.next()) {
            BigDecimal  unitPrice = rs.getBigDecimal("ABS(price)");
             BigDecimal price = unitPrice.multiply(new BigDecimal(rs.getInt("QTY")));
            BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = price.add(tax);
            toi = new TemplateOrderItem(productCode, rs.getString("description"), price,price, tax, rs.getInt("QTY"), cost, 1);
        }
        return toi;
    }

    public ShoppingCartItem findProductDetailsForShoppingCart(String productCode) throws SQLException{
            Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ShoppingCartItem sci = null;
        log.info("findProductDetailsForShoppingCart..productCode.."+productCode);
        String selectStatement = "SELECT description,abs(price) from mcat_order_line_items where part_num=? order by id desc";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, productCode);
        rs = ps.executeQuery();
        if (rs.next()) {
            BigDecimal unitPrice=rs.getBigDecimal("abs(price)");
            BigDecimal tax = unitPrice.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = unitPrice.add(tax);
            sci = new ShoppingCartItem(productCode, rs.getString("description"), unitPrice, unitPrice, tax, 1, cost, 0);
        }
        return sci;
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
