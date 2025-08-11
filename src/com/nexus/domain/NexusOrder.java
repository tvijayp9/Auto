/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Terry
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "xy_order")
public class NexusOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "OrderNo")
    private String orderNo;
    @Basic(optional = false)
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(name = "delivery_date")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    @Column(name = "status")
    private String status;
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "buyid", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Registration buyId;
    @JoinColumn(name = "supid", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Registration supId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<OrderLineItems> orderLineItemsCollection;

    public NexusOrder() {
    }

    public NexusOrder(Integer id) {
        this.id = id;
    }

    public NexusOrder(Integer id, Date orderDate) {
        this.id = id;
        this.orderDate = orderDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Registration getBuyId() {
        return buyId;
    }

    public void setBuyId(Registration buyId) {
        this.buyId = buyId;
    }

    public Registration getSupId() {
        return supId;
    }

    public void setSupid(Registration supId) {
        this.supId = supId;
    }

    public Collection<OrderLineItems> getOrderLineItemsCollection() {
        return orderLineItemsCollection;
    }

    public void setOrderLineItemsCollection(Collection<OrderLineItems> orderLineItemsCollection) {
        this.orderLineItemsCollection = orderLineItemsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NexusOrder)) {
            return false;
        }
        NexusOrder other = (NexusOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nexus.domain.NexusOrder[id=" + id + "]";
    }

}
