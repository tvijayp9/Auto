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
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Vijay
 */
public class AddAmcapQuoteItemAction extends ActionSupport {

    Logger log = Logger.getLogger(AddAmcapQuoteItemAction.class);
    private ProductManagementService productManagementService;
    private String[] productCode;
    String product_table_name = null;
    List<TemplateOrderItem> list;

    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        product_table_name = (String) ac.getSession().get("product_table_name");
        List<TemplateOrderItem> toi = (List<TemplateOrderItem>) session.get("newQuote");
        if (productCode != null) {
            list = productManagementService.addAmcapQuoteItem(productCode, toi, product_table_name);
            session.put("newQuote", list);
            log.info("size in action class=" + toi.size() + "..new list..size.." + list.size());
        }
        return SUCCESS;
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
