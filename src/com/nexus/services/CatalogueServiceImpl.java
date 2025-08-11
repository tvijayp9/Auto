/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.services;


import com.nexus.dao.AutoOrderCountDAO;
import com.nexus.dao.FavouriteLineItemsDAO;
import com.nexus.dao.FavouriteOrderDAO;
import com.nexus.dao.OrderDAO;

import com.nexus.dao.ProductHistoryDAO;
import com.nexus.domain.Order;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.nexus.dao.MemberLogosDAO;
import com.nexus.dao.MessageDAO;
import com.nexus.dao.MicrocatDAO;
import com.nexus.dao.MicrocatOrderDAO;
import com.nexus.dao.MicrocatOrderItemsDAO;
import com.nexus.dao.OrderItemsUMGDAO;
import com.nexus.dao.PartnerLinkDAO;
import com.nexus.dao.ProductsUMGDAO;
import com.nexus.dao.QuotesDAO;
import com.nexus.dao.QuotesItemsDAO;
import com.nexus.dao.RegistrationDAO;
import com.nexus.dao.RulesDAO;
import com.nexus.dao.UMGOrdersQuotesDAO;
import com.nexus.domain.InvoiceLineItem;
import com.nexus.domain.JQGridRow;
import com.nexus.domain.JQGridRow1;
import com.nexus.domain.Microcat;
import com.nexus.domain.MicrocatOrderItems;
import com.nexus.domain.OrderAddressData;
import com.nexus.domain.PartnerLink;
import com.nexus.domain.PrintOrder;
import com.nexus.domain.Quote;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.domain.SubTotal;
import com.nexus.domain.TemplateOrderItem;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import org.apache.log4j.Logger;

/**
 *
 * @author Terry
 */
@Transactional
public class CatalogueServiceImpl implements CatalogueService {

    Logger log=Logger.getLogger(CatalogueServiceImpl.class);
    private OrderDAO orderDAO;
    private ProductHistoryDAO productHistoryDAO;
    private AutoOrderCountDAO autoOrderCountDAO;
    private FavouriteOrderDAO favouriteOrderDAO;
    private FavouriteLineItemsDAO favouriteLineItemsDAO;
    private PartnerLinkDAO partnerLinkDAO;
    private MemberLogosDAO memberLogosDAO;
    private MessageDAO messageDAO;
    private ProductsUMGDAO productsUMGDAO;
    private RegistrationDAO registrationDAO;
    private OrderItemsUMGDAO orderItemsUMGDAO;
    private MicrocatDAO microcatDAO;
    private MicrocatOrderDAO microcatOrderDAO;
    private MicrocatOrderItemsDAO microcatOrderItemsDAO;
    private QuotesDAO quotesDAO;
    private QuotesItemsDAO quotesItemsDAO;
    private UMGOrdersQuotesDAO umgOrdersQuotesDAO;
    private RulesDAO rulesDAO;

    public int getOrderListCount(String id, String supId, String search, String orderNumber, String orderDate, String orderDate1) throws SQLException {
        return orderDAO.findOrderListCount(id, supId, search, orderNumber, orderDate, orderDate1);
    }

    public int getArchivedOrderListCount(String id, String supId, String search, String orderNumber, String orderDate, String orderDate1) throws SQLException {
        return orderDAO.findArchivedOrderListCount(id, supId, search, orderNumber, orderDate, orderDate1);
    }

    public List getOrderList(String id, String supId, String search, String orderNumber, String orderDate, String orderDate1, int start, int limit, String sidx, String sord) throws SQLException {
        return orderDAO.findOrderList(id, supId, search, orderNumber, orderDate, orderDate1, start, limit, sidx, sord);
    }

    public List getArchivedOrderList(String id, String supId, String search, String orderNumber, String orderDate, String orderDate1, int start, int limit, String sidx, String sord) throws SQLException {
        return orderDAO.findArchivedOrderList(id, supId, search, orderNumber, orderDate, orderDate1, start, limit, sidx, sord);
    }

    public int getOrderItemsCount(String orderId) throws SQLException {
        return productsUMGDAO.findOrderItemsCount(orderId);
    }

    public int getArchivedOrderItemsCount(String orderId) throws SQLException {
        return productHistoryDAO.findArchivedOrderItemsCount(orderId);
    }

    public List getOrderItems(String orderId, int start, int limit, String sidx, String sord) throws SQLException {
        return productsUMGDAO.findOrderItems(orderId, start, limit, sidx, sord);
    }

    public List getOrderItems(int orderId) throws SQLException {
        return productsUMGDAO.findOrderItems(orderId);
    }

    public List getArchivedOrderItems(String orderId, int start, int limit, String sidx, String sord) throws SQLException {
        return productHistoryDAO.findArchivedOrderItems(orderId, start, limit, sidx, sord);
    }

    public int getFutureOrderListCount(String id, String supId, String search, String orderNumber, String orderDate) throws SQLException {
        return orderDAO.findFutureOrderListCount(id, supId, getFormattedDate(), search, orderNumber, orderDate);
    }

    public List getFutureOrderList(String id, String supId, String search, String orderNumber, String orderDate, int start, int limit, String sidx, String sord) throws SQLException {
        return orderDAO.findFutureOrderList(id, supId, getFormattedDate(), search, orderNumber, orderDate, start, limit, sidx, sord);
    }

    public String getFormattedDate() {
        SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy/MM/dd");
        return dateFormatter.format(new Date());
    }

    public String getFormattedDate(Date date) {
        SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy/MM/dd");
        return dateFormatter.format(date);
    }

