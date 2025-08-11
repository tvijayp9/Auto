/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Terry
 */
public interface TabsDAO {

    int findTabsCount(int siteid) throws SQLException;

    List<JQGridRow> findTabs(int siteid, int start, int limit, String sidx, String sord) throws SQLException;

    int findTabsCountByRoleId(int roleId) throws SQLException;

    List<JQGridRow> findTabsByRoleId(int roleId, int start, int limit, String sidx, String sord) throws SQLException;

    int findtMoreTabsCountByRoleId(int roleId,int userType) throws SQLException;

    List<JQGridRow> findMoreTabsByRoleId(int siteid,int roleId, int start, int limit, String sidx, String sord) throws SQLException;

    //Properties getMySections(String loginid) throws SQLException;
    Properties getMySections(int roleType) throws SQLException;

    //List getMyTabs(String loginid) throws SQLException;

    List getMyTabs(int roleType) throws SQLException;

//    String getDefaultAction(String loginid) throws SQLException;

     String getDefaultAction(int roleType) throws SQLException;
}
