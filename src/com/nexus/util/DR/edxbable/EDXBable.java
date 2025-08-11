package com.nexus.util.DR.edxbable;

public class EDXBable
{
	/** Variables to hold values */
	private String EDXID = "";
	private String KEYRULE = "";	
	private String validationError = "";
	public Originator ORIGINATOR = new Originator();
	public Destination DESTINATION = new Destination();
	public Pair PAIR = new Pair();

	/**
	 * Constructor for EDXBable
	 */
	public EDXBable()
	{

	}
	
	public String print()
	{
		String ret = "";
		ret += "\n========== EDXBABLE ==========\n";
		ret += "\tEDXID: " + this.getEDXID() + "\n";
		ret += "\tKEYRULE: " + this.getKEYRULE() + "\n";	
		ret += "\tvalidationError: " + this.getValidationError() + "\n";	
		ret += ORIGINATOR.print();
		ret += DESTINATION.print();
		ret += PAIR.print();
		return ret;
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

	/** getEDXID */
	public String getEDXID()
	{
		return EDXID;
	}
	
	/** setEDXID */
	public void setEDXID(String val)
	{
		EDXID = val;
	}
	
	/** getKEYRULE */
	public String getKEYRULE()
	{
		return KEYRULE;
	}
	
	/** setKEYRULE */
	public void setKEYRULE(String val)
	{
		KEYRULE = val;
	}


}

