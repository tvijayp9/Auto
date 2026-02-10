/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.ShoppingCartItem;
import com.nexus.domain.TemplateOrderItem;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Vijay Thumma
 */
public interface ProductManagementDAO {
    
    public boolean uploadParts(String uploadFile, String filePath , String productTable) throws SQLException;
    
    public boolean uploadMRLParts(String uploadFile, String filePath, String productTable) throws SQLException;
    
    public boolean updatePart(String siteName, String catName, String partNumber, String description, String price, String productTable) throws SQLException;
    
    public boolean deletePart(String siteName, String catName, String partNumber, String productTable) throws SQLException;
    
    public int findSupplierProductsCount(String searchFor, String searchIn, String product_table_name) throws SQLException;
    
    public List findSupplierProducts(String searchFor, String searchIn, int start, int limit, String sidx, String sord, String product_table_name) throws SQLException;
    
    public ShoppingCartItem findProductDetailsForShoppingCart(String siteName, String categoryName, String productCode, String product_table_name) throws SQLException;
    
    public List<String> findSupplierProductsSiteNames(String product_table_name) throws SQLException;
    
    public List<String> findSupplierProductsCategoryNames(String siteName, String product_table_name) throws SQLException;
    
    public int findSupplierProductsBySiteCount(String siteName, String categoryName, String product_table_name) throws SQLException;

    public List findSupplierProductsBySiteName(String sitename, String categoryname, int start, int limit, String sidx, String sord, String product_table_name) throws SQLException;
    
    public TemplateOrderItem findProductDetailsForTemplate(String siteName, String categoryName, String productCode, String product_table_name) throws SQLException;
    
    public List findQuoteItemsByQuoteId(int quoteId, int start, int limit, String sidx, String sord) throws SQLException;
    
    public int findQuoteItemsByQuoteIdCount(int quoteId) throws SQLException;
    
    public ShoppingCartItem findProductDetailsForQuoteShoppingCart(String itemId) throws SQLException;
    
    public String findQuoteNameByQuoteId(int quoteId) throws SQLException;
    
    public List<TemplateOrderItem> findQuoteItemsByQuoteId(int quoteId) throws SQLException;
}
