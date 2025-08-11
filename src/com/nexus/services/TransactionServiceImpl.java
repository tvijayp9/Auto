/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.services;

import com.nexus.dao.MessageDAO;
import com.nexus.dao.OrderDAO;

import com.nexus.dao.PartnerLinkDAO;
import com.nexus.dao.ProductHistoryDAO;
import com.nexus.dao.ProductsUMGDAO;
import com.nexus.dao.RegistrationDAO;
import com.nexus.domain.InvoiceLineItem;
import com.nexus.domain.JQGridRow;
import com.nexus.domain.Order;
import com.nexus.domain.OrderAddressData;
import com.nexus.domain.PartnerLink;
import com.nexus.domain.PurchaseOrder;
import com.nexus.web.common.MessagesList;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Terry
 */
@Transactional
public class TransactionServiceImpl implements TransactionService {
    Logger log=Logger.getLogger(TransactionServiceImpl.class);
    private MessageDAO messageDAO;
    private OrderDAO orderDAO;
    private PartnerLinkDAO partnerLinkDAO;
    
    private ProductHistoryDAO productHistoryDAO;
    private RegistrationDAO registrationDAO;
    private ProductsUMGDAO productsUMGDAO;

    public int getInBoundMessageListCount(String id, String search, String vch_document_id, String company, String dt_received,String dt1_received) throws SQLException {
        return messageDAO.findInBoundMessageListCount(id, search, vch_document_id, company, dt_received, dt1_received);
    }

    public int getInBoundArchivedMessageListCount(String id, String search, String vch_document_id, String company, String dt_received,String dt1_received) throws SQLException {
        return messageDAO.findInBoundArchivedMessageListCount(id, search, vch_document_id, company, dt_received,dt1_received);
    }

