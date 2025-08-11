/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.domain.TemplateOrderItem;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Terry
 */
public class CreateNewTemplateAction extends ActionSupport {

    public String execute() {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        if (session.get("newTemplate") == null) {
            List<TemplateOrderItem> toi = new ArrayList();
            session.put("newTemplate", toi);
        }
        return SUCCESS;
    }
}
