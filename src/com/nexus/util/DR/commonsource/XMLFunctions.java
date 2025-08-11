package com.nexus.util.DR.commonsource;

/**
 * Title:        XMLFunctions
 * Description:  This library of functions is designed to provide a variety of XML
 *               related generic functions which can be used in a variety of applications.
 * Copyright:    Copyright (c) 2002
 * Company:      XMLYES
 * @author Oscar Pfohl
 * @version 1.0
 */



/** XML packages */
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;


/** Import required Classes...*/
import com.nexus.util.DR.misc.*;
import java.io.*;
import java.nio.channels.*;
import java.util.Vector;
import java.util.Enumeration;
import org.apache.log4j.Logger;




/**
 * Class XMLFunctions:
 *  - Contains all functions dealing with XML files...
 */
public class XMLFunctions implements ErrorHandler
{
    /** All output will use this encoding */
    static final String outputEncoding = "UTF-8";
    
    /** Set to TRUE to print to System.out */
    public boolean debug;
    
    /** Value to hold debug file */
    private Debug logFile;
    
    /** Used when creating the comment node in transactions...*/
    Vector commentList = new Vector(10, 2);
    
    /** return result variable */
    boolean reValidateResult = false;
    
    /** return result variable */
    boolean errorsOccurred = false;
    
    /** Global class variable used to parse XML files */
    private DocumentBuilder db;
    
    /** Used when printing output of DOM */
    private String tabb;
    
    /** number of times to try and read a file before giving up*/
    private int numOfTimesToRead = 4;
    
    private boolean validatedOK= true;
    
    /** Constants used for JAXP 1.2 */
    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    
    private String last_validation_error = "";
    
     Logger log=Logger.getLogger(XMLFunctions.class);
    
    
    /**
     * FUNCTION [XMLFunctions()]:
     *  - Constructor.
     */
    public XMLFunctions()
    {
       // logger = Logger.getLogger("commonsource.XMLFunctions");
        /** Step 1: create a DocumentBuilderFactory and setNamespaceAware */
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        
        
        /** Step 2: create a DocumentBuilder */
        try
        {
            
            db = dbf.newDocumentBuilder();
        }
        catch(Exception e)
        {
            log.error("Exception creating Document Builder: "+e.toString());
        }
        
        debug = false;
        tabb = "";
    }
    
    
    /**
     * FUNCTION [getLastValidationError()]:
     *  - Gets the last error that occurred while validating a document.
     */
    public String getLastValidationError()
    {
        return last_validation_error;
    }
    
    
    
    /**
     * FUNCTION [setLogFile()]:
     *  - Points to the logFile for output.
     */
    public void setLogFile(Debug origLogFile)
    {
        logFile = origLogFile;
    }
    
    
    
