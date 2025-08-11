/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.services;

import com.nexus.domain.Microcat;
import com.nexus.domain.Partner;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface TradingPartnerService {

    List getMyPartnersList(String id,String search, String company,int start,int limit,String sidx,String sord) throws SQLException;

    List getMyPartnersList(int id,int start,int limit,String sidx,String sord) throws SQLException;

    int getMyPartnersListCount(String id,String search,String company) throws SQLException;

    int getMyPartnersListCount(int id) throws SQLException;

    List getMembersList(String id,String search,String company,int start,int limit,String sidx,String sord) throws SQLException;

    int getMembersListCount(String id,String search,String company) throws SQLException;

    void changePriceType(int priceType,String partners) throws SQLException;

    void createMicrocatAccounts(int newMemberId,List<Microcat> list) throws SQLException;

    int getMicrocatsCount(int nexusId) throws SQLException;

    List getMicrocats(int nexusId,int start,int limit,String sidx,String sord) throws SQLException;

    void createMicrocatAccount(int nexusId,String microcatAccountNumber,String microcatUsername,String microcatPassword) throws SQLException;

    Microcat getMicrocatById(int accountId) throws SQLException;

    void updateMicrocatAccount(int accountId,String microcatAccountNumber,String microcatUsername,String microcatPassword,int status) throws SQLException;

    void deleteMicrocats(String id)throws SQLException;
    
    public List<Partner> getMyPartnersList(int supplierId) throws SQLException;
}
