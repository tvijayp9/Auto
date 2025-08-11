/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.dao;

import com.nexus.domain.ShoppingCartItem;
import com.nexus.domain.TemplateOrderItem;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface QuotesItemsDAO {

 void insertQuoteItems(int QId,String gtin,BigDecimal price,String description,int qty) throws SQLException;

     int findQuoteItemsByQuoteIdCount(int quoteId) throws SQLException;

    List findQuoteItemsByQuoteId(int quoteId, int start, int limit, String sidx, String sord) throws SQLException;

    List<TemplateOrderItem> findQuoteItemsByQuoteId(int quoteId) throws SQLException;

        void deleteQuoteItem(int quoteItemId) throws SQLException;

            List<String> findProductCodeByQuoteId(int quoteId) throws SQLException;

            void updateQuoteItemQuantity(String id, int quantity) throws SQLException;

            ShoppingCartItem findProductDetailsForQuoteShoppingCart(String itemId) throws SQLException;

            void updateQuoteItemStatus(int quoteItemId) throws SQLException;
}
