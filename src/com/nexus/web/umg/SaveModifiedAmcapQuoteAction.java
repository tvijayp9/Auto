/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

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
 * @author Vijay Thumma
 */
public class SaveModifiedAmcapQuoteAction extends ActionSupport {

    Logger log=Logger.getLogger(SaveModifiedAmcapQuoteAction.class);
    private CatalogueService catalogueService;
    private int templateId;
    private String quoteName;

    public String execute() throws SQLException {
        log.info("inside SaveModifiedAmcapQuoteAction");
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        String supplierId = (String) session.get(Constant.SUPID);
        String id = (String) session.get(Constant.ID);
        List<TemplateOrderItem> toi = (List<TemplateOrderItem>) session.get("newQuote");
        setQuoteName((String) session.get("quoteName"));
        catalogueService.saveModifiedQuote(templateId,toi, new Integer(id).intValue(), new Integer(supplierId).intValue());
        session.remove("newQuote");
//        session.remove("quoteName");
        return SUCCESS;
    }

    /**
     * @return the catalogueSerivce
     */
    public CatalogueService getCatalogueService() {
        return catalogueService;
    }

    /**
     * @param catalogueSerivce the catalogueSerivce to set
     */
    public void setCatalogueService(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    /**
     * @return the templateId
     */
    public int getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId the templateId to set
     */
    public void setTemplateId(int templateId) {
        this.templateId = templateId;
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
    
}
