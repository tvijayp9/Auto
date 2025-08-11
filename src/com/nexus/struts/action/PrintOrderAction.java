/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.struts.action;

import com.nexus.domain.Order;
import com.nexus.domain.SubTotal;
import com.nexus.services.CatalogueService;
import com.nexus.services.ServiceFinder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Terry
 */
public class PrintOrderAction extends ActionSupport implements ServletRequestAware {

    Logger log=Logger.getLogger(PrintOrderAction.class);
    private HttpServletRequest request;
    private String pono;
    private String supplierName;
    private Date orderDate;
    private List items;
    private String comment;
    private CatalogueService catalogueService;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;
    private BigDecimal totalCost;

    public String execute() {
        ActionContext ac = ActionContext.getContext();
        HttpSession session = request.getSession();
        try {
            String id = session.getAttribute("ID").toString();
            String orderid = request.getParameter("orderid");
            String supid = session.getAttribute("supplierid").toString();
            log.info("..id.."+id+"..orderid.."+orderid+"..supid.."+supid);
            com.nexus.dao.SpringHibernateDAO DAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            Order order = DAO.getOrderByOrderId(orderid);
            pono = order.getOrderNo();
            comment = order.getComments();
            supplierName = DAO.getSupplierName(supid);
            try {
                java.text.SimpleDateFormat dateFormatter1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
                orderDate = dateFormatter1.parse(order.getDeliveryDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Update for reading the items information from xy_order_item_details rather than product table, becuse item history is different than current item list.
            //items = DAO.getOrderitemlines(orderid);
            items = catalogueService.getOrderItems(new Integer(orderid).intValue());
            SubTotal subtotal = catalogueService.getSubtotalForPrintOrder(items);
            totalPrice = subtotal.getTotalPrice();
            totalTax = subtotal.getTotalTax();
            totalCost = subtotal.getTotalCost();
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
    public List getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List items) {
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
     * @return the totalPrice
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the totalTax
     */
    public BigDecimal getTotalTax() {
        return totalTax;
    }

    /**
     * @param totalTax the totalTax to set
     */
    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    /**
     * @return the totalCost
     */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
