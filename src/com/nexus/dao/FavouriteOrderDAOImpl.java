/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Terry
 */
public class FavouriteOrderDAOImpl implements FavouriteOrderDAO {

    Logger log=Logger.getLogger(FavouriteOrderDAOImpl.class);
    private SessionFactory sessionFactory;

    public void insertFavouriteOrder(String orderNumber, String favouriteName, int id, int supplierId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertFavouriteOrder..orderNumber.."+orderNumber+"...favouriteName..."+favouriteName+"..id.."+id+"..supplierId.."+supplierId);
        String insertStatement = "insert into xy_favourite_order (orderno,favouritename,buyer_id,supplier_id) values(?,?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setString(1, orderNumber);
        ps.setString(2, favouriteName);
        ps.setInt(3, id);
        ps.setInt(4, supplierId);
        ps.executeUpdate();
    }

    public int findFavouriteOrderIdByOrderNumber(String orderNumber) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findFavouriteOrderIdByOrderNumber..orderNumber.."+orderNumber);
        String selectStatement = "Select id from xy_favourite_order where orderno=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, orderNumber);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

    public int findFavouriteOrderId(String templateName, int id, int supplierId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findFavouriteOrderId..templateName.."+templateName+"..id.."+id+"..supplierId.."+supplierId);
        String selectStatement = "Select max(id) from xy_favourite_order where orderno=? and favouritename=? and buyer_id=? and supplier_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, templateName);
        ps.setString(2, templateName);
        ps.setInt(3, id);
        ps.setInt(4, supplierId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public void deleteFavouriteOrder(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("deleteFavouriteOrder...id.."+id);
        String updateStatement = "delete from xy_favourite_order where id = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, id);
        ps.executeUpdate();
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
