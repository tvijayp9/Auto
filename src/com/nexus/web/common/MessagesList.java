package com.nexus.web.common;

public class MessagesList {
    
    private int id;    
    private String status;
    private String docid;
    private String type;
    private String date;    
    private String to;
    private String from;    
    private int download;
    private String filename;
    private boolean orderOnline=false;
    private int senderId;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    /**
     * @return the orderOnline
     */
    public boolean isOrderOnline() {
        return orderOnline;
    }

    /**
     * @param orderOnline the orderOnline to set
     */
    public void setOrderOnline(boolean orderOnline) {
        this.orderOnline = orderOnline;
    }

    /**
     * @return the senderId
     */
    public int getSenderId() {
        return senderId;
    }

    /**
     * @param senderId the senderId to set
     */
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

            
    
    
    
}
