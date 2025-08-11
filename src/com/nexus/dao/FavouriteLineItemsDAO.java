/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface FavouriteLineItemsDAO {

    void insertFavouriteLineItems(int favouriteOrderId,String gtin) throws SQLException;

    int findFavouriteOrderListCount(int id, int supplierId) throws SQLException;

    List findFavouriteOrderList(int id, int supplierId, int start, int limit, String sidx, String sord) throws SQLException;

    int findTemplateOrderItemsByTemplateIdCount(int templateId,String product_table_name) throws SQLException;

    List findTemplateOrderItemsByTemplateId(int templateId, String priceType, int id,int supplierId,int start, int limit, String sidx, String sord,String product_table_name) throws SQLException;

    List<String> findProductCodeByTemplateId(int templateId) throws SQLException;

    void deleteTemplateOrderItem(int templateOrderItemId) throws SQLException;

    String findProductCodeByTemplateOrderItemId(int templateOrderItemId) throws SQLException;
}
