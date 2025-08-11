/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.nexus.domain.SubTotal;
import com.nexus.domain.TemplateOrderItem;
import com.nexus.services.CatalogueService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Vijay Thumma
 */
public class CheckOutQuoteAction extends ActionSupport {

    private CatalogueService catalogueService;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;
    private BigDecimal totalCost;

      private String templateName;
    private String email;
    private String comment;

    public String execute() {
        ActionContext ac = ActionContext.getContext();
        List<TemplateOrderItem> toi = (List<TemplateOrderItem>) ac.getSession().get("newQuote");
        SubTotal subtotal = catalogueService.getSubtotalforTemplate(toi);
        totalPrice=subtotal.getTotalPrice();
        totalTax=subtotal.getTotalTax();
        totalCost=subtotal.getTotalCost();

         Object o = ac.getSession().get("templateName");
         Object oo = ac.getSession().get("email");
        Object ooo = ac.getSession().get("comment");
        if (o != null) {
            setTemplateName((String) o);
        }
        if (oo != null) {
            setEmail((String) oo);
        }
        if (ooo != null) {
            setComment((String) ooo);
        }
       
        return SUCCESS;
    }

    /**
     * @return the catalogueSerivce
     */
    public CatalogueService getCatalogueService() {
        return catalogueService;
    }

    /**
     * @param catalogueSerivce the catalogueSerivce to set
     */
    public void setCatalogueService(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    /**
     * @return the totalPrice
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the totalTax
     */
    public BigDecimal getTotalTax() {
        return totalTax;
    }

    /**
     * @param totalTax the totalTax to set
     */
    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    /**
     * @return the totalCost
     */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName the templateName to set
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
