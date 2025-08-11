/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.JQGridRow;
import com.nexus.services.TransactionService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Terry
 */
public class ShowOutBoundListDetailsAction extends ActionSupport {

    private int page;
    private int rows;
    private String sidx;
    private String sord;
    private TransactionService transactionService;
    private int total_pages;
    private int count;
    private List<JQGridRow> result;
    private String _search;
    private String vch_document_id;
    private String company;
    private String dt_received;
    private String dt1_received;

    

    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        String id = (String) ac.getSession().get(Constant.ID);
        count = transactionService.getOutBoundMessageListCount(id, _search, vch_document_id, company, dt_received,dt1_received);
        if (count > 0) {
            total_pages = (int) Math.ceil((double) count / rows);
        } else {
            total_pages = 0;
        }
        if (page > total_pages) {
            page = total_pages;
        }
        int start = rows * page - rows;
        if (start >= 0) {
            result = transactionService.getOutBoundMessageList(id, _search, vch_document_id, company, dt_received,dt1_received, start, rows, sidx, sord);
        } else {
            result = new ArrayList();
        }
        return SUCCESS;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the rows
     */
    @JSON(serialize = false)
    public int getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * @return the sidx
     */
    @JSON(serialize = false)
    public String getSidx() {
        return sidx;
    }

    /**
     * @param sidx the sidx to set
     */
    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    /**
     * @return the sord
     */
    @JSON(serialize = false)
    public String getSord() {
        return sord;
    }

    /**
     * @param sord the sord to set
     */
    public void setSord(String sord) {
        this.sord = sord;
    }

    /**
     * @return the tradingPartnerService
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
     * @return the total_pages
     */
    @JSON(name = "total")
    public int getTotal_pages() {
        return total_pages;
    }

    /**
     * @param total_pages the total_pages to set
     */
    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    /**
     * @return the count
     */
    @JSON(name = "records")
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the rows
     */
    @JSON(name = "rows")
    public List getResult() {
        return result;
    }

    /**
     * @param rows the rows to set
     */
    public void setResult(List result) {
        this.result = result;
    }

    /**
     * @return the _search
     */
    @JSON(serialize = false)
    public String get_search() {
        return _search;
    }

    /**
     * @param search the _search to set
     */
    public void set_search(String _search) {
        this._search = _search;
    }

    /**
     * @return the vch_document_id
     */
    @JSON(serialize = false)
    public String getVch_document_id() {
        return vch_document_id;
    }

    /**
     * @param vch_document_id the vch_document_id to set
     */
    public void setVch_document_id(String vch_document_id) {
        this.vch_document_id = vch_document_id;
    }

    /**
     * @return the company
     */
    @JSON(serialize = false)
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the dt_received
     */
    @JSON(serialize = false)
    public String getDt_received() {
        return dt_received;
    }

    /**
     * @param dt_received the dt_received to set
     */
    public void setDt_received(String dt_received) {
        this.dt_received = dt_received;
    }
    public String getDt1_received() {
        return dt1_received;
    }

    public void setDt1_received(String dt1_received) {
        this.dt1_received = dt1_received;
    }
}
