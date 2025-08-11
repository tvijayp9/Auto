/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.Microcat;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.services.CatalogueService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Terry
 */
public class OrderFromScaniaAction extends ActionSupport {

    Logger log=Logger.getLogger(OrderFromScaniaAction.class);
    private boolean done;
    private CatalogueService catalogueService;
    String product_table_name=null;
    private String supnexusId;
    public String execute() throws SQLException {
        try{
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        Microcat cat = (Microcat) session.get("scania");
//        String id = (String) session.get(Constant.ID);
//        String supplierId = (String) session.get(Constant.SUPID);
        log.info("supnexusid..."+supnexusId);
         List<ShoppingCartItem> sci = new ArrayList();
            session.put("shoppingcart", sci);
             log.info("2supnexusid.account no.."+cat.getAccountNumber()+".email.."+cat.getEmail());
       // List<ShoppingCartItem> sci = (List<ShoppingCartItem>) session.get("shoppingcart");
        done = catalogueService.orderFromScania(Integer.parseInt(cat.getAccountNumber()),cat.getEmail(),sci);
         log.info("done..."+done);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * @return the done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * @param done the done to set
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * @return the catalogueService
     */
    @JSON(serialize = false)
    public CatalogueService getCatalogueService() {
        return catalogueService;
    }

    /**
     * @param catalogueService the catalogueService to set
     */
    public void setCatalogueService(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    /**
     * @return the supnexusId
     */
    @JSON(serialize = false)
    public String getSupnexusId() {
        return supnexusId;
    }

    /**
     * @param supnexusId the supnexusId to set
     */
    public void setSupnexusId(String supnexusId) {
        this.supnexusId = supnexusId;
    }
}
