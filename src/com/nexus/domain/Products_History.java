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
import javax.persistence.Table;

/**
 *
 * @author Sunil
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "xy_order_items_details")
public class Products_History implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Product_Code1")
    private String pid;
    @Column(name = "GTIN")
    private String gtin;
    @Basic(optional = false)
    @Column(name = "Product_Name")
    private String pname;
    @Column(name = "Product_Description")
    private String shortDesc;
    @Column(name = "UOM")
    private String uom;
    @Column(name = "Price")
    private float price;
    @Column(name = "BaseProductNumber")
    private String bpNumber;
    @Column(name = "ProductIDExtension")
    private String piExt;
    @Column(name = "CatalogProviderIDRef")
    private String catProviderID;
    @Column(name = "ComparableUOM")
    private String compUOM;
    @Column(name = "ComparableUOMConversionFactor")
    private String compUOMConFact;
    @Column(name = "Manufacturer")
    private String manufacturer;
    @Column(name = "ManuPartNumber")
    private String manuPartNumber;
    @Column(name = "LeadTime")
    private int leadTime;
    @Column(name = "LeadTimeUOM")
    private String leadTimeUOM;
    @Column(name = "ValidFrom")
    private String ValidFrom;
    @Column(name = "ValidUntil")
    private String ValidUntil;
    @Column(name = "CountryOfOrigin")
    private String country;
    @Column(name = "MinOrder")
    private int minOrder;
    @Column(name = "LotSize")
    private int lotSize;
    @Column(name = "LongDescriptionValue")
    private String longDesc;
    @Column(name = "LongDescriptionPurpose")
    private String longDescPurpose;
    @Column(name = "CatalogContractID")
    private String catContractID;
    @Column(name = "CatalogContractItemID")
    private String CataContractItemID;
    @Column(name = "AttachmentURL")
    private String url;
    @Column(name = "AttachmentPurpose")
    private String attachmentPurpose;
    @Column(name = "AttachmentMIMEType")
    private String mimeType;
    @Column(name = "ProductAttachment_ShortDescription")
    private String pashortDesc;
    @Column(name = "ProductAttachment_LongDescription")
    private String palongDesc;
    @Column(name = "ProductAttachment_LongDescriptionPurpose")
    private String palongDescPurpose;
    @Column(name = "RelatedProduct")
    private String relProduct;
    @Column(name = "AttributeID")
    private String attrID;
    @Column(name = "AttributeUnit")
    private String attrUnit;
    @Column(name = "AttributeValue")
    private String attrValue;

    public Products_History() {
    }

    public Products_History(Integer id) {
        this.id = id;
    }

    public Products_History(String catProviderID) {
        this.catProviderID = catProviderID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getCataContractItemID() {
        return CataContractItemID;
    }

    public void setCataContractItemID(String CataContractItemID) {
        this.CataContractItemID = CataContractItemID;
    }

    public String getValidFrom() {
        return ValidFrom;
    }

    public void setValidFrom(String ValidFrom) {
        this.ValidFrom = ValidFrom;
    }

    public String getValidUntil() {
        return ValidUntil;
    }

    public void setValidUntil(String ValidUntil) {
        this.ValidUntil = ValidUntil;
    }

    public String getAttachmentPurpose() {
        return attachmentPurpose;
    }

    public void setAttachmentPurpose(String attachmentPurpose) {
        this.attachmentPurpose = attachmentPurpose;
    }

    public String getAttrID() {
        return attrID;
    }

    public void setAttrID(String attrID) {
        this.attrID = attrID;
    }

    public String getAttrUnit() {
        return attrUnit;
    }

    public void setAttrUnit(String attrUnit) {
        this.attrUnit = attrUnit;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getBpNumber() {
        return bpNumber;
    }

    public void setBpNumber(String bpNumber) {
        this.bpNumber = bpNumber;
    }

    public String getCatContractID() {
        return catContractID;
    }

    public void setCatContractID(String catContractID) {
        this.catContractID = catContractID;
    }

    public String getCompUOM() {
        return compUOM;
    }

    public void setCompUOM(String compUOM) {
        this.compUOM = compUOM;
    }

    public String getCompUOMConFact() {
        return compUOMConFact;
    }

    public void setCompUOMConFact(String compUOMConFact) {
        this.compUOMConFact = compUOMConFact;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(int leadTime) {
        this.leadTime = leadTime;
    }

    public String getLeadTimeUOM() {
        return leadTimeUOM;
    }

    public void setLeadTimeUOM(String leadTimeUOM) {
        this.leadTimeUOM = leadTimeUOM;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getLongDescPurpose() {
        return longDescPurpose;
    }

    public void setLongDescPurpose(String longDescPurpose) {
        this.longDescPurpose = longDescPurpose;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    public String getManuPartNumber() {
        return manuPartNumber;
    }

    public void setManuPartNumber(String manuPartNumber) {
        this.manuPartNumber = manuPartNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public int getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(int minOrder) {
        this.minOrder = minOrder;
    }

    public String getPalongDesc() {
        return palongDesc;
    }

    public void setPalongDesc(String palongDesc) {
        this.palongDesc = palongDesc;
    }

    public String getPashortDesc() {
        return pashortDesc;
    }

    public void setPashortDesc(String pashortDesc) {
        this.pashortDesc = pashortDesc;
    }

    public String getPalongDescPurpose() {
        return palongDescPurpose;
    }

    public void setPalongDescPurpose(String palongDescPurpose) {
        this.palongDescPurpose = palongDescPurpose;
    }

    public String getPiExt() {
        return piExt;
    }

    public void setPiExt(String piExt) {
        this.piExt = piExt;
    }

    public String getRelProduct() {
        return relProduct;
    }

    public void setRelProduct(String relProduct) {
        this.relProduct = relProduct;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCatProviderID() {
        return catProviderID;
    }

    public void setCatProviderID(String catProviderID) {
        this.catProviderID = catProviderID;
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
        if (!(object instanceof Products_History)) {
            return false;
        }
        Products_History other = (Products_History) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nexus.domain.Registration[id=" + id + "]";
    }
}
