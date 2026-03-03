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
public class TemplateOrderItem implements Serializable{
    private static final long serialVersionUID = 1L;
    private int templateId;
    private String productCode;
    private String description;
    private BigDecimal unitPrice;
    private int soh;
    private int qty;
    private int leadTime;
    private String units;
    private String tax;
    private String status;
     private BigDecimal price;
    private BigDecimal totaltax;
    private BigDecimal cost;
    private String siteName;
    private String categoryName;
    public TemplateOrderItem(){}
    
    public TemplateOrderItem(String productCode, String description, BigDecimal unitPrice, int soh) {
        this.productCode = productCode;
        this.description = description;
        this.unitPrice = unitPrice;
        this.soh = soh;
    }

    public TemplateOrderItem(String productCode, String description, BigDecimal unitPrice, int soh, int qty) {
        this.productCode = productCode;
        this.description = description;
        this.unitPrice = unitPrice;
        this.soh = soh;
        this.qty = qty;
    }

    public TemplateOrderItem(String productCode, String description, BigDecimal unitPrice, int soh, int qty, String status) {
        this.productCode = productCode;
        this.description = description;
        this.unitPrice = unitPrice;
        this.soh = soh;
        this.qty = qty;
        this.status = status;
    }

    public TemplateOrderItem(int qty, String productCode, String description, BigDecimal unitPrice) {
        this.productCode = productCode;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public TemplateOrderItem(String productCode, String description, BigDecimal unitPrice,BigDecimal price, BigDecimal totaltax,int qty, BigDecimal cost, int soh) {
        this.productCode = productCode;
        this.description = description;
        this.unitPrice=unitPrice;
        this.price = price;
        this.totaltax=totaltax;
        this.qty = qty;
        this.cost = cost;
        this.soh = soh;
    }
    
    public TemplateOrderItem(String siteName, String categoryName, String productCode, String description, BigDecimal unitPrice,BigDecimal price, BigDecimal totaltax,int qty, BigDecimal cost, int leadTime) {
        this.siteName = siteName;
        this.categoryName = categoryName;
        this.productCode = productCode;
        this.description = description;
        this.unitPrice=unitPrice;
        this.price = price;
        this.totaltax=totaltax;
        this.qty = qty;
        this.cost = cost;
        this.leadTime = leadTime;
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
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * @return the units
     */
    public String getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(String units) {
        this.units = units;
    }

    /**
     * @return the tax
     */
    public String getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(String tax) {
        this.tax = tax;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return the totaltax
     */
    public BigDecimal getTotaltax() {
        return totaltax;
    }

    /**
     * @param totaltax the totaltax to set
     */
    public void setTotaltax(BigDecimal totaltax) {
        this.totaltax = totaltax;
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
     * @return the templateId
     */
    public int getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId the templateId to set
     */
    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    /**
     * @return the leadTime
     */
    public int getLeadTime() {
        return leadTime;
    }

    /**
     * @param leadTime the leadTime to set
     */
    public void setLeadTime(int leadTime) {
        this.leadTime = leadTime;
    }

}
