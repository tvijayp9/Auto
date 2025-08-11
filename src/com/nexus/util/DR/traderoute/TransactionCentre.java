package com.nexus.util.DR.traderoute;

/**
* Title:        TradeRoute
* Description:
* Copyright:    Copyright (c) 2002
* Company:      XMLYES
* @author Oscar Pfohl
* @version 1.0
*/

/** Import all required classes */
import com.nexus.services.DataResolutionService;
import com.nexus.services.DataResolutionServiceImpl;
import java.io.*;
import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Enumeration;
import com.nexus.util.DR.commonsource.*;
import com.nexus.util.DR.accessdbcomm.*;
import com.nexus.util.DR.misc.*;
import org.apache.log4j.Logger;

/** 
 * Class TransactionCentre:
 *  - Called from an ASP file.
 */
public class TransactionCentre
{
    private SAXTransactionReaderPO saxReaderPO = null;
    private SAXTransactionReaderExport saxReaderExport = null;
    private BableEngine bableEngine = null;
    private static XMLFunctions xmlInterface = null;
    /** Global structure for holding todoObjects */
    private static Vector todoObjects = new Vector();
    private static Vector COObjects = new Vector();
    private static Vector TransObjects = new Vector();
    /** variable to hold locked file list */
    private LockedFileList LFL = null;
    public Enumeration list = null;
    /** Variables to hold the values from the config file */
    private static String configFileLocation = "";
    private static String logPropFileLocation = "";
    /** Variables to hold the values from the config file */
    private static String transType = "";
    /** Variables to hold the directory paths for the application */
    private static String TRADEROUTE_QUEUE_DIR = "";
    private static String TRADEROUTE_LOCKED_FILES_PATH = "";
    private static int NUMBER_TRANS_PER_SCREEN = 0;
    /** Variable to hold any error which may have occurred during execution. */
    private static String errorText = "";
    /** Directory list read in from config file */
    public ConfigValuesList dirList;
    /** connection to the error data base */
    //private DatabaseInterface dbConn = null;
    /** SQL query used to get the latest key value from the database */
    private static String Get_Max_Key_Query = "SELECT * FROM AdministratorErrorLogs WHERE Key = (SELECT MAX(Key) FROM AdministratorErrorLogs)";

    // private static DbCommunicatorImpl dbCommunicatorImpl = null;

     private DataResolutionService dataResolutionService=new DataResolutionServiceImpl();
     
    /** TimeStamp variable */
    private TimeStamp tStamp = null;
    private File data_in_dir = null;
    boolean allreadyInitialised = false;
    /** SQL query to find the latest added ParentKey in the Parent_Transactions table */
    private final static String get_Latest_ParentKey_SQL = "SELECT * FROM Parent_Transactions WHERE ParentKey = (SELECT MAX(ParentKey) FROM Parent_Transactions)";
    
    /** SQL query to find the latest added ParentKey in the Parent_Transactions table */
    private final static String get_Latest_TransStat_SQL = "SELECT * FROM Transaction_Status WHERE StatusID = (SELECT MAX(StatusID) FROM Transaction_Status)";
    private int counter = 0;
    //private static Logger rootLogger = null;
    //private static Logger logger = null;
     Logger log=Logger.getLogger(TransactionCentre.class);
    private Document transResStore = null;
    
    
    
    /************************************** FUNCTIONS **********************************/


	/**
	 * FUNCTION [TransactionCentre()]:
	 *  - Constructor.
	 */
	public TransactionCentre()
	{
        log.info("inside TransactionCentre");
	}


	/**
	 * FUNCTION [setErrorText()]:
	 *  - Set the errorText property.
	 */
	public void setErrorText( String val)
	{
		errorText = val;
	}
	
	/**
	 * FUNCTION [getErrorText()]:
	 *  - return the errorText property.
	 */
	public String getErrorText()
	{
		return errorText;
	}

	/**
	 * FUNCTION [setConfigFileLocation()]:
	 *  - Set the configFileLocation property.
	 */
	public void setConfigFileLocation( String val)
	{
		configFileLocation = val;
	}
	
	/**
	 * FUNCTION [getConfigFileLocation()]:
	 *  - return the configFileLocation property.
	 */
	public String getConfigFileLocation()
	{
		return configFileLocation;
	}
	
	/**
	 * FUNCTION [setConfigFileLocation()]:
	 *  - Set the configFileLocation property.
	 */
	public void setLogPropFileLocation( String val)
	{
		logPropFileLocation = val;
	}
	
