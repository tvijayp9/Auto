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
public class MicrocatOrderItems {

    private String productCode;
    private String description;
    private BigDecimal unitPrice;
    private int quantity;

    public MicrocatOrderItems() {
    }

    public MicrocatOrderItems(String productCode, String description, BigDecimal unitPrice,int quantity) {
        this.productCode = productCode;
        this.description = description;
        this.unitPrice=unitPrice;
        this.quantity = quantity;
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
}
