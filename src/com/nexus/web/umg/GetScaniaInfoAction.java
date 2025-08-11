/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.Microcat;
import com.nexus.services.CatalogueService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.struts2.util.ServletContextAware;

/**
 *
 * @author Terry
 */
public class GetScaniaInfoAction extends ActionSupport implements ServletContextAware {
    private String microcatId;
    private String accountNumber;
    private String username;
    private String password;
    private String email;
    private boolean available;
    private CatalogueService catalogueService;
    private ServletContext servletContext;
    Logger log=Logger.getLogger(GetScaniaInfoAction.class);
    public String execute() throws SQLException {
        String unlockedTime=servletContext.getInitParameter("scaniaAccountUnlockedTime");
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        String id = (String) session.get(Constant.ID);
        log.info("id..."+id);
            Microcat cat = catalogueService.getScaniaInfo(new Integer(id));
            if (cat == null) {
                 log.info("if cat is null...");
//                Microcat cat1 = catalogueService.getScaniaInfoByTime(new Integer(id),new Integer(unlockedTime).intValue());
//                if (cat1 == null) {
//                     log.info("if cat1 is null...");
//                    available = false;
//                } else {
//                    setMicrocatId("" + cat1.getId());
//                    accountNumber = cat1.getAccountNumber();
//                    username = cat1.getUsername();
//                    password = cat1.getPassword();
//                    setEmail(cat1.getEmail());
//                    available = true;
//                    session.put("scania", cat1);
//                    catalogueService.updateScaniaTime(cat1.getId());
//                }
                available = false;
            } else {
                 log.info("if cat is not null..."+cat.getId());
                setMicrocatId("" + cat.getId());
                accountNumber = cat.getAccountNumber();
                username = cat.getUsername();
                password = cat.getPassword();
                setEmail(cat.getEmail());
                available = true;
                session.remove("scania");
                session.put("scania", cat);
                catalogueService.updateScaniaStatus(cat.getId());
            }

        return SUCCESS;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the availabe
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param availabe the availabe to set
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * @return the catalogueService
     */
    @JSON(serialize = false)
    public CatalogueService getCatalogueService() {
        return catalogueService;
    }

    /**
     * @param catalogueService the catalogueService to set
     */
    public void setCatalogueService(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    /**
     * @return the servletContext
     */
    @JSON(serialize = false)
    public ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * @param servletContext the servletContext to set
     */
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * @return the microcatId
     */
    public String getMicrocatId() {
        return microcatId;
    }

    /**
     * @param microcatId the microcatId to set
     */
    public void setMicrocatId(String microcatId) {
        this.microcatId = microcatId;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
