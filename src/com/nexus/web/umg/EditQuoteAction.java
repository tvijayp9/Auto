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
import org.apache.log4j.Logger;

/**
 *
 * @author Terry
 */
public class EditQuoteAction extends ActionSupport {

    Logger log=Logger.getLogger(EditQuoteAction.class);
    private CatalogueService catalogueService;
    private String id;
    private int quantity;

    public String execute() {
        ActionContext ac = ActionContext.getContext();
        List<TemplateOrderItem> sci = (List<TemplateOrderItem>) ac.getSession().get("newQuote");
        log.info("id="+id+"..quantity="+quantity+"..size="+sci.size());
        catalogueService.updateQuoteItemQuantity(id, quantity, sci);
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

    /**
     * @return the quantity
     */
    @JSON(serialize = false)
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
