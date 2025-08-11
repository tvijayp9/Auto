package com.nexus.util.DR.commonsource;

// Imported TraX classes
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;


// Imported java classes
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import org.w3c.dom.*;

import com.nexus.util.DR.misc.*;
import org.apache.log4j.Logger;


public class XSLTFunctions
{

     Logger log=Logger.getLogger(XSLTFunctions.class);
	/** Directory list read in from config file */
	private ConfigValuesList dirList = null;

	/** variable to hold xmlFunctions object */
	private XMLFunctions xmlInterface = null;
	
	/** counter */
	private int counter = 0;
	
	/** TimeStamp variable */
	private TimeStamp tStamp = new TimeStamp();
	
	
	
	

	/**
	 * FUNCTION [setDirectoryList()]:
	 *  - setDirectoryList: set the vector object
	 */
	public void setDirectoryList(ConfigValuesList directoryList)
	{
		dirList = directoryList;	
		xmlInterface = new XMLFunctions();			
	}


	/**
	 * FUNCTION [applyStylesheetToDocument()]:
	 *  - createResponseDocument: return the path to the created document.
	 */
	public String applyStylesheetToDocument(String styleSheetPath, String transPath, String resultPath)
	{		
		try
		{			
			transformDOM(styleSheetPath, transPath, resultPath);
		}
		catch(Exception e)
		{
			resultPath = "";
			log.info("[XSLTFunctions] -applyStylesheetToDocument- XSL Exception: ");
			e.printStackTrace();
		}
		
		return resultPath;
	}


	/**
	 * FUNCTION [createResponseDocument()]:
	 *  - createResponseDocument: return the path to the created response document.
	 */
	public String createResponseDocument(String transType, String transPath, String responseType)
	{
		File transFile = new File(transPath);
		
		Document responseDOM = null;
		Document transDOM = null;
		
		if(!transFile.exists())
		{
			return "";
		}
		
		/** read in the dom */
		try
		{
			transDOM = xmlInterface.readInXMLFile(transFile.getAbsolutePath());		
		}
		catch(Exception e)
		{
			log.info("[XSLTFunctions] - Couldn't read in file: "+transPath);
			return "";
		}
		
		/** get the required paths */
		String schemaPath = dirList.getConfigValue("PROP_ORDER_RESPONSE_SCHEMA_LOCATION");	
		String responsePath = dirList.getConfigValue("PROP_TRS_TEMP_DIR") + "\\";
		String styleSheetPath = "";			
		String responseFile = "";
		
		/** get the transaction's ID */
		Node root = transDOM.getDocumentElement();
		Node DOCDATA = xmlInterface.getNode(root, "DOCDATA");
		String docID = xmlInterface.getAttributeValue(DOCDATA, "DOCID");
		
		if(docID == null)
		{
			docID = "00000";
		}
		
		/** used to make transaction unique */
		if(counter == 99)
		{
			counter = 0;
		}
		counter++;
		
		/** is it a PO or CO */
		if(transType.equalsIgnoreCase("PurchaseOrder"))
		{					
			styleSheetPath = dirList.getConfigValue("PROP_PO2RESPONSE_STYLESHEET");
			responseFile = dirList.getConfigValue("PROP_ORDER_RESPONSE_PREFIX");
		}
		else
		{
			styleSheetPath = dirList.getConfigValue("PROP_CO2RESPONSE_STYLESHEET");
			responseFile = dirList.getConfigValue("PROP_CHANGEORDER_RESPONSE_PREFIX");
		}
		
		/** create the name of the response file */
		tStamp.generateTimeStamp();
		String uniqueKey = tStamp.getFormat1();
		String creationDate = tStamp.getFormat2();		
		Integer count = new Integer(counter);
		String timeStamp = uniqueKey + "-" + count.toString();		
		
		responseFile = responseFile + "_" + docID + "_" +timeStamp + ".xml";
		responsePath = responsePath + responseFile;
		
		/** create a response using a stylesheet */
		/*log.info("Generating response with: ");
		log.info("xslPath: " + styleSheetPath);
		log.info("xmlPath: " + transPath);
		log.info("schemaPath: " + schemaPath);
		log.info("response path: " + responsePath);*/
				
		try
		{			
			transformDOM(styleSheetPath, transPath, responsePath);
		}
		catch(Exception e)
		{
			responsePath = "";
			log.info("[XSLTFunctions] -createResponseDocument- XSL Exception: ");
			e.printStackTrace();
		}
		
		
		File respFile = new File(responsePath);
						
		if(respFile.exists())
		{
			/** read in the response file and add the required values to it */
			try
			{
				responseDOM = xmlInterface.readInXMLFile(respFile.getAbsolutePath());
				responseDOM = removeAttribs(responseDOM);
				
				Document updatedDOM = null;				
				
				if(responseType.equalsIgnoreCase("reject"))
				{
					updatedDOM = addRejectValuesToResponse(responseDOM);
				}
				else if(responseType.equalsIgnoreCase("accept"))
				{
					updatedDOM = addAcceptValuesToResponse(responseDOM);									
				}	
				
				/** write out the updated dom */
				xmlInterface.writeDomToFile(updatedDOM, responsePath);		
			}
			catch(Exception e)
			{
				log.info("[XSLTFunctions] - Couldn't read in response file: "+responsePath);
				responsePath = "";
			}
		}
		else
		{
			log.info("[XSLTFunctions] - Couldn't create response file for: "+transPath+", please send it Manually.");
			String errorDir = dirList.getConfigValue("PROP_DATA_ERR_DIR");
			String errorPath = errorDir + "\\" + transFile.getName();
			xmlInterface.writeDomToFile(transDOM, errorPath);
			responsePath = "";
		}			
		
		return responsePath;
	}



