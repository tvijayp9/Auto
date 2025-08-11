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
public interface MicrocatOrderDAO {

    int findMicrocatOrderCountByAccountNumber(String accountNumber,String nexusId) throws SQLException;

    int findMaxMicrocatOrderIdByAccountNumber(String accountNumber,String SupNexusId) throws SQLException;

    void updateMicrocatOrderStatusByAccountNumber(String accountNumber,String SupNexusId) throws SQLException;
    
    int findScaniaOrderCountByAccountNumber(String accountNumber,String nexusId,String email) throws SQLException;
    
    public int findMaxScaniaOrderIdByDealerId(Integer dealerId,String email) throws SQLException;
    
    public void updateScaniaOrderStatusByAccountNumber(Integer dealer_id,String email) throws SQLException;
}
