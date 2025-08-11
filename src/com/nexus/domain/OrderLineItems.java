/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Terry
 */
@Entity
@Table(name = "xy_order_line_items")
@NamedQueries({@NamedQuery(name = "OrderLineItems.findAll", query = "SELECT o FROM OrderLineItems o"), @NamedQuery(name = "OrderLineItems.findById", query = "SELECT o FROM OrderLineItems o WHERE o.id = :id"), @NamedQuery(name = "OrderLineItems.findByProductCode", query = "SELECT o FROM OrderLineItems o WHERE o.productCode = :productCode"), @NamedQuery(name = "OrderLineItems.findByQty", query = "SELECT o FROM OrderLineItems o WHERE o.qty = :qty")})
public class OrderLineItems implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PRODUCT_CODE")
    private String productCode;
    @Basic(optional = false)
    @Column(name = "QTY")
    private int qty;
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NexusOrder orderId;

    public OrderLineItems() {
    }

    public OrderLineItems(Integer id) {
        this.id = id;
    }

    public OrderLineItems(Integer id, int qty) {
        this.id = id;
        this.qty = qty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public NexusOrder getOrderId() {
        return orderId;
    }

    public void setOrderId(NexusOrder orderId) {
        this.orderId = orderId;
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
        if (!(object instanceof OrderLineItems)) {
            return false;
        }
        OrderLineItems other = (OrderLineItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nexus.domain.OrderLineItems[id=" + id + "]";
    }

}
