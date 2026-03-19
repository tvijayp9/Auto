/*
 * XCBLOrderProcessor.java
 *
 * Created on 12 October 2004, 11:03
 */
package com.nexus.util.DR.traderoute;

import com.nexus.services.DataResolutionService;
import com.nexus.services.DataResolutionServiceImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Security;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

// Root Wrapper Class
class ApiDataWrapper {

    @JsonProperty("ApiData")
    public ApiData apiData;

    public ApiDataWrapper(ApiData apiData) {
        this.apiData = apiData;
    }
}

// Model Classes
class ApiData {

    @JsonProperty("BasketData")
    private BasketData basketData;

    public ApiData(BasketData basketData) {
        this.basketData = basketData;
    }

    public BasketData getBasketData() {
        return basketData;
    }
}


class BasketData {

    @JsonProperty("BasketLineData")
    private List<BasketLineData> basketLineData;
    @JsonProperty("OrderData")
    private List<OrderData> orderData;
    public BasketData(){}
    public BasketData(List<BasketLineData> basketLineData) {
        this.basketLineData = basketLineData;
    }
    
    public List<BasketLineData> getBasketLineData() {
        return basketLineData;
    }

    /**
     * @return the orderData
     */
    public List<OrderData> getOrderData() {
        return orderData;
    }

    /**
     * @param orderData the orderData to set
     */
    public void setOrderData(List<OrderData> orderData) {
        this.orderData = orderData;
    }

   
}

class OrderData {

    @JsonProperty("CustomerPO")
    private String customerPO;
    @JsonProperty("Notes")
    private String notes;

    public OrderData(String customerPO, String notes){
        this.customerPO = customerPO;
        this.notes = notes;
    }
    /**
     * @return the customerPO
     */
    public String getCustomerPO() {
        return customerPO;
    }

    /**
     * @param customerPO the customerPO to set
     */
    public void setCustomerPO(String customerPO) {
        this.customerPO = customerPO;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
}

class BasketLineData {

    @JsonProperty("FranchiseID")
    private String franchiseID;
    @JsonProperty("PartID")
    private String partID;
    @JsonProperty("Quantity")
    private int quantity;

    public BasketLineData(String franchiseID, String partID, int quantity) {
        this.franchiseID = franchiseID;
        this.partID = partID;
        this.quantity = quantity;
    }

    /**
     * @return the franchiseID
     */
    public String getFranchiseID() {
        return franchiseID;
    }

    /**
     * @param franchiseID the franchiseID to set
     */
    public void setFranchiseID(String franchiseID) {
        this.franchiseID = franchiseID;
    }

    /**
     * @return the partID
     */
    public String getPartID() {
        return partID;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "BasketLineDat{"
                + "franchiseID='" + franchiseID + '\''
                + ", partID='" + partID + '\''
                + ", quantity=" + quantity
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BasketLineData that = (BasketLineData) obj;
        return partID.equals(that.partID) && franchiseID.equals(that.franchiseID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(franchiseID, partID);
    }
}

class OrderDetails {

    private String orderNo;
    private int orderLine;
    private int orderSubLine;
    private String franchiseID;
    private String partID;
    private String description;
    private String orderLineStatus;
    private double orderQty;
    private double backOrderQty;
    private double allocatedQty;
    private double unitPrice;
    private double extPrice;
    private int lineStatus;
    private double extPriceIncTax;
    // Constructor

    public OrderDetails(String orderNo, int orderLine, int orderSubLine, String franchiseID, String partID,
            String description, String orderLineStatus, double orderQty, double backOrderQty,
            double allocatedQty, double unitPrice, double extPrice, int lineStatus, double extPriceIncTax) {
        this.orderNo = orderNo;
        this.orderLine = orderLine;
        this.orderSubLine = orderSubLine;
        this.franchiseID = franchiseID;
        this.partID = partID;
        this.description = description;
        this.orderLineStatus = orderLineStatus;
        this.orderQty = orderQty;
        this.backOrderQty = backOrderQty;
        this.allocatedQty = allocatedQty;
        this.unitPrice = unitPrice;
        this.extPrice = extPrice;
        this.lineStatus = lineStatus;
        this.extPriceIncTax = extPriceIncTax;
    }

    // To String for debugging
    @Override
    public String toString() {
        return "OrderDetails{"
                + "OrderNo='" + orderNo + '\''
                + ", OrderLine=" + orderLine
                + ", OrderSubLine=" + orderSubLine
                + ", FranchiseID='" + franchiseID + '\''
                + ", PartID='" + partID + '\''
                + ", Description='" + description + '\''
                + ", OrderLineStatus='" + orderLineStatus + '\''
                + ", OrderQty=" + orderQty
                + ", BackOrderQty=" + backOrderQty
                + ", AllocatedQty=" + allocatedQty
                + ", UnitPrice=" + unitPrice
                + ", ExtPrice=" + extPrice
                + ", LineStatus=" + lineStatus
                + ", ExtPriceIncTax=" + extPriceIncTax
                + '}';
    }

    /**
     * @return the orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @return the orderLine
     */
    public int getOrderLine() {
        return orderLine;
    }

    /**
     * @return the orderSubLine
     */
    public int getOrderSubLine() {
        return orderSubLine;
    }

    /**
     * @return the franchiseID
     */
    public String getFranchiseID() {
        return franchiseID;
    }

