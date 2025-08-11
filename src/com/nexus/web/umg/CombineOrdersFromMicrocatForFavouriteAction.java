/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.Microcat;
import com.nexus.domain.TemplateOrderItem;
import com.nexus.services.CatalogueService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Terry
 */
public class CombineOrdersFromMicrocatForFavouriteAction extends ActionSupport {

    private boolean done;
    private CatalogueService catalogueService;
    String product_table_name=null;
    private String supnexusId;
    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        Microcat cat = (Microcat) session.get("microcat");
        String id = (String) session.get(Constant.ID);
        String supplierId = (String) session.get(Constant.SUPID);
        product_table_name=(String)ac.getSession().get("product_table_name");
        List<TemplateOrderItem> toi = (List<TemplateOrderItem>) session.get("newTemplate");
        done = catalogueService.combineOrdersFromMicrocatForFavourite(cat.getAccountNumber(), toi, new Integer(id).intValue(), new Integer(supplierId).intValue(),product_table_name,supnexusId);
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
