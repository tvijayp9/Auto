/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.domain.TemplateOrderItem;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 *
 * @author Terry
 */
public class CheckTemplateAction extends ActionSupport {

    private boolean check = false;

    public String execute() {
        ActionContext ac = ActionContext.getContext();
        List<TemplateOrderItem> toi = (List<TemplateOrderItem>) ac.getSession().get("newTemplate");
        if (toi.size() > 0) {
            check = true;
        }
        return SUCCESS;
    }

    /**
     * @return the check
     */
    public boolean isCheck() {
        return check;
    }

    /**
     * @param check the check to set
     */
    public void setCheck(boolean check) {
        this.check = check;
    }
}
