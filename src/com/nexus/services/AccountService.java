/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.services;

import com.nexus.domain.Registration;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Terry
 */
public interface AccountService {

    void updateMicrocatStatus(int microcatId) throws SQLException;

     String getDefaultAction(int roleType) throws SQLException;

    //Properties getMySections(String loginid) throws SQLException;
    Properties getMySections(int roleType) throws SQLException;

    //List getMyTabs(String loginid) throws SQLException;
    List getMyTabs(int roleType) throws SQLException;

    String getLogoName(int nexusId) throws SQLException;

}
