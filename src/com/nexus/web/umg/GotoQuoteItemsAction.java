/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.web.Catalogue.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Terry
 */
public class GotoQuoteItemsAction extends ActionSupport {

    private Date deliveryDate;
    private String comment;
    private String templateId;

    public String execute() {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        if (deliveryDate != null) {
            session.put("quotedeliveryDate", deliveryDate);
        }
        if ((comment != null) && (!comment.trim().equals(""))) {
            session.put("quotecomment", comment);
        }
        return SUCCESS;
    }

    /**
     * @return the deliveryDate
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param deliveryDate the deliveryDate to set
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the templateId
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId the templateId to set
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
