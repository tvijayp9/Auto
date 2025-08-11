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
public class DeleteRoleAction extends ActionSupport {

    private AdministrationService administrationService;
    private String id;

    public String execute() throws SQLException {
        administrationService.deleteRoleById(new Integer(id).intValue());
        return SUCCESS;
    }

    /**
     * @return the administrationService
     */
    @JSON(serialize = false)
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
     * @return the id
     */
    @JSON(serialize = false)
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
