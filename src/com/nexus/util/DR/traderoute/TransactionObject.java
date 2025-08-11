package com.nexus.util.DR.traderoute;

import org.apache.log4j.Logger;

/**
* Title:        TradeRouteServer
* Description:
* Copyright:    Copyright (c) 2002
* Company:      XMLYES
* @author Oscar Pfohl
* @version 1.0
*/

public class TransactionObject
{
    Logger log=Logger.getLogger(TransactionObject.class);
	public String fileID;
    public String docID;
	public String fileName;
	public String fileDescription;
	public String fileStatus;
	public String tradeFormApp;
	public String transType;
	public String lockedStatus;
	public String creationDate;
	public String transCreator;
        public String userName;
        public String generalNote;
        private String partnerName;
	public TransactionObject()
	{

		fileID = "";
                docID = "";
		fileDescription = "";
		fileStatus = "";
		tradeFormApp = "";
		transType = "";
		lockedStatus = "";
		creationDate = "";
		transCreator = "";
		fileName = "";
                userName = "";
                generalNote = "";
	}
	
	
	public void print()
	{
		log.info("--- Transaction Object ---");
		log.info("fileID = "+fileID);
                log.info("docID = "+docID);
		log.info("fileName = "+fileName);
		log.info("fileDescription = "+fileDescription);
		log.info("fileStatus = "+fileStatus);
		log.info("tradeFormApp = "+tradeFormApp);
		log.info("transType = "+transType);
		log.info("lockedStatus = "+lockedStatus);
		log.info("creationDate = "+creationDate);
		log.info("transCreator = "+transCreator);
                log.info("userName = "+userName);
                log.info("generalNote = "+generalNote);
		log.info("\n");
	}
	

        /** setDocID */
	public void setDocID(String value)
	{
		docID = value;
	}

	/** getDocID */
	public String getDocID()
	{
		return docID;
	}
        
	/** setElementName */
	public void setFileID(String value)
	{
		fileID = value;
	}

	/** getElementName */
	public String getFileID()
	{
		return fileID;
	}
	
	/** setFileName */
	public void setFileName(String value)
	{
		fileName = value;
	}

	/** getFileName */
	public String getFileName()
	{
		return fileName;
	}

	/** setFileDescription */
	public void setFileDescription(String value)
	{
		fileDescription = value;
	}

	/** getFileDescription */
	public String getFileDescription()
	{
		return fileDescription;
	}

	/** setFileStatus */
	public void setFileStatus(String value)
	{
		fileStatus = value;
	}

	/** getFileStatus */
	public String getFileStatus()
	{
		return fileStatus;
	}


	/** setTransType */
	public void setTransType(String value)
	{
		transType = value;
	}

	/** getTransType */
	public String getTransType()
	{
		return transType;
	}
	/** setTradeFormApp */
	public void setTradeFormApp(String value)
	{
		tradeFormApp = value;
	}

	/** getTradeFormApp */
	public String getTradeFormApp()
	{
		return tradeFormApp;
	}
	
	/** setLockedStatus */
	public void setLockedStatus(String value)
	{
		lockedStatus = value;
	}

	/** getLockedStatus */
	public String getLockedStatus()
	{
		return lockedStatus;
	}
	
	/** setCreationDate */
	public void setCreationDate(String value)
	{
		creationDate = value;
	}

	/** getCreationDate */
	public String getCreationDate()
	{
		return creationDate;
	}
	
	/** setTransCreator */
	public void setTransCreator(String value)
	{
		transCreator = value;
	}

	/** getTransCreator */
	public String getTransCreator()
	{
		return transCreator;
	}
        
        /** setUserName */
	public void setUserName(String value)
	{
		userName = value;
	}

	/** getUserName */
	public String getUserName()
	{
		return userName;
	}
        
        /** setGeneralNote */
	public void setGeneralNote(String value)
	{
		generalNote = value;
	}

	/** getGeneralNote */
	public String getGeneralNote()
	{
		return generalNote;
	}

	public static void main(String[] args)
	{
		TransactionObject transObject1 = new TransactionObject();
	}

    /**
     * @return the partnerName
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * @param partnerName the partnerName to set
     */
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
}