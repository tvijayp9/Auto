/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Terry
 */
public class MemberLogosDAOImpl implements MemberLogosDAO {

    Logger log=Logger.getLogger(MemberLogosDAOImpl.class);
    private SessionFactory sessionFactory;

    public String findLogoName(int nexusId) throws SQLException{
        String logoName = null;
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findLogoName..nexusId.."+nexusId);
        String selectStatement = "select distinct logo_name from xy_member_logos ml,xy_partner_link pl where (pl.Buyer_Id=ml.Nexus_ID or pl.Supplier_Id=ml.Nexus_ID) and (pl.Buyer_Id=? or pl.Supplier_Id=?)";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, nexusId);
        ps.setInt(2, nexusId);
        rs = ps.executeQuery();
        if (rs.next()) {
            logoName = rs.getString("logo_name");
        }
        return logoName;
    }
    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
