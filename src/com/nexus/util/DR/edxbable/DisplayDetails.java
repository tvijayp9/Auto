package com.nexus.util.DR.edxbable;

public class DisplayDetails
{
	
	/** JSP page display values */	
	private String fontColour = "";
	private String fontSize = "";
	private String bgColour = "";
	private String displayInTree = "";
	private String displayInContent = "";

	
	/** 
	 * FUNCTION [DisplayDetails()]:
	 *  - Constructor
	 */
	public DisplayDetails()
	{
	
	}
	
	/** 
	 * FUNCTION [print()]:
	 *  - Return a printable string of all the elements.
	 */
	public String print()
	{
		String ret = "";		
		
		ret += "\n\t\t========== DISPLAYVALUES ==========\n";	
		ret += "\n\t\tfontColour: " + this.getFontColour();
		ret += "\n\t\tfontSize: " + this.getFontSize();
		ret += "\n\t\tbgColour: " + this.getBgColour();
		ret += "\n\t\tdisplayInTree: " + this.getDisplayInTree();
		ret += "\n\t\tdisplayInContent: " + this.getDisplayInContent();		
	
		return ret;
	}
	
	/************************** JSP page display values *************************/
	
	/** setFontColor */
	public void setFontColour(String value)
	{
		fontColour = value;
	}

	/** getFontColor */
	public String getFontColour()
	{
		return fontColour;
	}
	
	/** setFontSize */
	public void setFontSize(String value)
	{
		fontSize = value;
	}

	/** getFontSize */
	public String getFontSize()
	{
		return fontSize;
	}
	
	/** setBgColor */
	public void setBgColour(String value)
	{
		bgColour = value;
	}

	/** getBgColor */
	public String getBgColour()
	{
		return bgColour;
	}
	
	/** setDisplayInContent */
	public void setDisplayInContent(String value)
	{
		displayInContent = value;
	}

	/** getDisplayInContent */
	public String getDisplayInContent()
	{
		return displayInContent;
	}
	
	/** setDisplayInTree */
	public void setDisplayInTree(String value)
	{
		displayInTree = value;
	}

	/** getDisplayInTree */
	public String getDisplayInTree()
	{
		return displayInTree;
	}
}

