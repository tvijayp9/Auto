/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.Registration;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface RegistrationDAO {

    List findMembersList(String id, String search, String company, int start, int limit, String sidx, String sord) throws SQLException;

    int findMembersListCount(String id, String search, String company) throws SQLException;

    String findCompanyById(String id) throws SQLException;

    void updatePriceType(int priceType, int partner) throws SQLException;

    int findPriceTypeById(int id) throws SQLException;
}
