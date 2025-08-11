/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import com.nexus.domain.Order;
import com.nexus.domain.OrderAddressData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Terry
 */
public class OrderDAOImpl implements OrderDAO {

    Logger log=Logger.getLogger(OrderDAOImpl.class);
    private SessionFactory sessionFactory;

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

    public int findOrderListCount(String id, String supId, String search, String orderNumber, String orderDate, String orderDate1) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findOrderListCount..id.."+id+"..supId.."+supId+"..search.."+search+"..orderNumber.."+orderNumber+"..orderDate.."+orderDate+"..orderDate1.."+orderDate1);
        String selectStatement = "SELECT count(id) FROM xy_order where buyid = ? and supid=?";
        if (search.equals("true")) {
            if (orderNumber != null) {
                selectStatement += " and OrderNo like '%" + orderNumber + "%'";
            }
            if ((orderDate != null) && (orderDate1 != null)) {
                selectStatement += " and DATE(order_date) between '" + orderDate + "' and '" + orderDate1 + "'";
            }
            if ((orderDate != null) && (orderDate1 == null)) {
                selectStatement += " and DATE(order_date)>='" + orderDate + "'";
            }
            if ((orderDate == null) && (orderDate1 != null)) {
                selectStatement += " and DATE(order_date)<='" + orderDate1 + "'";
            }
        }
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        ps.setInt(2, new Integer(supId).intValue());
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public int findArchivedOrderListCount(String id, String supId, String search, String orderNumber, String orderDate, String orderDate1) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findArchivedOrderListCount..id.."+id+"..supId.."+supId+"..search.."+search+"..orderNumber.."+orderNumber+"..orderDate.."+orderDate+"..orderDate1.."+orderDate1);
        String selectStatement = "SELECT count(id) FROM xy_order_archive where buyid = ? and supid=?";
        if (search.equals("true")) {
            if (orderNumber != null) {
                selectStatement += " and OrderNo like '%" + orderNumber + "%'";
            }
            if ((orderDate != null) && (orderDate1 != null)) {
                selectStatement += " and DATE(order_date) between '" + orderDate + "' and '" + orderDate1 + "'";
            }
            if ((orderDate != null) && (orderDate1 == null)) {
                selectStatement += " and DATE(order_date)>='" + orderDate + "'";
            }
            if ((orderDate == null) && (orderDate1 != null)) {
                selectStatement += " and DATE(order_date)<='" + orderDate1 + "'";
            }
        }
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        ps.setInt(2, new Integer(supId).intValue());
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List findOrderList(String id, String supId, String search, String orderNumber, String orderDate, String orderDate1, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("findOrderList..id.."+id+"..supId.."+supId+"..search.."+search+"..orderNumber.."+orderNumber+"..orderDate.."+orderDate+"..orderDate1.."+orderDate1);
        String selectStatement = "SELECT o.id, o.OrderNo,o.order_date,o.status,o.delivery_date,o.comment,uoq.qrn FROM xy_order o left join xy_umg_orders_qrn uoq on o.id=uoq.orderid where o.buyid = ? and o.supid=?";
        if (search.equals("true")) {
            if (orderNumber != null) {
                selectStatement += " and OrderNo like '%" + orderNumber + "%'";
            }
            if ((orderDate != null) && (orderDate1 != null)) {
                selectStatement += " and DATE(order_date) between '" + orderDate + "' and '" + orderDate1 + "'";
            }
            if ((orderDate != null) && (orderDate1 == null)) {
                selectStatement += " and DATE(order_date)>='" + orderDate + "'";
            }
            if ((orderDate == null) && (orderDate1 != null)) {
                selectStatement += " and DATE(order_date)<='" + orderDate1 + "'";
            }
        }
        selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        ps.setInt(2, new Integer(supId).intValue());
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("OrderNo"));
            cell.add(rs.getString("order_date"));
            cell.add(rs.getString("delivery_date"));
            cell.add(rs.getString("status"));
            cell.add(rs.getString("comment"));
            cell.add(rs.getString("qrn"));
            cell.add("");
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public List findArchivedOrderList(String id, String supId, String search, String orderNumber, String orderDate, String orderDate1, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("findArchivedOrderList..id.."+id+"..supId.."+supId+"..search.."+search+"..orderNumber.."+orderNumber+"..orderDate.."+orderDate+"..orderDate1.."+orderDate1);
        String selectStatement = "SELECT id, OrderNo,order_date,status,delivery_date,comment FROM xy_order_archive where buyid = ? and supid=?";
        if (search.equals("true")) {
            if (orderNumber != null) {
                selectStatement += " and OrderNo like '%" + orderNumber + "%'";
            }
            if ((orderDate != null) && (orderDate1 != null)) {
                selectStatement += " and DATE(order_date) between '" + orderDate + "' and '" + orderDate1 + "'";
            }
            if ((orderDate != null) && (orderDate1 == null)) {
                selectStatement += " and DATE(order_date)>='" + orderDate + "'";
            }
            if ((orderDate == null) && (orderDate1 != null)) {
                selectStatement += " and DATE(order_date)<='" + orderDate1 + "'";
            }
        }
        selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        ps.setInt(2, new Integer(supId).intValue());
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("OrderNo"));
            cell.add(rs.getString("order_date"));
            cell.add(rs.getString("delivery_date"));
            cell.add(rs.getString("status"));
            cell.add(rs.getString("comment"));
            cell.add("");
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public int findFutureOrderListCount(String id, String supId, String formattedDate, String search, String orderNumber, String orderDate) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findFutureOrderListCount..id.."+id+"..supId.."+supId+"..search.."+search+"..orderNumber.."+orderNumber+"..orderDate.."+orderDate+"..formattedDate.."+formattedDate);
        String selectStatement = "SELECT count(id) FROM xy_order where buyid = ? and delivery_date > ? and supid=?";
        if (search.equals("true")) {
            if (orderNumber != null) {
                selectStatement += " and OrderNo like '%" + orderNumber + "%'";
            }
            if (orderDate != null) {
                selectStatement += " and DATE(order_date)='" + orderDate + "'";
            }
        }
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        ps.setString(2, formattedDate);
        ps.setInt(3, new Integer(supId).intValue());
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List findFutureOrderList(String id, String supId, String formattedDate, String search, String orderNumber, String orderDate, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("findFutureOrderList..id.."+id+"..supId.."+supId+"..search.."+search+"..orderNumber.."+orderNumber+"..orderDate.."+orderDate+"..formattedDate.."+formattedDate);
        String selectStatement = "SELECT o.id, o.OrderNo,o.order_date,o.status,o.delivery_date,o.comment,uoq.qrn FROM xy_order o left join xy_umg_orders_qrn uoq on o.id=uoq.orderid where o.buyid = ? and o.delivery_date>? and o.supid=?";
        if (search.equals("true")) {
            if (orderNumber != null) {
                selectStatement += " and OrderNo like '%" + orderNumber + "%'";
            }
            if (orderDate != null) {
                selectStatement += " and DATE(order_date)='" + orderDate + "'";
            }
        }
        selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        ps.setString(2, formattedDate);
        ps.setInt(3, new Integer(supId).intValue());
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("OrderNo"));
            cell.add(rs.getString("order_date"));
            cell.add(rs.getString("delivery_date"));
            cell.add(rs.getString("status"));
            cell.add(rs.getString("comment"));
            cell.add(rs.getString("qrn"));
            cell.add("");
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public void insertOrder(String orderNumber, String id, String supId, String deliveryDate, String comment) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertOrder..id.."+id+"..supId.."+supId+"..comment.."+comment+"..deliveryDate.."+deliveryDate);
        String insertStatement = "insert into xy_order (orderno,buyid,supid,delivery_date,status,comment) values(?,?,?,?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setString(1, orderNumber);
        ps.setString(2, id);
        ps.setString(3, supId);
        ps.setString(4, deliveryDate);
        ps.setString(5, "InProgress");
        ps.setString(6, comment);
        ps.executeUpdate();
    }

    public Integer insertCXMLOrder(String orderNumber, String id, String supId, String deliveryDate, String comment,String quoteNo) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        Integer insertedId=0;
        log.info("insertCXMLOrder..id.."+id+"..supId.."+supId+"..comment.."+comment+"..deliveryDate.."+deliveryDate);
        String insertStatement = "insert into xy_order (orderno,buyid,supid,delivery_date,status,comment,orderQuoteNo) values(?,?,?,?,?,?,?)";
        ps = connection.prepareStatement(insertStatement,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, orderNumber);
        ps.setString(2, id);
        ps.setString(3, supId);
        ps.setString(4, deliveryDate);
        ps.setString(5, "InProgress");
        ps.setString(6, comment);
        ps.setString(7, quoteNo);
        ps.executeUpdate();
        ResultSet keys = ps.getGeneratedKeys();
        keys.next();
        insertedId = keys.getInt(1);
        return insertedId;
    }

    public void insertOrderQuote(String orderNumber, String id, String supId, String deliveryDate, String comment) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertOrder..id.."+id+"..supId.."+supId+"..comment.."+comment+"..deliveryDate.."+deliveryDate);
        String insertStatement = "insert into xy_order_quote (orderno,buyid,supid,delivery_date,status,comment) values(?,?,?,?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setString(1, orderNumber);
        ps.setString(2, id);
        ps.setString(3, supId);
        ps.setString(4, deliveryDate);
        ps.setString(5, "InProgress");
        ps.setString(6, comment);
        ps.executeUpdate();
    }

    public void updateOrderStatus(String orderNumber, String autoOrderNumber, String id, String supId, String status) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateOrderStatus..id.."+id+"..supId.."+supId+"..autoOrderNumber.."+autoOrderNumber+"..status.."+status+"..orderNumber.."+orderNumber);
        String updateStatement = "UPDATE xy_order SET status = ?,orderno=? where orderno = ? and buyid=? and supid = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setString(1, status);
        ps.setString(2, orderNumber);
        ps.setString(3, autoOrderNumber);
        ps.setString(4, id);
        ps.setString(5, supId);
        ps.executeUpdate();
    }

    public void updateOrderStatusForShoppingCart(String orderNumber, String autoOrderNumber, String id, String supId, String status, String deliveryDate, String comment) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateOrderStatusForShoppingCart..id.."+id+"..supId.."+supId+"..autoOrderNumber.."+autoOrderNumber+"..status.."+status+"..orderNumber.."+orderNumber+"..deliveryDate.."+deliveryDate+"..comment.."+comment);
        String updateStatement = "UPDATE xy_order SET status = ?,orderno=?,delivery_date=?,comment=? where orderno = ? and buyid=? and supid = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setString(1, status);
        ps.setString(2, orderNumber);
        ps.setString(3, deliveryDate);
        ps.setString(4, comment);
        ps.setString(5, autoOrderNumber);
        ps.setString(6, id);
        ps.setString(7, supId);
        ps.executeUpdate();
    }

    public void updateOrderQuoteStatusForShoppingCart(String orderNumber, String autoOrderNumber, String id, String supId, String status, String deliveryDate, String comment) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateOrderStatusForShoppingCart..id.."+id+"..supId.."+supId+"..autoOrderNumber.."+autoOrderNumber+"..status.."+status+"..orderNumber.."+orderNumber+"..deliveryDate.."+deliveryDate+"..comment.."+comment);
        String updateStatement = "UPDATE xy_order_quote SET status = ?,orderno=?,delivery_date=?,comment=? where orderno = ? and buyid=? and supid = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setString(1, status);
        ps.setString(2, orderNumber);
        ps.setString(3, deliveryDate);
        ps.setString(4, comment);
        ps.setString(5, autoOrderNumber);
        ps.setString(6, id);
        ps.setString(7, supId);
        ps.executeUpdate();
    }

    public int findOrderId(String orderNumber, String id, String supId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findOrderId..id.."+id+"..supId.."+supId+"..orderNumber.."+orderNumber);
        String selectStatement = "select id from xy_order where orderno = ? and buyid=? and supid=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, orderNumber);
        ps.setString(2, id);
        ps.setString(3, supId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public int findOrderQuoteId(String orderNumber, String id, String supId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findOrderQuoteId..id.."+id+"..supId.."+supId+"..orderNumber.."+orderNumber);
        String selectStatement = "select id from xy_order_quote where orderno = ? and buyid=? and supid=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, orderNumber);
        ps.setString(2, id);
        ps.setString(3, supId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public Order findOrderByMessageId(String messageId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findOrderByMessageId..messageId.."+messageId);
        String selectStatement = "select o.id,o.orderno,o.buyid,o.supid,o.delivery_date,o.comment from xy_order o inner join xy_message m on o.orderno=m.vch_document_id where m.n_message_id = ?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, messageId);
        rs = ps.executeQuery();
        if (rs.next()) {
            return new Order(rs.getInt("id"), rs.getString("orderno"), rs.getString("buyid"), rs.getString("supid"), rs.getString("delivery_date"), rs.getString("comment"));
        } else {
            return null;
        }
    }
    
    public Order findOrderByMsgId(String messageId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findOrderByMsgId..messageId.."+messageId);
        String selectStatement = "select o.id,o.orderno,o.buyid,o.supid,o.delivery_date,o.invoiceNo,o.comment from xy_order o inner join xy_message m on o.orderno=m.vch_document_id where m.n_message_id = ?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, messageId);
        rs = ps.executeQuery();
        if (rs.next()) {
            return new Order(rs.getInt("id"), rs.getString("orderno"), rs.getString("buyid"), rs.getString("supid"), rs.getString("delivery_date"), rs.getString("invoiceNo"),rs.getString("comment"));
        } else {
            return null;
        }
    }

    public Order findArchivedOrderByMessageId(String messageId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findArchivedOrderByMessageId..messageId.."+messageId);
        String selectStatement = "select o.id,o.orderno,o.buyid,o.supid,o.delivery_date,o.comment from xy_order_archive o inner join xy_message_archive m on o.orderno=m.vch_document_id where m.n_message_id = ?";
        log.info(" selectStatement = " + selectStatement);
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, messageId);
        rs = ps.executeQuery();
        if (rs.next()) {
            return new Order(rs.getInt("id"), rs.getString("orderno"), rs.getString("buyid"), rs.getString("supid"), rs.getString("delivery_date"), rs.getString("comment"));
        } else {
            return null;
        }
    }

    public void insertCXMLOrderAddress(Integer orderId, OrderAddressData orderAddressData) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertCXMLOrderAddress..orderId.."+orderId);
        String insertStatement = "INSERT INTO xy_order_address(orderId,shipToAddressId,shipToName, shipToDelivery, shipToStreet, shipToCity, shipToState, shipToPOCode, shipToCountry, shipToEmail, billToName, billToDelivery, billToStreet, billToCity, billToState, billToPOCode, billToCountry,billingName,headerNote,shippingNote) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, orderId);
        ps.setString(2, orderAddressData.getShipToAddressId());
        ps.setString(3, orderAddressData.getShipToName());
        ps.setString(4, orderAddressData.getShipToDelivery());
        ps.setString(5, orderAddressData.getShipToStreet());
        ps.setString(6, orderAddressData.getShipToCity());
        ps.setString(7, orderAddressData.getShipToState());
        ps.setInt(8, orderAddressData.getShipToPOCode());
        ps.setString(9, orderAddressData.getShipToCountry());
        ps.setString(10, orderAddressData.getShipToEmail());
        ps.setString(11, orderAddressData.getBillToName());
        ps.setString(12, orderAddressData.getBillToDelivery());
        ps.setString(13, orderAddressData.getBillToStreet());
        ps.setString(14, orderAddressData.getBillToCity());
        ps.setString(15, orderAddressData.getBillToState());
        ps.setInt(16, orderAddressData.getBillToPOCode());
        ps.setString(17, orderAddressData.getBillToCountry());
        ps.setString(18, orderAddressData.getBillingName());
        ps.setString(19, orderAddressData.getHeaderNote());
        ps.setString(20, orderAddressData.getShippingNote());
        ps.executeUpdate();
    }

    public OrderAddressData getCXMLOrderAddress(Integer messageId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        OrderAddressData orderAddressData=null;
        log.info("getCXMLOrderAddress..messageId.."+messageId);
        String insertStatement = "select x.orderId,x.shipToAddressId,x.shipToName, x.shipToDelivery, x.shipToStreet, x.shipToCity, x.shipToState, x.shipToPOCode, x.shipToCountry, x.shipToEmail, x.billToName, x.billToDelivery, x.billToStreet, x.billToCity, x.billToState, x.billToPOCode, x.billToCountry,x.billingName,x.headerNote,x.shippingNote from xy_order_address x,xy_message y where x.orderId=y.orderId and y.N_MESSAGE_ID=?";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, messageId);
        rs=ps.executeQuery();
        while(rs.next()){
            orderAddressData=new OrderAddressData(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getInt(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20));
        }
        return orderAddressData;
    }
    
    public void generateInvoice(String orderNo,String invoiceNo) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("generateInvoice..id.."+orderNo+"..invoiceNo.."+invoiceNo);
        String updateStatement = "UPDATE xy_order SET invoiceNo = ? where orderno = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setString(1, invoiceNo);
        ps.setString(2, orderNo);
        ps.executeUpdate();
    }
    
    public Order findOrderByorderNo(String orderNo) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findOrderByorderNo..orderNo.."+orderNo);
        String selectStatement = "select id,orderno,buyid,supid,delivery_date,invoiceNo,comment from xy_order where orderNo = ?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, orderNo);
        rs = ps.executeQuery();
        if (rs.next()) {
            return new Order(rs.getInt("id"), rs.getString("orderno"), rs.getString("buyid"), rs.getString("supid"), rs.getString("delivery_date"), rs.getString("invoiceNo"),rs.getString("comment"));
        } else {
            return null;
        }
    }


}
