/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.services.ProductManagementService;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Vijay Thumma
 */
public class EditProductDetailsAction extends ActionSupport {
    Logger log = Logger.getLogger(EditProductDetailsAction.class);
    private ProductManagementService productManagementService;
    private String id;
    private String description;
    private String price1;

    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        String productTableName = (String) ac.getSession().get("productTableName");
        log.info("EditProductDetailsAction product table name="+productTableName+"..id="+id+"...description="+description+"..price1="+getPrice1());
        String[] token = id.split("_");
        getProductManagementService().updatePart(token[0],token[1],token[2], description, price1, productTableName);
        return SUCCESS;
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
     * @return the productManagementService
     */
    @JSON(serialize = false)
    public ProductManagementService getProductManagementService() {
        return productManagementService;
    }

    /**
     * @param productManagementService the productManagementService to set
     */
    public void setProductManagementService(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price1
     */
    public String getPrice1() {
        return price1;
    }

    /**
     * @param price1 the price1 to set
     */
    public void setPrice1(String price1) {
        this.price1 = price1;
    }

}
