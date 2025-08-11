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
public class AutoOrderCountDAOImpl implements AutoOrderCountDAO {

    Logger log=Logger.getLogger(AutoOrderCountDAOImpl.class);
    private SessionFactory sessionFactory;

    public int getAutoOrderNumber(String id, String supId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("..getAutoOrderNumber..id.."+id+"..supId.."+supId);
        String selectStatement = "select total_count from xy_auto_order_count where buyer_id=? and supplier_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(id).intValue());
        ps.setInt(2, new Integer(supId).intValue());
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    public void updateAutoOrderNumber(int number, String id, String supId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("..updateAutoOrderNumber..id.."+id+"..supId.."+supId+"..number.."+number);
        String updateStatement = "update xy_auto_order_count set total_count=? where buyer_id=? and supplier_id=?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, number);
        ps.setInt(2, new Integer(id).intValue());
        ps.setInt(3, new Integer(supId).intValue());
        ps.executeUpdate();
    }

    public void insertAutoOrderNumber(int number, String id, String supId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("..insertAutoOrderNumber..id.."+id+"..supId.."+supId+"..number.."+number);
        String insertStatement = "insert into xy_auto_order_count (buyer_id,supplier_id,total_count) values (?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, new Integer(id).intValue());
        ps.setInt(2, new Integer(supId).intValue());
        ps.setInt(3, number);
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
