package com.nexus.web.common;

public class ItemList {

    private int id;
    private String productid;
    private String name;
    private String desc;
    private String uom;
    private float price;
    private String qty;
    private String gtin;
    private String CategoryID = "";
    private String Product_Code1 = "";
    private String Action = "";
    private String BaseProductNumber = "";
    private String ProductIDExtension = "";
    private String CatalogProviderIDRef = "";
    private String CatalogIDRef = "";
    private String ProductIDRef = "";
    private String ComparableUOM = "";
    private String ComparableUOMConversionFactor = "";
    private String Manufacturer = "";
    private String ManuPartNumber = "";
    private int LeadTime = 0;
    private String LeadTimeUOM = "";
    private String ValidFrom = "";
    private String ValidUntil = "";
    private String CountryOfOrigin = "";
    private int MinOrder = 0;
    private int LotSize = 0;
    private String ShortDescription = "";
    private String LongDescriptionValue = "";
    private String LongDescriptionPurpose = "";
    private String CatalogContractID = "";
    private String CatalogContractItemID = "";
    private String AttachmentURL = "";
    private String AttachmentPurpose = "";
    private String AttachmentMIMEType = "";
    private String ProductAttachment_ShortDescription = "";
    private String ProductAttachment_LongDescription = "";
    private String ProductAttachment_LongDescriptionPurpose = "";
    private String RelatedProduct = "";
    private String AttributeID = "";
    private String AttributeUnit = "";
    private String AttributeValue = "";
    private String Type = "";
    private Integer sequenceNumber = 0;
    private boolean exist;

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public ItemList() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String Action) {
        this.Action = Action;
    }

    public String getAttachmentMIMEType() {
        return AttachmentMIMEType;
    }

    public void setAttachmentMIMEType(String AttachmentMIMEType) {
        this.AttachmentMIMEType = AttachmentMIMEType;
    }

    public String getAttachmentPurpose() {
        return AttachmentPurpose;
    }

    public void setAttachmentPurpose(String AttachmentPurpose) {
        this.AttachmentPurpose = AttachmentPurpose;
    }

    public String getAttachmentURL() {
        return AttachmentURL;
    }

    public void setAttachmentURL(String AttachmentURL) {
        this.AttachmentURL = AttachmentURL;
    }

    public String getAttributeID() {
        return AttributeID;
    }

    public void setAttributeID(String AttributeID) {
        this.AttributeID = AttributeID;
    }

    public String getAttributeUnit() {
        return AttributeUnit;
    }

    public void setAttributeUnit(String AttributeUnit) {
        this.AttributeUnit = AttributeUnit;
    }

    public String getAttributeValue() {
        return AttributeValue;
    }

    public void setAttributeValue(String AttributeValue) {
        this.AttributeValue = AttributeValue;
    }

    public String getBaseProductNumber() {
        return BaseProductNumber;
    }

    public void setBaseProductNumber(String BaseProductNumber) {
        this.BaseProductNumber = BaseProductNumber;
    }

    public String getCatalogContractID() {
        return CatalogContractID;
    }

    public void setCatalogContractID(String CatalogContractID) {
        this.CatalogContractID = CatalogContractID;
    }

    public String getCatalogContractItemID() {
        return CatalogContractItemID;
    }

    public void setCatalogContractItemID(String CatalogContractItemID) {
        this.CatalogContractItemID = CatalogContractItemID;
    }

    public String getCatalogIDRef() {
        return CatalogIDRef;
    }

    public void setCatalogIDRef(String CatalogIDRef) {
        this.CatalogIDRef = CatalogIDRef;
    }

    public String getCatalogProviderIDRef() {
        return CatalogProviderIDRef;
    }

    public void setCatalogProviderIDRef(String CatalogProviderIDRef) {
        this.CatalogProviderIDRef = CatalogProviderIDRef;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getComparableUOM() {
        return ComparableUOM;
    }

    public void setComparableUOM(String ComparableUOM) {
        this.ComparableUOM = ComparableUOM;
    }

    public String getComparableUOMConversionFactor() {
        return ComparableUOMConversionFactor;
    }

    public void setComparableUOMConversionFactor(String ComparableUOMConversionFactor) {
        this.ComparableUOMConversionFactor = ComparableUOMConversionFactor;
    }

    public String getCountryOfOrigin() {
        return CountryOfOrigin;
    }

    public void setCountryOfOrigin(String CountryOfOrigin) {
        this.CountryOfOrigin = CountryOfOrigin;
    }

    public int getLeadTime() {
        return LeadTime;
    }

    public void setLeadTime(int LeadTime) {
        this.LeadTime = LeadTime;
    }

    public String getLeadTimeUOM() {
        return LeadTimeUOM;
    }

    public void setLeadTimeUOM(String LeadTimeUOM) {
        this.LeadTimeUOM = LeadTimeUOM;
    }

    public String getLongDescriptionPurpose() {
        return LongDescriptionPurpose;
    }

    public void setLongDescriptionPurpose(String LongDescriptionPurpose) {
        this.LongDescriptionPurpose = LongDescriptionPurpose;
    }

    public String getLongDescriptionValue() {
        return LongDescriptionValue;
    }

    public void setLongDescriptionValue(String LongDescriptionValue) {
        this.LongDescriptionValue = LongDescriptionValue;
    }

    public int getLotSize() {
        return LotSize;
    }

    public void setLotSize(int LotSize) {
        this.LotSize = LotSize;
    }

    public String getManuPartNumber() {
        return ManuPartNumber;
    }

    public void setManuPartNumber(String ManuPartNumber) {
        this.ManuPartNumber = ManuPartNumber;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String Manufacturer) {
        this.Manufacturer = Manufacturer;
    }

    public int getMinOrder() {
        return MinOrder;
    }

    public void setMinOrder(int MinOrder) {
        this.MinOrder = MinOrder;
    }

    public String getProductAttachment_LongDescription() {
        return ProductAttachment_LongDescription;
    }

    public void setProductAttachment_LongDescription(String ProductAttachment_LongDescription) {
        this.ProductAttachment_LongDescription = ProductAttachment_LongDescription;
    }

    public String getProductAttachment_LongDescriptionPurpose() {
        return ProductAttachment_LongDescriptionPurpose;
    }

    public void setProductAttachment_LongDescriptionPurpose(String ProductAttachment_LongDescriptionPurpose) {
        this.ProductAttachment_LongDescriptionPurpose = ProductAttachment_LongDescriptionPurpose;
    }

    public String getProductAttachment_ShortDescription() {
        return ProductAttachment_ShortDescription;
    }

    public void setProductAttachment_ShortDescription(String ProductAttachment_ShortDescription) {
        this.ProductAttachment_ShortDescription = ProductAttachment_ShortDescription;
    }

    public String getProductIDExtension() {
        return ProductIDExtension;
    }

    public void setProductIDExtension(String ProductIDExtension) {
        this.ProductIDExtension = ProductIDExtension;
    }

    public String getProductIDRef() {
        return ProductIDRef;
    }

    public void setProductIDRef(String ProductIDRef) {
        this.ProductIDRef = ProductIDRef;
    }

    public String getProduct_Code1() {
        return Product_Code1;
    }

    public void setProduct_Code1(String Product_Code1) {
        this.Product_Code1 = Product_Code1;
    }

    public String getRelatedProduct() {
        return RelatedProduct;
    }

    public void setRelatedProduct(String RelatedProduct) {
        this.RelatedProduct = RelatedProduct;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String ShortDescription) {
        this.ShortDescription = ShortDescription;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
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

    public ItemList(String productid, String name, String uom, String gtin) {
        this.productid = productid;
        this.name = name;
        this.uom = uom;
        this.gtin = gtin;
    }

    public ItemList(String productid, String name, String uom, String gtin, boolean exist) {
        this.productid = productid;
        this.name = name;
        this.uom = uom;
        this.gtin = gtin;
        this.exist = exist;
    }

    public ItemList(String productid, String name, String uom, String gtin, String qty) {
        this.productid = productid;
        this.name = name;
        this.uom = uom;
        this.qty = qty;
        this.gtin = gtin;
    }

    public ItemList(int id, String productid, String name, String desc, String uom, float price) {
        this.id = id;
        this.productid = productid;
        this.name = name;
        this.desc = desc;
        this.uom = uom;
        this.price = price;
    }

    /**
     * @return the sequenceNumber
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * @param sequenceNumber the sequenceNumber to set
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * @return the exist
     */
    public boolean isExist() {
        return exist;
    }

    /**
     * @param exist the exist to set
     */
    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
