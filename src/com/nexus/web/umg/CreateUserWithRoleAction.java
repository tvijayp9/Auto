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
public class CreateUserWithRoleAction extends ActionSupport {

    private AdministrationService administrationService;
    private String userId;
    private String password;
    private String name;
    private String email;
    private int roles;

    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        String id = (String) ac.getSession().get(Constant.ID);
        administrationService.createUserWithRole(new Integer(id).intValue(), userId, password, name, email, roles);
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
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the roles
     */
    public int getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(int roles) {
        this.roles = roles;
    }
}
