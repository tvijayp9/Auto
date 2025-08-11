/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.common;

/**
 *
 * @author User
 */
public class IndustryType {

    String key;
    String description;

    public IndustryType(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public IndustryType() {
    }

    public String getKey() {
        return this.key;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IndustryType)) {
            return false;
        } else {
            return key.equals(((IndustryType) obj).getKey());
        }
    }

    public int hashCode() {
        return key.hashCode();
    }
    }
