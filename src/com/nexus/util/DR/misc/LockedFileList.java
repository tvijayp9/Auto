package com.nexus.util.DR.misc;

import org.w3c.dom.*;
import com.nexus.util.DR.commonsource.*;
import org.apache.log4j.Logger;

public class LockedFileList
{
    Logger log=Logger.getLogger(LockedFileList.class);
	/** variable to hold path of locked file list */
	private static String lockedFilePath = "";
	
	private static XMLFunctions xmlInterface = null;
	
	private static BableEngine bableEngine = null;
	
	/**
	 * FUNCTION [LockedFileList()]:
	 *  - Constructor.
	 */
	public LockedFileList(String lockedFile)
	{
		/** set locked file path */
		lockedFilePath = lockedFile;			
		xmlInterface = new XMLFunctions();	
		bableEngine = new BableEngine();
	}
	
	
	/**
	 * FUNCTION [initialise()]:
	 *  - initialise variables.
	 */
	public void initialise()
	{
		/** setup variables */
		bableEngine = null;
		xmlInterface = null;
		bableEngine = new BableEngine();
		xmlInterface = new XMLFunctions();
	}
	
	
	
	/**
	 * FUNCTION [isFileLocked()]:
	 *  - Return the status of the current file.
	 */
	public boolean isFileLocked(String fileName)
	{	
		initialise();
		
		boolean ret = false;
		
		Document lockedFile = null;
		try{	
			lockedFile = xmlInterface.readInXMLFile(lockedFilePath);
		}
		catch(Exception e)
		{
			log.info("Locked File List: Could Not Load Locked File: "+e.getMessage());
		}
		
		if(lockedFile != null)
		{
		
			NodeList nl = lockedFile.getElementsByTagName("FILENAME");
			
			for(int i = 0; i < nl.getLength(); i++)
			{
				Node currNode = nl.item(i);
				
				if(currNode.hasChildNodes())
				{
					String currFileName = currNode.getFirstChild().getNodeValue();
					
					/** add current file name to the global vector */
					if(currFileName.equalsIgnoreCase(fileName))
					{
						ret = true;
					}
				}
			}
		}
		
		return ret;						
	}
	
	
	
	/**
	 * FUNCTION [setFileAsLocked()]:
	 *  - add the current file to the locked file list.
	 */
	public void setFileAsLocked(String fileName)
	{
		initialise();
		
		Document lockedFile = null;
		try{	
			lockedFile = xmlInterface.readInXMLFile(lockedFilePath);
		}
		catch(Exception e)
		{
			log.info("Locked File List: Could Not Load Locked File: "+e.getMessage());
		}
		
		if(lockedFile != null)
		{
		
			NodeList nl = lockedFile.getElementsByTagName("LOCKEDFILELIST");
			
			/** get the LOCKEDFILELIST node */
			Node LFL = nl.item(0);
			
			/** check that the filename is not allready in the list */
			NodeList allFILENAMES = LFL.getChildNodes();
			boolean allreadyInThere = false;
			
			for(int i = 0; i < allFILENAMES.getLength(); i++)
			{
				Node currFILENAME = allFILENAMES.item(i);
				
				if(currFILENAME.getNodeType() == Node.ELEMENT_NODE)
				{					
					if(currFILENAME.getFirstChild().getNodeValue().equalsIgnoreCase(fileName))
					{						
						allreadyInThere = true;
					}					
				}
			}
			
			if(!allreadyInThere)
			{
				/** create a FILENAME Node */
				log.info("");
				Element FILENAME = lockedFile.createElement("FILENAME");
				FILENAME.appendChild(lockedFile.createTextNode(fileName));
				
				LFL.appendChild(FILENAME);
				
				xmlInterface.writeDomToFile(lockedFile, lockedFilePath);
			}
		}
	}
	
	/**
	 * FUNCTION [removeFile()]:
	 *  - remove the current file from the locked file list.
	 */
	public void removeFile(String fileName)
	{
		initialise();
		
		Document lockedFile = null;
		try{	
			lockedFile = xmlInterface.readInXMLFile(lockedFilePath);
		}
		catch(Exception e)
		{
			log.info("Locked File List: Could Not Load Locked File: "+e.getMessage());
		}
		
		if(lockedFile != null)
		{
		
			NodeList nl = lockedFile.getElementsByTagName("FILENAME");
			
			for(int i = 0; i < nl.getLength(); i++)
			{
				Node currNode = nl.item(i);
				
				if(currNode.hasChildNodes())
				{
					String currFileName = currNode.getFirstChild().getNodeValue();
					
					/** add current file name to the global vector */
					if(currFileName.equalsIgnoreCase(fileName))
					{
						/** remove the Node */
						Node parentNode = currNode.getParentNode();
						
						parentNode.removeChild(currNode);
						
						xmlInterface.writeDomToFile(lockedFile, lockedFilePath);
					}
				}
			}
		}
	}
	
	
	
	/**
	 * FUNCTION [main()]:
	 *  - Only used for debugging.
	 */
	public static void main(String[] args)
	{
		/*LockedFileList lfl = new LockedFileList("C:\\OSCAR\\JavaProjects\\TradeRoute\\LockedFiles.xml");
		
		if(lfl.isFileLocked("OSCAAR.xml"))
		{
			log.info("it's locked");
		}
		else
		{
			log.info("it's not locked");
		}*/
		
	}

}

