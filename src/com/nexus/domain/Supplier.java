/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.domain;

/**
 *
 * @author Terry
 */
public class Supplier {

    private int supplierId;
    private String logoName;
    private int type;

    public Supplier() {
    }

    public Supplier(int supplierId, String logoName) {
        this.supplierId = supplierId;
        this.logoName = logoName;
    }

    /**
     * @return the supplierId
     */
    public int getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @return the logoName
     */
    public String getLogoName() {
        return logoName;
    }

    /**
     * @param logoName the logoName to set
     */
    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
}
