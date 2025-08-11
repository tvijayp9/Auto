/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.nexus.services.TransactionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import org.apache.struts2.util.ServletContextAware;

/**
 *
 * @author Terry
 */
public class ViewNewOrderFromInboundTransactionAction extends ActionSupport implements ServletContextAware {

    private TransactionService transactionService;
    private List allNewOrders;
    private HashMap reportParams;
    private ServletContext context;

    public String execute() throws SQLException, ParseException {
        reportParams = new HashMap();
        String path = context.getRealPath("/reports") + "\\";
        reportParams.put("SUBREPORT_DIR", path);
        ActionContext ac = ActionContext.getContext();
        String id = (String) ac.getSession().get(Constant.ID);
        allNewOrders = transactionService.getAllNewOrders(new Integer(id).intValue());
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
     * @return the allSelectedOrders
     */
    public List getAllNewOrders() {
        return allNewOrders;
    }

    /**
     * @param allSelectedOrders the allSelectedOrders to set
     */
    public void setAllNewOrders(List allNewOrders) {
        this.allNewOrders = allNewOrders;
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
