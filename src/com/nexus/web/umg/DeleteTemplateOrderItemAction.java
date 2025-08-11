/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.TemplateOrderItem;
import com.nexus.services.CatalogueService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 *
 * @author Terry
 */
public class DeleteTemplateOrderItemAction extends ActionSupport {

    private CatalogueService catalogueService;
    private String id;

    public String execute() {
        ActionContext ac = ActionContext.getContext();
        List<TemplateOrderItem> toi = (List<TemplateOrderItem>) ac.getSession().get("newTemplate");
        catalogueService.deleteTemplateOrderItem(id, toi);
        return SUCCESS;
    }

    /**
     * @return the catalogueService
     */
    @JSON(serialize = false)
    public CatalogueService getCatalogueService() {
        return catalogueService;
    }

    /**
     * @param catalogueService the catalogueService to set
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
