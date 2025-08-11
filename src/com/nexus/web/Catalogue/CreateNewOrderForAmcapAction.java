/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.nexus.domain.ShoppingCartItem;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateNewOrderForAmcapAction extends ActionSupport {
   public String execute() throws SQLException {
      ActionContext ac = ActionContext.getContext();
      Map session = ac.getSession();
      if (session.get("shoppingcart") == null) {
         List<ShoppingCartItem> sci = new ArrayList();
         session.put("shoppingcart", sci);
      }

      return "success";
   }
}
