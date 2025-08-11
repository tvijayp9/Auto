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
 * @author Terry
 */
public interface ProductsUMGDAO {

    int findSupplierProductsCount(String searchFor, String searchIn,String product_table_name) throws SQLException;

    List findSupplierProducts(String price, int id, int supplierId, String searchFor, String searchIn, int start, int limit, String sidx, String sord,String product_table_name) throws SQLException;

    ShoppingCartItem findProductDetailsForShoppingCart(int id, int supplierId, String priceType, String productCode,String product_table_name) throws SQLException;

    ShoppingCartItem findProductDetailsForShoppingCart(int id, int supplierId, String priceType, String productCode, int quantity,String product_table_name) throws SQLException;

    TemplateOrderItem findProductDetailsForTemplate(int id, int supplierId, String priceType, String productCode,String product_table_name) throws SQLException;

    int findOrderItemsCount(String orderId) throws SQLException;

    List findOrderItems(String orderId, int start, int limit, String sidx, String sord) throws SQLException;

    List findOrderItems(int orderId) throws SQLException;
    
    public List findInvoiceItems(int orderId) throws SQLException;
    
    public List findOrderItemsforInvoice(int orderId) throws SQLException;
     
    public List findInvoiceLineItems(int orderId) throws SQLException;
}
