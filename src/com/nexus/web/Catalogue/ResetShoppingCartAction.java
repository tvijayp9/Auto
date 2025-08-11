/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.Catalogue;

import com.nexus.domain.Microcat;
import com.nexus.domain.Order;
import com.nexus.domain.PartnerLink;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.services.AccountService;
import com.nexus.services.CatalogueService;
import com.nexus.services.ServiceFinder;
import com.nexus.web.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class ResetShoppingCartAction extends ActionSupport implements ServletRequestAware {
   Logger log=Logger.getLogger(ResetShoppingCartAction.class);
    private CatalogueService catalogueService;
    private AccountService accountService;
    private String comment;
    private Date deliveryDate;
    private String orderNumber;
    private String autoOrderNumber;
    private HttpServletRequest request;
   public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
    public String execute() throws Exception {

        ActionContext ac = ActionContext.getContext();
        String supId = (String) ac.getSession().get(Constant.SUPID);
        String id = (String) ac.getSession().get(Constant.ID);
        SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String orderDate = dateFormatter.format(new Date());
        deliveryDate=new Date();
        PartnerLink partnerLink=catalogueService.getOrderComment(id, supId);
        //comment="rio tinto";
        comment=partnerLink.getBuyerName();
        log.info("id.."+id+"..supId.."+supId+"..deliveryDate..."+deliveryDate+"..comment.."+comment);
        Map session = ac.getSession();
        Order order = null;
        Object o = session.get("order");
        if (o == null) {
            order = catalogueService.createOrder(id, supId, deliveryDate, comment);
            session.put("order", order);
        } else {
            order = (Order) o;
        }
        orderNumber = order.getOrderNo();
        autoOrderNumber = order.getOrderNo();
        log.info("..orderNumber.."+orderNumber+"..autoOrderNumber.."+autoOrderNumber);

        List<ShoppingCartItem> sci = (List<ShoppingCartItem>) ac.getSession().get("shoppingcart");

        catalogueService.createOrderItemsForShoppingCart(sci, new Integer(id).intValue(), new Integer(supId).intValue(), orderNumber, autoOrderNumber, deliveryDate, comment);
        String company = (String) session.get("mycompany");
        String clientname=session.get("loginpage").toString();
        log.info("clientName in..."+clientname+"..company.."+company+"orderNumber in...");
        sendOrderMail(company, orderNumber, orderDate,supId,id,clientname);


         session.remove("shoppingcart");
         session.remove("order");
         session.remove("deliveryDate");
         session.remove("comment");

         HttpSession session1 = request.getSession(false);
            Object obj = session1.getAttribute("microcat");
            if (obj != null) {
                Microcat cat = (Microcat) obj;
                log.info("microcat obj not null.."+cat.getId());
                getAccountService().updateMicrocatStatus(cat.getId());
            }else{
             log.info("microcat obj null..");
            }
            
            
            if (session != null) {
                session.clear();
            }
         return "resetCart";
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
     * @return the accountService
     */
    public AccountService getAccountService() {
        return accountService;
    }

    /**
     * @param accountService the accountService to set
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

   

}
