/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Terry
 */
public class ProductsDisplay {

    private static List<ProductsField> list;

    public ProductsDisplay() {
    }
    
    static {
        list = new ArrayList();
        list.add(new ProductsField("Product_Code1", "Product Code"));
        list.add(new ProductsField("GTIN", "GTIN"));
        list.add(new ProductsField("Product_Name", "Product Name"));
        list.add(new ProductsField("Product_Description", "Product Description"));
        list.add(new ProductsField("UOM", "UOM"));
        list.add(new ProductsField("Price", "Price"));
        list.add(new ProductsField("BaseProductNumber", "BaseProductNumber"));
        list.add(new ProductsField("ProductIdExtension", "ProductIdExtension"));
        list.add(new ProductsField("ComparableUOM", "ComparableUOM"));
        list.add(new ProductsField("ComparableUOMConversionFactor", "ComparableUOMConversionFactor"));
        list.add(new ProductsField("Manufacturer", "Manufacturer"));
        list.add(new ProductsField("ManuPartNumber", "ManuPartNumber"));
        list.add(new ProductsField("LeadTime", "LeadTime"));
        list.add(new ProductsField("LeadTimeUOM", "LeadTimeUOM"));
        list.add(new ProductsField("ValidFrom", "ValidFrom"));
        list.add(new ProductsField("ValidUntil", "ValidUntil"));
        list.add(new ProductsField("CountryOfOrigin", "CountryOfOrigin"));
        list.add(new ProductsField("MinOrder", "MinOrder"));
        list.add(new ProductsField("LotSize", "LotSize"));
        list.add(new ProductsField("LongDescriptionValue", "LongDescriptionValue"));
        list.add(new ProductsField("LongDescriptionPurpose", "LongDescriptionPurpose"));
        list.add(new ProductsField("CatalogContractID", "CatalogContractID"));
        list.add(new ProductsField("CatalogContractItemID", "CatalogContractItemID"));
        list.add(new ProductsField("AttachmentURL", "AttachmentURL"));
        list.add(new ProductsField("AttachmentPurpose", "AttachmentPurpose"));
        list.add(new ProductsField("AttachmentMIMEType", "AttachmentMIMEType"));
        list.add(new ProductsField("ProductAttachment_ShortDescription", "ProductAttachment_ShortDescription"));
        list.add(new ProductsField("ProductAttachment_LongDescription", "ProductAttachment_LongDescription"));
        list.add(new ProductsField("ProductAttachment_LongDescriptionPurpose", "ProductAttachment_LongDescriptionPurpose"));
        list.add(new ProductsField("RelatedProduct", "RelatedProduct"));
        list.add(new ProductsField("AttributeID", "AttributeID"));
        list.add(new ProductsField("AttributeUnit", "AttributeUnit"));
        list.add(new ProductsField("AttributeValue", "AttributeValue"));
    }

    public static List<ProductsField> getProductsDisplay() {
        return list;
    }
}
