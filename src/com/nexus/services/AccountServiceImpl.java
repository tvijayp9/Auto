/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.services;

import com.nexus.dao.MemberLogosDAO;
import com.nexus.dao.MicrocatDAO;
import com.nexus.dao.RegistrationDAO;
import com.nexus.dao.UserDAO;
import com.nexus.dao.TabsDAO;
import com.nexus.domain.Registration;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Terry
 */
@Transactional
public class AccountServiceImpl implements AccountService {

    private RegistrationDAO registrationDAO;
    private UserDAO userDAO;
    private TabsDAO tabsDAO;
    private MicrocatDAO microcatDAO;
    private MemberLogosDAO memberLogosDAO;

    public void updateMicrocatStatus(int microcatId) throws SQLException {
        microcatDAO.updateMicrocatStatus(microcatId, 1);
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

     public String getDefaultAction(int roleType) throws SQLException {
        return tabsDAO.getDefaultAction(roleType);
    }

    public Properties getMySections(int roleType) throws SQLException {
        return tabsDAO.getMySections(roleType);
    }

    public List getMyTabs(int roleType) throws SQLException {
        return tabsDAO.getMyTabs(roleType);
    }

    public String getLogoName(int nexusId) throws SQLException {
        return memberLogosDAO.findLogoName(nexusId);
    }

   /**
     * @return the userDAO
     */
    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * @param userDAO the userDAO to set
     */
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
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

    /**
     * @return the userDAO
     */
    public TabsDAO getTabsDAO() {
        return tabsDAO;
    }

    /**
     * @param userDAO the userDAO to set
     */
    public void setTabsDAO(TabsDAO tabsDAO) {
        this.tabsDAO = tabsDAO;
    }

    /**
     * @return the memberLogosDAO
     */
    public MemberLogosDAO getMemberLogosDAO() {
        return memberLogosDAO;
    }

    /**
     * @param memberLogosDAO the memberLogosDAO to set
     */
    public void setMemberLogosDAO(MemberLogosDAO memberLogosDAO) {
        this.memberLogosDAO = memberLogosDAO;
    }
}
