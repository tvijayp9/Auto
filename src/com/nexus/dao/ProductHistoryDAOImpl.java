/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import com.nexus.web.common.ItemList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Terry
 */
public class ProductHistoryDAOImpl implements ProductHistoryDAO {

    Logger log=Logger.getLogger(ProductHistoryDAOImpl.class);
    private SessionFactory sessionFactory;

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int findArchivedOrderItemsCount(String orderId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findArchivedOrderItemsCount..orderId.."+orderId);
        /*String selectStatement = "select count(oli.id) from xy_order_line_items oli,xy_order_items_details oid," +
        "buyer_products_mapping bpm,xy_partner_link pl,xy_order o where oli.PRODUCT_CODE = oid.gtin " +
        "and oli.ID = oid.ID and bpm.productcode = oli.PRODUCT_CODE and oli.ORDER_ID=o.id and o.buyid = pl.Buyer_Id " +
        "and o.supid = pl.Supplier_Id and pl.ID = bpm.partner_link_id and oli.ORDER_ID=?";*/
        String selectStatement = "select count(oli.id) from (((xy_order_line_items_archive as oli inner join xy_order_items_details as oid on oli.Item_history_ID=oid.id) " +
                "inner join xy_order_archive as o on oli.order_id=o.id) inner join xy_partner_link as pl on o.buyid=pl.buyer_id and o.supid=pl.supplier_id) " +
                "inner join buyer_products_mapping as bpm on bpm.partner_link_id=pl.id and bpm.productcode=oid.gtin where oli.order_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(orderId).intValue());
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    public List findArchivedOrderItems(String orderId, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("findArchivedOrderItems..orderId.."+orderId);
        /*String selectStatement = "select oli.id, oid.gtin, oid.Product_Name,oid.UOM,oli.QTY,oid.Product_Code " +
        "from xy_order_line_items oli,xy_order_items_details oid," +
        "buyer_products_mapping bpm,xy_partner_link pl,xy_order o where oli.PRODUCT_CODE = oid.gtin " +
        "and oli.ID = oid.ID and bpm.productcode = oli.PRODUCT_CODE and oli.ORDER_ID=o.id and o.buyid = pl.Buyer_Id " +
        "and o.supid = pl.Supplier_Id and pl.ID = bpm.partner_link_id and oli.ORDER_ID=? order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;*/
        String selectStatement = "select oli.id, oid.gtin, oid.Product_Name,oid.UOM,oli.QTY,bpm.buyer_item_number " +
                "from (((xy_order_line_items_archive as oli inner join xy_order_items_details as oid on oli.Item_history_ID=oid.id) " +
                "inner join xy_order_archive as o on oli.order_id=o.id) inner join xy_partner_link as pl on o.buyid=pl.buyer_id and o.supid=pl.supplier_id) " +
                "inner join buyer_products_mapping as bpm on bpm.partner_link_id=pl.id and bpm.productcode=oid.gtin " +
                "where oli.order_id=? order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(orderId).intValue());
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("buyer_item_number"));
            cell.add(rs.getString("gtin"));
            cell.add(rs.getString("Product_Name"));
            cell.add(rs.getString("UOM"));
            cell.add(rs.getString("QTY"));
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

     public List findOrderItemsByOrderId(int orderId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List items = new ArrayList();
        log.info("findOrderItemsByOrderId..orderId.."+orderId);
        String selectStatement = "select xy_order_items_details.gtin, xy_order_items_details.Product_Name,xy_order_items_details.UOM,xy_order_line_items.QTY, xy_order_items_details.CategoryID," +
                "buyer_products_mapping.buyer_item_number,xy_order_items_details.Product_Code1,xy_order_items_details.Product_Description,buyer_products_mapping.price," +
                "xy_order_items_details.Action,xy_order_items_details.BaseProductNumber,xy_order_items_details.ProductIDExtension," +
                "xy_order_items_details.CatalogProviderIDRef,xy_order_items_details.CatalogIDRef,xy_order_items_details.ProductIDRef,xy_order_items_details.ComparableUOM," +
                "xy_order_items_details.ComparableUOMConversionFactor,xy_order_items_details.Manufacturer,xy_order_items_details.ManuPartNumber,xy_order_items_details.LeadTime," +
                "xy_order_items_details.LeadTimeUOM,xy_order_items_details.ValidFrom,xy_order_items_details.ValidUntil,xy_order_items_details.CountryOfOrigin,xy_order_items_details.MinOrder," +
                "xy_order_items_details.LotSize,xy_order_items_details.ShortDescription,xy_order_items_details.LongDescriptionValue,xy_order_items_details.LongDescriptionPurpose," +
                "xy_order_items_details.CatalogContractID,xy_order_items_details.CatalogContractItemID,xy_order_items_details.AttachmentURL,xy_order_items_details.AttachmentPurpose," +
                "xy_order_items_details.AttachmentMIMEType,xy_order_items_details.ProductAttachment_ShortDescription,xy_order_items_details.ProductAttachment_LongDescription," +
                "xy_order_items_details.ProductAttachment_LongDescriptionPurpose,xy_order_items_details.RelatedProduct,xy_order_items_details.AttributeID,xy_order_items_details.AttributeUnit," +
                "xy_order_items_details.AttributeValue,xy_order_items_details.Type from xy_order_line_items,xy_order_items_details,buyer_products_mapping,xy_partner_link,xy_order " +
                "where xy_order_line_items.Item_history_ID = xy_order_items_details.id " +
                "and buyer_products_mapping.productcode = xy_order_items_details.gtin " +
                "and xy_order_line_items.ORDER_ID=xy_order.id " +
                "and xy_order.buyid = xy_partner_link.Buyer_Id " +
                "and xy_order.supid = xy_partner_link.Supplier_Id " +
                "and xy_partner_link.ID = buyer_products_mapping.partner_link_id " +
                "and xy_order_line_items.ORDER_ID=? order by buyer_products_mapping.id";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, orderId);
        rs = ps.executeQuery();
        int i=1;
        while (rs.next()) {
            ItemList item = new ItemList();
            item.setGtin(rs.getString("gtin"));
            item.setName(rs.getString("Product_Name"));
            item.setUom(rs.getString("uom"));
            item.setQty(rs.getString("qty"));
            item.setCategoryID(rs.getString("CategoryID"));
            item.setProductid(rs.getString("buyer_item_number"));
            item.setProduct_Code1(rs.getString("Product_Code1"));
            item.setDesc(rs.getString("Product_Description"));
            item.setPrice(rs.getFloat("price"));
            item.setAction(rs.getString("Action"));
            item.setBaseProductNumber(rs.getString("BaseProductNumber"));
            item.setProductIDExtension(rs.getString("ProductIDExtension"));
            item.setCatalogProviderIDRef(rs.getString("CatalogProviderIDRef"));
            item.setCatalogIDRef(rs.getString("CatalogIDRef"));
            item.setProductIDRef(rs.getString("ProductIDRef"));
            item.setComparableUOM(rs.getString("ComparableUOM"));
            item.setComparableUOMConversionFactor(rs.getString("ComparableUOMConversionFactor"));
            item.setManufacturer(rs.getString("Manufacturer"));
            item.setManuPartNumber(rs.getString("ManuPartNumber"));
            item.setLeadTime(rs.getInt("LeadTime"));
            item.setLeadTimeUOM(rs.getString("LeadTimeUOM"));
            item.setValidFrom(rs.getString("ValidFrom"));
            item.setValidUntil(rs.getString("ValidUntil"));
            item.setCountryOfOrigin(rs.getString("CountryOfOrigin"));
            item.setMinOrder(rs.getInt("MinOrder"));
            item.setLotSize(rs.getInt("LotSize"));
            item.setShortDescription(rs.getString("ShortDescription"));
            item.setLongDescriptionValue(rs.getString("LongDescriptionValue"));
            item.setLongDescriptionPurpose(rs.getString("LongDescriptionPurpose"));
            item.setCatalogContractID(rs.getString("CatalogContractID"));
            item.setCatalogContractID(rs.getString("CatalogContractItemID"));
            item.setAttachmentURL(rs.getString("AttachmentURL"));
            item.setAttachmentPurpose(rs.getString("AttachmentPurpose"));
            item.setAttachmentMIMEType(rs.getString("AttachmentMIMEType"));
            item.setProductAttachment_ShortDescription(rs.getString("ProductAttachment_ShortDescription"));
            item.setProductAttachment_LongDescription(rs.getString("ProductAttachment_LongDescription"));
            item.setProductAttachment_LongDescriptionPurpose(rs.getString("ProductAttachment_LongDescriptionPurpose"));
            item.setRelatedProduct(rs.getString("RelatedProduct"));
            item.setAttributeID(rs.getString("AttributeID"));
            item.setAttributeUnit(rs.getString("AttributeUnit"));
            item.setAttributeValue(rs.getString("AttributeValue"));
            item.setType(rs.getString("Type"));
            item.setSequenceNumber(new Integer(i));
            items.add(item);
            i++;
        }
        return items;
    }

