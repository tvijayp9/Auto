/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import com.nexus.domain.Quote;
import com.nexus.domain.TemplateOrderItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Terry
 */
public class QuotesDAOImpl implements QuotesDAO {

    Logger log=Logger.getLogger(QuotesDAOImpl.class);
    private SessionFactory sessionFactory;

    public String checkQRN(int id, int supplierId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("checkQRN...id..."+id+"...supplierId..."+supplierId);
        String selectStatement = "SELECT qrn from xy_quotes where buyer_id=? and supplier_id=? order by id desc";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setInt(2, supplierId);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("qrn");
        } else {
            return null;
        }
    }

    public void insertQuote(String qrn, String quoteName, int id, int supplierId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertQuote...qrn..."+qrn+"...quoteName..."+quoteName+"..supplierId.."+supplierId);
        String insertStatement = "insert into xy_quotes (qrn,qname,buyer_id,supplier_id) values(?,?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setString(1, qrn);
        ps.setString(2, quoteName);
        ps.setInt(3, id);
        ps.setInt(4, supplierId);
        ps.executeUpdate();
    }

    public int findQId(String qrn, String quoteName, int id, int supplierId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findQId...id..."+id+"...supplierId..."+supplierId+"..quoteName.."+quoteName);
        String selectStatement = "Select max(id) from xy_quotes where qrn=? and qname=? and buyer_id=? and supplier_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, qrn);
        ps.setString(2, quoteName);
        ps.setInt(3, id);
        ps.setInt(4, supplierId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public int findQuotesListCount(int id, int supplierId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findQuotesListCount...id..."+id+"...supplierId..."+supplierId);
        String selectStatement = "select count(id) from xy_quotes where buyer_id=? and supplier_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setInt(2, supplierId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List findQuotesList(int id, int supplierId, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("findQuotesList...id..."+id+"...supplierId..."+supplierId);
        String selectStatement = "select id,qrn,qname,comment from xy_quotes where buyer_id=? and supplier_id=? order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setInt(2, supplierId);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("id"));
            cell.add(rs.getString("qrn"));
            cell.add(rs.getString("qname"));
            cell.add(rs.getString("comment"));
            cell.add("");
            cell.add("");
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public void deleteQuotes(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("deleteQuotes...id..."+id);
        String updateStatement = "delete from xy_quotes where id = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public String findQRNByQuoteId(int quoteId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findQRNByQuoteId...iquoteId..."+quoteId);
        String selectStatement = "Select qrn from xy_quotes where id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, quoteId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getString("qrn");
    }

    public void getParametersForPrintQuote(int qid, HashMap reportParams) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("getParametersForPrintQuote...qid..."+qid);
        String selectStatement = "Select qrn,qname from xy_quotes where id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, qid);
        rs = ps.executeQuery();
        rs.next();
        reportParams.put("qrn", rs.getString("qrn"));
        reportParams.put("qname", rs.getString("qname"));
    }

    public Quote findQuoteDetailsByQid(int qid) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Quote quote = new Quote();
        List<TemplateOrderItem> list = new ArrayList();
        quote.setItems(list);
        log.info("findQuoteDetailsByQid...qid..."+qid);
        String selectStatement = "Select q.qrn,q.qname,q.create_date,qi.product_code,qi.price,qi.description,qi.status,qi.qty from xy_quotes q inner join xy_quotes_items qi on q.id=qi.qid where q.id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, qid);
        rs = ps.executeQuery();
        while (rs.next()) {
            if (list.size() == 0) {
                quote.setQrn(rs.getString("qrn"));
                quote.setQname(rs.getString("qname"));
                quote.setCreateDate(rs.getString("create_date"));
            }
            TemplateOrderItem toi = new TemplateOrderItem();
            toi.setQty(rs.getInt("qty"));
            toi.setProductCode(rs.getString("product_code"));
            toi.setDescription(rs.getString("description"));
            toi.setUnitPrice(rs.getBigDecimal("price"));
            toi.setUnits("Each");
            toi.setTax("GST");
            int status = rs.getInt("status");
            if (status == 0) {
                toi.setStatus("Ordered");
            } else {
                toi.setStatus(" ");
            }
            list.add(toi);
        }
        return quote;
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
