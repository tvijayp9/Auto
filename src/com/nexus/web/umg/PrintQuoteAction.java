/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.services.CatalogueService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.struts2.util.ServletContextAware;

/**
 *
 * @author Terry
 */
public class PrintQuoteAction extends ActionSupport implements ServletContextAware {

    Logger log=Logger.getLogger(PrintQuoteAction.class);
    private CatalogueService catalogueService;
    private HashMap reportParams;
    private List items;
    private String qid;
    private ServletContext context;

    public String execute() throws SQLException, ParseException {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        reportParams = catalogueService.getParametersForPrintQuote(new Integer(qid).intValue());
        String logoname = (String) session.get("logoname");
        String path = context.getRealPath("/images") + "/" + logoname;
        log.info("logoname.."+logoname+"..path.."+path);
        reportParams.put("logo", path);
        items = catalogueService.getQuoteItemsByQuoteId(new Integer(qid).intValue());
        return SUCCESS;
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
     * @return the reportParams
     */
    public HashMap getReportParams() {
        return reportParams;
    }

    /**
     * @param reportParams the reportParams to set
     */
    public void setReportParams(HashMap reportParams) {
        this.reportParams = reportParams;
    }

    /**
     * @return the items
     */
    public List getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List items) {
        this.items = items;
    }

    /**
     * @return the qid
     */
    public String getQid() {
        return qid;
    }

    /**
     * @param qid the qid to set
     */
    public void setQid(String qid) {
        this.qid = qid;
    }

    public void setServletContext(ServletContext context) {
        this.context = context;
    }
}
