/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.services.ProductManagementService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vijay Thumma
 */
public class AddCatalogueShoppingCartItemAction extends ActionSupport {

    private ProductManagementService productManagementService;
    private String[] productCode;
    String product_table_name=null;
    public String execute() throws SQLException{
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        String id = (String) session.get(Constant.ID);
        String supplierId = (String) session.get(Constant.SUPID);
        List<ShoppingCartItem> sci = (List<ShoppingCartItem>) session.get("shoppingcart");
        product_table_name=(String)ac.getSession().get("product_table_name");
        productManagementService.addShoppingCartItem(productCode, sci,product_table_name);
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
     * @return the productId
     */
    @JSON(serialize = false)
    public String[] getProductCode() {
        return productCode;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductCode(String[] productCode) {
        this.productCode = productCode;
    }
}