    public Order createOrder(String id, String supId, Date orderDate, String comment) throws SQLException {
        int autoOrderNumber = autoOrderCountDAO.getAutoOrderNumber(id, supId);
        int thisOrderNumber = autoOrderNumber + 1;
        String orderNumber = "B" + id + "S" + supId + "N" + thisOrderNumber;
        String deliveryDate = getFormattedDate(orderDate);
        orderDAO.insertOrder(orderNumber, id, supId, deliveryDate, comment);
        if (autoOrderNumber == 0) {
            autoOrderCountDAO.insertAutoOrderNumber(thisOrderNumber, id, supId);
        } else {
            autoOrderCountDAO.updateAutoOrderNumber(thisOrderNumber, id, supId);
        }
        int orderId = orderDAO.findOrderId(orderNumber, id, supId);
        return new Order(orderId, orderNumber);
    }

    public Order createCXMLOrder(String id, String supId, String orderDate, String comment,String orderNumber,String quoteNo,OrderAddressData orderAddressData) throws SQLException {
        int orderId=orderDAO.insertCXMLOrder(orderNumber, id, supId, orderDate, comment,quoteNo);
//        int orderId = orderDAO.findOrderId(orderNumber, id, supId);
        orderDAO.insertCXMLOrderAddress(orderId, orderAddressData);
        return new Order(orderId, orderNumber);
    }

    public Order createOrderQuote(String id, String supId, Date orderDate, String comment) throws SQLException {
        int autoOrderNumber = autoOrderCountDAO.getAutoOrderNumber(id, supId);
        int thisOrderNumber = autoOrderNumber + 1;
        String orderNumber = "B" + id + "S" + supId + "N" + thisOrderNumber;
        String deliveryDate = getFormattedDate(orderDate);
        orderDAO.insertOrderQuote(orderNumber, id, supId, deliveryDate, comment);
        if (autoOrderNumber == 0) {
            autoOrderCountDAO.insertAutoOrderNumber(thisOrderNumber, id, supId);
        } else {
            autoOrderCountDAO.updateAutoOrderNumber(thisOrderNumber, id, supId);
        }
        int orderId = orderDAO.findOrderQuoteId(orderNumber, id, supId);
        return new Order(orderId, orderNumber);
    }

    public Order createOrderForQuote(String id, String supId, Date orderDate, String comment, String quoteId) throws SQLException {
        Order order = createOrder(id, supId, orderDate, comment);
        String qrn = quotesDAO.findQRNByQuoteId(new Integer(quoteId).intValue());
        umgOrdersQuotesDAO.insertOrderQuote(order.getId(), qrn);
        return order;
    }

     public PartnerLink getOrderComment(String id, String supId) throws SQLException {
        return partnerLinkDAO.findPartnerLinkByBuyerIdAndSupplierId(id, supId);
    }

    public int getOrderId(String orderNumber, String id, String supId) throws SQLException {
        return orderDAO.findOrderId(orderNumber, id, supId);
    }

    public int getOrderQuoteId(String orderNumber, String id, String supId) throws SQLException {
        return orderDAO.findOrderQuoteId(orderNumber, id, supId);
    }

    public void addFavouriteOrder(String orderNumber, String favouriteName, int id, int supplierId) throws SQLException {
        favouriteOrderDAO.insertFavouriteOrder(orderNumber, favouriteName, id, supplierId);
        int favouriteOrderId = favouriteOrderDAO.findFavouriteOrderIdByOrderNumber(orderNumber);
        List<String> gtins = orderItemsUMGDAO.findOrderItemsByOrderNumber(orderNumber);
        for (String gtin : gtins) {
            favouriteLineItemsDAO.insertFavouriteLineItems(favouriteOrderId, gtin);
        }
    }

    public void saveNewTemplate(String templateName, List<TemplateOrderItem> template, int id, int supplierId) throws SQLException {
        favouriteOrderDAO.insertFavouriteOrder(templateName, templateName, id, supplierId);
        int templateId = favouriteOrderDAO.findFavouriteOrderId(templateName, id, supplierId);
        for (TemplateOrderItem toi : template) {
            favouriteLineItemsDAO.insertFavouriteLineItems(templateId, toi.getProductCode());
        }
    }

    public String saveNewQuote(String quoteName, List<TemplateOrderItem> quote, int id, int supplierId) throws SQLException {
        String qrn = quotesDAO.checkQRN(id, supplierId);
        String effectiveQRN = "B" + id + "S" + supplierId + "QRN1";
        if (qrn != null) {
            String[] array = qrn.split("QRN");
            int count = new Integer(array[1]).intValue() + 1;
            effectiveQRN = "B" + id + "S" + supplierId + "QRN" + count;
        }
        quotesDAO.insertQuote(effectiveQRN, quoteName, id, supplierId);
        int QId = quotesDAO.findQId(effectiveQRN, quoteName, id, supplierId);
        for (TemplateOrderItem toi : quote) {
            quotesItemsDAO.insertQuoteItems(QId, toi.getProductCode(), toi.getUnitPrice(), toi.getDescription(), toi.getQty());
        }
        return effectiveQRN;
    }

    public void addQuoteFromShoppingcart(String orderNumber, String quoteName, int id, int supplierId) throws SQLException {
        String qrn = quotesDAO.checkQRN(id, supplierId);
        String effectiveQRN = "B" + id + "S" + supplierId + "QRN1";
        if (qrn != null) {
            String[] array = qrn.split("QRN");
            int count = new Integer(array[1]).intValue() + 1;
            effectiveQRN = "B" + id + "S" + supplierId + "QRN" + count;
        }
        quotesDAO.insertQuote(effectiveQRN, quoteName, id, supplierId);
        int QId = quotesDAO.findQId(effectiveQRN, quoteName, id, supplierId);
        List<TemplateOrderItem> list = orderItemsUMGDAO.findOrderItems(orderNumber);
        for (TemplateOrderItem toi : list) {
            quotesItemsDAO.insertQuoteItems(QId, toi.getProductCode(), toi.getUnitPrice(), toi.getDescription(), toi.getQty());
        }
    }

