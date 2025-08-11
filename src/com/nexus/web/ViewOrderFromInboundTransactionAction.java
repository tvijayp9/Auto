/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.web;

import com.nexus.domain.PurchaseOrder;
import com.nexus.domain.SubTotal;
import com.nexus.services.CatalogueService;
import com.nexus.services.TransactionService;
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.apache.log4j.Logger;
import com.nexus.domain.OrderAddressData;


/**
 *
 * @author Terry
 */
public class ViewOrderFromInboundTransactionAction extends ActionSupport {

    Logger log=Logger.getLogger(ViewOrderFromInboundTransactionAction.class);
    private List items;
    private String messageId;
    private String company;
    private TransactionService transactionService;
    private String customerCode;
    private String orderNumber;
    private String comment;
    private String deliveryDate;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;
    private BigDecimal totalCost;
    private CatalogueService catalogueService;
    private OrderAddressData orderAddressData;


//     private  Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
//      Font.BOLD);
//  private  Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
//      Font.NORMAL, BaseColor.RED);
//  private  Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
//      Font.BOLD);
//  private  Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
//      Font.BOLD);

    public String execute() throws SQLException, ParseException,Exception {
         String result = null;
        PurchaseOrder po = transactionService.getPurchaseOrderFromInboundTransaction(messageId);
         log.info("messageId="+messageId+"...company="+company);
        if (po != null) {
            customerCode = po.getCustomerCode();
            if(company.equalsIgnoreCase("AusDrill")){
                setOrderAddressData(transactionService.getCXMLOrderAddress(Integer.parseInt(messageId)));
                result="AusDrillPrint";
            }else{
                if(customerCode.equalsIgnoreCase("Scania"))
                    result="ScaniaPrint";    
                else
                    result="Print";
            }
            
            orderNumber = po.getOrderNumber();
            comment = po.getComment();
            deliveryDate = po.getDeliveryDate();
            log.info("customerCode...."+customerCode+"..orderNumber..."+orderNumber+"..deliveryDate.."+deliveryDate);
            items = po.getItemList();
            SubTotal subtotal = catalogueService.getSubtotalForPrintOrder(items);
            totalPrice = subtotal.getTotalPrice();
            totalTax = subtotal.getTotalTax();
            totalCost = subtotal.getTotalCost();
            transactionService.updateMessageStatus(messageId, 1);
//            generatePDF();
        }
             return result;
    }
   
//    public void generatePDF()throws Exception{
//
//       Document document=new Document();
//       PdfWriter.getInstance(document,new FileOutputStream("C://Test//data1.pdf"));
//       document.open();
//       addContent(document);
////       PdfPTable  table=new PdfPTable(6);
//
//
////       Anchor anchor = new Anchor("Order Details", catFont);
////    anchor.setName("Order Details");
////
////    // Second parameter is the number of the chapter
////    Chapter catPart = new Chapter(new Paragraph(anchor), 1);
////
////    Paragraph subPara = new Paragraph("Buyer", subFont);
////   Section subCatPart = catPart.addSection(subPara);
////    subCatPart.add(new Paragraph(customerCode));
////
////    subPara = new Paragraph("Purchase Order No", subFont);
////    subCatPart = catPart.addSection(subPara);
////    subCatPart.add(new Paragraph(orderNumber));
////
////       subPara = new Paragraph("Comment", subFont);
////    subCatPart = catPart.addSection(subPara);
////    subCatPart.add(new Paragraph(comment));
////
////      subPara = new Paragraph("Order Delivery Date", subFont);
////    subCatPart = catPart.addSection(subPara);
////    subCatPart.add(new Paragraph(deliveryDate));
//
//
////    document.add(catPart);
////    Paragraph paragraph = new Paragraph();
////    addEmptyLine(paragraph, 5);
////       table.addCell("Product Item No");
////       table.addCell("Product Description");
////       table.addCell("Quantity");
////       table.addCell("Price");
////       table.addCell("Tax");
////       table.addCell("Cost");
////
////       Iterator itr=items.iterator();
////       while(itr.hasNext()){
////          PrintOrder printOrder=(PrintOrder) itr.next();
////          table.addCell(printOrder.getProductCode());
////          table.addCell(printOrder.getDescription());
////          table.addCell(((Integer)printOrder.getQuantity()).toString());
////          table.addCell(((BigDecimal)printOrder.getPrice()).toString());
////          table.addCell(((BigDecimal)printOrder.getTax()).toString());
////          table.addCell(((BigDecimal)printOrder.getCost()).toString());
////
////       }
////
////
////       document.add(table);
//      // document.addSubject(totalPrice.toString());
//      // document.addSubject(totalTax.toString());
//      // document.addSubject(totalCost.toString());
//       document.close();
//   }

//    private static void addEmptyLine(Paragraph paragraph, int number) {
//    for (int i = 0; i < number; i++) {
//      paragraph.add(new Paragraph(" "));
//    }
//  }
//
//     private  void addContent(Document document) throws DocumentException {
//   Anchor anchor = new Anchor("Order Details", catFont);
//    anchor.setName("Order Details");
//
//    // Second parameter is the number of the chapter
//    Chapter catPart = new Chapter(new Paragraph(anchor), 1);
//
//    Paragraph subPara = new Paragraph("Buyer555: "+customerCode, subFont);
//   Section subCatPart = catPart.addSection(subPara);
//
//    subPara = new Paragraph("Purchase Order No: "+orderNumber, subFont);
//    subCatPart = catPart.addSection(subPara);
//
//    subPara = new Paragraph("Comment: "+comment, subFont);
//    subCatPart = catPart.addSection(subPara);
//
//    subPara = new Paragraph("Order Delivery Date: "+deliveryDate, subFont);
//    subCatPart = catPart.addSection(subPara);
//
//
//
//    Paragraph paragraph = new Paragraph();
//    addEmptyLine(paragraph, 2);
//    subCatPart.add(paragraph);
//    createTable(subCatPart);
//
//     Paragraph p1 = new Paragraph("Total Price: $",subFont);
//     p1.add(totalPrice.toString());
//     Paragraph p2 = new Paragraph("Total Tax:$ ",subFont);
//     p2.add(totalTax.toString());
//     Paragraph p3 = new Paragraph("Total Cost: $",subFont);
//     p3.add(totalCost.toString());
//     subCatPart.add(p1);
//     subCatPart.add(p2);
//     subCatPart.add(p3);
//     document.add(catPart);
//
//  }
//
//
//
//     private  void createTable(Section subCatPart)
//      throws BadElementException {
//    PdfPTable  table=new PdfPTable(6);
//    table.setWidthPercentage(100f);
//
//    table.addCell("Product Item No");
//       table.addCell("Product Description");
//       table.addCell("Quantity");
//       table.addCell("Price");
//       table.addCell("Tax");
//       table.addCell("Cost");
//
//       Iterator itr=items.iterator();
//       while(itr.hasNext()){
//          PrintOrder printOrder=(PrintOrder) itr.next();
//          table.addCell(printOrder.getProductCode());
//          table.addCell(printOrder.getDescription());
//          table.addCell(((Integer)printOrder.getQuantity()).toString());
//          table.addCell(((BigDecimal)printOrder.getPrice()).toString());
//          table.addCell(((BigDecimal)printOrder.getTax()).toString());
//          table.addCell(((BigDecimal)printOrder.getCost()).toString());
//
//       }
//
//
//       //document.add(table);
//
//    subCatPart.add(table);
//
//  }


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
     * @return the deliveryDate
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param deliveryDate the deliveryDate to set
     */
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
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
}
