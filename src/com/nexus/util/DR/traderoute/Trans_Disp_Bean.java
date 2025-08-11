package com.nexus.util.DR.traderoute;

import com.nexus.services.DataResolutionService;
import com.nexus.services.DataResolutionServiceImpl;
import java.io.*;
import java.text.NumberFormat;
import org.w3c.dom.*;
import com.nexus.util.DR.commonsource.*;
import com.nexus.util.DR.misc.*;
import java.util.*;
import com.nexus.util.DR.edxbable.*;
import com.nexus.util.DR.accessdbcomm.*;
//import server.exportprint.*;
import org.apache.xerces.dom.DocumentImpl;
import java.util.logging.*;
import java.util.zip.Inflater;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.lang.StringUtils;
import org.jdom.JDOMException;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;
import org.jdom.output.DOMOutputter;
import org.jdom.transform.JDOMSource;

/**
 * Class to deal with interaction of TradeRoute Trans_Disp_FS.jsp file
 */
public class Trans_Disp_Bean {

    /******************************** VARIABLES ***********************************/
    /************ EXTERNAL VARIABLES **************/
    /** variable to hold xmlFunctions object */
    private XMLFunctions xmlInterface = null;
    /** variable to hold XSLTFunctions object */
    private XSLTFunctions xslInterface = null;
    /** variable to hold bableEngine object */
    private BableEngine bableEngine = null;
    /** Directory list read in from config file */
    private ConfigValuesList dirList = null;
    /** connection to the error data base */
    //private DatabaseInterface dbConn = null;
    //private static DbCommunicatorImpl dbCommunicatorImpl = null;

    private DataResolutionService dataResolutionService=new DataResolutionServiceImpl();
    /** User list containing registered users */
//    private TRSUserList tul = null;
    /** TimeStamp variable */
    private TimeStamp tStamp = new TimeStamp();
    private static Logger logger = null;
    /************** CLASSES INTERNAL VARIABLES *************/
    /** hold the config file location */
    private String configFileLocation = "";
    /** Variables to hold the input path for the application */
    private String TRADEROUTE_QUEUE_DIR = "";
    /** used in recurse DOM */
    private int objectId = 1;
    /** number of times to try copying a file to the processing dir */
    private int numberTimesToCopy = 0;
    /** Variable to hold the current transaction */
    private Document currentTransactionDOM = null;
    /** Variables to hold infromation about the current Transaction. */
    private String fileName = "";
    private String lastFileInConversation = "";
    /** holds the value from the front end drop down list, ORDER, SHIP, INVOICE ... */
    private String transType = "";
    private String userName = "";
    private String userType = "";
    /** holds the value in the DOCDATA\\TRADEROUTEVALUES\\TRANSACTIONTYPE node */
    private String documentType = "";
    /** Variable to hold any error information which occurs while processing this transaction */
    private String errorText = "";
    private String closeForm = "";
    /**************** SCHEMA/VALIDATION VARIABLES *************/
    /** Variables to hold the various schema's in memory */
    private Document poSchemaDOM = null;
    private Document coSchemaDOM = null;
    private Document expSchemaDOM = null;
    private Document responseSchemaDOM = null;
    private SAXBuilder _xmlBuilder = null;
    /** vector to hold a list of created mini schemas */
    private Map smallSchemaList = new HashMap();
    /** holds the names of the types needed by the current COMPLEXTYPE node */
    private Vector complexTypeRefList = null;
    /** variable to hold the location of the schema for this transaction */
    private String schemaTemplateLoc = "";
    /** Vector to hold valuesObject's to pass back to JSP page */
    private Vector objectsList = new Vector();
    /** Variable to hold the tree structure of the DOM, passed back to the JSP page */
    private StringBuffer output = null;
    /************ MISC VARIABLES *****************/
    /** boolean to hold continuation status */
    private boolean keepGoing;
    /** boolean to flag a validation error in the previous batch of values */
    boolean errorInLastBatch = false;
    private int counter = 0;
    /** SQL query to find the latest added ParentKey in the Parent_Transactions table */
    private final static String get_Latest_ParentKey_SQL = "SELECT * FROM Parent_Transactions WHERE ParentKey = (SELECT MAX(ParentKey) FROM Parent_Transactions)";
    /** SQL query to find the latest added ParentKey in the Parent_Transactions table */
    private final static String get_Latest_TransStat_SQL = "SELECT * FROM Transaction_Status WHERE StatusID = (SELECT MAX(StatusID) FROM Transaction_Status)";
    private boolean allreadyInitialised = false;
    private XCBLOrderProcessor _xcblOrderProcessor;
    private XCBLChangeOrderProcessor _xcblChangeOrderProcessor;
    int tempObjectId;
    private String treeOutput;
    private String tempDir;
    /************************************** FRAME SET GENERAL FUNCTIONS ***********************************/
    /**
     * FUNCTION [initialise()]:
     *  - do all setup work required for class.
     */
    public void initialise() {
        /** set the continuation status boolean */
        keepGoing = true;
        if (!allreadyInitialised) {
            logger = Logger.getLogger("traderoute.Trans_Disp_Bean");
            logger.info("- initialise()...in");
            /** reset a couple of classes, the XML parser is not reentrant, so needs to be reinitialised */
            xmlInterface = null;
            bableEngine = null;
            xmlInterface = new XMLFunctions();
            xslInterface = new XSLTFunctions();
            bableEngine = new BableEngine();
            dirList = ConfigValuesList.getInstance("Trans_Disp_Bean", "TRADEROUTE");
            /** set the config list for the bableEngine class */
            xslInterface.setDirectoryList(dirList);
            bableEngine.setDirectoryList(dirList);
            /** load all the required schemas */
            String poSchemaLoc = dirList.getConfigValue("PROP_PO_SCHEMA_LOCATION");
            String coSchemaLoc = dirList.getConfigValue("PROP_CO_SCHEMA_LOCATION");
            String expSchemaLoc = dirList.getConfigValue("PROP_EXP_SCHEMA_LOCATION");
            String responseSchemaLoc = dirList.getConfigValue("PROP_ORDER_RESPONSE_SCHEMA_LOCATION");
            schemaTemplateLoc = dirList.getConfigValue("PROP_SCHEMA_TEMPLATE");
             tempDir=dirList.getConfigValue("PROP_TEMP_DIR");
            /** read in the schema */
            try {
                poSchemaDOM = xmlInterface.readInXMLFile(poSchemaLoc);
                coSchemaDOM = xmlInterface.readInXMLFile(coSchemaLoc);
                expSchemaDOM = xmlInterface.readInXMLFile(expSchemaLoc);
                responseSchemaDOM = xmlInterface.readInXMLFile(responseSchemaLoc);
            } catch (Exception e) {
                this.setErrorText(": Error reading in Schemas: " + e.getMessage());
                e.printStackTrace();
                keepGoing = false;
            }
        }
        allreadyInitialised = true;
    }

    /**
     * [Function] getTransactionType
     * - return the transaction type of the currentTransactionDOM
     */
    private String getTransactionType() {
        String ret = "";
        if (getCurrentTransactionDOM() != null) {
            Node root = getCurrentTransactionDOM().getDocumentElement();
            ret = xmlInterface.getNodeValue(root, "DOCDATA\\TRADEROUTEVALUES\\TRANSACTIONTYPE");
        }
        return ret;
    }

    /**
     * [Function] getTransactionType
     * - return the transaction type of the currentTransactionDOM
     */
    public String getTransactionOriginator() {
        String ret = "";
        if (getCurrentTransactionDOM() != null) {
            Node root = getCurrentTransactionDOM().getDocumentElement();
            ret = xmlInterface.getNodeValue(root, "DOCDATA\\TRADEROUTEVALUES\\DOCCREATORTPID");
        }
        return ret;
    }

    /** load the minischemas from the right directory for this transaction */
    private void loadMiniSchemas() {
        String schemaDir = dirList.getConfigValue("PROP_SMALL_SCHEMA_DIR");
        schemaDir = schemaDir + "\\" + documentType;
        File schemaDirectory = new File(schemaDir);
        if (schemaDirectory.isDirectory()) {
            File[] files = schemaDirectory.listFiles();
            for (int i = 0; i < files.length; i++) {
                File currFile = files[i];
                /** read the small schema into a DOM */
                try {
                    Document currSchema = xmlInterface.readInXMLFile(currFile.getAbsolutePath());
                    /** add the filename to the schema list */
                    System.out.println("Added mini schema to list: "+currFile.getName());
                    smallSchemaList.put(currFile.getName(), currSchema);
                } catch (Exception e) {
                    logger.info("-loadMiniSchemas- Exception reading schema: " + e.getMessage());
                }
            }
        }
    }


