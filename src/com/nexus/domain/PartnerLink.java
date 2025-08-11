/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.domain;

/**
 *
 * @author Terry
 */
public class PartnerLink {

    private String buyerNumber;
    private String buyerName;
    private int id;
    private String buyerNumberAndBuyerName;

    public PartnerLink() {
    }

    public PartnerLink(String buyerNumber, String buyerName) {
        this.buyerNumber = buyerNumber;
        this.buyerName = buyerName;
    }

    public PartnerLink(int id, String buyerNumberAndBuyerName) {
        this.id = id;
        this.buyerNumberAndBuyerName = buyerNumberAndBuyerName;
    }

    /**
     * @return the buyerNumber
     */
    public String getBuyerNumber() {
        return buyerNumber;
    }

    /**
     * @param buyerNumber the buyerNumber to set
     */
    public void setBuyerNumber(String buyerNumber) {
        this.buyerNumber = buyerNumber;
    }

    /**
     * @return the buyerName
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * @param buyerName the buyerName to set
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
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
     * @return the buyerNumberAndBuyerName
     */
    public String getBuyerNumberAndBuyerName() {
        return buyerNumberAndBuyerName;
    }

    /**
     * @param buyerNumberAndBuyerName the buyerNumberAndBuyerName to set
     */
    public void setBuyerNumberAndBuyerName(String buyerNumberAndBuyerName) {
        this.buyerNumberAndBuyerName = buyerNumberAndBuyerName;
    }
}
