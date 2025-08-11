/**
 *
 * @author Vijay Thumma
 */

package com.nexus.web.Catalogue;

import com.nexus.domain.Order;
import com.nexus.domain.PartnerLink;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.domain.SubTotal;
import com.nexus.services.AccountService;
import com.nexus.services.CatalogueService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class CXMLPunchOutAction extends ActionSupport implements ServletRequestAware{

    Logger log=Logger.getLogger(CXMLPunchOutAction.class);
    private CatalogueService catalogueService;
    private AccountService accountService;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;
    private BigDecimal totalCost;
    private List<ShoppingCartItem> result;
    private int count;
    String id;
    String supplierId;
    String punchout;
    private String comment;
    private Date deliveryDate;
    private String orderNumber;
    private String autoOrderNumber;
    private String strCXML = null;
     private HttpServletRequest request;
   public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public String execute() throws SQLException, IOException, Exception {
        String result = null;

        String toDomain = "DUNS";
        String fromDomain = "Auto";
        String toIdentity = "user@coupa.com";
        String buyerCookie = null;
        String payloadId = null;
        String timestmp = null;
        String postURL = null;
        String itemDescription=null;
        ActionContext ac = ActionContext.getContext();
        List<ShoppingCartItem> sci = (List<ShoppingCartItem>) ac.getSession().get("shoppingcart");
        id = (String) ac.getSession().get("ID");
        supplierId = (String) ac.getSession().get("supplierid");
        buyerCookie = (String) ac.getSession().get("buyerCoockie");
        postURL = (String) ac.getSession().get("fromURL");
        count = sci.size();
        SubTotal subtotal = catalogueService.getSubtotal(sci);
        totalPrice = subtotal.getTotalPrice();
        totalTax = subtotal.getTotalTax();
        totalCost = subtotal.getTotalCost();

        Random random = new Random();
        int randomNumber = random.nextInt(10000000);
        payloadId = randomNumber + "Auto";
        timestmp = getDate();

        System.out.println("CXMLPunchOutAction  supplierId==" + supplierId + "...buyerCookie==" + buyerCookie);

        deliveryDate = new Date();
        PartnerLink partnerLink = catalogueService.getOrderComment(id, supplierId);
        comment = partnerLink.getBuyerName();
        log.info("id.." + id + "..supId.." + supplierId + "..deliveryDate..." + deliveryDate + "..comment.." + comment);
        Map session = ac.getSession();
        Order order = null;
        Object o = session.get("order");
        if (o == null) {
            order = catalogueService.createOrderQuote(id, supplierId, deliveryDate, comment);
            session.put("order", order);
        } else {
            order = (Order) o;
        }
        orderNumber = order.getOrderNo();
        autoOrderNumber = order.getOrderNo();
        log.info("..orderNumber.." + orderNumber + "..autoOrderNumber.." + autoOrderNumber);
        catalogueService.createOrderQuoteItemsForShoppingCart(sci, new Integer(id).intValue(), new Integer(supplierId).intValue(), orderNumber, autoOrderNumber, deliveryDate, comment);


        setStrCXML("<?xml version=" + "\"" + "1.0" + "\"" + " encoding=" + "\"" + "UTF-8" + "\"" + "?>");
        setStrCXML(getStrCXML() + "<!DOCTYPE cXML SYSTEM " + "\"" + "http://xml.cxml.org/schemas/cXML/1.2.023/cXML.dtd" + "\"" + ">");
        setStrCXML(getStrCXML() + " <cXML payloadID=" + "\"" + payloadId + "\"" + " timestamp=" + "\"" + timestmp + "\"" + ">");
        setStrCXML(getStrCXML() + "<Header>");
        setStrCXML(getStrCXML() + "<From>");
        setStrCXML(getStrCXML() + "<Credential domain=" + "\"" + fromDomain + "\"" + ">");
        setStrCXML(getStrCXML() + "<Identity/>");
        setStrCXML(getStrCXML() + "</Credential>");
        setStrCXML(getStrCXML() + "</From>");
        setStrCXML(getStrCXML() + "<To>");
        setStrCXML(getStrCXML() + "<Credential domain=" + "\"" + toDomain + "\"" + ">");
        setStrCXML(getStrCXML() + "<Identity>" + toIdentity + "</Identity>");
        setStrCXML(getStrCXML() + "</Credential>");
        setStrCXML(getStrCXML() + "</To>");
        setStrCXML(getStrCXML() + "<Sender>");
        setStrCXML(getStrCXML() + "<Credential domain=" + "\"" + fromDomain + "\"" + ">");
        setStrCXML(getStrCXML() + "<Identity/>");
        setStrCXML(getStrCXML() + "</Credential>");
        setStrCXML(getStrCXML() + "<UserAgent/>");
        setStrCXML(getStrCXML() + "</Sender>");
        setStrCXML(getStrCXML() + "</Header>");

        setStrCXML(getStrCXML() + "<Message>");

        setStrCXML(getStrCXML() + "<PunchOutOrderMessage>");
        setStrCXML(getStrCXML() + "<BuyerCookie>" + buyerCookie + "</BuyerCookie>");

        setStrCXML(getStrCXML() + "<PunchOutOrderMessageHeader operationAllowed=" + "\"edit\">");
        setStrCXML(getStrCXML() + "<Total>");
        setStrCXML(getStrCXML() + "<Money currency=" + "\"AUD" + "\">" + totalPrice + "</Money>");
        setStrCXML(getStrCXML() + "</Total>");

        setStrCXML(getStrCXML() + "<Shipping>");
        setStrCXML(getStrCXML() + "<Money currency=" + "\"AUD" + "\">" + "0.0" + "</Money>");
        setStrCXML(getStrCXML() + "<Description xml:lang=" + "\"en-US" + "\">" + "Unknown" + "</Description>");
        setStrCXML(getStrCXML() + "</Shipping>");

        setStrCXML(getStrCXML() + "<Tax>");
        setStrCXML(getStrCXML() + "<Money currency=" + "\"AUD" + "\">" + totalTax + "</Money>");
        setStrCXML(getStrCXML() + "<Description xml:lang=" + "\"en-US" + "\">" + "Unknown" + "</Description>");
        setStrCXML(getStrCXML() + "</Tax>");

        setStrCXML(getStrCXML() + "</PunchOutOrderMessageHeader>");

        for (ShoppingCartItem shoppingCartItem : sci) {
            setStrCXML(getStrCXML() + "<ItemIn quantity=" + "\"" + shoppingCartItem.getQuantity() + "\"" + ">");

            setStrCXML(getStrCXML() + "<ItemID>");
            setStrCXML(getStrCXML() + "<SupplierPartID>" + shoppingCartItem.getProductCode() + "</SupplierPartID>");
            setStrCXML(getStrCXML() + "<SupplierPartAuxiliaryID>" + orderNumber + "</SupplierPartAuxiliaryID>");
            setStrCXML(getStrCXML() + "</ItemID>");
            setStrCXML(getStrCXML() + "<ItemDetail>");
            setStrCXML(getStrCXML() + "<UnitPrice>");
            setStrCXML(getStrCXML() + "<Money currency=" + "\"AUD" + "\">" + shoppingCartItem.getUnitPrice() + "</Money>");
            itemDescription=shoppingCartItem.getDescription(); //when item description contains '&', Coupa is throwing an error.Hence we are replacing '&' with space. 2017/03/17
            if(itemDescription.contains("&"))
                itemDescription=itemDescription.replace('&',' ');
            setStrCXML(getStrCXML() + "</UnitPrice>");
            setStrCXML(getStrCXML() + "<Description xml:lang=" + "\"en-US" + "\">" + itemDescription + "</Description>");
            setStrCXML(getStrCXML() + "<UnitOfMeasure>" + "EA" + "</UnitOfMeasure>");
            setStrCXML(getStrCXML() + "<Classification domain=" + "\"" + fromDomain + "\"" + ">" + orderNumber + "</Classification>");

            setStrCXML(getStrCXML() + "<ManufacturerName/>");
            setStrCXML(getStrCXML() + "<LeadTime>" + 0 + "</LeadTime>");

            setStrCXML(getStrCXML() + "</ItemDetail>");
            setStrCXML(getStrCXML() + "</ItemIn>");
        }

        setStrCXML(getStrCXML() + "</PunchOutOrderMessage>");
        setStrCXML(getStrCXML() + "</Message>");
        setStrCXML(getStrCXML() + "</cXML>");

//        out.write(strCXML);
        ac.getSession().put("strCXML", strCXML);
        log.info("response==" + strCXML);

        result = "cxmlpunchOut";
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

    /**
     * @return the result
     */
    public List<ShoppingCartItem> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(List<ShoppingCartItem> result) {
        this.result = result;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
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

    /**
     * @return the strCXML
     */
    public String getStrCXML() {
        return strCXML;
    }

    /**
     * @param strCXML the strCXML to set
     */
    public void setStrCXML(String strCXML) {
        this.strCXML = strCXML;
    }
}