   /** remove the required attributes from the transaction */
   public Document removeAttribs(Document transDOM)
   {
   		/** get the root node */
   		Element root = transDOM.getDocumentElement();   		   		
   		NamedNodeMap attribs = root.getAttributes();
   		
   		attribs.removeNamedItem("xmlns");
   		attribs.removeNamedItem("xmlns:xsi");
   		attribs.removeNamedItem("xsi:schemaLocation");
   		
   		return  transDOM;		
   }
	
	
	



	private Document addAcceptValuesToResponse(Document respDOM)
	{
		Node root = respDOM.getDocumentElement();
		
		/** set the ResponseTypeCoded to rejected */
		xmlInterface.setElementValue(root, "OrderResponseHeader\\ResponseType\\ResponseTypeCoded", "Accepted");
			
		/** set SellerOrderResponseNumber */
		String uniqueKey = tStamp.getFormat1();		
		Integer count = new Integer(counter);
		String orderRespNum = uniqueKey + "-" + count.toString();
		xmlInterface.setElementValue(root, "OrderResponseHeader\\OrderResponseNumber\\SellerOrderResponseNumber", orderRespNum);
				
		/** set OrderResponseIssueDate */
		String creationDate = tStamp.getXCBLFormat();
		xmlInterface.setElementValue(root, "OrderResponseHeader\\OrderResponseIssueDate", creationDate);
				
		return respDOM;
	}
	
	
	private Document addRejectValuesToResponse(Document respDOM)
	{
		Node root = respDOM.getDocumentElement();
		
		/** set the ResponseTypeCoded to rejected */
		xmlInterface.setElementValue(root, "OrderResponseHeader\\ResponseType\\ResponseTypeCoded", "Rejected");
			
		/** set SellerOrderResponseNumber */
		String uniqueKey = tStamp.getFormat1();		
		Integer count = new Integer(counter);
		String orderRespNum = uniqueKey + "-" + count.toString();
		xmlInterface.setElementValue(root, "OrderResponseHeader\\OrderResponseNumber\\SellerOrderResponseNumber", orderRespNum);
				
		/** set OrderResponseIssueDate */
		String creationDate = tStamp.getXCBLFormat();
		xmlInterface.setElementValue(root, "OrderResponseHeader\\OrderResponseIssueDate", creationDate);
				
		return respDOM;
	}










	public void transformDOM(String xslPath, String xmlPath, String outputPath)throws TransformerException, TransformerConfigurationException, 
           FileNotFoundException, IOException
	{
		
		File styleSheet = new File(xslPath);
		File xmlFile = new File(xmlPath);
		
		if(styleSheet.exists() && xmlFile.exists())
		{			
			// Use the static TransformerFactory.newInstance() method to instantiate 
		  	// a TransformerFactory. The javax.xml.transform.TransformerFactory 
		  	// system property setting determines the actual class to instantiate --
		  	// org.apache.xalan.transformer.TransformerImpl.
			TransformerFactory tFactory = TransformerFactory.newInstance();
			
			
			// Use the TransformerFactory to instantiate a Transformer that will work with  
			// the stylesheet you specify. This method call also processes the stylesheet
		  	// into a compiled Templates object.
			Transformer transformer = tFactory.newTransformer(new StreamSource(styleSheet));
			
			//transformer.setParameter("rootNode", "//CCC");
			// Use the Transformer to apply the associated Templates object to an XML document
			// (foo.xml) and write the output to a file (foo.out).
			FileOutputStream fOut = new FileOutputStream(outputPath);
			
			transformer.transform(new StreamSource(xmlFile), new StreamResult(fOut));
			
			fOut.close();
		}
		else
		{
			log.info("StyleSheet or XML Doesn't exist...");
		}
		
		styleSheet = null;
		xmlFile = null;
			
	
	}

	public static void main(String[] args)
	{
		XSLTFunctions xfn = new XSLTFunctions();
		
		try{
			
			String styleSheet = "C:/DEV_PROJECTS/NEW_Resolver/TRS_BuildBableNodes.xsl";			
			String xmlFile = "C:/DEV_PROJECTS/NEW_Resolver/TEST_XML_FILE_UNBABLEISED.xml";
			String respFile = "C:/DEV_PROJECTS/NEW_Resolver/TEST_XML_FILE_BABLEISED.xml";
			
			//String xmlFile = "C:\\Temp\\Stuff\\inputPOB2BE.xml";
			//String respFile = "C:\\Temp\\Stuff\\outputPOB2BE.xml";
			
			//String xmlFile = "C:\\Temp\\Stuff\\inputPONEWMONT.xml";
			//String respFile = "C:\\Temp\\Stuff\\outputPONEWMONT.xml";
			long start = System.currentTimeMillis();
			System.out.println("transforming...");
			xfn.transformDOM(styleSheet, xmlFile, respFile);
			
			System.out.println("done...");
			long end = System.currentTimeMillis();
                 System.out.println("Time Taken: "+(end-start));
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION: "+ e.getMessage());
			e.printStackTrace();
		}
		
	}
}

