/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.nexus.services.CatalogueService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;

/**
 *
 * @author Terry
 */
public class AddFavouriteOrderAction extends ActionSupport {

    private CatalogueService catalogueService;
    private String orderNumber;
    private String name;
    private int saveAs;

    public String execute() throws SQLException{
        ActionContext ac = ActionContext.getContext();
        String supplierId = (String) ac.getSession().get(Constant.SUPID);
        String id = (String) ac.getSession().get(Constant.ID);
        if(saveAs==1){
        catalogueService.addFavouriteOrder(orderNumber, name,new Integer(id).intValue(), new Integer(supplierId).intValue());
        return "favouriteOrder";
        }
        else{
            catalogueService.addQuoteFromShoppingcart(orderNumber, name, new Integer(id).intValue(), new Integer(supplierId).intValue());
        return "quote";
        }
    }

    /**
     * @return the catalogueService
     */
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
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the favouriteName
     */
    public String getName() {
        return name;
    }

    /**
     * @param favouriteName the favouriteName to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the saveAs
     */
    public int getSaveAs() {
        return saveAs;
    }

    /**
     * @param saveAs the saveAs to set
     */
    public void setSaveAs(int saveAs) {
        this.saveAs = saveAs;
    }
}
