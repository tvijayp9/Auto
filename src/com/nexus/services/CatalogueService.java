/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.services;

import com.nexus.domain.InvoiceLineItem;
import com.nexus.domain.JQGridRow;
import com.nexus.domain.Microcat;
import com.nexus.domain.Order;
import com.nexus.domain.OrderAddressData;
import com.nexus.domain.PartnerLink;
import com.nexus.domain.PrintOrder;
import com.nexus.domain.Quote;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import com.nexus.domain.ShoppingCartItem;
import com.nexus.domain.SubTotal;
import com.nexus.domain.TemplateOrderItem;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 *
 * @author Terry
 */
public interface CatalogueService {

    int getOrderListCount(String id, String supId, String search, String orderNumber, String orderDate, String orderDate1) throws SQLException;

    int getArchivedOrderListCount(String id, String supId, String search, String orderNumber, String orderDate, String order_date1) throws SQLException;

    List getOrderList(String id, String supId, String search, String orderNumber, String orderDate, String orderDate1, int start, int limit, String sidx, String sord) throws SQLException;

    List getArchivedOrderList(String id, String supId, String search, String orderNumber, String orderDate, String orderDate1, int start, int limit, String sidx, String sord) throws SQLException;

    int getOrderItemsCount(String orderId) throws SQLException;

    int getArchivedOrderItemsCount(String orderId) throws SQLException;

    List getOrderItems(String orderId, int start, int limit, String sidx, String sord) throws SQLException;

    List getOrderItems(int orderId) throws SQLException;

    List getArchivedOrderItems(String orderId, int start, int limit, String sidx, String sord) throws SQLException;

    int getFutureOrderListCount(String id, String supId, String search, String orderNumber, String orderDate) throws SQLException;

    List getFutureOrderList(String id, String supId, String search, String orderNumber, String orderDate, int start, int limit, String sidx, String sord) throws SQLException;

    String getFormattedDate();

    Order createOrder(String id, String supId, Date orderDate, String comment) throws SQLException;

    Order createOrderQuote(String id, String supId, Date orderDate, String comment) throws SQLException;

    Order createOrderForQuote(String id, String supId, Date orderDate, String comment, String quoteId) throws SQLException;

    int getOrderId(String orderNumber, String id, String supId) throws SQLException;

    void addFavouriteOrder(String orderNumber, String favouriteName, int id, int supplierId) throws SQLException;

    void saveNewTemplate(String templateName, List<TemplateOrderItem> template, int id, int supplierId) throws SQLException;

    String saveNewQuote(String quoteName, List<TemplateOrderItem> quote, int id, int supplierId) throws SQLException;

    void saveModifiedTemplate(int templateId, List<TemplateOrderItem> template, int id, int supplierId) throws SQLException;

    void saveModifiedQuote(int templateId, List<TemplateOrderItem> template, int id, int supplierId) throws SQLException;

    int getFavouriteOrderListCount(int id, int supplierId) throws SQLException;

    List getFavouriteOrderList(int id, int supplierId, int start, int limit, String sidx, String sord) throws SQLException;

    int getQuotesListCount(int id, int supplierId) throws SQLException;

    List getQuotesList(int id, int supplierId, int start, int limit, String sidx, String sord) throws SQLException;

    void deleteFavouriteOrder(String id) throws SQLException;

    void deleteQuotes(String id) throws SQLException;

    boolean checkOrderReceived(String microcatId,String accountNumber,String nexusId) throws SQLException;

    int getSupplierProductsByCategoryIdCount(String searchFor, String searchIn,String product_table_name) throws SQLException;

    int getTemplateOrderItemsByTemplateIdCount(int templateId,String product_table_name) throws SQLException;

    List getTemplateOrderItemsByTemplateId(int templateId, int id, int supplierId, int start, int limit, String sidx, String sord,String product_table_name) throws SQLException;

    int getQuoteItemsByQuoteIdCount(int quoteId) throws SQLException;

    List getQuoteItemsByQuoteId(int quoteId, int start, int limit, String sidx, String sord) throws SQLException;

    List getQuoteItemsByQuoteId(int quoteId) throws SQLException;

    List getSupplierProductsByCategoryId(int id, int supplierId, String searchFor, String searchIn, int start, int limit, String sidx, String sord,String product_table_name) throws SQLException;

    List getShoppingCart(List<ShoppingCartItem> shoppingCart, int start, int limit) throws SQLException;

    List getTemplate(List<TemplateOrderItem> template, int start, int limit) throws SQLException;

    List getQuote(List<TemplateOrderItem> template, int start, int limit) throws SQLException;

    void addShoppingCartItem(int id, int supplierId, String[] productCode, List<ShoppingCartItem> shoppingCart,String product_table_name) throws SQLException;

