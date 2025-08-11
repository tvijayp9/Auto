/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.nexus.domain.ShoppingCartItem;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Terry
 */
public class CheckShoppingCartAction extends ActionSupport {
    Logger log=Logger.getLogger(CheckShoppingCartAction.class);
    private boolean check = false;
   
     public String execute() {
        ActionContext ac = ActionContext.getContext();
        List<ShoppingCartItem> sci = (List<ShoppingCartItem>) ac.getSession().get("shoppingcart");
        if (sci.size() > 0) {
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