    /**
     * [Function] ProcessTransaction
     * -  Process the currently selected transaction from the transaction centre list.
     */
    public String processTransaction(String transationId) {
        /** read in the selected XML file */
        if (keepGoing) {
            try {
                getXMLFile(transationId);
                //logger.info("[Trans_Disp_Bean: "+this.getLastFileInConversation()+"]: Read in the Transaction: ");
                if (getCurrentTransactionDOM() == null) {
                    keepGoing = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.setErrorText("[Trans_Disp_Bean: " + this.getLastFileInConversation() + "] Error: Could not read in the Transaction: " + fileName);
                keepGoing = false;
            }
        }


        /**
         * The last Transaction in the current conversation list is now loaded into
         * the currentTransactionDOM object.
         */
        if (keepGoing) {
            /** Is this transaction resolved allready ? */
            Node root = (Node) getCurrentTransactionDOM().getDocumentElement();
            //String resState = xmlInterface.getNodeValue(root, "DOCDATA\\TRADEROUTEVALUES\\TRADEFORMSTATUS");
            /** add this user's name to the user name list within the file */
            String currUserList = xmlInterface.getNodeValue(root, "DOCDATA\\TRADEROUTEVALUES\\USERLIST");
            if (currUserList.equalsIgnoreCase("")) {
                currUserList = userName;
            } else {
                currUserList = currUserList.trim() + "," + userName;
            }
            xmlInterface.setElementValue(root, "DOCDATA\\TRADEROUTEVALUES\\USERLIST", currUserList);
            /** save the dom */
        //    saveCurrentTransaction();
            /** get the document type of this transaction */
           // documentType = xmlInterface.getNodeValue(root, "DOCDATA\\TRADEROUTEVALUES\\TRANSACTIONTYPE");
            System.out.println("Imp testing...."+documentType);
            //documentType = "PurchaseOrder";
            /** load the schemas into the list */
            loadMiniSchemas();
            System.out.println("after loadMiniSchemas...."+keepGoing);
        }

        /** create the xml tree to put into the JSP page, store it in the output buffer */
        if (keepGoing) {
            setTreeOutput(generateXMLTree());
        }

//        return lastFileInConversation;
        return getTreeOutput();
    }

    /**
     * FUNCTION [saveValueToSchema()]:
     *  - save a value into the specified edxid's schema
     */
    public void saveValueToSchema(String edxId, String value) {
        //logger.info("- Saved value into schema...");

        //logger.info("edxId: "+edxId);
        //logger.info("value: "+value);

        /** get the node with this edxID */
        NodeList allEDXBABLES = getCurrentTransactionDOM().getElementsByTagName("EDXBABLE");
        for (int i = 0; i < allEDXBABLES.getLength(); i++) {
            Node currEDXBABLE = allEDXBABLES.item(i);
            /** get the edxid value */
            String currEDXID = xmlInterface.getNodeValue(currEDXBABLE, "EDXID");
            currEDXID = currEDXID.trim();
            /** if this  is the right node */
            if (currEDXID.equalsIgnoreCase(edxId)) {
                /** does it have a specific schema? */
                String schemaToUse = xmlInterface.getNodeValue(currEDXBABLE, "DESTINATION\\SCHEMATOUSE");
                if (!schemaToUse.equalsIgnoreCase("")) {
                    /** use the specific schema */
                    try {
                        File schema = new File(schemaToUse);
                        if (schema.isFile()) {
                            Document userSchema = xmlInterface.readInXMLFile(schemaToUse);
                            /** search all the enumerations in the full schema for this node name */
                            NodeList schemaEnums = userSchema.getElementsByTagName("xsd:enumeration");
                            /** get the parent node of the enumerations */
                            Node enumParent = schemaEnums.item(0).getParentNode();
                            Element newEnum = userSchema.createElement("xsd:enumeration");
                            newEnum.setAttribute("value", value);
                            enumParent.appendChild(newEnum);
                            /** write out the schema over itself */
                            xmlInterface.writeDomToFile(userSchema, schema.getAbsolutePath());
                        }
                    } catch (Exception e) {
                        logger.info("- Exception Reading Specific Schema: " + e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * FUNCTION [getEnumerationList()]:
     *  - returns a Vector containing all the enumeration values for this edxID element.
     */
    public Vector getEnumerationList(String edxId) {
        Vector retList = new Vector();
        /** get the node with this edxID */
        NodeList allEDXBABLES = getCurrentTransactionDOM().getElementsByTagName("EDXBABLE");
        for (int i = 0; i < allEDXBABLES.getLength(); i++) {
            Node currEDXBABLE = allEDXBABLES.item(i);
            /** get the edxid value */
            String currEDXID = xmlInterface.getNodeValue(currEDXBABLE, "EDXID");
            currEDXID = currEDXID.trim();
            /** if this  is the right node */
            if (currEDXID.equalsIgnoreCase(edxId)) {
                /** found it, get the parent name */
                String nodeName = currEDXBABLE.getParentNode().getNodeName();
                /** does it have a specific schema? */
                String schemaToUse = xmlInterface.getNodeValue(currEDXBABLE, "DESTINATION\\SCHEMATOUSE");
                Document fullSchema = null;
                if (schemaToUse.equalsIgnoreCase("")) {
                    /** use the big schema */
                    /** is it a PO or CO */
                    if (documentType.equalsIgnoreCase("PurchaseOrder")) {
                        fullSchema = poSchemaDOM;
                    } else if (documentType.equalsIgnoreCase("ExportDocument")) {
                        fullSchema = expSchemaDOM;
                    } else {
                        fullSchema = coSchemaDOM;
                    }
                } else {
                    /** use the specific schema */
                    try {
                        File schema = new File(schemaToUse);
                        if (schema.isFile()) {
                            fullSchema = xmlInterface.readInXMLFile(schemaToUse);
                            /** search all the enumerations in the full schema for this node name */
                            NodeList schemaEnums = fullSchema.getElementsByTagName("xsd:enumeration");
                            for (int j = 0; j < schemaEnums.getLength(); j++) {
                                Node currEnum = schemaEnums.item(j);
                                String currVal = xmlInterface.getAttributeValue(currEnum, "value");
                                /** add it to the vector */
                                retList.add(currVal);
                            }
                        } else {
                            /** small schema didn't exist */
                            return retList;
                        }
                    } catch (Exception e) {
                        logger.info("- Exception Reading Specific Schema: " + e.getMessage());
                    }
                }

                /** search all the elements in the full schema for this node name */
                NodeList schemaElements = fullSchema.getElementsByTagName("xsd:element");
                for (int j = 0; j < schemaElements.getLength(); j++) {
                    Node currNode = schemaElements.item(j);
                    /** get the name attribute of this Node */
                    String nameAttribVal = xmlInterface.getAttributeValue(currNode, "name");
                    if (nameAttribVal.equalsIgnoreCase(nodeName)) {
                        /** get it's 'type' attribute */
                        String typeAttribVal = xmlInterface.getAttributeValue(currNode, "type");
                        /** search all the elements in the full schema for this simpleType */
                        NodeList schemaSimpleTypes = fullSchema.getElementsByTagName("xsd:simpleType");
                        for (int k = 0; k < schemaSimpleTypes.getLength(); k++) {
                            Node currentNode = schemaSimpleTypes.item(k);
                            /** get the name attribute of this Node */
                            String simpleTypeName = xmlInterface.getAttributeValue(currentNode, "name");
                            if (simpleTypeName.equalsIgnoreCase(typeAttribVal)) {
                                /** Found the right simpleType node in the schema */
                                /** get all the values into a vector */
                                Document xmlDoc = new DocumentImpl();
                                Node root = xmlDoc.importNode(currentNode, true);
                                xmlDoc.appendChild(root);
                                /** get all the enumeration types */
                                NodeList enums = xmlDoc.getElementsByTagName("xsd:enumeration");
                                for (int h = 0; h < enums.getLength(); h++) {
                                    Node currEnum = enums.item(h);
                                    String currVal = xmlInterface.getAttributeValue(currEnum, "value");
                                    /** add it to the vector */
                                    retList.add(currVal);
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return retList;
    }

    /**
     * FUNCTION [moveFilesToProcessingDir()]:
     *  - move all the files in the passed in conversation list into the processing dir.
     */
    public void moveFilesToProcessingDir(Vector convList) {

        for (int i = 0; i < convList.size(); i++) {
            QueryResult currData = (QueryResult) convList.elementAt(i);
            /** find the current transaction in the input queue */
            String currPath = getTransactionPathFromQueue("PROP_" + transType + "_DIR", currData.getDOCKEY());
            File currFile = new File(currPath);
            if (currFile.exists()) {
                String destDir = dirList.getConfigValue("PROP_PROCESS_DIR");
                try {
                    String destPath = destDir + "\\" + currFile.getName();
                    xmlInterface.copyFile(currPath, destPath);
                    File newFile = new File(destPath);
                    if (newFile.exists()) {
                        logger.info("- moveFilesToProcessingDir - Moved File To Processing Dir: " + currFile.getName());
                        currFile.delete();
                    } else {
                        logger.info("- moveFilesToProcessingDir - File didn't copy: " + currFile.getName());
                        numberTimesToCopy++;
                        if (numberTimesToCopy < 5) {
                            logger.info("- moveFilesToProcessingDir - Trying copy again: " + currFile.getName());
                            moveFilesToProcessingDir(convList);
                        }
                    }
                } catch (Exception e) {
                    logger.info("- moveFilesToProcessingDir - Exception copying file to processing dir: " + currFile.getName());
                    logger.info(": Error: " + e.getMessage());
                }
            }
        }
    }

    /**
     * FUNCTION [getTransactionFromInputQueue()]:
     *  - return a Path of the transaction with the current DOCKEY.
     */
    public String getTransactionPathFromQueue(String queueName, String transDocKey) {
        String retPath = "";
        /** create the right input dir path */
        String inputDir = dirList.getConfigValue(queueName);
        File inputDirectory = new File(inputDir);
        if (inputDirectory.isDirectory()) {
            File[] fileList = inputDirectory.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                File currFile = fileList[i];
                int pos = currFile.getName().indexOf(transDocKey);
                /** found the right file */
                if (pos != -1) {
                    retPath = currFile.getAbsolutePath();
                }
            }
        }
        return retPath;
    }

    /**
     * FUNCTION [getXMLFile()]:
     *  - read the correct XML file into a Document object.
     */
    public void getXMLFile() throws Exception {
        try {
            //System.out.println("Looking for prop: "+"PROP_"+transType+"_DIR");
            /** create the right input dir path */
            TRADEROUTE_QUEUE_DIR = dirList.getConfigValue("PROP_" + transType + "_DIR");
            if (lastFileInConversation.equals("")) {
                lastFileInConversation = fileName;
            }
            /** Check for files in the input directory */
            String filePath = TRADEROUTE_QUEUE_DIR + "\\" + lastFileInConversation;
            System.out.println("filePath: " + filePath);
            File currFile = new File(filePath);
            if (currFile.isFile()) {
                /** Read the current file into a DOM */
                setCurrentTransactionDOM(null);
                setCurrentTransactionDOM(xmlInterface.readInXMLFile(currFile.getAbsolutePath()));
            } else {
                //logger.info("The Transaction : "+currFile.getAbsolutePath()+", does not exist.");
                setErrorText("The Transaction : " + currFile.getAbsolutePath() + ", does not exist.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void getXMLFile(String transId) throws Exception {
        try {
            setCurrentTransactionDOM(null);
            //currentTransactionDOM = this.getDocument(transId);
            setCurrentTransactionDOM(this.openTransaction(transId));
        } catch (Exception e) {
            throw e;
        }
    }

    public Document getDocument(String transId) throws Exception {
        //dbCommunicatorImpl = DbCommunicatorImpl.getInstance("C:\\TradeRoute\\config_files\\edxconfig.xml");
        //byte[] compressedData = dbCommunicatorImpl.getTransactionDocument(transId);
        System.out.println("getDocument..transId="+transId);
        byte[] compressedData = dataResolutionService.getTransactionDocument(transId);
         System.out.println("compressedData..length="+compressedData.length);
        byte[] docData = this.decompressByteArray(compressedData);
        Document ret = xmlInterface.readFile(docData);
        return ret;
    }

    public byte[] decompressByteArray(byte[] data) throws Exception {
        Inflater decompressor = new Inflater();
        System.out.println("data length..."+data.length);
        decompressor.setInput(data);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        byte[] buf = new byte[1024];
        while (!decompressor.finished()) {
            int count = decompressor.inflate(buf);
            bos.write(buf, 0, count);
        }
        try {
            bos.close();
        } catch (IOException io) {
        }
        return bos.toByteArray();
    }

    /**
     * FUNCTION [generateXMLTree()]:
     *  - generate a Javascript model to use in the front end, put it into the output buffer.
     */
    public String generateXMLTree() {
        try {
            System.out.println("generateXMLTree.."+lastFileInConversation);
            objectId = 1;
            /** setup the required output buffer */
            output = new StringBuffer();
            if (getCurrentTransactionDOM() == null) {
                System.out.println("currentTransactionDOM is null");
                getXMLFile(this.lastFileInConversation);
            }
            /** Get the BUSOBJ  node */
            NodeList nl = getCurrentTransactionDOM().getElementsByTagName("BUSOBJ");
            Node BUSOBJ = nl.item(0);
            Node root = null;
            /** Get the (root) node of the actual transaction */
            NodeList rootChildren = BUSOBJ.getChildNodes();
            System.out.println("rootChildren.getLength = " + rootChildren.getLength());
            for (int i = 0; i < rootChildren.getLength(); i++) {
                Node currNode = rootChildren.item(i);
                if (currNode.getNodeType() == Node.ELEMENT_NODE) {
                    root = currNode;
                    break;
                }
            }
            /** Get the root's english name, or use node name if there is none */
            String rootEngName = xmlInterface.getNodeValue(root, "EDXBABLE\\DESTINATION\\ENGLISHNAME");
            System.out.println("rootEngName val.."+rootEngName);
            if (rootEngName.equals("")) {
                rootEngName = root.getNodeName();
            }
            String bgcol = xmlInterface.getNodeValue(root, "EDXBABLE\\DESTINATION\\DISPLAYDETAILS\\BGCOLOUR");
            System.out.println("bgcol val.."+bgcol);
            /************ This is the string that is sent back from the OAG_Bean.java class *****************
             * var e1 = createObject(1, -1, "Purchase Order Root","PROCESS_PO_007","TestPurchaseOrder1.xml");
             * var e2 = createObject(2, "25", "Purchase Order Lines", "PROCESS_PO_007", "TestPurchaseOrder1.xml");
             * append(e1 , e2);
             * var e3 = createObject(3, "99", "PO Line", "PROCESS_PO_007", "TestPurchaseOrder1.xml");
             * append(e2 , e3);
             * var e8 = createObject(8, "75", "PO Line", "PROCESS_PO_007", "TestPurchaseOrder1.xml");
             * append(e2 , e8);
             */
            System.out.println("rootEngName val.."+rootEngName+"..transType..."+transType+"...lastFileInConversation...."+lastFileInConversation+"..bgcol...."+bgcol);
            output.append("var e1 = createObject(1, -1, \"" + rootEngName + "\",\"" + transType + "\",\"" + lastFileInConversation + "\",\"" + bgcol + "\");\n");
            /** call the recursive function to do all the children */
            if (rootChildren != null) {
                recurseDOM(root);
            }
            System.out.println("\n*** Generated XML TREE ****\n");
            System.out.println(output.toString());
//            System.out.println("output = " + output);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return output.toString();
    }

    /**
     * FUNCTION [recurseDOM()]:
     *  - Recurse the passed in NodeList, printing to System.out if debug = true.
     */
    public void recurseDOM(Node parentNode) {
       // System.out.println(" recurseDOM");
        int currentObjectId = objectId;
       // System.out.println("currentObjectId.." + currentObjectId + "..objectId.." + objectId);
        Node childNode = parentNode.getFirstChild();
        while (childNode != null) {
           // System.out.println(" in while currentObjectId.." + currentObjectId + "..objectId.." + objectId);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                String nodeName = childNode.getNodeName();
               // System.out.println(" nodeName = " + nodeName);
                if (nodeName != null && (nodeName.equalsIgnoreCase("EDXBABLE"))) {
                  //  System.out.println(" EDXBABLE nodeName = " + xmlInterface.getNodeValue(childNode, "DESTINATION\\ENGLISHNAME"));
                } else {
                    getStringValue(childNode, currentObjectId, objectId);
                    if (childNode.hasChildNodes()) {
                       // System.out.println(" calling recurseDOM(childNode);");
                        recurseDOM(childNode);
                    }
                }
            }
          //  System.out.println(" childNode.getNodeName()" + childNode.getNodeName());
            if (childNode.getNextSibling() != null) {
               // System.out.println(" childNode.getNextSibling()" + childNode.getNextSibling().getNodeName());
           }
            childNode = childNode.getNextSibling();
        }
    }

    /**
     * FUNCTION [getStringValue()]:
     *  - Append the JSP string value onto the string buffer.
     */
    public void getStringValue(Node currNode, int currentObjectId, int objId) {
       // System.out.println("currentObjectId.." + currentObjectId + "..objectId.." + objectId);
        NodeList nl = currNode.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node currKid = nl.item(i);
            if (currKid.getNodeName().equalsIgnoreCase("EDXBABLE")) {
                /** do we show this node ? */
                //System.out.println("currKid.getNodeName.." + currKid.getNodeName() + "..currentObjectId.." + currentObjectId + "..objectId.." + objectId);
                String showNode = xmlInterface.getNodeValue(currKid, "DESTINATION\\DISPLAYDETAILS\\DISPLAYINTREE");
                if (showNode.equalsIgnoreCase("true")) {
                    objectId++;
                    /** get the required values */
                    String edxID = xmlInterface.getNodeValue(currKid, "EDXID");
                    String englishName = xmlInterface.getNodeValue(currKid, "DESTINATION\\ENGLISHNAME");
                    String Xpath = xmlInterface.getNodeValue(currKid, "ORIGINATOR\\NAME");
                    String bgcolor=xmlInterface.getNodeValue(currKid, "DESTINATION\\DISPLAYDETAILS\\BGCOLOUR");
                   // System.out.println("edxID...." + edxID + "...englishName.." + englishName + "...Showing Xpath in Tree: " + Xpath+"...bgcolor..."+bgcolor);
                    if(Xpath.equalsIgnoreCase("ChangeOrder/ChangeOrderDetail"))
                        tempObjectId=objectId;
                    else if(Xpath.equalsIgnoreCase("ChangeOrder/ChangeOrderDetail/ListOfChangeOrderItemDetail/ChangeOrderItemDetail/ItemDetailChanges/ItemDetail"))
                    {
                        if(currentObjectId!=tempObjectId)
                            currentObjectId=tempObjectId;   //Item Line should added to Order Detail(3)
                    }
                    if (englishName.equalsIgnoreCase("")) {
                        englishName = currKid.getParentNode().getNodeName();
                    }

                    /** create the node in javascript */
                    output.append(
                            "var e" + objectId + " = createObject(" + objectId + ", " + (edxID == null ? "null" : "\"" + edxID + "\"") + ", " + (englishName == null ? "null" : "\"" + englishName + "\"") //+ ", "
                            //+ (Xpath == null ? "null" : "\"" + Xpath + "\"")
                           // + ", \"" + transType + "\", \"" + lastFileInConversation + "\");\n");
                             + ", \"" + transType + "\", \"" + lastFileInConversation + "\", \"" + bgcolor + "\");\n");

                    /** append the  node to the parent in javascript */
                   // System.out.println("currentObjectId.." + currentObjectId + "..objectId.." + objectId);
                    output.append("append(e" + currentObjectId + " , " + "e" + objectId + ");\n");
                   // System.out.println("output...." + output);
                }

            }
        }
    }

    /*************************** FRONT END INTERACTION FUNCTIONS **************************/
    /**
     * FUNCTION [saveUpdateObjects()]:
     *  - save a list of update objects into the currently loaded DOM.
     */
    public synchronized void saveUpdateObjects(org.w3c.dom.Document document,String doctype,Enumeration pageBody,String fName,Vector updateList,String id,String reason,String supNexusId) {
        boolean saveClicked = false;
       boolean flag1=false;
       boolean redcolor=false;
       String bgcol="";
       String orig_name_qty=null;
       String orig_name_unitprice =null;
       
       float quantity=0.00F;
       float unitprice=0.00F;
       String qty=null;
       String price=null;
       boolean qtyflag=false;
       boolean priceflag=false;
       setCurrentTransactionDOM(document);
       documentType=doctype;
       System.out.println("documenttype..."+documentType+"...objectsList.size()..."+objectsList.size());
       this.initialise();
       this.lastFileInConversation=fName;
       if(pageBody!=null){
        while(pageBody.hasMoreElements())
        {
            objectsList.add(pageBody.nextElement());
        }
       }
       if (getCurrentTransactionDOM() != null) {
            //System.out.println("[Trans_Disp_Bean: "+this.getFileName()+"] Saving Update Objects...");
            errorInLastBatch = false;
            /** get an enumeration of the current objects */
            Enumeration updateObjs = updateList.elements();
            /** create a temporary variable */
            String buttonClicked = "";
            //System.out.println(".getCurrentTransactionDOM..getNodeValue.."+getCurrentTransactionDOM().getNodeValue()+"..localname.."+getCurrentTransactionDOM().getLocalName()+"..nodename.."+getCurrentTransactionDOM().getNodeName());
            /** Get all the EDXID values in the current trans DOM */
            NodeList allEDXBABLES = getCurrentTransactionDOM().getElementsByTagName("EDXBABLE");
            /** while there are more update objects */
            //System.out.println("update list size: "+updateList.size());
            while (updateObjs.hasMoreElements()) {
                /** get the current update object */
                UpdateObject currObj = (UpdateObject) updateObjs.nextElement();
                //currObj.print();
                /** get the name of the current update object */
                String currObjName = currObj.getParentName();

                System.out.println("Current Object Name: "+currObjName);
                System.out.println("currobj.getbuttonclicked..."+currObj.getButtonClicked()+".before..buttonClicked.."+flag1);
                /** if this is the "closeForm" object */
                if (currObj.getButtonClicked().equalsIgnoreCase("checkvalues")){
                        flag1=true;
                        saveClicked = true;
                    }
                /** if this is the "buttonClicked" object */
                if (currObj.getParentName().equalsIgnoreCase("buttonClicked")) {
                    buttonClicked = currObj.getNewValue();
                    buttonClicked = buttonClicked.trim();
                    System.out.println("buttonClicked: "+buttonClicked);
                    if (buttonClicked.equalsIgnoreCase("save") || buttonClicked.equalsIgnoreCase("accept") || buttonClicked.equalsIgnoreCase("reject")|| buttonClicked.equalsIgnoreCase("cancel")) {
                        System.out.println("save clicked..");
                        saveClicked = true;
                    }
                    /** if it's print, then print */
                    if (buttonClicked.equalsIgnoreCase("print")) {
                        /** cancel the document */
                        printCurrentTransaction();
                       // errorInLastBatch = true;
                    }
                    /** if it's cancel, then don't save changes */
                    if (buttonClicked.equalsIgnoreCase("cancel")) {
                        /** cancel the document */
                        cancelCurrentTransaction();
                        break;
                    }
                    if (buttonClicked.equalsIgnoreCase("comment")) {
                        /** reject the document */
                        try {
                            addComment(reason);
                            updateTransactionStatus("Open");
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
             }

                /** filter out the fileName && id && buttonClicked && transType objects */
                if ((!currObjName.equalsIgnoreCase("fileName")) &&
                        (!currObjName.equalsIgnoreCase("id")) &&
                        (!currObjName.equalsIgnoreCase("buttonClicked")) &&
                        (!currObjName.equalsIgnoreCase("transType")) ) {

                    /** VALIDATE THE CURRENT OBJECT */
                    boolean errorInCurrObject = false;

                    /** is it a PO or CO or EXP */
                   System.out.println("..flag1..val.."+flag1);
                    if(flag1){
                    if (documentType.equalsIgnoreCase("PurchaseOrder")) {
                        /** validate the current Object */
                        System.out.println("Document Type is PurchaseOrder");
                        if (!validateUpdateObject(currObj, poSchemaDOM)) {
                            errorInLastBatch = true;
                            errorInCurrObject = true;
                            /** get the error */
                            //logger.info("Error Returned From Parser: "+xmlInterface.getLastValidationError());
                            currObj.setValidationError(xmlInterface.getLastValidationError());
                        }
                       // flag=false;
                    } else if (documentType.equalsIgnoreCase("ExportDocument")) {
                        System.out.println("Document Type is Export Document");
                        /** export document */
                        /** validate the current Object */
                        if (!validateUpdateObject(currObj, expSchemaDOM)) {
                            errorInLastBatch = true;
                            errorInCurrObject = true;
                            /** get the error */
                            logger.info("Error Returned From Parser: " + xmlInterface.getLastValidationError());
                            currObj.setValidationError(xmlInterface.getLastValidationError());
                        }
                    //System.out.println("Object Validated Ok");
                    } else {
                        /** change order */
                        /** validate the current Object */
                        System.out.println("Document Type is ChangeOrder");
                        if (!validateUpdateObject(currObj, coSchemaDOM)) {
                            errorInLastBatch = true;
                            errorInCurrObject = true;
                            /** get the error */
                            //logger.info("Error Returned From Parser: "+xmlInterface.getLastValidationError());
                            currObj.setValidationError(xmlInterface.getLastValidationError());
                        }
                    }
                    }

                    if (errorInCurrObject) {

                        /** SET THE VALUE IN THE OBJECTS LIST TO THE TYPED IN VALUE SO IT STAYS ON SCREEN */
                        System.out.println("1objectsList.size="+objectsList.size());
                        Enumeration objsList = objectsList.elements();

                        String currEDXID = currObj.getParentName();
                        System.out.println("errorInCurrObject curredxid..." + currEDXID);
                        EDXBable uObj = null;

                        while (objsList.hasMoreElements()) {
                            uObj = (EDXBable) objsList.nextElement();

                            String currBableEDXID = uObj.getEDXID();
                            String currBableEDXNAME = uObj.ORIGINATOR.getNAME();
                            //System.out.println("currBableEDXNAME..."+currBableEDXNAME);
                            if(currBableEDXID.equalsIgnoreCase(currEDXID)){
                                /** found the right object */
                                int posInVector = objectsList.indexOf(uObj);

                                /** set the value to the one typed in */
                                //logger.info("Setting error to: "+currObj.getValidationError());
                                uObj.setValidationError(currObj.getValidationError());
                                uObj.DESTINATION.setRESOLVEDVALUE(currObj.getNewValue());
                                uObj.DESTINATION.DISPLAYDETAILS.setBgColour("RED");
                                 System.out.println("setting up new val in the current object..." + currObj.getNewValue());
                                /** replace the object */
                                objectsList.set(posInVector, uObj);
                                break;
                            }

                        }
                    } else {
                        /** set the object's last error to blank */
                        System.out.println("There was an NO Error In Current Object. size="+objectsList.size());
                        Enumeration objsList = objectsList.elements();
                        String currEDXIDVal = currObj.getParentName();

                        EDXBable uObj = null;

                        while (objsList.hasMoreElements()) {

                            uObj = (EDXBable) objsList.nextElement();

                            String currBableEDXID = uObj.getEDXID();
                            String currBableEDXNAME = uObj.ORIGINATOR.getNAME();
                            // System.out.println("currBableEDXID..."+currBableEDXID+".currEDXIDVal.."+currEDXIDVal);
                            if(currBableEDXID.equalsIgnoreCase(currEDXIDVal))
                            {
                                /** found the right object */
                                int posInVector = objectsList.indexOf(uObj);

                                /** set the value to the one typed in */
                                //logger.info("Setting error to: Blank");
                                uObj.setValidationError("");
                                System.out.println("9currobj.getnewval...."+currObj.getNewValue()+"..position..."+posInVector);
                                uObj.DESTINATION.setRESOLVEDVALUE(currObj.getNewValue());

                                if(currObj.getNewValue()!=null || currObj.getNewValue()!=null)
                                    uObj.DESTINATION.DISPLAYDETAILS.setBgColour("GREEN");
                                /** replace the object */
                                objectsList.set(posInVector, uObj);

                                break;
                            }
                        }

                       // System.out.println("before first for loop..." );
                        /** SAVE THE VALUE TO THE CURRENT TRANSACTION DOM */
                        /** find the current edxid in the list of all EDXIDS */
                        for (int i = 0; i < allEDXBABLES.getLength(); i++) {
                            Node currEDXBABLE = allEDXBABLES.item(i);

                            /** get the edxid value */
                            String currEDXID = xmlInterface.getNodeValue(currEDXBABLE, "EDXID");
                            currEDXID = currEDXID.trim();

                            String currEDXNAME = xmlInterface.getNodeValue(currEDXBABLE, "ORIGINATOR\\NAME");
                            currEDXNAME = currEDXNAME.trim();
                            if (documentType.equalsIgnoreCase("PurchaseOrder")) {
                               // System.out.println("modifying purchase order");
                                orig_name_qty = "Order/OrderDetail/ListOfItemDetail/ItemDetail/BaseItemDetail/TotalQuantity/Quantity/QuantityValue";
                                orig_name_unitprice = "Order/OrderDetail/ListOfItemDetail/ItemDetail/PricingDetail/ListOfPrice/Price/UnitPrice/UnitPriceValue";
                            }else{
                               // System.out.println("modifying change order");
                                orig_name_qty = "ChangeOrder/ChangeOrderDetail/ListOfChangeOrderItemDetail/ChangeOrderItemDetail/ItemDetailChanges/ItemDetail/BaseItemDetail/TotalQuantity/Quantity/QuantityValue";
                                orig_name_unitprice = "ChangeOrder/ChangeOrderDetail/ListOfChangeOrderItemDetail/ChangeOrderItemDetail/ItemDetailChanges/ItemDetail/PricingDetail/ListOfPrice/Price/UnitPrice/UnitPriceValue";
                            }
                            /** if this  is the right node */
                            if(currEDXID.equalsIgnoreCase(currObjName)){
                           // if (currEDXNAME.equalsIgnoreCase(currObjName)) {
                                /** get the new value */
                                String newVal = currObj.getNewValue();
                                String storetype = xmlInterface.getNodeValue(currEDXBABLE, "DESTINATION\\STOREVALUEINBABLE");
                                xmlInterface.setElementValue(currEDXBABLE, "DESTINATION\\RESOLVEDVALUE", newVal);
                                System.out.println("55 Saving value ("+newVal+") for edx ID: "+currEDXID+"..storetype="+storetype+".....newVal="+newVal);
                                    if ((currEDXNAME.equalsIgnoreCase(orig_name_qty))) {
                                        qty=currObj.getNewValue();
                                        //System.out.println("new quantity.."+qty);
                                        if(qty!=null || qty!="")
                                            quantity = Float.parseFloat(qty);
                                            qtyflag=true;
                                        xmlInterface.setElementValue(currEDXBABLE, "DESTINATION\\RESOLVEDVALUE", qty);
                                    }else if ((currEDXNAME.equalsIgnoreCase(orig_name_unitprice))) {
                                         price=currObj.getNewValue();
                                      //   System.out.println("new price.."+price);
                                        if(price!=null || price!="")
                                            unitprice = Float.parseFloat(price);
                                            priceflag=true;
                                        xmlInterface.setElementValue(currEDXBABLE, "DESTINATION\\RESOLVEDVALUE", price);
                                    }
                                if(qtyflag && priceflag && flag1){
                                    calculateTotalLineItemPrice(quantity,unitprice,updateList);
                                }

                                //if (saveClicked) {
                                if(storetype.equalsIgnoreCase("PS")){
                                   String origval = xmlInterface.getNodeValue(currEDXBABLE, "ORIGINATOR\\VALUE");
                                    String resolvedval = xmlInterface.getNodeValue(currEDXBABLE, "DESTINATION\\RESOLVEDVALUE");
                                    String Parentval = xmlInterface.getNodeValue(currEDXBABLE, "ORIGINATOR\\PARENTVALUE");
                                    saveOrUpdateBable(currEDXID, origval, resolvedval, currEDXNAME, Parentval);
                                }
                                /** update the currentEDXBABLE with the new value */
                                
                                if(StringUtils.isNotEmpty(newVal)){
                                     System.out.println("setting bgcolor green.."+currEDXID);
                                    xmlInterface.setElementValue(currEDXBABLE, "DESTINATION\\DISPLAYDETAILS\\BGCOLOUR", "GREEN");
                                }
                                else{
                                    System.out.println("setting bgcolor red.."+currEDXID);
                                    xmlInterface.setElementValue(currEDXBABLE, "DESTINATION\\DISPLAYDETAILS\\BGCOLOUR", "RED");
                                }
                                /** update the actual value in the transaction file */
                                xmlInterface.setElementValue(currEDXBABLE.getParentNode(), "", newVal);

                                /** update the current elements value to dontConfirm (green tick) unless it's a lock or optional */
                                String confValue = xmlInterface.getNodeValue(currEDXBABLE, "DESTINATION\\RESOLVETYPE");

                                if (confValue.trim().equalsIgnoreCase("doConfirm")) {
                                    xmlInterface.setElementValue(currEDXBABLE, "DESTINATION\\RESOLVETYPE", "dontConfirm");
                                }

                                bgcol=xmlInterface.getNodeValue(currEDXBABLE, "DESTINATION\\DISPLAYDETAILS\\BGCOLOUR");
                                if(bgcol.equalsIgnoreCase("RED")){
                                    redcolor=true;
                                }
                                break;
                            }
                        }
                        for (int j = 0; j < allEDXBABLES.getLength(); j++) {
                            Node currEDXBABLE = allEDXBABLES.item(j);
                            /** get the edxid value */
                            String currEDXID = xmlInterface.getNodeValue(currEDXBABLE, "EDXID");
                            currEDXID = currEDXID.trim();
                            String EDXNAME = xmlInterface.getNodeValue(currEDXBABLE, "ORIGINATOR\\NAME");
                            EDXNAME = EDXNAME.trim();
                            /** if this  is the right node */
                            if(currEDXID.equalsIgnoreCase(id)){
                            if(redcolor){
                                    System.out.println("for id..setting bgcolor red.."+currEDXID);
                                    xmlInterface.setElementValue(currEDXBABLE, "DESTINATION\\DISPLAYDETAILS\\BGCOLOUR", "RED");
                                }
                                else{
                                    System.out.println("for id...setting bgcolor green.."+currEDXID);
                                    xmlInterface.setElementValue(currEDXBABLE, "DESTINATION\\DISPLAYDETAILS\\BGCOLOUR", "GREEN");
                                }

                            }
                    }
                    }
                }
            }
            //calculating Total Order Summary based on quantity and price values
            calculateTotalOrderSummaryPrice();
            //logger.info("** saveUpdateObjects ** writing file...");
            if (getCurrentTransactionDOM() != null) {
                /** save the current changes to the file */
                System.out.println("Current TransactionDOm is not null.."+getCurrentTransactionDOM().getNodeValue()+"...tempDir=="+tempDir);
                String filePath = tempDir + "/" + lastFileInConversation;
                xmlInterface.writeDomToFile(getCurrentTransactionDOM(),filePath);
            }else{
                System.out.println("Current TransactionDOm is null..");
            }

            /** check if a button was pressed */
            if (buttonClicked.equalsIgnoreCase("save")) {
                /** save the document */
                System.out.println("save button clicked..");
                saveCurrentTransaction();
                updateTransactionStatus("Open");
            //createPrintFile();
            }

            if (buttonClicked.equalsIgnoreCase("reject")) {
                /** reject the document */
                try {
                    rejectCurrentTransaction(reason,supNexusId);
                    updateTransactionStatus("Open");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            if (buttonClicked.equalsIgnoreCase("comment")) {
//                /** reject the document */
//                try {
//                    addComment(reason);
//                    updateTransactionStatus("Open");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }

            if (buttonClicked.equalsIgnoreCase("pending")) {
                /** pending the document */
                pendingCurrentTransaction();
            }

            if (buttonClicked.equalsIgnoreCase("accept")) {
                /** accept the document */
                try {
                    //acceptCurrentTransaction();reason
                    acceptCurrentTransaction(reason,supNexusId);
                    updateTransactionStatus("Open");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     *  This api is used for calculating total line Item price based on changes in Quantity and price value
     * @param qty of type float
     * @param price of type float
     * @param updateList of type Vector
     */
    public void calculateTotalLineItemPrice(float qty, float price, Vector updateList) {
        Enumeration updateObjs = updateList.elements();
        NodeList allEDXBABLES = getCurrentTransactionDOM().getElementsByTagName("EDXBABLE");
        String orig_name_totalunitprice = null;
        String totalprice = null;
        if (documentType.equalsIgnoreCase("PurchaseOrder"))
        {
            orig_name_totalunitprice = "Order/OrderDetail/ListOfItemDetail/ItemDetail/PricingDetail/TotalValue/MonetaryValue/MonetaryAmount";
        }else
        {
            orig_name_totalunitprice="ChangeOrder/ChangeOrderDetail/ListOfChangeOrderItemDetail/ChangeOrderItemDetail/ItemDetailChanges/ItemDetail/PricingDetail/TotalValue/MonetaryValue/MonetaryAmount";
        }
        while (updateObjs.hasMoreElements()) {
            /** get the current update object */
            UpdateObject currObj = (UpdateObject) updateObjs.nextElement();
            String currObjName = currObj.getParentName();
            /** filter out the fileName && id && buttonClicked && transType objects */
            if ((!currObjName.equalsIgnoreCase("fileName")) &&
                    (!currObjName.equalsIgnoreCase("id")) &&
                    (!currObjName.equalsIgnoreCase("buttonClicked")) &&
                    (!currObjName.equalsIgnoreCase("transType"))) {

                Enumeration objsList = objectsList.elements();
                String currEDXIDVal = currObj.getParentName();

                EDXBable uObj = null;

                for (int i = 0; i < allEDXBABLES.getLength(); i++) {
                    Node currEDXBABLE = allEDXBABLES.item(i);

                    /** get the edxid value */
                    String currEDXID = xmlInterface.getNodeValue(currEDXBABLE, "EDXID");
                    currEDXID = currEDXID.trim();

                    String currEDXNAME = xmlInterface.getNodeValue(currEDXBABLE, "ORIGINATOR\\NAME");
                    currEDXNAME = currEDXNAME.trim();
                    
                    /** if this  is the right node */
                    if (currEDXID.equalsIgnoreCase(currObjName)) {
                        String newVal = currObj.getNewValue();
                      if ((currEDXNAME.equalsIgnoreCase(orig_name_totalunitprice))) {
                            totalprice = getUpdatedTotal(qty, price);
                            xmlInterface.setElementValue(currEDXBABLE, "DESTINATION\\RESOLVEDVALUE", totalprice);
                            totalprice = null;
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * This is api is used to calculate order summary price 
     */
    public void calculateTotalOrderSummaryPrice(){
        NodeList allEDXBABLES = getCurrentTransactionDOM().getElementsByTagName("EDXBABLE");
        String orig_name_totalunitprice = null;
        String ordersummary_totalamount = null;
        String totalprice = null;
         float totalamount = 0.00F;
          float totalOrderamount = 0.00F;
          if (documentType.equalsIgnoreCase("PurchaseOrder"))
          {
                orig_name_totalunitprice = "Order/OrderDetail/ListOfItemDetail/ItemDetail/PricingDetail/TotalValue/MonetaryValue/MonetaryAmount";
                ordersummary_totalamount = "Order/OrderSummary/TotalAmount/MonetaryValue/MonetaryAmount";
          }else
          {
                orig_name_totalunitprice = "ChangeOrder/ChangeOrderDetail/ListOfChangeOrderItemDetail/ChangeOrderItemDetail/ItemDetailChanges/ItemDetail/PricingDetail/TotalValue/MonetaryValue/MonetaryAmount";
                ordersummary_totalamount = "ChangeOrder/ChangeOrderSummary/RevisedOrderSummary/OrderSummary/TotalAmount/MonetaryValue/MonetaryAmount";
          }
            for (int i = 0; i < allEDXBABLES.getLength(); i++) {
                    Node currEDXBABLE = allEDXBABLES.item(i);

                    String currEDXNAME = xmlInterface.getNodeValue(currEDXBABLE, "ORIGINATOR\\NAME");
                    currEDXNAME = currEDXNAME.trim();
                   // System.out.println("currEDXNAME..." + currEDXNAME+"...orig_name_totalunitprice..."+orig_name_totalunitprice);
                       if ((currEDXNAME.equalsIgnoreCase(orig_name_totalunitprice))) {
                            String totalLineItemvalue = xmlInterface.getNodeValue(currEDXBABLE, "DESTINATION\\RESOLVEDVALUE");
                            totalLineItemvalue = totalLineItemvalue.trim();
                            totalamount=Float.parseFloat(totalLineItemvalue);
                            totalOrderamount+=totalamount;
                        }
                }
                 //System.out.println("Total Order total price.." + totalOrderamount);
                for (int j = 0; j < allEDXBABLES.getLength(); j++) {
                    Node currEDXBABLE = allEDXBABLES.item(j);

                    String currEDXNAME = xmlInterface.getNodeValue(currEDXBABLE, "ORIGINATOR\\NAME");
                    currEDXNAME = currEDXNAME.trim();
                      // System.out.println("currEDXNAME..." + currEDXNAME+"...ordersummary_totalamount..."+ordersummary_totalamount);
                       if ((currEDXNAME.equalsIgnoreCase(ordersummary_totalamount))) {
                            xmlInterface.setElementValue(currEDXBABLE, "DESTINATION\\RESOLVEDVALUE", floatToString(totalOrderamount));
                           // System.out.println("after calculating total price.." + totalprice);
                        }
                }
    }
    
    public EdxBableBean checkStore(String xpath, String partnerId) {
        EdxBableBean edxBableBean = new EdxBableBean();
        try {
            //dbCommunicatorImpl = DbCommunicatorImpl.getInstance("C:\\TradeRoute\\config_files\\edxconfig.xml");
            //edxBableBean = dbCommunicatorImpl.getEdxBable(xpath, partnerId);
            edxBableBean = dataResolutionService.getEdxBable(xpath, partnerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return edxBableBean;
    }

    public void saveOrUpdateBable(String currEDXID, String origval, String resolvedval, String currEDXNAME, String parentval) {
        EdxBableBean edxBableBean = new EdxBableBean();

       // dbCommunicatorImpl = DbCommunicatorImpl.getInstance("C:\\TradeRoute\\config_files\\edxconfig.xml");
        try {
            //String partnerId=dbCommunicatorImpl.getPartnerId(this.lastFileInConversation);
              System.out.println(" currEDXID = " + currEDXID+"..origval="+origval+"..resolvedval="+resolvedval+"...currEDXNAME="+currEDXNAME+"..parentval="+parentval+"...lastFileInConversation="+lastFileInConversation);
            String partnerId=dataResolutionService.getPartnerId(this.lastFileInConversation);
            System.out.println("partnerId="+partnerId);
            edxBableBean = checkStore(currEDXNAME, partnerId);
            Element element2 = null;
            //String destval = dbCommunicatorImpl.checkValuePair(edxBableBean.getEDXID(), origval, parentval);
            String destval = dataResolutionService.checkValuePair(edxBableBean.getEDXID(), origval, parentval);
            System.out.println(" destval = " + destval);
            if (destval != null) {
                // update a value pair
                if (!resolvedval.equals(destval)) {
                    //dbCommunicatorImpl.updateValuePair(edxBableBean.getEDXID(), origval, resolvedval, parentval);
                    dataResolutionService.updateValuePair(edxBableBean.getEDXID(), origval, resolvedval, parentval);
                }
            } else {
                // add a new value pair
                if (!origval.equals(resolvedval)) {
                    System.out.println("origval!=resolved value creating new value pair");
                    //dbCommunicatorImpl.newValuePair(edxBableBean.getEDXID(), origval, resolvedval, parentval);
                    dataResolutionService.newValuePair(edxBableBean.getEDXID(), origval, resolvedval, parentval);
                }
            }
//                if (orig_name1.equalsIgnoreCase(orig_name_unitprice)) {
//                    updateLineItemTotal(n.getId().trim(), "price");
//                }
        } catch (Exception e) {
            System.out.println("Exception searching for PAIR Info Node: " + e.getMessage());
            e.printStackTrace();

        }
    }

    /************************************** VALIDATION FUNCTION *********************************/
    /**
     * FUNCTION [validateUpdateObject()]:
     *  - return true if the data in this object is valid.
     *
     * 	Validation:
     *
     * 	If the schemaToUse value for the current Element is "none" - don't validate it.
     * 	If the schemaToUse value for the current Element is "" - Use the BIG schema for this Document type.
     * 	If the schemaToUse value for the current Element is "<c:\\aaa\\bbb.xsd>" - Use a specific schema.
     *
     *
     */
    public synchronized boolean validateUpdateObject(UpdateObject currObj, Document fullSchema) {
        boolean ret = true;

        /** get the object's EDXID and value to validate */
        String currObjEDXID = currObj.getParentName();
        String valueToValidate = currObj.getNewValue();
        String nodeName = "";
        String schemaToUse = "";
        Document xmlDoc = null;

        String schemaOutputPath = "";
        String xmlOutputPath = "";



        /** get all the EDXBBALE's in the currentTransactionDON */
        NodeList allEDXBABLES = getCurrentTransactionDOM().getElementsByTagName("EDXBABLE");

        for (int i = 0; i < allEDXBABLES.getLength(); i++) {
            Node currEDX = allEDXBABLES.item(i);

            String currEDXID = xmlInterface.getNodeValue(currEDX, "EDXID");
            String currEDXNAME = xmlInterface.getNodeValue(currEDX, "ORIGINATOR\\NAME");
            //System.out.println("edxid...."+xmlInterface.getNodeValue(currEDX, "EDXID"));
            // System.out.println("ORIGINATOR\\NAME...."+xmlInterface.getNodeValue(currEDX, "ORIGINATOR\\NAME"));
            //System.out.println("currObjEDXID..."+currObjEDXID);
            //if (currEDXNAME.equalsIgnoreCase(currObjEDXID)) {
            if (currEDXID.equalsIgnoreCase(currObjEDXID)) {
                /** found the right node */
                nodeName = currEDX.getParentNode().getNodeName();
                schemaToUse = xmlInterface.getNodeValue(currEDX, "DESTINATION\\SCHEMATOUSE");
                break;
            }
        }

        /** if a nodeName was not found, return true */
        if (nodeName.equalsIgnoreCase("")) {
            return true;
        }

        if (schemaToUse.equalsIgnoreCase("none")) {
            return true;
        }

        if (schemaToUse.equalsIgnoreCase("")) {
            /** Nodes which need to be added to the schema template */
            Node elementToAddToMiniSchema = null;
            Node simpleTypeToAddToMiniSchema = null;
            Node complexTypeToAddToMiniSchema = null;
            Vector refList = null;

            /** vairiable to hold the type of the element */
            String typeAttribVal = "";


            /** search all the elements in the full schema for this node name */
            NodeList schemaElements = fullSchema.getElementsByTagName("xsd:element");

            for (int j = 0; j < schemaElements.getLength(); j++) {
                Node currNode = schemaElements.item(j);

                /** get the name attribute of this Node */
                String nameAttribVal = xmlInterface.getAttributeValue(currNode, "name");

                if (nameAttribVal.equalsIgnoreCase(nodeName)) {
                    /** Found the right node in the schema */
                    elementToAddToMiniSchema = currNode;

                    /** get it's 'type' attribute */
                    typeAttribVal = xmlInterface.getAttributeValue(currNode, "type");
                    break;
                }
            }

            /** if this element doesn't have a type then return true */
            if (typeAttribVal.equalsIgnoreCase("")) {
                return true;
            }


            /*logger.info("\n------------- Validating Object ---------------");
            logger.info("Obj Name: "+nodeName);
            logger.info("Obj Type: "+typeAttribVal);
            logger.info("Obj Value: "+valueToValidate);*/



            /**
             * If the element type contains the word 'xsd:' then it's a base type and no need to look
             * further in the schema to find the simpleType.
             */
            if (typeAttribVal.indexOf("xsd:") != -1) {
                /** has a standard type, eg) xsd:string, xsd:int, etc.. */
            } else {
                /** has an simpleType or complex Type, find it. */
                /** search all the elements in the full schema for this node name */
                NodeList schemaSimpleTypes = fullSchema.getElementsByTagName("xsd:simpleType");

                for (int k = 0; k < schemaSimpleTypes.getLength(); k++) {
                    Node currNode = schemaSimpleTypes.item(k);

                    /** get the name attribute of this Node */
                    String simpleTypeName = xmlInterface.getAttributeValue(currNode, "name");

                    if (simpleTypeName.equalsIgnoreCase(typeAttribVal)) {
                        /** Found the right simpleType node in the schema */
                        simpleTypeToAddToMiniSchema = currNode;
                        break;
                    }
                }


                /** If we didn't find a SimpleType, then look for Complex Types */
                if (simpleTypeToAddToMiniSchema == null) {
                    /** look through all the COMPLEX types */
                    NodeList schemaComplexTypes = fullSchema.getElementsByTagName("xsd:complexType");

                    for (int l = 0; l < schemaComplexTypes.getLength(); l++) {
                        Node currNode = schemaComplexTypes.item(l);

                        /** get the name attribute of this Node */
                        String complexTypeName = xmlInterface.getAttributeValue(currNode, "name");

                        if (complexTypeName.equalsIgnoreCase(typeAttribVal)) {
                            /** Found the right COMPLEXTYPE node in the schema */
                            complexTypeToAddToMiniSchema = currNode;

                            /** go and get all the type's that this COMPLEXTYPE references */
                            refList = getComplexTypeReferences(complexTypeToAddToMiniSchema, fullSchema);
                            break;
                        }
                    }

                }


            }

            /** can't save files with a ':' in the path */
            typeAttribVal = typeAttribVal.replaceAll(":", "_");

            String searchVal = typeAttribVal + ".xsd";


            Document schemaTemplate = null;


            /**
             * If the list of mini schemas allready contains the one we're looking for, i.e. we've
             * allready come across this type of element before
             */
            if (smallSchemaList.containsKey(searchVal)) {
                /** allready had a schema by that name */
                schemaTemplate = (Document) smallSchemaList.get(searchVal);

                /** if the current element doesn't exist in the schema add it */
                NodeList smallSchemaElements = schemaTemplate.getElementsByTagName("xsd:element");

                boolean foundit = false;

                for (int j = 0; j < smallSchemaElements.getLength(); j++) {
                    Node currElement = smallSchemaElements.item(j);

                    /** get the name attribute of this Node */
                    String elementName = xmlInterface.getAttributeValue(currElement, "name");

                    if (elementName.equalsIgnoreCase(nodeName)) {
                        /** allready in there */
                        foundit = true;
                        break;
                    }
                }

                /** current element was not allready in the schema */
                if (!foundit) {
                    /** get the root of the schema template */
                    Node schemaRoot = (Node) schemaTemplate.getDocumentElement();

                    /** if it wasn't in there allready */
                    if (elementToAddToMiniSchema != null) {
                        NamedNodeMap attribs = elementToAddToMiniSchema.getAttributes();

                        if (attribs.getNamedItem("minOccurs") != null) {
                            //logger.info("Removing minOccurs");
                            attribs.removeNamedItem("minOccurs");
                        }

                        if (attribs.getNamedItem("maxOccurs") != null) {
                            //logger.info("Removing minOccurs");
                            attribs.removeNamedItem("maxOccurs");
                        }

                        Node impElementNode = schemaTemplate.importNode(elementToAddToMiniSchema, false);
                        schemaRoot.appendChild(impElementNode);
                    }
                }
            } else {
                /** first time we've seen this schema type */
                /** read in the template file */
                try {
                    schemaTemplate = xmlInterface.readInXMLFile(schemaTemplateLoc);
                } catch (Exception e) {
                    logger.info("Error reading template file.. " + e.getMessage());
                }

                /** get the root of the schema template */
                Node schemaRoot = (Node) schemaTemplate.getDocumentElement();

                /** add the found node's to the schema*/
                if (elementToAddToMiniSchema != null) {
                    NamedNodeMap attribs = elementToAddToMiniSchema.getAttributes();

                    if (attribs.getNamedItem("minOccurs") != null) {
                        //logger.info("Removing minOccurs");
                        attribs.removeNamedItem("minOccurs");
                    }

                    if (attribs.getNamedItem("maxOccurs") != null) {
                        //logger.info("Removing minOccurs");
                        attribs.removeNamedItem("maxOccurs");
                    }


                    Node impElementNode = schemaTemplate.importNode(elementToAddToMiniSchema, false);
                    schemaRoot.appendChild(impElementNode);
                }

                /** add the found node's simpletype to the schema*/
                if (simpleTypeToAddToMiniSchema != null) {
                    Node impSimpleNode = schemaTemplate.importNode(simpleTypeToAddToMiniSchema, true);
                    schemaRoot.appendChild(impSimpleNode);
                }

                if (complexTypeToAddToMiniSchema != null) {
                    Node impComplexNode = schemaTemplate.importNode(complexTypeToAddToMiniSchema, true);
                    schemaRoot.appendChild(impComplexNode);
                }


                if (refList != null) {
                    Enumeration list = refList.elements();

                    while (list.hasMoreElements()) {
                        Node currNode = (Node) list.nextElement();
                        Node impCurrNode = schemaTemplate.importNode(currNode, true);
                        schemaRoot.appendChild(impCurrNode);
                    }
                }

                /** add the schema to the small schema list */
                smallSchemaList.put(searchVal, schemaTemplate);
            }

            schemaOutputPath = dirList.getConfigValue("PROP_SMALL_SCHEMA_DIR") + "\\" + documentType + "\\" + typeAttribVal + ".xsd";
            xmlOutputPath = dirList.getConfigValue("PROP_SMALL_SCHEMA_DIR") + "\\ValidTemp_" + typeAttribVal + ".xml";

            /** write out the schema file */
            xmlInterface.writeDomToFile(schemaTemplate, schemaOutputPath);

            /** create the XML document */
            xmlDoc = new DocumentImpl();
            Element root = xmlDoc.createElement(nodeName);

            String modSchemaPath = schemaOutputPath.replace('\\', '/');

            root.setAttribute("xsi:noNamespaceSchemaLocation", modSchemaPath);
            root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

            root.appendChild(xmlDoc.createTextNode(valueToValidate));

            xmlDoc.appendChild(root);
        } else {
            /** There was a schema specified to use for this element */
            schemaOutputPath = schemaToUse;
            xmlOutputPath = dirList.getConfigValue("PROP_SMALL_SCHEMA_DIR") + "\\ValidTemp_" + nodeName + ".xml";

            try {
                Document specificSchema = xmlInterface.readInXMLFile(schemaOutputPath);

                NodeList nl = specificSchema.getElementsByTagName("xsd:element");
                Node elementNode = nl.item(0);

                String attribName = xmlInterface.getAttributeValue(elementNode, "name");

                /** create the XML document */
                xmlDoc = new DocumentImpl();
                Element root = xmlDoc.createElement(attribName);

                String modSchemaPath = schemaOutputPath.replace('\\', '/');

                root.setAttribute("xsi:noNamespaceSchemaLocation", modSchemaPath);
                root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

                root.appendChild(xmlDoc.createTextNode(valueToValidate));

                xmlDoc.appendChild(root);

            } catch (Exception e) {
                logger.info("- Validation Exception: " + e.getMessage());
                return false;
            }
        }

        /******************** VALIDATION PART *********************/
        //logger.info("XML output path: "+xmlOutputPath);
        /** write out the xml file */
        xmlInterface.writeDomToFile(xmlDoc, xmlOutputPath);

        /** validate the xml file */
        if (xmlInterface.validateTransaction(xmlOutputPath, "")) {
        } else {
            ret = false;
        }

        /** clean up */
        File tempFile = new File(xmlOutputPath);
        tempFile.delete();

        return ret;
    }

    /**
     * FUNCTION [getComplexTypeReferences()]:
     *  - return all the Nodes that this complexType references.
     */
    public Vector getComplexTypeReferences(Node complexTypeNode, Document fullSchema) {
        Vector retList = new Vector();

        /** instansiate the global vector */
        complexTypeRefList = new Vector();

        /** go get all the required types to get */
        recurseComplexType(complexTypeNode);

        /** go and get all the returned types and add them to the retList */
        Enumeration refList = complexTypeRefList.elements();

        while (refList.hasMoreElements()) {
            String currVal = (String) refList.nextElement();

            /** get the current required type from the full schema (Assuming they're simpleTypes)*/
            NodeList schemaSimpleTypes = fullSchema.getElementsByTagName("xsd:simpleType");

            for (int k = 0; k < schemaSimpleTypes.getLength(); k++) {
                Node currNode = schemaSimpleTypes.item(k);

                /** get the name attribute of this Node */
                String simpleTypeName = xmlInterface.getAttributeValue(currNode, "name");

                if (simpleTypeName.equalsIgnoreCase(currVal)) {
                    /** Found the right simpleType node in the schema */
                    retList.add(currNode);
                    break;
                }
            }
        }

        /** cleanup */
        complexTypeRefList = null;

        return retList;
    }

    /**
     * FUNCTION [recurseComplexType()]:
     *  - Recurse the passed in COMPLEXTYPE Node
     */
    public void recurseComplexType(Node root) {
        /** get the first child of the parent */
        Node childNode = root.getFirstChild();

        /** get chapters' & headings' titles and URLs*/
        while (childNode != null) {
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                /** get the current node's name */
                String currNodeName = childNode.getNodeName();

                if ((currNodeName.equalsIgnoreCase("xsd:element")) || (currNodeName.equalsIgnoreCase("xsd:attribute"))) {
                    /** get this node's type */
                    String nodeType = xmlInterface.getAttributeValue(childNode, "type");

                    /** if it's not blank */
                    if (!nodeType.equalsIgnoreCase("")) {
                        if (nodeType.indexOf("xsd:") == -1) {
                            /** add this type to the external list */
                            complexTypeRefList.add(nodeType);
                        }
                    }
                }
            }

            if (childNode.hasChildNodes()) {
                recurseComplexType(childNode);
            }

            /** get the next sibling */
            childNode = childNode.getNextSibling();

        }
    /** end while */
    }

    
    public void saveCurrentTransaction() {
        try {
            boolean flag = false;
            System.out.println("lastFileInConversation..." + lastFileInConversation);
            //dbCommunicatorImpl = DbCommunicatorImpl.getInstance("C:\\TradeRoute\\config_files\\edxconfig.xml");
            //flag = dbCommunicatorImpl.saveTransactionDocument(currentTransactionDOM, lastFileInConversation);
            Node root = currentTransactionDOM.getDocumentElement();
            NodeList allEDXBABLES = getCurrentTransactionDOM().getElementsByTagName("EDXBABLE");
            String uomval="Order/OrderDetail/ListOfItemDetail/ItemDetail/BaseItemDetail/TotalQuantity/Quantity/UnitOfMeasurement/UOMCoded";
            for (int i = 0; i < allEDXBABLES.getLength(); i++) {
                    Node currEDXBABLE = allEDXBABLES.item(i);

                    String currEDXNAME = xmlInterface.getNodeValue(currEDXBABLE, "ORIGINATOR\\NAME");
                    currEDXNAME = currEDXNAME.trim();
                   // System.out.println("currEDXNAME..." + currEDXNAME+"...orig_name_totalunitprice..."+orig_name_totalunitprice);
                       if ((currEDXNAME.equalsIgnoreCase(uomval))) {
                            String resolval = xmlInterface.getNodeValue(currEDXBABLE, "DESTINATION\\RESOLVEDVALUE");
                            resolval = resolval.trim();
                           System.out.println("resolval..."+resolval);
                        }
                }
            
            flag = dataResolutionService.saveTransactionDocument(getCurrentTransactionDOM(),lastFileInConversation);
            System.out.println("flag value after save..." + flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    //updateTransStatus(currentTransactionDOM, "Open", "Resolved");
    }


    /** cancelCurrentTransaction */
    public void cancelCurrentTransaction() {
        //logger.info("[Trans_Disp_Bean: "+this.getLastFileInConversation()+"] cancelCurrentTransaction...in");
        //updateTransStatus(currentTransactionDOM, "Open", "Resolved");
        try {
            updateTransactionStatus("Open");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** printCurrentTransaction */
    public void printCurrentTransaction() {
        //logger.info("[Trans_Disp_Bean: "+this.getLastFileInConversation()+"] printCurrentTransaction...in");

        /** create the right input dir path */
        // TRADEROUTE_QUEUE_DIR = dirList.getConfigValue("PROP_"+transType+"_DIR");
        if (lastFileInConversation.equals("")) {
            lastFileInConversation = fileName;
        }

        /** Check for files in the input directory */
        //  String filePath = TRADEROUTE_QUEUE_DIR + "\\" + lastFileInConversation;
        //   File currFile = new File(filePath);
        //  if(currFile.isFile())
        //  {
        /** send the current transaction DOM to dynex for printing */
        //    logger.info("Transaction sent to Dynex for printing:"+currFile.getName());
//            createPrintFile(currFile.getAbsolutePath());
        createPrintFile();
    //  }
//        else
//        {
//            logger.info("[Trans_Disp_Bean: "+this.getLastFileInConversation()+"] Couldn't Print current file, it doesn't exist.:"+currFile.getAbsolutePath());
//        }
    }

    /** pendingCurrentTransaction */
    public void pendingCurrentTransaction() {
        //logger.info("[Trans_Disp_Bean: "+this.getLastFileInConversation()+"] pendingCurrentTransaction...in");
    }


    /**
     * FUNCTION [acceptExportTransaction()]:
     */
    public void acceptExportTransaction() {
        //updateBableFile();

        /** save the current changes to the file */
        String filePath = TRADEROUTE_QUEUE_DIR + "\\" + lastFileInConversation;
        logger.info("** acceptExportTransaction ** writing file...");
        xmlInterface.writeDomToFile(getCurrentTransactionDOM(),filePath);

        /** move file to the accept dir */
        String destPathAcc = dirList.getConfigValue("PROP_PROCESS_ACCEPT_DIR");
        destPathAcc = destPathAcc + "\\" + lastFileInConversation;
        try {
            xmlInterface.copyFile(filePath, destPathAcc);
            logger.info("Sent Export Doc To Accept Dir: " + destPathAcc);

            /** delete the original file */
            File origFile = new File(filePath);
            boolean fileExists = origFile.exists();
            while (fileExists) {
                //logger.info("- acceptExportTransaction - Deleting Original: "+origFile.getAbsolutePath());
                if (origFile.delete()) {
                    logger.info("Deleted Transaction: " + origFile.getName());
                } else {
                    logger.info("Couldn't Delete Transaction: " + origFile.getName());
                }
                fileExists = origFile.exists();
            }
            origFile = null;
        } catch (Exception e) {
            logger.info("-acceptExportTransaction- Exception Copying File to Dynex Dir:");
            e.printStackTrace();
        }

        setCurrentTransactionDOM(null);
    }


    public void acceptCurrentTransaction(String invoiceNo,String supNexusId) throws Exception {
        System.out.println("inside acceptCurrentTransaction="+lastFileInConversation+"..invoiceNo="+invoiceNo);
        saveCurrentTransaction();
        _xcblOrderProcessor = new XCBLOrderProcessor();
        _xcblChangeOrderProcessor = new XCBLChangeOrderProcessor();
        //dbCommunicatorImpl = DbCommunicatorImpl.getInstance("C:\\TradeRoute\\config_files\\edxconfig.xml");
        /** get a list of all the transaction's with this root id */
        //Vector transHeirarchy = dbCommunicatorImpl.getTransactionHeirarchyList(lastFileInConversation);
        Vector transHeirarchy = dataResolutionService.getTransactionHeirarchyList(lastFileInConversation);
        /** process all the transaction's in the heirarchy */
        for (int i = 0; i < transHeirarchy.size(); i++) {
            TransactionBean tBean = (TransactionBean) transHeirarchy.elementAt(i);
            //converting org.w3c.Document to org.jdom.Document    Vijay   06/05/2010
            org.jdom.Document currentDoc = convertW3CToJDOM(getCurrentTransactionDOM());
            /** accept this transaction, reject all the others */
            if (tBean.getId().equalsIgnoreCase(lastFileInConversation)) {
                if (tBean.getTransType().equalsIgnoreCase("Order")) {
                    //_xcblOrderProcessor.postProcess(currentDoc);
                    if(supNexusId.equalsIgnoreCase("500000")) //Newtown Orders
                        _xcblOrderProcessor.accept(currentDoc,invoiceNo);
                    else //kalamunda orders
                        _xcblOrderProcessor.accept_kala(currentDoc,Integer.parseInt(supNexusId));
                } else if (tBean.getTransType().equalsIgnoreCase("ChangeOrder")) {
                   // _xcblChangeOrderProcessor.postProcess(currentDoc);
                    _xcblChangeOrderProcessor.accept(currentDoc,invoiceNo);
                }
                //dbCommunicatorImpl.updateResolvedState(tBean.getId(), "accepted");
                //dbCommunicatorImpl.updateTransactionState(lastFileInConversation, "Processing");
                dataResolutionService.updateResolvedState(tBean.getId(), "accepted",Integer.parseInt(supNexusId));
                //dataResolutionService.updateTransactionState(lastFileInConversation, "Processing");
            } else {
                /** reject this transaction */
                currentDoc = convertW3CToJDOM(getDocument(tBean.getId()));

                if (tBean.getTransType().equalsIgnoreCase("Order")) {
                    //_xcblOrderProcessor.postProcess(currentDoc);
                    _xcblOrderProcessor.reject(currentDoc,"");
                } else if (tBean.getTransType().equalsIgnoreCase("ChangeOrder")) {
                  //  _xcblChangeOrderProcessor.postProcess(currentDoc);
                    _xcblChangeOrderProcessor.reject(currentDoc,"");
                }
                //dbCommunicatorImpl.updateResolvedState(tBean.getId(), "rejected");
                dataResolutionService.updateResolvedState(tBean.getId(), "rejected",Integer.parseInt(supNexusId));
                // change for new UMG Quadrem requirement
                // update the status to process bcos Quadrem does nto want response for document which is not the last one in seq
                //dbCommunicatorImpl.updateTransactionState(tBean.getId(), "Processed");
                dataResolutionService.updateTransactionState(tBean.getId(), "Processed","");
            }

            //_xmlWriter.output(currentDoc, new java.io.FileOutputStream(new java.io.File("C:/"+tBean.getId()+" "+i+".xml")));

            //dbCommunicatorImpl.saveTransactionDocument(convertJDOMTOW3C(currentDoc), tBean.getId());
            dataResolutionService.saveTransactionDocument(convertJDOMTOW3C(currentDoc), tBean.getId());
            /** update locked state to open */
            //dbCommunicatorImpl.setTransactionLockedState(tBean.getId(), "Open");
            dataResolutionService.setTransactionLockedState(tBean.getId(), "Open");
        /** update the transaction's state to 'processing' so the processor will pick it up */
        // moving this statement to the more correct place to update this based on trans by trans and based on document
        //_dbInterface.updateTransactionState(tBean.getId(), "processing");
        }
    }

    public void rejectCurrentTransaction(String reason,String supNexusId) throws Exception {
        saveCurrentTransaction();
        _xcblOrderProcessor = new XCBLOrderProcessor();
        _xcblChangeOrderProcessor = new XCBLChangeOrderProcessor();
       // dbCommunicatorImpl = DbCommunicatorImpl.getInstance("C:\\TradeRoute\\config_files\\edxconfig.xml");
        /** get a list of all the transaction's with this root id */
        //Vector transHeirarchy = dbCommunicatorImpl.getTransactionHeirarchyList(lastFileInConversation);
        Vector transHeirarchy = dataResolutionService.getTransactionHeirarchyList(lastFileInConversation);

        /** process all the transaction's in the heirarchy */
        for (int i = 0; i < transHeirarchy.size(); i++) {
            TransactionBean tBean = (TransactionBean) transHeirarchy.elementAt(i);
            org.jdom.Document currentDoc = convertW3CToJDOM(getCurrentTransactionDOM());
            if (tBean.getTransType().equalsIgnoreCase("Order")) {
               // _xcblOrderProcessor.postProcess(currentDoc);
                if(supNexusId.equalsIgnoreCase("500000")) //Newtown Orders
                    _xcblOrderProcessor.reject(currentDoc,reason);
                else //kalamunda orders
                    _xcblOrderProcessor.reject_kala(currentDoc,reason,Integer.parseInt(supNexusId));
            } else if (tBean.getTransType().equalsIgnoreCase("ChangeOrder")) {
                _xcblChangeOrderProcessor.reject(currentDoc,reason);
            }

//            dbCommunicatorImpl.saveTransactionDocument(convertJDOMTOW3C(currentDoc), tBean.getId());
//            /** update locked state to open */
//            dbCommunicatorImpl.setTransactionLockedState(tBean.getId(), "Open");
//            /** update the transaction's state to 'processing' so the processor will pick it up */
//            dbCommunicatorImpl.updateTransactionState(tBean.getId(), "processing");
//            /** update the resolved state */
//            dbCommunicatorImpl.updateResolvedState(tBean.getId(), "rejected");

            dataResolutionService.saveTransactionDocument(convertJDOMTOW3C(currentDoc), tBean.getId());
            /** update locked state to open */
            dataResolutionService.setTransactionLockedState(tBean.getId(), "Open");
            /** update the transaction's state to 'processing' so the processor will pick it up */
            dataResolutionService.updateTransactionState(tBean.getId(), "processing",reason);
            /** update the resolved state */
            dataResolutionService.updateResolvedState(tBean.getId(), "rejected",Integer.parseInt(supNexusId));
        }
    }

    public void addComment(String comment) throws Exception {

        dataResolutionService.addInternalComment(lastFileInConversation, comment);
    }

    public void createPrintFile() {
        try {
            String printDir = dirList.getConfigValue("print_dir");
            //converting org.w3c.Document to org.jdom.Document    Vijay   06/05/2010
            DOMBuilder builder = new DOMBuilder();
            org.jdom.Document jdomDoc = builder.build(getCurrentTransactionDOM());
            this.initialise();
            String stylesheetPath = "";
            if (documentType.equalsIgnoreCase("PurchaseOrder")) {
                System.out.println("documentType" + documentType+"..printDir="+printDir);
                /** find out which partner this file is from */
                //stylesheetPath = dirList.getConfigValue("xsl_po_print_path");
                //stylesheetPath =System.getProperty("catalina.base")+ "\\webapps\\Auto\\xsl\\print_po.xsl";
                stylesheetPath =dirList.getConfigValue("xsl_po_print_path");
                System.out.println("Using Generic Print StyleSheet: " + stylesheetPath);
            } else {
                //stylesheetPath = dirList.getConfigValue("xsl_co_print_path");
                //stylesheetPath =System.getProperty("catalina.base")+ "\\webapps\\Auto\\xsl\\print_co.xsl";
                stylesheetPath =dirList.getConfigValue("xsl_co_print_path");
            }

            if (stylesheetPath.equals("")) {
                throw new Exception("Could not find print stylesheet config property for ");
            }
            JDOMSource source = new JDOMSource(jdomDoc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer(new StreamSource(new File(stylesheetPath)));
            //File resultTempFile = new File(System.getProperty("catalina.base")+"\\webapps\\Auto\\temp\\print_" + lastFileInConversation + ".htm");
            File resultTempFile = new File(printDir+"/print_" + lastFileInConversation + ".htm");
            //File  resultTempFile1=new File("webapps\\Auto\\temp\\print_" + lastFileInConversation + ".htm");
            //File resultTempFile = new File("http:\\203.206.178.177:8080\\Auto\\temp\\print_" + lastFileInConversation + ".htm");
            System.out.println("2resultfile get absolutepath.." + resultTempFile.getAbsolutePath() + "..path.." + resultTempFile.getPath());
            FileOutputStream fout = new FileOutputStream(resultTempFile.getAbsolutePath());
            StreamResult result = new StreamResult(fout);
            transformer.transform(source, result);
            fout.flush();
            fout.close();
           // if ((new File(resultTempFile.getPath()).exists())) {
            //System.out.println( "22..path.." + resultTempFile.getPath());
            //Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http:/203.206.178.177:8080/Auto/temp/print_20130417041412-2.htm");
			//p.waitFor();
    		//}
           // else {
            //System.out.println("File is not exists.."+resultTempFile.getAbsolutePath());
           // }
        // BrowserLauncher.openURL(resultTempFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    /**
     * FUNCTION [getTransactionObjects()]:
     *  - return a list of valuesObjects containing all the eleements in this
     *    transactions' XML file.
     */
    public synchronized Enumeration getTransactionObjects(String edxID) {
        try {
            System.out.println("errorInLastBatch..getTransactionObjects."+errorInLastBatch+"...edxID="+edxID);
            if (errorInLastBatch == false) {

                /** clear the objects list */
                objectsList.removeAllElements();

                /** read in the current transaction */
                if (getCurrentTransactionDOM() == null) {
                    getXMLFile(this.lastFileInConversation);
                }

                if (getCurrentTransactionDOM() != null) {

                    /** Get all the EDXBABLE structures */
                    NodeList EDXBABLES = getCurrentTransactionDOM().getElementsByTagName("EDXBABLE");

                    /** For all the structures returned */
                    for (int i = 0; i < EDXBABLES.getLength(); i++) {
                        /** Get the current EDXBABLE structure */
                        Node currentEDXBABLE = EDXBABLES.item(i);

                        /** Get the EDXID of this EDXBABLE */
                        String currEDXID = xmlInterface.getNodeValue(currentEDXBABLE, "EDXID");

                        /** if this is the right EDXBABLE */
                        if (currEDXID.equalsIgnoreCase(edxID)) {

                            //logger.info("Found the right EDXBABLE("+currEDXID+"): "+currentEDXBABLE.getParentNode().getNodeName());

                            /** found the right node, get it's siblings which want to be displayed */
                            NodeList nl = currentEDXBABLE.getParentNode().getChildNodes();

                            /** for all the siblings returned */
                            for (int j = 0; j < nl.getLength(); j++) {
                                /** get the current sibling */
                                Node currSibling = nl.item(j);

                                /** exclude the current EDXBABLE */
                                if (!currSibling.getNodeName().equalsIgnoreCase("EDXBABLE")) {
                                    /** recurse this node and see whether it wants to be displayed */
                                    if (currSibling.getNodeType() == Node.ELEMENT_NODE) {
                                        recurseSibling(currSibling);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            this.setErrorText(e.getMessage());
            logger.info(e.getMessage());
        }

//                Enumeration retList = objectsList.elements();
//
//                logger.info("******************************* RET LIST ********************");
//
//                while(retList.hasMoreElements())
//                {
//                        EDXBable uObj = (EDXBable)retList.nextElement();
//
//                        logger.info("Current Ret Obj: "+uObj.DESTINATION.getENGLISHNAME());
//                        logger.info("Current Ret Obj: "+uObj.ORIGINATOR.getNAME());
//                }
//
//                logger.info("**************************************************************");
//
        return objectsList.elements();
    }

    /**
     * FUNCTION [recurseSibling()]:
     *  - Recurse the passed in Node, printing to System.out if debug = true.
     */
    public void recurseSibling(Node root) {
        /** get the first child of the parent */
        Node childNode = root.getFirstChild();

        /** get chapters' & headings' titles and URLs*/
        while (childNode != null) {
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                if (childNode.getNodeName().equalsIgnoreCase("EDXBABLE")) {
                    /** does this Node want to be displayed ? */
                    String displayValues = xmlInterface.getNodeValue(childNode, "DESTINATION\\DISPLAYDETAILS\\DISPLAYINCONTENT");

                    if (displayValues.equalsIgnoreCase("true")) {
                        addNodeToVector(childNode);
                    }
                }
            }

            if (childNode.hasChildNodes()) {
                recurseSibling(childNode);
            }

            /** get the next sibling */
            childNode = childNode.getNextSibling();

        }
    /** end while */
    }

    /**
     * FUNCTION [addNodeToVector()]:
     *  - Get the values for the passed in node and add to the objects list.
     */
    public void addNodeToVector(Node root) {
        /** create a new EDXBable object */
        EDXBable edxObj = new EDXBable();

        /** get all the required values */
        edxObj.setEDXID(xmlInterface.getNodeValue(root, "EDXID"));
        /** originator */
        edxObj.ORIGINATOR.setDATATYPE(xmlInterface.getNodeValue(root, "ORIGINATOR\\DATATYPE"));
        edxObj.ORIGINATOR.setNAME(xmlInterface.getNodeValue(root, "ORIGINATOR\\NAME"));
        edxObj.ORIGINATOR.setVALUE(xmlInterface.getNodeValue(root, "ORIGINATOR\\VALUE"));
        edxObj.ORIGINATOR.setPARENTVALUE(xmlInterface.getNodeValue(root, "ORIGINATOR\\PARENTVALUE"));
        /** destination */
        edxObj.DESTINATION.setDATATYPE(xmlInterface.getNodeValue(root, "DESTINATION\\DATATYPE"));
        edxObj.DESTINATION.setENGLISHNAME(xmlInterface.getNodeValue(root, "DESTINATION\\ENGLISHNAME"));
        edxObj.DESTINATION.setRESOLVEDVALUE(xmlInterface.getNodeValue(root, "DESTINATION\\RESOLVEDVALUE"));
        edxObj.DESTINATION.setHELPNOTE(xmlInterface.getNodeValue(root, "DESTINATION\\HELPNOTE"));
        edxObj.DESTINATION.setREQUIRED(xmlInterface.getNodeValue(root, "DESTINATION\\REQUIRED"));
        edxObj.DESTINATION.setSTOREVALUEINBABLE(xmlInterface.getNodeValue(root, "DESTINATION\\STOREVALUEINBABLE"));
        edxObj.DESTINATION.setRESOLVETYPE(xmlInterface.getNodeValue(root, "DESTINATION\\RESOLVETYPE"));
        edxObj.DESTINATION.setCURRKEYVALUE(xmlInterface.getNodeValue(root, "DESTINATION\\CURRKEYVALUE"));
        edxObj.DESTINATION.setSCHEMATOUSE(xmlInterface.getNodeValue(root, "DESTINATION\\SCHEMATOUSE"));
        /** display details */
        edxObj.DESTINATION.DISPLAYDETAILS.setBgColour(xmlInterface.getNodeValue(root, "DESTINATION\\DISPLAYDETAILS\\BGCOLOUR"));
        //edxObj.DESTINATION.DISPLAYDETAILS.setBgColour(bgcolor);
        edxObj.DESTINATION.DISPLAYDETAILS.setFontColour(xmlInterface.getNodeValue(root, "DESTINATION\\DISPLAYDETAILS\\FONTCOLOUR"));
        edxObj.DESTINATION.DISPLAYDETAILS.setFontSize(xmlInterface.getNodeValue(root, "DESTINATION\\DISPLAYDETAILS\\FONTSIZE"));
        edxObj.DESTINATION.DISPLAYDETAILS.setDisplayInTree(xmlInterface.getNodeValue(root, "DESTINATION\\DISPLAYDETAILS\\DISPLAYINTREE"));
        edxObj.DESTINATION.DISPLAYDETAILS.setDisplayInContent(xmlInterface.getNodeValue(root, "DESTINATION\\DISPLAYDETAILS\\DISPLAYINCONTENT"));


        //logger.info("Adding object to objectsList: "+edxObj.getEDXID());

        objectsList.add(edxObj);
    }

    /******************************** GET/SET FUNCTIONS ******************************/
    /**
     * FUNCTION [getString()]:
     *  - Return the output string to the .
     */
    public String getXMLString() {
//        if (!this.errorText.equals("")) {
//            updateTransStatus(currentTransactionDOM, "Open", "Resolved");
//            return "";
//        }
        //logger.info("XML String: \n"+output.toString());
        return output.toString();
    }

    /**
     * FUNCTION [setConfigFileLocation()]:
     *  - Set the configFileLocation property.
     */
    public void setConfigFileLocation(String val) {
        cleanUp();

        configFileLocation = val;

        /** Check if the config file exists and is valid */
        if (configFileLocation.equalsIgnoreCase("")) {
            setErrorText("The Config File Location has not been set.");
        }

        File configFile = new File(configFileLocation);

        if (!configFile.isFile()) {
            setErrorText("The Config File: " + getConfigFileLocation() + ", does not exist.");
        }
    }

    /**
     * FUNCTION [cleanUp()]:
     *  - reset all the variables to their original values.
     */
    public void cleanUp() {
        /** General variables */
        setCurrentTransactionDOM(null);
        objectsList.removeAllElements();
        output = null;
        objectId = 1;
        dirList = null;
        configFileLocation = "";
        errorText = "";
        //fileName = "";
        transType = "";

        /** Variables to hold the directory paths for the application */
        TRADEROUTE_QUEUE_DIR = "";
    }

    public String getDocumentType() {
        return documentType;
    }

    public Map getSmallSchemaList() {
        return smallSchemaList;
    }

    public void setSmallSchemaList(Map val) {
        smallSchemaList = val;
    }

    /**
     * FUNCTION [getCloseForm()]:
     *  - return the closeForm property.
     */
    public String getCloseForm() {
        return closeForm;
    }

    /**
     * FUNCTION [getConfigFileLocation()]:
     *  - return the configFileLocation property.
     */
    public String getConfigFileLocation() {
        return configFileLocation;
    }

    /**
     * FUNCTION [setErrorText()]:
     *  - Set the errorText property.
     */
    public void setErrorText(String val) {
        errorText = val;
    }

    /**
     * FUNCTION [getErrorText()]:
     *  - return the errorText property.
     */
    public String getErrorText() {
        return errorText;
    }

    /**
     * FUNCTION [setFileName()]:
     *  - Set the fileID property.
     */
    public void setFileName(String val) {
        fileName = val;
    }

    /**
     * FUNCTION [getFileName()]:
     *  - return the fileName property.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * FUNCTION [setLastFileInConversation()]:
     *  - Set the lastFileInConversation property.
     */
    public void setLastFileInConversation(String val) {
        lastFileInConversation = val;
    }

    /**
     * FUNCTION [getLastFileInConversation()]:
     *  - return the lastFileInConversation property.
     */
    public String getLastFileInConversation() {
        return lastFileInConversation;
    }

    /**
     * FUNCTION [setTransType()]:
     *  - Set the transType property.
     */
    public void setTransType(String val) {
        transType = val;
    }

    /**
     * FUNCTION [getTransType()]:
     *  - return the transType property.
     */
    public String getTransType() {
        return transType;
    }

    /**
     * FUNCTION [setUserName()]:
     *  - Set the userName property.
     */
    public void setUserName(String val) {
        userName = val;
    }

    /**
     * FUNCTION [getUserName()]:
     *  - return the userName property.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * FUNCTION [setUserType()]:
     *  - Set the userType property.
     */
    public void setUserType(String val) {
        userType = val;
    }

    /**
     * FUNCTION [getUserType()]:
     *  - return the userType property.
     */
    public String getUserType() {
        return userType;
    }

    /** Main used for testing */
    public static void main(String[] args) {

        Trans_Disp_Bean tb = new Trans_Disp_Bean();
        tb.setFileName("C:\\Dynex\\TRSapp\\TRANSACTIONS\\traderoute_queue\\Purchase_Orders\\POAXIS2MSA_S00424_20021120034432-4.xml");
        tb.setLastFileInConversation("C:\\Dynex\\TRSapp\\TRANSACTIONS\\traderoute_queue\\Purchase_Orders\\POAXIS2MSA_S00424_20021120034432-4.xml");
        tb.setConfigFileLocation("c:\\dynex\\edxconfig.xml");
        tb.setTransType("ORDER");
        tb.initialise();
        try {
            logger.info("Starting...");

            tb.setCurrentTransactionDOM(tb.xmlInterface.readInXMLFile("C:\\Dynex\\TRSapp\\TRANSACTIONS\\traderoute_queue\\Purchase_Orders\\POAXIS2MSA_S00424_20021120034432-4.xml"));

            //tb.rejectCurrentTransaction();
           // tb.acceptCurrentTransaction();

        } catch (Exception e) {
            logger.info("ERROR: ");
            e.printStackTrace();
        }
    }

    public org.w3c.dom.Document convertJDOMTOW3C(org.jdom.Document jdomDoc) throws JDOMException {
        DOMOutputter outputter = new DOMOutputter();
        return outputter.output(jdomDoc);
    }

    public org.jdom.Document convertW3CToJDOM(org.w3c.dom.Document w3cDoc) throws JDOMException {
        DOMBuilder builder = new DOMBuilder();
        return builder.build(w3cDoc);
    }

    public Document openTransaction(String transId) throws Exception {
        long start = System.currentTimeMillis();
        String userList = null;
        //dbCommunicatorImpl = DbCommunicatorImpl.getInstance("C:\\TradeRoute\\config_files\\edxconfig.xml");
        System.out.println("transId.in openTransaction..." + transId);
        try {
            //TransactionBean transactionBean = dbCommunicatorImpl.getFullTransactionInfoId(transId);
            TransactionBean transactionBean = dataResolutionService.getFullTransactionInfoId(transId);
            if (transactionBean != null) {
                // System.out.println("inside openTransaction:id " + transactionBean.getId());
//                    if (transactionBean.getOpenState().equalsIgnoreCase("locked"))
//                    {
//                        System.out.println("inside openTransaction:open state Locked " + transactionBean.getOpenState());
//                        int pos = transactionBean.getUserList().lastIndexOf(",");
//                        if (pos != -1) {
//                            userList = transactionBean.getUserList().substring(pos + 1);
//                        }
//                    }
//                    else
//                    {
//                        if (transactionBean.getState().equalsIgnoreCase("processing") || transactionBean.getState().equalsIgnoreCase("processed"))
//                        {
//                            System.out.println("inside openTransaction:state processing or processed " + transactionBean.getState());
//                            // printTransaction(tb);
//                        }
//                        else
//                        {
                System.out.println("inside openTransaction:state not processing or not processed " + transactionBean.getOpenState());
                /** get the transaction heirarchy list and lock each one */
                //dbCommunicatorImpl.setTransactionHeirarchyState(transactionBean.getRootParentTransactionId(), "Locked");
                dataResolutionService.setTransactionHeirarchyState(transactionBean.getRootParentTransactionId(), "Locked");
                /** get the last transaction in the heirarchy */
                //TransactionBean transBean = dbCommunicatorImpl.getLastTransactionInHeirarchyList(transId);
                TransactionBean transBean = dataResolutionService.getLastTransactionInHeirarchyList(transId);
                //this.updateUserList(transBean.getId(), user.getUserLoginName());
                if(transBean.getTransType().equalsIgnoreCase("Order")){
                    documentType="PurchaseOrder";
                }else if(transBean.getTransType().equalsIgnoreCase("ChangeOrder")){
                    documentType="ChangeOrder";
                }
                System.out.println("TransId: " + transBean.getId());
                setCurrentTransactionDOM(getDocument(transBean.getId()));
                long end = System.currentTimeMillis();
                double taken = (end - start) / 1000.00;
                System.out.println("Time taken: " + taken + " seconds.");
//                        }
//                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getCurrentTransactionDOM();
    }

    public void updateTransactionStatus(String status) {
        //dbCommunicatorImpl = DbCommunicatorImpl.getInstance("C:\\TradeRoute\\config_files\\edxconfig.xml");
        System.out.println("transId.in updateTransactionStatus..." + this.getLastFileInConversation());
        try {
            //TransactionBean transactionBean = dbCommunicatorImpl.getFullTransactionInfoId(this.getLastFileInConversation());
            //dbCommunicatorImpl.setTransactionHeirarchyState(transactionBean.getRootParentTransactionId(), status);
            TransactionBean transactionBean = dataResolutionService.getFullTransactionInfoId(this.getLastFileInConversation());
            dataResolutionService.setTransactionHeirarchyState(transactionBean.getRootParentTransactionId(), status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public String getUpdatedTotal(float quantity,float price){
         NumberFormat nf = java.text.NumberFormat.getInstance();
         String totalPrice=null;
            nf.setGroupingUsed(false);
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumIntegerDigits(1);
            nf.setMaximumIntegerDigits(10);
            try{
                totalPrice = "" + (quantity * price);
                totalPrice = nf.format(quantity * price);
                System.out.println("Total Amount "+totalPrice);
            }catch(NumberFormatException nfe){
            nfe.printStackTrace();
        }
            return totalPrice;
    }
    public String floatToString(float value){
         NumberFormat nf = java.text.NumberFormat.getInstance();
         String totalPrice=null;
            nf.setGroupingUsed(false);
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumIntegerDigits(1);
            nf.setMaximumIntegerDigits(10);
            try{
                totalPrice = "" + (value);
                totalPrice = nf.format(value);
            }catch(NumberFormatException nfe){
            nfe.printStackTrace();
        }
            return totalPrice;
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

    /**
     * @return the treeOutput
     */
    public String getTreeOutput() {
        return treeOutput;
    }

    /**
     * @param treeOutput the treeOutput to set
     */
    public void setTreeOutput(String treeOutput) {
        this.treeOutput = treeOutput;
    }

    /**
     * @return the currentTransactionDOM
     */
    public Document getCurrentTransactionDOM() {
        return currentTransactionDOM;
    }

    /**
     * @param currentTransactionDOM the currentTransactionDOM to set
     */
    public void setCurrentTransactionDOM(Document currentTransactionDOM) {
        this.currentTransactionDOM = currentTransactionDOM;
    }
}

