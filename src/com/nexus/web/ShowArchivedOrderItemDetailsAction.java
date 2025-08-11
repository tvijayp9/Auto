/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.JQGridRow;
import com.nexus.services.CatalogueService;
import java.util.List;

/**
 *
 * @author Sunil
 */
public class ShowArchivedOrderItemDetailsAction extends ActionSupport {

    private int page;
    private int rows;
    private String sidx;
    private String sord;
    private CatalogueService catalogueService;
    private int total_pages;
    private int count;
    private List<JQGridRow> result;
    private String orderId;

    public String execute() throws SQLException {
        count = catalogueService.getArchivedOrderItemsCount(orderId);
        if (count > 0) {
            total_pages = (int) Math.ceil((double) count / rows);
        } else {
            total_pages = 0;
        }
        if (page > total_pages) {
            page = total_pages;
        }
        int start=rows*page-rows;
        result=catalogueService.getArchivedOrderItems(orderId, start, rows, sidx, sord);
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
    @JSON(serialize=false)
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
    @JSON(serialize=false)
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
    @JSON(serialize=false)
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
    @JSON(serialize=false)
    public CatalogueService getCatalogueService() {
        return catalogueService;
    }

    /**
     * @param tradingPartnerService the tradingPartnerService to set
     */
    public void setCatalogueService(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    /**
     * @return the total_pages
     */
    @JSON(name="total")
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
    @JSON(name="records")
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
    @JSON(name="rows")
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
     * @return the orderId
     */
    @JSON(serialize=false)
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
