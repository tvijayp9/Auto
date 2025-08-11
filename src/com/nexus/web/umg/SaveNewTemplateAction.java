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
public class SaveNewTemplateAction extends ActionSupport {

    private CatalogueService catalogueService;
    private String templateName;

    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        String supplierId = (String) session.get(Constant.SUPID);
        String id = (String) session.get(Constant.ID);
        List<TemplateOrderItem> toi = (List<TemplateOrderItem>) session.get("newTemplate");
        catalogueService.saveNewTemplate(templateName,toi, new Integer(id).intValue(), new Integer(supplierId).intValue());
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
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName the templateName to set
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
