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
public interface MicrocatOrderItemsDAO {

    List findMicrocatOrderItemsByOrderId(int orderId) throws SQLException;

        TemplateOrderItem findProductDetailsForTemplate(String productCode) throws SQLException;

        ShoppingCartItem findProductDetailsForShoppingCart(String productCode) throws SQLException;
        
         public List findScaniaOrderItemsByOrderId(int orderId) throws SQLException;
}
