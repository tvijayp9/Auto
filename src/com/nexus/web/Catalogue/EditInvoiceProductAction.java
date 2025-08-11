/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.InvoiceLineItem;
import com.nexus.domain.JQGridRow;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.services.CatalogueService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigDecimal;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Terry
 */
public class EditInvoiceProductAction extends ActionSupport {
    Logger log = Logger.getLogger(EditInvoiceProductAction.class);
    private CatalogueService catalogueService;
    private String id;
    private String product_code;
    private int qty;
    private BigDecimal unitPrice;
    List<InvoiceLineItem> invoiceList=null;

    public String execute() {
        log.info("inside EditInvoiceProductAction  id="+id+"...quantity="+qty+"...product_code=="+product_code+"....unitPrice=="+unitPrice);
        ActionContext ac = ActionContext.getContext();
        List<InvoiceLineItem> invoiceCart = (List<InvoiceLineItem>) ac.getSession().get("invoicecart");
        log.info("size="+invoiceCart.size());
        catalogueService.updateQuantityforInvoice(id, qty,unitPrice, invoiceCart);
         //= (List<InvoiceLineItem>) ac.getSession().get("invoicecart");
//        for(InvoiceLineItem invoiceLineItem:invoiceList){
//          log.info("product code="+invoiceLineItem.getProductCode());  
//          log.info("OrderId="+invoiceLineItem.getOrderId());
//          log.info("Qty="+invoiceLineItem.getQuantity());
//          log.info("UnitPrice="+invoiceLineItem.getUnitprice());
//         // log.info("invoiceNo="+invoiceNo);
//        }
        return SUCCESS;
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
     * @return the id
     */
    @JSON(serialize = false)
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the quantity
     */
    @JSON(serialize = false)
    public int getQty() {
        return qty;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * @return the product_code
     */
    @JSON(serialize = false)
    public String getProduct_code() {
        return product_code;
    }

    /**
     * @param product_code the product_code to set
     */
    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    /**
     * @return the unitPrice
     */
     @JSON(serialize = false)
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
