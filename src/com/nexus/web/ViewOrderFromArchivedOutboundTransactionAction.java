/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.nexus.domain.PurchaseOrder;
import com.nexus.services.TransactionService;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Terry
 */
public class ViewOrderFromArchivedOutboundTransactionAction extends ActionSupport {

    private Collection items;
    private String messageId;
    private HashMap reportParams;
    private TransactionService transactionService;

    public String execute() throws SQLException, ParseException {
        PurchaseOrder po = transactionService.getPurchaseOrderFromArchivedOutboundTransaction(messageId);
        if (po != null) {
            reportParams = new HashMap();
            reportParams.put("orderNumber", po.getOrderNumber());
            reportParams.put("comment", po.getComment());
            reportParams.put("customerCode", po.getCustomerCode());
            reportParams.put("deliveryDate", po.getDeliveryDate());
            items = po.getItemList();
        }
        return SUCCESS;
    }

    /**
     * @return the items
     */
    public Collection getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Collection items) {
        this.items = items;
    }

    /**
     * @return the orderNumber
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
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
}
