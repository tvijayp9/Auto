package com.nexus.util.DR.accessdbcomm;

import java.util.Date;
import org.apache.log4j.Logger;
/** Class to hold returned SQL query values (record sets) */
public class QueryResult {
	
	 Logger log=Logger.getLogger(QueryResult.class);
	/** Strings to hold returned values from Parent_Transaction Table */
	private String ParentKey = "";
	private String DOCKEY = "";
	private String DocumentCreatorsTransactionID = "";
	private String DocumentCreatorTPID = "";
	private String DocumentType = "";
	private String SequenceNumber = "";
	private String RootParentTransactionUniqueID = "";
	private String PreviousParentTransactionUniqueID = "";
	private String TransactionNote = "";
	private Date LogDate;
	
	/** Strings to hold returned values from Transaction_Status Table */
	private int StatusID = -1;
	/** private final String DOCKEY = "";  Allready stored above... */
	private String UserID = "";
	private Date CreationDate;
	private String OpenState = "";
	private String ResolvedState = "";
	private String FileType = "";
	private Date SendDate;
	private Date ReceivedDate;
	private String SendMethod = "";
	private String Direction = "";
	private String DestinationAddress = "";
	private String ArchivePath = "";
    private String transactionNumber;
    private String partnerName;
	
	
	
	/** Get and Set methods for Parent Transaction Table */
	public String getParentKey()
	{
		return ParentKey;
	}
	
	public void setParentKey(String value)
	{
		ParentKey = value;
	}
	
	public String getDOCKEY()
	{
		return DOCKEY;
	}
	
	public void setDOCKEY(String value)
	{
		DOCKEY = value;
	}
	
	public String getDocumentCreatorsTransactionID(){
		return DocumentCreatorsTransactionID;
	}
	
	public void setDocumentCreatorsTransactionID(String value){
		DocumentCreatorsTransactionID=value;
	}
		
	public String getDocumentCreatorTPID(){
		return DocumentCreatorTPID;
	}
	
	public void setDocumentCreatorTPID(String value){
		DocumentCreatorTPID=value;
	}
	
	public String getDocumentType(){
		return DocumentType;
	}
	
	public void setDocumentType(String value){
		DocumentType = value;
	}
	
	public String getSequenceNumber(){
		return SequenceNumber;
	}
	
	public void setSequenceNumber(String value){
		SequenceNumber = value;
	}	
		
	public String getRootParentTransactionUniqueID(){
		return RootParentTransactionUniqueID;
	}
	
	public void setRootParentTransactionUniqueID(String value){
		RootParentTransactionUniqueID=value;
	}
	
	public String getPreviousParentTransactionUniqueID(){
		return PreviousParentTransactionUniqueID;
	}
	
	public void setPreviousParentTransactionUniqueID(String value){
		PreviousParentTransactionUniqueID=value;
	}
		
	public String getTransactionNote(){
		return TransactionNote;
	}
	
	public void setTransactionNote(String value){
		TransactionNote=value;
	}
	
	public Date getLogDate(){
		return LogDate;
	}
	
	public void setLogDate(Date value){
		LogDate=value;
	}
		
	
	/** Get and Set methods for Status Table */
	public int getStatusID()
	{
		return StatusID;
	}
	
	public void setStatusID(int value)
	{
		StatusID = value;
	}
	
	public String getUserID()
	{
		return UserID;
	}
	
	public void setUserID(String value)
	{
		UserID = value;
	}
	
	public Date getCreationDate(){
		return CreationDate;
	}
	
	public void setCreationDate(Date value){
		CreationDate=value;
	}
	
	public String getOpenState(){
		return OpenState;
	}
	
	public void setOpenState(String value){
		OpenState=value;
	}
	
	public String getResolvedState(){
		return ResolvedState;
	}
	
	public void setResolvedState(String value){
		ResolvedState=value;
	}
	
	public String getFileType(){
		return FileType;
	}
	
	public void setFileType(String value){
		FileType=value;
	}
	
	public Date getSendDate(){
		return SendDate;
	}
	
	public void setSendDate(Date value){
		SendDate=value;
	}
	
	public Date getReceivedDate(){
		return ReceivedDate;
	}
	
	public void setReceivedDate(Date value){
		ReceivedDate=value;
	}
	
	public String getSendMethod(){
		return SendMethod;
	}
	
	public void setSendMethod(String value){
		SendMethod=value;
	}
	
	public String getDirection(){
		return Direction;
	}
	
	public void setDirection(String value){
		Direction=value;
	}
	
	public String getDestinationAddress(){
		return DestinationAddress;
	}
	
	public void setDestinationAddress(String value){
		DestinationAddress=value;
	}
	
	public String getArchivePath(){
		return ArchivePath;
	}
	
	public void setArchivePath(String value){
		ArchivePath=value;
	}
	
	public void print(){
		log.info(ParentKey + "," + DOCKEY + ","
		+ DocumentCreatorsTransactionID + "," + DocumentCreatorTPID + ","
		+ SequenceNumber +"," + DocumentType + "," + RootParentTransactionUniqueID + ","
		+ PreviousParentTransactionUniqueID + "," + TransactionNote + ","
		+ LogDate + "\n--->"
		+ StatusID + "," + UserID + "," + CreationDate + "," + OpenState + ","
		+ ResolvedState + "," + FileType + "," + SendDate + "," 
		+ ReceivedDate + "," + SendMethod + "," + Direction + ","
		+ DestinationAddress + "," + ArchivePath + ".");
	}

    /**
     * @return the transactionNumber
     */
    public String getTransactionNumber() {
        return transactionNumber;
    }

    /**
     * @param transactionNumber the transactionNumber to set
     */
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
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



