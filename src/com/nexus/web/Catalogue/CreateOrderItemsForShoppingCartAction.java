/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web.Catalogue;

import com.nexus.domain.ShoppingCartItem;
import com.nexus.web.*;
import com.nexus.services.CatalogueService;
import com.nexus.services.ServiceFinder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

/**
 *
 * @author Terry
 */
public class CreateOrderItemsForShoppingCartAction extends ActionSupport implements ServletRequestAware, ServletContextAware {

    Logger log=Logger.getLogger(CreateOrderItemsForShoppingCartAction.class);
    private CatalogueService catalogueService;
    private String orderNumber;
    private String autoOrderNumber;
    private HttpServletRequest request;
    private ServletContext servletContext;

    public String execute() throws SQLException {
        ActionContext ac = ActionContext.getContext();
        Map session = ac.getSession();
        String supId = (String) session.get(Constant.SUPID);
        String id = (String) session.get(Constant.ID);
        Date deliveryDate = (Date) session.get("deliveryDate");
        String comment = (String) session.get("comment");
        List<ShoppingCartItem> sci = (List<ShoppingCartItem>) session.get("shoppingcart");
        catalogueService.createOrderItemsForShoppingCart(sci, new Integer(id).intValue(), new Integer(supId).intValue(), orderNumber, autoOrderNumber, deliveryDate, comment);
        String company = (String) session.get("mycompany");
        String clientname=session.get("loginpage").toString();
        log.info("clientName in..."+clientname+"..company.."+company);
//        int emailType = ((Integer) session.get("emailType")).intValue();
//        String email = null;
//        if (emailType == 1) {
//            email = servletContext.getInitParameter("newtownOrderEmail");
//        }
//        if (emailType == 2) {
//            email = servletContext.getInitParameter("kalamundaOrderEmail");
//        }
        SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String orderDate = dateFormatter.format(new Date());
         log.info("orderNumber in..."+orderNumber+"..orderDate.."+orderDate+"..supId.."+supId+"..id.."+id+"..clientname.."+clientname);
        sendOrderMail(company, orderNumber, orderDate,supId,id,clientname);
        session.remove("shoppingcart");
        session.remove("order");
        session.remove("deliveryDate");
        session.remove("comment");
        return SUCCESS;
    }

    public void sendOrderMail(String company, String orderNumber, String orderDate,String supid,String buyerid,String clientname) {
        try {
            com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            com.nexus.web.common.SendMail mailBean = (com.nexus.web.common.SendMail) ServiceFinder.getContext(request).getBean(com.nexus.web.common.ProjectConstants.MAIL_BEAN);
            log.info("company.."+company+"..orderNumber.."+orderNumber+"..supid..."+supid+"..buyerid..."+buyerid+"..clientname.."+clientname);
            //String email=catalogueService.findRuleValueMapping("Send Email for new Order", Integer.parseInt(buyerid), Integer.parseInt(supid));
            String email=partnerDao.findRuleValueMapping("Send Email for new Order", Integer.parseInt(supid));
            String reciepent[] = email.split(",");
//            String[] reciepent = {email};
            String emailsubject = company + " Order " + orderNumber;
            String emailmessage = "Mr/Mrs,";
            emailmessage += "\n\n Order " + orderNumber + " dated " + orderDate + " has arrived from customer " + company + ". Please take the necessary action to fulfil the order.";
            String url = "";

//            if (clientname.equalsIgnoreCase("newtown")) {
//                url = servletContext.getInitParameter("newtownUrl");
//                //emailmessage += "\n\n The URL to login to Nexus is http://218.214.0.189:8080/UMG/newtown.jsp";
//                emailmessage += "\n\n The URL to login to Nexus is "+url;
//            } else if (clientname.equalsIgnoreCase("kalamunda")){
//                url = servletContext.getInitParameter("kalamundaUrl");
//                //emailmessage += "\n\n The URL to login to Nexus is http://218.214.0.189:8080/UMG/kalamunda.jsp";
//                emailmessage += "\n\n The URL to login to Nexus is "+url;
//            }
            url = partnerDao.findRuleValueMapping("Supplier Order Url", Integer.parseInt(supid));
            emailmessage += "\n\n The URL to login to Nexus is "+url;

            emailmessage += "\n\n Best regards,";
            emailmessage += "\n\n Nexus Support Team";
            String from = com.nexus.web.common.ProjectConstants.FROM_MAIL;
            mailBean.sendMail(reciepent, emailsubject, emailmessage, from);
            log.info("order mail sent ");
        } catch (MessagingException ex) {
            log.error("Exception Message:", ex);
        }catch(Exception e){
            e.printStackTrace();
        }
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
     * @return the pono
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param pono the pono to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the autopono
     */
    public String getAutoOrderNumber() {
        return autoOrderNumber;
    }

    /**
     * @param autopono the autopono to set
     */
    public void setAutoOrderNumber(String autoOrderNumber) {
        this.autoOrderNumber = autoOrderNumber;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