	/**
	 * FUNCTION [getConfigFileLocation()]:
	 *  - return the configFileLocation property.
	 */
	public String getLogPropFileLocation()
	{
		return logPropFileLocation;
	}
	
	
	/**
	 * FUNCTION [setTransType()]:
	 *  - return the configFileLocation property.
	 */
	public void setTransType(String val)
	{
		transType = val;
	}
	
	/**
	 * FUNCTION [initialise()]:
	 *  - initialise variables.
	 */
	public void initialise()
	{
        log.info("inside initialise=="+allreadyInitialised);
		if(!allreadyInitialised)
		{
			//logger.info("Initialising...");
			/** setup variables */
            saxReaderPO = new SAXTransactionReaderPO();
            saxReaderExport = new SAXTransactionReaderExport();
            saxReaderPO.initialise();
            saxReaderExport.initialise();
			bableEngine = null;
			xmlInterface = null;
			bableEngine = new BableEngine();
			xmlInterface = new XMLFunctions();
			//configFileLocation="C:\\TradeRoute\\config_files\\edxconfig.xml";
            //configFileLocation=System.getProperty("catalina.base")+"\\webapps\\Auto\\map\\config_files\\edxconfig.xml";
            //configFileLocation="/home/xmlyes/tomcat/Applications/Auto/map/config_files/edxconfig.xml";
            configFileLocation=dataResolutionService.getTradeRouteFolderPath("TradeRouteConfig");
            System.out.println("configFileLocation=="+configFileLocation);
			/** setup configList */
			dirList = ConfigValuesList.getInstance("TransactionCentre","TRADEROUTE");
			dirList.initialise(configFileLocation);
									
			//tStamp  = new TimeStamp();
			
			/** generate today's file name for the log file */
	       // tStamp.generateTimeStamp();
	       // String logName = dirList.getConfigValue("PROP_LOG_DIR");
	      //  logName = logName + "\\TradeRoute_Log_"+tStamp.getFormat6()+".txt";
	        
             //   System.out.println("log: "+logName);
                
	        /** add a file handler */
	        /*
                try{
	            // Read in logging properties
	            System.setProperty("java.util.logging.config.file", logPropFileLocation);
	            LogManager lm = LogManager.getLogManager();
	            lm.readConfiguration();
	            
	            // create a logger object for this class
	            rootLogger = Logger.getLogger("");
	            // add a file handler
	            FileHandler fh = new FileHandler(logName, true);            
	            rootLogger.addHandler(fh);
	            
	            logger = Logger.getLogger("traderoute.TransactionCentre");
	            //logger.setUseParentHandlers(true);           
	        }
	        catch(IOException io)
	        {            
	            String errorMess = "Exception Creating Log File...\n"+io.getMessage()+
	            "\n\nTradeRoute Server will now shut down.";
	            System.out.println(errorMess);
                    io.printStackTrace();
	            System.exit(0);
	        }   */
                
	        log.info("************************ TradeRoute Starting ************************");
	        log.info("Starting, Config Path: " + configFileLocation);
			
                readInPartnerResolutionFile();
			/** connect to the database every time this function is called */
//			dbConn = DatabaseInterface.getInstance();
//
//			if(!dbConn.isConnected())
//			{
//				/** setup the errorDBConnection */
//				if(!dbConn.connectToDatabases(configFileLocation))
//				{
//					logger.info("ERROR Could not connect to Databases...Exiting");
//				}
//			}
						
			allreadyInitialised = true;
		}		
	}
	
	
	
	
	/**
	 * FUNCTION [createNewDoc()]:
	 *  - Create a new document based on the transaction type and put it in the queue.
	 */
	public void createNewDoc(String transType)
	{
		log.info("[TransactionCentre] - Created new: "+transType);
		
		/** increment the counter*/
		if(counter == 99)
		{
			counter = 0;
		}
		counter++;
		
				
		if(transType.equalsIgnoreCase("EXPORT"))
		{
			/** read in the template Export document */
			String expTemplatePath = dirList.getConfigValue("PROP_EXPORT_TEMPLATE");

			Document newExpDOM = null;
			
			try
			{
				newExpDOM = xmlInterface.readInXMLFile(expTemplatePath);
			}
			catch(Exception e)
			{
				log.error("[TransactionCentre] Exception reading in Export Template File. "+e.toString());
				return;
			}
			
			if(newExpDOM == null)
			{
				log.error("[TransactionCentre] Exception Newly created Export Doc was null");
				return;
			}
			
			/** fill in the required values */
			tStamp.generateTimeStamp();
			String timeStamp = tStamp.getFormat1();
			
			xmlInterface.setElementValue(newExpDOM.getDocumentElement(), "InvoiceHeader\\InvoiceNumber\\Reference\\RefNum", timeStamp);
			xmlInterface.setElementValue(newExpDOM.getDocumentElement(), "InvoiceHeader\\InvoiceReferences\\SupplierOrderNumber\\Reference\\RefNum", timeStamp);
						
						
			/** create  a new file name */			
			String filePath = dirList.getConfigValue("PROP_DATA_IN_DIR");			
			filePath = filePath + "\\Pro_Forma_Export_Doc_"+timeStamp+"-"+counter+"_.xml";
							
			/** Send the doc to the TR Server Input queue to be processed like any other transaction */
			xmlInterface.writeDomToFile(newExpDOM, filePath);
		}		
	}
	
	        
        private String getShortPartnerName(String partner)
        {
            String ret = "";
            boolean found = false;
            
            if(transResStore == null)
            {
                readInPartnerResolutionFile();
            }
            
            try
            {
                Node root = transResStore.getDocumentElement();
                NodeList partnerNodes = root.getChildNodes();
                
                for(int i = 0; i < partnerNodes.getLength(); i++)
                {
                    Node currPNode = partnerNodes.item(i);
                    
                    String currPartnerId = xmlInterface.getNodeValue(currPNode, "PARTNERID").trim();  
                    
                    if(currPartnerId.equalsIgnoreCase(partner))
                    {
                        ret = xmlInterface.getNodeValue(currPNode, "SHORTNAME").trim();  
                        found = true;
                        break;
                    }                    
                }
            }
            catch(Exception e)
            {
                log.error(e.toString());
            }
            
            if(!found)
            {
                ret = "unknown";
            }
            
            return ret;
        }
        
        
	
