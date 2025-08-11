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
public class SubTotal {

    private BigDecimal totalPrice;
    private BigDecimal totalTax;
    private BigDecimal totalCost;

    public SubTotal(BigDecimal totalPrice, BigDecimal totalTax, BigDecimal totalCost) {
        this.totalPrice = totalPrice;
        this.totalTax = totalTax;
        this.totalCost = totalCost;
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
