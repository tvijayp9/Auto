/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.dao;

import com.nexus.domain.InvoiceLineItem;
import com.nexus.domain.JQGridRow;
import com.nexus.domain.JQGridRow1;
import com.nexus.domain.PrintOrder;
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
public class ProductsUMGDAOImpl implements ProductsUMGDAO {

    Logger log=Logger.getLogger(ProductsUMGDAOImpl.class);
    private SessionFactory sessionFactory;

    public int findSupplierProductsCount(String searchFor, String searchIn,String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findSupplierProductsCount...searchFor.."+searchFor+"..searchIn.."+searchIn+"..product_table_name.."+product_table_name);
//        String selectStatement = "SELECT count(*) from products_umg";
        String selectStatement = "SELECT count(*) from "+product_table_name;
        if (searchFor != null) {
            if (!searchFor.trim().equals("")) {
                switch (new Integer(searchIn).intValue()) {
                    case 1:
                        selectStatement += " where product_code like ?";
                        break;
                    case 2:
                        selectStatement += " where description like ?";
                        break;
                }
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

    public List findSupplierProducts(String price, int id, int supplierId, String searchFor, String searchIn, int start, int limit, String sidx, String sord,String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow1> rows = new ArrayList();
        log.info("findSupplierProducts...price.."+price+"..id.."+id+"..supplierId.."+supplierId+"..searchFor.."+searchFor+"..searchIn.."+searchIn+"...product_table_name..."+product_table_name);
       // log.info("findSupplierProducts....price.."+price+"..id.."+id+"..supplierId..."+supplierId);
//        String selectStatement = "SELECT  distinct(product_code),description,ABS(" + price + "),soh, ABS(price) " +
//                "from (products_umg pu left join umg_buyer_products_mapping ubpm on pu.product_code=ubpm.productcode) " +
//                "left join xy_partner_link pl on pl.id=ubpm.partner_link_id where ((pl.Buyer_Id=? and pl.Supplier_Id=?) " +
//                //"or ubpm.partner_link_id is null )"; shanged by sunil for not displaying some products because of query
//                "or ubpm.partner_link_id is null or ubpm.partner_link_id is not null)";
        String selectStatement = "SELECT distinct(product_code),description,ABS(" + price + "),soh, ABS(price) from " +
//                "products_umg pu left join (umg_buyer_products_mapping ubpm ,xy_partner_link pl)" +
                product_table_name+" pu left join (umg_buyer_products_mapping ubpm ,xy_partner_link pl)" +
                "on pu.product_code=ubpm.productcode and pl.id=ubpm.partner_link_id " +
                "and pl.Buyer_Id=? and pl.Supplier_Id=?";
        if (searchFor != null) {
            if (!searchFor.trim().equals("")) {
                switch (new Integer(searchIn).intValue()) {
                    case 1:
                        selectStatement += " where product_code like ?";
                        break;
                    case 2:
                        selectStatement += " where description like ?";
                        break;
                }
            }
        }
        selectStatement += " order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
       // log.info(selectStatement);
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setInt(2, supplierId);
        if (searchFor != null) {
            if (!searchFor.trim().equals("")) {
                ps.setString(3, "%" + searchFor + "%");
            }
        }
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow1 row = new JQGridRow1();
            row.setId(rs.getString("product_code"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("soh"));
            //cell.add(rs.getString("gtin"));
            cell.add(rs.getString("product_code"));
            cell.add(rs.getString("description"));
            if (rs.getString("ABS(price)") == null) {
                cell.add(rs.getString("ABS(" + price + ")"));
            } else {
                cell.add(rs.getString("ABS(price)"));
            }

            cell.add("");
            cell.add("");
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public ShoppingCartItem findProductDetailsForShoppingCart(int id, int supplierId, String priceType, String productCode,String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ShoppingCartItem sci = null;
        log.info("findProductDetailsForShoppingCart....id.."+id+"..supplierId.."+supplierId+"..priceType.."+priceType+"..productCode.."+productCode+"..product_table_name"+product_table_name);
        // log.info("findProductDetailsForShoppingCart....productCode.."+productCode+"..id.."+id+"..supplierId..."+supplierId);
//        String selectStatement = "SELECT distinct(product_code),description,ABS(" + priceType + "),soh, ABS(price) " +
//                "from (products_umg pu left join umg_buyer_products_mapping ubpm on pu.product_code=ubpm.productcode) " +
//                "left join xy_partner_link pl on pl.id=ubpm.partner_link_id where ((pl.Buyer_Id=? and pl.Supplier_Id=?) " +
//                "or ubpm.partner_link_id is null) and product_code=?";
        String selectStatement = "SELECT distinct(product_code),description,ABS(" + priceType + "),soh, ABS(price) from " +
//                "products_umg pu left join (umg_buyer_products_mapping ubpm ,xy_partner_link pl)" +
                product_table_name+" pu left join (umg_buyer_products_mapping ubpm ,xy_partner_link pl)" +
                "on pu.product_code=ubpm.productcode and pl.id=ubpm.partner_link_id " +
                "and pl.Buyer_Id=? and pl.Supplier_Id=? where product_code=?";
//        String selectStatement = "SELECT product_code,description,ABS(price1),soh from products_umg WHERE product_code=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setInt(2, supplierId);
        ps.setString(3, productCode);
       // log.info("select stmt.."+selectStatement);
        rs = ps.executeQuery();
        if (rs.next()) {
            BigDecimal unitPrice;
            if (rs.getString("ABS(price)") == null) {
                unitPrice = rs.getBigDecimal("ABS(" + priceType + ")");
            } else {
                unitPrice = rs.getBigDecimal("ABS(price)");
            }
            BigDecimal tax = unitPrice.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = unitPrice.add(tax);
            sci = new ShoppingCartItem(rs.getString("product_code"), rs.getString("description"), unitPrice, unitPrice, tax, 1, cost, rs.getInt("soh"));
        }
        return sci;
    }

    public ShoppingCartItem findProductDetailsForShoppingCart(int id, int supplierId, String priceType, String productCode, int quantity,String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ShoppingCartItem sci = null;
        log.info("findProductDetailsForShoppingCart....id.."+id+"..supplierId.."+supplierId+"..priceType.."+priceType+"..productCode.."+productCode+"..quantity.."+quantity+"..product_table_name.."+product_table_name);
//        String selectStatement = "SELECT distinct(product_code),description,ABS(" + priceType + "),soh, ABS(price) " +
//                "from (products_umg pu left join umg_buyer_products_mapping ubpm on pu.product_code=ubpm.productcode) " +
//                "left join xy_partner_link pl on pl.id=ubpm.partner_link_id where ((pl.Buyer_Id=? and pl.Supplier_Id=?) " +
//                "or ubpm.partner_link_id is null) and product_code=?";
        String selectStatement = "SELECT distinct(product_code),description,ABS(" + priceType + "),soh, ABS(price) from " +
//                "products_umg pu left join (umg_buyer_products_mapping ubpm ,xy_partner_link pl)" +
                product_table_name+" pu left join (umg_buyer_products_mapping ubpm ,xy_partner_link pl)" +
                "on pu.product_code=ubpm.productcode and pl.id=ubpm.partner_link_id " +
                "and pl.Buyer_Id=? and pl.Supplier_Id=? where product_code=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setInt(2, supplierId);
        ps.setString(3, productCode);
        rs = ps.executeQuery();
        if (rs.next()) {
            BigDecimal unitPrice;
            if (rs.getString("ABS(price)") == null) {
                unitPrice = rs.getBigDecimal("ABS(" + priceType + ")");
            } else {
                unitPrice = rs.getBigDecimal("ABS(price)");
            }
            BigDecimal price = unitPrice.multiply(new BigDecimal(quantity));
            BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = price.add(tax);
            sci = new ShoppingCartItem(rs.getString("product_code"), rs.getString("description"), unitPrice, price, tax, quantity, cost, rs.getInt("soh"));
        }
        return sci;
    }

    public TemplateOrderItem findProductDetailsForTemplate(int id, int supplierId, String priceType, String productCode,String product_table_name) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        TemplateOrderItem toi = null;
        log.info("findProductDetailsForTemplate....id.."+id+"..supplierId.."+supplierId+"..priceType.."+priceType+"..productCode.."+productCode+"..product_table_name.."+product_table_name);
//        String selectStatement = "SELECT distinct(product_code),description,ABS(" + priceType + "),soh, ABS(price) " +
//                "from (products_umg pu left join umg_buyer_products_mapping ubpm on pu.product_code=ubpm.productcode) " +
//                "left join xy_partner_link pl on pl.id=ubpm.partner_link_id where ((pl.Buyer_Id=? and pl.Supplier_Id=?) " +
//                "or ubpm.partner_link_id is null) and product_code=?";
        String selectStatement = "SELECT distinct(product_code),description,ABS(" + priceType + "),soh, ABS(price) from " +
//                "products_umg pu left join (umg_buyer_products_mapping ubpm ,xy_partner_link pl)" +
                product_table_name+" pu left join (umg_buyer_products_mapping ubpm ,xy_partner_link pl)" +
                "on pu.product_code=ubpm.productcode and pl.id=ubpm.partner_link_id " +
                "and pl.Buyer_Id=? and pl.Supplier_Id=? where product_code=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, id);
        ps.setInt(2, supplierId);
        ps.setString(3, productCode);
        rs = ps.executeQuery();
        if (rs.next()) {
            BigDecimal unitPrice;
            if (rs.getString("ABS(price)") == null) {
                unitPrice = rs.getBigDecimal("ABS(" + priceType + ")");
            } else {
                unitPrice = rs.getBigDecimal("ABS(price)");
            }
            BigDecimal tax = unitPrice.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = unitPrice.add(tax);
            toi = new TemplateOrderItem(rs.getString("product_code"), rs.getString("description"), unitPrice,unitPrice, tax, 1, cost, rs.getInt("soh"));
            //toi = new TemplateOrderItem(rs.getString("product_code"), rs.getString("description"), unitPrice, rs.getInt("soh"),1);
        }
        return toi;
    }

    public int findOrderItemsCount(String orderId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        log.info("findOrderItemsCount....orderId.."+orderId);
        //String selectStatement = "select count(*) from xy_order_line_items_umg oliu inner join products_umg pu on oliu.product_code=pu.product_code where oliu.order_id=?";
        String selectStatement = "select count(*) from xy_order_line_items_umg where order_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(orderId).intValue());
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List findOrderItems(String orderId, int start, int limit, String sidx, String sord) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("1 findOrderItems....orderId.."+orderId+"...start="+start+"...limit="+limit+"....sidx="+sidx+"....sord="+sord);
        /* String selectStatement = "select oliu.id,pu.product_code,pu.description,oliu.qty,oliu.price " +
        "from xy_order_line_items_umg oliu inner join products_umg pu on oliu.product_code=pu.product_code " +
        "where oliu.order_id=? order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;*/
        String selectStatement = "select id,product_code,description,qty,price " +
                "from xy_order_line_items_umg where order_id=? order by " + sidx + " " + sord + " LIMIT " + start + "," + limit;
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, new Integer(orderId).intValue());
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("product_code"));
            cell.add(rs.getString("description"));
            int quantity = rs.getInt("qty");
            cell.add(new Integer(quantity).toString());
            BigDecimal unitPrice = rs.getBigDecimal("price");
            BigDecimal price = unitPrice.multiply(new BigDecimal(quantity));
            cell.add(price.toString());
            BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            cell.add(tax.toString());
            BigDecimal cost = price.add(tax);
            cell.add(cost.toString());
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public List findOrderItemsforInvoice(int orderId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<JQGridRow> rows = new ArrayList();
        log.info("1 findOrderItemsforInvoice....orderId.."+orderId);
        String selectStatement = "select id,product_code,description,qty,price,invoiceNo " +
                "from xy_order_line_items_umg where order_id=? order by id";
        ps = connection.prepareStatement(selectStatement);
         ps.setInt(1, orderId);
        rs = ps.executeQuery();
        while (rs.next()) {
            JQGridRow row = new JQGridRow();
            row.setId(rs.getInt("id"));
            List<String> cell = new ArrayList();
            cell.add(rs.getString("product_code"));
            cell.add(rs.getString("description"));
            int quantity = rs.getInt("qty");
            cell.add(new Integer(quantity).toString());
            BigDecimal unitPrice = rs.getBigDecimal("price");
            cell.add(unitPrice.toString());
            BigDecimal price = unitPrice.multiply(new BigDecimal(quantity));
            cell.add(price.toString());
            BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            cell.add(tax.toString());
            BigDecimal cost = price.add(tax);
            cell.add(cost.toString());
            cell.add(rs.getString("invoiceNo"));
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }
    
    public List findOrderItems(int orderId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PrintOrder> list = new ArrayList();
        log.info("2 findOrderItems....orderId.."+orderId);
        /*String selectStatement = "select pu.product_code,pu.description,oliu.qty,oliu.price " +
        "from xy_order_line_items_umg oliu inner join products_umg pu on oliu.product_code=pu.product_code " +
        "where oliu.order_id=? order by pu.product_code";*/
        String selectStatement = "select product_code,description,qty,price,deliveryDate,lineComment " +
                "from xy_order_line_items_umg where order_id=? order by id";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, orderId);
        rs = ps.executeQuery();
        while (rs.next()) {
            String productCode = rs.getString("product_code");
            String description = rs.getString("description");
            int quantity = rs.getInt("qty");
            BigDecimal unitPrice = rs.getBigDecimal("price");
            BigDecimal price = unitPrice.multiply(new BigDecimal(quantity));
            BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(3, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = price.add(tax);
            String deliveryDate = rs.getString("deliveryDate");
            String LineComment = rs.getString("lineComment");
            list.add(new PrintOrder(productCode, description, quantity, price, tax, cost,deliveryDate,LineComment));
        }
        return list;
    }
    
     public List findInvoiceItems(int orderId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<InvoiceLineItem> list = new ArrayList();
        log.info("2 findOrderItems....orderId.."+orderId);
        String selectStatement = "select product_code,description,qty,price " +
                "from xy_order_line_items_umg where order_id=? order by id";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, orderId);
        rs = ps.executeQuery();
        while (rs.next()) {
            String productCode = rs.getString("product_code");
            String description = rs.getString("description");
            int quantity = rs.getInt("qty");
            BigDecimal unitPrice = rs.getBigDecimal("price");
            BigDecimal price = unitPrice.multiply(new BigDecimal(quantity)).setScale(3, BigDecimal.ROUND_HALF_UP);
            BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(3, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = price.add(tax);
            list.add(new InvoiceLineItem(productCode, description, quantity,unitPrice, price, tax, cost));
        }
        return list;
    }
     
     public List findInvoiceLineItems(int orderId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean invoiceExist=false;
        String selectStatement =null;
        List<InvoiceLineItem> list = new ArrayList();
        String lineItemType="";
       
        invoiceExist=findInvoiceExists(orderId);
         log.info("2 findInvoiceLineItems....orderId.."+orderId+"...invoiceExist="+invoiceExist);
        if(!invoiceExist){
            selectStatement = "select id,id as invoiceLineItemId,product_code,description,qty as ordered_qty,qty,price,invoiceNo,lineNo from xy_order_line_items_umg where order_id=? order by id";
            lineItemType="order";
        }else{
            selectStatement = "select lineItemId as id,id as invoiceLineItemId,product_code,description,ordered_qty,qty,price,invoiceNo,lineNo from xy_invoice_line_items where order_id=? order by id";
            lineItemType="invoice";
        }
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, orderId);
        rs = ps.executeQuery();
        while (rs.next()) {
            //int id = rs.getInt("id");
            //int invoiceLineItemId=rs.getInt("invoiceLineItemId");
            //String productCode = rs.getString("product_code");
            //String description = rs.getString("description");
            int quantity = rs.getInt("qty");
//            int ordered_qty=rs.getInt("ordered_qty");
            BigDecimal unitPrice = rs.getBigDecimal("price");
//            BigDecimal price = unitPrice.multiply(new BigDecimal(quantity));
            BigDecimal price = unitPrice.multiply(new BigDecimal(quantity)).setScale(3, BigDecimal.ROUND_HALF_UP);
            BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(3, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = price.add(tax);
            
//            String invoiceNo = rs.getString("invoiceNo");
            log.info("3 findInvoiceLineItems....unitPrice.."+unitPrice+"...price="+price+"...tax==."+tax+"...cost=="+cost);
//            list.add(new InvoiceLineItem(id,orderId,invoiceLineItemId,productCode, description,ordered_qty, quantity,unitPrice, price, tax, cost,invoiceNo,lineItemType));
            list.add(new InvoiceLineItem(rs.getInt("id"),orderId,rs.getInt("invoiceLineItemId"),rs.getString("product_code"), rs.getString("description"),rs.getInt("ordered_qty"), quantity,unitPrice, price, tax, cost,rs.getString("invoiceNo"),lineItemType,rs.getInt("lineNo")));
        }
        return list;
    }
     
     public boolean findInvoiceExists(int orderId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Connection connection = session.connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag=false;
        String selectStatement = "select id from xy_invoice_line_items where order_id=?";
        ps = connection.prepareStatement(selectStatement);
        ps.setInt(1, orderId);
        rs = ps.executeQuery();
        while (rs.next()) {
            if(rs.getInt("id")>0)
                flag=true;
        }
        return flag;
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
