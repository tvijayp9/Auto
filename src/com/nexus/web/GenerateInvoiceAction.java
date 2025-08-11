/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nexus.domain.InvoiceLineItem;
import com.nexus.services.CatalogueService;
import com.nexus.services.TransactionService;
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.apache.log4j.Logger;
import com.nexus.domain.OrderAddressData;
import com.nexus.services.ServiceFinder;
import com.opensymphony.xwork2.ActionContext;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.struts2.interceptor.ServletRequestAware;


/**
 *
 * @author Vijay Thumma
 */
public class GenerateInvoiceAction extends ActionSupport implements ServletRequestAware{

    Logger log=Logger.getLogger(GenerateInvoiceAction.class);
    private HttpServletRequest request;
    private String messageId;
    private String company;
    private TransactionService transactionService;
    private CatalogueService catalogueService;
    private String customerCode;
    private String orderNumber;
    private String comment;
    private Date deliveryDate;
    private String invoiceNo;
    private String orderNo;
    private BigDecimal totalPrice= new BigDecimal(0);
    private BigDecimal totalTax= new BigDecimal(0);
    private BigDecimal totalCost= new BigDecimal(0);
    private String invoiceCXML = null;
    private OrderAddressData orderAddressData;
    private String[] productCode;
    
    public String execute() throws SQLException, ParseException, Exception {
        String result = null;

        String toDomain = "DUNS";
        String toIdentity = "757539440";//Coupa identity for integration
        String fromDomain = "Auto";
        String fromIdentity = "ausdrill_admin";
        String sharedSecret = "adminin";
        String payloadId = null;
        String timestmp = null;
        String invoiceOutFolder = "";
        String invoiceFilePath = "";
        BufferedWriter out = null;
        String itemDescription=null;
        String invoiceCoupaURL="https://ausdrill.coupahost.com/cxml/invoices";
        Integer statusCode=0;
//        int lineNo = 0;
        log.info("invoiceNo=" + invoiceNo + "...orderNumber=" + orderNumber);
        try {
            ActionContext ac = ActionContext.getContext();
            Map session = ac.getSession();
            com.nexus.dao.SpringHibernateDAO partnerDao = (com.nexus.dao.SpringHibernateDAO) ServiceFinder.getContext(request).getBean("SpringHibernateDao");
            invoiceOutFolder = partnerDao.getFolderPath("ausdrill_invoice");

            List<InvoiceLineItem> invoiceList = (List<InvoiceLineItem>) session.get("invoicecart");
            log.info("size=" + invoiceList.size());
//            catalogueService.addInvoiceNotoList(invoiceNo, productCode, invoiceList);
            



            Integer pcode=0;
        for (String productCodeElement : productCode) {
            for (InvoiceLineItem invoiceLineItem : invoiceList) {
                log.info("1productCodeElement=="+Integer.parseInt(productCodeElement)+"....."+invoiceLineItem.getLineItemId()+"...line item type.."+invoiceLineItem.getLineItemType()+"..invoice line item id"+invoiceLineItem.getInvoiceLineItemId());
                if(invoiceLineItem.getLineItemType().equals("order"))
                    pcode=invoiceLineItem.getLineItemId();
                else
                    pcode=invoiceLineItem.getInvoiceLineItemId();
                log.info("pcode=="+pcode);        
                if (Integer.parseInt(productCodeElement)==pcode) {
                    invoiceLineItem.setInvoiceNo(invoiceNo);
                    invoiceLineItem.setInvoiced(true);
                     log.info("2productCodeElement=="+productCodeElement+"....."+invoiceLineItem.getId());
                    break;
                }
            }
        }

            
            catalogueService.createInvoiceItems(invoiceList);

            Random random = new Random();
            int randomNumber = random.nextInt(10000000);
            payloadId = randomNumber + "Auto";
            timestmp = getDate();
            deliveryDate = new Date();

            setInvoiceCXML("<?xml version=" + "\"" + "1.0" + "\"" + " encoding=" + "\"" + "UTF-8" + "\"" + "?>");
            setInvoiceCXML(getInvoiceCXML() + "<!DOCTYPE cXML SYSTEM " + "\"" + "http://xml.cXML.org/schemas/cXML/1.2.020/InvoiceDetail.dtd" + "\"" + ">");
            setInvoiceCXML(getInvoiceCXML() + " <cXML version=" + "\"" + "1.2.014" + "\"" + " payloadID=" + "\"" + payloadId + "\"" + " timestamp=" + "\"" + timestmp + "\"" + ">");
            setInvoiceCXML(getInvoiceCXML() + "<Header>");
            setInvoiceCXML(getInvoiceCXML() + "<From>");
            setInvoiceCXML(getInvoiceCXML() + "<Credential domain=" + "\"" + fromDomain + "\"" + ">");
            setInvoiceCXML(getInvoiceCXML() + "<Identity>" + fromIdentity + "</Identity>");
            setInvoiceCXML(getInvoiceCXML() + "</Credential>");
            setInvoiceCXML(getInvoiceCXML() + "</From>");
            setInvoiceCXML(getInvoiceCXML() + "<To>");
            setInvoiceCXML(getInvoiceCXML() + "<Credential domain=" + "\"" + toDomain + "\"" + ">");
            setInvoiceCXML(getInvoiceCXML() + "<Identity>" + toIdentity + "</Identity>");
            setInvoiceCXML(getInvoiceCXML() + "</Credential>");
            setInvoiceCXML(getInvoiceCXML() + "</To>");
            setInvoiceCXML(getInvoiceCXML() + "<Sender>");
            setInvoiceCXML(getInvoiceCXML() + "<Credential domain=" + "\"" + fromDomain + "\"" + ">");
            setInvoiceCXML(getInvoiceCXML() + "<Identity>" + fromIdentity + "</Identity>");
            setInvoiceCXML(getInvoiceCXML() + "<SharedSecret>" + sharedSecret + "</SharedSecret>");
            setInvoiceCXML(getInvoiceCXML() + "</Credential>");
            setInvoiceCXML(getInvoiceCXML() + "<UserAgent/>");
            setInvoiceCXML(getInvoiceCXML() + "</Sender>");
            setInvoiceCXML(getInvoiceCXML() + "</Header>");
           // setInvoiceCXML(getInvoiceCXML() + "<Request deploymentMode=" + "\"" + "production" + "\"" + ">");
            setInvoiceCXML(getInvoiceCXML() + "<Request deploymentMode=" + "\"" + "test" + "\"" + ">");
            setInvoiceCXML(getInvoiceCXML() + "<InvoiceDetailRequest>");
            setInvoiceCXML(getInvoiceCXML() + "<InvoiceDetailRequestHeader invoiceID=" + "\"" + invoiceNo + "\"" + " purpose=" + "\"" + "standard" + "\"" + " operation=" + "\"" + "new" + "\"" + " invoiceDate=" + "\"" + timestmp + "\"" + ">");
            setInvoiceCXML(getInvoiceCXML() + " <InvoiceDetailHeaderIndicator/>");
            setInvoiceCXML(getInvoiceCXML() + "<InvoiceDetailLineIndicator isDiscountInLine=" + "\"" + "yes" + "\"" + "/>");
            setInvoiceCXML(getInvoiceCXML() + "<Extrinsic name=" + "\"" + "CustomFields" + "\"" + ">");
            setInvoiceCXML(getInvoiceCXML() + "<IdReference domain=" + "\"" + "CustomField" + "\"" + " identifier=" + "\"" + "official_tax_invoice_attachment" + "\"" + ">");
            setInvoiceCXML(getInvoiceCXML() + "<Description xml:lang=" + "\"en" + "\">" + "TRUE" + "</Description>");
            setInvoiceCXML(getInvoiceCXML() + "</IdReference>");
            setInvoiceCXML(getInvoiceCXML() + "</Extrinsic>");
            setInvoiceCXML(getInvoiceCXML() + "</InvoiceDetailRequestHeader>");
            setInvoiceCXML(getInvoiceCXML() + "<InvoiceDetailOrder>");
            setInvoiceCXML(getInvoiceCXML() + "<InvoiceDetailOrderInfo>");
            setInvoiceCXML(getInvoiceCXML() + "<OrderReference orderID=" + "\"" + orderNumber + "\"" + ">");
            setInvoiceCXML(getInvoiceCXML() + "<DocumentReference payloadID=" + "\"" + "" + "\"" + "/>");
            setInvoiceCXML(getInvoiceCXML() + "</OrderReference>");
            setInvoiceCXML(getInvoiceCXML() + "<SupplierOrderInfo orderID=" + "\"" + orderNumber + "\"" + "/>");
            setInvoiceCXML(getInvoiceCXML() + "</InvoiceDetailOrderInfo>");

            for (InvoiceLineItem invoiceLineItem : invoiceList) {
                if (invoiceLineItem.isInvoiced()) {
//                    lineNo++;
                    totalPrice = totalPrice.add(invoiceLineItem.getPrice());
                    totalTax = totalTax.add(invoiceLineItem.getTax());
                    totalCost = totalCost.add(invoiceLineItem.getCost());
//                    setInvoiceCXML(getInvoiceCXML() + "<InvoiceDetailItem invoiceLineNumber=" + "\"" + lineNo + "\"" + " quantity=" + "\"" + invoiceLineItem.getQuantity() + "\"" + ">");
                    setInvoiceCXML(getInvoiceCXML() + "<InvoiceDetailItem invoiceLineNumber=" + "\"" + invoiceLineItem.getLineNo() + "\"" + " quantity=" + "\"" + invoiceLineItem.getQuantity() + "\"" + ">");
                    setInvoiceCXML(getInvoiceCXML() + "<UnitOfMeasure>" + "EA" + "</UnitOfMeasure>");
                    setInvoiceCXML(getInvoiceCXML() + "<UnitPrice>");
                    setInvoiceCXML(getInvoiceCXML() + "<Money currency=" + "\"AUD" + "\">" + invoiceLineItem.getUnitprice() + "</Money>");
                    setInvoiceCXML(getInvoiceCXML() + "</UnitPrice>");
//                    setInvoiceCXML(getInvoiceCXML() + "<InvoiceDetailItemReference lineNumber=" + "\"" + lineNo + "\"" + ">");
                    setInvoiceCXML(getInvoiceCXML() + "<InvoiceDetailItemReference lineNumber=" + "\"" + invoiceLineItem.getLineNo() + "\"" + ">");
                    setInvoiceCXML(getInvoiceCXML() + "<ItemID>");
                    setInvoiceCXML(getInvoiceCXML() + "<SupplierPartID>" + invoiceLineItem.getProductCode() + "</SupplierPartID>");
                    setInvoiceCXML(getInvoiceCXML() + "</ItemID>");
                    
                    itemDescription=invoiceLineItem.getDescription(); //when item description contains '&', Coupa is throwing an error.Hence we are replacing '&' with space. 2017/03/17
                    if(itemDescription.contains("&"))
                        itemDescription=itemDescription.replace('&',' ');
                    
                    setInvoiceCXML(getInvoiceCXML() + "<Description xml:lang=" + "\"en" + "\">" + itemDescription + "</Description>");
                    setInvoiceCXML(getInvoiceCXML() + "</InvoiceDetailItemReference>");
                    setInvoiceCXML(getInvoiceCXML() + "<SubtotalAmount>");
                    setInvoiceCXML(getInvoiceCXML() + "<Money currency=" + "\"AUD" + "\">" + invoiceLineItem.getPrice() + "</Money>");
                    setInvoiceCXML(getInvoiceCXML() + "</SubtotalAmount>");
                    setInvoiceCXML(getInvoiceCXML() + "<Tax>");
                    setInvoiceCXML(getInvoiceCXML() + "<Money currency=" + "\"AUD" + "\">" + invoiceLineItem.getTax() + "</Money>");
                    setInvoiceCXML(getInvoiceCXML() + "<Description xml:lang=" + "\"en" + "\">" + "GST" + "</Description>");
                    setInvoiceCXML(getInvoiceCXML() + "<TaxDetail category=" + "\"GST\"" + " purpose=" + "\"tax\"" + " percentageRate=" + "\"10.00\"" + ">");
                    setInvoiceCXML(getInvoiceCXML() + "<TaxAmount>");
                    setInvoiceCXML(getInvoiceCXML() + "<Money currency=" + "\"AUD" + "\">" + invoiceLineItem.getTax() + "</Money>");
                    setInvoiceCXML(getInvoiceCXML() + "</TaxAmount>");
                    setInvoiceCXML(getInvoiceCXML() + "</TaxDetail>");
                    setInvoiceCXML(getInvoiceCXML() + "</Tax>");
                    setInvoiceCXML(getInvoiceCXML() + "<GrossAmount>");
                    setInvoiceCXML(getInvoiceCXML() + "<Money currency=" + "\"AUD" + "\">" + invoiceLineItem.getCost() + "</Money>");
                    setInvoiceCXML(getInvoiceCXML() + "</GrossAmount>");
                    setInvoiceCXML(getInvoiceCXML() + "<InvoiceDetailDiscount>");
                    setInvoiceCXML(getInvoiceCXML() + "<Money currency=" + "\"AUD" + "\">" + "0.00" + "</Money>");
                    setInvoiceCXML(getInvoiceCXML() + "</InvoiceDetailDiscount>");
                    setInvoiceCXML(getInvoiceCXML() + "<NetAmount>");
                    setInvoiceCXML(getInvoiceCXML() + "<Money currency=" + "\"AUD" + "\">" + invoiceLineItem.getCost() + "</Money>");
                    setInvoiceCXML(getInvoiceCXML() + "</NetAmount>");
                    setInvoiceCXML(getInvoiceCXML() + "<Comments/>");
                    setInvoiceCXML(getInvoiceCXML() + "<Extrinsic name=" + "\"LineType" + "\">" + "Item" + "</Extrinsic>");
                    setInvoiceCXML(getInvoiceCXML() + "</InvoiceDetailItem>");
                    invoiceLineItem.setInvoiced(false);
                }
            }
            setInvoiceCXML(getInvoiceCXML() + "</InvoiceDetailOrder>");
            setInvoiceCXML(getInvoiceCXML() + "<InvoiceDetailSummary>");
            setInvoiceCXML(getInvoiceCXML() + "<SubtotalAmount>");
            setInvoiceCXML(getInvoiceCXML() + "<Money currency=" + "\"AUD" + "\">" + totalPrice + "</Money>");
            setInvoiceCXML(getInvoiceCXML() + "</SubtotalAmount>");
            setInvoiceCXML(getInvoiceCXML() + "<Tax>");
            setInvoiceCXML(getInvoiceCXML() + "<Money currency=" + "\"AUD" + "\">" + totalTax + "</Money>");
            setInvoiceCXML(getInvoiceCXML() + "<Description xml:lang=" + "\"" + "\">" + "TotalTax" + "</Description>");
            setInvoiceCXML(getInvoiceCXML() + "<TaxDetail category=" + "\"GST" + "\"" + " purpose=" + "\"tax" + "\"" + " percentageRate=" + "\"10.00" + "\"" + ">");
            setInvoiceCXML(getInvoiceCXML() + "<TaxableAmount>");
            setInvoiceCXML(getInvoiceCXML() + "<Money currency=" + "\"AUD" + "\">" + totalPrice + "</Money>");
            setInvoiceCXML(getInvoiceCXML() + "</TaxableAmount>");
            setInvoiceCXML(getInvoiceCXML() + "<TaxAmount>");
            setInvoiceCXML(getInvoiceCXML() + "<Money currency=" + "\"AUD" + "\">" + totalTax + "</Money>");
            setInvoiceCXML(getInvoiceCXML() + "</TaxAmount>");
            setInvoiceCXML(getInvoiceCXML() + "</TaxDetail>");
            setInvoiceCXML(getInvoiceCXML() + "</Tax>");
            setInvoiceCXML(getInvoiceCXML() + "<GrossAmount>");
            setInvoiceCXML(getInvoiceCXML() + "<Money currency=" + "\"AUD" + "\">" + totalCost + "</Money>");
            setInvoiceCXML(getInvoiceCXML() + "</GrossAmount>");
            setInvoiceCXML(getInvoiceCXML() + "<NetAmount>");
            setInvoiceCXML(getInvoiceCXML() + "<Money currency=" + "\"AUD" + "\">" + totalCost + "</Money>");
            setInvoiceCXML(getInvoiceCXML() + "</NetAmount>");
            setInvoiceCXML(getInvoiceCXML() + "</InvoiceDetailSummary>");
            setInvoiceCXML(getInvoiceCXML() + "</InvoiceDetailRequest>");
            setInvoiceCXML(getInvoiceCXML() + "</Request>");
            setInvoiceCXML(getInvoiceCXML() + "</cXML>");
            ac.getSession().put("invoiceCXML", invoiceCXML);
            log.info("invoice response==" + invoiceCXML);
            SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
            String orderDate = dateFormatter.format(new Date());
            //invoiceFilePath = invoiceOutFolder + "\\" + invoiceNo + "_" + dateFormatter.format(new Date()) + ".xml";
            invoiceFilePath = invoiceOutFolder + "/" + invoiceNo + "_" + dateFormatter.format(new Date()) + ".xml";// For Linux version
            out = new BufferedWriter(new FileWriter(invoiceFilePath));
            out.write(invoiceCXML);
            statusCode=postCXMLInvoice(invoiceCoupaURL, invoiceFilePath,invoiceNo);
             log.info("statusCode="+statusCode);
            if(statusCode!=200){
                log.info("Ausdrill invoice has failed to post Coupa system.Invoice number="+invoiceNo);
                sendInvoiceFailureEMail(invoiceNo);
            }
            session.remove("invoicecart");
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }finally {
            out.close();
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
    
    public Integer postCXMLInvoice(String url,String filename,String invoiceNo) throws Exception{
        int result =0;
        File input = new File(filename);
        PostMethod post = new PostMethod(url);
        post.setRequestEntity(new InputStreamRequestEntity(new FileInputStream(input), input.length()));
        post.setRequestHeader("Content-type", "application/xml; charset=ISO-8859-1");
        HttpClient httpclient = new HttpClient();

        try {
            System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
                result = httpclient.executeMethod(post);
                log.info("Response status code: " + result);
                 log.info("Response body: "+post.getResponseBodyAsString());
        } catch(Exception ex){
           ex.printStackTrace();
        }finally {
            post.releaseConnection();
        }
        return result;
    }
    
    public void sendInvoiceFailureEMail(String invoiceNo) {
        try {
            com.nexus.web.common.SendMail mailBean = (com.nexus.web.common.SendMail) ServiceFinder.getContext(request).getBean(com.nexus.web.common.ProjectConstants.MAIL_BEAN);
            String reciepent[] = {"vthumma@ivbplus.com.au"};

            String emailsubject = " Ausdrill Invoice Failed with the Invoice No " + invoiceNo;
            String emailmessage = "Mr/Mrs,";
            emailmessage += "\n\n Ausdrill Invoice " + invoiceNo + " has failed to send to Coupa System . Please take the necessary action.";

            emailmessage += "\n\n Best regards,";
            emailmessage += "\n\n Nexus Support Team";
            String from = com.nexus.web.common.ProjectConstants.FROM_MAIL;
            mailBean.sendMail(reciepent, emailsubject, emailmessage, from);
            log.info("Invoice failure mail sent ");
        } catch (MessagingException ex) {
            log.error("Exception Message:", ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
 public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

   

    /**
     * @return the orderNumber
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * @return the transactionService
     */
   // @JSON(serialize = false)
    public TransactionService getTransactionService() {
        return transactionService;
    }

    /**
     * @param transactionService the transactionService to set
     */
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * @return the customerCode
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * @param customerCode the customerCode to set
     */
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    /**
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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
     * @return the orderAddressData
     */
    public OrderAddressData getOrderAddressData() {
        return orderAddressData;
    }

    /**
     * @param orderAddressData the orderAddressData to set
     */
    public void setOrderAddressData(OrderAddressData orderAddressData) {
        this.orderAddressData = orderAddressData;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the invoiceNo
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * @param invoiceNo the invoiceNo to set
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * @return the orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo the orderNo to set
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
     * @return the deliveryDate
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param deliveryDate the deliveryDate to set
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * @return the invoiceCXML
     */
    public String getInvoiceCXML() {
        return invoiceCXML;
    }

    /**
     * @param invoiceCXML the invoiceCXML to set
     */
    public void setInvoiceCXML(String invoiceCXML) {
        this.invoiceCXML = invoiceCXML;
    }
    
    /**
     * @return the productId
     */
    @JSON(serialize = false)
    public String[] getProductCode() {
        return productCode;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductCode(String[] productCode) {
        this.productCode = productCode;
    }
}
