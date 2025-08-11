/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.domain;

/**
 *
 * @author Terry
 */
public class Microcat {

    private int id;
    private String accountNumber;
    private String username;
    private String password;
    private String company;
    private int nexusId;
    private int status;
    private String email;

    public Microcat(int id, String accountNumber, String username, String password) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.username = username;
        this.password = password;
    }
    
    public Microcat(int id, String accountNumber, String username, String password, String email) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.username = username;
        this.password = password;
        this.email=email;
    }

    public Microcat(String accountNumber, String username, String password) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.password = password;
    }

    public Microcat(int id, int nexusId, String accountNumber, String username, String password, int status) {
        this.id = id;
        this.nexusId = nexusId;
        this.accountNumber = accountNumber;
        this.username = username;
        this.password = password;
        this.status = status;
    }
    
    public Microcat(int id, int nexusId, String accountNumber, String username, String password, int status,String email) {
        this.id = id;
        this.nexusId = nexusId;
        this.accountNumber = accountNumber;
        this.username = username;
        this.password = password;
        this.status = status;
        this.email=email;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the microcatAccountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param microcatAccountNumber the microcatAccountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nexusId
     */
    public int getNexusId() {
        return nexusId;
    }

    /**
     * @param nexusId the nexusId to set
     */
    public void setNexusId(int nexusId) {
        this.nexusId = nexusId;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }
    
    
}
