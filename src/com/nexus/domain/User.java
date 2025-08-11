/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.domain;

/**
 *
 * @author Terry
 */
public class User {
    private int id;
    private String userId;
    private String password;
    private String name;
    private int type;
    private String email;
    private int nexusId;

    public User(String userId, String password, String name, int type, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.type = type;
        this.email = email;
    }

    public User(int id,int nexusId,int type){
    this.id=id;
    this.nexusId=nexusId;
    this.type=type;
    }
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
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
}
