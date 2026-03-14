/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.TemplateOrderItem;
import com.nexus.services.ProductManagementService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Vijay Thumma
 */
public class AddAmcapProductItemAction extends ActionSupport {

    Logger log = Logger.getLogger(AddAmcapProductItemAction.class);
    private ProductManagementService productManagementService;
    String product_table_name = null;
    List<TemplateOrderItem> list;
    private int qty = 1;
    private String productcode="";
    private String description="";
    private BigDecimal price;
    private String quoteName = "";
    private String comment = "";
    private int leadtime = 0;
    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        product_table_name = (String) ac.getSession().get("product_table_name");
        List<TemplateOrderItem> toi = (List<TemplateOrderItem>) session.get("newQuote");
        setQuoteName((String)session.get("quoteName"));
        setComment((String)session.get("comment"));
        log.info("size in action qty=" + qty + "..productcode.." + productcode+"...description="+description+"...price="+price+"....leadtime="+getLeadtime());
        if (productcode != "") {
            list = productManagementService.addAmcapProductToQuoteItem(qty, productcode, description, price, leadtime, toi);
            session.put("newQuote", list);
            log.info("size in action class=" + toi.size() + "..new list..size.." + list.size());
        }
        return SUCCESS;
    }

    /**
     * @return the productManagementService
     */
     @JSON(serialize = false)
    public ProductManagementService getProductManagementService() {
        return productManagementService;
    }

    /**
     * @param productManagementService the productManagementService to set
     */
    public void setProductManagementService(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }


    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * @return the productcode
     */
    public String getProductcode() {
        return productcode;
    }

    /**
     * @param productcode the productcode to set
     */
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the quoteName
     */
    public String getQuoteName() {
        return quoteName;
    }

    /**
     * @param quoteName the quoteName to set
     */
    public void setQuoteName(String quoteName) {
        this.quoteName = quoteName;
    }

    /**
     * @return the leadtime
     */
    public int getLeadtime() {
        return leadtime;
    }

    /**
     * @param leadtime the leadtime to set
     */
    public void setLeadtime(int leadtime) {
        this.leadtime = leadtime;
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
    
}
