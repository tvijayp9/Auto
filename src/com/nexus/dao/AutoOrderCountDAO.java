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
public interface AutoOrderCountDAO {

    int getAutoOrderNumber(String id,String supId) throws SQLException;

    void updateAutoOrderNumber(int number,String id,String supId) throws SQLException;

    void insertAutoOrderNumber(int number,String id,String supId) throws SQLException;
}