    /****************************** READ/WRITE XML FUNCTIONS ******************************/
    /**
     * FUNCTION [writeDomToFile()]:
     *  - Write out the passed in DOM structure to the fileName passed in.
     */
    public void writeDomToFile(Document doc, String filename)
    {
        try
        {
            //logger.info("Wirte DOM to File: "+filename);
            
            
            /** Prepare the DOM document for writing */
            Source source = new DOMSource(doc);
            
            /** Prepare the output file */
            File file = new File(filename);

            file.createNewFile();
            file.setWritable(true, false);


            /** get a channel on the file */
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            FileChannel channel = raf.getChannel();
            
            /** Use the file channel to create a lock on the file.*/
            FileLock lock = channel.tryLock();
            
            while(lock == null)
            {
                log.info("File: "+file.getName()+" is locked, sleeping...: ");
                try
                {
                    Thread.currentThread().sleep(1500);
                }catch(java.lang.InterruptedException e)
                {}
                
                lock = channel.tryLock();
            }
            
            /** release the lock so the transformer can write to it */
            lock.release();
            channel.close();
            raf.close();
            
            
            /** Write the DOM document to the file */
           // Result result = new StreamResult(file);
            final StreamResult result = new StreamResult(file.toURI().getPath());



            //FileOutputStream outS = new FileOutputStream(raf.getFD());
            //Result result = new StreamResult(outS);
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            if(xformer==null){
              System.out.println("transformer is null");
            }
            xformer.transform(source, result);
            
            file = null;
            
        }
        catch (Exception e)
        {
            log.error("Exception Writing DOM To File ("+filename+"): "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    /**
     * FUNCTION [writeDomToFile()]:
     *  - Write out the passed in DOM structure to the fileName passed in.
     */
        /*public void writeDomToFile2(Document DOM, String fileName)
        {
         
                try{
         
                        /** Get a file channel for the file */
     /*   	File file = new File(fileName);
      
                /** if the file exists, delete it */
     /*   	if(file.exists())
                {
                        file.delete();
                }
      
                /** get a channel on the file */
     /*   	RandomAccessFile raf = new RandomAccessFile(file, "rw");
                FileChannel channel = raf.getChannel();
      
                /** Use the file channel to create a lock on the file.*/
     /*   	FileLock lock = channel.tryLock();
      
                        while(lock == null)
                        {
                                logger.info("[writeDOMToFile] - File: "+file.getName()+" is locked, sleeping...: ");
                                try{
                                Thread.currentThread().sleep(1500);
                                }catch(java.lang.InterruptedException e){}
      
                                lock = channel.tryLock();
                        }
      
                        //logger.info("[writeDOMToFile] - GOT THE LOCK ON THE FILE...: ");
      
                        OutputFormat format = new OutputFormat(DOM); //Serialize DOM
                        StringWriter stringOut = new StringWriter(); //Writer will be a String
                        XMLSerializer serial = new XMLSerializer(stringOut, format);
                        serial.asDOMSerializer(); // As a DOM Serializer
                        serial.serialize(DOM.getDocumentElement());
      
                        /** write out the file */
        /*		raf.writeBytes(stringOut.toString());
         
                        /** cleanup */
        /*		lock.release();
                        channel.close();
                        raf.close();
                        file = null;
                }
                catch(Exception e)
                {
                        logger.info("[writeDOMToFile] - Exception Writing DOM To File: ");
                        e.printStackTrace();
                }
        }*/
    
    
    
    /**
     * FUNCTION [readInXMLFile()]:
     *  - Read in an XML file from the specified path.
     *  - Will retry to read file (numOfTimesToRead) times if an error occurs.
     */
    public Document readInXMLFile(String xmlFilePath) throws Exception
    {
        //logger.info("[ReadinXMLFile] - Trying to read in: "+xmlFilePath);
        
        Document DOM = null;
        
        log.info("readInXMLFile=="+xmlFilePath);
        File inputFile = new File(xmlFilePath);
        
        if(inputFile.exists() && inputFile.isFile())
        {
            log.info("[ReadinXMLFile] - File is OK: "+inputFile.getName());
            
            /** get a channel on the file */
            RandomAccessFile raf = new RandomAccessFile(inputFile, "rw");
            FileChannel channel = raf.getChannel();
            
            /** Use the file channel to create a lock on the file.*/
            FileLock lock = channel.tryLock();
            
            while(lock == null)
            {
                log.info("Reading, File :"+inputFile.getName()+"is locked, sleeping...: ");
                try
                {
                    Thread.currentThread().sleep(1500);
                }catch(java.lang.InterruptedException e)
                {}
                
                lock = channel.tryLock();
            }
            
            //logger.info("[ReadinXMLFile] - GOT THE LOCK ON THE FILE... ");
            
            /** release the lock, can't release after using FileInputDtream as it becomes invalid */
            lock.release();
            
            /** get the file */
            FileInputStream fin = new FileInputStream(raf.getFD());
            
            try
            {
                log.info("before parse=="+fin.toString()+"...inputFile=="+inputFile.getName());
                DOM = db.parse(fin);
                log.info("after parse=="+DOM.toString());
            }
            catch(Exception e)
            {
                 log.info("- 2readInXMLFile Parsing Exception:="+e.toString());
                log.info("- 1readInXMLFile Parsing Exception:="+e.getMessage());
               
                
                /** cleanup */
                channel.close();
                raf.close();
                fin.close();
                inputFile = null;
                throw e;
            }
             log.info("2after parse=="+DOM.toString());
            /** cleanup */
           // channel.close();
            //raf.close();
            //fin.close();
            inputFile = null;
        }
        else
        {
            log.info("[ReadinXMLFile] - File: "+xmlFilePath+", does not exist or is not a valid file.");
            throw new Exception("[ReadinXMLFile] - File: "+xmlFilePath+", does not exist or is not a valid file.");
        }
        
        return DOM;
        
    }
    public Document readFile(byte[] xmlFile) throws Exception
    {
        //logger.info("[ReadinXMLFile] - Trying to read in: "+xmlFilePath);
        log.info("xmlFile data: "+xmlFile.length);
        Document DOM = null;
      
            try
            {

                DOM = db.parse(new ByteArrayInputStream(xmlFile));
                log.info("xmlFile DOM: "+DOM.toString());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            /** cleanup */
        
       

        return DOM;

    }
    
    /**
     * FUNCTION [readInXMLFile()]:
     *  - Read in an XML file from the specified path.
     *  - Will retry to read file (numOfTimesToRead) times if an error occurs.
     */
        /*public Document readInXMLFile(String xmlFilePath) throws Exception
        {
                Document DOM = null;
         
                try
                {
                        File input = new File(xmlFilePath);
         
                        /** parse the input file to get a Document object */
        /*		ReadLoop: for(int i=0; i < numOfTimesToRead; i++)
                        {
                                if(input.exists() && input.canRead())
                                {
                                        DOM = db.parse(input);
         
                                        break ReadLoop;
                                }
                                else
                                {
                                        Integer timesLeft = new Integer(numOfTimesToRead - i);
                                        logger.info("Could Not read file ("+input.getAbsolutePath()+"), sleeping...Will try again ("+timesLeft.toString()+") times.");
                                        Thread.sleep(2000);
                                        input = new File(xmlFilePath);
                                }
                        }
                }
                catch(Exception e)
                {
                        String mess = e.getMessage();
                        if(mess.indexOf("Premature") != -1)
                        {
                                logger.info(": Premature end of file... Sleeping.");
                                Thread.sleep(2000);
                                readInXMLFile(xmlFilePath);
                        }
                        else
                        {
                                logger.info(": readInXMLFile Exception: ");
                                e.printStackTrace(System.out);
                                throw e;
                        }
                }
         
                return DOM;
        }*/
    
    
    /**
     * FUNCTION [readInXMLFileAsString()]:
     *  - Read in an XML file from the specified path and return it as a string.
     *  - Will retry to read file (numOfTimesToRead) times if an error occurs.
     */
    public String readInXMLFileAsString(String xmlFilePath) throws Exception
    {
        FileReader source = new FileReader(xmlFilePath);
        BufferedReader in = new BufferedReader(source);
        
        String contents = "";
        String data = "";
        
        while((contents = in.readLine()) != null)
        {
            data += contents;
        }
        return data;
    }
    
    
    /**
     * FUNCTION [copyFile()]:
     *  - Copy one file to another.
     */
    public void copyFile(String origPath, String destPath) throws Exception
    {
        File inputFile = new File(origPath);
        File outputFile = new File(destPath);
        
        FileReader in = new FileReader(inputFile);
        FileWriter out = new FileWriter(outputFile);
        
        int c;
        
        while((c = in.read()) != -1)
        {
            out.write(c);
        }
        
        in.close();
        out.close();
        inputFile = null;
        outputFile = null;
    }
    
    
    /**************************** END READ/WRITE XML FUNCTIONS ****************************/
    
    
    
    
    
    
    
    
    /*************************** DOM PRINTING/RECURSION FUNCTIONS *************************/
    
    /**
     * FUNCTION [printDOM()]:
     *  - Print the Passed in DOM to System.out.
     */
    public void printDOM(Document DOM)
    {
        this.debug = true;
        NodeList nl = DOM.getChildNodes();
        recurseDOM(nl);
    }
    
    /**
     * FUNCTION [printNODE()]:
     *  - Print the Passed in Node to System.out.
     */
    public void printNODE(Node n)
    {
        this.debug = true;
        NodeList nl = n.getChildNodes();
        recurseDOM(nl);
    }
    
    
    /**
     * FUNCTION [recurseDOM()]:
     *  - Recurse the passed in NodeList, printing to System.out if debug = true.
     */
    public void recurseDOM(NodeList nl)
    {
        
        int numOfElement = nl.getLength();
        
        for (int i = 0; i < numOfElement; i++)
        {
            Node tempNode = nl.item(i);
            if (tempNode.getNodeType() == Node.COMMENT_NODE)
            {
                if (debug)
                {log.info("Comment Node : " + tempNode.getNodeValue());};
            }
            if (tempNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) nl.item(i);
                
                if (debug)
                {
                    System.out.print("Element: " + tabb + ":" + element.getNodeName());
                    if(element.hasChildNodes())
                    {
                        System.out.print(", Value: " + element.getFirstChild().getNodeValue());
                    }
                    //log.info("");
                }
                
                NamedNodeMap attribs = element.getAttributes();
                
                for(int j = 0; j < attribs.getLength(); j++)
                {
                    Node currAtt = attribs.item(j);
                    
                    log.info("Attrib ("+j+") : "+currAtt.getNodeName());
                }
                
                
                if (element.hasChildNodes() == true)
                {
                    tabb = tabb.concat("--");
                    NodeList children = element.getChildNodes();
                    /** This recursive call will explore all the child nodes...*/
                    recurseDOM(children);
                    tabb = tabb.substring(0, tabb.length() - 2);
                }
            }
        }
    }
    
    
    /************************* END DOM PRINTING/RECURSION FUNCTIONS ***********************/
    
    
    
    
    
    
    
    /*********************************** ELEMENT/ATTRIBUTE PROCESSING Functions **********************************/
    
    
    /**
     * FUNCTION [getNode()]:
     *  - return a Node of the name passed in.
     *
     *  - Root:
     * 		- NodeA
     * 		- NodeB
     * 			- NodeC
     *
     * - (usage: getNode(Root, NodeA) , will return NodeA)
     * - (usage: getNode(Root, NodeB\NodeC) , will return NodeC)
     */
    public Node getNode(Node root, String elementName)
    {
        Node retValue = null;
        
        /** get the name of the current node to search for */
        int pos = elementName.indexOf("\\");
        
        /** There are more elements in the path */
        if(pos != -1)
        {
            /** Get the name of the current node to search for */
            String currElName = elementName.substring(0, pos);
            
            /** Get the rest of the path */
            elementName = elementName.substring(pos+1);
            
            /** Get the node with this element name */
            NodeList kids = root.getChildNodes();
            
            for(int i = 0; i < kids.getLength(); i++)
            {
                Node currKid = kids.item(i);
                
                if(currKid.getNodeName().equalsIgnoreCase(currElName))
                {
                    retValue = getNode(currKid, elementName);
                }
            }
        }
        else
        {
            /** find this element name in this node's children */
            NodeList children = root.getChildNodes();
            
            for(int i = 0; i < children.getLength(); i++)
            {
                Node currKid = children.item(i);
                
                if(currKid.getNodeName().equalsIgnoreCase(elementName))
                {
                    retValue = currKid;
                }
            }
        }
        return retValue;
    }
    
    
    
    
    
    /**
     * FUNCTION [getNodeValue()]:
     *  - return the value of the Node name passed in
     *
     *  - Root:
     * 		- NodeA = 123
     * 		- NodeB = 456
     * 			- NodeC = 789
     *
     * - (usage: getNode(Root, NodeA) , will return 123)
     * - (usage: getNode(Root, NodeB\NodeC) , will return 789)
     */
    public String getNodeValue(Node root, String elementName)
    {
        String retValue = "";
        try
        {
            
            
            /** get the name of the current node to search for */
            int pos = elementName.indexOf("\\");
            
            /** There are more elements in the path */
            if(pos != -1)
            {
                /** Get the name of the current node to search for */
                String currElName = elementName.substring(0, pos);
                
                /** Get the rest of the path */
                elementName = elementName.substring(pos+1);
                
                /** Get the node with this element name */
                NodeList kids = root.getChildNodes();
                
                for(int i = 0; i < kids.getLength(); i++)
                {
                    Node currKid = kids.item(i);
                    
                    if(currKid.getNodeName().equalsIgnoreCase(currElName))
                    {
                        retValue = getNodeValue(currKid, elementName);
                    }
                }
            }
            else
            {
                /** find this element name in this node's children */
                NodeList children = root.getChildNodes();
                
                for(int i = 0; i < children.getLength(); i++)
                {
                    Node currKid = children.item(i);
                    
                    if(currKid.getNodeName().equalsIgnoreCase(elementName))
                    {
                        retValue = getNodeTextValue(currKid);
                    }
                }
            }
            
        }
        catch(Exception e)
        {
            //logger.info("Root: "+root.getNodeName());
            log.info("elementName: "+elementName);
            //e.printStackTrace();
        }
        
        return retValue;
        
    }
    
    
    
    /**
     * FUNCTION [setElementValue()]:
     *  - set the value of the Element name passed in, can do multiple structures
     *
     *  - Root:
     * 		- NodeA = 123
     * 		- NodeB = 456
     * 			- NodeC = 789
     *
     * - (usage: setElementValue(NodeA, 111) , will set NodeA's value to 111)
     */
    public void setElementValue(Node root, String elementName, String value)
    {
        if(!elementName.equalsIgnoreCase(""))
        {
            /** get the name of the current node to search for */
            int pos = elementName.indexOf("\\");
            
            /** There are more elements in the path */
            if(pos != -1)
            {
                /** Get the name of the current node to search for */
                String currElName = elementName.substring(0, pos);
                
                /** Get the rest of the path */
                elementName = elementName.substring(pos+1);
                
                /** Get the node with this element name */
                NodeList kids = root.getChildNodes();
                
                for(int i = 0; i < kids.getLength(); i++)
                {
                    Node currKid = kids.item(i);
                    
                    if(currKid.getNodeName().equalsIgnoreCase(currElName))
                    {
                        setElementValue(currKid, elementName, value);
                    }
                }
            }
            else
            {
                
                /** find this element name in this node's children */
                NodeList children = root.getChildNodes();
                
                for(int i = 0; i < children.getLength(); i++)
                {
                    Node currKid = children.item(i);
                    
                    if(currKid.getNodeName().equalsIgnoreCase(elementName))
                    {
                        if(currKid.hasChildNodes())
                        {
                            
                            
                            Node firstChild = currKid.getFirstChild();
                            
                            if(firstChild.getNodeType() == Node.TEXT_NODE)
                            {
                                firstChild.setNodeValue(value);
                            }
                            else
                            {
                                Document owner = root.getOwnerDocument();
                                currKid.insertBefore(owner.createTextNode(value), firstChild);
                            }
                        }
                        else
                        {
                            Document owner = root.getOwnerDocument();
                            currKid.appendChild(owner.createTextNode(value));
                        }
                    }
                }
            }
        }
        else
        {
            
            /** Set the root node's value */
            NodeList children = root.getChildNodes();
            
            boolean foundNode = false;
            
            for(int i = 0; i < children.getLength(); i++)
            {
                Node currNode = children.item(i);
                
                if(currNode.getNodeType() == Node.TEXT_NODE)
                {
                    currNode.setNodeValue(value);
                    foundNode = true;
                    
                }
            }
            
            if(!foundNode)
            {
                /** create the text node */
                root.setNodeValue(value);
            }
        }
        //return root;
    }
    
    
    
    /**
     * FUNCTION [getNodeTextValue()]:
     *  - Get the text value of the passed in element.
     */
    public void setAttributeValue(Node n, String attribName, String value)
    {
        try
        {
            NamedNodeMap attribs = n.getAttributes();
            Node ID = attribs.getNamedItem(attribName);
            if(ID != null)
            {
                ID.setNodeValue(value);
            }
            else
            {
                /** create the attribute */
                Element currEl = (Element)n;
                currEl.setAttribute(attribName, value);
                
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace(System.out);
        }
    }
    
    
    
    
    /**
     * FUNCTION [getNodeTextValue()]:
     *  - Get the text value of the passed in element.
     */
    public String getAttributeValue(Node n, String attribName)
    {
        String retVal = "";
        try
        {
            NamedNodeMap attribs = n.getAttributes();
            Node ID = attribs.getNamedItem(attribName);
            
            if(ID != null)
            {
                retVal = ID.getNodeValue();
                if(retVal == null)
                {
                    retVal = "";
                }
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace(System.out);
        }
        
        return retVal;
    }
    
    
    /**
     * FUNCTION [getNodeTextValue()]:
     *  - Get the text value of the passed in element.
     */
    public String getNodeTextValue(Node n)
    {
        String val = "";
        
        /** Get the first child of the element */
        Node TextNode = n.getFirstChild();
        
        if (!(TextNode == null))
        {
            val = TextNode.getNodeValue();
        }
        
        if(val == null)
        {
            val = "";
        }
        
        /** Trim the value */
        val = val.trim();
        return val;
    }
    
    
    
    /***************************** END ELEMENT/ATTRIBUTE PROCESSING Functions ****************************/
    
    
    
    
    
    
    
    
    
    
    /******************************** COMMENT/RETURN ROOT NODE FUNCTIONS ******************************/
    
    /**
     * FUNCTION [returnRootNode()]:
     *  - Return the attributes from the comment node back to the Root Node.
     */
    public Document returnRootNode(Document DOM)
    {
        
        Element root = DOM.getDocumentElement();
        
        NodeList children = root.getChildNodes();
        
        for (int i = 0; i < children.getLength(); i++)
        {
            Node currNode = children.item(i);
            
            if (currNode.getNodeType() == currNode.COMMENT_NODE)
            {
                String temp = currNode.getNodeValue();
                
                /** Get everything after the EDXDATA text */
                int pos = temp.indexOf(",");
                String attribs = temp.substring(pos + 1);
                
                int commaPos = attribs.indexOf(",");
                
                while (commaPos != -1)
                {
                    String currPair = attribs.substring(0, commaPos);
                    attribs = attribs.substring(commaPos + 1);
                    commaPos = attribs.indexOf(",");
                    
                    /** split up the current pair and add to commentObjects list */
                    int eqPos = currPair.indexOf("=");
                    CommentObject tempObj = new CommentObject();
                    
                    tempObj.setAttribName(currPair.substring(0, eqPos));
                    tempObj.setAttribValue(currPair.substring(eqPos + 1));
                    
                    commentList.add(tempObj);
                    
                }
                /**take care of the last pair of values...*/
                attribs = attribs.substring(0, attribs.length() - 1);
                
                CommentObject tempObj2 = new CommentObject();
                
                int eqPos2 = attribs.indexOf("=");
                tempObj2.setAttribName(attribs.substring(0, eqPos2));
                tempObj2.setAttribValue(attribs.substring(eqPos2 + 1));
                
                commentList.add(tempObj2);
                
                /** Finished adding all attribs to commentList */
                Enumeration attribList = commentList.elements();
                
                while (attribList.hasMoreElements())
                {
                    CommentObject currObj = (CommentObject) attribList.nextElement();
                    
                    root.setAttribute(currObj.getAttribName(), currObj.getAttribValue());
                    
                }
                /** remove the comment node*/
                root.removeChild(currNode);
                
            }
        }
        
        return DOM;
    }
    
    
    
    
    /**
     * FUNCTION [commentRootNode()]:
     *  - Put all the attribs from the root node into a comment node.
     *  - ASP doesn't like attributes in the root node.
     */
    public Document commentRootNode(Document DOM)
    {
        
        Element root = DOM.getDocumentElement();
        Node secondNode = root.getFirstChild().getNextSibling();
        
        String rootName = root.getNodeName();
        NamedNodeMap attribs = root.getAttributes();
        
        String attName = "";
        
        if (attribs.getLength() > 0)
        {
            
            for (int i = 0; i < attribs.getLength(); i++)
            {
                attName += "," + attribs.item(i).getNodeName();
                attName += "=";
                attName += "\"" + attribs.item(i).getNodeValue() + "\"";
            }
            
            while (attribs.getLength() != 0)
            {
                root.removeAttribute(attribs.item(0).getNodeName());
            }
            
            /** Create the comment String */
            String commentString = "<";
            commentString += rootName;
            commentString += attName;
            commentString += ">";
            
            /** Create a comment node */
            Comment COMM = DOM.createComment(commentString);
            
            /** Insert the comment node before the second node */
            Node test = root.appendChild(COMM);
        }
        return DOM;
    }
    
    
    /****************************** END COMMENT/RETURN ROOT NODE FUNCTIONS ****************************/
    
    
    
    
    
    
    
    /****************************** BABLE FILE SPECIFIC FUNCTIONS ****************************/
    
    
    /**
     * FUNCTION [getKeyRuleValue()]:
     *  - Get the value of an element in the bableFile (handle directory structures).
     *  - (eg: OROGINATOR\NAME )
     */
    public String getKeyRuleValue(Node root, String elementName)
    {
        String retValue = "";
        
        /** Get the position of the first 'p' in the element name */
        String tempKeyRule = elementName;
        int pos = tempKeyRule.indexOf("p");
        int parentCounter = 0;
        
        /** Count the number of 'p' characters in the keyRule */
        while (pos != -1)
        {
            tempKeyRule = tempKeyRule.substring(pos + 2, tempKeyRule.length());
            pos = tempKeyRule.indexOf("p");
            parentCounter++;
        }
        
        /** Move up the right number of parent nodes */
        for (int i = 0; i < parentCounter; i++)
        {
            root = root.getParentNode();
        }
        
        /** get the name of the current node to search for */
        int pos2 = tempKeyRule.indexOf("\\");
        
        /** There are more elements in the path */
        if(pos2 != -1)
        {
            /** Get the name of the current node to search for */
            String currElName = tempKeyRule.substring(0, pos2);
            
            /** Get the rest of the path */
            tempKeyRule = tempKeyRule.substring(pos2+1);
            
            /** Get the node with this element name */
            NodeList kids = root.getChildNodes();
            
            for(int i = 0; i < kids.getLength(); i++)
            {
                Node currKid = kids.item(i);
                
                if(currKid.getNodeName().equalsIgnoreCase(currElName))
                {
                    retValue = getKeyRuleValue(currKid, tempKeyRule);
                }
            }
        }
        else
        {
            
            /** return the value of this node */
            if(elementName.equalsIgnoreCase(""))
            {
                retValue = getNodeTextValue(root);
            }
            else
            {
                /** find this element name in this node's children */
                NodeList children = root.getChildNodes();
                
                for(int i = 0; i < children.getLength(); i++)
                {
                    Node currKid = children.item(i);
                    
                    if(currKid.getNodeName().equalsIgnoreCase(tempKeyRule))
                    {
                        if(currKid.hasChildNodes())
                        {
                            retValue = currKid.getFirstChild().getNodeValue();
                        }
                    }
                }
            }
        }
        
        if(retValue == null)
        {
            retValue = "";
        }
        
        retValue = retValue.trim();
        return retValue;
    }
    
    
    /****************************** END BABLE FILE SPECIFIC FUNCTIONS ****************************/
    
    
    
    
    
    
    
    /*********************************** DOCUMENT VALIDATION FUNCTION ***********************************/
    
    /**
     * FUNCTION [validateTransaction()]:
     *  - /** Validate the transaction against the schema in the root node attribute.
     */
    public boolean validateTransaction(String fileName, String schemaFile)
    {
        
        File xmlFile = new File(fileName);
        File schemaDoc = new File(schemaFile);
        
        if(xmlFile.exists())
        {
            //logger.info("Starting Validation");
            /** reset the validatedOK boolean */
            validatedOK = true;
            last_validation_error = "";
            
            /** Step 1: create a DocumentBuilderFactory and setNamespaceAware */
            DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
            dbf2.setNamespaceAware(true);
            dbf2.setValidating(true);
            dbf2.setIgnoringElementContentWhitespace(true);
            
            /** set the shcema language */
            try
            {
                dbf2.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            }
            catch (IllegalArgumentException x)
            {
                /** This can happen if the parser does not support JAXP 1.2 */
                log.error("Error: JAXP DocumentBuilderFactory attribute not recognized: "+ JAXP_SCHEMA_LANGUAGE);
                log.error("Check to see if parser conforms to JAXP 1.2 spec.");
                
                //System.exit(1);
            }
            
            
            /** Set the schema source, if any.  See the JAXP 1.2 maintenance */
            /** update specification for more complex usages of this feature. */
            if(!schemaFile.equalsIgnoreCase(""))
            {
                dbf2.setAttribute(JAXP_SCHEMA_SOURCE, new File(schemaFile));
            }
            
            
            // Optional: set various configuration options
            //dbf2.setIgnoringComments(true);
            //dbf2.setIgnoringElementContentWhitespace(true);
            //dbf2.setCoalescing(true);
            // The opposite of creating entity ref nodes is expanding them inline
            //dbf2.setExpandEntityReferences(true);
            
            
            DocumentBuilder db2 = null;
            
            /** Step 2: create a DocumentBuilder */
            try
            {
                
                db2 = dbf2.newDocumentBuilder();
                db2.setErrorHandler(this);
                
                //logger.info("- Beginning Validation...");
                Document doc = db2.parse(new File(fileName));
                //logger.info("- Finished Validation...");
            }
            catch(Exception e)
            {
                log.error("High level Parse/Validation error occurred..."+e.toString());
                try
                {
                    log.info("Sleeping... ");
                    Thread.sleep(10000);
                }
                catch(Exception e2)
                {}
                try
                {
                    
                    Document doc = db2.parse(new File(fileName));
                }
                catch(Exception e3)
                {
                    log.error("Exception validating transaction... "+e.toString());
                    validatedOK = false;
                }
            }
            
        }
        else
        {
            log.error("High level Parse/Validation error occurred...The xml file didn't exist..."+fileName);
            validatedOK = false;
        }
        return validatedOK;
        
    }
    
    /*********************************** END DOCUMENT VALIDATION FUNCTION ********************************/
    
    
    //
    // ErrorHandler methods
    //
    
    /** Warning. */
    public void warning(SAXParseException ex) throws SAXException
    {
        printError("Warning", ex);
    } // warning(SAXParseException)
    
    /** Error. */
    public void error(SAXParseException ex) throws SAXException
    {
        printError("Error", ex);
    } // error(SAXParseException)
    
    /** Fatal error. */
    public void fatalError(SAXParseException ex) throws SAXException
    {
        
        log.info("**************** FATAL ERROR *******************");
        
        String mess = ex.getMessage();
        if(mess.indexOf("Premature") != -1)
        {
            log.info("[Error Handler] Premature end of file.");
        }
        else
        {
            printError("Fatal Error", ex);
            //throw ex;
        }
    } // fatalError(SAXParseException)
    
    /** Prints the error message. */
    protected void printError(String type, SAXParseException ex)
    {
        
        validatedOK = false;
        
        String errorString = "["+type+"]"+ex.getMessage();
        
        last_validation_error = last_validation_error +"\n"+ errorString;
        
    }
    
    
    public Document removeBables(Document DOM)
    {
        
        /** Get all the elements in the DOM */
        NodeList allElements = DOM.getElementsByTagName("*");
        
        /** For all the elements */
        for (int i = 0; i < allElements.getLength(); i++)
        {
            /** Get the current element */
            Node currBable = allElements.item(i);
            
            /** Get the children of the current Element */
            NodeList kids = currBable.getChildNodes();
            
            /** For all the kids of the current element */
            for (int j = 0; j < kids.getLength(); j++)
            {
                /** Get the current child */
                Node currNode = kids.item(j);
                
                /** If the current kid is the EDXBABLE node */
                if (currNode.getNodeName().equalsIgnoreCase("EDXBABLE"))
                {
                    /** Remove the EDXBABLE node */
                    currBable.removeChild(currNode);
                    
                    break;
                }
            }
        }
        
        /** return the modified DOM */
        return DOM;
    }
    
    
    
    
    /**
     * FUNCTION [main()]:
     *  - Only used for debugging purposes.
     */
    public static void main(String[] args)
    {
        XMLFunctions prog = new XMLFunctions();
        prog.debug = true;
        
        try
        {
            
            //Document DOM = prog.readInXMLFile("c:\\temp\\bbb.xml");
           // log.info("Starting...");
            String fileName = "C:\\Temp2\\TRS_EXPINV_STRUCTUREONLY.xml";
            String schemaName = "";//"C:\\Temp2\\Order.xsd";
            
            if(prog.validateTransaction(fileName, schemaName))
            {
               // log.info("OK");
            }
            else
            {
              //  log.info("NOT OK");
              //  log.info(prog.getLastValidationError());
                
            }
            
            
            
            //log.info("Done...");
            //String res = prog.readInXMLFileAsString("C:\\Temp\\EXPORT XML FILES\\xslt1.txt");
            
            //res = res.replaceAll("\n", "");
            
            //prog.validateTransaction(fileName, schemaName);
            
            
            //FileOutputStream fs = new OutputStream();
            
            //	StringWriter s = new StringWriter();
            ///	FileWriter f = new FileWriter(s);
            //	s.write(res);
            //BufferedWriter b = new BufferedWriter();
            
            //logger.info(res);
            
            
            //    		if(prog.validateTransaction(fileName, schemaName))
            //    		{
            //    			logger.info("OK");
            //    		}
            //    		else
            //    		{
            //    			logger.info("NOT OK");
            //    			logger.info(prog.getLastValidationError());
            //
            //    		}
            
        }
        catch(Exception e)
        {
            System.out.println("Exception : "+e.getMessage());
            //e.printStackTrace();
        }
        
        
        
        /** while keepRunning is still true */
        /*while(true)
        {
                /** get the purchase order queue */
        /*	File po_queue = new File("C:\\Temp\\BIG_XML");
         
        /*	if(po_queue.isDirectory())
                {
                        /** get a list of all the files in the pruchase order queue */
        /*		File[] po_list = po_queue.listFiles();
         
                        /** while there are more purchase orders to process*/
        /*		while(po_list.length != 0)
                        {
                                /** get the first file */
        /*			File current_po = po_list[0];
         
                                try{
         
                                        Document DOM = prog.readInXMLFile(current_po.getAbsolutePath());
         
                                        prog.writeDomToFile(DOM, "C:\\Temp\\BIG_XML\\Test.xml");
                                        }
                                        catch(Exception e)
                                        {
                                                logger.info("Exception : "+e.getMessage());
                                                e.printStackTrace();
                                        }
                        }
                }
        }*/
        
        
        
                /*try{
                 
                        Document DOM = prog.readInXMLFile("C:\\Temp\\BIG_XML\\ChangeOrder5.xml");
                 
                        prog.writeDomToFile(DOM, "C:\\Temp\\BIG_XML\\Test.xml");
                 
                        XMLFunctions prog2 = new XMLFunctions();
                 
                        logger.info("prog2 reading file...");
                        Document DOM2 = prog2.readInXMLFile("C:\\Temp\\BIG_XML\\ChangeOrder5.xml");
                 
                        //String fileName = "C:\\DYNEX\\TRSapp\\TEST DATA\\Transactions\\(1) From Mincom (xCBL Format)\\Order.xml";
                        //String schemaLoc = "C:\\DYNEX\\PROTOCOLS\\xCBL3.0\\xCBL-xsd-3.0-FULL\\Order.xsd";
                 
                        //String fileName = "C:\\DYNEX\\TRSapp\\TEST DATA\\Transactions\\(1) From Mincom (xCBL Format)\\ChangeOrder.xml";
                        //String schemaLoc = "C:\\DYNEX\\PROTOCOLS\\xCBL3.0\\xCBL-xsd-3.0-FULL\\ChangeOrder.xsd";
                 
                        /** validate a transaction */
                        /*if(prog.validateTransaction(fileName, schemaLoc))
                        {
                                logger.info("Validated OK");
                        }
                        else
                        {
                                logger.info("Couldn't Validate");
                        }*/
        
        
        
        
        //Document DOM = prog.readInXMLFile("C:\\DYNEX\\TRSapp\\TEST DATA\\(1) From Mincom (xCBL Format)\\input.xml");
        //Document DOM = prog.readInXMLFile("C:\\DYNEX\\TRSapp\\TEST DATA\\(2) From MincomAdpater\\AXIS2MSAPO_[S00401]_20020905060802-0.xml");
        //Document DOM = prog.readInXMLFile("C:\\DYNEX\\TRSapp\\TEST DATA\\(3) After running through TRSBableiser\\AXIS2MSAPO_[S00401]_20020905060802-0.xml");
        //Document DOM = prog.readInXMLFile("C:\\DYNEX\\TRSapp\\TRANSACTIONS\\traderoute_queue\\Purchase_Orders\\AXIS2MSAPO_[S00401]_20020905060802-0.xml");
        
        
        
        //Node root = (Node)DOM.getDocumentElement();
        
        //logger.info("Root Node: "+root.getNodeName());
        
        /** Get the value of a Node */
        //String val = prog.getNodeValue(root, "OrderResponseDetail\\ListOfOrderResponseItemDetail\\OrderResponseItemDetail\\OriginalItemDetail\\ItemDetail\\LineItemNote");
        //logger.info("Value: "+val);
        
        /** Get an actual Node, print it's value */
        //Node tempNode = prog.getNode(root, "OrderResponseDetail\\ListOfOrderResponseItemDetail\\OrderResponseItemDetail\\OriginalItemDetail\\ItemDetail\\LineItemNote");
        //logger.info("Node Name: "+tempNode.getNodeName()+", Node Value: "+prog.getNodeTextValue(tempNode));
        
        /** Set a Node's value */
        //Node tempNode = prog.getNode(root, "OrderResponseDetail\\ListOfOrderResponseItemDetail\\OrderResponseItemDetail\\OriginalItemDetail\\ItemDetail\\LineItemNote");
        //prog.setElementValue(tempNode, "", "hello world");
        //logger.info("Node Name: "+tempNode.getNodeName()+", Node Value: "+prog.getNodeTextValue(tempNode));
        
        //or
        
        //prog.setElementValue(root, "OrderResponseDetail\\ListOfOrderResponseItemDetail\\OrderResponseItemDetail\\OriginalItemDetail\\ItemDetail\\LineItemNote", "hello world");
        //Node tempNode = prog.getNode(root, "OrderResponseDetail\\ListOfOrderResponseItemDetail\\OrderResponseItemDetail\\OriginalItemDetail\\ItemDetail\\LineItemNote");
        //logger.info("Node Name: "+tempNode.getNodeName()+", Node Value: "+prog.getNodeTextValue(tempNode));
        
        
                /*}
                catch(Exception e)
                {
                        logger.info("Exception : "+e.getMessage());
                        e.printStackTrace();
                }*/
    }
}