/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author terrysu
 */
public class UMGOrdersQuotesDAOImpl implements UMGOrdersQuotesDAO{

    Logger log=Logger.getLogger(UMGOrdersQuotesDAOImpl.class);
    private SessionFactory sessionFactory;

    public void insertOrderQuote(int orderId,String qrn) throws SQLException{
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertOrderQuote...orderId.."+orderId+"..qrn..."+qrn);
        String insertStatement = "insert into xy_umg_orders_qrn (orderid,qrn) values(?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, orderId);
        ps.setString(2, qrn);
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
