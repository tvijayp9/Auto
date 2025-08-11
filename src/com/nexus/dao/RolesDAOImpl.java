/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import com.nexus.domain.Role;
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
public class RolesDAOImpl implements RolesDAO {

    Logger log=Logger.getLogger(RolesDAOImpl.class);
    private SessionFactory sessionFactory;

    public int findRolesCountById(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findRolesCountById..id.."+id);
        String selectStatement = "select count(*) from xy_roles where nexus_id=? and id>0";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List<JQGridRow> findRolesById(int id, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("1 findRolesById..id.."+id);
        String selectStatement = "SELECT id,name from xy_roles where nexus_id=? and id>0 order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("name"));
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public List<Role> findRolesById(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Role> roles = new ArrayList();
        log.info("2 findRolesById..id.."+id);
        String selectStatement = "SELECT id,name from xy_roles where nexus_id=? and id>0 order by id";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        while (rs.next()) {
            roles.add(new Role(rs.getInt("id"), rs.getString("name")));
        }
        return roles;
    }

    public void deleteRoleById(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("deleteRoleById..id.."+id);
        String updateStatement = "delete from xy_roles where id = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void insertRole(String roleName, int nexusId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertRole..roleName.."+roleName+"..nexusId.."+nexusId);
        String insertStatement = "insert into xy_roles (name,nexus_id) values(?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setString(1, roleName);
        ps.setInt(2, nexusId);
        ps.executeUpdate();
    }

    public int findRoleId(String roleName, int nexusId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
         log.info("findRoleId..roleName.."+roleName+"..nexusId.."+nexusId);
        String selectStatement = "select max(id) from xy_roles where name=? and nexus_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, roleName);
        ps.setInt(2, nexusId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public void insertRoleTab(int roleId, int tabId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertRoleTab..roleId.."+roleId+"..tabId.."+tabId);
        String insertStatement = "insert into xy_roles_tabs_mapping (role_id,tab_id) values(?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, roleId);
        ps.setInt(2, tabId);
        ps.executeUpdate();
    }

    public String findRoleNameByRoleId(int roleId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findRoleNameByRoleId..roleId.."+roleId);
        String selectStatement = "select name from xy_roles where id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, roleId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getString("name");
    }

    public void updateRoleNameByRoleId(String roleName, int roleId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateRoleNameByRoleId..roleId.."+roleId+"..roleName.."+roleName);
        String updateStatement = "UPDATE xy_roles SET name = ? where id = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setString(1, roleName);
        ps.setInt(2, roleId);
        ps.executeUpdate();
    }

    public void deleteRoleTab(int roleId, int tabId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("deleteRoleTab..roleId.."+roleId+"..tabId.."+tabId);
        String updateStatement = "delete from xy_roles_tabs_mapping where role_id = ? and tab_id=?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, roleId);
        ps.setInt(2, tabId);
        ps.executeUpdate();
    }

     public int findCustomerAdminRoleId(int nexusId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findCustomerAdminRoleId..nexusId.."+nexusId);
        String selectStatement = "SELECT type from user_login where nexus_id=? and type < 0";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, nexusId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
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
