package com.nexus.util.DR.misc;

import org.apache.log4j.Logger;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public class UserObject {

    Logger log=Logger.getLogger(UserObject.class);
	private int KeyValue = 0;
	private String UserLoginName = "";
	private String UserFullName = "";
	private String UserPassword = "";
	private String UserType = "";
	
	public UserObject()
	{		
	}
	
	
	
	/** get KeyValue */
	public int getKeyValue()
	{
		return KeyValue;
	}
	
	/** set KeyValue */
	public void setKeyValue(int val)
	{
		KeyValue = val;
	}
	
	/** get UserLoginName */
	public String getUserLoginName()
	{
		return UserLoginName;
	}
	
	/** set UserLoginName */
	public void setUserLoginName(String val)
	{
		UserLoginName = val;
	}
	
	/** get UserFullName */
	public String getUserFullName()
	{
		return UserFullName;
	}
	
	/** set UserFullName */
	public void setUserFullName(String val)
	{
		UserFullName = val;
	}
	
	/** get UserPassword */
	public String getUserPassword()
	{
		return UserPassword;
	}
	
	/** set UserPassword */
	public void setUserPassword(String val)
	{
		UserPassword = val;
	}
	
	/** get UserType */
	public String getUserType()
	{
		return UserType;
	}
	
	/** set UserType */
	public void setUserType(String val)
	{
		UserType = val;
	}
	
	public void print()
	{
		log.info("************** User ***************");
		log.info("Key Value: "+this.getKeyValue());
		log.info("Login Name: "+this.getUserLoginName());
		log.info("Full Name: "+this.getUserFullName());
		log.info("User Password: "+this.getUserPassword());
		log.info("User Type: "+this.getUserType());
		log.info("***********************************");
	}
	

	public static void main(String[] args) {
	}
}
