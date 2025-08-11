/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Vijay Thumma
 */

public class InvoiceLineItem implements Serializable {
    private int id;
    
    private int lineNo;
    
    private int orderId;
    
    private int invoiceLineItemId;
    
    private String productCode;
    
    private String description;
    
     private int orderedQuantity;
    
    private int quantity;
    
    private BigDecimal unitprice;
    
    private String invoiceNo;
    
    private int lineItemId;
    
    private boolean invoiced;
    
    private String lineItemType;
    
    private BigDecimal price;
    private BigDecimal tax;
    private BigDecimal cost;
    
    

    public InvoiceLineItem(String productCode, String description, int quantity,BigDecimal unitprice, BigDecimal price, BigDecimal tax, BigDecimal cost) {
        this.productCode = productCode;
        this.description = description;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.price = price;
        this.tax = tax;
        this.cost = cost;
    }
    
    public InvoiceLineItem(int lineItemId,int orderId,String productCode, String description, int quantity,BigDecimal unitprice, BigDecimal price, BigDecimal tax, BigDecimal cost,String invoiceNo) {
        this.lineItemId = lineItemId;
        this.orderId = orderId;
        this.productCode = productCode;
        this.description = description;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.price = price;
        this.tax = tax;
        this.cost = cost;
        this.invoiceNo=invoiceNo;
    }
    
     public InvoiceLineItem(int lineItemId,int orderId,int invoiceLineItemId,String productCode, String description,int orderedQuantity, int quantity,BigDecimal unitprice, BigDecimal price, BigDecimal tax, BigDecimal cost,String invoiceNo,String lineItemType,int lineNo) {
        this.lineItemId = lineItemId;
        this.orderId = orderId;
        this.invoiceLineItemId=invoiceLineItemId;
        this.productCode = productCode;
        this.description = description;
        this.orderedQuantity=orderedQuantity;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.price = price;
        this.tax = tax;
        this.cost = cost;
        this.invoiceNo=invoiceNo;
        this.lineItemType=lineItemType;
        this.lineNo=lineNo;
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
     * @return the unitprice
     */
    public BigDecimal getUnitprice() {
        return unitprice;
    }

    /**
     * @param unitprice the unitprice to set
     */
    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the invoiceNo
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * @param invoiceNo the invoiceNo to set
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * @return the lineItemId
     */
    public int getLineItemId() {
        return lineItemId;
    }

    /**
     * @param lineItemId the lineItemId to set
     */
    public void setLineItemId(int lineItemId) {
        this.lineItemId = lineItemId;
    }

    /**
     * @return the orderedQuantity
     */
    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    /**
     * @param orderedQuantity the orderedQuantity to set
     */
    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    /**
     * @return the invoiceLineItemId
     */
    public int getInvoiceLineItemId() {
        return invoiceLineItemId;
    }

    /**
     * @param invoiceLineItemId the invoiceLineItemId to set
     */
    public void setInvoiceLineItemId(int invoiceLineItemId) {
        this.invoiceLineItemId = invoiceLineItemId;
    }

    /**
     * @return the invoiced
     */
    public boolean isInvoiced() {
        return invoiced;
    }

    /**
     * @param invoiced the invoiced to set
     */
    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    /**
     * @return the lineItemType
     */
    public String getLineItemType() {
        return lineItemType;
    }

    /**
     * @param lineItemType the lineItemType to set
     */
    public void setLineItemType(String lineItemType) {
        this.lineItemType = lineItemType;
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
}
