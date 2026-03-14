/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Terry
 */
public class ShoppingCartItem implements Serializable{
    private static final long serialVersionUID = 1L;
    private int lineNo;
    private String productCode;
    private String description;
    private BigDecimal unitPrice;
    private BigDecimal price;
    private BigDecimal tax;
    private int quantity;
    private BigDecimal cost;
    private int soh;
    private int leadtime;
    private int quoteItemId;
    private String uom;
    private String deliveryDate;
    private String linelevelComment;
    private String siteName;
    private String categoryName;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(String productCode, String description, BigDecimal unitPrice,BigDecimal price, BigDecimal tax,int quantity, BigDecimal cost, int soh) {
        this.productCode = productCode;
        this.description = description;
        this.unitPrice=unitPrice;
        this.price = price;
        this.tax=tax;
        this.quantity = quantity;
        this.cost = cost;
        this.soh = soh;
    }
    
    public ShoppingCartItem(String siteName, String categoryName, String productCode, String description, BigDecimal unitPrice,BigDecimal price, BigDecimal tax,int quantity, BigDecimal cost, int soh) {
        this.siteName = siteName;
        this.categoryName = categoryName;
        this.productCode = productCode;
        this.description = description;
        this.unitPrice=unitPrice;
        this.price = price;
        this.tax=tax;
        this.quantity = quantity;
        this.cost = cost;
        this.soh = soh;
    }

    public ShoppingCartItem(int lineNo,String productCode, String description, BigDecimal unitPrice,int quantity,String uom) {
        this.lineNo=lineNo;
        this.productCode = productCode;
        this.description = description;
        this.unitPrice=unitPrice;
        this.quantity = quantity;
        this.uom = uom;
    }
    
    public ShoppingCartItem(int lineNo,String productCode, String description, BigDecimal unitPrice,int quantity,String uom,String deliveryDate,String linelevelComment) 
    {
        this.lineNo=lineNo;
        this.productCode = productCode;
        this.description = description;
        this.unitPrice=unitPrice;
        this.quantity = quantity;
        this.uom = uom;
        this.deliveryDate=deliveryDate;
        this.linelevelComment=linelevelComment;
    }

        public ShoppingCartItem(String productCode, String description, BigDecimal unitPrice,BigDecimal price, BigDecimal tax,int quantity, BigDecimal cost, int leadtime,int quoteItemId) {
        this.productCode = productCode;
        this.description = description;
        this.unitPrice=unitPrice;
        this.price = price;
        this.tax=tax;
        this.quantity = quantity;
        this.cost = cost;
        this.leadtime = leadtime;
        this.quoteItemId=quoteItemId;
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * @return the soh
     */
    public int getSoh() {
        return soh;
    }

    /**
     * @param soh the soh to set
     */
    public void setSoh(int soh) {
        this.soh = soh;
    }

    /**
     * @return the unitPrice
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the tax
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * @return the cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * @return the quoteItemId
     */
    public int getQuoteItemId() {
        return quoteItemId;
    }

    /**
     * @param quoteItemId the quoteItemId to set
     */
    public void setQuoteItemId(int quoteItemId) {
        this.quoteItemId = quoteItemId;
    }

    /**
     * @return the uom
     */
    public String getUom() {
        return uom;
    }

    /**
     * @param uom the uom to set
     */
    public void setUom(String uom) {
        this.uom = uom;
    }

    /**
     * @return the lineNo
     */
    public int getLineNo() {
        return lineNo;
    }

    /**
     * @param lineNo the lineNo to set
     */
    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
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
     * @return the linelevelComment
     */
    public String getLinelevelComment() {
        return linelevelComment;
    }

    /**
     * @param linelevelComment the linelevelComment to set
     */
    public void setLinelevelComment(String linelevelComment) {
        this.linelevelComment = linelevelComment;
    }

    /**
     * @return the siteName
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * @param siteName the siteName to set
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the leadtime
     */
    public int getLeadtime() {
        return leadtime;
    }

    /**
     * @param leadtime the leadtime to set
     */
    public void setLeadtime(int leadtime) {
        this.leadtime = leadtime;
    }
    
    
}
