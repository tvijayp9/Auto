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
public interface ProductHistoryDAO {

    List findArchivedOrderItems(String orderId, int start,int limit,String sidx,String sord) throws SQLException;

    int findArchivedOrderItemsCount(String orderId) throws SQLException;

    List findOrderItemsByOrderId(int orderId) throws SQLException;
    
    List findArchivedOrderItemsByOrderId(int orderId) throws SQLException;

}
