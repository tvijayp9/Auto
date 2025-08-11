package com.nexus.web.common;

public class DataBean {
	private String id;
	private String value;
        public DataBean(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public DataBean() {
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
