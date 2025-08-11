/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.Microcat;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface MicrocatDAO {

    Microcat findMicroInfo(int id) throws SQLException;

    void insertMicrocatAccounts(int newMemberId, String accountNumber, String username, String password) throws SQLException;

    void updateMicrocatStatus(int microcatId, int status) throws SQLException;

    Microcat findMicroInfoByTime(int id, Timestamp t) throws SQLException;

    void updateMicrocatTime(int microcatId, Timestamp t) throws SQLException;

    int findMicrocatsCount(int nexusId) throws SQLException;

    List findMicrocats(int nexusId, int start, int limit, String sidx, String sord) throws SQLException;

    Microcat findMicrocatById(int accountId) throws SQLException;

    void updateMicrocatAccount(int accountId, String microcatAccountNumber, String microcatUsername, String microcatPassword, int status) throws SQLException;

    void deleteMicrocat(int id)throws SQLException;
    
    Microcat findScaniaInfo(int id) throws SQLException;
    
    Microcat findScaniaInfoByTime(int id, Timestamp t) throws SQLException;
    
    void updateScaniaTime(int microcatId, Timestamp t) throws SQLException;
    
    void updateScaniaStatus(int microcatId, int status) throws SQLException;
}
