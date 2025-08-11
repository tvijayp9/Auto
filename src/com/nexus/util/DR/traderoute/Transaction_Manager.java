package com.nexus.util.DR.traderoute;
import com.nexus.services.DataResolutionService;
import com.nexus.services.DataResolutionServiceImpl;
import java.util.Vector;
import java.util.Enumeration;
import com.nexus.util.DR.misc.*;
import org.apache.log4j.Logger;

/**
 * Class [Transaction_Manager]
 * Deals with maintaining instances of Trans_Disp_Bean objects. A new instance is created
 * each time a transaction is opened from the Transactoin Centre JSP page 
 */
public class Transaction_Manager {

    Logger log=Logger.getLogger(Transaction_Manager.class);
	/*************************************** VARIABLES ***************************************/
	
	/** Vector to hold list of Trans_Disp_Bean instances */
	private Vector openTransList = new Vector();
     private DataResolutionService dataResolutionService=new DataResolutionServiceImpl();
    private String treeOutput;
	
	/** hold the config file location */
	//private String configFileLocation = "C:\\TradeRoute\\config_files\\edxconfig.xml";
    //private String configFileLocation = "\\map\\config_files\\edxconfig.xml";
   // private String  configFileLocation=System.getProperty("catalina.base")+"\\webapps\\Auto\\map\\config_files\\edxconfig.xml";
	//private String configFileLocation="/home/xmlyes/tomcat/Applications/Auto/map/config_files/edxconfig.xml";
    private String configFileLocation=dataResolutionService.getTradeRouteFolderPath("TradeRouteConfig");
	/************************************** GENERAL FUNCTIONS **********************************/
	
	
	/**
	 * FUNCTION [setCounter()]:
	 *  - used for debugging in JSP pages.
	 */
	public void setCounter(int val)
	{
		log.info("Set Counter to: "+val);
	}
	
	
	/**
	 * FUNCTION [echoString()]:
	 *  - used for debugging in JSP pages.
	 */
	public void echoString(String val)
	{
		log.info("Echo: "+val);
	}
	
	
	/**
	 * FUNCTION [getErrorText()]:
	 *  - return the errorText property.
	 */
	public String getErrorText(String fName)
	{				
		String errorText = "";
		/** find the right object to work with */
		Trans_Disp_Bean currObj = getObjectFromList(fName);
		if(currObj != null)
		{
			errorText = currObj.getErrorText();
		}
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
	 * FUNCTION [printList()]:
	 *  - print out the contents of the openTransList
	 */
	public void printList()
	{
		log.info("[Transaction_Manager] ---------- Open Transactions List -----------");
		/** get the list of objects */
		Enumeration list = openTransList.elements();
		while(list.hasMoreElements())
		{
			Trans_Disp_Bean currObj = (Trans_Disp_Bean)list.nextElement();
			log.info("[Transaction_Manager] - Current Obj:File Name: "+currObj.getFileName());
			currObj = null;
		}
	}
	
	
	
	/************************************ CLASS SPECIFIC FUNCTIONS ******************************/
	
	/**
	 * FUNCTION [createNewDisplayObject()]:
	 *  - Creates a new instance of the Trans_Disp_Bean object with the passed in Id
	 */
	public Trans_Disp_Bean createNewDisplayObject(String fileName, String transType, String uName, String uType)
	{
		String ret = "";
        Trans_Disp_Bean currObj = null;
		//Trans_Disp_Bean currObj = getObjectFromList(fileName);
		/** if it doesn't allready exist */
		//if(currObj == null)
		//{
			log.info("\n[Transaction_Manager] Creating New Trans_Disp_Bean: "+fileName);
			/** create the new object */
			currObj = new Trans_Disp_Bean();
			/** set all the required variables */
			currObj.setConfigFileLocation(configFileLocation);
			currObj.setFileName(fileName);
			currObj.setTransType(transType);
			currObj.setUserName(uName);
			currObj.setUserType(uType);
			currObj.setLastFileInConversation(fileName);
			/** initialise the object */
			currObj.initialise();
			/** process the object's transaction file */
			treeOutput=currObj.processTransaction(fileName);
            currObj.setTreeOutput(treeOutput);
			/** add the new object to the list */
			openTransList.add(currObj);
			log.info("[Transaction_Manager] Finished Creating New Trans_Disp_Bean: "+fileName);
                        log.info("[Transaction_Manager] User Name: "+uName);
		//}
		
		return currObj;
	} 
	
	/**
	 * FUNCTION [removeDisplayObject()]:
	 *  - removes a instance of the Trans_Disp_Bean object with the passed in Id
	 */
	public void removeDisplayObject(String fileName)
	{		
		/** find the right object to work with */
		Trans_Disp_Bean currObj = getObjectFromList(fileName);
		if(currObj != null)
		{
			log.info("[Transaction_Manager] Removing Trans_Disp_Bean: "+fileName);
			openTransList.removeElement(currObj);
		}
	} 
	
	/**
	 * FUNCTION [escapeString()]:
	 *  - used to escape strings so they can be displayed in web pages
	 */
	public String escapeString(String val)
	{
		val = val.replaceAll("\"", "&quot;");
		val = val.replaceAll("<", "&lt;");
		val = val.replaceAll(">", "&gt;");
		val = val.replaceAll("'", "&acute;");
		val = val.replaceAll("\n", "");
		
		return val.trim();
	}
	
	/**
	 * FUNCTION [getObjectFromList()]:
	 *  - get the object with the passed in name from the list
	 */
	public Trans_Disp_Bean getObjectFromList(String fileName)
	{
        log.info("inside getObjectFromList...fileName="+fileName);
		Trans_Disp_Bean ret = null;
		/** find the right object to return */
		Enumeration list = openTransList.elements();
        log.info("inside getObjectFromList...list="+list.toString());
		while(list.hasMoreElements())
		{
             log.info("inside getObjectFromList...inside while loop ..");
			Trans_Disp_Bean currObj = (Trans_Disp_Bean)list.nextElement();
			String currFileName = currObj.getLastFileInConversation();
			/** found the right one */
			if(currFileName.equalsIgnoreCase(fileName))
			{
				ret = currObj;
				break;
			}
		}
        return ret;
	}
	
	/******************************* INTERFACE FUNCTIONS ************************************/

	/**
	 * FUNCTION [saveUpdateObjects()]:
	 *  - saves the passed in list to the correct object's DOM.
	 *  - defined here because we need to catch buttonClick events such as cancel.
	 */
	public void saveUpdateObjects(Trans_Disp_Bean currObj,org.w3c.dom.Document document,Enumeration pageBody,String fName, Vector retList,String id,String reason,String supNexusId)
	{		
		log.info("\n[Transaction_Manager] saveUpdateObjects in..fname..."+fName+"..retList size..."+retList.size()+"...id="+id+"...reason="+reason+"....supNexusId=="+supNexusId);
		/** find the right object to work with & save the objects */
		//Trans_Disp_Bean currObj = getObjectFromList(fName);
		currObj.saveUpdateObjects(document,currObj.getDocumentType(),pageBody,fName,retList,id,reason,supNexusId);
					
		/** do we need to remove the current instance from the list */
		Enumeration updateObjs = retList.elements();
		
		while(updateObjs.hasMoreElements())
		{
			/** get the current update object */
			UpdateObject currUpObj = (UpdateObject) updateObjs.nextElement();			
			
			/** get the name of the current update object */
			String currObjName = currUpObj.getParentName();	
			
			/** if this is the "buttonClicked" object */
			if(currUpObj.getParentName().equalsIgnoreCase("buttonClicked"))
			{	
				//log.info("\n[Transaction_Manager] buttonClicked in...");
				//log.info("\n[Transaction_Manager] currUpObj: \n"+currUpObj.print());
				String buttonClicked = currUpObj.getNewValue();
				buttonClicked = buttonClicked.trim();
				
				/** if it's cancel, then remove from list */
				if(buttonClicked.equalsIgnoreCase("cancel"))
				{
					//log.info("\n[Transaction_Manager] buttonClicked cancel...");
					/** cancel the document */
					removeDisplayObject(fName);
					break;					
				}
				
				/** if it's save, then remove from list */
				if(buttonClicked.equalsIgnoreCase("save"))
				{
					//log.info("\n[Transaction_Manager] buttonClicked cancel...");
					/** cancel the document */
					removeDisplayObject(fName);
					break;					
				}
				
				/** if it's reject, then remove from list */
				if(buttonClicked.equalsIgnoreCase("reject"))
				{
					//log.info("\n[Transaction_Manager] buttonClicked cancel...");
					/** cancel the document */
					removeDisplayObject(fName);
					break;					
				}
				
				/** if it's reject, then remove from list */
				if(buttonClicked.equalsIgnoreCase("accept"))
				{
					//log.info("\n[Transaction_Manager] buttonClicked cancel...");
					/** cancel the document */
					removeDisplayObject(fName);
					break;					
				}
                if(buttonClicked.equalsIgnoreCase("comment"))
				{
					//log.info("\n[Transaction_Manager] buttonClicked cancel...");
					/** cancel the document */
					removeDisplayObject(fName);
					break;
				}
			}
		}
	}
	
	
	
	/**
	 * FUNCTION [main()]:
	 *  - used for debugging.
	 */
	public static void main(String[] args) 
	{
		Transaction_Manager tm = new Transaction_Manager();
		tm.setConfigFileLocation("C:\\Dynex\\edxconfig.xml");
		/*tm.createNewDisplayObject("aaa.xml");
		tm.createNewDisplayObject("bbb.xml");
		tm.createNewDisplayObject("ccc.xml");
		tm.createNewDisplayObject("ddd.xml");*/
		tm.printList();
		tm.removeDisplayObject("ccc.xml");
		tm.printList();
		
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
}