    public List findArchivedOrderItemsByOrderId(int orderId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List items = new ArrayList();
        log.info("findArchivedOrderItemsByOrderId..orderId.."+orderId);
        String selectStatement = "select b.gtin, b.Product_Name,b.UOM,a.QTY, b.CategoryID," +
                "c.buyer_item_number,b.Product_Code1,b.Product_Description,c.price," +
                "b.Action,b.BaseProductNumber,b.ProductIDExtension," +
                "b.CatalogProviderIDRef,b.CatalogIDRef,b.ProductIDRef,b.ComparableUOM," +
                "b.ComparableUOMConversionFactor,b.Manufacturer,b.ManuPartNumber,b.LeadTime," +
                "b.LeadTimeUOM,b.ValidFrom,b.ValidUntil,b.CountryOfOrigin,b.MinOrder," +
                "b.LotSize,b.ShortDescription,b.LongDescriptionValue,b.LongDescriptionPurpose," +
                "b.CatalogContractID,b.CatalogContractItemID,b.AttachmentURL,b.AttachmentPurpose," +
                "b.AttachmentMIMEType,b.ProductAttachment_ShortDescription,b.ProductAttachment_LongDescription," +
                "b.ProductAttachment_LongDescriptionPurpose,b.RelatedProduct,b.AttributeID,b.AttributeUnit," +
                "b.AttributeValue,b.Type from xy_order_line_items_archive a,xy_order_items_details b,buyer_products_mapping c,xy_partner_link d,xy_order_archive e " +
                "where a.Item_history_ID = b.id " +
                "and c.productcode = b.gtin " +
                "and a.ORDER_ID=e.id " +
                "and e.buyid = d.Buyer_Id " +
                "and e.supid = d.Supplier_Id " +
                "and d.ID = c.partner_link_id " +
                "and a.ORDER_ID=? order by c.id";
        ps = connection.prepareStatement(selectStatement);
        log.info("findArchivedOrderItemsByOrderId selectStatement = "+selectStatement);
        ps.setInt(1, orderId);
        rs = ps.executeQuery();
        int i=1;
        while (rs.next()) {
            ItemList item = new ItemList();
            item.setGtin(rs.getString("gtin"));
            item.setName(rs.getString("Product_Name"));
            item.setUom(rs.getString("uom"));
            item.setQty(rs.getString("qty"));
            item.setCategoryID(rs.getString("CategoryID"));
            item.setProductid(rs.getString("buyer_item_number"));
            item.setProduct_Code1(rs.getString("Product_Code1"));
            item.setDesc(rs.getString("Product_Description"));
            item.setPrice(rs.getFloat("price"));
            item.setAction(rs.getString("Action"));
            item.setBaseProductNumber(rs.getString("BaseProductNumber"));
            item.setProductIDExtension(rs.getString("ProductIDExtension"));
            item.setCatalogProviderIDRef(rs.getString("CatalogProviderIDRef"));
            item.setCatalogIDRef(rs.getString("CatalogIDRef"));
            item.setProductIDRef(rs.getString("ProductIDRef"));
            item.setComparableUOM(rs.getString("ComparableUOM"));
            item.setComparableUOMConversionFactor(rs.getString("ComparableUOMConversionFactor"));
            item.setManufacturer(rs.getString("Manufacturer"));
            item.setManuPartNumber(rs.getString("ManuPartNumber"));
            item.setLeadTime(rs.getInt("LeadTime"));
            item.setLeadTimeUOM(rs.getString("LeadTimeUOM"));
            item.setValidFrom(rs.getString("ValidFrom"));
            item.setValidUntil(rs.getString("ValidUntil"));
            item.setCountryOfOrigin(rs.getString("CountryOfOrigin"));
            item.setMinOrder(rs.getInt("MinOrder"));
            item.setLotSize(rs.getInt("LotSize"));
            item.setShortDescription(rs.getString("ShortDescription"));
            item.setLongDescriptionValue(rs.getString("LongDescriptionValue"));
            item.setLongDescriptionPurpose(rs.getString("LongDescriptionPurpose"));
            item.setCatalogContractID(rs.getString("CatalogContractID"));
            item.setCatalogContractID(rs.getString("CatalogContractItemID"));
            item.setAttachmentURL(rs.getString("AttachmentURL"));
            item.setAttachmentPurpose(rs.getString("AttachmentPurpose"));
            item.setAttachmentMIMEType(rs.getString("AttachmentMIMEType"));
            item.setProductAttachment_ShortDescription(rs.getString("ProductAttachment_ShortDescription"));
            item.setProductAttachment_LongDescription(rs.getString("ProductAttachment_LongDescription"));
            item.setProductAttachment_LongDescriptionPurpose(rs.getString("ProductAttachment_LongDescriptionPurpose"));
            item.setRelatedProduct(rs.getString("RelatedProduct"));
            item.setAttributeID(rs.getString("AttributeID"));
            item.setAttributeUnit(rs.getString("AttributeUnit"));
            item.setAttributeValue(rs.getString("AttributeValue"));
            item.setType(rs.getString("Type"));
            item.setSequenceNumber(new Integer(i));
            items.add(item);
            i++;
        }
        return items;
    }

}
