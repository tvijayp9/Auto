/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vijay Thumma
 */
package com.nexus.web.Catalogue;

import com.nexus.domain.Order;
import com.nexus.domain.OrderAddressData;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.domain.User;
import com.nexus.services.CatalogueService;
import com.nexus.services.ServiceFinder;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.*;
import org.apache.log4j.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.mail.MessagingException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class PurchaseOrderAction extends ActionSupport implements ServletRequestAware {

    Logger log = Logger.getLogger(PurchaseOrderAction.class);
    private CatalogueService catalogueService;
    private HttpServletRequest request;
    private HttpSession session = null;
    private SAXBuilder _xmlBuilder;
    private OrderAddressData orderAddressData;
    private String username = null;
    private String password = null;

    public String execute() throws Exception {
        com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
        String result = null;
        String strCXML = null;

        String strTimeStamp = null;
        String attrval = null;

        String orderNumber = null;
        String orderDate = null;
        String totalMoney = null;
        String shipToAddressId = null;
        String shipToName = null;
        String shipToDelivery = null;
        String shipToStreet = null;
        String shipToCity = null;
        String shipToState = null;
        String shipToPOCode = null;
        String shipToCountry = null;
        String shipToEmail = null;
        String billToName = null;
        String billToDelivery = null;
        String billToStreet = null;
        String billToCity = null;
        String billToState = null;
        String billToPOCode = null;
        String billToCountry = null;
        String headerLevelComment = null;
        String shippingComment = null;

        String lineNo = null;
        String qty = null;
        String deliverydate =null;
        String supPartId = null;
        String supPartAuxId = null;
        String unitPrice = null;
        String desc = null;
        String uom = null;
        String linelevelcomment =null;
        String billingName = null;
        String backupFolder = null;
        Document doc = null;
        int supplierId = 0;
        int buyerId = 0;
        List<Element> list = new ArrayList<Element>();
        List cxmllist=null;
        ShoppingCartItem shoppingCartItem=null;
        try {

            User user = partnerDao.checkUserLogin(getUsername(), getPassword());
            if (user != null) {
                session = request.getSession();
                buyerId = user.getNexusId();
                supplierId = catalogueService.getSupplierIdById(user.getNexusId());

                _xmlBuilder = new SAXBuilder();
                session = request.getSession();
                log.info("inside purchase order action=");
                ServletInputStream httpIn = request.getInputStream();

                Random random = new Random();
                int randomNumber = random.nextInt(10000000);
                attrval = randomNumber + "tty";
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setContentType("application/xml");
                PrintWriter out = response.getWriter();
                strTimeStamp = getDate();

                _xmlBuilder.setReuseParser(false);
                doc = _xmlBuilder.build(httpIn);
                Element root = doc.getRootElement();
                log.info("root value=" + root);


                orderNumber = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getAttributeValue("orderID");
                orderDate = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getAttributeValue("orderDate");
                totalMoney = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("Total").getChild("Money").getValue();
                shipToAddressId=root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("ShipTo").getChild("Address").getAttributeValue("addressID");
                shipToName = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("ShipTo").getChild("Address").getChild("Name").getValue();
                shipToDelivery = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("ShipTo").getChild("Address").getChild("PostalAddress").getChild("DeliverTo").getValue();
                shipToStreet = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("ShipTo").getChild("Address").getChild("PostalAddress").getChild("Street").getValue();
                shipToCity = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("ShipTo").getChild("Address").getChild("PostalAddress").getChild("City").getValue();
                shipToState = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("ShipTo").getChild("Address").getChild("PostalAddress").getChild("State").getValue();
                shipToPOCode = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("ShipTo").getChild("Address").getChild("PostalAddress").getChild("PostalCode").getValue();
                shipToCountry = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("ShipTo").getChild("Address").getChild("PostalAddress").getChild("Country").getValue();
                shipToEmail = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("ShipTo").getChild("Address").getChild("Email").getValue();

                billToName = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("BillTo").getChild("Address").getChild("Name").getValue();
                billToDelivery = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("BillTo").getChild("Address").getChild("PostalAddress").getChild("DeliverTo").getValue();
                billToStreet = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("BillTo").getChild("Address").getChild("PostalAddress").getChild("Street").getValue();
                billToCity = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("BillTo").getChild("Address").getChild("PostalAddress").getChild("City").getValue();
                billToState = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("BillTo").getChild("Address").getChild("PostalAddress").getChild("State").getValue();
                billToPOCode = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("BillTo").getChild("Address").getChild("PostalAddress").getChild("PostalCode").getValue();
                billToCountry = root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("BillTo").getChild("Address").getChild("PostalAddress").getChild("Country").getValue();
//                headerLevelComment=root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("Extrinsic").getAttributeValue("name");
                headerLevelComment=root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("Extrinsic").getValue();
                shippingComment=root.getChild("Request").getChild("OrderRequest").getChild("OrderRequestHeader").getChild("Shipping").getChild("Description").getValue();
                if(shippingComment.contains("Toll IPEC (Overnight)")){
                    if(orderNumber.startsWith("ASL")){
                        shippingComment="Please use Toll Online to book collection with Toll IPEC, account number 71G255. Use "+ orderNumber.split("-")[1]+" as the reference number. Any problems please contact Shared Services on (08) 9311 5733.";
                    }else if(orderNumber.startsWith("ANW")){
                        shippingComment="Please use Toll Online to book collection with Toll IPEC, account number YW2444. Use "+ orderNumber.split("-")[1]+" as the reference number. Any problems please contact Shared Services on (08) 9311 5733.";
                    }
                }else if(shippingComment.contains("Toll Express / NQX (General Freight)")){  //New Requirement come from Ausdrill for this condition.2017/03/17
                    if(orderNumber.startsWith("ASL")){
                        shippingComment="Please use Toll Online to book collection with Toll Express / NQX, account number 46427. Use "+ orderNumber.split("-")[1]+" as the reference number.";
                    }
                }        
                log.info("...orderNumber==" + orderNumber + "..orderDate==" + orderDate + "...totalMoney==" + totalMoney+"...headerLevelComment=="+headerLevelComment+"...shippingComment==="+shippingComment);
                log.info("...shipToName==" + shipToName + "..shipToDelivery==" + shipToDelivery + "...shipToStreet==" + shipToStreet + "...shipToCity==" + shipToCity + "..shipToState==" + shipToState + "...shipToPOCode==" + shipToPOCode + "...shipToCountry==" + shipToCountry + "..shipToEmail==" + shipToEmail);
                log.info("...billToName==" + billToName + "..billToDelivery==" + billToDelivery + "...billToStreet==" + billToStreet + "...billToCity==" + billToCity + "..billToState==" + billToState + "...billToPOCode==" + billToPOCode + "...billToCountry==" + billToCountry);
               
                 cxmllist=new ArrayList();
                
                list = root.getChild("Request").getChild("OrderRequest").getChildren();
                for (Element lineItem : list) {
                    if (lineItem.getName().equals("ItemOut")) {
                        shoppingCartItem=new ShoppingCartItem();
                        lineNo = lineItem.getAttributeValue("lineNumber");
                        qty = lineItem.getAttributeValue("quantity");
                        deliverydate = lineItem.getAttributeValue("requestedDeliveryDate");
                        
                        supPartId = lineItem.getChild("ItemID").getChild("SupplierPartID").getValue();
                        supPartAuxId = lineItem.getChild("ItemID").getChild("SupplierPartAuxiliaryID").getValue();
                        unitPrice = lineItem.getChild("ItemDetail").getChild("UnitPrice").getChild("Money").getValue();
                        desc = lineItem.getChild("ItemDetail").getChild("Description").getValue();
                        uom = lineItem.getChild("ItemDetail").getChild("UnitOfMeasure").getValue();
                        linelevelcomment = lineItem.getChild("ItemDetail").getChild("Extrinsic").getValue();
                        billingName=lineItem.getChild("Distribution").getChild("Accounting").getAttributeValue("name");
                        //shoppingCartItem=new ShoppingCartItem(supPartId, desc, new BigDecimal(unitPrice), Integer.parseInt(qty), uom);
                         shoppingCartItem=new ShoppingCartItem(Integer.parseInt(lineNo),supPartId, desc, new BigDecimal(unitPrice), Integer.parseInt(qty), uom,deliverydate,linelevelcomment);
                        cxmllist.add(shoppingCartItem);
                        System.out.println("...lineNo==" + lineNo + "..qty==" + qty + "...supPartId==" + supPartId + "...supPartAuxId==" + supPartAuxId + "..unitPrice==" + unitPrice + "...desc==" + desc + "...uom==" + uom);
                    }
                }
                 orderAddressData = new OrderAddressData(randomNumber,shipToAddressId, shipToName, shipToDelivery, shipToStreet, shipToCity, shipToState, Integer.parseInt(shipToPOCode), shipToCountry, shipToEmail, billToName, billToDelivery, billToStreet, billToCity, billToState, Integer.parseInt(billToPOCode), billToCountry,billingName,headerLevelComment,shippingComment);
                Order order=catalogueService.createCXMLOrder(""+buyerId, ""+supplierId, orderDate.substring(0, orderDate.indexOf("T")), "AusDrill", orderNumber,supPartAuxId, orderAddressData);
                catalogueService.createCXMLOrderItemsForShoppingCart(cxmllist, buyerId, supplierId, orderNumber, orderNumber, orderDate.substring(0, orderDate.indexOf("T")), "AusDrill", order.getId());
               sendOrderMail("AusDrill", orderNumber, orderDate, supplierId);

                backupFolder = partnerDao.getFolderPath("backup");
                XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
                xmlOutput.output(doc, new FileWriter(backupFolder + orderNumber + "_" + getDateTime() + ".xml"));


                strCXML = "<?xml version=" + "\"" + "1.0" + "\"" + " encoding=" + "\"" + "UTF-8" + "\"" + "?>";
                strCXML = strCXML + "<!DOCTYPE cXML SYSTEM " + "\"" + "http://xml.cxml.org/schemas/cXML/1.2.023/cXML.dtd" + "\"" + ">";
                strCXML = strCXML + " <cXML payloadID=" + "\"" + attrval + "\"" + " timestamp=" + "\"" + strTimeStamp + "\"" + ">";
                strCXML = strCXML + "<Response>";
                strCXML = strCXML + "   <Status code=" + "\"" + 200 + "\"" + " text=" + "\"" + "OK" + "\"" + ">" + "</Status>";
                strCXML = strCXML + " </Response>";
                strCXML = strCXML + "</cXML>";

                out.write(strCXML);
            }
        } catch (Exception e) {
            log.error("Exception Message:", e);
            e.printStackTrace();
        }
        return result;
    }

    public String getDate() throws Exception {
        StringBuffer str = null;
        Date d = null;
        try {
            str = new StringBuffer();
            d = new Date();
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
            str.append(dateFormat1.format(d));
            str.append("T");
            str.append(dateFormat2.format(d));
            System.out.println("today=" + str.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    public String getDateTime() throws Exception {
        String str = null;
        Date d = null;
        try {

            d = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            str = dateFormat.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    public void sendOrderMail(String company, String orderNumber, String orderDate,Integer supid) {
        try {
            com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            com.nexus.web.common.SendMail mailBean = (com.nexus.web.common.SendMail) ServiceFinder.getContext(request).getBean(com.nexus.web.common.ProjectConstants.MAIL_BEAN);
            log.info("company.."+company+"..orderNumber.."+orderNumber+"..supid..."+supid);
            String email=partnerDao.findRuleValueMapping("Send Email for new Order", supid);
            String reciepent[] = email.split(",");
//            String[] reciepent = {email};
            String emailsubject = company + " Order " + orderNumber;
            String emailmessage = "Mr/Mrs,";
            emailmessage += "\n\n Order " + orderNumber + " dated " + orderDate + " has arrived from customer " + company + ". Please take the necessary action to fulfil the order.";
            String url = "";
            url = partnerDao.findRuleValueMapping("Supplier Order Url", supid);
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

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
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
}
