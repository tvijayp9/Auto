/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.InvoiceLineItem;
import com.nexus.domain.JQGridRow;
import com.nexus.domain.PurchaseOrder;
import com.nexus.services.TransactionService;
import com.opensymphony.xwork2.ActionContext;
import java.text.ParseException;
import java.util.List;
import org.apache.log4j.Logger;
import java.util.Map;

/**
 *
 * @author Vijay Thumma
 */
public class ShowInvoiceDetailsAction extends ActionSupport {

    Logger log = Logger.getLogger(ShowInvoiceDetailsAction.class);
    private TransactionService transactionService;
    private List<JQGridRow> items;
     private List<InvoiceLineItem> invoiceLineItems;
    private String messageId;
    private String customerCode;
    private String orderNumber;
    private String invoiceNo;

    public String execute() throws SQLException, ParseException, Exception {
        ActionContext ac = ActionContext.getContext();
         Map session = ac.getSession();
        log.info("messageId=" + messageId);
        
        PurchaseOrder po = transactionService.getPurchaseOrderforInvoice(messageId);
        if (po != null) {
            setCustomerCode(po.getCustomerCode());
            setOrderNumber(po.getOrderNumber());
            setInvoiceNo(po.getInvoiceNo());
            log.info("customerCode...." + getCustomerCode() + "..orderNumber..." + getOrderNumber());
            invoiceLineItems=po.getItemList();
           // setItems(po.getItemList());
            items=transactionService.constructInvoiceLineItems(invoiceLineItems);
//        if (session.get("invoicecart") == null) {
            session.put("invoicecart", invoiceLineItems);
//        }
            log.info("items...." + getItems().size());
            transactionService.updateMessageStatus(messageId, 1);
        }
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
     * @param tradingPartnerService the tradingPartnerService to set
     */
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * @return the rows
     */
    @JSON(name = "rows")
    public List<JQGridRow> getItems() {
        return items;
    }

    /**
     * @param rows the rows to set
     */
    public void setItems(List items) {
        this.items = items;
    }

    /**
     * @return the messageId
     */
    @JSON(serialize = false)
    public String getMessageId() {
        return messageId;
    }

    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * @return the customerCode
     */
    @JSON(serialize = false)
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * @param customerCode the customerCode to set
     */
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    /**
     * @return the orderNumber
     */
    @JSON(serialize = false)
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the invoiceNo
     */
    @JSON(serialize = false)
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * @param invoiceNo the invoiceNo to set
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
}
