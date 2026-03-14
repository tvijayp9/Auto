/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.domain.TemplateOrderItem;
import java.math.BigDecimal;
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
public class QuotesItemsDAOImpl implements QuotesItemsDAO {

     Logger log=Logger.getLogger(QuotesItemsDAOImpl.class);
    private SessionFactory sessionFactory;

    public void insertQuoteItems(int QId, String gtin, BigDecimal price, String description, int qty) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertQuoteItems..qid.."+QId+"..gtin.."+gtin+"..price.."+price+"..description.."+description+"..qty.."+qty);
        String insertStatement = "insert into xy_quotes_items (qid,product_code,price,description,qty) values(?,?,?,?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, QId);
        ps.setString(2, gtin);
        ps.setBigDecimal(3, price);
        ps.setString(4, description);
        ps.setInt(5, qty);
        ps.executeUpdate();
    }

    public int findQuoteItemsByQuoteIdCount(int quoteId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findQuoteItemsByQuoteIdCount..quoteId.."+quoteId);
        String selectStatement = "SELECT count(*) FROM xy_quotes_items qi left join products_umg pu on qi.product_code=pu.product_code where qi.qid=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, quoteId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List findQuoteItemsByQuoteId(int quoteId, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        /*String selectStatement = "SELECT qi.id,pu.product_code,qi.description,pu.soh,ABS(qi.price),qi.status,qi.qty "
        + "from ((products_umg pu inner join xy_quotes_items qi on qi.product_code=pu.product_code) "
        + "left join umg_buyer_products_mapping ubpm on pu.product_code=ubpm.productcode) "
        + "left join xy_partner_link pl on pl.id=ubpm.partner_link_id where "
        + "((pl.Buyer_Id=? and pl.Supplier_Id=?) or ubpm.partner_link_id is null) and qi.qid=? "
        + "order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;*/
        log.info("1 findQuoteItemsByQuoteId..quoteId.."+quoteId);
        String selectStatement = "SELECT qi.id,qi.product_code,qi.description,pu.soh,ABS(qi.price),qi.status,qi.qty " + "from xy_quotes_items qi left join products_umg pu on qi.product_code=pu.product_code where qi.qid=? " + "order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;

        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, quoteId);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            cell.add(new Integer(rs.getInt("qty")).toString());
            String soh = rs.getString("soh");
            if ((soh == null) || (soh.equals(""))) {
                soh = "0";
            }
            cell.add(soh);
            //cell.add(rs.getString("gtin"));
            cell.add(rs.getString("product_code"));
            cell.add(rs.getString("description"));
            cell.add(rs.getString("ABS(qi.price)"));
            cell.add("");
            cell.add("");
            cell.add(rs.getString("status"));
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public List<TemplateOrderItem> findQuoteItemsByQuoteId(int quoteId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TemplateOrderItem> rows = new ArrayList();
        log.info("2 findQuoteItemsByQuoteId..quoteId.."+quoteId);
        String selectStatement = "SELECT qi.product_code,qi.description,qi.lead_time,ABS(qi.price),qi.status,qi.qty " + "from xy_quotes_items qi where qi.qid=? " + "order by qi.product_code";

        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, quoteId);
        rs = ps.executeQuery();
        while (rs.next()) {
            TemplateOrderItem toi = new TemplateOrderItem(rs.getBigDecimal("ABS(qi.price)"), rs.getString("product_code"), rs.getString("description"),  rs.getInt("lead_time"), rs.getInt("qty"), rs.getString("status"));
            rows.add(toi);
        }
        return rows;
    }

    public ShoppingCartItem findProductDetailsForQuoteShoppingCart(String itemId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ShoppingCartItem sci = null;
        log.info("findProductDetailsForQuoteShoppingCart..itemId.."+itemId);
        String selectStatement = "SELECT qi.product_code,qi.description,pu.soh,ABS(qi.price),qi.qty,qi.id " +
                "from xy_quotes_items qi left join products_umg pu on qi.product_code=pu.product_code where qi.id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, itemId);
        rs = ps.executeQuery();
        if (rs.next()) {
            BigDecimal unitPrice = rs.getBigDecimal("ABS(qi.price)");
            int quantity = rs.getInt("qty");
            BigDecimal price = unitPrice.multiply(new BigDecimal(quantity));
            BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = price.add(tax);
            String soh = rs.getString("soh");
            int realsoh = 0;
            if ((soh != null) && (!soh.equals(""))) {
                realsoh = new Integer(soh).intValue();
            }
            sci = new ShoppingCartItem(rs.getString("product_code"), rs.getString("description"), unitPrice, price, tax, quantity, cost, realsoh, rs.getInt("id"));
        }
        return sci;
    }

    public void deleteQuoteItem(int quoteItemId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("deleteQuoteItem..quoteItemId.."+quoteItemId);
        String updateStatement = "delete from xy_quotes_items where id = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, quoteItemId);
        ps.executeUpdate();
    }

    public List<String> findProductCodeByQuoteId(int quoteId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        log.info("findProductCodeByQuoteId..quoteId.."+quoteId);
        String selectStatement = "SELECT product_code FROM xy_quotes_items where qid=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, quoteId);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("product_code"));
        }
        return list;
    }

    public void updateQuoteItemQuantity(String id, int quantity) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateQuoteItemQuantity..id.."+id+"..quantity.."+quantity);
        String updateStatement = "update xy_quotes_items set qty=? where id=?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, quantity);
        ps.setInt(2, new Integer(id).intValue());
        ps.executeUpdate();
    }

    public void updateQuoteItemStatus(int quoteItemId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("updateQuoteItemStatus..quoteItemId.."+quoteItemId);
        String updateStatement = "update xy_quotes_items set status=0 where id=?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, quoteItemId);
        ps.executeUpdate();
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
