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
public class FavouriteLineItemsDAOImpl implements FavouriteLineItemsDAO {

    Logger log=Logger.getLogger(FavouriteLineItemsDAOImpl.class);
    private SessionFactory sessionFactory;

    public void insertFavouriteLineItems(int favouriteOrderId, String gtin) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("insertFavouriteLineItems..favouriteOrderId.."+favouriteOrderId+"..gtin.."+gtin);
        String insertStatement = "insert into xy_fav_line_items (fav_order_id,gtin) values(?,?)";
        ps = connection.prepareStatement(insertStatement);
        ps.setInt(1, favouriteOrderId);
        ps.setString(2, gtin);
        ps.executeUpdate();
    }

    public int findFavouriteOrderListCount(int id, int supplierId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findFavouriteOrderListCount..id.."+id+"..supplierId.."+supplierId);
        String selectStatement = "select count(id) from xy_favourite_order where buyer_id=? and supplier_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setInt(2, supplierId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List findFavouriteOrderList(int id, int supplierId, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("findFavouriteOrderList..id.."+id+"..supplierId.."+supplierId);
        String selectStatement = "select id,favouritename from xy_favourite_order where buyer_id=? and supplier_id=? order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setInt(2, supplierId);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("id"));
            cell.add(rs.getString("favouritename"));
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public int findTemplateOrderItemsByTemplateIdCount(int templateId,String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findTemplateOrderItemsByTemplateIdCount..templateId.."+templateId+"..product_table_name..."+product_table_name);
        //String selectStatement = "SELECT count(*) FROM xy_fav_line_items fli inner join products_umg pu on fli.gtin=pu.product_code where fli.fav_order_id=?";
        String selectStatement = "SELECT count(*) FROM xy_fav_line_items fli inner join "+product_table_name+" pu on fli.gtin=pu.product_code where fli.fav_order_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, templateId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List findTemplateOrderItemsByTemplateId(int templateId, String priceType, int id, int supplierId, int start, int limit, String sidx, String sord,String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
         log.info("findTemplateOrderItemsByTemplateId..templateId.."+templateId+"..id.."+id+"..supplierId.."+supplierId+"...product_table_name..."+product_table_name);
        String selectStatement = "SELECT distinct(fli.id),fli.gtin,pu.description,ABS(" + priceType + "),pu.soh,ABS(ubpm.price),moli.description,ABS(moli.price) " +
//                "from (((xy_fav_line_items fli left join products_umg pu on fli.gtin=pu.product_code) " +
                 "from (((xy_fav_line_items fli left join "+product_table_name+" pu on fli.gtin=pu.product_code) " +
                "left join umg_buyer_products_mapping ubpm on pu.product_code=ubpm.productcode) " +
                "left join xy_partner_link pl on pl.id=ubpm.partner_link_id)left join mcat_order_line_items moli on fli.gtin=moli.part_num where " +
                "((pl.Buyer_Id=? and pl.Supplier_Id=?) or ubpm.partner_link_id is null) and fli.fav_order_id=? " +
                "order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;

        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setInt(2, supplierId);
        ps.setInt(3, templateId);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            String gtin = rs.getString("fli.gtin");
            String pudescription = rs.getString("pu.description");
            String pricetype = rs.getString("ABS(" + priceType + ")");
            String soh = rs.getString("pu.soh");
            String ubpmprice = rs.getString("ABS(ubpm.price)");
            String molidescription = rs.getString("moli.description");
            String moliprice = rs.getString("ABS(moli.price)");
            if (pudescription == null && pricetype == null && soh == null && ubpmprice == null) {
                cell.add("0");
                cell.add(gtin);
                cell.add(molidescription);
                cell.add(moliprice);
            } else {
                cell.add(soh);
                cell.add(gtin);
                cell.add(pudescription);
                if (ubpmprice == null) {
                    cell.add(pricetype);
                } else {
                    cell.add(ubpmprice);
                }
            }
            cell.add("");
            cell.add("");
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public void deleteTemplateOrderItem(int templateOrderItemId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("deleteTemplateOrderItem..templateOrderItemId.."+templateOrderItemId);
        String updateStatement = "delete from xy_fav_line_items where id = ?";
        ps = connection.prepareStatement(updateStatement);
        ps.setInt(1, templateOrderItemId);
        ps.executeUpdate();
    }

    public String findProductCodeByTemplateOrderItemId(int templateOrderItemId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findProductCodeByTemplateOrderItemId..templateOrderItemId.."+templateOrderItemId);
        String selectStatement = "SELECT gtin FROM xy_fav_line_items where id=?;";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, templateOrderItemId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getString("gtin");
    }

    public List<String> findProductCodeByTemplateId(int templateId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        log.info("findProductCodeByTemplateId..templateId.."+templateId);
        String selectStatement = "SELECT gtin FROM xy_fav_line_items where fav_order_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, templateId);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("gtin"));
        }
        return list;
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
