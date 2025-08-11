/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.services.CatalogueService;
import com.nexus.services.ProductManagementService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Terry
 */
public class EditCatalogueShoppingCartAction extends ActionSupport {
    Logger log = Logger.getLogger(EditCatalogueShoppingCartAction.class);
    private ProductManagementService productManagementService;
    private String id;
    private int quantity;

    public String execute() {
        log.info("inside EditShoppingCartAction");
        ActionContext ac = ActionContext.getContext();
        List<ShoppingCartItem> sci = (List<ShoppingCartItem>) ac.getSession().get("shoppingcart");
        productManagementService.updateQuantity(id, quantity, sci);
        return SUCCESS;
    }

    /**
     * @return the catalogueService
     */
    @JSON(serialize = false)
    public ProductManagementService getProductManagementService() {
        return productManagementService;
    }

    /**
     * @param ProductManagementService the productManagementService to set
     */
    public void setProductManagementService(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
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
