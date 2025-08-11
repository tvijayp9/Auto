/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.domain;

import java.math.BigDecimal;

/**
 *
 * @author Terry
 */
public class PrintOrder {

    private String productCode;
    private String description;
    private int quantity;
    private String deliveryDate;
    private String lineComment;
    private BigDecimal price;
    private BigDecimal tax;
    private BigDecimal cost;

    public PrintOrder(String productCode, String description, int quantity, BigDecimal price, BigDecimal tax, BigDecimal cost) {
        this.productCode = productCode;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.tax = tax;
        this.cost = cost;
    }
    
     public PrintOrder(String productCode, String description, int quantity, BigDecimal price, BigDecimal tax, BigDecimal cost,String deliveryDate,String lineComment) {
        this.productCode = productCode;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.tax = tax;
        this.cost = cost;
        this.deliveryDate=deliveryDate;
        this.lineComment=lineComment;
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
     * @return the lineComment
     */
    public String getLineComment() {
        return lineComment;
    }

    /**
     * @param lineComment the lineComment to set
     */
    public void setLineComment(String lineComment) {
        this.lineComment = lineComment;
    }
}
