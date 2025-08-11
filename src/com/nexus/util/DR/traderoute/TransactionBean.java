package com.nexus.util.DR.traderoute;

import org.apache.log4j.Logger;


public class TransactionBean 
{
     Logger log=Logger.getLogger(TransactionBean.class);
    private String[] filterStrings = new String[3];
    private String id;
    private String partner;
    private String number;
    private String altNumber;
    private String transType;
    private String issueDate;
    private String processDate;
    private String outputDate;
    private String transmissionDate;
    private String messageAckDate;
    private String transAckDate;
    private String responseDate;
    private String responseType;
    private String responseComment;
    private String description;
    private String state;
    private String userList;
    private String lastUser;
    private String sequenceNumber;
    private String totalTransactionAmount;
    private String rootParentTransactionId;
    private String openState;
    
    public TransactionBean()
    {    
    }
    
    public TransactionBean(String id, String partner, String transType, String number, String altNumber, String issueDate, String processDate, String outputDate, String transmissionDate, String messageAckDate, String transAckDate, String responseDate, String responseType, String responseComment, String state)
    {
        this.id = id;
        this.partner = partner;
        this.transType = transType;
        this.number = number;
        this.altNumber = altNumber;
        this.issueDate = issueDate;
        this.processDate = processDate;
        this.outputDate = outputDate;
        this.transmissionDate = transmissionDate;
        this.messageAckDate = messageAckDate;
        this.transAckDate = transAckDate;
        this.responseDate = responseDate;
        this.responseType = responseType;
        this.responseComment = responseComment;
        this.state = state;
    }
    
    public void print()
    {
        log.info("\n***** Transaction Bean ******");
        log.info("id :"+id);
        log.info("partner :"+partner);
        log.info("number :"+number);
        log.info("altNumber :"+altNumber);
        log.info("transType :"+transType);
        log.info("issueDate :"+issueDate);
        log.info("processDate :"+processDate);
        log.info("outputDate :"+outputDate);
        log.info("transmissionDate :"+transmissionDate);
        log.info("messageAckDate :"+messageAckDate);
        log.info("transAckDate :"+transAckDate);
        log.info("responseDate :"+responseDate);
        log.info("responseType :"+responseType);
        log.info("responseComment :"+responseComment);
        log.info("description :"+description);
        log.info("state :"+state);
        log.info("userList :"+userList);
        log.info("lastUser :"+lastUser);
        log.info("sequenceNumber :"+sequenceNumber);
        log.info("totalTransactionAmount :"+totalTransactionAmount);
        log.info("***** End Transaction Bean ******\n");
    }
    
    public String getId()
    {
        return id;
    }
    
    public String getPartner()
    {
        return partner;
    }
    
    public String getTransType()
    {
        return transType;
    }
    
    public String getNumber()
    {
        return number;
    }
    
    public String getIssueDate()
    {
        return issueDate;
    }
    
    public String getProcessDate()
    {
        return processDate;
    }
    
    public String getOutputDate()
    {
        return outputDate;
    }
    
    public String getTransmissionDate()
    {
        return transmissionDate;
    }
    
    public String getMessageAckDate()
    {
        return messageAckDate;
    }
    
    public String getTransAckDate()
    {
        return transAckDate;
    }
    
    public String getResponseDate()
    {
        return responseDate;
    }
    
    public String getResponseType()
    {
        return responseType;
    }
    
    public String getResponseComment()
    {
        return responseComment;
    }
        
    public int compareTo(Object obj)
    {
        TransactionBean otherBean = (TransactionBean)obj;
        
        return getId().compareToIgnoreCase(otherBean.getId());
    }
    
    /**
     * Setter for property id.
     * @param id New value of property id.
     */
    public void setId(java.lang.String id)
    {
        this.id = id;
    }
    
    /**
     * Setter for property partner.
     * @param partner New value of property partner.
     */
    public void setPartner(java.lang.String partner)
    {
        this.partner = partner;
    }
    
    /**
     * Setter for property number.
     * @param number New value of property number.
     */
    public void setNumber(java.lang.String number)
    {
        this.number = number;
    }
    
    /**
     * Setter for property transType.
     * @param transType New value of property transType.
     */
    public void setTransType(java.lang.String transType)
    {
        this.transType = transType;
    }
    
    /**
     * Setter for property issueDate.
     * @param issueDate New value of property issueDate.
     */
    public void setIssueDate(java.lang.String issueDate)
    {
        this.issueDate = issueDate;
    }
    
    /**
     * Setter for property processDate.
     * @param processDate New value of property processDate.
     */
    public void setProcessDate(java.lang.String processDate)
    {
        this.processDate = processDate;
    }
    
    /**
     * Setter for property outputDate.
     * @param outputDate New value of property outputDate.
     */
    public void setOutputDate(java.lang.String outputDate)
    {
        this.outputDate = outputDate;
    }
    
