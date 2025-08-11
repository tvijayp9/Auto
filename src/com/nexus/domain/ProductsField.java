/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.domain;

/**
 *
 * @author Terry
 */
public class ProductsField {

    private String field;
    private String displayName;

    public ProductsField(String field, String displayName) {
        this.field = field;
        this.displayName = displayName;
    }

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
