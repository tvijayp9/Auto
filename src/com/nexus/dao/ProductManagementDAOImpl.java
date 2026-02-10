/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.JQGridRow;
import com.nexus.domain.JQGridRow1;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.domain.TemplateOrderItem;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Vijay Thumma
 */
public class ProductManagementDAOImpl implements ProductManagementDAO {
    private static final String UNDERSCORE = "_";

    Logger log = Logger.getLogger(ProductManagementDAOImpl.class);
    private SessionFactory sessionFactory;

    public boolean uploadParts(String uploadFile, String filePath, String productTable) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        File temp_Dir = new File(filePath);
        String _tempDirPath = temp_Dir.getAbsolutePath();
        _tempDirPath = _tempDirPath.replaceAll("\\\\", "\\\\\\\\");
        System.out.println("_tempDirPath:" + _tempDirPath + "..fileName=" + filePath);
        String sql = "LOAD DATA LOCAL INFILE '" + _tempDirPath + "' REPLACE INTO TABLE "+productTable+" FIELDS TERMINATED BY '@#$%' ESCAPED BY '^' LINES TERMINATED BY '\n' (site_name,category_name,product_code,description,price1,supplier_id)";
        System.out.println("sql:" + sql);
        ps = connection.prepareStatement(sql);
        int updateRecords = ps.executeUpdate();
        if(updateRecords > 0){
            return true;
        } else{
            return false;
        }
    }
    
    public boolean uploadMRLParts(String uploadFile, String filePath, String productTable) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        File temp_Dir = new File(filePath);
        String _tempDirPath = temp_Dir.getAbsolutePath();
        _tempDirPath = _tempDirPath.replaceAll("\\\\", "\\\\\\\\");
        System.out.println("_tempDirPath:" + _tempDirPath + "..fileName=" + filePath);
        String sql = "LOAD DATA LOCAL INFILE '" + _tempDirPath + "' REPLACE INTO TABLE "+productTable+" FIELDS TERMINATED BY '@#$%' ESCAPED BY '^' LINES TERMINATED BY '\n' (product_code,description,price1,supplier_id)";
        System.out.println("sql:" + sql);
        ps = connection.prepareStatement(sql);
        int updateRecords = ps.executeUpdate();
        if(updateRecords > 0){
            return true;
        } else{
            return false;
        }
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

    public boolean updatePart(String siteName, String catName, String partNumber, String description, String price, String productTable) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        String sql = "UPDATE "+productTable+" set ";
        if(StringUtils.isEmpty(description)){
            sql =sql + "price1 = ? where site_name = ? and category_name = ? and product_code = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, StringUtils.trim(price));
        } else if(StringUtils.isEmpty(price)){
            sql =sql + "description = ? where site_name = ? and category_name = ? and product_code = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, StringUtils.trim(description));
        }
        log.info("inside updatePart siteName="+siteName+" ... catName="+catName+"...partNumber="+partNumber+"..description="+description+"...price="+price+"..productTable="+productTable);
        ps.setString(2, StringUtils.trim(siteName));
        ps.setString(3, StringUtils.trim(catName));
        ps.setString(4, StringUtils.trim(partNumber));
        int updateRecords = ps.executeUpdate();
        if(updateRecords > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean deletePart(String siteName, String catName, String partNumber, String productTable) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        log.info("inside deletePart siteName="+siteName+"...catName="+catName+"...partNumber="+partNumber+"..productTable="+productTable);
        String sql = "DELETE FROM "+productTable+ " where site_name = ? and category_name = ? and product_code = ? ";
        ps = connection.prepareStatement(sql);
        ps.setString(1, StringUtils.trim(siteName));
        ps.setString(2, StringUtils.trim(catName));
        ps.setString(3, StringUtils.trim(partNumber));
        int updateRecords = ps.executeUpdate();
        if(updateRecords > 0){
            return true;
        } else{
            return false;
        }
    }
    
    public int findSupplierProductsCount(String searchFor, String searchIn, String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findSupplierProductsCount...searchFor.." + searchFor + "..searchIn.." + searchIn + "..product_table_name.." + product_table_name);
        String selectStatement = "SELECT count(*) from " + product_table_name;
        if (StringUtils.isNotEmpty(searchFor)) {
            switch (new Integer(searchIn).intValue()) {
                case 1:
                    selectStatement += " where site_name like ?";
                    break;
                case 2:
                    selectStatement += " where category_name like ?";
                    break;
                case 3:
                    selectStatement += " where product_code like ?";
                    break;
                case 4:
                    selectStatement += " where description like ?";
                    break;
            }
        }
        ps = connection.prepareStatement(selectStatement);
        if (searchFor != null) {
            if (!searchFor.trim().equals("")) {
                ps.setString(1, "%" + searchFor + "%");
            }
        }
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    public int findSupplierProductsBySiteCount(String siteName, String categoryName, String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findSupplierProductsBySiteCount...siteName.." + siteName + "..categoryName.." + categoryName + "..product_table_name.." + product_table_name);
        String selectStatement = "SELECT count(*) from " + product_table_name;
                    selectStatement += " where site_name like ? ";
        if(StringUtils.isNotEmpty(categoryName)){
            selectStatement += " and category_name like ?";
        }    
        ps = connection.prepareStatement(selectStatement);
        if (!siteName.trim().equals("")) {
            ps.setString(1, "%" + siteName + "%");
        }
        if(StringUtils.isNotEmpty(categoryName)){
            ps.setString(2, "%" + categoryName + "%");
        }
      
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List findSupplierProducts(String searchFor, String searchIn, int start, int limit, String sidx, String sord, String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow1> rows = new ArrayList();
        log.info("findSupplierProducts....searchFor.." + searchFor + "..searchIn.." + searchIn + "...product_table_name..." + product_table_name);

        String selectStatement = "SELECT soh, site_name, category_name, product_code, description, ABS(price1) from " + product_table_name;
        if (StringUtils.isNotEmpty(searchFor)) {
            switch (new Integer(searchIn).intValue()) {
                case 1:
                    selectStatement += " where site_name like ?";
                    break;
                case 2:
                    selectStatement += " where category_name like ?";
                    break;
                case 3:
                    selectStatement += " where product_code like ?";
                    break;
                case 4:
                    selectStatement += " where description like ?";
                    break;
            }
        }
        selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        if (StringUtils.isNotEmpty(searchFor)) {
            ps.setString(1, "%" + searchFor + "%");
        }
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow1 row = new JQGridRow1();
            String siteName = StringUtils.strip(rs.getString("site_name"));
            String categoryName = StringUtils.strip(rs.getString("category_name"));
            String productCode = StringUtils.strip(rs.getString("product_code"));
            row.setId(new StringBuilder(siteName).append(UNDERSCORE).append(categoryName).append(UNDERSCORE).append(productCode).toString());
            List<String> cell = new ArrayList();
            cell.add(rs.getString("soh"));
            cell.add(siteName);
            cell.add(categoryName);
            cell.add(productCode);
            cell.add(rs.getString("description"));
            cell.add(rs.getString("ABS(price1)"));
            cell.add("");
            cell.add("");
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }
    
    public List findSupplierProductsBySiteName(String sitename, String categoryname, int start, int limit, String sidx, String sord, String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow1> rows = new ArrayList();
        log.info("findSupplierProducts....siteName.." + sitename + "..categoryName.." + categoryname + "...product_table_name..." + product_table_name);

        String selectStatement = "SELECT soh, site_name, category_name, product_code, description, ABS(price1) from " + product_table_name;
        selectStatement += " where site_name like ? ";
        if(StringUtils.isNotEmpty(categoryname)){
            selectStatement += " and category_name like ?";
        }
        selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        if (!sitename.trim().equals("")) {
            ps.setString(1, "%" + sitename + "%");
        }
        if(StringUtils.isNotEmpty(categoryname)){
            ps.setString(2, "%" + categoryname + "%");
        }
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow1 row = new JQGridRow1();
            String siteName = StringUtils.strip(rs.getString("site_name"));
            String categoryName = StringUtils.strip(rs.getString("category_name"));
            String productCode = StringUtils.strip(rs.getString("product_code"));
            row.setId(new StringBuilder(siteName).append(UNDERSCORE).append(categoryName).append(UNDERSCORE).append(productCode).toString());
            List<String> cell = new ArrayList();
            cell.add(rs.getString("soh"));
            cell.add(siteName);
            cell.add(categoryName);
            cell.add(productCode);
            cell.add(rs.getString("description"));
            cell.add(rs.getString("ABS(price1)"));
            cell.add("");
            cell.add("");
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }
    
    public ShoppingCartItem findProductDetailsForShoppingCart(String siteName, String categoryName, String productCode, String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ShoppingCartItem sci = null;
        log.info("findProductDetailsForShoppingCart....siteName.."+siteName+"..categoryName.."+categoryName+"..productCode.."+productCode+"..product_table_name"+product_table_name);
        
        String selectStatement = "SELECT site_name, category_name, product_code, description, soh, ABS(price1) from " +
                product_table_name+ " where site_name = ? and category_name = ? and product_code = ?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, siteName);
        ps.setString(2, categoryName);
        ps.setString(3, productCode);
        rs = ps.executeQuery();
        if (rs.next()) {
            BigDecimal unitPrice = rs.getBigDecimal("ABS(price1)");
            BigDecimal tax = unitPrice.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = unitPrice.add(tax);
            sci = new ShoppingCartItem(rs.getString("site_name"),rs.getString("category_name"),rs.getString("product_code"), rs.getString("description"), unitPrice, unitPrice, tax, 1, cost, rs.getInt("soh"));
        }
        return sci;
    }
    
    public TemplateOrderItem findProductDetailsForTemplate(String siteName, String categoryName, String productCode, String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        TemplateOrderItem toi = null;
        log.info("findProductDetailsForTemplate....siteName.."+siteName+"..categoryName.."+categoryName+"..productCode.."+productCode+"..product_table_name.."+product_table_name);
        String selectStatement = "SELECT site_name, category_name, product_code, description, soh, ABS(price1) from " +
                product_table_name+ " where site_name = ? and category_name = ? and product_code = ?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, siteName);
        ps.setString(2, categoryName);
        ps.setString(3, productCode);
        rs = ps.executeQuery();
        if (rs.next()) {
            BigDecimal unitPrice = rs.getBigDecimal("ABS(price1)");
            BigDecimal tax = unitPrice.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = unitPrice.add(tax);
            toi = new TemplateOrderItem(rs.getString("site_name"),rs.getString("category_name"),rs.getString("product_code"), rs.getString("description"), unitPrice,unitPrice, tax, 1, cost, rs.getInt("soh"));
        }
        return toi;
    }
    public List<String> findSupplierProductsSiteNames(String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> siteNames = new ArrayList<String>();
        String selectStatement = "SELECT DISTINCT(site_name) from " + product_table_name + " order by site_name ASC";
        ps = connection.prepareStatement(selectStatement);
        rs = ps.executeQuery();
        while (rs.next()) {
            siteNames.add(StringUtils.trim(rs.getString("site_name")));
        }
        return siteNames;
    }
    
    public List<String> findSupplierProductsCategoryNames(String siteName, String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> categoryNames = new ArrayList<String>();
        String selectStatement = "SELECT DISTINCT(category_name) from " + product_table_name + " WHERE site_name = ? order by category_name ASC";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, siteName);
        rs = ps.executeQuery();
        while (rs.next()) {
            categoryNames.add(StringUtils.trim(rs.getString("category_name")));
        }
        return categoryNames;
    }
    
    public int findQuoteItemsByQuoteIdCount(int quoteId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findQuoteItemsByQuoteIdCount..quoteId.."+quoteId);
        String selectStatement = "SELECT count(*) FROM xy_quotes_items qi where qi.qid=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, quoteId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    public String findQuoteNameByQuoteId(int quoteId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findQuoteNameByQuoteId..quoteId.." + quoteId);
        String selectStatement = "SELECT qname FROM xy_quotes where id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, quoteId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getString(1);
    }
    
    public List findQuoteItemsByQuoteId(int quoteId, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("1 findQuoteItemsByQuoteId..quoteId.."+quoteId);
        String selectStatement = "SELECT qi.id,qi.product_code,qi.description,qi.price,qi.qty from xy_quotes_items qi where qi.qid=? order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;

        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, quoteId);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            int qty = rs.getInt("qty");
            cell.add(new Integer(qty).toString());
            cell.add(rs.getString("product_code"));
            cell.add(rs.getString("description"));
            BigDecimal unitPrice = rs.getBigDecimal("price");
            BigDecimal price = unitPrice.multiply(new BigDecimal(qty));
            BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = price.add(tax);
            
            cell.add(String.valueOf(unitPrice));
            cell.add(String.valueOf(price));
            cell.add(String.valueOf(tax));
            cell.add(String.valueOf(cost));
            
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
        TemplateOrderItem templateOrderItem = null;
        log.info("1 findQuoteItemsByQuoteId..quoteId.."+quoteId);
        String selectStatement = "SELECT qi.id, qi.product_code,qi.description,qi.price,qi.qty from xy_quotes_items qi where qi.qid=?";

        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, quoteId);
        rs = ps.executeQuery();
        while (rs.next()) {
           templateOrderItem = new TemplateOrderItem();
           templateOrderItem.setTemplateId(rs.getInt("id"));
            templateOrderItem.setProductCode(rs.getString("product_code"));
            int qty = rs.getInt("qty");
            BigDecimal unitPrice = rs.getBigDecimal("price");
            templateOrderItem.setQty(qty);
            templateOrderItem.setDescription(rs.getString("description"));
            templateOrderItem.setUnitPrice(unitPrice);
            
            BigDecimal price = unitPrice.multiply(new BigDecimal(qty));
            BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = price.add(tax);
            
            templateOrderItem.setPrice(price);
            templateOrderItem.setTotaltax(tax);
            templateOrderItem.setCost(cost);
            
            rows.add(templateOrderItem);
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
        String selectStatement = "SELECT qi.product_code,qi.description,qi.price,qi.qty,qi.id " +
                "from xy_quotes_items qi where qi.id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setString(1, itemId);
        rs = ps.executeQuery();
        if (rs.next()) {
            BigDecimal unitPrice = rs.getBigDecimal("price");
            int quantity = rs.getInt("qty");
            BigDecimal price = unitPrice.multiply(new BigDecimal(quantity));
            BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = price.add(tax);
            sci = new ShoppingCartItem(rs.getString("product_code"), rs.getString("description"), unitPrice, price, tax, quantity, cost, 0, rs.getInt("id"));
        }
        return sci;
    }
}
