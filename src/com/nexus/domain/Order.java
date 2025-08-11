/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.domain;

/**
 *
 * @author Terry
 */
public class Order {
  private int id;
  private String orderNo;
  private String buyId;
  private String supplierId;
  private String deliveryDate;
  private String invoiceNo;
  private String comments;

  public Order(){}

  public Order(int id,String orderNo,String buyId,String supplierId,String deliveryDate,String comments){
    this.id=id;
    this.orderNo=orderNo;
    this.buyId=buyId;
    this.supplierId=supplierId;
    this.deliveryDate=deliveryDate;
    this.comments=comments;
  }
  
   public Order(int id,String orderNo,String buyId,String supplierId,String deliveryDate,String invoiceNo,String comments){
    this.id=id;
    this.orderNo=orderNo;
    this.buyId=buyId;
    this.supplierId=supplierId;
    this.deliveryDate=deliveryDate;
    this.invoiceNo=invoiceNo;
    this.comments=comments;
  }

  public Order(String orderNo,String deliveryDate,String comments){
    this.orderNo=orderNo;
    this.deliveryDate=deliveryDate;
    this.comments=comments;
  }

  public Order(int id, String orderNo){
    this.id=id;
    this.orderNo=orderNo;
  }

    /**
     * @return the orderNumber
     */
    public String getBuyId() {
        return buyId;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the supplierId
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
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
}
