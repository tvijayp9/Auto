/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Terry
 */
public class RegistrationDAOImpl implements RegistrationDAO {

    Logger log=Logger.getLogger(RegistrationDAOImpl.class);
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

    public int findMembersListCount(String id, String search, String company) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findMembersListCount..id.."+id+"..search.."+search+"..company.."+company);
        String selectStatement = "SELECT COUNT(registration.ID) FROM registration,us_country where registration.COUNTRY = us_country.N_COUNTRY_ID and registration.member_type=?";
        if (search.equals("true")) {
            selectStatement += " and registration.company like ?";
        }
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, "open");
        if (search.equals("true")) {
            ps.setString(2, "%" + company + "%");
        }
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List findMembersList(String id, String search, String company, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("findMembersList..id.."+id+"..search.."+search+"..company.."+company);
        String selectStatement = "SELECT registration.ID,registration.COMPANY,registration.EMAIL,registration.PHNO,registration.STATE,registration.COMPANY_URL,us_country.vch_country_name FROM registration,us_country where registration.COUNTRY = us_country.N_COUNTRY_ID and registration.member_type=?";
        if (search.equals("true")) {
            selectStatement += " and registration.company like ?";
        }
        selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, "open");
        if (search.equals("true")) {
            ps.setString(2, "%" + company + "%");
        }
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("registration.ID"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("registration.company"));
            cell.add(rs.getString("registration.email"));
            cell.add(rs.getString("registration.phno"));
            cell.add(rs.getString("registration.state"));
            cell.add(rs.getString("vch_country_name"));
            cell.add(rs.getString("registration.company_url"));
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public String findCompanyById(String id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
         log.info("findCompanyById..id.."+id);
        String selectStatement = "select company from registration where id = ?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, id);
        rs = ps.executeQuery();
        rs.next();
        return rs.getString("company");
    }

    public void updatePriceType(int priceType, int partner) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updatePriceType..priceType.."+priceType+"..partner."+partner);
        String updateStatement = "UPDATE registration SET member_type=? where id= ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setString(1, new Integer(priceType).toString());
        ps.setInt(2, partner);
        ps.executeUpdate();
    }

    public int findPriceTypeById(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findPriceTypeById..id.."+id);
        String selectStatement = "select member_type from registration where id = ?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        rs.next();
        return new Integer(rs.getString("member_type")).intValue();
    }

}
