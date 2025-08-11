package com.nexus.util.DR.traderoute;

/**
 * @author Oscar Pfohl
 *
 * Class: DatabaseFunctions.java
 * 
 * Date: 8/07/2003
 * 
 * Purpose:
 * 
 */

//import server.accessdbcomm.*;
//import server.accesserrorlogdbcomm.*;
//import server.commonsource.*;
import java.io.*;
import com.nexus.util.DR.misc.ConfigValuesList;
import org.apache.log4j.Logger;

public class DatabaseFunctions {

    Logger log=Logger.getLogger(DatabaseFunctions.class);
	/** connection to the error data base */
	//private DatabaseInterface dbConn = null;
	
	/** Directory list read in from config file */
	public ConfigValuesList dirList;
	
	boolean allreadyInitialised = false;
	
	private String errorText = "";
	
	private int numberOfRetrys = 0;
	
	/** Variables to hold the values from the config file */
	private static String configFileLocation = "";
	
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
	public void setConfigFileLocation( String val)
	{
		configFileLocation = val;
	}
	
	/** return the error text string */
	public String getErrorText()
	{
		return errorText;
	}
	
	
	public void setNumOfRetrys(int val)
	{
		numberOfRetrys = val;
	}
	
	/**
	 * FUNCTION [initialise()]:
	 *  - initialise variables.
	 */
	public void initialise()
	{
		if(numberOfRetrys == 0)
		{
			numberOfRetrys = 5;
		}
		
		if(!allreadyInitialised)
		{
			/** setup configList */
			dirList = ConfigValuesList.getInstance("DatabaseFunctions", "TRADEROUTE");
			dirList.initialise(configFileLocation);
			
			
			log.info("[DatabaseFunctions] - Initialising...");
			
			/** connect to the database every time this function is called */
//			dbConn = DatabaseInterface.getInstance();
//
//			if(!dbConn.isConnected())
//			{
//				/** setup the errorDBConnection */
//				if(!dbConn.connectToDatabases(configFileLocation))
//				{
//					log.info("[DatabaseFunctions]:ERROR Could not connect to Databases...Exiting");
//				}
//			}
						
			allreadyInitialised = true;
		}		
	}
	
	/** unlock the specified transaction in the database */
//	public void unlockTransaction(String transType, String transID)
//	{
//		boolean worked = false;
//
//
//		for(int i = 0; i < numberOfRetrys; i++)
//		{
//			log.info("[DatabaseFunctions] Trying To Unlock File ("+transID+") Try:("+i+"). ");
//
//			if(worked)
//			{
//				errorText = "Transaction was unlocked successfully.";
//				break;
//			}
//
//			try
//			{
//
//
//				/** find the docID of this transaction */
//
//				String SQL1 ="SELECT * FROM Parent_Transactions INNER JOIN Transaction_Status ON"+
//						"[Parent_Transactions].[DOCKEY]=[Transaction_Status].[DOCKEY] "+
//						"WHERE (([Parent_Transactions].[DocumentCreatorsTransactionID]='"+transID+"'));";
//
//				//String SQL1 ="SELECT * FROM `Parent_Transactions` WHERE DocumentCreatorsTransactionID='"+transID+"'";
//
//				Vector results = dbConn.sendQuery("Trans_DB", SQL1,1);
//
//				/** If a duplicate was found */
//				if(results.size() != 0)
//				{
//					/** found the transaction */
//					QueryResult parentData = (QueryResult)results.elementAt(0);
//
//					String docKey = parentData.getDOCKEY();
//
////					log.info("[DatabaseFunctions] Found Transaction, DocKey:  ("+docKey+"). ");
////					log.info("***************** FIRST QUERY *****************\n");
////					parentData.print();
//
//						for(int j = 0; j < results.size(); j++)
//						{
//
//							parentData.setOpenState("Open");
//
//							/** send the update */
//							int res = dbConn.sendUpdate(22,3, parentData);
//
//							if(res == -1)
//							{
//								/** didn't work */
//								//log.info("[DatabaseFunctions] Updated Transaction, Didn't Work.... ");
//								errorText = "Transaction could not be unlocked, try again in a few minutes.";
//							}
//							else
//							{
//								/** worked */
//								//log.info("[DatabaseFunctions] Updated Transaction, Worked.... ");
//								errorText = "Transaction ("+transID+") has been unlocked.";
//								break;
//							}
//
//						}
//
//				}
//				else
//				{
//					errorText = "Transaction ("+transID+") could not be found in the database.";
//				}
//
//			}
//			catch(Exception e)
//			{
//				log.info("[DatabaseFunctions] unlockTransaction Exception: ");
//				e.printStackTrace();
//			}
//		}
//	}
	
	
	public boolean deleteTransaction(String transType, String transID)
	{
		boolean ret = false;
		
		if(transID.equals(""))
		{
			errorText = "Please enter a transaction number.";
			return false;
		}
		
		/** find the transaction with this ID */
		
		String PO_Dir = "";
		
		if(transType.equalsIgnoreCase("PO"))
		{
			PO_Dir = dirList.getConfigValue("PROP_ORDER_DIR");
		}
		else
		{
			PO_Dir = dirList.getConfigValue("PROP_EXPORT_DIR");
		}
		
		String archive_DIR = dirList.getConfigValue("PROP_DYNEX_ARCHIVING_DIR");
		
		File po_directory = new File(PO_Dir);
		
		if(po_directory.exists() && po_directory.isDirectory())
		{
			File[] fileList = po_directory.listFiles();
			
			boolean found = false;
			
			
			for(int i = 0; i < fileList.length; i++)
			{
				File currFile = fileList[i];
				
				if(currFile.getName().indexOf(transID) != -1)
				{
					/** found the right file */
					try
					{
						copyFile(currFile.getAbsolutePath(), archive_DIR +"\\"+currFile.getName());
												
						currFile.delete();
						
						errorText = "File ("+currFile.getName()+") has been deleted.";
						found = true;
						break;
					}
					catch(Exception e)
					{
						log.info("[deleteTransaction] Exception deleting file..."+currFile.getName());
						//e.printStackTrace();
						errorText = "File ("+currFile.getName()+") could not bee deleted, please try in a few minutes.";
					}
				}				
			}
			
			if(!found)
			{
				errorText = "Could not find transaction number ("+transID+").";
			}						
		}
		
		return ret;
	} 


	/**
	 * FUNCTION [copyFile()]:
	 *  - Copy one file to another.
	 */
	public void copyFile(String origPath, String destPath) throws Exception
	{
		File inputFile = new File(origPath);
		File outputFile = new File(destPath);
		
		FileReader in = new FileReader(inputFile);
		FileWriter out = new FileWriter(outputFile);
				
		int c;
		
		while((c = in.read()) != -1)
		{
			out.write(c);
		}
		
		in.close();
		out.close();
		inputFile = null;
		outputFile = null;		
	}



//	public static void main(String[] args)
//	{
//
//		DatabaseFunctions dbf = new DatabaseFunctions();
//
//		dbf.setConfigFileLocation("C:\\dynex\\edxconfig.xml");
//		dbf.initialise();
//
//		dbf.unlockTransaction("PO", "962683");
//
//	}
}