	private void readInPartnerResolutionFile()
        {
            try
            {
                transResStore = xmlInterface.readInXMLFile(dirList.getConfigValue("PROP_PARTNER_RES_FILE"));               
            }
            catch(Exception e)
            {
                log.error(e.toString());
            }
        }
	
	/**
	 * FUNCTION [getBody()]:
	 *  - Return the body of the TransactionCentre HTML page.
	 */
//	public Enumeration getBody()
//	{
//		/** clear lists */
//		todoObjects.removeAllElements();
//		COObjects.removeAllElements();
//		list = null;
//
//		/** Get all required config values */
//		TRADEROUTE_QUEUE_DIR = dirList.getConfigValue("PROP_"+transType+"_DIR");
//
//		/** Check for files in the input directory */
//		data_in_dir = new File(TRADEROUTE_QUEUE_DIR);
//
//		if(data_in_dir.isDirectory())
//		{
//			/** get all the files in the input directory */
//			File[] files = data_in_dir.listFiles();
//
//			/** loop through all the files in the input directory */
//			inputDirLoop: for (int i = 0; i < files.length; i++)
//			{
//				/** get the current file */
//				File currFile = files[i];
//
//                                TransactionObject currObj = null;
//
//                                /** use export reader */
//                                if(transType.equalsIgnoreCase("EXPORT"))
//                                {
//                                    //log.info("Using Export Sax Reader...");
//                                    currObj = saxReaderExport.readDocumentValues(currFile.toURI().toString());
//                                }
//                                else
//                                {
//                                    //log.info("Using PO Sax Reader...");
//                                    /** use po reader */
//                                    currObj = saxReaderPO.readDocumentValues(currFile.toURI().toString());
//                                }
//
//                log.info("in TransactionCenter.currObj.getDocID().."+currObj.getDocID());
//                                /** find out if the file is locked or not */
////				String SQL1 ="SELECT * FROM Parent_Transactions INNER JOIN Transaction_Status ON"+
////						"[Parent_Transactions].[DOCKEY]=[Transaction_Status].[DOCKEY] "+
////						"WHERE (([Parent_Transactions].[DocumentCreatorsTransactionID]='"+currObj.getDocID()+"'));";
//				String SQL1 ="SELECT * FROM Parent_Transactions INNER JOIN Transaction_Status ON Parent_Transactions.DOCKEY=Transaction_Status.DOCKEY WHERE Parent_Transactions.DocumentCreatorsTransactionID="+currObj.getDocID();
//				Vector results = dbConn.sendQuery("Trans_DB", SQL1,1);
//
//				/** If a duplicate was found */
//				if(results!=null && results.size() != 0)
//				{
//					QueryResult resData = (QueryResult)results.elementAt(0);
//					currObj.setLockedStatus(resData.getOpenState());
//				}
//
//                                String documentType = "";
//
//                if(currObj.getTransType().equalsIgnoreCase("PurchaseOrder"))
//				{
//					documentType = "PO";
//                                        currObj.setTransType("ORDER");
//					currObj.setFileDescription("Purchase Order Needs To Be Resolved.");
//                                        String transCreator = getShortPartnerName(currObj.getTransCreator());
//                                        currObj.setTransCreator(transCreator);
//				}
//				if(currObj.getTransType().equalsIgnoreCase("ChangeOrder"))
//				{
//					documentType = "CO";
//                                        currObj.setTransType("CHANGEORDER");
//					currObj.setFileDescription("Change Order Needs To Be Resolved.");
//                                        String transCreator = getShortPartnerName(currObj.getTransCreator());
//                                        currObj.setTransCreator(transCreator);
//				}
//				if(currObj.getTransType().equalsIgnoreCase("ExportDocument"))
//				{
//					documentType = "EXP";
//                                        currObj.setTransType("EXPORT");
//					currObj.setFileDescription("Export Document Needs To Be Resolved.");
//				}
//
//
//
//                                /** set the name of the transaction on screen */
//                                String fileID = documentType + " [" + currObj.getDocID() +"] " +currObj.getTransCreator();
//				currObj.setFileID(fileID);
//
//                                /** get a readable date */
//                                String longDate = currObj.getCreationDate();
//
//                                int pos = longDate.indexOf(" ");
//
//                                if(pos != -1)
//                                {
//                                    longDate = longDate.substring(0, pos);
//                                    currObj.setCreationDate(longDate.trim());
//                                }
//
//                                /** fix up the user name field */
//                                String userList = currObj.getUserName();
//
//                                if(userList.equals(""))
//                                {
//                                    currObj.setUserName("None");
//                                }
//                                else
//                                {
//                                    int pos2 = userList.lastIndexOf(",");
//
//                                    if(pos2 != -1)
//                                    {
//                                        currObj.setUserName(userList.substring(pos2+1));
//                                    }
//                                }
//                                //currObj.print();
//
//				/** add CO's to their own list first, and then append */
//				if(documentType.equalsIgnoreCase("CO"))
//				{
//					COObjects.add(currObj);
//				}
//				else
//				{
//					todoObjects.add(currObj);
//				}
//			}
//		}
//
//		/** add all the CO's to the return list */
//		Enumeration COList = COObjects.elements();
//
//		while(COList.hasMoreElements())
//		{
//			TransactionObject currCO = (TransactionObject)COList.nextElement();
//			todoObjects.add(currCO);
//		}
//
//
//		/** return the enumeration */
//		list = todoObjects.elements();
//		return list;
//	}

