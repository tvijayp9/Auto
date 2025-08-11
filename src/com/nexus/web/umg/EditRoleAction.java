/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.services.AdministrationService;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;

/**
 *
 * @author Terry
 */
public class EditRoleAction extends ActionSupport {

    private AdministrationService administrationService;
    private int roleId;
    private String roleName;
    private int existingCount;

    public String execute() throws SQLException {
        roleName = administrationService.getRoleNameByRoleId(roleId);
        existingCount = administrationService.getTabsCountByRoleId(roleId);
        return SUCCESS;
    }

    /**
     * @return the administrationService
     */
    public AdministrationService getAdministrationService() {
        return administrationService;
    }

    /**
     * @param administrationService the administrationService to set
     */
    public void setAdministrationService(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    /**
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the existingCount
     */
    public int getExistingCount() {
        return existingCount;
    }

    /**
     * @param existingCount the existingCount to set
     */
    public void setExistingCount(int existingCount) {
        this.existingCount = existingCount;
    }
}
