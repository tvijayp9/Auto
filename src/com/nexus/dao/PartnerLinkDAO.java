/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.Partner;
import com.nexus.domain.PartnerLink;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Terry
 */
public interface PartnerLinkDAO {

    List findMyPartnersList(String id, String search, String company, int start, int limit, String sidx, String sord) throws SQLException;

    int findMyPartnersListCount(String id, String search, String company) throws SQLException;

    List findMyPartnersList(int id, int start, int limit, String sidx, String sord) throws SQLException;

    int findMyPartnersListCount(int id) throws SQLException;

    PartnerLink findPartnerLinkByBuyerIdAndSupplierId(String buyerId, String supplierId) throws SQLException;

    int findSupplierIdByBuyerId(int buyerId) throws SQLException;
    
    public List<Partner> findPartnersBySupplierId(int supllierId);
}
