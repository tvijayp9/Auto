/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.services.ProductManagementService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Vijay Thumma
 */
public class DeleteProductItemAction extends ActionSupport {
    Logger log = Logger.getLogger(DeleteProductItemAction.class);
    private ProductManagementService productManagementService;  
    private String id;

    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        String productTableName = (String) ac.getSession().get("productTableName");
        log.info("DeleteProductItemAction product table name="+productTableName+"..id="+id);
        String[] token = id.split("_");
        productManagementService.deletePart(token[0],token[1],token[2], productTableName);
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
