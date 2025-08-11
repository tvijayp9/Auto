/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.struts.action;

import com.nexus.domain.Order;
import com.nexus.services.ServiceFinder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Sunil
 */
public class PrintArchivedOrderAction extends ActionSupport implements ServletRequestAware{

    Logger log=Logger.getLogger(PrintArchivedOrderAction.class);
    private HttpServletRequest request;
    private String pono;
    private String supplierName;
    private Date orderDate;
    private Collection items;
    private String comment;

    public String execute(){
      ActionContext ac=ActionContext.getContext();
      HttpSession session = request.getSession();
        try {
            String id = session.getAttribute("ID").toString();
            String orderid = request.getParameter("orderid");
            String supid = session.getAttribute("supplierid").toString();
            log.info("..id.."+id+"..orderid.."+orderid+"..supid.."+supid);
            com.nexus.dao.SpringHibernateDAO DAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            Order order=DAO.getArchivedOrderByOrderId(orderid);
            pono=order.getOrderNo();
            comment=order.getComments();
            supplierName=DAO.getSupplierName(supid);
            try {
                java.text.SimpleDateFormat dateFormatter1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
                orderDate = dateFormatter1.parse(order.getDeliveryDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Update for reading the items information from xy_order_item_details rather than product table, becuse item history is different than current item list.
            //items = DAO.getOrderitemlines(orderid);
            setItems(DAO.getArchivedOrderitemlinesdetails(orderid));
            //session.setAttribute("orderid", orderid);
        } catch (Exception e) {
            addActionError("Invalid CAT ID! Please try again!");
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

       public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
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

    /**
     * @return the supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
