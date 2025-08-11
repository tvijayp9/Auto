package com.nexus.util.DR.misc;

/**
* Title:        TradeRouteServer
* Description:
* Copyright:    Copyright (c) 2002
* Company:      XMLYES
* @author Oscar Pfohl
* @version 1.0
*/

/** Import all required classes */
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import org.apache.log4j.Logger;



/** 
 * Class Debug:
 *  - Used to write messages to a log file.
 */
public class Debug
{
    Logger log=Logger.getLogger(Debug.class);
	private File logFile;
	private FileWriter logFileWriter;



	/**
	 * FUNCTION [Debug()]:
	 *  - Constructor.
	 */
	public Debug()
	{
	}




	/**
	 * FUNCTION [createFile()]:
	 *  - Create a new LogFile.
	 */
	public void createFile(String logFileName)
	{
		try
			{
			logFile = new File(logFileName);
			logFileWriter = new FileWriter(logFile);
		}
		catch (IOException io)
			{
			log.info("Could Not open log file: " + logFileName + ", " + io);
		}
	}



	
	/**
	 * FUNCTION [closeFile()]:
	 *  - Close the LogFile.
	 */
	public void closeFile()
	{
		try
			{
			logFileWriter.close();
		}
		catch (IOException io)
			{
			log.info("Could Not close log file: " + io);
		}
	}



	/**
	 * FUNCTION [write()]:
	 *  - Write to the logFile.
	 */
	public void write(String message)
	{
		try
			{
			Date now = new Date(System.currentTimeMillis());
			DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
			String val = df.format(now);
			logFileWriter.write(val + " - " + message + "\r\n");
		}
		catch (IOException io)
			{
			log.info("Could Not write message to log file: " + io);
		}

	}


	
	/**
	 * FUNCTION [main()]:
	 *  - Used for debugging.
	 */
	public static void main(String[] args)
	{
		/**Debug debug1 = new Debug();*/
	}
}