/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.DR;

import com.nexus.services.DataResolutionService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.JQGridRow;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

public class ShowProcessedTransactionListAction extends ActionSupport implements ServletRequestAware{

    Logger log=Logger.getLogger(ShowProcessedTransactionListAction.class);
    private int page;
    private int rows;
    private String sidx;
    private String sord;
    private DataResolutionService dataResolutionService;
    private int total_pages;
    private int count;
    private List<JQGridRow> result;
    private String _search;
    private String transactionnumber;
    private String type;
    HttpServletRequest request;


    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
    public String execute() throws SQLException {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute(Constant.ID);
        String userloginId = (String) session.getAttribute("userLoginId");
        String isSupplier=(String)session.getAttribute("isSupplier");
        type = (String) session.getAttribute("filtertype");
        count = dataResolutionService.getMyTransactionsListCount(id,userloginId,isSupplier, _search, transactionnumber,type);
        log.info("count: " + count);
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
                result = dataResolutionService.findProcessedTransactionsList(id, userloginId, isSupplier, _search, transactionnumber, start, rows, sidx, sord);
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
    public DataResolutionService getDataResolutionService() {
        return dataResolutionService;
    }

    /**
     * @param tradingPartnerService the tradingPartnerService to set
     */
    public void setDataResolutionService(DataResolutionService dataResolutionService) {
        this.dataResolutionService = dataResolutionService;
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the transactionnumber
     */
     @JSON(serialize = false)
    public String getTransactionnumber() {
        return transactionnumber;
    }

    /**
     * @param transactionnumber the transactionnumber to set
     */
    public void setTransactionnumber(String transactionnumber) {
        this.transactionnumber = transactionnumber;
    }
}