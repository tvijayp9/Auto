/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.util.DR.edxbable;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class TreeView implements Serializable{

    private String dispDetails;
    private String fieldName;
    private String resolvedValue;
    private String origValue;
    private String schemaToUse;
    private String tickType;
    private String edxId;
    private String fileName;
    private String validError;

    /**
     * @return the dispDetails
     */
    public String getDispDetails() {
        return dispDetails;
    }

    /**
     * @param dispDetails the dispDetails to set
     */
    public void setDispDetails(String dispDetails) {
        this.dispDetails = dispDetails;
    }

    /**
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @return the resolvedValue
     */
    public String getResolvedValue() {
        return resolvedValue;
    }

    /**
     * @param resolvedValue the resolvedValue to set
     */
    public void setResolvedValue(String resolvedValue) {
        this.resolvedValue = resolvedValue;
    }

    /**
     * @return the origValue
     */
    public String getOrigValue() {
        return origValue;
    }

    /**
     * @param origValue the origValue to set
     */
    public void setOrigValue(String origValue) {
        this.origValue = origValue;
    }

    /**
     * @return the schemaToUse
     */
    public String getSchemaToUse() {
        return schemaToUse;
    }

    /**
     * @param schemaToUse the schemaToUse to set
     */
    public void setSchemaToUse(String schemaToUse) {
        this.schemaToUse = schemaToUse;
    }

    /**
     * @return the tickType
     */
    public String getTickType() {
        return tickType;
    }

    /**
     * @param tickType the tickType to set
     */
    public void setTickType(String tickType) {
        this.tickType = tickType;
    }

    /**
     * @return the edxId
     */
    public String getEdxId() {
        return edxId;
    }

    /**
     * @param edxId the edxId to set
     */
    public void setEdxId(String edxId) {
        this.edxId = edxId;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the validError
     */
    public String getValidError() {
        return validError;
    }

    /**
     * @param validError the validError to set
     */
    public void setValidError(String validError) {
        this.validError = validError;
    }
}
