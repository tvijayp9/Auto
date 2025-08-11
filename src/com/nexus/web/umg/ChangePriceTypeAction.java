/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.umg;

import com.nexus.services.ServiceFinder;
import com.nexus.services.TradingPartnerService;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Terry
 */
public class ChangePriceTypeAction extends ActionSupport implements ServletRequestAware{

     Logger log=Logger.getLogger(ChangePriceTypeAction.class);
     private HttpServletRequest request;
    private TradingPartnerService tradingPartnerService;
    private int priceType;
    private String partners;
     private Collection pricetypes;

     public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
    public String execute() throws SQLException {
        log.info("pricetype.."+priceType);
        HttpSession session = request.getSession();
        Integer supplierId=Integer.parseInt((String)session.getAttribute("supplierid").toString());
        log.info("supplierid val="+supplierId);
        tradingPartnerService.changePriceType(priceType, partners);
        com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        pricetypes=partnerDao.getPriceList(supplierId);
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
     * @return the priceType
     */
    public int getPriceType() {
        return priceType;
    }

    /**
     * @param priceType the priceType to set
     */
    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    /**
     * @return the partners
     */
    public String getPartners() {
        return partners;
    }

    /**
     * @param partners the partners to set
     */
    public void setPartners(String partners) {
        this.partners = partners;
    }

    /**
     * @return the pricetypes
     */
    public Collection getPricetypes() {
        return pricetypes;
    }

    /**
     * @param pricetypes the pricetypes to set
     */
    public void setPricetypes(Collection pricetypes) {
        this.pricetypes = pricetypes;
    }
}
