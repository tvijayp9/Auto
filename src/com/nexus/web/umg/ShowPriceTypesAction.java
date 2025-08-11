/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.umg;

import com.nexus.services.ServiceFinder;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author user
 */
public class ShowPriceTypesAction extends ActionSupport implements ServletRequestAware {

    Logger log=Logger.getLogger(ShowPriceTypesAction.class);
    private HttpServletRequest request;
     public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
     private int priceType = 0;
     private Collection pricetypes;

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

     public String execute() throws Exception {
         HttpSession session = request.getSession();
        Integer supplierId=Integer.parseInt((String)session.getAttribute("supplierid").toString());
        log.info("supplierid val="+supplierId);
        com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        pricetypes= partnerDao.getPriceList(supplierId);
        return SUCCESS;
    }

}
