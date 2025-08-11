package com.nexus.util.DR.misc;


/** 
 * Class used to store details of directories required for the application.
 */
public class DirectoryObject
{
	private String dirName;
	private String dirValue;

	/** constructor */
	public DirectoryObject()
	{
		dirName = "";
		dirValue = "";
	}

	/** return a string to print out */
	public String print()
	{

		String ret = "\n\t\tdirName: " + this.getDirName();
		ret += "\n\t\tdirValue: " + this.getDirValue();	
		return ret;
	}

	/** setDirName */
	public void setDirName(String value)
	{
		dirName = value;
	}

	/** getDirName */
	public String getDirName()
	{
		return dirName;
	}
	
	/** setDirValue */
	public void setDirValue(String value)
	{
		dirValue = value;
	}

	/** getDirValue */
	public String getDirValue()
	{
		return dirValue;
	}

}

