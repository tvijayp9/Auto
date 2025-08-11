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
public class MicrocatOrderDAOImpl implements MicrocatOrderDAO {

    Logger log=Logger.getLogger(MicrocatOrderDAOImpl.class);
    private SessionFactory sessionFactory;

    public int findMicrocatOrderCountByAccountNumber(String accountNumber,String nexusId) throws SQLException {
        String supplierId=null;
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        supplierId=getSupplierId(nexusId);
        log.info("findMicrocatOrderCountByAccountNumber..accountNumber.."+accountNumber+"..supplierId..."+supplierId);
        String selectStatement = "SELECT count(*) FROM mcat_order where accnum=? and status=2 and mcatsupid=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, accountNumber);
        ps.setString(2, supplierId);
        //ps.setInt(2, Integer.parseInt(supplierId));
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    public int findScaniaOrderCountByAccountNumber(String accountNumber,String nexusId,String email) throws SQLException {
        //String supplierId=null;
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //supplierId=getSupplierId(nexusId);
        log.info("findScaniaOrderCountByAccountNumber..accountNumber.."+accountNumber+"...email=="+email);
//        String selectStatement = "SELECT count(*) FROM scania_order where dealer_id=? and status=2 and email=?";
        String selectStatement = "SELECT count(*) FROM scania_order where Customer_No=? and status=2 and email=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, Integer.parseInt(accountNumber));
        ps.setString(2, email);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

     public String getSupplierId(String nexusId) throws SQLException {
        String supplierId=null;
         Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String selectStatement = "select xy_rules_values.VALUE  from xy_rules_values,xy_rules,xy_rules_values_mapping where  xy_rules.DESCRIPTION = 'MCATSUPID' and xy_rules.ID = xy_rules_values.RULE_ID and xy_rules_values.ID = xy_rules_values_mapping.RULES_VALUES_ID and xy_rules_values_mapping.XY_PLINK_ID= ?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, nexusId);
        rs = ps.executeQuery();
        if(rs.next())
            supplierId=rs.getString(1);

        return supplierId;
    }

    public int findMaxMicrocatOrderIdByAccountNumber(String accountNumber,String SupNexusId) throws SQLException {
        String supplierId=null;
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        supplierId=getSupplierId(SupNexusId);
        log.info("findMaxMicrocatOrderIdByAccountNumber..accountNumber.."+accountNumber+"...supplierId..."+supplierId);
        String selectStatement = "SELECT max(id) FROM mcat_order where accnum=? and status=2 and mcatsupid=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, accountNumber);
        ps.setString(2, supplierId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
     public int findMaxScaniaOrderIdByDealerId(Integer dealerId,String email) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        log.info("findMaxScaniaOrderIdByDealerId..dealerId.."+dealerId+"...email..."+email);
//        String selectStatement = "SELECT max(id) FROM scania_order where Dealer_ID=? and status=2 and email=?";
        String selectStatement = "SELECT max(id) FROM scania_order where Customer_No=? and status=2 and email=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, dealerId);
        ps.setString(2, email);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public void updateMicrocatOrderStatusByAccountNumber(String accountNumber,String SupNexusId) throws SQLException {
        String supplierId=null;
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        supplierId=getSupplierId(SupNexusId);
        log.info("updateMicrocatOrderStatusByAccountNumber..accountNumber.."+accountNumber+"..supplierId..."+supplierId);
        String updateStatement = "update mcat_order set status=3 where ACCNUM=? and status=2 and mcatsupid=?";
        ps = connection.prepareStatement(updateStatement);
        ps.setString(1, accountNumber);
        ps.setString(2, supplierId);
        ps.executeUpdate();
    }
    
    public void updateScaniaOrderStatusByAccountNumber(Integer dealer_id,String email) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateScaniaOrderStatusByAccountNumber..dealer_id.."+dealer_id+"..email..."+email);
//        String updateStatement = "update scania_order set status=3 where dealer_id=? and status=2 and email=?";
          String updateStatement = "update scania_order set status=3 where Customer_No=? and status=2 and email=?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, dealer_id);
        ps.setString(2, email);
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
