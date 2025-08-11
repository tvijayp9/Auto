/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.common;

/**
 *
 * @author User
 */

public class IndustryName {
        String key;
        String description;
        public IndustryName(String key, String description) {
            this.key = key;
            this.description = description;
        }

        public String getKey() { return this.key; }
        public String getDescription() { return this.description; }

        public boolean equals(Object obj) {
            if (! (obj instanceof IndustryName)) {
                return false;
            }
            else {
                return key.equals(((IndustryName)obj).getKey());
            }
        }

        public int hashCode() {
            return key.hashCode();
        }
    }