    public void saveModifiedTemplate(int templateId, List<TemplateOrderItem> template, int id, int supplierId) throws SQLException {
        List<String> productCodeList = favouriteLineItemsDAO.findProductCodeByTemplateId(templateId);
        for (TemplateOrderItem toi : template) {
            boolean existing = false;
            for (String productCode : productCodeList) {
                if (productCode.equals(toi.getProductCode())) {
                    existing = true;
                    break;
                }
            }
            if (existing) {
                continue;
            } else {
                favouriteLineItemsDAO.insertFavouriteLineItems(templateId, toi.getProductCode());
            }
        }
    }

    public void saveModifiedQuote(int templateId, List<TemplateOrderItem> template, int id, int supplierId) throws SQLException {
        List<String> productCodeList = quotesItemsDAO.findProductCodeByQuoteId(templateId);
        for (TemplateOrderItem toi : template) {
            boolean existing = false;
            for (String productCode : productCodeList) {
                if (productCode.equals(toi.getProductCode())) {
                    existing = true;
                    break;
                }
            }
            if (existing) {
                continue;
            } else {
                quotesItemsDAO.insertQuoteItems(templateId, toi.getProductCode(), toi.getUnitPrice(), toi.getDescription(), toi.getQty());
            }
        }
    }

    public int getFavouriteOrderListCount(int id, int supplierId) throws SQLException {
        return favouriteLineItemsDAO.findFavouriteOrderListCount(id, supplierId);
    }

    public List getFavouriteOrderList(int id, int supplierId, int start, int limit, String sidx, String sord) throws SQLException {
        return favouriteLineItemsDAO.findFavouriteOrderList(id, supplierId, start, limit, sidx, sord);
    }

    public int getQuotesListCount(int id, int supplierId) throws SQLException {
        return quotesDAO.findQuotesListCount(id, supplierId);
    }

    public List getQuotesList(int id, int supplierId, int start, int limit, String sidx, String sord) throws SQLException {
        return quotesDAO.findQuotesList(id, supplierId, start, limit, sidx, sord);
    }

    public void deleteFavouriteOrder(String id) throws SQLException {
        String[] rowId = id.split("\\,");
        for (String row : rowId) {
            favouriteOrderDAO.deleteFavouriteOrder(new Integer(row).intValue());
        }
    }

    public void deleteQuotes(String id) throws SQLException {
        String[] rowId = id.split("\\,");
        for (String row : rowId) {
            quotesDAO.deleteQuotes(new Integer(row).intValue());
        }
    }

    public boolean checkOrderReceived(String microcatId,String accountNumber,String nexusId) throws SQLException {
        int count = microcatOrderDAO.findMicrocatOrderCountByAccountNumber(accountNumber,nexusId);
        if (count == 0) {
            return false;
        } else {
            microcatDAO.updateMicrocatStatus(Integer.parseInt(microcatId), 1);
            return true;
        }
    }
    
     public boolean checkScaniaOrderReceived(String microcatId,String accountNumber,String nexusId,String email) throws SQLException {
        log.info(" checkScaniaOrderReceived microcatid=="+microcatId+"....accountNumber=="+accountNumber+"....nexusId=="+nexusId+"....email=="+email);
         int count = microcatOrderDAO.findScaniaOrderCountByAccountNumber(accountNumber,nexusId,email);
         log.info("checkScaniaOrderReceived=="+count);
        if (count == 0) {
            return false;
        } else {
            microcatDAO.updateScaniaStatus(Integer.parseInt(microcatId), 1);
            return true;
        }
    }

    public int getSupplierIdById(int id) throws SQLException {
        return partnerLinkDAO.findSupplierIdByBuyerId(id);
    }

    public int getSupplierProductsByCategoryIdCount(String searchFor, String searchIn,String product_table_name) throws SQLException {
        return productsUMGDAO.findSupplierProductsCount(searchFor, searchIn,product_table_name);
    }

    public String getPriceType(int id) throws SQLException {
        int priceType = registrationDAO.findPriceTypeById(id);
        String price;
        switch (priceType) {
            case 1:
                price = "price1";
                break;
            case 2:
                price = "price2";
                break;
            case 3:
                price = "price3";
                break;
            case 4:
                price = "price4";
                break;
            case 5:
                price = "price5";
                break;
            case 6:
                price = "price6";
                break;
            case 7:
                price = "price7";
                break;
            case 8:
                price = "price8";
                break;
            default:
                price = "price1";
                break;
        }
        return price;
    }

    public List getSupplierProductsByCategoryId(int id, int supplierId, String searchFor, String searchIn, int start, int limit, String sidx, String sord,String product_table_name) throws SQLException {
        return productsUMGDAO.findSupplierProducts(getPriceType(id), id, supplierId, searchFor, searchIn, start, limit, sidx, sord,product_table_name);
    }

    public int getTemplateOrderItemsByTemplateIdCount(int templateId,String product_table_name) throws SQLException {
        return favouriteLineItemsDAO.findTemplateOrderItemsByTemplateIdCount(templateId,product_table_name);
    }

    public List getTemplateOrderItemsByTemplateId(int templateId, int id, int supplierId, int start, int limit, String sidx, String sord,String product_table_name) throws SQLException {
        return favouriteLineItemsDAO.findTemplateOrderItemsByTemplateId(templateId, getPriceType(id), id, supplierId, start, limit, sidx, sord,product_table_name);
    }

