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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vijay Thumma
 */
public class AddMoreAmcapQuoteItemsAction extends ActionSupport {

    private int templateId;
    private List<String> sitenames;
    private String sitename;
    private String quoteName;
    private ProductManagementService productManagementService;
    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
         if (session.get("newQuote") == null) {
            List<TemplateOrderItem> toi = new ArrayList();
            session.put("newQuote", toi);
         }
         setQuoteName((String) session.get("quoteName"));
         String productTableName = (String) session.get("PRODUCT_TABLE_NAME");
        setSitenames(productManagementService.findSupplierProductsSiteNames(productTableName));
        return SUCCESS;
    }

    /**
     * @return the templateId
     */
    public int getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId the templateId to set
     */
    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    /**
     * @return the sitenames
     */
    public List<String> getSitenames() {
        return sitenames;
    }

    /**
     * @param sitenames the sitenames to set
     */
    public void setSitenames(List<String> sitenames) {
        this.sitenames = sitenames;
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
     * @return the quoteName
     */
    public String getQuoteName() {
        return quoteName;
    }

    /**
     * @param quoteName the quoteName to set
     */
    public void setQuoteName(String quoteName) {
        this.quoteName = quoteName;
    }
    
}
