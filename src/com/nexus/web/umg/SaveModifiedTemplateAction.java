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

/**
 *
 * @author Terry
 */
public class SaveModifiedTemplateAction extends ActionSupport {

    private CatalogueService catalogueService;
    private int templateId;

    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        String supplierId = (String) session.get(Constant.SUPID);
        String id = (String) session.get(Constant.ID);
        List<TemplateOrderItem> toi = (List<TemplateOrderItem>) session.get("newTemplate");
        catalogueService.saveModifiedTemplate(templateId,toi, new Integer(id).intValue(), new Integer(supplierId).intValue());
        session.remove("newTemplate");
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
