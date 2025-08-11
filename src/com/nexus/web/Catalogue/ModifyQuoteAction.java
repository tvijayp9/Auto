/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

/**
 *
 * @author Terry
 */
public class ModifyQuoteAction extends ActionSupport {

     private String templateName;
    private String email;
    private String comment;

    public String execute() {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        if ((templateName != null) && (!templateName.trim().equals(""))) {
            session.put("templateName", templateName);
        }
        if ((email != null) && (!email.trim().equals(""))) {
            session.put("email", email);
        }
        if ((comment != null) && (!comment.trim().equals(""))) {
            session.put("comment", comment);
        }
        return SUCCESS;
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
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName the templateName to set
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
