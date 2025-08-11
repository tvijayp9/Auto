package com.nexus.util.DR.edxbable;

public class Destination
{

	/** Variables to hold values */
	private String ENGLISHNAME = "";
	private String HELPNOTE = "";
	private String DATATYPE = "";
	private String REQUIRED = "";
	private String STOREVALUEINBABLE = "";
	private String RESOLVETYPE = "";
	private String CURRKEYVALUE = "";
	private String RESOLVEDVALUE = "";
	private String VALIDATEDOK = "";
	private String SCHEMATOUSE = "";
   
	
	public DisplayDetails DISPLAYDETAILS = new DisplayDetails();

	/**
	 * Constructor for EDXBable
	 */
	public Destination()
	{

	}
	
	public String print()
	{
		String ret = "";
		ret += "\n========== DESTINATION ==========\n";
		ret += "\tENGLISHNAME: " + this.getENGLISHNAME() + "\n";
        ret += "\tHELPNOTE: " + this.getHELPNOTE() + "\n";	
		ret += "\tDATATYPE: " + this.getDATATYPE() + "\n";	
		ret += "\tREQUIRED: " + this.getREQUIRED() + "\n";	
		ret += "\tSTOREVALUEINBABLE: " + this.getSTOREVALUEINBABLE() + "\n";	
		ret += "\tRESOLVETYPE: " + this.getRESOLVETYPE() + "\n";	
		ret += "\tCURRKEYVALUE: " + this.getCURRKEYVALUE() + "\n";	
		ret += "\tRESOLVEDVALUE: " + this.getRESOLVEDVALUE() + "\n";
		ret += "\tVALIDATEDOK: " + this.getVALIDATEDOK() + "\n";
		ret += "\tSCHEMATOUSE: " + this.getSCHEMATOUSE() + "\n";
		ret += DISPLAYDETAILS.print();
		return ret;
	}
	
	/** getENGLISHNAME */
	public String getENGLISHNAME()
	{
		return ENGLISHNAME;
	}
	
	/** setENGLISHNAME */
	public void setENGLISHNAME(String val)
	{
		ENGLISHNAME = val;
	}
	
	/** getHELPNOTE */
	public String getHELPNOTE()
	{
		return HELPNOTE;
	}
	
	/** setHELPNOTE */
	public void setHELPNOTE(String val)
	{
		HELPNOTE = val;
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
	
	/** getREQUIRED */
	public String getREQUIRED()
	{
		return REQUIRED;
	}
	
	/** setREQUIRED */
	public void setREQUIRED(String val)
	{
		REQUIRED = val;
	}
	
	/** getSTOREVALUEINBABLE */
	public String getSTOREVALUEINBABLE()
	{
		return STOREVALUEINBABLE;
	}
	
	/** setSTOREVALUEINBABLE */
	public void setSTOREVALUEINBABLE(String val)
	{
		STOREVALUEINBABLE = val;
	}
	
	/** getRESOLVETYPE */
	public String getRESOLVETYPE()
	{
		return RESOLVETYPE;
	}
	
	/** setRESOLVETYPE */
	public void setRESOLVETYPE(String val)
	{
		RESOLVETYPE = val;
	}
	
	/** getCURRKEYVALUE */
	public String getCURRKEYVALUE()
	{
		return CURRKEYVALUE;
	}
	
	/** setCURRKEYVALUE */
	public void setCURRKEYVALUE(String val)
	{
		CURRKEYVALUE = val;
	}
	
	/** getLASTKNOWN */
	public String getRESOLVEDVALUE()
	{
		return RESOLVEDVALUE;
	}
	
	/** setLASTKNOWN */
	public void setRESOLVEDVALUE(String val)
	{
		RESOLVEDVALUE = val;
	}
	
	/** getVALIDATEDOK */
	public String getVALIDATEDOK()
	{
		return VALIDATEDOK;
	}
	
	/** setVALIDATEDOK */
	public void setVALIDATEDOK(String val)
	{
		VALIDATEDOK = val;
	}
	
	/** getSCHEMATOUSE */
	public String getSCHEMATOUSE()
	{
		return SCHEMATOUSE;
	}
	
	/** setSCHEMATOUSE */
	public void setSCHEMATOUSE(String val)
	{
		SCHEMATOUSE = val;
	}
}