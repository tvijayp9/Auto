/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.nexus.dao.SpringHibernateDAO;
import com.nexus.dao.SpringHibernateDAO;
import com.nexus.domain.Order;
import com.nexus.services.CatalogueService;
import com.nexus.services.ServiceFinder;
import com.nexus.web.common.ItemList;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Terry
 */
public class CreateOrderAction extends ActionSupport implements ServletRequestAware {

    private CatalogueService catalogueService;
    private String uname;
    private Date orderDate;
    private String pono;
    private HttpServletRequest request;
    private Collection items;

    public String execute() throws SQLException{
        ActionContext ac = ActionContext.getContext();
        String supId = (String) ac.getSession().get(Constant.SUPID);
        String id = (String) ac.getSession().get(Constant.ID);
        Order order = catalogueService.createOrder(id, supId, orderDate, uname);
        pono=order.getOrderNo();
        ac.getSession().put("orderid", order.getId());
        SpringHibernateDAO partnerDAO = (SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        items = partnerDAO.getMyItemlist(id, supId);
        Iterator i = items.iterator();
        while (i.hasNext()) {
            ItemList itemlist = (ItemList) i.next();
            if (request.getParameter(itemlist.getGtin()) == null) {
            } else {
                itemlist.setQty(request.getParameter(itemlist.getGtin()));
            }

        }
        return SUCCESS;
    }

    /**
     * @return the catalogueService
     */
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
     * @return the uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * @param uname the uname to set
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the pono
     */
    public String getPono() {
        return pono;
    }

    /**
     * @param pono the pono to set
     */
    public void setPono(String pono) {
        this.pono = pono;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    /**
     * @return the items
     */
    public Collection getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Collection items) {
        this.items = items;
    }
}
