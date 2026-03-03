/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.services;

import com.nexus.domain.ShoppingCartItem;
import com.nexus.domain.TemplateOrderItem;
import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Vijay Thumma
 */
public interface ProductManagementService {
    
    public boolean uploadParts(File uploadFile, String uploadFilePath, String supNexusId, String productsTableName) throws SQLException;
    
    public boolean uploadMRLParts(File uploadFile, String uploadFilePath, String supNexusId, String productsTableName) throws SQLException;
            
    public boolean updatePart(String siteName, String catName, String partNumber, String description, String price, String productTable) throws SQLException;
    
    public boolean deletePart(String siteName, String catName, String partNumber, String productTable) throws SQLException;
    
    public int findSupplierProductsCount(String searchFor, String searchIn, String product_table_name) throws SQLException;
    
    public List findSupplierProducts(String searchFor, String searchIn, int start, int limit, String sidx, String sord, String product_table_name) throws SQLException;
    
    public void addShoppingCartItem(String[] productCode, List<ShoppingCartItem> shoppingCart,String product_table_name) throws SQLException;
    
    public List getShoppingCart(List<ShoppingCartItem> shoppingCart, int start, int limit) throws SQLException;
    
    public void updateQuantity(String productCode, int quantity, List<ShoppingCartItem> shoppingCart);
    
    public void deleteShoppingCartItem(String productCode, List<ShoppingCartItem> shoppingCart);
    
    public List<String> findSupplierProductsSiteNames(String product_table_name) throws SQLException;
    
    public List<String> findSupplierProductsCategoryNames(String siteName, String product_table_name) throws SQLException;
    
    public int findSupplierProductsBySiteCount(String siteName, String categoryName, String product_table_name) throws SQLException;

    public List findSupplierProductsBySiteName(String sitename, String categoryname, int start, int limit, String sidx, String sord, String product_table_name) throws SQLException;
    
    public List<TemplateOrderItem> addAmcapQuoteItem(String[] productCode, List<TemplateOrderItem> quote,String product_table_name) throws SQLException;
    
    public List getQuote(List<TemplateOrderItem> template, int start, int limit) throws SQLException;
    
    public List<TemplateOrderItem> addAmcapProductToQuoteItem(int qty, String productCode, String description, BigDecimal price, int leadTime, List<TemplateOrderItem> quote) throws SQLException;
    
    public String saveNewQuote(String quoteName, List<TemplateOrderItem> quote, int id, int supplierId, String comment) throws SQLException;
    
    public List findQuoteItemsByQuoteId(int quoteId, int start, int limit, String sidx, String sord) throws SQLException;
    
    public int findQuoteItemsByQuoteIdCount(int quoteId) throws SQLException;
    
    public void addQuoteShoppingCartItem(String itemsId, List<ShoppingCartItem> shoppingCart, List<TemplateOrderItem> quotesItems) throws SQLException;
    
    public HashMap findQuoteByQuoteId(int quoteId) throws SQLException;
    
    public void updateQuoteQuantity(String productCode, int quantity, List<TemplateOrderItem> quoteOrderItems);
    
    public List<TemplateOrderItem> findQuoteItemsByQuoteId(int quoteId) throws SQLException;
    
    public List getQuoteItems(List<TemplateOrderItem> template, int start, int limit) throws SQLException;
}
