/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.services.ProductManagementService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Terry
 */
public class DeleteCatalogueShoppingCartItemAction extends ActionSupport {
    Logger log = Logger.getLogger(DeleteCatalogueShoppingCartItemAction.class);
    private ProductManagementService productManagementService;
    private String id;

    public String execute() {
        log.info("inside DeleteCatalogueShoppingCartItemAction");
        ActionContext ac = ActionContext.getContext();
        List<ShoppingCartItem> sci = (List<ShoppingCartItem>) ac.getSession().get("shoppingcart");
        productManagementService.deleteShoppingCartItem(id, sci);
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
     * @param catalogueService the catalogueService to set
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
}
