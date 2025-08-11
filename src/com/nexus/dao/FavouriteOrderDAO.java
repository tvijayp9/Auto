/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import java.sql.SQLException;

/**
 *
 * @author Terry
 */
public interface FavouriteOrderDAO {

    void insertFavouriteOrder(String orderNumber, String favouriteName, int id, int supplierId) throws SQLException;

    int findFavouriteOrderIdByOrderNumber(String orderNumber) throws SQLException;

    int findFavouriteOrderId(String templateName, int id, int supplierId) throws SQLException;

    void deleteFavouriteOrder(int id) throws SQLException;
}
