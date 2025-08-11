/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.services.CatalogueService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Terry
 */
public class EditShoppingCartAction extends ActionSupport {
    Logger log = Logger.getLogger(EditShoppingCartAction.class);
    private CatalogueService catalogueService;
    private String id;
    private int quantity;

    public String execute() {
        log.info("inside EditShoppingCartAction");
        ActionContext ac = ActionContext.getContext();
        List<ShoppingCartItem> sci = (List<ShoppingCartItem>) ac.getSession().get("shoppingcart");
        catalogueService.updateQuantity(id, quantity, sci);
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
