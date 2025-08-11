/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.domain;

import java.util.List;

/**
 *
 * @author Terry
 */
public class Quote {

    private String qrn;
    private String qname;
    private String createDate;
    private List<TemplateOrderItem> items;

    /**
     * @return the qrn
     */
    public String getQrn() {
        return qrn;
    }

    /**
     * @param qrn the qrn to set
     */
    public void setQrn(String qrn) {
        this.qrn = qrn;
    }

    /**
     * @return the qname
     */
    public String getQname() {
        return qname;
    }

    /**
     * @param qname the qname to set
     */
    public void setQname(String qname) {
        this.qname = qname;
    }

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the items
     */
    public List<TemplateOrderItem> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<TemplateOrderItem> items) {
        this.items = items;
    }
}
