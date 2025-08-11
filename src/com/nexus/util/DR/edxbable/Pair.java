package com.nexus.util.DR.edxbable;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author
 * @version 1.0
 */

public class Pair
{

	/** Variables to hold Originator/Destination information */
	private String ORIGVALUE = "";
	private String ORIGUNIQUEKEY = "";
	private String DESTVALUE = "";

	public String print()
	{
		String ret = "";
		ret += "\n========== PAIR ==========\n";
		ret += "\tORIGVALUE: " + this.getORIGVALUE() + "\n";
		ret += "\tORIGUNIQUEKEY: " + this.getORIGUNIQUEKEY() + "\n";
		ret += "\tDESTVALUE: " + this.getDESTVALUE() + "\n";
		return ret;
	}

	/**Function getORIGVALUE */
	public String getORIGVALUE()
	{
		return ORIGVALUE;
	}

	/**Function setORIGVALUE */
	public void setORIGVALUE(String val)
	{
		ORIGVALUE = val;
	}

	/**Function getORIGUNIQUEKEY */
	public String getORIGUNIQUEKEY()
	{
		return ORIGUNIQUEKEY;
	}

	/**Function setORIGUNIQUEKEY */
	public void setORIGUNIQUEKEY(String val)
	{
		ORIGUNIQUEKEY = val;
	}

	/**Function getDESTVALUE */
	public String getDESTVALUE()
	{
		return DESTVALUE;
	}

	/**Function setDESTVALUE */
	public void setDESTVALUE(String val)
	{
		DESTVALUE = val;
	}
	
	public Pair()
	{
	}

}