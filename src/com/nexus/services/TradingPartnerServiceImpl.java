/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.services;

import com.nexus.dao.MicrocatDAO;
import com.nexus.dao.PartnerLinkDAO;
import com.nexus.dao.RegistrationDAO;
import com.nexus.domain.Microcat;
import com.nexus.domain.Partner;
import java.sql.SQLException;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Terry
 */
@Transactional
public class TradingPartnerServiceImpl implements TradingPartnerService {

    private PartnerLinkDAO partnerLinkDAO;
    private RegistrationDAO registrationDAO;
    private MicrocatDAO microcatDAO;

    /**
     * @return the partnerLinkDAO
     */
    public PartnerLinkDAO getPartnerLinkDAO() {
        return partnerLinkDAO;
    }

    /**
     * @param partnerLinkDAO the partnerLinkDAO to set
     */
    public void setPartnerLinkDAO(PartnerLinkDAO partnerLinkDAO) {
        this.partnerLinkDAO = partnerLinkDAO;
    }

    public List getMyPartnersList(String id, String search, String company, int start, int limit, String sidx, String sord) throws SQLException {
        return partnerLinkDAO.findMyPartnersList(id, search, company, start, limit, sidx, sord);
    }
    
    public List<Partner> getMyPartnersList(int supplierId) throws SQLException {
        return partnerLinkDAO.findPartnersBySupplierId(supplierId);
    }

    public int getMyPartnersListCount(String id, String search, String company) throws SQLException {
        return partnerLinkDAO.findMyPartnersListCount(id, search, company);
    }

    public List getMyPartnersList(int id, int start, int limit, String sidx, String sord) throws SQLException {
        return partnerLinkDAO.findMyPartnersList(id, start, limit, sidx, sord);
    }

    public int getMyPartnersListCount(int id) throws SQLException {
        return partnerLinkDAO.findMyPartnersListCount(id);
    }

    public List getMembersList(String id, String search, String company, int start, int limit, String sidx, String sord) throws SQLException {
        return registrationDAO.findMembersList(id, search, company, start, limit, sidx, sord);
    }

    public int getMembersListCount(String id, String search, String company) throws SQLException {
        return registrationDAO.findMembersListCount(id, search, company);
    }

    public void changePriceType(int priceType, String partners) throws SQLException {
        String[] parntersArray = partners.split("\\,");
        for (String partner : parntersArray) {
            registrationDAO.updatePriceType(priceType, new Integer(partner).intValue());
        }
    }

    public void createMicrocatAccounts(int newMemberId, List<Microcat> list) throws SQLException {
        for (Microcat microcat : list) {
            microcatDAO.insertMicrocatAccounts(newMemberId, microcat.getAccountNumber(), microcat.getUsername(), microcat.getPassword());
        }
    }

    public int getMicrocatsCount(int nexusId) throws SQLException {
        return microcatDAO.findMicrocatsCount(nexusId);
    }

    public List getMicrocats(int nexusId, int start, int limit, String sidx, String sord) throws SQLException {
        return microcatDAO.findMicrocats(nexusId, start, limit, sidx, sord);
    }

    public void createMicrocatAccount(int nexusId, String microcatAccountNumber, String microcatUsername, String microcatPassword) throws SQLException {
        microcatDAO.insertMicrocatAccounts(nexusId, microcatAccountNumber, microcatUsername, microcatPassword);
    }

    public Microcat getMicrocatById(int accountId) throws SQLException {
        return microcatDAO.findMicrocatById(accountId);
    }

    public void updateMicrocatAccount(int accountId, String microcatAccountNumber, String microcatUsername, String microcatPassword, int status) throws SQLException {
        microcatDAO.updateMicrocatAccount(accountId, microcatAccountNumber, microcatUsername, microcatPassword, status);
    }

    public void deleteMicrocats(String id) throws SQLException {
        String[] cats = id.split("\\,");
        for (String cat : cats) {
            microcatDAO.deleteMicrocat(new Integer(cat).intValue());
        }
    }

    /**
     * @return the registrationDAO
     */
    public RegistrationDAO getRegistrationDAO() {
        return registrationDAO;
    }

    /**
     * @param registrationDAO the registrationDAO to set
     */
    public void setRegistrationDAO(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    /**
     * @return the microcatDAO
     */
    public MicrocatDAO getMicrocatDAO() {
        return microcatDAO;
    }

    /**
     * @param microcatDAO the microcatDAO to set
     */
    public void setMicrocatDAO(MicrocatDAO microcatDAO) {
        this.microcatDAO = microcatDAO;
    }
}