    /**
     * @return the partID
     */
    public String getPartID() {
        return partID;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the orderLineStatus
     */
    public String getOrderLineStatus() {
        return orderLineStatus;
    }

    /**
     * @return the orderQty
     */
    public double getOrderQty() {
        return orderQty;
    }

    /**
     * @return the backOrderQty
     */
    public double getBackOrderQty() {
        return backOrderQty;
    }

    /**
     * @return the allocatedQty
     */
    public double getAllocatedQty() {
        return allocatedQty;
    }

    /**
     * @return the unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @return the extPrice
     */
    public double getExtPrice() {
        return extPrice;
    }

    /**
     * @return the lineStatus
     */
    public int getLineStatus() {
        return lineStatus;
    }

    /**
     * @return the extPriceIncTax
     */
    public double getExtPriceIncTax() {
        return extPriceIncTax;
    }
}

class MyAuthenticator extends Authenticator {

    private String username;
    private String password;

    public MyAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}

/**
 *
 * @author user
 */
public class XCBLOrderProcessor {

    private static final String SERVER_URL = "https://tstapi.revolutionnext.com.au";
    private static final String AUTH_URL = "/Auth.Authenticator/authenticate";
    private static final String UPDATE_BASKET_URL = "/api/web/v1/Parts.PartsPortal/updateBasket";
    private static final String EMPTY_BASKET_URL = "/api/web/v1/Parts.PartsPortal/emptyBasket";
    private static final String BASKET_TO_ORDER_URL = "/api/web/v1/Parts.PartsPortal/basketToOrder";
    private static final String GET_ORDER_LINES_URL = "/api/web/v1/Parts.PartsPortal/getOrderLines?OrderNo=";
    private static final String GET_ORDER_URL = "/api/web/v1/Parts.PartsPortal/getOrders?OrderNo=";
    private static final String SEARCH_PARTS_URL = "/api/web/v1/Parts.PartsPortal/searchParts?PageSize=5&FranchiseID=&PartID=";
    private static final String X_API_KEY = "X-API-KEY";
    private static final String X_API_VALUE = "4198ZSIoeMfDXdw4Cmq5dj7/wIbqvI31gmX3QZwYADkeERyX0RyFuM4DebZe8G/cHe+7qeErg8+9YGKovA4y7VnftaYctyfKq9do2gcqmelrCx2VgSgrCLbpdgEbJc5cW3BbClcQ323zA7jbUWyt/yPUnbMc1dWT5ST9ASmfrgHnv+hWF";
    private static final String X_SENDERID = "X-SenderID";
    private static final String X_SENDERID_VALUE = "Tbone";
    private static final String X_RECEIVERID = "X-ReceiverID";
    private static final String X_RECEIVERID_VALUE = "61.T00003.011130";
    private static final String USERNAME = "nexus.order.allstock"; // Replace with actual username
    private static final String PASSWORD = "W3lc@mE2R3v!";
    //private static DbCommunicatorImpl dbCommunicatorImpl = null;
    Logger log = Logger.getLogger(XCBLOrderProcessor.class);
    private DataResolutionService dataResolutionService = new DataResolutionServiceImpl();

    /**
     * Creates a new instance of XCBLOrderProcessor
     */
    public XCBLOrderProcessor() throws Exception {
        // dbCommunicatorImpl=DbCommunicatorImpl.getInstance("C:\\TradeRoute\\config_files\\edxconfig.xml");
    }

    public void postProcess(Document doc) throws Exception {
        String transId = doc.getRootElement().getChild("DOCDATA").getChild("TRADEROUTEVALUES").getChild("TRANSACTION_ID").getTextTrim();
        String alternateTransNumber = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderNumber").getChild("SellerOrderNumber").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").getTextTrim();
//        dataResolutionService.updateAlternateTransactionNumber(transId, invoiceNo);
    }

    public void accept(Document doc, String invoiceNo) throws Exception {
        log.info("Accept processing Newtown orders");
        String transId = doc.getRootElement().getChild("DOCDATA").getChild("TRADEROUTEVALUES").getChild("TRANSACTION_ID").getTextTrim();
        doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("TempItemCoded").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText("Accepted");
        if (invoiceNo != "") {
            doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderNumber").getChild("SellerOrderNumber").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText(invoiceNo);
        }

        List lineItems = null;
        /**
         * set all line items to Accepted status
         */
        lineItems = XPath.newInstance("//EDXDATA/DOCDATA/BUSOBJ/Order/OrderDetail/ListOfItemDetail/ItemDetail").selectNodes(doc.getRootElement());
        Iterator it = lineItems.iterator();

        while (it.hasNext()) {
            Element currLineItem = (Element) it.next();
            currLineItem.getChild("TempItemCoded").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText("ItemAccepted");
        }
        dataResolutionService.updateAlternateTransactionNumber(transId, invoiceNo);
    }

    public void reject(Document doc, String reason) throws Exception {
        log.info("Reject processing Newtown orders doc type==" + doc.getDocType() + "..root element==" + doc.getRootElement().getName());
        try {
            doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("TempItemCoded").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText("NotAccepted");
            if (reason != "") {
                doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("TempOrderResponseHeaderNote").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText(reason);
            }
            /**
             * set all line items to Rejected status
             */
            List lineItems = XPath.newInstance("//EDXDATA/DOCDATA/BUSOBJ/Order/OrderDetail/ListOfItemDetail/ItemDetail").selectNodes(doc.getRootElement());
            Iterator it = lineItems.iterator();

            while (it.hasNext()) {
                Element currLineItem = (Element) it.next();
                currLineItem.getChild("TempItemCoded").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText("ItemRejected");
            }
        } catch (Exception npe) {
            npe.printStackTrace();
        }

    }

