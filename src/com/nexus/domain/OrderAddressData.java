/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.domain;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class OrderAddressData implements Serializable {

    private Integer orderId;
    private String shipToAddressId;
    private String shipToName;
    private String shipToDelivery;
    private String shipToStreet;
    private String shipToCity;
    private String shipToState;
    private Integer shipToPOCode;
    private String shipToCountry;
    private String shipToEmail;
    private String billToName;
    private String billToDelivery;
    private String billToStreet;
    private String billToCity;
    private String billToState;
    private Integer billToPOCode;
    private String billToCountry;
    private String billingName;
    private String headerNote;
    private String shippingNote;

    public OrderAddressData() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public OrderAddressData(Integer orderId, String shipToAddressId, String shipToName, String shipToDelivery, String shipToStreet, String shipToCity, String shipToState, Integer shipToPOCode, String shipToCountry, String shipToEmail, String billToName, String billToDelivery, String billToStreet, String billToCity, String billToState, Integer billToPOCode, String billToCountry, String billingName, String headerNote,String shippingNote) {
        this.orderId = orderId;
        this.shipToAddressId = shipToAddressId;
        this.shipToName = shipToName;
        this.shipToDelivery = shipToDelivery;
        this.shipToStreet = shipToStreet;
        this.shipToCity = shipToCity;
        this.shipToState = shipToState;
        this.shipToPOCode = shipToPOCode;
        this.shipToCountry = shipToCountry;
        this.shipToEmail = shipToEmail;
        this.billToName = billToName;
        this.billToDelivery = billToDelivery;
        this.billToStreet = billToStreet;
        this.billToCity = billToCity;
        this.billToState = billToState;
        this.billToPOCode = billToPOCode;
        this.billToCountry = billToCountry;
        this.billingName = billingName;
        this.headerNote=headerNote;
        this.shippingNote=shippingNote;
    }

    /**
     * @return the orderId
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the shipToName
     */
    public String getShipToName() {
        return shipToName;
    }

    /**
     * @param shipToName the shipToName to set
     */
    public void setShipToName(String shipToName) {
        this.shipToName = shipToName;
    }

    /**
     * @return the shipToDelivery
     */
    public String getShipToDelivery() {
        return shipToDelivery;
    }

    /**
     * @param shipToDelivery the shipToDelivery to set
     */
    public void setShipToDelivery(String shipToDelivery) {
        this.shipToDelivery = shipToDelivery;
    }

    /**
     * @return the shipToStreet
     */
    public String getShipToStreet() {
        return shipToStreet;
    }

    /**
     * @param shipToStreet the shipToStreet to set
     */
    public void setShipToStreet(String shipToStreet) {
        this.shipToStreet = shipToStreet;
    }

    /**
     * @return the shipToCity
     */
    public String getShipToCity() {
        return shipToCity;
    }

    /**
     * @param shipToCity the shipToCity to set
     */
    public void setShipToCity(String shipToCity) {
        this.shipToCity = shipToCity;
    }

    /**
     * @return the shipToState
     */
    public String getShipToState() {
        return shipToState;
    }

    /**
     * @param shipToState the shipToState to set
     */
    public void setShipToState(String shipToState) {
        this.shipToState = shipToState;
    }

    /**
     * @return the shipToPOCode
     */
    public Integer getShipToPOCode() {
        return shipToPOCode;
    }

    /**
     * @param shipToPOCode the shipToPOCode to set
     */
    public void setShipToPOCode(Integer shipToPOCode) {
        this.shipToPOCode = shipToPOCode;
    }

    /**
     * @return the shipToCountry
     */
    public String getShipToCountry() {
        return shipToCountry;
    }

    /**
     * @param shipToCountry the shipToCountry to set
     */
    public void setShipToCountry(String shipToCountry) {
        this.shipToCountry = shipToCountry;
    }

    /**
     * @return the shipToEmail
     */
    public String getShipToEmail() {
        return shipToEmail;
    }

    /**
     * @param shipToEmail the shipToEmail to set
     */
    public void setShipToEmail(String shipToEmail) {
        this.shipToEmail = shipToEmail;
    }

    /**
     * @return the billToName
     */
    public String getBillToName() {
        return billToName;
    }

    /**
     * @param billToName the billToName to set
     */
    public void setBillToName(String billToName) {
        this.billToName = billToName;
    }

    /**
     * @return the billToDelivery
     */
    public String getBillToDelivery() {
        return billToDelivery;
    }

    /**
     * @param billToDelivery the billToDelivery to set
     */
    public void setBillToDelivery(String billToDelivery) {
        this.billToDelivery = billToDelivery;
    }

    /**
     * @return the billToStreet
     */
    public String getBillToStreet() {
        return billToStreet;
    }

    /**
     * @param billToStreet the billToStreet to set
     */
    public void setBillToStreet(String billToStreet) {
        this.billToStreet = billToStreet;
    }

    /**
     * @return the billToCity
     */
    public String getBillToCity() {
        return billToCity;
    }

    /**
     * @param billToCity the billToCity to set
     */
    public void setBillToCity(String billToCity) {
        this.billToCity = billToCity;
    }

    /**
     * @return the billToState
     */
    public String getBillToState() {
        return billToState;
    }

    /**
     * @param billToState the billToState to set
     */
    public void setBillToState(String billToState) {
        this.billToState = billToState;
    }

    /**
     * @return the billToPOCode
     */
    public Integer getBillToPOCode() {
        return billToPOCode;
    }

    /**
     * @param billToPOCode the billToPOCode to set
     */
    public void setBillToPOCode(Integer billToPOCode) {
        this.billToPOCode = billToPOCode;
    }

    /**
     * @return the billToCountry
     */
    public String getBillToCountry() {
        return billToCountry;
    }

    /**
     * @param billToCountry the billToCountry to set
     */
    public void setBillToCountry(String billToCountry) {
        this.billToCountry = billToCountry;
    }

    /**
     * @return the shipToAddressId
     */
    public String getShipToAddressId() {
        return shipToAddressId;
    }

    /**
     * @param shipToAddressId the shipToAddressId to set
     */
    public void setShipToAddressId(String shipToAddressId) {
        this.shipToAddressId = shipToAddressId;
    }

    /**
     * @return the billingName
     */
    public String getBillingName() {
        return billingName;
    }

    /**
     * @param billingName the billingName to set
     */
    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    /**
     * @return the headerNote
     */
    public String getHeaderNote() {
        return headerNote;
    }

    /**
     * @param headerNote the headerNote to set
     */
    public void setHeaderNote(String headerNote) {
        this.headerNote = headerNote;
    }

    /**
     * @return the shippingNote
     */
    public String getShippingNote() {
        return shippingNote;
    }

    /**
     * @param shippingNote the shippingNote to set
     */
    public void setShippingNote(String shippingNote) {
        this.shippingNote = shippingNote;
    }

}
