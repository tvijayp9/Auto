/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.services.ProductManagementService;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public class SearchCategoriesForAmcapAction extends ActionSupport {
    private List<String> categoryNames;
    private String sitename;
    private ProductManagementService productManagementService;
    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        String productTableName=(String) ac.getSession().get("PRODUCT_TABLE_NAME");
        setCategoryNames(productManagementService.findSupplierProductsCategoryNames(getSitename(), productTableName));
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
     * @return the categoryNames
     */
    public List<String> getCategoryNames() {
        return categoryNames;
    }

    /**
     * @param categoryNames the categoryNames to set
     */
    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    /**
     * @return the sitename
     */
    public String getSitename() {
        return sitename;
    }

    /**
     * @param sitename the sitename to set
     */
    public void setSitename(String sitename) {
        this.sitename = sitename;
    }
    
    
}