    /**
     * Setter for property transmissionDate.
     * @param transmissionDate New value of property transmissionDate.
     */
    public void setTransmissionDate(java.lang.String transmissionDate)
    {
        this.transmissionDate = transmissionDate;
    }
    
    /**
     * Setter for property messageAckDate.
     * @param messageAckDate New value of property messageAckDate.
     */
    public void setMessageAckDate(java.lang.String messageAckDate)
    {
        this.messageAckDate = messageAckDate;
    }
    
    /**
     * Setter for property transAckDate.
     * @param transAckDate New value of property transAckDate.
     */
    public void setTransAckDate(java.lang.String transAckDate)
    {
        this.transAckDate = transAckDate;
    }
    
    /**
     * Setter for property responseDate.
     * @param responseDate New value of property responseDate.
     */
    public void setResponseDate(java.lang.String responseDate)
    {
        this.responseDate = responseDate;
    }
    
    /**
     * Setter for property responseType.
     * @param responseType New value of property responseType.
     */
    public void setResponseType(java.lang.String responseType)
    {
        this.responseType = responseType;
    }
    
    /**
     * Setter for property responseComment.
     * @param responseComment New value of property responseComment.
     */
    public void setResponseComment(java.lang.String responseComment)
    {
        this.responseComment = responseComment;
    }
    
    /**
     * Getter for property description.
     * @return Value of property description.
     */
    public java.lang.String getDescription()
    {
        return description;
    }
    
    /**
     * Setter for property description.
     * @param description New value of property description.
     */
    public void setDescription(java.lang.String description)
    {
        this.description = description;
    }
    
    /**
     * Getter for property state.
     * @return Value of property state.
     */
    public java.lang.String getState()
    {
        return state;
    }
    
    /**
     * Setter for property state.
     * @param state New value of property state.
     */
    public void setState(java.lang.String state)
    {
        this.state = state;
    }
    
    /**
     * Getter for property userList.
     * @return Value of property userList.
     */
    public java.lang.String getUserList()
    {
        return userList;
    }
    
    /**
     * Setter for property userList.
     * @param userList New value of property userList.
     */
    public void setUserList(java.lang.String userList)
    {
        this.userList = userList;
    }
    
    /**
     * Getter for property sequenceNumber.
     * @return Value of property sequenceNumber.
     */
    public java.lang.String getSequenceNumber()
    {
        return sequenceNumber;
    }
    
    /**
     * Setter for property sequenceNumber.
     * @param sequenceNumber New value of property sequenceNumber.
     */
    public void setSequenceNumber(java.lang.String sequenceNumber)
    {
        this.sequenceNumber = sequenceNumber;
    }
    
    /**
     * Getter for property totalTransactionAmount.
     * @return Value of property totalTransactionAmount.
     */
    public java.lang.String getTotalTransactionAmount()
    {
        return totalTransactionAmount;
    }
    
    /**
     * Setter for property totalTransactionAmount.
     * @param totalTransactionAmount New value of property totalTransactionAmount.
     */
    public void setTotalTransactionAmount(java.lang.String totalTransactionAmount)
    {
        this.totalTransactionAmount = totalTransactionAmount;
    }
    
    /**
     * Getter for property altNumber.
     * @return Value of property altNumber.
     */
    public java.lang.String getAltNumber()
    {
        return altNumber;
    }
    
    /**
     * Setter for property altNumber.
     * @param altNumber New value of property altNumber.
     */
    public void setAltNumber(java.lang.String altNumber)
    {
        this.altNumber = altNumber;
    }
    
    /**
     * Set the strings to filter on.
     * @return String [] the strings to filter on.
     */    
    public String[] getFilterStrings()
    {
        filterStrings[0] = partner;
        filterStrings[1] = number;
        filterStrings[2] = altNumber;
        return filterStrings;
    }
    
    /**
     * Getter for property lastUser.
     * @return Value of property lastUser.
     */
    public java.lang.String getLastUser()
    {
        return lastUser;
    }
    
    /**
     * Setter for property lastUser.
     * @param lastUser New value of property lastUser.
     */
    public void setLastUser(java.lang.String lastUser)
    {
        this.lastUser = lastUser;
    }

    /**
     * @return the rootParentTransactionId
     */
    public String getRootParentTransactionId() {
        return rootParentTransactionId;
    }

    /**
     * @param rootParentTransactionId the rootParentTransactionId to set
     */
    public void setRootParentTransactionId(String rootParentTransactionId) {
        this.rootParentTransactionId = rootParentTransactionId;
    }

    /**
     * @return the openState
     */
    public String getOpenState() {
        return openState;
    }

    /**
     * @param openState the openState to set
     */
    public void setOpenState(String openState) {
        this.openState = openState;
    }
    
}