    public void accept_kala(Document doc, Integer nexusId) throws Exception {
        log.info("Accept processing Kalamunda orders nexusId=" + nexusId);
        HashMap prodDesc = null;

        String transId = doc.getRootElement().getChild("DOCDATA").getChild("TRADEROUTEVALUES").getChild("TRANSACTION_ID").getTextTrim();
        doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("TempItemCoded").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText("Accepted");
        String orderDate = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderIssueDate").getText();

        String orderNo = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderNumber").getChild("BuyerOrderNumber").getText();
        String comment1 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("ShipToParty").getChild("Party").getChild("NameAddress").getChild("Name2").getText();
        String comment2 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("ShipToParty").getChild("Party").getChild("NameAddress").getChild("Name3").getText();
        String comment3 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("ShipToParty").getChild("Party").getChild("NameAddress").getChild("Street").getText();
        String comment4 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("ShipToParty").getChild("Party").getChild("NameAddress").getChild("City").getText();
        String comment5 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("ShipToParty").getChild("Party").getChild("NameAddress").getChild("City").getText();
        String shipTo2 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("ShipToParty").getChild("Party").getChild("NameAddress").getChild("Name1").getText();
        String billTo1 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("BillToParty").getChild("Party").getChild("NameAddress").getChild("Name1").getText();
        String billTo2 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("BillToParty").getChild("Party").getChild("NameAddress").getChild("Street").getText();
        String billTo3 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("BillToParty").getChild("Party").getChild("NameAddress").getChild("Name2").getText();
        String billTo4 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("BillToParty").getChild("Party").getChild("NameAddress").getChild("City").getText();

        log.info("orderNo==" + orderNo + "..comment1==" + comment1 + "..comment2==" + comment2 + "..comment3==" + comment3 + "..comment4==" + comment4);

        //        /** set all line items to Accepted status */
        List lineItems = XPath.newInstance("//EDXDATA/DOCDATA/BUSOBJ/Order/OrderDetail/ListOfItemDetail/ItemDetail").selectNodes(doc.getRootElement());
        List<BasketLineData> basketItems = new ArrayList<>();
        List<BasketLineData> finalBasketItems = new ArrayList<>();
        Iterator it = lineItems.iterator();
        String partNo = "";
        String lineNo = "";
        String qty = "";
        String desc = "";
        int i = 0;
        prodDesc = new HashMap();
        BasketLineData basketLine = null;
        while (it.hasNext()) {
            Element currLineItem = (Element) it.next();
            currLineItem.getChild("TempItemCoded").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText("ItemAccepted");
            lineNo = currLineItem.getChild("BaseItemDetail").getChild("LineItemNum").getChild("BuyerLineItemNum").getText();
            partNo = currLineItem.getChild("BaseItemDetail").getChild("ItemIdentifiers").getChild("PartNumbers").getChild("SellerPartNumber").getChild("PartNum").getChild("PartID").getText();
            desc = currLineItem.getChild("BaseItemDetail").getChild("ItemIdentifiers").getChild("ItemDescription").getText();
            qty = currLineItem.getChild("BaseItemDetail").getChild("TotalQuantity").getChild("Quantity").getChild("QuantityValue").getText();
            basketLine = prepareBasketLineData(partNo, Integer.parseInt(qty));
            prodDesc.put(basketLine.getPartID(), desc);
            basketItems.add(basketLine);
            i++;
        }
        log.info("\n=== before removing duplicate items ===" + basketItems.size());
        finalBasketItems = removeDuplicateParts(basketItems);
        log.info("\n=== after removing duplicate items ===" + finalBasketItems.size());
        HashMap orderDetails = new HashMap();
        orderDetails.put("shipTo1", shipTo2);
        orderDetails.put("shipTo2", comment1 + " " + comment2);
        orderDetails.put("shipTo3", comment3);
        orderDetails.put("shipTo4", comment4);
        orderDetails.put("shipviaDesc1", shipTo2);
        orderDetails.put("shipviaDesc2", comment5);
        orderDetails.put("billTo1", billTo1);
        orderDetails.put("billTo2", billTo2);
        orderDetails.put("billTo3", billTo3);
        orderDetails.put("billTo4", billTo4);
        orderDetails.put("orderNo", orderNo);
        orderDetails.put("orderDate", orderDate);
        orderDetails.put("orderNo", orderNo);
        log.info("\n=== Processing Byrnecut order ===" + orderNo);
        getTuneAccessToken(nexusId, finalBasketItems, orderDetails, prodDesc);
    }

    private List<BasketLineData> removeDuplicateParts(List<BasketLineData> basketItems) {
        // Use LinkedHashMap to store unique partID entries
        Map<String, BasketLineData> resultMap = new LinkedHashMap<String, BasketLineData>();
        for (BasketLineData item : basketItems) {
            String key = item.getFranchiseID() + "-" + item.getPartID(); // Unique key

            if (resultMap.containsKey(key)) {
                // Update quantity if exists
                BasketLineData existing = resultMap.get(key);
                resultMap.put(key, new BasketLineData(existing.getFranchiseID(), existing.getPartID(), existing.getQuantity() + item.getQuantity()));
            } else {
                // Add new entry
                resultMap.put(key, item);
            }
        }
        // Convert map values to list
        return new ArrayList<BasketLineData>(resultMap.values());
    }

    /**
     * If the partNo starts with TO then franchiseId is 'TOY'
     *
     * @param partNo
     * @param qty
     * @return
     */
    private BasketLineData prepareBasketLineData(String partNo, int qty) {
        String franchiseId = "";
        String partNumber = "";
        if (partNo.startsWith("TOTO-")) {
            franchiseId = "TOY";
            partNumber = partNo.split("-")[1];
        } else if (partNo.startsWith("TO")) {
            franchiseId = "TOY";
            partNumber = partNo.substring(2);
        } else if (partNo.startsWith("NG")) {
            franchiseId = "NG";
            partNumber = partNo.substring(2);
        }
        return new BasketLineData(franchiseId, partNumber, qty);
    }

