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
 * @author Sunil
 */
public class RulesDAOImpl implements RulesDAO {

    Logger log=Logger.getLogger(RulesDAOImpl.class);
    private SessionFactory sessionFactory;

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

     public String findRuleValueMapping(String rule, int buyerid, int supid) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String desc = "";
         log.info("findRuleValueMapping  rule.. " + rule+"..buyerid.."+buyerid+"..supid.."+supid);
        String selectStatement = "select a.VALUE from xy_rules_values a,xy_rules_values_mapping b,xy_partner_link c,xy_rules d   " +
                "where a.RULE_ID = d.ID and d.DESCRIPTION = ? and a.ID = b.RULES_VALUES_ID and b.XY_PLINK_ID = c.ID and c.Buyer_Id = ? and c.Supplier_Id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, rule);
        ps.setInt(2, buyerid);
        ps.setInt(3, supid);
        rs = ps.executeQuery();
        log.info("findRuleValueMapping= " + selectStatement);
        while (rs.next()) {
            desc = rs.getString("value");
        }
        return desc;

    }

      public String findRuleValueMapping(String rule, int nexusid) throws SQLException {
         log.info("findRuleValueMapping  rule:" + rule+"..nexusId..."+nexusid);
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String desc = "";
        String selectStatement = "select a.VALUE from xy_rules_values a,xy_rules_values_mapping b,xy_rules d   " +
                "where a.RULE_ID = d.ID and d.DESCRIPTION = ? and a.ID = b.RULES_VALUES_ID and b.XY_PLINK_ID=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, rule);
        ps.setInt(2, nexusid);
        rs = ps.executeQuery();
        log.info("selectStatement " + selectStatement);
        while (rs.next()) {
            desc = rs.getString("value");
        }
        return desc;

    }
}
