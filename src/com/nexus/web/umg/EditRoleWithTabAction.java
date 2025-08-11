/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.services.AdministrationService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;

/**
 *
 * @author Terry
 */
public class EditRoleWithTabAction extends ActionSupport {

    private AdministrationService administrationService;
    private String roleName;
    private int roleId;
    private String moreTabs;
    private String existingTabs;

    public String execute() throws SQLException {
        administrationService.editRoleWithTab(roleName, roleId, moreTabs, existingTabs);
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
     * @return the moreTabs
     */
    public String getMoreTabs() {
        return moreTabs;
    }

    /**
     * @param moreTabs the moreTabs to set
     */
    public void setMoreTabs(String moreTabs) {
        this.moreTabs = moreTabs;
    }

    /**
     * @return the existingTabs
     */
    public String getExistingTabs() {
        return existingTabs;
    }

    /**
     * @param existingTabs the existingTabs to set
     */
    public void setExistingTabs(String existingTabs) {
        this.existingTabs = existingTabs;
    }
}
