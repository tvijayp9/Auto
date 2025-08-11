/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.services.TransactionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;

/**
 *
 * @author Terry
 */
public class AllNewOrdersCountAction extends ActionSupport {

    private TransactionService transactionService;
    private int count;

    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        String id = (String) ac.getSession().get(Constant.ID);
        count = transactionService.getAllNewOrdersCount(new Integer(id).intValue());
        return SUCCESS;
    }

    /**
     * @return the transactionService
     */
    @JSON(serialize = false)
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
     * @return the count
     */
    @JSON(name = "count")
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
