/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.Microcat;
import com.nexus.saml.SAMLAttribute;
import com.nexus.saml.SamlIdp;
import com.nexus.services.CatalogueService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.struts2.util.ServletContextAware;

/**
 *
 * @author Terry
 */
public class GetMicrocatInfoAction extends ActionSupport implements ServletContextAware {
    private String microcatId;
    private String accountNumber;
    private String username;
    private String password;
    private String company;
    private String relayState;
    private String idpSSOUrl;
    private boolean available;
    private String samlAssertion;
    private CatalogueService catalogueService;
    private ServletContext servletContext;
    Logger log=Logger.getLogger(GetMicrocatInfoAction.class);
    public String execute() throws SQLException {
        String unlockedTime=servletContext.getInitParameter("microcatAccountUnlockedTime");
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        String id = (String) session.get(Constant.ID);
        log.info("id..."+id);
        //Object o = session.get("microcat");
//        if (o == null) {
//            log.info("Microcat session null...");
            Microcat cat = catalogueService.getMicrocatInfo(new Integer(id));
            if (cat == null) {
                Microcat cat1 = catalogueService.getMicrocatInfoByTime(new Integer(id),new Integer(unlockedTime).intValue());
                if (cat1 == null) {
                    available = false;
                } else {
                    setMicrocatId("" + cat1.getId());
                    accountNumber = cat1.getAccountNumber();
                    username = cat1.getUsername();
                    company = cat1.getCompany();
                    available = true;
                    session.put("microcat", cat1);
                    catalogueService.updateMicrocatTime(cat1.getId());
                }
            } else {
                setMicrocatId("" + cat.getId());
                accountNumber = cat.getAccountNumber();
                username = cat.getUsername();
                company = cat.getCompany();
                available = true;
                session.put("microcat", cat);
                catalogueService.updateMicrocatStatus(cat.getId());
            }
            if(available){
                try{
                    SamlIdp idp = new SamlIdp(servletContext);
                    List<SAMLAttribute> attributes = new ArrayList<SAMLAttribute>();
                    attributes.add(new SAMLAttribute("username", Arrays.asList(username)));
                    String samlResponse = idp.buildEncodedRespponse(username, attributes);
                    samlAssertion = samlResponse;
                    relayState = idp.getRelayState()+ "&brand=" + company;
                    idpSSOUrl = idp.getDestinationUrl();
                }catch(Exception exp){
                    exp.printStackTrace();
                }
            }
//        }
//        else {
//            log.info("Microcat session not null...");
//            Microcat cat = (Microcat) o;
//            accountNumber = cat.getAccountNumber();
//            username = cat.getUsername();
//            password = cat.getPassword();
//            available = true;
//        }
        return SUCCESS;
    }
    
     /**
     * @return the samlAssertion
     */
    public String getSamlAssertion() {
        return this.samlAssertion;
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
     * @return the company
     */
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
     * @return the relayState
     */
    public String getRelayState() {
        return relayState;
    }

    /**
     * @return the idpSSOUrl
     */
    public String getIdpSSOUrl() {
        return idpSSOUrl;
    }

    /**
     * @param idpSSOUrl the idpSSOUrl to set
     */
    public void setIdpSSOUrl(String idpSSOUrl) {
        this.idpSSOUrl = idpSSOUrl;
    }
}
