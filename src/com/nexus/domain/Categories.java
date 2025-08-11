/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.domain;

/**
 *
 * @author Terry
 */
public class Categories {

    private int id;
    private String categoryName;
    private int account;

    public Categories() {
    }

    public Categories(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Categories(int id, String categoryName,int account) {
        this.id = id;
        this.categoryName = categoryName;
        this.account=account;
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
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the account
     */
    public int getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(int account) {
        this.account = account;
    }
}
