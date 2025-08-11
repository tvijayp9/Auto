package com.nexus.util.DR.misc;

/**
* Title:        TradeRouteServer
* Description:
* Copyright:    Copyright (c) 2002
* Company:      XMLYES
* @author Oscar Pfohl
* @version 1.0
*/

/**
 * Used to store new values which need to be added back into the Bable file.
 * 
 */

public class UpdateObject
{

	private String parentName;
	private String oldValue;
	private String newValue;
	private String keyValue;
	private String validationError;
    private String buttonClicked;
	public UpdateObject()
	{
		parentName = "";
		oldValue = "";
		newValue = "";
		keyValue = "";
		validationError = "";
	}

	public String print()
	{

		String ret = "\n\t\tparentName: " + this.getParentName();
		ret += "\n\t\toldValue: " + this.getOldValue();
		ret += "\n\t\tnewValue: " + this.getNewValue();
		ret += "\n\t\tkeyValue: " + this.getKeyValue();
		ret += "\n\t\tvalidationError: " + this.getValidationError();
		return ret;
	}

	/** setParentName */
	public void setParentName(String value)
	{
		parentName = value;
	}

	/** getParentNamee */
	public String getParentName()
	{
		return parentName;
	}

	/** setOldValue */
	public void setOldValue(String value)
	{
		oldValue = value;
	}

	/** getOldValue */
	public String getOldValue()
	{
		return oldValue;
	}

	/** setNewValue */
	public void setNewValue(String value)
	{
		newValue = value;
	}

	/** getNewValue */
	public String getNewValue()
	{
		return newValue;
	}
	
	/** setKeyValue */
	public void setKeyValue(String value)
	{
		keyValue = value;
	}

	/** getKeyValue */
	public String getKeyValue()
	{
		return keyValue;
	}
	
	/** setKeyValue */
	public void setValidationError(String value)
	{
		validationError = value;
	}

	/** getKeyValue */
	public String getValidationError()
	{
		return validationError;
	}

	public static void main(String[] args)
	{
		UpdateObject updateObject1 = new UpdateObject();
	}

    /**
     * @return the buttonClicked
     */
    public String getButtonClicked() {
        return buttonClicked;
    }

    /**
     * @param buttonClicked the buttonClicked to set
     */
    public void setButtonClicked(String buttonClicked) {
        this.buttonClicked = buttonClicked;
    }

}