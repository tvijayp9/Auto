package com.nexus.util.DR.edxbable;

public class Originator
{

	/** Variables to hold values */
	private String NAME = "";
	private String VALUE = "";
	private String DATATYPE = "";
    private String PARENTVALUE = "";
	/**
	 * Constructor for EDXBable
	 */
	public Originator()
	{

	}
	
	public String print()
	{
		String ret = "";
		ret += "\n========== ORIGINATOR ==========\n";
		ret += "\tNAME: " + this.getNAME() + "\n";	
		ret += "\tVALUE: " + this.getVALUE() + "\n";	
		ret += "\tDATATYPE: " + this.getDATATYPE() + "\n";	
		return ret;
	}
	
	/** getNAME */
	public String getNAME()
	{
		return NAME;
	}
	
	/** setNAME */
	public void setNAME(String val)
	{
		NAME = val;
	}
	
	/** getVALUE */
	public String getVALUE()
	{
		return VALUE;
	}
	
	/** setVALUE */
	public void setVALUE(String val)
	{
		VALUE = val;
	}
	
	/** getDATATYPE */
	public String getDATATYPE()
	{
		return DATATYPE;
	}
	
	/** setDATATYPE */
	public void setDATATYPE(String val)
	{
		DATATYPE = val;
	}

    /**
     * @return the PARENTVALUE
     */
    public String getPARENTVALUE() {
        return PARENTVALUE;
    }

    /**
     * @param PARENTVALUE the PARENTVALUE to set
     */
    public void setPARENTVALUE(String PARENTVALUE) {
        this.PARENTVALUE = PARENTVALUE;
    }
}