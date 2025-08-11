/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Terry
 */
public class ViewTemplateOrderItemsAction extends ActionSupport {

    private int templateId;

    public String execute() {
        return SUCCESS;
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
}