    public String findUsername(Map<String, String> map, String input) {
        String lowerInput = input.toLowerCase();
    
        for (String value : map.values()) {
            String regex = "\\b" + value.toLowerCase() + "\\b";
        if (lowerInput.matches(".*" + regex + ".*")) {
            return value;
        }
        }
    return null;
    }
    

    /**
     *
     * @param nexusId
     * @throws IOException
     */
    private void getTuneAccessToken(int nexusId, List<BasketLineData> basketItems, HashMap orderDetails, HashMap prodDesc) throws IOException {
        log.info("\n=== Authentication API Call ===");
        try {
            HashMap nexusParameters = dataResolutionService.getNexusParameters(nexusId);
            // Force TLS 1.2 for HTTPS connections
            Security.addProvider(new BouncyCastleProvider());
            System.setProperty("https.protocols", "TLSv1.2");
            System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
            System.setProperty("https.cipherSuites", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");

            URL url = new URL(new StringBuilder(nexusParameters.get("server_url").toString()).append(AUTH_URL).toString());
            log.info("Authentication Request URL: " + url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String username = findUsername(nexusParameters, orderDetails.get("shipTo1").toString());
            log.info("username: " + username + "...shipTo1=" + orderDetails.get("shipTo1").toString());
            if (username == null) {
                username = nexusParameters.get("username").toString();
            }
            String authString = username + ":" + nexusParameters.get("password").toString();
            String encodedAuth = javax.xml.bind.DatatypeConverter.printBase64Binary(authString.getBytes());
            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
            constructGetApiCall(conn, nexusParameters);

            String result = processTuneResponse(conn);

            // Parse JSON response to extract access token
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(result);
            String accessToken = null;
            if (jsonNode.has("access_token")) {
                accessToken = jsonNode.get("access_token").asText();
            }

            // Extract access token
            if (accessToken != null) {
                callUpdateBasketAPIRequest(accessToken, basketItems, nexusParameters, orderDetails, prodDesc);
            } else {
                throw new Exception("No access token in response");
            }
            log.info("\n=== END Authentication API Call ===");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String generateJson(ApiDataWrapper wrapper) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // Exclude null values
        return objectMapper.writeValueAsString(wrapper);
    }

    private void callUpdateBasketAPIRequest(String authString, List<BasketLineData> basketItems, HashMap nexusParameters, HashMap orderDetails, HashMap prodDesc) throws java.io.IOException {
        List<OrderDetails> orderDetailList = new ArrayList<>();
        List<BasketLineData> newlyPopulatedList = new ArrayList<>();
        try {
            String result = callUpdateBasketAPI(nexusParameters, basketItems, authString);
            log.info("UpdateBasket API Response: " + result);
            List<BasketLineData> errorBasketItems = processUpdateBasketResponse(result);
            log.info("UpdateBasket API Response errorBasketItems.size(): " + errorBasketItems.size());

            if (errorBasketItems.size() != 0) {
//                newleyPopulatedList = processUpdateBasketLists(authString, basketItems, errorBasketItems, nexusParameters);
                newlyPopulatedList = filterBasketLists(basketItems, errorBasketItems);
                if (newlyPopulatedList.size() > 0) {
                    result = callUpdateBasketAPI(nexusParameters, newlyPopulatedList, authString);
                }
            }
            // call Basket to Order
            String tuneOrderNo = callBasketToOrderApi(authString, nexusParameters, orderDetails);
            if (errorBasketItems.size() != 0) {
                sendEmailToKalamunda(errorBasketItems, nexusParameters, orderDetails, tuneOrderNo);
            }
            if (tuneOrderNo != "") {
                orderDetailList = callGetOrderLinesApi(authString, tuneOrderNo, nexusParameters);
                createOrderResponse(authString, tuneOrderNo, orderDetailList, orderDetails, prodDesc, nexusParameters);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BasketLineData> filterBasketLists(List<BasketLineData> originalList, List<BasketLineData> errorList) throws IOException {
        log.info("originalList size=" + originalList.size() + "...errorList=" + errorList.size());
        List<BasketLineData> resultList = new ArrayList<>();
        for (BasketLineData item : originalList) {
            boolean isErrorItem = false;

            // Check if the item exists in errorList
            for (BasketLineData errorItem : errorList) {
                if (item.getPartID().equals(errorItem.getPartID())) {
                    isErrorItem = true;
                    break;
                }
            }

            // If the item is not in errorList, add it to resultList
            if (!isErrorItem) {
                resultList.add(item);
            }
        }
        log.info("resultList size=" + resultList.size());
        return resultList;
    }

    public List<BasketLineData> processUpdateBasketLists(String token, List<BasketLineData> originalList, List<BasketLineData> errorList, HashMap nexusParameters) throws IOException {
        log.info("Setting up correct franchise Ids");
        List<BasketLineData> resultList = new ArrayList<>();
        Map<String, String> correctedCategoryMap = new HashMap<>();
        for (BasketLineData errorLine : errorList) {
            String newFranchiseId = callSearchPartsApi(token, errorLine.getPartID(), nexusParameters);
            if (newFranchiseId != "") {
                errorLine.setFranchiseID(newFranchiseId);
            } else {
                throw new IOException();
            }
            correctedCategoryMap.put(errorLine.getPartID(), errorLine.getFranchiseID());
        }

        for (BasketLineData original : originalList) {
            String newFranchiseID = correctedCategoryMap.containsKey(original.getPartID()) ? correctedCategoryMap.get(original.getPartID()) : original.getFranchiseID();
            resultList.add(new BasketLineData(newFranchiseID, original.getPartID(), original.getQuantity()));
        }
        return resultList;
    }

    private void sendEmailToKalamunda(List<BasketLineData> errorList, HashMap nexusParameters, HashMap orderDetails, String tuneOrderNo) {
        final String username = nexusParameters.get("email_username").toString();
        final String password = nexusParameters.get("email_password").toString();
        final String recipient = nexusParameters.get("recipient").toString();
        String orderNo = orderDetails.get("orderNo").toString();
        // SMTP server configuration
        Properties props = new Properties();
        
        props.put("mail.smtp.host", "smtp.zoho.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.zoho.com");
        

        Authenticator auth = new MyAuthenticator(username, password);
        Session session = Session.getInstance(props, auth);

        try {

            StringBuilder sb = new StringBuilder();
            sb.append("These below parts haven't been procesed with Tune system. Please process this parts maually using the order number ").append(orderNo).append("\n\n");
            sb.append("Tune order number is ").append(tuneOrderNo).append("\n\n");
            for (BasketLineData lineItem : errorList) {
                sb.append(lineItem.getPartID()).append(",");
            }
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("IVBPlus TECH TEAM <vthumma@ivbplus.com>"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Issue with part numbers with Tune system for Tune Order Number " + tuneOrderNo);
            message.setText(sb.toString());

            // Send the email
            Transport.send(message);

            log.info("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String callSearchPartsApi(String authString, String partNo, HashMap nexusParameters) throws IOException {
        String franchiseId = "";
        log.info("\n=== Get Search Parts API Call ===");
        try {
            // Create and configure request
            URL url = new URL(new StringBuilder(nexusParameters.get("server_url").toString()).append(SEARCH_PARTS_URL).append(partNo).toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            constructGetApiCall(conn, nexusParameters);
            conn.setRequestProperty("Authorization", "Bearer " + authString);

            String responseBody = processTuneResponse(conn);

            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(responseBody, Object.class);
            String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            log.info("\nFormatted Response JSON:");
            log.info(prettyJson);
            franchiseId = processSearchPartResponse(responseBody);

            log.info("\n=== End of Search Parts API Call ===");
        } catch (Exception e) {
            System.err.println("\nError during API call:");
            e.printStackTrace();
        }
        return franchiseId;
    }

    public String processSearchPartResponse(String responseBody) throws JsonProcessingException, IOException {
        // Initialize ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // Parse JSON string
        JsonNode rootNode = objectMapper.readTree(responseBody);
        // Extract the "OrderLineData" array
        JsonNode orderLineDataArray = rootNode.path("responseData").path("PartsData");
        // Iterate over order lines
        for (JsonNode partNode : orderLineDataArray) {
            return partNode.path("FranchiseID").asText();
        }
        return null;
    }

    public String getInvoiceTotal(String responseBody) throws JsonProcessingException, IOException {
        // Initialize ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // Parse JSON string
        JsonNode rootNode = objectMapper.readTree(responseBody);
        // Extract the "OrderLineData" array
        JsonNode orderLineDataArray = rootNode.path("responseData").path("OrderHeaderData");
        // Iterate over order lines
        for (JsonNode partNode : orderLineDataArray) {
            return partNode.path("TotalIncTax").asText();
        }
        return null;
    }

    private BasketLineData extractFranchiseAndPartNumber(String body) {
        BasketLineData basketLineData = null;
        // Regex Pattern: Captures the part number and franchise code
        String pattern = "Part\\s+(\\S+)\\s+record not found for franchise\\s+(\\S+)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(body);

        // Extract values if pattern matches
        if (m.find()) {
            String partNumber = m.group(1); // First capture group
            String franchiseCode = m.group(2);// Second capture group
            basketLineData = new BasketLineData(franchiseCode, partNumber, 0);
            log.info("Part Number: " + partNumber);
            log.info("Franchise Code: " + franchiseCode);
        }
        return basketLineData;
    }

    public List<BasketLineData> processUpdateBasketResponse(String responseBody) {
        List<BasketLineData> errorBasketItems = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);
            JsonNode apiMessageData = rootNode.get("apiMessageData");
            int count = apiMessageData.size();
            log.info("count: " + count);
            BasketLineData errorLineData;
            for (JsonNode message : apiMessageData) {
                String type = message.get("type").asText();
                String msg = message.get("msg").asText();
                log.info("type: " + type + "..msg=" + msg);
                if ("ERROR".equalsIgnoreCase(type)) {
                    errorLineData = extractFranchiseAndPartNumber(msg);
                    if (errorLineData != null) {
                        errorBasketItems.add(errorLineData);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorBasketItems;
    }

    private String callBasketToOrderApi(String authString, HashMap nexusParameters, HashMap orderDetails) throws java.io.IOException {
        String orderNumber = "";
        log.info("\n=== Basket To Order API Call ===");
        URL url = new URL(new StringBuilder(nexusParameters.get("server_url").toString()).append(BASKET_TO_ORDER_URL).toString());
        log.info("Basket To Order Request URL: " + url.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        String orderNo = orderDetails.get("orderNo").toString();
        log.info("Basket To Order orderNo: " + orderNo);
        OrderData orderData = new OrderData(orderNo, "");
        List<OrderData> list = new ArrayList<OrderData>();
        list.add(orderData);
        BasketData basketData = new BasketData();
        basketData.setOrderData(list);
        ApiData apiData = new ApiData(basketData);
        ApiDataWrapper wrapper = new ApiDataWrapper(apiData);
        // Convert to JSON
        String jsonPayload = generateJson(wrapper);
        
        constructPostApiCall(conn, authString, nexusParameters);
        
        log.info("Basket To Order request body in JSON: " + jsonPayload);

        // Send POST data
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonPayload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        String result = processTuneResponse(conn);

        log.info("callBasketToOrderApi response=" + result);

        orderNumber = retrieveOrderNoFromBasketToOrderResponse(result);
        log.info("\n=== End of API Call ===" + orderNumber);
        return orderNumber;
    }

    private void callEmptyBasketApi(String authString, HashMap nexusParameters) throws java.io.IOException {
        log.info("\n=== Empty Basket API Call ===");
        URL url = new URL(new StringBuilder(nexusParameters.get("server_url").toString()).append(EMPTY_BASKET_URL).toString());
        log.info("Empty Basket Request URL: " + url.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        constructPostApiCall(conn, authString, nexusParameters);

        String emptyJson = "{}";

        // Send POST data
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = emptyJson.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        String result = processTuneResponse(conn);
        log.info("Empty Basket API response=" + result);
    }

    public static String retrieveOrderNoFromBasketToOrderResponse(String responseBody) {
        String orderNumber = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);
            JsonNode apiMessageData = rootNode.get("apiMessageData");
            for (JsonNode message : apiMessageData) {
                if ("INFORMATION".equalsIgnoreCase(message.get("type").asText())) {
                    orderNumber = message.get("msg").asText();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderNumber;
    }

    private List<OrderDetails> callGetOrderLinesApi(String authString, String orderNo, HashMap nexusParameters) throws java.io.IOException {
        log.info("\n=== Get Order Lines API Call ===");
        List<OrderDetails> orderDetailList = new ArrayList<>();
        try {
            URL url = new URL(new StringBuilder(nexusParameters.get("server_url").toString()).append(GET_ORDER_LINES_URL).append(orderNo).toString());
            log.info("Get Order Lines Request URL: " + url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            constructGetApiCall(conn, nexusParameters);
            conn.setRequestProperty("Authorization", "Bearer " + authString);

            String result = processTuneResponse(conn);
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(result.toString(), Object.class);
            String prettyJson =
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            //log.info("\nFormatted Response JSON:");
            //log.info(prettyJson);
            orderDetailList = processOrderLineItemsResponse(result.toString());
        } catch (Exception e) {
            log.info("Unable to format response as JSON: "
                    + e.getMessage());
        }
        log.info("\n=== End of Get Order Lines API Call ===");
        return orderDetailList;
    }

    private double callGetOrderApi(String authString, String orderNo, HashMap nexusParameters) throws java.io.IOException {
        log.info("\n=== Get Order API Call ===");
        double invTotal = 0.0d;
        try {
            URL url = new URL(new StringBuilder(nexusParameters.get("server_url").toString()).append(GET_ORDER_URL).append(orderNo).toString());
            log.info("Get Order API Request URL: " + url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            constructGetApiCall(conn, nexusParameters);
            conn.setRequestProperty("Authorization", "Bearer " + authString);

            String result = processTuneResponse(conn);
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(result.toString(), Object.class);
            String prettyJson =
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            log.info("\nFormatted Response JSON:");
            log.info(prettyJson);
            String invoiceTotal = getInvoiceTotal(result.toString());
            invTotal = Double.parseDouble(invoiceTotal);
            log.info("\ninvTotal:" + invTotal);
        } catch (Exception e) {
            log.info("Unable to format response as JSON: "
                    + e.getMessage());
        }
        log.info("\n=== End of API Call ===");
        return invTotal;
    }

    public List<OrderDetails> processOrderLineItemsResponse(String responseBody) throws JsonProcessingException, IOException {
        // List to store filtered orders
        List<OrderDetails> allocatedOrders = new ArrayList<>();
        OrderDetails details = null;
        // Initialize ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // Parse JSON string
        JsonNode rootNode = objectMapper.readTree(responseBody);
        // Extract the "OrderLineData" array
        JsonNode orderLineDataArray = rootNode.path("responseData").path("OrderLineData");
        // Iterate over order lines
        for (JsonNode orderNode : orderLineDataArray) {
            if ("40 - Allocated".equals(orderNode.path("OrderLineStatus").asText())) {
                details = new OrderDetails(
                        orderNode.path("OrderNo").asText(),
                        orderNode.path("OrderLine").asInt(),
                        orderNode.path("OrderSubLine").asInt(),
                        orderNode.path("FranchiseID").asText(),
                        orderNode.path("PartID").asText(),
                        orderNode.path("Description").asText(),
                        orderNode.path("OrderLineStatus").asText(),
                        orderNode.path("OrderQty").asDouble(),
                        orderNode.path("BackOrderQty").asDouble(),
                        orderNode.path("AllocatedQty").asDouble(),
                        orderNode.path("UnitPrice").asDouble(),
                        orderNode.path("ExtPrice").asDouble(),
                        5, //OK
                        orderNode.path("ExtPriceIncTax").asDouble());

            } else if ("30 - Backorder".equals(orderNode.path("OrderLineStatus").asText())) {
                details = new OrderDetails(
                        orderNode.path("OrderNo").asText(),
                        orderNode.path("OrderLine").asInt(),
                        orderNode.path("OrderSubLine").asInt(),
                        orderNode.path("FranchiseID").asText(),
                        orderNode.path("PartID").asText(),
                        orderNode.path("Description").asText(),
                        orderNode.path("OrderLineStatus").asText(),
                        orderNode.path("OrderQty").asDouble(),
                        orderNode.path("BackOrderQty").asDouble(),
                        orderNode.path("AllocatedQty").asDouble(),
                        orderNode.path("UnitPrice").asDouble(),
                        orderNode.path("ExtPrice").asDouble(),
                        6, //back order status
                        orderNode.path("ExtPriceIncTax").asDouble());
            }
            // Add to list
            allocatedOrders.add(details);
        }

        // Print the filtered list
//        for (OrderDetails order : allocatedOrders) {
//            log.info(order);
//        }
        return allocatedOrders;
    }

    public void createOrderResponse(String authString, String orderNo, List<OrderDetails> response, HashMap details, HashMap proddesc, HashMap nexusParameters) throws IOException {

        Document document = new Document();
        document.setRootElement(new Element("OrderResponse"));
        double invoiceTotalIncl = callGetOrderApi(authString, orderNo, nexusParameters);
        for (OrderDetails orderDetail : response) {
            orderNo = orderDetail.getOrderNo();
            Element data = new Element("Data");
            data.addContent(new Element("orderNo").setText(details.get("orderNo").toString()));
            data.addContent(new Element("invoiceNo").setText(orderDetail.getOrderNo()));
            data.addContent(new Element("invoiceTotalIncl").setText("" + invoiceTotalIncl));
            data.addContent(new Element("ediCreateDate").setText(getDateTime("dd/MM/yyyy")));
            data.addContent(new Element("ediCreateTime").setText(getDateTime("HHmmss")));
            data.addContent(new Element("linNo").setText("" + orderDetail.getOrderLine()));
            data.addContent(new Element("partId").setText(orderDetail.getPartID()));
            data.addContent(new Element("partDesc").setText("" + proddesc.get(orderDetail.getPartID())));
            data.addContent(new Element("qtyOrdered").setText("" + orderDetail.getOrderQty()));
            data.addContent(new Element("backorderQty").setText("" + orderDetail.getBackOrderQty()));
            data.addContent(new Element("extdPrice").setText("" + orderDetail.getExtPrice()));
            data.addContent(new Element("unitPrice").setText("" + orderDetail.getUnitPrice()));
            data.addContent(new Element("status").setText("" + orderDetail.getLineStatus()));
            data.addContent(new Element("shipTo1").setText("" + details.get("shipTo1")));
            data.addContent(new Element("shipTo2").setText("" + details.get("shipTo2")));
            data.addContent(new Element("shipTo3").setText("" + details.get("shipTo3")));
            data.addContent(new Element("shipTo4").setText("" + details.get("shipTo4")));
            data.addContent(new Element("billTo1").setText("" + details.get("billTo1")));
            data.addContent(new Element("billTo2").setText("" + details.get("billTo2")));
            data.addContent(new Element("billTo3").setText("" + details.get("billTo3")));
            data.addContent(new Element("billTo4").setText("" + details.get("billTo4")));
            data.addContent(new Element("shipviaDesc1").setText("" + details.get("shipviaDesc1")));
            data.addContent(new Element("shipviaDesc2").setText("" + details.get("shipviaDesc2")));
            data.addContent(new Element("orderDate").setText("" + details.get("orderDate")));
            document.getRootElement().addContent(data);
        }
        String filename = orderNo + "_" + getDateTime("yyyyMMddHHmmssS") + ".xml";
        File poafile = new File(nexusParameters.get("poa_dir") + filename);
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileOutputStream(poafile));
    }

    private double calculateInvoiceTotalIncl(List<OrderDetails> response) {
        double totalInvoiceIncl = 0.0;
        for (OrderDetails orderDetail : response) {
            totalInvoiceIncl = totalInvoiceIncl + orderDetail.getExtPriceIncTax();
        }
        return totalInvoiceIncl;
    }

    private String getDateTime(String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void reject_kala(Document doc, String reason, Integer nexusId) throws Exception {
        log.info("Reject processing Kalamunda orders");
//            Comment comment = null;
//            ShipTo shipTo = null;
        String partNo = "";
        String lineNo = "";
        String qty = "";
        String desc = "";
        int i = 0;
        String price = "";
        int status = 7;  //rejected
        Document document = new Document();
        document.setRootElement(new Element("OrderResponse"));
        HashMap nexusConfig = dataResolutionService.getNexusConfig(nexusId);
        String poaDir = nexusConfig.get("poaDirPath").toString();
        //String poaDir = "C:/Nexus_Staging/TradeConnect_2_6_0/temp/Pentana/POA/out/";
        doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("TempItemCoded").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText("NotAccepted");
        if (reason != "") {
            doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("TempOrderResponseHeaderNote").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText(reason);
        }
        /**
         * set all line items to Rejected status
         */
        List lineItems = XPath.newInstance("//EDXDATA/DOCDATA/BUSOBJ/Order/OrderDetail/ListOfItemDetail/ItemDetail").selectNodes(doc.getRootElement());
        Iterator it = lineItems.iterator();

        String orderDate = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderIssueDate").getText();
        String orderNo = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderNumber").getChild("BuyerOrderNumber").getText();
        String comment1 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("ShipToParty").getChild("Party").getChild("NameAddress").getChild("Name2").getText();
        String comment2 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("ShipToParty").getChild("Party").getChild("NameAddress").getChild("Name3").getText();
        String comment3 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("ShipToParty").getChild("Party").getChild("NameAddress").getChild("Street").getText();
        String comment4 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("ShipToParty").getChild("Party").getChild("NameAddress").getChild("City").getText();
        String comment5 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("ShipToParty").getChild("Party").getChild("NameAddress").getChild("City").getText();
        String shipTo2 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("ShipToParty").getChild("Party").getChild("NameAddress").getChild("Name1").getText();
        String billTo1 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("BillToParty").getChild("Party").getChild("NameAddress").getChild("Name1").getText();;
        String billTo2 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("BillToParty").getChild("Party").getChild("NameAddress").getChild("Street").getText();;
        String billTo3 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("BillToParty").getChild("Party").getChild("NameAddress").getChild("Name2").getText();;
        String billTo4 = doc.getRootElement().getChild("DOCDATA").getChild("BUSOBJ").getChild("Order").getChild("OrderHeader").getChild("OrderParty").getChild("BillToParty").getChild("Party").getChild("NameAddress").getChild("City").getText();;

        String filename = orderNo + "_" + getDateTime("yyyyMMddHHmmssS") + ".xml";
        File poafile = new File(poaDir + filename);
        log.info("orderNo==" + orderNo + "..comment1==" + comment1 + "..comment2==" + comment2 + "..comment3==" + comment3 + "..comment4==" + comment4);

        while (it.hasNext()) {
            Element currLineItem = (Element) it.next();
            currLineItem.getChild("TempItemCoded").getChild("EDXBABLE").getChild("ORIGINATOR").getChild("VALUE").setText("ItemRejected");
            lineNo = currLineItem.getChild("BaseItemDetail").getChild("LineItemNum").getChild("BuyerLineItemNum").getText();
            partNo = currLineItem.getChild("BaseItemDetail").getChild("ItemIdentifiers").getChild("PartNumbers").getChild("SellerPartNumber").getChild("PartNum").getChild("PartID").getText();
            desc = currLineItem.getChild("BaseItemDetail").getChild("ItemIdentifiers").getChild("ItemDescription").getText();
            qty = currLineItem.getChild("BaseItemDetail").getChild("TotalQuantity").getChild("Quantity").getChild("QuantityValue").getText();
            price = currLineItem.getChild("PricingDetail").getChild("ListOfPrice").getChild("Price").getChild("UnitPrice").getChild("UnitPriceValue").getText();
            Double partqty = Double.parseDouble(qty);

            Element data = new Element("Data");
            data.addContent(new Element("orderNo").setText(orderNo));
            data.addContent(new Element("customerNo").setText(""));
            data.addContent(new Element("quoteNo").setText(""));
            data.addContent(new Element("orderId").setText(""));
            data.addContent(new Element("invoiceNo").setText(""));
            data.addContent(new Element("salesPersonId").setText(""));
            data.addContent(new Element("invoiceTotalExcl").setText("0.0"));
            data.addContent(new Element("invoiceTotalIncl").setText("0.0"));
            data.addContent(new Element("ediCreateDate").setText(getDateTime("dd/MM/yyyy")));
            data.addContent(new Element("ediCreateTime").setText(getDateTime("HHmmss")));
            data.addContent(new Element("linNo").setText(lineNo));
            data.addContent(new Element("partId").setText(partNo));
            data.addContent(new Element("partDesc").setText(desc));
            data.addContent(new Element("qtyOrdered").setText("" + partqty.intValue()));
            data.addContent(new Element("backorderQty").setText(""));
            data.addContent(new Element("extdPrice").setText("0.0"));
            data.addContent(new Element("unitPrice").setText(price));
            data.addContent(new Element("status").setText("" + status));
            data.addContent(new Element("shipTo1").setText(shipTo2 + "  " + comment1));
            data.addContent(new Element("shipTo2").setText(comment2));
            data.addContent(new Element("shipTo3").setText(comment3));
            data.addContent(new Element("shipTo4").setText(comment4));
            data.addContent(new Element("billTo1").setText(billTo1));
            data.addContent(new Element("billTo2").setText(billTo2));
            data.addContent(new Element("billTo3").setText(billTo3));
            data.addContent(new Element("billTo4").setText(billTo4));
            data.addContent(new Element("shipviaDesc1").setText(shipTo2));
            data.addContent(new Element("shipviaDesc2").setText(comment5));
            data.addContent(new Element("orderDate").setText(orderDate));
            document.getRootElement().addContent(data);
        }
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileOutputStream(poafile));
    }

    /**
     * @return the dataResolutionService
     */
    public DataResolutionService getDataResolutionService() {
        return dataResolutionService;
    }

    /**
     * @param dataResolutionService the dataResolutionService to set
     */
    public void setDataResolutionService(DataResolutionService dataResolutionService) {
        this.dataResolutionService = dataResolutionService;
    }

    private void constructGetApiCall(HttpURLConnection conn, HashMap nexusParameters) throws ProtocolException {
        // Set headers
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty(X_API_KEY, nexusParameters.get("api_value").toString());
        conn.setRequestProperty(X_SENDERID, nexusParameters.get("sender_id_value").toString());
        conn.setRequestProperty(X_RECEIVERID, nexusParameters.get("receiver_id_value").toString());
    }

    private void constructPostApiCall(HttpURLConnection conn, String authString, HashMap nexusParameters) throws ProtocolException {
        conn.setRequestMethod("POST");
        conn.setRequestProperty(X_API_KEY, nexusParameters.get("api_value").toString());
        conn.setRequestProperty(X_SENDERID, nexusParameters.get("sender_id_value").toString());
        conn.setRequestProperty(X_RECEIVERID, nexusParameters.get("receiver_id_value").toString());
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + authString);
        conn.setDoOutput(true);
    }

    private String processTuneResponse(HttpURLConnection conn) throws IOException {
        // Read response
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line.trim());
        }
        reader.close();
        return result.toString();
    }

    private String callUpdateBasketAPI(HashMap nexusParameters, List<BasketLineData> basketItems, String authString) throws MalformedURLException, ProtocolException, IOException {
        log.info("\n=== Calling Empty Basket API Call just to make sure there is no remaining items===");
        callEmptyBasketApi(authString, nexusParameters);
        log.info("\n=== Update Basket API Call ===");
        URL url = new URL(new StringBuilder(nexusParameters.get("server_url").toString()).append(UPDATE_BASKET_URL).toString());
        log.info("Update Basket Request URL: " + url.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        BasketData basketData = new BasketData(basketItems);
        ApiData apiData = new ApiData(basketData);
        ApiDataWrapper wrapper = new ApiDataWrapper(apiData);
        // Convert to JSON
        String jsonPayload = generateJson(wrapper);
        log.info("update basket request body in JSON: " + jsonPayload);
        constructPostApiCall(conn, authString, nexusParameters);
        // Send POST data
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonPayload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        String result = processTuneResponse(conn);
        log.info("\n=== End Update Basket API Call ===");
        return result;
    }
}
