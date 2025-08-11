/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import com.nexus.domain.Role;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface RolesDAO {

    int findRolesCountById(int id) throws SQLException;

    List<JQGridRow> findRolesById(int id, int start, int limit, String sidx, String sord) throws SQLException;

    List<Role> findRolesById(int id) throws SQLException;

    void deleteRoleById(int id) throws SQLException;

    void insertRole(String roleName,int nexusId) throws SQLException;

    int findRoleId(String roleName,int nexusId) throws SQLException;

    void insertRoleTab(int roleId,int tabId) throws SQLException;

    String findRoleNameByRoleId(int roleId) throws SQLException;

    void updateRoleNameByRoleId(String roleName,int roleId) throws SQLException;

    void deleteRoleTab(int roleId,int tabId) throws SQLException;

     public int findCustomerAdminRoleId(int nexusId) throws SQLException;
}