    public List getInBoundMessageList(String id, String search, String vch_document_id, String company, String dt_received,String dt1_received, int start, int limit, String sidx, String sord) throws SQLException {
        List messageLists = messageDAO.findInBoundMessageList(id, search, vch_document_id, company, dt_received,dt1_received, start, limit, sidx, sord);
        List<JQGridRow> rows = new ArrayList();
        Iterator i = messageLists.iterator();
        while (i.hasNext()) {
            JQGridRow row = new JQGridRow();
            MessagesList ml = (MessagesList) i.next();
            row.setId(ml.getId());
            List<String> cell = new ArrayList();
            cell.add(ml.getDocid());
            cell.add(ml.getType());
            cell.add(ml.getFrom());
            cell.add(ml.getDate());
            cell.add(ml.getStatus());
//            String vchDocumentId = ml.getDocid();
//            String vchPath = ml.getFilename();
           // cell.add(ml.getDocid());
             cell.add(ml.getFrom());
                cell.add("1");
//            if ((vchPath.startsWith("Nexus_PO_")) && (vchPath.indexOf(vchDocumentId) != -1)) {
//                cell.add("1");
//                cell.add("1");
//            } else if ((vchPath.startsWith("AusDrill_PO_")) && (vchPath.indexOf(vchDocumentId) != -1)) {
//                cell.add(vchDocumentId);
//                cell.add("1");
//            }else {
//                cell.add("0");
//                cell.add("0");
//            }
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public List getInBoundArchivedMessageList(String id, String search, String vch_document_id, String company, String dt_received,String dt1_received, int start, int limit, String sidx, String sord) throws SQLException {
        List messageLists = messageDAO.findInBoundArchivedMessageList(id, search, vch_document_id, company,dt_received, dt1_received, start, limit, sidx, sord);
        List<JQGridRow> rows = new ArrayList();
        Iterator i = messageLists.iterator();
        while (i.hasNext()) {
            JQGridRow row = new JQGridRow();
            MessagesList ml = (MessagesList) i.next();
            row.setId(ml.getId());
            List<String> cell = new ArrayList();
            cell.add(ml.getDocid());
            cell.add(ml.getType());
            cell.add(ml.getFrom());
            cell.add(ml.getDate());
            cell.add(ml.getStatus());
            String vchDocumentId = ml.getDocid();
            String vchPath = ml.getFilename();
            if ((vchPath.startsWith("Nexus_PO_")) && (vchPath.indexOf(vchDocumentId) != -1)) {
                cell.add("1");
                cell.add("1");
            } else {
                cell.add("0");
                cell.add("0");
            }
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public void updateMessageStatus(String id, int status) throws SQLException {
        String[] rowId = id.split("\\,");
        for (int i = 0; i < rowId.length; i++) {
            messageDAO.updateMessageStatus(rowId[i], status);
        }
    }

    public int getOutBoundMessageListCount(String id, String search, String vch_document_id, String company, String dt_received,String dt1_received) throws SQLException {
        return messageDAO.findOutBoundMessageListCount(id, search, vch_document_id, company, dt_received,dt1_received);
    }

    public int getOutBoundArchivedMessageListCount(String id, String search, String vch_document_id, String company, String dt_received,String dt1_received) throws SQLException {
        return messageDAO.findOutBoundArchivedMessageListCount(id, search, vch_document_id, company, dt_received,dt1_received);
    }
    
    public List getOutBoundMessageList(String id, String search, String vch_document_id, String company, String dt_received,String dt1_received, int start, int limit, String sidx, String sord) throws SQLException {
        List messageLists = messageDAO.findOutBoundMessageList(id, search, vch_document_id, company, dt_received,dt1_received, start, limit, sidx, sord);
        List<JQGridRow> rows = new ArrayList();
        Iterator i = messageLists.iterator();
        while (i.hasNext()) {
            JQGridRow row = new JQGridRow();
            MessagesList ml = (MessagesList) i.next();
            row.setId(ml.getId());
            List<String> cell = new ArrayList();
            cell.add(ml.getDocid());
            cell.add(ml.getType());
            cell.add(ml.getTo());
            cell.add(ml.getDate());
            cell.add(ml.getStatus());
            String vchDocumentId = ml.getDocid();
            String vchPath = ml.getFilename();
            if ((vchPath.startsWith("Nexus_PO_")) && (vchPath.indexOf(vchDocumentId) != -1)) {
                cell.add("1");
                cell.add("1");
            } else {
                cell.add("0");
                cell.add("0");
            }
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public List getOutBoundArchivedMessageList(String id, String search, String vch_document_id, String company, String dt_received,String dt1_received, int start, int limit, String sidx, String sord) throws SQLException {
        List messageLists = messageDAO.findOutBoundArchivedMessageList(id, search, vch_document_id, company, dt_received,dt1_received, start, limit, sidx, sord);
        List<JQGridRow> rows = new ArrayList();
        Iterator i = messageLists.iterator();
        while (i.hasNext()) {
            JQGridRow row = new JQGridRow();
            MessagesList ml = (MessagesList) i.next();
            row.setId(ml.getId());
            List<String> cell = new ArrayList();
            cell.add(ml.getDocid());
            cell.add(ml.getType());
            cell.add(ml.getTo());
            cell.add(ml.getDate());
            cell.add(ml.getStatus());
            String vchDocumentId = ml.getDocid();
            String vchPath = ml.getFilename();
            if ((vchPath.startsWith("Nexus_PO_")) && (vchPath.indexOf(vchDocumentId) != -1)) {
                cell.add("1");
                cell.add("1");
            } else {
                cell.add("0");
                cell.add("0");
            }
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }

    public PurchaseOrder getPurchaseOrderFromInboundTransaction(String messageId) throws SQLException, ParseException {
        Order order = orderDAO.findOrderByMessageId(messageId);
        if (order != null) {
            String orderNumber = order.getOrderNo();
            String comment = order.getComments();
            PartnerLink partnerLink = partnerLinkDAO.findPartnerLinkByBuyerIdAndSupplierId(order.getBuyId(), order.getSupplierId());
            String customerCode = partnerLink.getBuyerName();
            String deliveryDate = dateFormat(order.getDeliveryDate());
            List items = productsUMGDAO.findOrderItems(order.getId());
            return new PurchaseOrder(customerCode, orderNumber, comment, deliveryDate, items);
        } else {
            return null;
        }
    }
    
    public PurchaseOrder getPurchaseOrder(String messageId) throws SQLException, ParseException {
        Order order = orderDAO.findOrderByMsgId(messageId);
        if (order != null) {
            String orderNumber = order.getOrderNo();
            String comment = order.getComments();
            PartnerLink partnerLink = partnerLinkDAO.findPartnerLinkByBuyerIdAndSupplierId(order.getBuyId(), order.getSupplierId());
            String customerCode = partnerLink.getBuyerName();
            String deliveryDate = dateFormat(order.getDeliveryDate());
            List items = productsUMGDAO.findOrderItems(order.getId());
            return new PurchaseOrder(customerCode, orderNumber, comment, deliveryDate,order.getInvoiceNo(), items);
        } else {
            return null;
        }
    }
    
    public PurchaseOrder getPurchaseOrderforInvoice(String messageId) throws SQLException, ParseException {
        Order order = orderDAO.findOrderByMsgId(messageId);
        if (order != null) {
            String orderNumber = order.getOrderNo();
            String comment = order.getComments();
            Integer orderId=order.getId();
            PartnerLink partnerLink = partnerLinkDAO.findPartnerLinkByBuyerIdAndSupplierId(order.getBuyId(), order.getSupplierId());
            String customerCode = partnerLink.getBuyerName();
            String deliveryDate = dateFormat(order.getDeliveryDate());
            //List items = productsUMGDAO.findOrderItemsforInvoice(order.getId());
            List items = productsUMGDAO.findInvoiceLineItems(order.getId());
            return new PurchaseOrder(orderId,customerCode, orderNumber, comment, deliveryDate,order.getInvoiceNo(), items);
        } else {
            return null;
        }
    }
    
    public List constructInvoiceLineItems(List<InvoiceLineItem> invoiceLineItems) throws SQLException {
        List<JQGridRow> rows = new ArrayList();
        Iterator i = invoiceLineItems.iterator();
        while (i.hasNext()) {
            JQGridRow row = new JQGridRow();
            InvoiceLineItem invoiceLineItem = (InvoiceLineItem) i.next();
            if(invoiceLineItem.getLineItemId()!=invoiceLineItem.getInvoiceLineItemId())
                row.setId(invoiceLineItem.getInvoiceLineItemId());
            else
                row.setId(invoiceLineItem.getLineItemId());
            List<String> cell = new ArrayList();
//            cell.add(invoiceLineItem.getLineNo()+"");
            cell.add(invoiceLineItem.getProductCode());
            cell.add(invoiceLineItem.getDescription());
            cell.add(invoiceLineItem.getOrderedQuantity()+"");
            cell.add(invoiceLineItem.getQuantity()+"");
            cell.add(invoiceLineItem.getUnitprice().toString());
            cell.add(invoiceLineItem.getPrice().toString());
            cell.add(invoiceLineItem.getTax().toString());
            cell.add(invoiceLineItem.getCost().toString());
            cell.add(invoiceLineItem.getInvoiceNo());
            //cell.add(invoiceLineItem.getInvoiceLineItemId()+"");
            log.info("id=="+invoiceLineItem.getLineItemId()+"...getInvoiceLineItemId=="+invoiceLineItem.getInvoiceLineItemId()+"...invoiceLineItem.getOrderedQuantity()="+invoiceLineItem.getOrderedQuantity()+"..invoioceNo=="+invoiceLineItem.getInvoiceNo()+"...product code=="+invoiceLineItem.getProductCode());
            log.info("unit price=="+invoiceLineItem.getUnitprice()+"...price=="+invoiceLineItem.getPrice()+"...tax="+invoiceLineItem.getTax()+"..Cost=="+invoiceLineItem.getCost());
            row.setCell(cell);
            rows.add(row);
        }
        return rows;
    }
    
    public PurchaseOrder getPurchaseOrderInfo(String orderNo) throws SQLException, ParseException {
        Order order = orderDAO.findOrderByorderNo(orderNo);
        if (order != null) {
            String deliveryDate = dateFormat(order.getDeliveryDate());
            List items = productsUMGDAO.findInvoiceItems(order.getId());
            return new PurchaseOrder("AusDrill", order.getOrderNo(), order.getComments(), deliveryDate, order.getInvoiceNo(),items);
        } else {
            return null;
        }
    }

    public PurchaseOrder getPurchaseOrderFromArchivedInboundTransaction(String messageId) throws SQLException, ParseException {
        Order order = orderDAO.findArchivedOrderByMessageId(messageId);
        if (order != null) {
            String orderNumber = order.getOrderNo();
            String comment = order.getComments();
            PartnerLink partnerLink = partnerLinkDAO.findPartnerLinkByBuyerIdAndSupplierId(order.getBuyId(), order.getSupplierId());
            String customerCode = partnerLink.getBuyerNumber().trim() + " " + partnerLink.getBuyerName();
            String deliveryDate = dateFormat(order.getDeliveryDate());
            List items = productHistoryDAO.findArchivedOrderItemsByOrderId(order.getId());
            return new PurchaseOrder(customerCode, orderNumber, comment, deliveryDate, items);
        } else {
            return null;
        }
    }


    public String dateFormat(String date) throws ParseException {
        SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormatter2 = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormatter2.format(dateFormatter1.parse(date));
    }

    public PurchaseOrder getPurchaseOrderFromOutboundTransaction(String messageId) throws SQLException, ParseException {
        Order order = orderDAO.findOrderByMessageId(messageId);
        if (order != null) {
            String orderNumber = order.getOrderNo();
            String comment = order.getComments();
            String customerCode = registrationDAO.findCompanyById(order.getSupplierId());
            String deliveryDate = dateFormat(order.getDeliveryDate());
            List items = productHistoryDAO.findOrderItemsByOrderId(order.getId());
            return new PurchaseOrder(customerCode, orderNumber, comment, deliveryDate, items);
        } else {
            return null;
        }
    }

    public PurchaseOrder getPurchaseOrderFromArchivedOutboundTransaction(String messageId) throws SQLException, ParseException {
        Order order = orderDAO.findArchivedOrderByMessageId(messageId);
        if (order != null) {
            String orderNumber = order.getOrderNo();
            String comment = order.getComments();
            String customerCode = registrationDAO.findCompanyById(order.getSupplierId());
            String deliveryDate = dateFormat(order.getDeliveryDate());
            List items = productHistoryDAO.findArchivedOrderItemsByOrderId(order.getId());
            return new PurchaseOrder(customerCode, orderNumber, comment, deliveryDate, items);
        } else {
            return null;
        }
    }

    public List getAllSelectedOrders(String id) throws SQLException, ParseException {
        String[] rowId = id.split("\\,");
        List allSelectedOrders = new ArrayList();
        for (int i = 0; i < rowId.length; i++) {
            String messageId = rowId[i];
            PurchaseOrder po = getPurchaseOrderFromInboundTransaction(messageId);
            if (po != null) {
                allSelectedOrders.add(po);
                updateMessageStatus(messageId, 1);
            }
        }
        return allSelectedOrders;
    }

    public List getAllSelectedArchivedInboundOrders(String id) throws SQLException, ParseException {
        String[] rowId = id.split("\\,");
        List allSelectedOrders = new ArrayList();
        for (int i = 0; i < rowId.length; i++) {
            String messageId = rowId[i];
            PurchaseOrder po = getPurchaseOrderFromArchivedInboundTransaction(messageId);
            if (po != null) {
                allSelectedOrders.add(po);
                updateMessageStatus(messageId, 1);
            }
        }
        return allSelectedOrders;
    }
    
    public List getAllNewOrders(int id) throws SQLException, ParseException {
        List messages = messageDAO.findInBoundMessageByStatus(0, id);
        Iterator i = messages.iterator();
        List allNewOrders = new ArrayList();
        while (i.hasNext()) {
            String messageId = (String) i.next();
            PurchaseOrder po = getPurchaseOrderFromInboundTransaction(messageId);
            if (po != null) {
                allNewOrders.add(po);
                updateMessageStatus(messageId, 1);
            }
        }
        return allNewOrders;
    }
    
    public void generateInvoice(String orderNo,String invoiceNo) throws SQLException {
        orderDAO.generateInvoice(orderNo, invoiceNo);
    }

    public int getAllNewOrdersCount(int id) throws SQLException{
      return messageDAO.findInBoundMessageCountByStatus(0, id);
    }

    public OrderAddressData getCXMLOrderAddress(Integer messageId) throws SQLException{
        return orderDAO.getCXMLOrderAddress(messageId);
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
     * @return the orderDAO
     */
    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    /**
     * @param orderDAO the orderDAO to set
     */
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
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
}
