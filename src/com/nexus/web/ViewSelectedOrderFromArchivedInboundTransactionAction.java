/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.nexus.services.TransactionService;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.struts2.util.ServletContextAware;

/**
 *
 * @author Terry
 */
public class ViewSelectedOrderFromArchivedInboundTransactionAction extends ActionSupport implements ServletContextAware {

    Logger log=Logger.getLogger(ViewSelectedOrderFromArchivedInboundTransactionAction.class);
    private TransactionService transactionService;
    private String id;
    private List allSelectedArchivedOrders;
    private HashMap reportParams;
    private ServletContext context;

    public String execute() throws SQLException, ParseException {
        reportParams = new HashMap();
        String path=context.getRealPath("/reports")+"\\";
        reportParams.put("SUBREPORT_DIR", path);
        log.info("ID in ViewSelectedOrderFromArchivedInboundTransactionAction = "+ id);
        allSelectedArchivedOrders = transactionService.getAllSelectedArchivedInboundOrders(id);
        log.info("ID in ViewSelectedOrderFromArchivedInboundTransactionAction = "+ allSelectedArchivedOrders.size());
        return SUCCESS;
    }

    /**
     * @return the transactionService
     */
    public TransactionService getTransactionService() {
        return transactionService;
    }

    /**
     * @param transactionService the transactionService to set
     */
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * @return the id
     */
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
     * @return the allSelectedOrders
     */
    public List getAllSelectedArchivedOrders() {
        return allSelectedArchivedOrders;
    }

    /**
     * @param allSelectedOrders the allSelectedOrders to set
     */
    public void setAllSelectedArchivedOrders(List allSelectedArchivedOrders) {
        this.allSelectedArchivedOrders = allSelectedArchivedOrders;
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

    public void setServletContext(ServletContext context) {
        this.context = context;
    }
}
