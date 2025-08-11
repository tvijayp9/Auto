/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.dao;

import java.sql.SQLException;

/**
 *
 * @author terrysu
 */
public interface UMGOrdersQuotesDAO {

    void insertOrderQuote(int orderId,String qrn) throws SQLException;
}
