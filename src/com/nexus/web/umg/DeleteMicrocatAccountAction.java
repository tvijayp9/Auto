/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.services.AdministrationService;
import com.nexus.services.TradingPartnerService;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;

/**
 *
 * @author Terry
 */
public class DeleteMicrocatAccountAction extends ActionSupport {

    private TradingPartnerService tradingPartnerService;
    private String id;

    public String execute() throws SQLException {
        tradingPartnerService.deleteMicrocats(id);
        return SUCCESS;
    }

    /**
     * @return the id
     */
    @JSON(serialize = false)
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
     * @return the tradingPartnerService
     */
    @JSON(serialize = false)
    public TradingPartnerService getTradingPartnerService() {
        return tradingPartnerService;
    }

    /**
     * @param tradingPartnerService the tradingPartnerService to set
     */
    public void setTradingPartnerService(TradingPartnerService tradingPartnerService) {
        this.tradingPartnerService = tradingPartnerService;
    }
}
