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
public class CreateNewAmcapQuoteAction extends ActionSupport {
    private List<String> sitenames;
    private String sitename;
    private String quoteName="";
    private String comment="";
    private ProductManagementService productManagementService;
    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        if (session.get("newQuote") == null) {
            List<TemplateOrderItem> toi = new ArrayList();
            session.put("newQuote", toi);
        }
        if (session.get("quoteName") == null) {
            session.put("quoteName", "");
        } else {
            setQuoteName(session.get("quoteName").toString());
        }
        
        if (session.get("comment") == null) {
            session.put("comment", "");
        } else {
            setComment(session.get("comment").toString());
        }

        String productTableName = (String) session.get("PRODUCT_TABLE_NAME");
        setSitenames(productManagementService.findSupplierProductsSiteNames(productTableName));

        return SUCCESS;
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

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
}

        