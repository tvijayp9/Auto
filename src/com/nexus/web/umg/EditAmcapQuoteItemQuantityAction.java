/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.TemplateOrderItem;
import com.nexus.services.ProductManagementService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Terry
 */
public class EditAmcapQuoteItemQuantityAction extends ActionSupport {

    private ProductManagementService productManagementService;
    private String id;
    private int quantity;

    public String execute() throws SQLException{
        ActionContext ac = ActionContext.getContext();
        List<TemplateOrderItem> quoteItems = (List<TemplateOrderItem>) ac.getSession().get("quoteItemList");
        productManagementService.updateQuoteQuantity(id, quantity, quoteItems);
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
