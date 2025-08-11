/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.struts.action;

import com.nexus.services.TradingPartnerService;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.nexus.domain.Partner;
import java.util.*;
/**
 *
 * @author Vijay Thumma
 */
public class ProductManagementAction extends ActionSupport implements ServletRequestAware{
    Logger log = Logger.getLogger(ProductManagementAction.class);
    private HttpServletRequest request;
    private TradingPartnerService tradingPartnerService;
    private List<Partner> partnerList = new ArrayList<Partner>();
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        String supId = (String) ac.getSession().get(Constant.SUPID);
        setPartnerList(tradingPartnerService.getMyPartnersList(Integer.parseInt(supId)));
        ac.getSession().put("partners", getPartnerList());
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
     * @return the partnerList
     */
    public List<Partner> getPartnerList() {
        return partnerList;
    }

    /**
     * @param partnerList the partnerList to set
     */
    public void setPartnerList(List<Partner> partnerList) {
        this.partnerList = partnerList;
    }

 
}
