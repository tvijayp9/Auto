/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.domain.ShoppingCartItem;
import com.nexus.domain.SubTotal;
import com.nexus.services.CatalogueService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Terry
 */
public class QuoteCheckOutAction extends ActionSupport {

     Logger log=Logger.getLogger(QuoteCheckOutAction.class);
    private CatalogueService catalogueService;
    private Date deliveryDate;
    private String comment;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;
    private BigDecimal totalCost;
    private String itemsId;
    private String templateId;

    public String execute() throws SQLException{
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        List<ShoppingCartItem> sci = new ArrayList();
        log.info("itemsId.."+itemsId);
        catalogueService.addQuoteShoppingCartItem(itemsId, sci);
        session.put("quoteshoppingcart", sci);
        SubTotal subtotal = catalogueService.getSubtotal(sci);
        totalPrice = subtotal.getTotalPrice();
        totalTax = subtotal.getTotalTax();
        totalCost = subtotal.getTotalCost();
        Object o = session.get("quotedeliveryDate");
        Object oo = session.get("quotecomment");
        if (o != null) {
            deliveryDate = (Date) o;
        }
        if (oo != null) {
            comment = (String) oo;
        }
        return SUCCESS;
    }

    /**
     * @return the deliveryDate
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param deliveryDate the deliveryDate to set
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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
     * @return the itemsId
     */
    public String getItemsId() {
        return itemsId;
    }

    /**
     * @param itemsId the itemsId to set
     */
    public void setItemsId(String itemsId) {
        this.itemsId = itemsId;
    }

    /**
     * @return the templateId
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId the templateId to set
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
