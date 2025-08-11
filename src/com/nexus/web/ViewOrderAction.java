/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.nexus.domain.PurchaseOrder;
import com.nexus.domain.SubTotal;
import com.nexus.services.CatalogueService;
import com.nexus.services.TransactionService;
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.apache.log4j.Logger;
import com.nexus.domain.OrderAddressData;


/**
 *
 * @author Vijay Thumma
 */
public class ViewOrderAction extends ActionSupport {

    Logger log=Logger.getLogger(ViewOrderAction.class);
    private List items;
    private String messageId;
    private String company;
    private TransactionService transactionService;
    private String customerCode;
    private String orderNumber;
    private String comment;
    private String deliveryDate;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;
    private BigDecimal totalCost;
    private CatalogueService catalogueService;
    private OrderAddressData orderAddressData;


    public String execute() throws SQLException, ParseException,Exception {
         String result = null;
        PurchaseOrder po = transactionService.getPurchaseOrderFromInboundTransaction(messageId);
         log.info("messageId="+messageId+"...company="+company);
        if (po != null) {
//            if(company.equalsIgnoreCase("AusDrill")){
                setOrderAddressData(transactionService.getCXMLOrderAddress(Integer.parseInt(messageId)));
                result="viewOrder";
//            }else{
//                result="Print";
//            }
            customerCode = po.getCustomerCode();
            orderNumber = po.getOrderNumber();
            comment = po.getComment();
            deliveryDate = po.getDeliveryDate();
            log.info("customerCode...."+customerCode+"..orderNumber..."+orderNumber+"..deliveryDate.."+deliveryDate);
            items = po.getItemList();
            SubTotal subtotal = catalogueService.getSubtotalForPrintOrder(items);
            totalPrice = subtotal.getTotalPrice();
            totalTax = subtotal.getTotalTax();
            totalCost = subtotal.getTotalCost();
            transactionService.updateMessageStatus(messageId, 1);
        }
             return result;
    }
   
    /**
     * @return the items
     */
    public List getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List items) {
        this.items = items;
    }

    /**
     * @return the orderNumber
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * @return the transactionService
     */
    public TransactionService getTransactionService() {
        return transactionService;
    }

    /**
     * @param transactionService the transactionService to set
     */
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * @return the customerCode
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * @param customerCode the customerCode to set
     */
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param deliveryDate the deliveryDate to set
     */
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
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
     * @return the orderAddressData
     */
    public OrderAddressData getOrderAddressData() {
        return orderAddressData;
    }

    /**
     * @param orderAddressData the orderAddressData to set
     */
    public void setOrderAddressData(OrderAddressData orderAddressData) {
        this.orderAddressData = orderAddressData;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }
}
