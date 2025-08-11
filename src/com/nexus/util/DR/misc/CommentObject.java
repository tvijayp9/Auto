package com.nexus.util.DR.misc;

/**
* Title:        TradeRouteServer
* Description:
* Copyright:    Copyright (c) 2002
* Company:      XMLYES
* @author Oscar Pfohl
* @version 1.0
*/

public class CommentObject
{

	private String attribName;
	private String attribValue;

	public CommentObject()
	{
	}

	/** setElementName */
	public void setAttribName(String value)
	{
		attribName = value;
	}

	/** getElementName */
	public String getAttribName()
	{
		return attribName;
	}

	/** setElementName */
	public void setAttribValue(String value)
	{
		attribValue = value;
	}

	/** getElementName */
	public String getAttribValue()
	{
		return attribValue;
	}

	public static void main(String[] args)
	{
		CommentObject CommentObject1 = new CommentObject();
	}
}