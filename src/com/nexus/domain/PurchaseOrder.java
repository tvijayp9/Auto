/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.domain;

import java.util.List;

/**
 *
 * @author Terry
 */
public class PurchaseOrder {

    private Integer id; 
    private String customerCode;
    private String orderNumber;
    private String comment;
    private String deliveryDate;
    private String invoiceNo;
    private List itemList;

    public PurchaseOrder(){}

    public PurchaseOrder(String customerCode,String orderNumber,String comment,String deliveryDate,List itemList){
      this.customerCode=customerCode;
      this.orderNumber=orderNumber;
      this.comment=comment;
      this.deliveryDate=deliveryDate;
      this.itemList=itemList;
    }
     public PurchaseOrder(String customerCode,String orderNumber,String comment,String deliveryDate,String invoiceNo,List itemList){
      this.customerCode=customerCode;
      this.orderNumber=orderNumber;
      this.comment=comment;
      this.deliveryDate=deliveryDate;
      this.invoiceNo=invoiceNo;
      this.itemList=itemList;
    }
     
      public PurchaseOrder(Integer id,String customerCode,String orderNumber,String comment,String deliveryDate,String invoiceNo,List itemList){
      this.id=id;
      this.customerCode=customerCode;
      this.orderNumber=orderNumber;
      this.comment=comment;
      this.deliveryDate=deliveryDate;
      this.invoiceNo=invoiceNo;
      this.itemList=itemList;
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
     * @return the orderDate
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * @return the itemList
     */
    public List getItemList() {
        return itemList;
    }

    /**
     * @param itemList the itemList to set
     */
    public void setItemList(List itemList) {
        this.itemList = itemList;
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
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
