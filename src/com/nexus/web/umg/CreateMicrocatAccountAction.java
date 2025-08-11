/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.domain.Microcat;
import com.nexus.services.TradingPartnerService;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Terry
 */
public class CreateMicrocatAccountAction extends ActionSupport implements ServletRequestAware {

     Logger log=Logger.getLogger(CreateMicrocatAccountAction.class);
    private int newMemberId;
    private int microcatAccountAmount;
    private TradingPartnerService tradingPartnerService;
    private HttpServletRequest request;

    public String execute() throws SQLException {
        List<Microcat> list = new ArrayList();
        for (int i = 1; i <= microcatAccountAmount; i++) {
            String accountNumber = request.getParameter("microcatAccountNumber" + i);
            String username = request.getParameter("microcatUsername" + i);
            String password = request.getParameter("microcatPassword" + i);
            log.info("accountNumber.."+accountNumber+".username.."+username);
            list.add(new Microcat(accountNumber, username, password));
        }
        tradingPartnerService.createMicrocatAccounts(newMemberId, list);
        return SUCCESS;
    }

    /**
     * @return the newMemberId
     */
    public int getNewMemberId() {
        return newMemberId;
    }

    /**
     * @param newMemberId the newMemberId to set
     */
    public void setNewMemberId(int newMemberId) {
        this.newMemberId = newMemberId;
    }

    /**
     * @return the microcatAccountAmount
     */
    public int getMicrocatAccountAmount() {
        return microcatAccountAmount;
    }

    /**
     * @param microcatAccountAmount the microcatAccountAmount to set
     */
    public void setMicrocatAccountAmount(int microcatAccountAmount) {
        this.microcatAccountAmount = microcatAccountAmount;
    }

    /**
     * @return the tradingPartnerService
     */
    public TradingPartnerService getTradingPartnerService() {
        return tradingPartnerService;
    }

    /**
     * @param tradingPartnerService the tradingPartnerService to set
     */
    public void setTradingPartnerService(TradingPartnerService tradingPartnerService) {
        this.tradingPartnerService = tradingPartnerService;
    }

        public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
}
