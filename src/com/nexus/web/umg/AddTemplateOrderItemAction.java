/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.TemplateOrderItem;
import com.nexus.services.CatalogueService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Terry
 */
public class AddTemplateOrderItemAction extends ActionSupport {

    private CatalogueService catalogueService;
    private String[] productCode;
    String product_table_name=null;
    public String execute() throws SQLException{
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        String id = (String) session.get(Constant.ID);
        String supplierId = (String) session.get(Constant.SUPID);
        product_table_name=(String)ac.getSession().get("product_table_name");
        List<TemplateOrderItem> toi = (List<TemplateOrderItem>) session.get("newTemplate");
        catalogueService.addTemplateOrderItem(new Integer(id).intValue(), new Integer(supplierId).intValue(),productCode, toi,product_table_name);
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
