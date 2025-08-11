package com.nexus.web.common;

public class UsersList {
    
    private int id;
    private String loginid;
    private String company;
    private String email;
    private String address;    
    private String phno;
    private String fxno;
    private String address2;
    private String contact;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private String company_url;  
    private String member_type;
    private String logoName;

    
    private int siccode;

    
    public String getMember_type() {
        return member_type;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }
    public int getSiccode() {
        return siccode;
    }

    public void setSiccode(int siccode) {
        this.siccode = siccode;
    }
    
    
    public String getAddress2() {
        return this.address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLoginid() {
        return loginid;
    }
    
    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }
    
    public String getCompany() {
        return company ;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    
    public String getFxno() {
        return fxno;
    }
    
    public void setFxno(String fxno) {
        this.fxno = fxno;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getPostcode() {
        return postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
    
    public String getCompany_url() {
        return company_url;
    }

    public void setCompany_url(String company_url) {
        this.company_url = company_url;
    }

    /**
     * @return the logoName
     */
    public String getLogoName() {
        return logoName;
    }

    /**
     * @param logoName the logoName to set
     */
    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }
    
    
}
