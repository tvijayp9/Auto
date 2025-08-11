/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.services;

import com.nexus.dao.ProductManagementDAO;
import com.nexus.dao.QuotesDAO;
import com.nexus.dao.QuotesItemsDAO;
import com.nexus.domain.JQGridRow1;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.domain.TemplateOrderItem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vijay Thumma
 */
@Transactional
public class ProductManagementServiceImpl implements ProductManagementService{
    Logger log=Logger.getLogger(ProductManagementServiceImpl.class);
    private ProductManagementDAO productManagementDAO;
    private QuotesDAO quotesDAO;
    private QuotesItemsDAO quotesItemsDAO;
    public boolean uploadParts(File uploadFile, String uploadFilePath, String supNexusId, String productsTableName) throws SQLException {

        InputStream is = null;
        OutputStream os = null;
        StringTokenizer str = null;
        boolean updatedRecords = false;
        String site_name ="";
        String category_name = "";
        String product_code = "";
        String description = "";
        String price1="";
        String newLine = "";
        try {
            String line;
            String delimiter = "@#$%";
            String outFileName = System.currentTimeMillis() + ".txt";
            String outFilePath = new StringBuilder(uploadFilePath).append(outFileName).toString();
            is = new FileInputStream(uploadFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            os = new FileOutputStream(outFilePath);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(os));
            line = reader.readLine();
            log.info("fileName..." + uploadFile.getName() + "...path=" + outFilePath);
            if(!line.startsWith("\"") || !line.endsWith("\"")){
                return updatedRecords;
            }

              while (line != null) {
                str = new StringTokenizer(line, "\"");
                site_name = str.nextToken();
                str.nextToken();
                category_name = str.nextToken();
                str.nextToken();
                product_code = str.nextToken();
                str.nextToken();
                description = str.nextToken();
                str.nextToken();
                price1 = str.nextToken();
                
                newLine = new StringBuilder(StringUtils.trim(site_name)).append(delimiter).append(StringUtils.trim(category_name)).append(delimiter).append(StringUtils.trim(product_code)).append(delimiter).append(StringUtils.trim(description)).append(delimiter).append(StringUtils.trim(price1)).append(delimiter).append(supNexusId).toString();
                writer.println(newLine);
                line = reader.readLine();
            }
            writer.flush();
            updatedRecords = productManagementDAO.uploadParts(outFileName, outFilePath, productsTableName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return updatedRecords;
    }
    
    public String saveNewQuote(String quoteName, List<TemplateOrderItem> quote, int id, int supplierId) throws SQLException {
        String qrn = getQuotesDAO().checkQRN(id, supplierId);
        String effectiveQRN = "B" + id + "S" + supplierId + "QRN1";
        if (qrn != null) {
            String[] array = qrn.split("QRN");
            int count = new Integer(array[1]).intValue() + 1;
            effectiveQRN = "B" + id + "S" + supplierId + "QRN" + count;
        }
        getQuotesDAO().insertQuote(effectiveQRN, quoteName, id, supplierId);
        int QId = getQuotesDAO().findQId(effectiveQRN, quoteName, id, supplierId);
        for (TemplateOrderItem toi : quote) {
            getQuotesItemsDAO().insertQuoteItems(QId, toi.getProductCode(), toi.getUnitPrice(), toi.getDescription(), toi.getQty());
        }
        return effectiveQRN;
    }
    
    public boolean updatePart(String siteName, String catName, String partNumber, String description, String price, String productTable) throws SQLException {
        return productManagementDAO.updatePart(siteName, catName, partNumber, description, price, productTable);
    }

    public boolean deletePart(String siteName, String catName, String partNumber, String productTable) throws SQLException{
        return productManagementDAO.deletePart(siteName, catName, partNumber, productTable);
    }
    
    public int findSupplierProductsCount(String searchFor, String searchIn, String product_table_name) throws SQLException{
        return productManagementDAO.findSupplierProductsCount(searchFor, searchIn, product_table_name);
    }
    
    public List findSupplierProducts(String searchFor, String searchIn, int start, int limit, String sidx, String sord, String product_table_name) throws SQLException{
        return productManagementDAO.findSupplierProducts(searchFor, searchIn, start, limit, sidx, sord, product_table_name);
    }
    
    public int findSupplierProductsBySiteCount(String siteName, String categoryName, String product_table_name) throws SQLException{
        return productManagementDAO.findSupplierProductsBySiteCount(siteName, categoryName, product_table_name);
    }

    public List findSupplierProductsBySiteName(String sitename, String categoryname, int start, int limit, String sidx, String sord, String product_table_name) throws SQLException{
        return productManagementDAO.findSupplierProductsBySiteName(sitename, categoryname, start, limit, sidx, sord, product_table_name);
    }
    
    public List<TemplateOrderItem> findQuoteItemsByQuoteId(int quoteId) throws SQLException{
        return productManagementDAO.findQuoteItemsByQuoteId(quoteId);
    }
    
    public void addShoppingCartItem(String[] productCode, List<ShoppingCartItem> shoppingCart,String product_table_name) throws SQLException {
            String siteName = "";
            String categoryName = "";
            String productItemCode = "";
        for (String productCodeElement : productCode) {
            boolean existing = false;
            String[] token = productCodeElement.split("_");
            siteName = token[0];
            categoryName = token[1];
            productItemCode = token[2];
            for (ShoppingCartItem sci : shoppingCart) {

                if (siteName.equalsIgnoreCase(sci.getSiteName()) && categoryName.equalsIgnoreCase(sci.getCategoryName()) && productItemCode.equalsIgnoreCase(sci.getProductCode())) {
                    existing = true;
                    break;
                }
            }
            if (existing) {
                continue;
            } else {
                shoppingCart.add(productManagementDAO.findProductDetailsForShoppingCart(siteName, categoryName, productItemCode, product_table_name));
            }
        }
    }
    
    public List<TemplateOrderItem> addAmcapQuoteItem(String[] productCode, List<TemplateOrderItem> quote,String product_table_name) throws SQLException {
            String siteName = "";
            String categoryName = "";
            String productItemCode = "";
        for (String productCodeElement : productCode) {
            boolean existing = false;
            String[] token = productCodeElement.split("_");
            siteName = token[0];
            categoryName = token[1];
            productItemCode = token[2];
            for (TemplateOrderItem toi : quote) {
                if (siteName.equalsIgnoreCase(toi.getSiteName()) && categoryName.equalsIgnoreCase(toi.getCategoryName()) && productItemCode.equalsIgnoreCase(toi.getProductCode())) {
                    existing = true;
                    break;
                }
            }
            if (existing) {
                continue;
            } else {
                quote.add(productManagementDAO.findProductDetailsForTemplate(siteName, categoryName, productItemCode, product_table_name));
            }
        }
        log.info("quote size="+quote.size());
        return quote;
    }
    
    public void updateQuoteQuantity(String quoteItemId, int quantity, List<TemplateOrderItem> quoteOrderItems) {
        for (TemplateOrderItem toi : quoteOrderItems) {
            if (toi.getTemplateId() == Integer.parseInt(quoteItemId)) {
                toi.setQty(quantity);
                BigDecimal unitPrice = toi.getUnitPrice();
                toi.setUnitPrice(unitPrice);
                BigDecimal price = unitPrice.multiply(new BigDecimal(quantity));
                toi.setPrice(price);
                BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                toi.setTotaltax(tax);
                BigDecimal cost = price.add(tax);
                toi.setCost(cost);
                break;
            }
        }
    }
            
    public List<TemplateOrderItem> addAmcapProductToQuoteItem(int qty, String productCode, String description, BigDecimal unitPrice, List<TemplateOrderItem> quote) throws SQLException {
        boolean existing = false;
        for (TemplateOrderItem toi : quote) {
            if (StringUtils.isEmpty(toi.getSiteName()) && productCode.equalsIgnoreCase(toi.getProductCode()) && description.equalsIgnoreCase(toi.getDescription())) {
                existing = true;
                break;
            }
        }
        if (!existing) {
            BigDecimal price = unitPrice.multiply(new BigDecimal(qty));
            BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = price.add(tax);
            TemplateOrderItem toi = new TemplateOrderItem("", "", productCode, description, unitPrice, price, tax, qty, cost, 1);
            quote.add(toi);
        }
        log.info("quote size=" + quote.size());
        return quote;
    }
    
    public List getShoppingCart(List<ShoppingCartItem> shoppingCart, int start, int limit) throws SQLException {
        List<JQGridRow1> rows = new ArrayList();
        try{
        int size = shoppingCart.size();
        int end = 0;
        if (start + limit > size) {
            end = size;
        } else {
            end = start + limit;
        }
        List<ShoppingCartItem> shoppingCartSubList = shoppingCart.subList(start, end);
        for (ShoppingCartItem sci : shoppingCartSubList) {
            JQGridRow1 row = new JQGridRow1();
            row.setId(new StringBuilder(sci.getSiteName()).append("_").append(sci.getCategoryName()).append("_").append(sci.getProductCode()).toString());
            List<String> cell = new ArrayList();
            cell.add(new Integer(sci.getQuantity()).toString());
            cell.add(sci.getSiteName());
            cell.add(sci.getCategoryName());
            cell.add(sci.getProductCode());
            cell.add(sci.getDescription());
            cell.add(sci.getUnitPrice().toString());
            cell.add(sci.getPrice().toString());
            cell.add(sci.getTax().toString());
            cell.add(sci.getCost().toString());
            row.setCell(cell);
            rows.add(row);
        }
        }catch(NullPointerException e){
            log.error("Exception Message", e);
        }
        return rows;
    }
    
    public List getQuote(List<TemplateOrderItem> template, int start, int limit) throws SQLException {
        List<JQGridRow1> rows = new ArrayList();
        int size = template.size();
        int end = 0;
        if (start + limit > size) {
            end = size;
        } else {
            end = start + limit;
        }
        List<TemplateOrderItem> templateSubList = template.subList(start, end);
        for (TemplateOrderItem toi : templateSubList) {
            JQGridRow1 row = new JQGridRow1();
            row.setId(toi.getProductCode());
            List<String> cell = new ArrayList();
            cell.add(new Integer(toi.getQty()).toString());
            cell.add(new Integer(toi.getSoh()).toString());
            cell.add(toi.getProductCode());
            cell.add(toi.getDescription());
            cell.add(toi.getUnitPrice().toString());

            cell.add(toi.getPrice().toString());
            cell.add(toi.getTotaltax().toString());
            cell.add(toi.getCost().toString());
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }
    
    public List getQuoteItems(List<TemplateOrderItem> template, int start, int limit) throws SQLException {
        List<JQGridRow1> rows = new ArrayList();
        int size = template.size();
        int end = 0;
        if (start + limit > size) {
            end = size;
        } else {
            end = start + limit;
        }
        List<TemplateOrderItem> templateSubList = template.subList(start, end);
        for (TemplateOrderItem toi : templateSubList) {
            JQGridRow1 row = new JQGridRow1();
            row.setId(new Integer(toi.getTemplateId()).toString());
            List<String> cell = new ArrayList();
            cell.add(new Integer(toi.getQty()).toString());
            cell.add(toi.getProductCode());
            cell.add(toi.getDescription());
            cell.add(toi.getUnitPrice().toString());

            cell.add(toi.getPrice().toString());
            cell.add(toi.getTotaltax().toString());
            cell.add(toi.getCost().toString());
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }
    
    public void updateQuantity(String productCode, int quantity, List<ShoppingCartItem> shoppingCart) {
        String[] token = productCode.split("_");
        String siteName = token[0];
        String categoryName = token[1];
        String productItemCode = token[2];
        for (ShoppingCartItem sci : shoppingCart) {
            if (siteName.equalsIgnoreCase(sci.getSiteName()) && categoryName.equalsIgnoreCase(sci.getCategoryName()) && productItemCode.equalsIgnoreCase(sci.getProductCode())) {
                sci.setQuantity(quantity);
                BigDecimal unitPrice = sci.getUnitPrice();
                BigDecimal price = unitPrice.multiply(new BigDecimal(quantity));
                sci.setPrice(price);
                BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                sci.setTax(tax);
                BigDecimal cost = price.add(tax);
                sci.setCost(cost);
                break;
            }
        }
    }
    
    public void deleteShoppingCartItem(String productCode, List<ShoppingCartItem> shoppingCart) {
        String[] token = productCode.split("_");
        String siteName = token[0];
        String categoryName = token[1];
        String productItemCode = token[2];
        for (ShoppingCartItem sci : shoppingCart) {
            if (siteName.equalsIgnoreCase(sci.getSiteName()) && categoryName.equalsIgnoreCase(sci.getCategoryName()) && productItemCode.equalsIgnoreCase(sci.getProductCode())) {
                shoppingCart.remove(sci);
                break;
            }
        }
    }
    
    public void addQuoteShoppingCartItem(String itemsId, List<ShoppingCartItem> shoppingCart, List<TemplateOrderItem> quotesItems) throws SQLException {
        String[] items = itemsId.split("\\,");
        for (String itemId : items) {
            for(TemplateOrderItem templateOrderItem : quotesItems){
                if(new Integer(itemId)== templateOrderItem.getTemplateId()){
                    shoppingCart.add(new ShoppingCartItem(templateOrderItem.getProductCode(), templateOrderItem.getDescription(), templateOrderItem.getUnitPrice(), templateOrderItem.getPrice(), templateOrderItem.getTotaltax(), templateOrderItem.getQty(), templateOrderItem.getCost(), 0, templateOrderItem.getTemplateId()));
                }
            }
//            shoppingCart.add(productManagementDAO.findProductDetailsForQuoteShoppingCart(itemId));
        }
    }
    
    public int findQuoteItemsByQuoteIdCount(int quoteId) throws SQLException{
        return productManagementDAO.findQuoteItemsByQuoteIdCount(quoteId);
    }
    public List findQuoteItemsByQuoteId(int quoteId, int start, int limit, String sidx, String sord) throws SQLException{
        return productManagementDAO.findQuoteItemsByQuoteId(quoteId, start, limit, sidx, sord);
    }
    public List<String> findSupplierProductsSiteNames(String product_table_name) throws SQLException{
        return productManagementDAO.findSupplierProductsSiteNames(product_table_name);
    }
    
    public List<String> findSupplierProductsCategoryNames(String siteName, String product_table_name) throws SQLException{
        return productManagementDAO.findSupplierProductsCategoryNames(siteName, product_table_name);
    }
    
    public String findQuoteNameByQuoteId(int quoteId) throws SQLException{
        return productManagementDAO.findQuoteNameByQuoteId(quoteId);
    }
    
    /**
     * @return the productManagementDAO
     */
    public ProductManagementDAO getProductManagementDAO() {
        return productManagementDAO;
    }

    /**
     * @param productManagementDAO the productManagementDAO to set
     */
    public void setProductManagementDAO(ProductManagementDAO productManagementDAO) {
        this.productManagementDAO = productManagementDAO;
    }

    /**
     * @return the quotesDAO
     */
    public QuotesDAO getQuotesDAO() {
        return quotesDAO;
    }

    /**
     * @param quotesDAO the quotesDAO to set
     */
    public void setQuotesDAO(QuotesDAO quotesDAO) {
        this.quotesDAO = quotesDAO;
    }

    /**
     * @return the quotesItemsDAO
     */
    public QuotesItemsDAO getQuotesItemsDAO() {
        return quotesItemsDAO;
    }

    /**
     * @param quotesItemsDAO the quotesItemsDAO to set
     */
    public void setQuotesItemsDAO(QuotesItemsDAO quotesItemsDAO) {
        this.quotesItemsDAO = quotesItemsDAO;
    }

}
