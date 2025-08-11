/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.services;

import com.nexus.dao.PartnerLinkDAO;
import com.nexus.dao.RolesDAO;
import com.nexus.dao.TabsDAO;
import com.nexus.dao.UserDAO;
import com.nexus.domain.JQGridRow;
import com.nexus.domain.Role;
import com.nexus.domain.User;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Terry
 */
@Transactional
public class AdministrationServiceImpl implements AdministrationService {

    private PartnerLinkDAO partnerLinkDAO;
    private RolesDAO rolesDAO;
    private UserDAO userDAO;
        private TabsDAO tabsDAO;

   

    

    public Date getFormattedDateTime(String dateTime) throws ParseException {
        SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormatter.parse(dateTime);
    }

    public int getRolesCountById(int id) throws SQLException {
        return rolesDAO.findRolesCountById(id);
    }

    public List<JQGridRow> getRolesById(int id, int start, int limit, String sidx, String sord) throws SQLException {
        return rolesDAO.findRolesById(id, start, limit, sidx, sord);
    }

    public List<Role> getRolesById(int id) throws SQLException {
        return rolesDAO.findRolesById(id);
    }

    public boolean checkRoleAssignedToUser(int roleId) throws SQLException {
        int count = userDAO.findRoleAssignedToUserCount(roleId);
        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteRoleById(int id) throws SQLException {
        rolesDAO.deleteRoleById(id);
    }

    public int getTabsCount(int siteid) throws SQLException {
        return tabsDAO.findTabsCount(siteid);
    }

    public List<JQGridRow> getTabs(int siteid, int start, int limit, String sidx, String sord) throws SQLException {
        return tabsDAO.findTabs(siteid,start, limit, sidx, sord);
    }

    public void createRoleWithTab(int id, String roleName, String tabs) throws SQLException {
        rolesDAO.insertRole(roleName, id);
        int roleId = rolesDAO.findRoleId(roleName, id);
        String[] tabId = tabs.split("\\,");
        for (int i = 0; i < tabId.length; i++) {
            rolesDAO.insertRoleTab(roleId, new Integer(tabId[i]).intValue());
        }
    }

    public String getRoleNameByRoleId(int roleId) throws SQLException {
        return rolesDAO.findRoleNameByRoleId(roleId);
    }

    public int getTabsCountByRoleId(int roleId) throws SQLException {
        return tabsDAO.findTabsCountByRoleId(roleId);
    }

    public List<JQGridRow> getTabsByRoleId(int roleId, int start, int limit, String sidx, String sord) throws SQLException {
        return tabsDAO.findTabsByRoleId(roleId, start, limit, sidx, sord);
    }

    public int getMoreTabsCountByRoleId(int roleId,int userType) throws SQLException {
        return tabsDAO.findtMoreTabsCountByRoleId(roleId,userType);
    }

    public List<JQGridRow> getMoreTabsByRoleId(int siteid,int roleId, int start, int limit, String sidx, String sord) throws SQLException {
        return tabsDAO.findMoreTabsByRoleId(siteid,roleId,start, limit, sidx, sord);
    }

    public void editRoleWithTab(String roleName, int roleId, String moreTabs, String existingTabs) throws SQLException {
        rolesDAO.updateRoleNameByRoleId(roleName, roleId);
        if (!moreTabs.equals("")) {
            String[] tabId = moreTabs.split("\\,");
            for (int i = 0; i < tabId.length; i++) {
                rolesDAO.insertRoleTab(roleId, new Integer(tabId[i]).intValue());
            }
        }
        if (!existingTabs.equals("")) {
            String[] tabId = existingTabs.split("\\,");
            for (int i = 0; i < tabId.length; i++) {
                rolesDAO.deleteRoleTab(roleId, new Integer(tabId[i]).intValue());
            }
        }
    }

    public int getCustomerAdminRoleId(int nexusId) throws SQLException{
        return rolesDAO.findCustomerAdminRoleId(nexusId);
    }
    public int getAllUsersCountById(int id) throws SQLException {
        return userDAO.findAllUsersCountById(id);
    }

    public List<JQGridRow> getAllUsersById(int id, int start, int limit, String sidx, String sord) throws SQLException {
        return userDAO.findAllUsersById(id, start, limit, sidx, sord);
    }

    public boolean checkUserId(String userId) throws SQLException {
        int count = userDAO.checkUserId(userId);
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void createUserWithRole(int id, String userId, String password, String name, String email, int type) throws SQLException {
        userDAO.insertUserWithRole(id, userId, password, name, email, type);
    }

    public void deleteUserById(int id) throws SQLException {
        userDAO.deleteUserById(id);
    }

    public User getUserById(int id) throws SQLException {
        return userDAO.findUserById(id);
    }

    public void updateUser(int id, String password, String name, String email, int type) throws SQLException {
        userDAO.updateUser(id, password, name, email, type);
    }

    /**
     * @return the partnerLinkDAO
     */
    public PartnerLinkDAO getPartnerLinkDAO() {
        return partnerLinkDAO;
    }

    /**
     * @param partnerLinkDAO the partnerLinkDAO to set
     */
    public void setPartnerLinkDAO(PartnerLinkDAO partnerLinkDAO) {
        this.partnerLinkDAO = partnerLinkDAO;
    }

    /**
     * @return the rolesDAO
     */
    public RolesDAO getRolesDAO() {
        return rolesDAO;
    }

    /**
     * @param rolesDAO the rolesDAO to set
     */
    public void setRolesDAO(RolesDAO rolesDAO) {
        this.rolesDAO = rolesDAO;
    }

    /**
     * @return the userDAO
     */
    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * @param userDAO the userDAO to set
     */
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

        /**
     * @return the tabsDAO
     */
    public TabsDAO getTabsDAO() {
        return tabsDAO;
    }

    /**
     * @param tabsDAO the tabsDAO to set
     */
    public void setTabsDAO(TabsDAO tabsDAO) {
        this.tabsDAO = tabsDAO;
    }
}
