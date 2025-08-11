/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.services;


import com.nexus.domain.JQGridRow;
import com.nexus.domain.Role;
import com.nexus.domain.User;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface AdministrationService {

    
    int getRolesCountById(int id) throws SQLException;

    List<JQGridRow> getRolesById(int id, int start, int limit, String sidx, String sord) throws SQLException;

    List<Role> getRolesById(int id) throws SQLException;

    boolean checkRoleAssignedToUser(int roleId) throws SQLException;

    void deleteRoleById(int id) throws SQLException;

    int getTabsCount(int siteid) throws SQLException;

    List<JQGridRow> getTabs(int siteid, int start, int limit, String sidx, String sord) throws SQLException;

    void createRoleWithTab(int id, String roleName, String tabs) throws SQLException;

    String getRoleNameByRoleId(int roleId) throws SQLException;

    int getTabsCountByRoleId(int roleId) throws SQLException;

    List<JQGridRow> getTabsByRoleId(int roleId, int start, int limit, String sidx, String sord) throws SQLException;

    int getMoreTabsCountByRoleId(int roleId,int userType) throws SQLException;

    List<JQGridRow> getMoreTabsByRoleId(int siteid,int roleId, int start, int limit, String sidx, String sord) throws SQLException;

    void editRoleWithTab(String roleName, int roleId, String moreTabs, String existingTabs) throws SQLException;

    int getAllUsersCountById(int id) throws SQLException;

    List<JQGridRow> getAllUsersById(int id, int start, int limit, String sidx, String sord) throws SQLException;

    boolean checkUserId(String userId) throws SQLException;

    void createUserWithRole(int id, String userId, String password, String name, String email, int type) throws SQLException;

    void deleteUserById(int id) throws SQLException;

    User getUserById(int id) throws SQLException;

    void updateUser(int id, String password, String name, String email, int type) throws SQLException;

    public int getCustomerAdminRoleId(int nexusId) throws SQLException;
}
