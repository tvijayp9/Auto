/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.services.TransactionService;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;

/**
 *
 * @author Terry
 */
public class ArchiveInBoundMessageAction extends ActionSupport {

    private TransactionService transactionService;
    private String id;

    /**
     * @return the transactionService
     */
    @JSON(serialize=false)
    public TransactionService getTransactionService() {
        return transactionService;
    }

    /**
     * @param transactionService the transactionService to set
     */
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public String execute() throws SQLException {
        transactionService.updateMessageStatus(id,2);
        return SUCCESS;
    }

    /**
     * @return the id
     */
    @JSON(serialize=false)
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
