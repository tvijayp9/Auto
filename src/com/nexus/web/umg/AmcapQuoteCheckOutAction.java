/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.domain.ShoppingCartItem;
import com.nexus.domain.SubTotal;
import com.nexus.domain.TemplateOrderItem;
import com.nexus.services.CatalogueService;
import com.nexus.services.ProductManagementService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Terry
 */
public class AmcapQuoteCheckOutAction extends ActionSupport {

     Logger log=Logger.getLogger(AmcapQuoteCheckOutAction.class);
    private ProductManagementService productManagementService;
    private CatalogueService catalogueService;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;
    private BigDecimal totalCost;
    private String itemsId;
    private String templateId;
    private List<ShoppingCartItem> result;
    private int count;
    String id;
    String supplierId;
    String punchout;

    public String execute() throws SQLException{
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        List<ShoppingCartItem> sci = new ArrayList();
        log.info("itemsId.."+itemsId);
        List<TemplateOrderItem> quoteItems = (List<TemplateOrderItem>) ac.getSession().get("quoteItemList");
        productManagementService.addQuoteShoppingCartItem(itemsId, sci, quoteItems);
        session.put("shoppingcart", sci);
        
        id=(String)ac.getSession().get("ID");
        supplierId=(String)ac.getSession().get("supplierid");
        setResult(sci);
        count=sci.size();
        SubTotal subtotal = catalogueService.getSubtotal(sci);
        totalPrice = subtotal.getTotalPrice();
        totalTax = subtotal.getTotalTax();
        totalCost = subtotal.getTotalCost();
        punchout=catalogueService.findRuleValueMappingbySupId("PUNCHOUT_PAGE",Integer.parseInt(supplierId));
        log.info("punch out page="+punchout);
        return punchout;
       
    }

    /**
     * @return the catalogueSerivce
     */
    public ProductManagementService getProductManagementService() {
        return productManagementService;
    }

    /**
     * @param catalogueSerivce the catalogueSerivce to set
     */
    public void setProductManagementService(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    /**
     * @return the totalPrice
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the totalTax
     */
    public BigDecimal getTotalTax() {
        return totalTax;
    }

    /**
     * @param totalTax the totalTax to set
     */
    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    /**
     * @return the totalCost
     */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * @return the itemsId
     */
    public String getItemsId() {
        return itemsId;
    }

    /**
     * @param itemsId the itemsId to set
     */
    public void setItemsId(String itemsId) {
        this.itemsId = itemsId;
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
     * @return the result
     */
    public List<ShoppingCartItem> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(List<ShoppingCartItem> result) {
        this.result = result;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }
}
