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
 * @author Terry
 */
public class SaveModifiedQuoteAction extends ActionSupport {

    Logger log=Logger.getLogger(SaveModifiedQuoteAction.class);
    private CatalogueService catalogueService;
    private int templateId;

    public String execute() throws SQLException {
        log.info("inside SaveModifiedQuoteAction");
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        String supplierId = (String) session.get(Constant.SUPID);
        String id = (String) session.get(Constant.ID);
        List<TemplateOrderItem> toi = (List<TemplateOrderItem>) session.get("newQuote");
        catalogueService.saveModifiedQuote(templateId,toi, new Integer(id).intValue(), new Integer(supplierId).intValue());
       // session.remove("newQuote");
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
}
