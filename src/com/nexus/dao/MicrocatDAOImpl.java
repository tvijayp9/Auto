/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import com.nexus.domain.Microcat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Terry
 */
public class MicrocatDAOImpl implements MicrocatDAO {

    Logger log=Logger.getLogger(MicrocatDAOImpl.class);
    private SessionFactory sessionFactory;

    public Microcat findMicroInfo(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findMicroInfo...id.."+id);
        String selectStatement = "SELECT id,account_number,username,password FROM microcat_login where nexus_id=? and status=1";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            return new Microcat(rs.getInt("id"), rs.getString("account_number"), rs.getString("username"), rs.getString("password"));
        } else {
            return null;
        }
    }

    public Microcat findMicrocatById(int accountId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findMicrocatById...accountId.."+accountId);
        String selectStatement = "SELECT id,nexus_id,account_number,username,password,status FROM microcat_login where id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, accountId);
        rs = ps.executeQuery();
        if (rs.next()) {
            return new Microcat(rs.getInt("id"), rs.getInt("nexus_id"), rs.getString("account_number"), rs.getString("username"), rs.getString("password"), rs.getInt("status"));
        } else {
            return null;
        }
    }

    public void insertMicrocatAccounts(int newMemberId, String accountNumber, String username, String password) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertMicrocatAccounts...newMemberId.."+newMemberId+"..accountNumber.."+accountNumber);
        String insertStatement = "insert into microcat_login (nexus_id,account_number,username,password) VALUES (?,?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, newMemberId);
        ps.setString(2, accountNumber);
        ps.setString(3, username);
        ps.setString(4, password);
        ps.executeUpdate();
    }

    public void updateMicrocatStatus(int microcatId, int status) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateMicrocatStatus...microcatId.."+microcatId+"..status.."+status);
        String updateStatement = "update microcat_login set status=? where id=?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, status);
        ps.setInt(2, microcatId);
        ps.executeUpdate();
    }

    public Microcat findMicroInfoByTime(int id, Timestamp t) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findMicroInfoByTime...id.."+id);
        String selectStatement = "SELECT id,account_number,username,password FROM microcat_login where nexus_id=? and status=0 and startTime<=?;";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setTimestamp(2, t);
        rs = ps.executeQuery();
        if (rs.next()) {
            return new Microcat(rs.getInt("id"), rs.getString("account_number"), rs.getString("username"), rs.getString("password"));
        } else {
            return null;
        }
    }

    public void updateMicrocatTime(int microcatId, Timestamp t) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateMicrocatTime...microcatId.."+microcatId);
        String updateStatement = "update microcat_login set startTime=? where id=?";
        ps = connection.prepareStatement(updateStatement);
        ps.setTimestamp(1, t);
        ps.setInt(2, microcatId);
        ps.executeUpdate();
    }

    public int findMicrocatsCount(int nexusId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findMicrocatsCount...nexusId.."+nexusId);
        String selectStatement = "SELECT count(*) FROM microcat_login where nexus_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, nexusId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List findMicrocats(int nexusId, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("findMicrocats...nexusId.."+nexusId);
        String selectStatement = "SELECT id,account_number,username,password,status FROM microcat_login where nexus_id=? order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, nexusId);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("account_number"));
            cell.add(rs.getString("username"));
            cell.add(rs.getString("password"));
            cell.add(rs.getString("status"));
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public void updateMicrocatAccount(int accountId, String microcatAccountNumber, String microcatUsername, String microcatPassword, int status) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateMicrocatAccount...accountId.."+accountId+"..microcatAccountNumber.."+microcatAccountNumber+"..microcatUsername.."+"..status.."+status);
        String updateStatement = "update microcat_login set account_number=?,username=?,password=?,status=? where id=?";
        ps = connection.prepareStatement(updateStatement);
        ps.setString(1, microcatAccountNumber);
        ps.setString(2, microcatUsername);
        ps.setString(3, microcatPassword);
        ps.setInt(4, status);
        ps.setInt(5, accountId);
        ps.executeUpdate();
    }

    public void deleteMicrocat(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("deleteMicrocat...id.."+id);
        String updateStatement = "delete from microcat_login where id = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    
    public Microcat findScaniaInfo(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findScaniaInfo...id.."+id);
        String selectStatement = "SELECT id,account_number,username,password,email FROM scania_login where nexus_id=? and status=1";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            return new Microcat(rs.getInt("id"), rs.getString("account_number"), rs.getString("username"), rs.getString("password"), rs.getString("email"));
        } else {
            return null;
        }
    }
    
    public Microcat findScaniaInfoByTime(int id, Timestamp t) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findScaniaInfoByTime...id.."+id);
        String selectStatement = "SELECT id,account_number,username,password,email FROM scania_login where nexus_id=? and status=0 and startTime<=?;";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setTimestamp(2, t);
        rs = ps.executeQuery();
        if (rs.next()) {
            return new Microcat(rs.getInt("id"), rs.getString("account_number"), rs.getString("username"), rs.getString("password"), rs.getString("email"));
        } else {
            return null;
        }
    }

    public void updateScaniaTime(int scaniaId, Timestamp t) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateScaniaTime...scaniaId.."+scaniaId);
        String updateStatement = "update scania_login set startTime=? where id=?";
        ps = connection.prepareStatement(updateStatement);
        ps.setTimestamp(1, t);
        ps.setInt(2, scaniaId);
        ps.executeUpdate();
    }
    
    public void updateScaniaStatus(int scaniaId, int status) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateScaniaStatus...scaniaId.."+scaniaId+"..status.."+status);
        String updateStatement = "update scania_login set status=? where id=?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, status);
        ps.setInt(2, scaniaId);
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
