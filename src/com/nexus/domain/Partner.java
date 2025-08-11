/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.domain;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Partner implements Serializable{
    
    private String partnerId;
    private String partnerName;

    public Partner(){
        
    }
    public Partner(String partnerId, String partnerName){
        this.partnerId = partnerId;
        this.partnerName = partnerName;
    }
    /**
     * @return the partnerId
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * @param partnerId the partnerId to set
     */
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    /**
     * @return the partnerName
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * @param partnerName the partnerName to set
     */
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

}
