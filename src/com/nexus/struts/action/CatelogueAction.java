package com.nexus.struts.action;

import com.nexus.services.ServiceFinder;
import com.nexus.web.common.DataBean;
import com.nexus.web.common.ItemList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import java.util.*;
import org.apache.log4j.Logger;

public class CatelogueAction extends ActionSupport implements ServletRequestAware {

    Logger log=Logger.getLogger(CatelogueAction.class);
    private Collection categories;
    private Collection items;
    private Collection orders;
    private Collection col2;
    private String supplierid;

    public Collection getOrders() {
        return orders;
    }

    public Collection getItems() {
        return items;
    }

    public Collection getCategories() {
        return categories;
    }
    private HttpServletRequest request;
    private String myname = "";
    private Integer id = 0;
    private Collection col;
    private Collection col1;
    private Collection messagecol;
    int partnerid = 0;
    Boolean isEnabled = false;
    String pono = "";
    String uname = "";
    String cancelreason = "";
    String orderstatus = "";

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getCancelreason() {
        return cancelreason;
    }

    public void setCancelreason(String cancelreason) {
        this.cancelreason = cancelreason;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
    Date orderDate;
    String theFormattedDate;
    java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy/MM/dd");
    java.text.SimpleDateFormat baiadaFormat = new java.text.SimpleDateFormat("yyyyMMdd");

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public Collection getCol() {
        return col;
    }

    public Collection getMessagecol() {
        return messagecol;
    }

    public Collection getCol1() {
        return col1;
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }

    public String showcatelogue() throws Exception {
        HttpSession session = request.getSession();
        String supplierid = request.getParameter("supplierid");
        if (supplierid == null) {
            supplierid = session.getAttribute("supplierid").toString();
        }
        session.setAttribute("supplierid", supplierid);
        

        try {
            String uid = session.getAttribute("ID").toString();
            log.info("showcatelogue..supplierid " + supplierid+".uid.."+uid);
            com.nexus.dao.SpringHibernateDAO partnerDAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            categories = partnerDAO.getCategoryList(uid, supplierid);

            if (categories.size() == 1) {
                ArrayList list = new ArrayList();
                list = (ArrayList) categories;
                DataBean data = new DataBean();
                data = (DataBean) list.get(0);
                String catid = data.getId();
                session.setAttribute("catid", catid);
                items = partnerDAO.getItemsByCategory(catid);
                list = (ArrayList) items;
                ItemList itemlist = new ItemList();
                for (int i = 0; i < list.size(); i++) {
                    itemlist = (ItemList) list.get(i);
                    //        System.out.println(itemlist.getProductid());
//                    System.out.println(itemlist.getName());
                    if (request.getParameter(itemlist.getGtin()) == null) {
                    } else {
                        itemlist.setQty(request.getParameter(itemlist.getGtin().toString()));
                    }
                }
                if (request.getParameter("uname") == null) {
                } else {
                    setUname(request.getParameter("uname").toString());
                }
            }
            log.info(categories.size());
        } catch (Exception e) {
            addActionError("Invalid buyerid or supplier id! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String showmyitemlist() throws Exception {
        HttpSession session = request.getSession();
        try {
//            if ((session.getAttribute("ID").toString() == null) || session.getAttribute("ID").toString().equalsIgnoreCase("")) {
//                System.out.println("Session Expired");
//                return ERROR;
//            } else {
            if (supplierid == null) {
                supplierid = session.getAttribute("supplierid").toString();
            }
            session.setAttribute("supplierid", supplierid);
            log.info("showmyitemlist..supplierid " + supplierid);
            String uid = session.getAttribute("ID").toString();
            log.info("userid " + uid);
            com.nexus.dao.SpringHibernateDAO partnerDAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            String bannerName = partnerDAO.getBannerNameByNexusId(supplierid);
            session.setAttribute("bannerName", bannerName);
            session.setAttribute("viewType", 1);
            ArrayList list = new ArrayList();
            items = partnerDAO.getMyItemlist(uid, supplierid);
            list = (ArrayList) items;
            ItemList itemlist = new ItemList();
            for (int i = 0; i < list.size(); i++) {
                itemlist = (ItemList) list.get(i);
                if (request.getParameter(itemlist.getGtin()) == null) {
                } else {
                    itemlist.setQty(request.getParameter(itemlist.getGtin().toString()));
                }
            }
            if (request.getParameter("uname") == null) {
            } else {
                setUname(request.getParameter("uname").toString());
            }
//            }
        } catch (Exception e) {
        }
        return SUCCESS;
    }

    public String showitemlist() throws Exception {
        HttpSession session = request.getSession();
        String catid = request.getParameter("catid");
        if (catid == null) {
            catid = session.getAttribute("catid").toString();
        }

        session.setAttribute("catid", catid);
        log.info("catid:" + catid);
        try {
            String id = session.getAttribute("ID").toString();
            com.nexus.dao.SpringHibernateDAO partnerDAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            items =partnerDAO.getItemsByCategory(catid);
        } catch (Exception e) {
            addActionError("Invalid CAT ID! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }

        return SUCCESS;
    }

    public String confirmorder() throws Exception {
        HttpSession session = request.getSession();

        String supplierid = session.getAttribute("supplierid").toString();
        if (request.getParameter("uname") == null) {
        } else {
            String username = request.getParameter("uname");
            this.setUname(username);
            log.info("uname:" + getUname());
        }

        /*if ((request.getParameter("orderDate") == null) || (request.getParameter("orderDate").equalsIgnoreCase(""))) {
        int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
        System.out.println("del date = " + request.getParameter("orderDate"));
        java.text.SimpleDateFormat dateFormatter1 = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String nextdate = dateFormatter1.format(new Date().getTime() + MILLIS_IN_DAY);
        Date ndate = dateFormatter1.parse(nextdate);
        setOrderDate(ndate);
        System.out.println("---orderDate is null---");
        } else {
        System.out.println("---orderDate is not null---");
        java.text.SimpleDateFormat dateFormatter1 = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String deldate = request.getParameter("orderDate");
        System.out.println("------del date = " + deldate);
        Date today = dateFormatter1.parse(deldate);
        setOrderDate(today);
        }*/

        try {
            String id = session.getAttribute("ID").toString();
            com.nexus.dao.SpringHibernateDAO partnerDAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            //items = partnerDAO.getItemsByCategory(catid);
            items =
                    partnerDAO.getMyItemlist(id, supplierid);
            ArrayList list = new ArrayList();
            list =
                    (ArrayList) items;
            ItemList itemlist = new ItemList();
            for (int i = 0; i <
                    list.size(); i++) {
                itemlist = (ItemList) list.get(i);
                log.info(itemlist.getGtin());
                log.info(itemlist.getName());
                if (request.getParameter(itemlist.getGtin()) == null) {
                } else {
                    String qty = request.getParameter(itemlist.getGtin().toString());
                    itemlist.setQty(qty);

                }

            }
        } catch (Exception e) {
            addActionError("Invalid CAT ID! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }

        return SUCCESS;
    }

    
    public String cancelorder() throws Exception {
        HttpSession session = request.getSession();
        try {
            com.nexus.dao.SpringHibernateDAO DAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            String orderid = session.getAttribute("orderid").toString();
            if (!(request.getParameter("cancelreason") == null)) {
                log.info("cancelorder..orderid:" + orderid);
                log.info("cancelorder..reason:" + request.getParameter("cancelreason"));
                DAO.cancelOrder(orderid, request.getParameter("cancelreason").toString());
                DAO.updateOrderStatus(orderid, "Cancelled By User");
            }
            session.removeAttribute("shoppingcart");
            session.removeAttribute("order");
            session.removeAttribute("deliveryDate");
            session.removeAttribute("comment");
        } catch (Exception e) {
            addActionError("Invalid ! Please try again!");
           log.error("Exception Message:", e);
            return ERROR;
        }

        return SUCCESS;
    }

        public String cancelorderforquote() throws Exception {
        HttpSession session = request.getSession();
        try {
            com.nexus.dao.SpringHibernateDAO DAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            String orderid = session.getAttribute("orderid").toString();
            if (!(request.getParameter("cancelreason") == null)) {
                log.info("cancelorderforquote..orderid:" + orderid);
                log.info("cancelorderforquote..reason:" + request.getParameter("cancelreason"));
                DAO.cancelOrder(orderid, request.getParameter("cancelreason").toString());
                DAO.updateOrderStatus(orderid, "Cancelled By User");
            }
            session.removeAttribute("quoteshoppingcart");
            session.removeAttribute("quotedeliveryDate");
            session.removeAttribute("quotecomment");
        } catch (Exception e) {
            addActionError("Invalid ! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }

        return SUCCESS;
    }

    public String orderlist() throws Exception {
        HttpSession session = request.getSession();
        try {
            String id = session.getAttribute("ID").toString();
            String supid = session.getAttribute("supplierid").toString();
            com.nexus.dao.SpringHibernateDAO DAO = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            orders =DAO.getOrderList(id, supid);
        } catch (Exception e) {
            addActionError("Invalid CAT ID! Please try again!");
            log.error("Exception Message:", e);
            return ERROR;
        }

        return SUCCESS;
    }

   
    public String getPono() {
        return pono;
    }

    public void setPono(String pono) {
        this.pono = pono;
    }

    
    /**
     * @return the supplierid
     */
    public String getSupplierid() {
        return supplierid;
    }

    /**
     * @param supplierid the supplierid to set
     */
    public void setSupplierid(String supplierid) {
        this.supplierid = supplierid;
    }
}
