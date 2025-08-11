/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.services.CatalogueService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Terry
 */
public class AddTemplateOrderItemsToShoppingcartAction extends ActionSupport {

    private CatalogueService catalogueService;
    private String[] templateOrderItemsId;
    String product_table_name=null;
    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        String id = (String) session.get(Constant.ID);
        String supplierId = (String) session.get(Constant.SUPID);
        product_table_name=(String)ac.getSession().get("product_table_name");
        List<ShoppingCartItem> sci;
        if (session.get("shoppingcart") == null) {
            sci = new ArrayList();
            session.put("shoppingcart", sci);
        } else {
            sci = (List<ShoppingCartItem>) session.get("shoppingcart");
        }
        catalogueService.addTemplateOrderItemsToShoppingcart(new Integer(id).intValue(), new Integer(supplierId).intValue(),templateOrderItemsId, sci,product_table_name);
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
     * @return the productId
     */
    @JSON(serialize = false)
    public String[] getTemplateOrderItemsId() {
        return templateOrderItemsId;
    }

    /**
     * @param productId the productId to set
     */
    public void setTemplateOrderItemsId(String[] templateOrderItemsId) {
        this.templateOrderItemsId = templateOrderItemsId;
    }
}
