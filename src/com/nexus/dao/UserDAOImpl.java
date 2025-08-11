/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import com.nexus.domain.User;
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
public class UserDAOImpl implements UserDAO {

     Logger log=Logger.getLogger(UserDAOImpl.class);
    private SessionFactory sessionFactory;

    public void insertUser(int memberId, String username, String password, String contact, String email) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertUser...memberId."+memberId+"..username.."+username+"..contact.."+contact+"..email.."+email);
        String insertStatement = "insert into user_login(Nexus_id,loginid,password,name,type,email) VALUES (?,?,?,?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, memberId);
        ps.setString(2, username);
        ps.setString(3, password);
        ps.setString(4, contact);
        ps.setString(5, "admin");
        ps.setString(6, email);
        ps.executeUpdate();
    }

    public int findRoleAssignedToUserCount(int roleId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findRoleAssignedToUserCount...roleId."+roleId);
        String selectStatement = "select count(*) from user_login where type=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, roleId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public int findAllUsersCountById(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findAllUsersCountById...id."+id);
//        commented to get the users based on userType > 0
//        String selectStatement = "SELECT count(*) FROM user_login ul inner join xy_roles r on ul.type=r.id where ul.nexus_id=? and ul.type!=0";
        String selectStatement = "SELECT count(*) FROM user_login ul inner join xy_roles r on ul.type=r.id where ul.nexus_id=? and ul.type > 0";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List<JQGridRow> findAllUsersById(int id, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("findAllUsersById...id."+id);
//        commented to get the users based on userType > 0
//        String selectStatement = "SELECT ul.id as userId,ul.loginid, r.name as roleName " +
//                "FROM user_login ul inner join xy_roles r on ul.type=r.id where ul.nexus_id=? and ul.type!=0 " +
//                "order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        String selectStatement = "SELECT ul.id as userId,ul.loginid, r.name as roleName " +
                "FROM user_login ul inner join xy_roles r on ul.type=r.id where ul.nexus_id=? and ul.type > 0 " +
                "order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("userId"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("loginid"));
            cell.add(rs.getString("roleName"));
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public int checkUserId(String userId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("checkUserId...userId."+userId);
        String selectStatement = "SELECT count(*) FROM user_login where loginid=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, userId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public void insertUserWithRole(int id, String userId, String password, String name, String email, int type) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertUserWithRole...id."+id+"..userId.."+userId+"..name.."+name+"..email..."+email+"..type.."+type);
        String insertStatement = "insert into user_login(Nexus_id,loginid,password,name,type,email) VALUES (?,?,?,?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, id);
        ps.setString(2, userId);
        ps.setString(3, password);
        ps.setString(4, name);
        ps.setInt(5, type);
        ps.setString(6, email);
        ps.executeUpdate();
    }

    public void deleteUserById(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("deleteUserById...id."+id);
        String updateStatement = "delete from user_login where id = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public User findUserById(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findUserById...id."+id);
        String selectStatement = "SELECT loginid,password,name,type,email from user_login where id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        rs.next();
        return new User(rs.getString("loginid"), rs.getString("password"), rs.getString("name"), rs.getInt("type"), rs.getString("email"));
    }

    public void updateUser(int id, String password, String name, String email, int type) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateUser...id."+id);
        String updateStatement = "UPDATE user_login SET password=?,name=?,type=?,email=? where id = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setString(1, password);
        ps.setString(2, name);
        ps.setInt(3, type);
        ps.setString(4, email);
        ps.setInt(5, id);
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
