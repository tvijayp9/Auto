/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.domain.Role;
import com.nexus.domain.User;
import com.nexus.services.AdministrationService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Terry
 */
public class EditUserAction extends ActionSupport {

    private AdministrationService administrationService;
    private int id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private int role;
    private List<Role> roles;

    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        String nexus_id = (String) ac.getSession().get(Constant.ID);
        roles = administrationService.getRolesById(new Integer(nexus_id).intValue());
        User user = administrationService.getUserById(new Integer(id).intValue());
        userId = user.getUserId();
        password = user.getPassword();
        name = user.getName();
        email = user.getEmail();
        role = user.getType();
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
    public int getRole() {
        return role;
    }

    /**
     * @param roles the roles to set
     */
    public void setRole(int role) {
        this.role=role;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
