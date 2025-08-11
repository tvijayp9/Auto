/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.nexus.web.*;
import com.nexus.domain.Order;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.domain.SubTotal;
import com.nexus.services.CatalogueService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Terry
 */
public class CreateOrderFromShoppingCartAction extends ActionSupport {

    Logger log=Logger.getLogger(CreateOrderFromShoppingCartAction.class);
    private CatalogueService catalogueService;
    private String comment;
    private Date deliveryDate;
    private String orderNumber;
    private String autoOrderNumber;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;
    private BigDecimal totalCost;

    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        String supId = (String) ac.getSession().get(Constant.SUPID);
        String id = (String) ac.getSession().get(Constant.ID);
        log.info("id.."+id+"..supId.."+supId+"..deliveryDate..."+deliveryDate+"..comment.."+comment);
        Map session = ac.getSession();
        Order order = null;
        Object o = session.get("order");
        if (o == null) {
            order = catalogueService.createOrder(id, supId, deliveryDate, comment);
            session.put("order", order);
        } else {
            order = (Order) o;
        }
        orderNumber = order.getOrderNo();
        autoOrderNumber = order.getOrderNo();
        log.info("..orderNumber.."+orderNumber+"..autoOrderNumber.."+autoOrderNumber);
        ac.getSession().put("orderid", order.getId());
        session.put("deliveryDate", deliveryDate);
        if ((comment != null) && (!comment.trim().equals(""))) {
            session.put("comment", comment);
        }
        List<ShoppingCartItem> sci = (List<ShoppingCartItem>) ac.getSession().get("shoppingcart");
        SubTotal subtotal = catalogueService.getSubtotal(sci);
        totalPrice = subtotal.getTotalPrice();
        totalTax = subtotal.getTotalTax();
        totalCost = subtotal.getTotalCost();
        return SUCCESS;
    }

    /**
     * @return the catalogueService
     */
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
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the autoOrderNumber
     */
    public String getAutoOrderNumber() {
        return autoOrderNumber;
    }

    /**
     * @param autoOrderNumber the autoOrderNumber to set
     */
    public void setAutoOrderNumber(String autoOrderNumber) {
        this.autoOrderNumber = autoOrderNumber;
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
}
