package com.nexus.util.DR.misc;

/** import required classes */
import java.util.Vector;
import java.util.Enumeration;
import org.w3c.dom.*;
import java.io.*;
import org.apache.log4j.Logger;
import com.nexus.util.DR.commonsource.XMLFunctions;

/**
 * Class directoryList:
 *  - Class for storing and accessing all the directory values in the
 *    EDXConfig file.
 */
public class ConfigValuesList
{
    /** singleton member variable */
    private static ConfigValuesList configList = null;
    
    /** Variable to hold directory objects */
    private Vector dirList;
    
    /** config file location set at startup */
    private String configFileLocation = "";
    
    /** Variable to hold XMLFunctions object */
    private XMLFunctions xmlInterface = null;
    
    private boolean initialised = false;
    
    private String ElementName = "";
    
    //private static Logger logger = null;
    Logger log=Logger.getLogger(ConfigValuesList.class);
    /**
     * FUNCTION [directoryList]
     * - private constructor so you can't instantiate.
     */
    private ConfigValuesList(String nodeName)
    {
        ElementName = nodeName;
    }
    
    public void initialise(String configPath)
    {
         log.info("inside initialise==."+configPath);
        if(!initialised)
        {
            configFileLocation = configPath;
            dirList = new Vector();
            xmlInterface = new XMLFunctions();
             log.info("2inside initialise==."+configPath);
            if(configFileLocation.equalsIgnoreCase(""))
            {
                log.info("Please set a config file location, it is blank.");
            }
            else
            {
                  log.info("3inside initialise==."+configPath);
                File cf = new File(configFileLocation);
                 log.info("4inside initialise==."+configPath);
                if(cf.exists() && cf.isFile())
                {
                     log.info("5inside initialise==."+configPath);
                    try
                    {
                        loadConfigValues(configFileLocation);
                    }
                    catch(Exception e)
                    {
                        log.info("Exception loading Config Values: "+e.toString());
                        System.exit(0);
                    }
                }
                else
                {
                    log.info("Config File: ("+configFileLocation+") does not exist, or is not a valid file.");
                    System.exit(0);
                }
            }
        }
        initialised = true;
    }
    
    
    /** method which implements singleton pattern */
    public static ConfigValuesList getInstance(String ClassName, String nodeName)
    {
       // if(logger == null)
       // {
      //      logger = Logger.getLogger("misc.ConfigValuesList");
      //  }
        
        if(configList == null)
        {
            //logger.info("Creating New Instance of ConfigValuesList()...Calling Class("+ClassName+")");            
            try
            {
                configList = new ConfigValuesList(nodeName);
            }
            catch(Exception e)
            {
                System.out.println("Exception Creating New Instance of ConfigValuesList:" +e.toString());
            }
        }
        else
        {
            //logger.info("Returning existing Instance of ConfigValuesList()...Calling Class("+ClassName+")");           
        }
        return configList;
    }
    
    /**
     * FUNCTION [addDirObject]
     * - add a Directory Object into the list.
     */
    private void addConfigObject(DirectoryObject dir)
    {
        dirList.add(dir);
    }
    
    
    
    /**
     * FUNCTION [addDirObject]
     * - return a Directory Object into the list.
     */
    private DirectoryObject getConfigObject(String dirName)
    {
        Enumeration en = dirList.elements();
        
        DirectoryObject ret = new DirectoryObject();
        
        while(en.hasMoreElements())
        {
            DirectoryObject tempObject = (DirectoryObject)en.nextElement();
            
            if(tempObject.getDirName().equalsIgnoreCase(dirName))
            {
                ret = tempObject;
                break;
            }
        }
        return ret;
    }
    
    
    /**
     * FUNCTION [loadDirectories]
     * - load all the required directories into the directory list object.
     */
    private void loadConfigValues(String configFilePath) throws Exception
    {
        log.info("loadConfigValues configFilePath=="+configFilePath);
        
        /** Read it into a DOM */
        Document ConfigDOM = null;
        try
        {
             log.info("2loadConfigValues configFilePath=="+configFilePath);
            ConfigDOM = xmlInterface.readInXMLFile(configFilePath);
        }
        catch(Exception e)
        {
            /** error loading directories */
            log.info("loadConfigValues Exception: "+e.toString());
            throw e;
        }
        
        /** Get all required element values */        
        NodeList TradeRouteNodes = ConfigDOM.getElementsByTagName(ElementName);
        Node TradeRouteNode = TradeRouteNodes.item(0);
        NodeList childNodes = TradeRouteNode.getChildNodes();
        
        /** Get all the elements under passed in NodeName in the config file */
        for(int i = 0; i < childNodes.getLength(); i++)
        {
            Node currNode = childNodes.item(i);
            
            if(currNode.ELEMENT_NODE != 0)
            {
                if(!currNode.getNodeName().equalsIgnoreCase("#text"))
                {
                    DirectoryObject tempObj = new DirectoryObject();
                    tempObj.setDirName(currNode.getNodeName());
                    if(currNode.hasChildNodes())
                    {
                        tempObj.setDirValue(currNode.getFirstChild().getNodeValue());
                    }
                    addConfigObject(tempObj);
                    tempObj = null;
                }
            }
            currNode = null;
        }
    }
    
    
    
    
    /**
     * FUNCTION [getDirectoryValue()]:
     *  - Return the value of the directoryName passed in which is stored
     *  - in the directoryList Vector object.
     */
    public String getConfigValue(String dirPropName)
    {
        if(!initialised)
        {
            log.info("Not yet initialised.");
            System.out.println("[ConfigValuesList] Not yet initialised");
            return "";
        }
        
        Enumeration en = dirList.elements();
        
        String ret = "";
        
        while(en.hasMoreElements())
        {
            DirectoryObject tempObject = (DirectoryObject)en.nextElement();
            
            if(tempObject.getDirName().equalsIgnoreCase(dirPropName))
            {
                ret = tempObject.getDirValue();
                break;
            }
        }
        return ret;
    }
    
    public static void main(String[] args)
    {
        
        ConfigValuesList c = ConfigValuesList.getInstance("ConfigValuesList", "TradeConnect");
        //c.initialise("c:\\dynex\\edxconfig.xml");
        
        System.out.println("PROP_DATA_IN_DIR: "+c.getConfigValue("PROP_DATA_IN_DIR"));
        
        ConfigValuesList d = ConfigValuesList.getInstance("ConfigValuesList", "TradeConnect");
        
        System.out.println("PROP_DATA_IN_DIR2: "+d.getConfigValue("PROP_DATA_IN_DIR"));
        
    }
}