    public int getQuoteItemsByQuoteIdCount(int quoteId) throws SQLException {
        return quotesItemsDAO.findQuoteItemsByQuoteIdCount(quoteId);
    }

    public List getQuoteItemsByQuoteId(int quoteId, int start, int limit, String sidx, String sord) throws SQLException {
        return quotesItemsDAO.findQuoteItemsByQuoteId(quoteId, start, limit, sidx, sord);
    }

    public List getQuoteItemsByQuoteId(int quoteId) throws SQLException {
        List<TemplateOrderItem> items = quotesItemsDAO.findQuoteItemsByQuoteId(quoteId);
        for (TemplateOrderItem toi : items) {
            toi.setUnits("Each");
            toi.setTax("GST");
            if (new Integer(toi.getStatus()).intValue() == 0) {
                toi.setStatus("Ordered");
            } else {
                toi.setStatus("");
            }
        }
        return items;
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
            row.setId(sci.getProductCode());
            List<String> cell = new ArrayList();
            cell.add(new Integer(sci.getQuantity()).toString());
            cell.add(new Integer(sci.getSoh()).toString());
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

    public List getTemplate(List<TemplateOrderItem> template, int start, int limit) throws SQLException {
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
            cell.add(new Integer(toi.getSoh()).toString());
            cell.add(toi.getProductCode());
            cell.add(toi.getDescription());
            cell.add(toi.getUnitPrice().toString());
            cell.add("");
            cell.add("");
            row.setCell(cell);
            rows.add(row);
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
            //cell.add("");
            //cell.add("");
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public SubTotal getSubtotalforTemplate(List<TemplateOrderItem> templateorder) {
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal totalTax = new BigDecimal(0);
        BigDecimal totalCost = new BigDecimal(0);
        for (TemplateOrderItem toi : templateorder) {

            totalPrice = totalPrice.add(toi.getPrice());
            totalTax = totalTax.add((BigDecimal)toi.getTotaltax());
            totalCost = totalCost.add(toi.getCost());
//            log.info("totalPrice="+totalPrice+"..totalCost="+totalCost+"...tax."+toi.getTotaltax()+".....="+toi.getUnitPrice());
        }
        return new SubTotal(totalPrice, totalTax, totalCost);
    }

    public void addShoppingCartItem(int id, int supplierId, String[] productCode, List<ShoppingCartItem> shoppingCart,String product_table_name) throws SQLException {
        String priceType = getPriceType(id);
        for (String productCodeElement : productCode) {
            boolean existing = false;
            for (ShoppingCartItem sci : shoppingCart) {

                if (productCodeElement.equals(sci.getProductCode())) {
                    existing = true;
                    break;
                }
            }
            if (existing) {
                continue;
            } else {
                shoppingCart.add(productsUMGDAO.findProductDetailsForShoppingCart(id, supplierId, priceType, productCodeElement,product_table_name));
            }
        }
    }

    public void addQuoteShoppingCartItem(String itemsId, List<ShoppingCartItem> shoppingCart) throws SQLException {
        String[] items = itemsId.split("\\,");
        for (String itemId : items) {
            shoppingCart.add(quotesItemsDAO.findProductDetailsForQuoteShoppingCart(itemId));
        }
    }

    public void addTemplateOrderItemsToShoppingcart(int id, int supplierId, String[] templateOrderItemsId, List<ShoppingCartItem> shoppingCart,String product_table_name) throws SQLException {
        String priceType = getPriceType(id);
        for (String templateOrderItemId : templateOrderItemsId) {
            String productCode = favouriteLineItemsDAO.findProductCodeByTemplateOrderItemId(new Integer(templateOrderItemId).intValue());
            log.info("productCode="+productCode+"..shoppingCart size="+shoppingCart.size());
            boolean existing = false;
            for (ShoppingCartItem sci : shoppingCart) {
                log.info("inside for loop="+sci.getProductCode());
                if (productCode.equals(sci.getProductCode())) {
                    existing = true;
                    break;
                }
            }
            if (existing) {
                continue;
            } else {
                ShoppingCartItem item = productsUMGDAO.findProductDetailsForShoppingCart(id, supplierId, priceType, productCode,product_table_name);
                if (item != null) {
                    log.info("shopping cart for the product is not null..product="+productCode);
                    shoppingCart.add(item);
                } else {
                    log.info("shopping cart for the product is  null..product="+productCode);
                    shoppingCart.add(microcatOrderItemsDAO.findProductDetailsForShoppingCart(productCode));
                }
            }
        }
    }

    public void addTemplateOrderItem(int id, int supplierId, String[] productCode, List<TemplateOrderItem> template,String product_table_name) throws SQLException {
        String priceType = getPriceType(id);
        for (String productCodeElement : productCode) {
            boolean existing = false;
            for (TemplateOrderItem toi : template) {
                if (productCodeElement.equals(toi.getProductCode())) {
                    existing = true;
                    break;
                }
            }
            if (existing) {
                continue;
            } else {
                template.add(productsUMGDAO.findProductDetailsForTemplate(id, supplierId, priceType, productCodeElement,product_table_name));
            }
        }
    }

    public List<TemplateOrderItem> addQuoteItem(int id, int supplierId, String[] productCode, List<TemplateOrderItem> quote,String product_table_name) throws SQLException {
        String priceType = getPriceType(id);
        for (String productCodeElement : productCode) {
            boolean existing = false;
            for (TemplateOrderItem toi : quote) {
                if (productCodeElement.equals(toi.getProductCode())) {
                    existing = true;
                    break;
                }
            }
            if (existing) {
                continue;
            } else {
                quote.add(productsUMGDAO.findProductDetailsForTemplate(id, supplierId, priceType, productCodeElement,product_table_name));
            }
        }
        log.info("quote size="+quote.size());
        return quote;
    }

    public void deleteShoppingCartItem(String productCode, List<ShoppingCartItem> shoppingCart) {
        String[] items = productCode.split("\\,");
        for (String item : items) {
            for (ShoppingCartItem sci : shoppingCart) {
                if (item.equals(sci.getProductCode())) {
                    shoppingCart.remove(sci);
                    break;
                }
            }
        }
    }

    public void deleteTemplateOrderItem(String productCode, List<TemplateOrderItem> template) {
        String[] items = productCode.split("\\,");
        for (String item : items) {
            for (TemplateOrderItem toi : template) {
                if (item.equals(toi.getProductCode())) {
                    template.remove(toi);
                    break;
                }
            }
        }
    }

    public void deleteTemplateOrderItem(String templateOrderItemsId) throws SQLException {
        String[] items = templateOrderItemsId.split("\\,");
        for (String item : items) {
            favouriteLineItemsDAO.deleteTemplateOrderItem(new Integer(item).intValue());
        }
    }

    public void deleteQuoteItem(String quoteItemsId) throws SQLException {
        String[] items = quoteItemsId.split("\\,");
        for (String item : items) {
            quotesItemsDAO.deleteQuoteItem(new Integer(item).intValue());
        }
    }

    public void updateQuantity(String productCode, int quantity, List<ShoppingCartItem> shoppingCart) {
        for (ShoppingCartItem sci : shoppingCart) {
            if (sci.getProductCode().equals(productCode)) {
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
    
     public void updateQuantityforInvoice(String id, int modifiedquantity, BigDecimal modifiedunitPrice,List<InvoiceLineItem> invoiceLineItems) {
        BigDecimal unitPrice=null;
        Integer pcode=0;
        
        int quantity=0;
         for (InvoiceLineItem invoiceLineItem : invoiceLineItems) {
                log.info("updateQuantityforInvoice productCode=="+id+"........"+invoiceLineItem.getLineItemId());
                if(invoiceLineItem.getLineItemType().equals("order"))
                    pcode=invoiceLineItem.getLineItemId();
                else
                    pcode=invoiceLineItem.getInvoiceLineItemId();
                
                if (pcode==Integer.parseInt(id)) {
                if(modifiedquantity!=0)
                    invoiceLineItem.setQuantity(modifiedquantity);
                else if(modifiedunitPrice!=null)
                    invoiceLineItem.setUnitprice(modifiedunitPrice);
                
                 unitPrice=invoiceLineItem.getUnitprice();
                    quantity=invoiceLineItem.getQuantity();
                BigDecimal price = unitPrice.multiply(new BigDecimal(quantity)).setScale(3, BigDecimal.ROUND_HALF_UP);
                invoiceLineItem.setPrice(price);
                BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(3, BigDecimal.ROUND_HALF_UP);
                invoiceLineItem.setTax(tax);
                BigDecimal cost = price.add(tax);
                invoiceLineItem.setCost(cost);
                //log.info("updateQuantityforInvoice qty=="+invoiceLineItem.getQuantity());
                break;
            }
        }
        
    }
     
     public void addInvoiceNotoList(String invoiceNo,String[] productCode, List<InvoiceLineItem> invoiceLineItems) throws SQLException {
            Integer pcode=0;
             boolean existing = false;
        for (String productCodeElement : productCode) {
            for (InvoiceLineItem invoiceLineItem : invoiceLineItems) {
                log.info("1productCodeElement=="+Integer.parseInt(productCodeElement)+"....."+invoiceLineItem.getLineItemId()+"...line item type.."+invoiceLineItem.getLineItemType());
                if(invoiceLineItem.getLineItemType().equals("order"))
                    pcode=invoiceLineItem.getLineItemId();
                else
                    pcode=invoiceLineItem.getInvoiceLineItemId();
                log.info("pcode=="+pcode);        
                if (Integer.parseInt(productCodeElement)==pcode) {
                    invoiceLineItem.setInvoiceNo(invoiceNo);
                    invoiceLineItem.setInvoiced(true);
                     log.info("2productCodeElement=="+productCodeElement+"....."+invoiceLineItem.getId());
//                    existing = true;
                    break;
                }
            }
//            if (existing) {
//                continue;
//            } 
        }
    }
     

    public void updateQuoteItemQuantity(String productCode, int quantity, List<TemplateOrderItem> quoteItems) {
        for (TemplateOrderItem sci : quoteItems) {
            if (sci.getProductCode().equals(productCode)) {
                sci.setQty(quantity);
                BigDecimal unitPrice = sci.getUnitPrice();
                BigDecimal price = unitPrice.multiply(new BigDecimal(quantity));
                sci.setPrice(price);
                BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                sci.setTotaltax(tax);
                BigDecimal cost = price.add(tax);
                sci.setCost(cost);
                break;
            }
        }
    }

    public void updateQuoteItemQuantity(String id, int quantity) throws SQLException {
        quotesItemsDAO.updateQuoteItemQuantity(id, quantity);
    }

    public SubTotal getSubtotal(List<ShoppingCartItem> shoppingCart) {
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal totalTax = new BigDecimal(0);
        BigDecimal totalCost = new BigDecimal(0);
        for (ShoppingCartItem sci : shoppingCart) {
            totalPrice = totalPrice.add(sci.getPrice());
            totalTax = totalTax.add(sci.getTax());
            totalCost = totalCost.add(sci.getCost());
        }
        return new SubTotal(totalPrice, totalTax, totalCost);
    }

    public SubTotal getSubtotalForPrintOrder(List<PrintOrder> list) {
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal totalTax = new BigDecimal(0);
        BigDecimal totalCost = new BigDecimal(0);
        for (PrintOrder po : list) {
            totalPrice = totalPrice.add(po.getPrice());
            totalTax = totalTax.add(po.getTax());
            totalCost = totalCost.add(po.getCost());
        }
        return new SubTotal(totalPrice, totalTax, totalCost);
    }
    
     public SubTotal getSubtotalForInvoice(List<InvoiceLineItem> list) {
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal totalTax = new BigDecimal(0);
        BigDecimal totalCost = new BigDecimal(0);
        for (InvoiceLineItem po : list) {
            totalPrice = totalPrice.add(po.getPrice());
            totalTax = totalTax.add(po.getTax());
            totalCost = totalCost.add(po.getCost());
        }
        return new SubTotal(totalPrice, totalTax, totalCost);
    }

    public void createOrderItemsForShoppingCart(List<ShoppingCartItem> shoppingCart, int id, int supId, String orderNumber, String autoOrderNumber, Date deliveryDate, String comment) throws SQLException {
        int orderid = getOrderId(autoOrderNumber, new Integer(id).toString(), new Integer(supId).toString());
        for (ShoppingCartItem sci : shoppingCart) {
            orderItemsUMGDAO.insertOrderItems(orderid, sci.getProductCode(), sci.getQuantity(), sci.getUnitPrice(), sci.getDescription());
        }
        String fileName = "Nexus_PO_" + id + "_2_" + supId + "_" + orderid + "_" + orderNumber + ".csv";
        messageDAO.insertMessage(orderNumber, id, 1, supId, fileName);
        String dd = getFormattedDate(deliveryDate);
        synchronized(this){
        orderDAO.updateOrderStatusForShoppingCart(orderNumber, autoOrderNumber, new Integer(id).toString(), new Integer(supId).toString(), "New", dd, comment);
    }
    }

    public void createOrderQuoteItemsForShoppingCart(List<ShoppingCartItem> shoppingCart, int id, int supId, String orderNumber, String autoOrderNumber, Date deliveryDate, String comment) throws SQLException {
        int orderid = getOrderQuoteId(autoOrderNumber, new Integer(id).toString(), new Integer(supId).toString());
        for (ShoppingCartItem sci : shoppingCart) {
            orderItemsUMGDAO.insertOrderQuoteItems(orderid, sci.getProductCode(), sci.getQuantity(), sci.getUnitPrice(), sci.getDescription());
        }
//        String fileName = "Nexus_PO_" + id + "_2_" + supId + "_" + orderid + "_" + orderNumber + ".csv";
//        messageDAO.insertMessage(orderNumber, id, 1, supId, fileName);
        String dd = getFormattedDate(deliveryDate);
        orderDAO.updateOrderQuoteStatusForShoppingCart(orderNumber, autoOrderNumber, new Integer(id).toString(), new Integer(supId).toString(), "New", dd, comment);
    }

     public void createCXMLOrderItemsForShoppingCart(List<ShoppingCartItem> shoppingCart, int id, int supId, String orderNumber, String autoOrderNumber, String deliveryDate, String comment,Integer orderid) throws SQLException {
        for (ShoppingCartItem sci : shoppingCart) {
            orderItemsUMGDAO.insertCXMLOrderItems(orderid, sci.getProductCode(), sci.getQuantity(), sci.getUnitPrice(), sci.getDescription(),sci.getUom(),sci.getLineNo(),sci.getDeliveryDate(),sci.getLinelevelComment());
        }
        String fileName = "AusDrill_PO_" + id + "_2_" + supId + "_" + orderid + "_" + orderNumber + ".csv";
        messageDAO.insertCXMLMessage(orderNumber, id, 1, supId, fileName,orderid);
        orderDAO.updateOrderStatus(orderNumber, autoOrderNumber, new Integer(id).toString(), new Integer(supId).toString(), "New");
    }
     
      public void createInvoiceItems(List<InvoiceLineItem> invoiceLineItems) throws SQLException {
        for (InvoiceLineItem invoiceLineItem : invoiceLineItems) {
            orderItemsUMGDAO.insertInvoiceLineItems(invoiceLineItem);
        }
    }

    public void createOrderItemsForQuote(List<ShoppingCartItem> shoppingCart, int id, int supId, String orderNumber, String autoOrderNumber) throws SQLException {
        int orderid = getOrderId(autoOrderNumber, new Integer(id).toString(), new Integer(supId).toString());
        for (ShoppingCartItem sci : shoppingCart) {
            orderItemsUMGDAO.insertOrderItems(orderid, sci.getProductCode(), sci.getQuantity(), sci.getUnitPrice(), sci.getDescription());
            quotesItemsDAO.updateQuoteItemStatus(sci.getQuoteItemId());
        }
        String fileName = "Nexus_PO_" + id + "_2_" + supId + "_" + orderid + "_" + orderNumber + ".csv";
        messageDAO.insertMessage(orderNumber, id, 1, supId, fileName);
        orderDAO.updateOrderStatus(orderNumber, autoOrderNumber, new Integer(id).toString(), new Integer(supId).toString(), "New");
    }


    public Microcat getMicrocatInfo(int id) throws SQLException {
        return microcatDAO.findMicroInfo(id);
    }
    public boolean orderFromScania(int dealerId, String email,List<ShoppingCartItem> scis) throws SQLException {
        int maxId = microcatOrderDAO.findMaxScaniaOrderIdByDealerId(dealerId, email);
        List<MicrocatOrderItems> mois = microcatOrderItemsDAO.findScaniaOrderItemsByOrderId(maxId);
        log.info("size=="+mois.size());
        for (MicrocatOrderItems moi : mois) {
            int moiQuantity = moi.getQuantity();
            BigDecimal unitPrice = moi.getUnitPrice();
            BigDecimal price = unitPrice.multiply(new BigDecimal(moiQuantity));
            BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal cost = price.add(tax);
            //log.info("item details=="+moi.getProductCode()+"....."+moi.getDescription()+"....."+unitPrice+"....."+price+"......"+tax+"........"+cost);
            scis.add(new ShoppingCartItem(moi.getProductCode(), moi.getDescription(), unitPrice, price, tax, moiQuantity, cost, 0));
            
        }
        microcatOrderDAO.updateScaniaOrderStatusByAccountNumber(dealerId, email);
        return true;
    }
    public boolean combineOrdersFromMicrocat(String accountNumber, List<ShoppingCartItem> scis, int id, int supplierId,String product_table_name,String supNexusId) throws SQLException {
        String priceType = getPriceType(id);
        int maxId = microcatOrderDAO.findMaxMicrocatOrderIdByAccountNumber(accountNumber,supNexusId);
        List<MicrocatOrderItems> mois = microcatOrderItemsDAO.findMicrocatOrderItemsByOrderId(maxId);
        log.info("priceType="+priceType+"..maxId="+maxId+"..size=="+mois.size());
        for (MicrocatOrderItems moi : mois) {
            String moiProductCode = moi.getProductCode();
            int moiQuantity = moi.getQuantity();
            boolean existing = false;
            for (ShoppingCartItem sci : scis) {
                if (moiProductCode.equals(sci.getProductCode())) {
                    int totalQuantity = sci.getQuantity() + moi.getQuantity();
                    sci.setQuantity(totalQuantity);
                    BigDecimal unitPrice = sci.getUnitPrice();
                    BigDecimal price = unitPrice.multiply(new BigDecimal(totalQuantity));
                    sci.setPrice(price);
                    BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    sci.setTax(tax);
                    BigDecimal cost = price.add(tax);
                    sci.setCost(cost);
                    existing = true;
                    break;
                }
            }
            if (existing) {
                continue;
            } else {
                ShoppingCartItem item = productsUMGDAO.findProductDetailsForShoppingCart(id, supplierId, priceType, moiProductCode, moiQuantity,product_table_name);
                if (item != null) {
                    scis.add(item);
                } else {
                    BigDecimal unitPrice = moi.getUnitPrice();
                    BigDecimal price = unitPrice.multiply(new BigDecimal(moiQuantity));
                    BigDecimal tax = price.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal cost = price.add(tax);
                    scis.add(new ShoppingCartItem(moiProductCode, moi.getDescription(), unitPrice, price, tax, moiQuantity, cost, 0));
                }
            }
        }
        microcatOrderDAO.updateMicrocatOrderStatusByAccountNumber(accountNumber,supNexusId);
        return true;
    }

    public boolean combineOrdersFromMicrocatForFavourite(String accountNumber, List<TemplateOrderItem> tois, int id, int supplierId,String product_table_name,String supNexusId) throws SQLException {
        String priceType = getPriceType(id);
        int maxId = microcatOrderDAO.findMaxMicrocatOrderIdByAccountNumber(accountNumber,supNexusId);
        List<MicrocatOrderItems> mois = microcatOrderItemsDAO.findMicrocatOrderItemsByOrderId(maxId);
        for (MicrocatOrderItems moi : mois) {
            String moiProductCode = moi.getProductCode();
            boolean existing = false;
            for (TemplateOrderItem toi : tois) {
                if (moiProductCode.equals(toi.getProductCode())) {
                    existing = true;
                    break;
                }
            }
            if (existing) {
                continue;
            } else {
                TemplateOrderItem item = productsUMGDAO.findProductDetailsForTemplate(id, supplierId, priceType, moiProductCode,product_table_name);
                if (item != null) {
                    tois.add(item);
                } else {
                    tois.add(microcatOrderItemsDAO.findProductDetailsForTemplate(moiProductCode));
                }
            }
        }
        microcatOrderDAO.updateMicrocatOrderStatusByAccountNumber(accountNumber,supNexusId);
        return true;
    }

    public void updateMicrocatStatus(int microcatId) throws SQLException {
        microcatDAO.updateMicrocatStatus(microcatId, 0);
    }
    
    public Microcat getMicrocatInfoByTime(int id, int unlockedTime) throws SQLException {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        c.set(Calendar.HOUR_OF_DAY, hour - unlockedTime);
        Timestamp t = new Timestamp(c.getTimeInMillis());
        return microcatDAO.findMicroInfoByTime(id, t);
    }

    public void updateMicrocatTime(int microcatId) throws SQLException {
        Calendar c = Calendar.getInstance();
        Timestamp t = new Timestamp(c.getTimeInMillis());
        microcatDAO.updateMicrocatTime(microcatId, t);
    }

    public HashMap getParametersForPrintQuote(int qid) throws SQLException {
        HashMap reportParams = new HashMap();
        quotesDAO.getParametersForPrintQuote(qid, reportParams);
        return reportParams;
    }

    public Quote getQuoteDetailsByQid(int qid) throws SQLException {
        return quotesDAO.findQuoteDetailsByQid(qid);
    }

     public String findRuleValueMapping(String rule,int buyerid,int supid) throws SQLException{
        return rulesDAO.findRuleValueMapping(rule, buyerid, supid);
    }

     public String findRuleValueMappingbySupId(String rule,int supid) throws SQLException{
        return rulesDAO.findRuleValueMapping(rule,  supid);
    }
     
      public Microcat getScaniaInfo(int id) throws SQLException {
        return microcatDAO.findScaniaInfo(id);
    }
    
    public Microcat getScaniaInfoByTime(int id, int unlockedTime) throws SQLException {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        c.set(Calendar.HOUR_OF_DAY, hour - unlockedTime);
        Timestamp t = new Timestamp(c.getTimeInMillis());
        return microcatDAO.findScaniaInfoByTime(id, t);
    }
    
    public void updateScaniaTime(int microcatId) throws SQLException {
        Calendar c = Calendar.getInstance();
        Timestamp t = new Timestamp(c.getTimeInMillis());
        microcatDAO.updateScaniaTime(microcatId, t);
    }
    
    public void updateScaniaStatus(int microcatId) throws SQLException {
        microcatDAO.updateScaniaStatus(microcatId, 0);
    }

    /**
     * @return the messageDAO
     */
    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    /**
     * @param messageDAO the messageDAO to set
     */
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    /**
     * @return the productHistoryDAO
     */
    public ProductHistoryDAO getProductHistoryDAO() {
        return productHistoryDAO;
    }

    /**
     * @param productHistoryDAO the productHistoryDAO to set
     */
    public void setProductHistoryDAO(ProductHistoryDAO productHistoryDAO) {
        this.productHistoryDAO = productHistoryDAO;
    }

    /**
     * @return the autoOrderCountDAO
     */
    public AutoOrderCountDAO getAutoOrderCountDAO() {
        return autoOrderCountDAO;
    }

    /**
     * @param autoOrderCountDAO the autoOrderCountDAO to set
     */
    public void setAutoOrderCountDAO(AutoOrderCountDAO autoOrderCountDAO) {
        this.autoOrderCountDAO = autoOrderCountDAO;
    }

    /**
     * @return the favouriteOrderDAO
     */
    public FavouriteOrderDAO getFavouriteOrderDAO() {
        return favouriteOrderDAO;
    }

    /**
     * @param favouriteOrderDAO the favouriteOrderDAO to set
     */
    public void setFavouriteOrderDAO(FavouriteOrderDAO favouriteOrderDAO) {
        this.favouriteOrderDAO = favouriteOrderDAO;
    }

    /**
     * @return the favouriteLineItemsDAO
     */
    public FavouriteLineItemsDAO getFavouriteLineItemsDAO() {
        return favouriteLineItemsDAO;
    }

    /**
     * @param favouriteLineItemsDAO the favouriteLineItemsDAO to set
     */
    public void setFavouriteLineItemsDAO(FavouriteLineItemsDAO favouriteLineItemsDAO) {
        this.favouriteLineItemsDAO = favouriteLineItemsDAO;
    }

    
    /**
     * @return the partnerLinkDAO
     */
    public PartnerLinkDAO getPartnerLinkDAO() {
        return partnerLinkDAO;
    }

    /**
     * @param partnerLinkDAO the partnerLinkDAO to set
     */
    public void setPartnerLinkDAO(PartnerLinkDAO partnerLinkDAO) {
        this.partnerLinkDAO = partnerLinkDAO;
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

    /**
     * @return the messageDAO
     */
    public MessageDAO getMessageDAO() {
        return messageDAO;
    }

    /**
     * @param messageDAO the messageDAO to set
     */
    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    /**
     * @return the productsUMGDAO
     */
    public ProductsUMGDAO getProductsUMGDAO() {
        return productsUMGDAO;
    }

    /**
     * @param productsUMGDAO the productsUMGDAO to set
     */
    public void setProductsUMGDAO(ProductsUMGDAO productsUMGDAO) {
        this.productsUMGDAO = productsUMGDAO;
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

    /**
     * @return the orderItemsUMGDAO
     */
    public OrderItemsUMGDAO getOrderItemsUMGDAO() {
        return orderItemsUMGDAO;
    }

    /**
     * @param orderItemsUMGDAO the orderItemsUMGDAO to set
     */
    public void setOrderItemsUMGDAO(OrderItemsUMGDAO orderItemsUMGDAO) {
        this.orderItemsUMGDAO = orderItemsUMGDAO;
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
     * @return the microcatOrderDAO
     */
    public MicrocatOrderDAO getMicrocatOrderDAO() {
        return microcatOrderDAO;
    }

    /**
     * @param microcatOrderDAO the microcatOrderDAO to set
     */
    public void setMicrocatOrderDAO(MicrocatOrderDAO microcatOrderDAO) {
        this.microcatOrderDAO = microcatOrderDAO;
    }

    /**
     * @return the microcatOrderItemsDAO
     */
    public MicrocatOrderItemsDAO getMicrocatOrderItemsDAO() {
        return microcatOrderItemsDAO;
    }

    /**
     * @param microcatOrderItemsDAO the microcatOrderItemsDAO to set
     */
    public void setMicrocatOrderItemsDAO(MicrocatOrderItemsDAO microcatOrderItemsDAO) {
        this.microcatOrderItemsDAO = microcatOrderItemsDAO;
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

    /**
     * @return the umgOrdersQuotesDAO
     */
    public UMGOrdersQuotesDAO getUmgOrdersQuotesDAO() {
        return umgOrdersQuotesDAO;
    }

    /**
     * @param umgOrdersQuotesDAO the umgOrdersQuotesDAO to set
     */
    public void setUmgOrdersQuotesDAO(UMGOrdersQuotesDAO umgOrdersQuotesDAO) {
        this.umgOrdersQuotesDAO = umgOrdersQuotesDAO;
    }


    /**
     * @return the rulesDAO
     */
    public RulesDAO getRulesDAO() {
        return rulesDAO;
    }

    /**
     * @param rulesDAO the rulesDAO to set
     */
    public void setRulesDAO(RulesDAO rulesDAO) {
        this.rulesDAO = rulesDAO;
    }

}
