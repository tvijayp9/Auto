/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.services.CatalogueService;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;

/**
 *
 * @author Terry
 */
public class DeleteFavouriteOrderAction extends ActionSupport {

    private CatalogueService catalogueService;
    private String id;

    public String execute() throws SQLException {
        catalogueService.deleteFavouriteOrder(id);
        return SUCCESS;
    }

    /**
     * @return the catlogueService
     */
    @JSON(serialize = false)
    public CatalogueService getCatalogueService() {
        return catalogueService;
    }

    /**
     * @param catlogueService the catlogueService to set
     */
    public void setCatalogueService(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
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
