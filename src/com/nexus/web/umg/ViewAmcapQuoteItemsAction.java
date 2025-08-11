/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.domain.TemplateOrderItem;
import com.nexus.services.ProductManagementService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vijay Thumma
 */
public class ViewAmcapQuoteItemsAction extends ActionSupport {

    private int templateId;
    private String quoteName;
    private ProductManagementService productManagementService;
    List<TemplateOrderItem> quoteItems;
    public String execute() throws SQLException {
        quoteName = productManagementService.findQuoteNameByQuoteId(templateId);
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        session.put("quoteName", quoteName);
        quoteItems = productManagementService.findQuoteItemsByQuoteId(templateId);
        session.put("quoteItemList", quoteItems);
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

    /**
     * @return the productManagementService
     */
    public ProductManagementService getProductManagementService() {
        return productManagementService;
    }

    /**
     * @param productManagementService the productManagementService to set
     */
    public void setProductManagementService(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

}