    void addQuoteShoppingCartItem(String itemsId, List<ShoppingCartItem> shoppingCart) throws SQLException;

    void addTemplateOrderItemsToShoppingcart(int id, int supplierId, String[] templateOrderItemsId, List<ShoppingCartItem> shoppingCart,String product_table_name) throws SQLException;

    void addTemplateOrderItem(int id, int supplierId, String[] productCode, List<TemplateOrderItem> template,String product_table_name) throws SQLException;

    List<TemplateOrderItem> addQuoteItem(int id, int supplierId, String[] productCode, List<TemplateOrderItem> quote,String product_table_name) throws SQLException;

    void deleteShoppingCartItem(String productCode, List<ShoppingCartItem> shoppingCart);

    void deleteTemplateOrderItem(String productCode, List<TemplateOrderItem> template);

    void deleteTemplateOrderItem(String templateOrderItemsId) throws SQLException;

    void deleteQuoteItem(String quoteItemsId) throws SQLException;

    void updateQuantity(String productCode, int quantity, List<ShoppingCartItem> shoppingCart);

    void updateQuoteItemQuantity(String productCode, int quantity, List<TemplateOrderItem> quoteItems);

    void updateQuoteItemQuantity(String id, int quantity) throws SQLException;

    SubTotal getSubtotal(List<ShoppingCartItem> shoppingCart);

    SubTotal getSubtotalForPrintOrder(List<PrintOrder> list);

    void createOrderItemsForShoppingCart(List<ShoppingCartItem> shoppingCart, int id, int supId, String orderNumber, String autoOrderNumber, Date deliveryDate, String comment) throws SQLException;

    void createOrderQuoteItemsForShoppingCart(List<ShoppingCartItem> shoppingCart, int id, int supId, String orderNumber, String autoOrderNumber, Date deliveryDate, String comment) throws SQLException;

    void createOrderItemsForQuote(List<ShoppingCartItem> shoppingCart, int id, int supId, String orderNumber, String autoOrderNumber) throws SQLException;

    int getSupplierIdById(int id) throws SQLException;

    Microcat getMicrocatInfo(int id) throws SQLException;

    boolean combineOrdersFromMicrocat(String accountNumber, List<ShoppingCartItem> sci, int id, int supplierId,String product_table_name,String supNexusId) throws SQLException;

    boolean combineOrdersFromMicrocatForFavourite(String accountNumber, List<TemplateOrderItem> tois, int id, int supplierId,String product_table_name,String supNexusId) throws SQLException;

    void updateMicrocatStatus(int microcatId) throws SQLException;

    Microcat getMicrocatInfoByTime(int id, int unlockedTime) throws SQLException;

    void updateMicrocatTime(int microcatId) throws SQLException;

    HashMap getParametersForPrintQuote(int qid) throws SQLException;

    void addQuoteFromShoppingcart(String orderNumber, String favouriteName, int id, int supplierId) throws SQLException;

    Quote getQuoteDetailsByQid(int qid) throws SQLException;

    String findRuleValueMapping(String rule,int buyerid,int supid) throws SQLException;

    public String findRuleValueMappingbySupId(String rule,int supid) throws SQLException;

    public SubTotal getSubtotalforTemplate(List<TemplateOrderItem> templateorder);

    public PartnerLink getOrderComment(String id, String supId) throws SQLException;

    public Order createCXMLOrder(String id, String supId, String orderDate, String comment,String orderNumber,String quoteNo,OrderAddressData orderAddressData) throws SQLException;

    public void createCXMLOrderItemsForShoppingCart(List<ShoppingCartItem> shoppingCart, int id, int supId, String orderNumber, String autoOrderNumber, String deliveryDate, String comment,Integer orderid) throws SQLException;
    
    public SubTotal getSubtotalForInvoice(List<InvoiceLineItem> list);
    
    public void updateQuantityforInvoice(String productCode, int quantity,BigDecimal unitPrice, List<InvoiceLineItem> invoiceLineItems);
    
    public void addInvoiceNotoList(String invoiceNo,String[] productCode, List<InvoiceLineItem> invoiceLineItems) throws SQLException;

    public void createInvoiceItems(List<InvoiceLineItem> invoiceLineItems) throws SQLException;
    
    Microcat getScaniaInfo(int id) throws SQLException;
    
    Microcat getScaniaInfoByTime(int id, int unlockedTime) throws SQLException;
    
    void updateScaniaTime(int microcatId) throws SQLException;
    
    void updateScaniaStatus(int microcatId) throws SQLException;
    
    boolean checkScaniaOrderReceived(String microcatId,String accountNumber,String nexusId,String email) throws SQLException;
    
    public boolean orderFromScania(int dealerId, String email,List<ShoppingCartItem> scis) throws SQLException;
}
