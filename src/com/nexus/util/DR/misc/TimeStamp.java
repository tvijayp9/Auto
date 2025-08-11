package com.nexus.util.DR.misc;

import java.util.*;
import java.text.*;
import org.apache.log4j.Logger;

/** class to hold Dynex specific timestamp fromat */
public class TimeStamp
{
	Logger log=Logger.getLogger(TimeStamp.class);
	/** XCBL FORMAT 20021213T00:00:00+08:00 */
	private String xcblFormat = "yyyyMMdd'T'hh:mm:ss";
	
	/** string to hold timestamp format */
	private String format1 = "yyyyMMddhhmmss";
	
	/** 16/09/2002 15:43:00 */
	private String format2 = "dd/MM/yyyy hh:mm:ss";
	
	/** 20020404T00:00:00+10:00  combine to make previous date */
	private String format3 = "yyyyMMdd";
	private String format4 = "hh:mm:ss";
	
	private String format5 = "dd/MM/yyyy";
        
        private String format6 = "yyyy-MMM-dd";
		
	//private String format3 = "dd/MM/yyyy h:mm:ss a";
	
	private String format1Result = "";
	private String format2Result = "";
	private String format3Result = "";
	private String format4Result = "";
	private String format5Result = "";
        private String format6Result = "";
	
	private Date DateFormat1 = null;
	private Date DateFormat2 = null;
	private Date DateFormat3 = null;
	private Date DateFormat4 = null;
	private Date DateFormat5 = null;
        private Date DateFormat6 = null;
	
	
	private Date now = null;
	
	public String getFormat1()
	{
		return format1Result;
	}
	
	public String getFormat2()
	{
		return format2Result;
	}
	
	public String getFormat3()
	{
		return format3Result;
	}
	
	public String getFormat4()
	{
		return format4Result;
	}
	
	public String getFormat5()
	{
		return format5Result;
	}
        
        public String getFormat6()
	{
		return format6Result;
	}
	
	public String getXCBLFormat()
	{
		return format3Result + "T" + format4Result;
	}
	
	public Date getDateFormat1()
	{
		return DateFormat1;
	}
	
	public Date getDateFormat2()
	{
		return DateFormat2;
	}
	
	public Date getDateFormat3()
	{
		return DateFormat3;
	}
	
	public Date getDateFormat4()
	{
		return DateFormat4;
	}
	
	public Date getDateFormat5()
	{
		return DateFormat5;
	}
        
        public Date getDateFormat6()
	{
		return DateFormat6;
	}
	
	public Date getDate()
	{
		if(now == null)
		{
			generateTimeStamp();
		}
		return now;
	}
	
	/** convert a date (22/12/2003) to and XCBL date () */
	public String convertToXCBL(Date normalDate)
	{
		String ret = "";
		
		try
		{
			SimpleDateFormat formatterX = new SimpleDateFormat(xcblFormat);
			ret = formatterX.format(normalDate);
		}
		catch(Exception e){
		}
		
		return ret;
	}
	
	/** convert an XCBL date to normal date (20021213T12:23:23+08:00) */
	public String convertToNormalDate(String xCBLDate)
	{
		int pos = xCBLDate.indexOf('+');
		
		String cutDate = xCBLDate.substring(0, pos);  
  		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'hh:mm:ss");
  		try{
  			Date res = dateFormat.parse(cutDate);
  			setDate(res);
  			
  			
  		
  			log.info("returned Date: "+format5Result);
  		
  		}
  		catch(Exception e)
  		{
  			e.printStackTrace();
  			return "Unknown";	
  		}
		return format5Result;
		
	}
	
	
	public void generateTimeStamp()
	{		
		SimpleDateFormat formatter1 = new SimpleDateFormat(format1);
		SimpleDateFormat formatter2 = new SimpleDateFormat(format2);
		SimpleDateFormat formatter3 = new SimpleDateFormat(format3);
		SimpleDateFormat formatter4 = new SimpleDateFormat(format4);
		SimpleDateFormat formatter5 = new SimpleDateFormat(format5);
                SimpleDateFormat formatter6 = new SimpleDateFormat(format6);
                		
		now = new Date();
		
		format1Result = formatter1.format(now);	
		format2Result = formatter2.format(now);	
		format3Result = formatter3.format(now);	
		format4Result = formatter4.format(now);	
		format5Result = formatter5.format(now);
                format6Result = formatter6.format(now);
		
	}
	
	public void setDate(Date val)
	{		
		SimpleDateFormat formatter1 = new SimpleDateFormat(format1);
		SimpleDateFormat formatter2 = new SimpleDateFormat(format2);
		SimpleDateFormat formatter3 = new SimpleDateFormat(format3);
		SimpleDateFormat formatter4 = new SimpleDateFormat(format4);
		SimpleDateFormat formatter5 = new SimpleDateFormat(format5);
                SimpleDateFormat formatter6 = new SimpleDateFormat(format6);
				
		format1Result = formatter1.format(val);	
		format2Result = formatter2.format(val);	
		format3Result = formatter3.format(val);	
		format4Result = formatter4.format(val);
		format5Result = formatter5.format(val);
                format6Result = formatter6.format(val);
		
	}
	
	public void generateDate(String val, int format)
	{
		try
		{	
			if(format == 1)
			{
				SimpleDateFormat formatter1 = new SimpleDateFormat(format1);
				DateFormat1 = formatter1.parse(val);
			}
			
			if(format == 2)
			{
				SimpleDateFormat formatter2 = new SimpleDateFormat(format2);
				DateFormat2 = formatter2.parse(val);
			}
			
			if(format == 3)
			{
				SimpleDateFormat formatter3 = new SimpleDateFormat(format3);
				DateFormat3 = formatter3.parse(val);
			}
			
			if(format == 4)
			{
				SimpleDateFormat formatter4 = new SimpleDateFormat(format4);
				DateFormat4 = formatter4.parse(val);
			}
			
			if(format == 5)
			{
				SimpleDateFormat formatter5 = new SimpleDateFormat(format5);
				DateFormat5 = formatter5.parse(val);
			}
                        
                        if(format == 6)
			{
				SimpleDateFormat formatter6 = new SimpleDateFormat(format6);
				DateFormat6 = formatter6.parse(val);
			}
		}
		catch(ParseException pe)
		{
			log.info("Parse Exception: ");
			pe.printStackTrace();
		}	
	}

	public static void main(String[] args)
	{
		TimeStamp t = new TimeStamp();
		
		//t.convertToXCBL(new Date());
		
		t.generateTimeStamp();		
		t.convertToNormalDate("20021213T10:23:23+08:00");
		//log.info("Format 6: "+t.getFormat6());
		//log.info("Format 1: "+t.getFormat1());
		//log.info("Format 2: "+t.getFormat2());
		//log.info("Format 3: "+t.getFormat3());
		//log.info("Format 4: "+t.getFormat4());
		//log.info("XCBL Format : "+t.getXCBLFormat());
		
		
		
	}
}

