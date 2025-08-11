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
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Terry
 */
public class EditMicrocatAccountAction extends ActionSupport{

    private int nexusId;
    private TradingPartnerService tradingPartnerService;
    private String microcatAccountNumber;
    private String microcatUsername;
    private String microcatPassword;
    private int accountId;
    private int status;

    public String execute() throws SQLException {
        Microcat cat=tradingPartnerService.getMicrocatById(accountId);
        nexusId=cat.getNexusId();
        microcatAccountNumber=cat.getAccountNumber();
        microcatUsername=cat.getUsername();
        microcatPassword=cat.getPassword();
        status=cat.getStatus();
        return SUCCESS;
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

    /**
     * @return the nexusId
     */
    public int getNexusId() {
        return nexusId;
    }

    /**
     * @param nexusId the nexusId to set
     */
    public void setNexusId(int nexusId) {
        this.nexusId = nexusId;
    }

    /**
     * @return the microcatAccountNumber
     */
    public String getMicrocatAccountNumber() {
        return microcatAccountNumber;
    }

    /**
     * @param microcatAccountNumber the microcatAccountNumber to set
     */
    public void setMicrocatAccountNumber(String microcatAccountNumber) {
        this.microcatAccountNumber = microcatAccountNumber;
    }

    /**
     * @return the microcatUsername
     */
    public String getMicrocatUsername() {
        return microcatUsername;
    }

    /**
     * @param microcatUsername the microcatUsername to set
     */
    public void setMicrocatUsername(String microcatUsername) {
        this.microcatUsername = microcatUsername;
    }

    /**
     * @return the microcatPassword
     */
    public String getMicrocatPassword() {
        return microcatPassword;
    }

    /**
     * @param microcatPassword the microcatPassword to set
     */
    public void setMicrocatPassword(String microcatPassword) {
        this.microcatPassword = microcatPassword;
    }

    /**
     * @return the accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