    String NexusID = "";

    public String getNexusID() {
        return NexusID;
    }

    public void setNexusID(String NexusID) {
        this.NexusID = NexusID;
    }

    private String transKey = "";

    

//    String TransType = "";
//
//    public String getTransType() {
//        return TransType;
//    }
//
//    public void setTransType(String TransType) {
//        this.TransType = TransType;
//    }

  /*  public String getTransaction() {
        TransObjects.clear();
        String transId = null;
        try{
        //dbCommunicatorImpl=DbCommunicatorImpl.getInstance("C:\\TradeRoute\\config_files\\edxconfig.xml");
        //transId=dbCommunicatorImpl.getTransactionId(transKey);
          transId=dataResolutionService.getTransactionId(transKey);
        }catch(Exception e){
            e.printStackTrace();
        }
        return transId;
    }

public String getTransactionType() {
        TransObjects.clear();
        String transType = null;
        try{
        //dbCommunicatorImpl=DbCommunicatorImpl.getInstance("C:\\TradeRoute\\config_files\\edxconfig.xml");
        //transType=dbCommunicatorImpl.getTransactionType(transKey);
        transType=dataResolutionService.getTransactionType(transKey);
        }catch(Exception e){
            e.printStackTrace();
        }
        return transType;
    } */

    public Enumeration getBody()
	{
		/** clear lists */
		todoObjects.removeAllElements();
		COObjects.removeAllElements();
		list = null;
        //dbCommunicatorImpl=DbCommunicatorImpl.getInstance("C:\\TradeRoute\\config_files\\edxconfig.xml");


			    TransactionObject currObj = null;

               // log.info("Nexus Id = " + NexusID);

                //String SQL1 ="SELECT * FROM TR_Transactions INNER JOIN TR_TransactionsExt ON TR_Transactions.TransactionId LIKE TR_TransactionsExt.TransactionId WHERE ((TR_Transactions.TransactionType LIKE 'Order'))";
                //String SQL1 ="SELECT * FROM TR_Transactions INNER JOIN TR_TransactionsExt ON TR_Transactions.TransactionId LIKE TR_TransactionsExt.TransactionId where TR_Transactions.TransactionState LIKE 'user' AND (TR_Transactions.Hidden NOT LIKE 'true') AND (TR_Transactions.TransactionType = 'Order' OR TR_Transactions.TransactionType = 'ChangeOrder')";
                //String SQL1 ="SELECT * FROM TR_Transactions INNER JOIN TR_TransactionsExt ON TR_Transactions.TransactionId LIKE TR_TransactionsExt.TransactionId where TR_Transactions.TransactionState LIKE 'user' AND (TR_Transactions.Hidden NOT LIKE 'true') AND (TR_Transactions.TransactionType = 'Order' OR TR_Transactions.TransactionType = 'ChangeOrder') and sup_nexus_id = '"+NexusID+"'";
                //log.info("SQL1 = " + SQL1);
				//Vector results = dbConn.sendQuery("Trans_DB", SQL1,1);
                //Vector results = dbCommunicatorImpl.getTransactions(NexusID);
                Vector results = dataResolutionService.getTransactions(NexusID);
                //log.info("results size in getbody..."+results.size());
				/** If a duplicate was found */
				if(results!=null && results.size() != 0)
				{
                    for(int j=0;j<results.size();j++)
                    {
                        currObj=new TransactionObject();
                        QueryResult resData = (QueryResult)results.elementAt(j);
                        currObj.setLockedStatus(resData.getOpenState());
                        currObj.setTransType(resData.getDocumentType());
                        currObj.setFileDescription("Purchase Order Needs To Be Resolved.");
                        currObj.setDocID(resData.getTransactionNumber());
                        currObj.setFileID(resData.getDocumentCreatorsTransactionID());
                          currObj.setCreationDate(resData.getCreationDate().toString());
                          currObj.setUserName(resData.getUserID());
                          currObj.setPartnerName(resData.getPartnerName());
                          currObj.setTradeFormApp("Trans_Disp_FS");
                          todoObjects.add(currObj);
                    }

				}

		/** return the enumeration */
		list = todoObjects.elements();
		return list;
	}
	
	
	
	
	
//	public int getNextKey()
//	{
//		Vector parentKeyResults = dbConn.sendQuery("Error_DB", Get_Max_Key_Query, 1);
//		/** no values in the table */
//		if(parentKeyResults.size() == 0)
//		{
//			return 1;
//		}
//		DbQueryErrorLogResult parentKeyData = (DbQueryErrorLogResult)parentKeyResults.elementAt(0);
//    	int currLPK = parentKeyData.getKey();
//
//    	currLPK++;
//    	return currLPK;
//	}
	
	
	
	

	/**
	 * FUNCTION [main()]:
	 *  - Only used for debugging.
	 */
	public static void main(String[] args)
	{
		TransactionCentre todoList1 = new TransactionCentre();
		todoList1.setConfigFileLocation("C:\\TradeRoute\\config_files\\edxconfig.xml");
                todoList1.setLogPropFileLocation("C:\\TradeRoute\\config_files\\logging.properties");
		todoList1.initialise();
		todoList1.setTransType("ORDER");
		
		Enumeration ret = todoList1.getBody();
		
		while(ret.hasMoreElements())
		{
			TransactionObject tro = (TransactionObject)ret.nextElement();
			tro.print();
		}
		
		//log.info("configFileLoc: "+todoList1.getConfigFileLocation());
		
		//log.info("error text: "+todoList1.getErrorText());
	}

    /**
     * @return the transKey
     */
    public String getTransKey() {
        return transKey;
    }

    /**
     * @param transKey the transKey to set
     */
    public void setTransKey(String transKey) {
        this.transKey = transKey;
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
}