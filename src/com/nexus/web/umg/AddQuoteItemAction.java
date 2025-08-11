/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.TemplateOrderItem;
import com.nexus.services.CatalogueService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Terry
 */
public class AddQuoteItemAction extends ActionSupport {
      Logger log=Logger.getLogger(AddQuoteItemAction.class);
    private CatalogueService catalogueService;
    private String[] productCode;
    String product_table_name=null;
    List<TemplateOrderItem> list;
    public String execute() throws SQLException{
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        String id = (String) session.get(Constant.ID);
        String supplierId = (String) session.get(Constant.SUPID);
        product_table_name=(String)ac.getSession().get("product_table_name");
        List<TemplateOrderItem> toi = (List<TemplateOrderItem>) session.get("newQuote");
        list=catalogueService.addQuoteItem(new Integer(id).intValue(), new Integer(supplierId).intValue(),productCode, toi,product_table_name);
        session.put("newQuote",list);
        log.info("size in action class="+toi.size()+"..new list..size.."+list.size());
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
     * @return the productId
     */
    @JSON(serialize = false)
    public String[] getProductCode() {
        return productCode;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductCode(String[] productCode) {
        this.productCode = productCode;
    }
}
