package com.nexus.util.DR.commonsource;

/**
 * Title:        CommonSource
 * Description:  This application contains the logic and processes that facilitate the completion and correct resolution of various XML file formats.
 * Copyright:    Copyright (c) 2002
 * Company:      XMLYES
 * @author Oscar Pfohl
 * @version 1.0
 */

/** Import required classes */
import org.w3c.dom.*;
import java.util.Vector;
import java.util.Enumeration;
import com.nexus.util.DR.edxbable.*;
import com.nexus.util.DR.misc.*;
import org.apache.log4j.Logger;

/**
 * Class BableEngine:
 *  - Contains functions that deal with the logical steps related to bable file manipulation.
 */
public class BableEngine
{
     Logger log=Logger.getLogger(BableEngine.class);
    /** Variable to hold directory paths */
    ConfigValuesList localDirectoryList = null;
    
    /** Variable to hold name of current Bable file */
    String bableFileName = "";
    
    /** Variable to hold global logfile */
    public Debug logFile;
    
    public XMLFunctions xmlInterface;
    
    /** Boolean to tell whether there is an error with the DOM currently being processed.*/
    public boolean errorWithinDOM = false;
    
    /** Global structure for holding new element values */
    Vector updateObjects = new Vector();
    
    /** Class that deals with generic bable functions */
    public BableMethods bableMethods = null;
    
    
    /**
     * FUNCTION [BableEngine()]:
     *  - Constructor: create xmlInterface Object.
     */
    public BableEngine()
    {
        xmlInterface = new XMLFunctions();
        bableMethods = new BableMethods();
        //xmlInterface.debug = true;
    }
    
    
    /**
     * FUNCTION [setDirectoryList()]:
     *  - setDirectoryList: set the vector object
     */
    public void setDirectoryList(ConfigValuesList directoryList)
    {
        localDirectoryList = directoryList;
    }
    
    
    /**
     * FUNCTION [setLogFile()]:
     *  - Set the log file for debugging.
     */
    public void setLogFile(Debug origLogFile)
    {
        logFile = origLogFile;
    }
    
    
    
    
    
    
    /************************************ GENERAL PROCESSING FUNCTIONS ****************************************/
    
    /**
     * FUNCTION [getNewValuesFromDocument()]:
     *  - Put all the new values into updateObjects list and remove EDXBABLE structures.
     */
    public Vector getNewValuesFromDocument(Document DOM)
    {
        /** Get all the <EDXBABLE> Tags from the DOM */
        NodeList elements = DOM.getElementsByTagName("EDXBABLE");
        
        /** For every <EDXBABLE> tag */
        for (int i = 0; i < elements.getLength(); i++)
        {
            
            /** Get the current EDXBABLE element */
            Node currEDXBABLE = elements.item(i);
            
            /** do we need to save this EDXBABLE back into the bable file ? */
            String storeInBable = xmlInterface.getNodeValue(currEDXBABLE, "DESTINATION\\STOREVALUEINBABLE");
            
            if(storeInBable.equalsIgnoreCase("true"))
            {
                
                /** Get the current Name (parent of EDXBABLE) */
                String currName = xmlInterface.getNodeValue(currEDXBABLE, "ORIGINATOR\\NAME");
                
                //log.info("Storing value for: "+currName);
                
                /** Get parent node */
                Node parentNode = currEDXBABLE.getParentNode();
                
                /** Get the children of the parent of this element */
                NodeList parentChildren = parentNode.getChildNodes();
                
                /** Variable to hold the new value */
                String newValue = xmlInterface.getNodeValue(currEDXBABLE, "DESTINATION\\RESOLVEDVALUE");
                
                /** Get the actual new value */
                                /*for (int j = 0; j < parentChildren.getLength(); j++)
                                {
                                        Node currChild = parentChildren.item(j);
                                 
                                        newValue = currChild.getNodeValue();
                                 
                                        if (newValue != null)
                                        {
                                                newValue = newValue.trim();
                                 
                                                if (!newValue.equalsIgnoreCase(""))
                                                {
                                                        break;
                                                }
                                        }
                                        else
                                        {
                                                break;
                                        }
                                }	*/
                
                /** Variables to hold original and key values */
                String origValue = xmlInterface.getNodeValue(currEDXBABLE, "ORIGINATOR\\VALUE");
                String keyValue = xmlInterface.getNodeValue(currEDXBABLE, "DESTINATION\\CURRKEYVALUE");
                
                /** create new updateObject */
                UpdateObject tempUpdateObj = new UpdateObject();
                
                /** trim vlaues */
                if (currName == null)
                {
                    currName = "";
                }
                if (origValue == null)
                {
                    origValue = "";
                }
                if (newValue == null)
                {
                    newValue = "";
                }
                if (keyValue == null)
                {
                    keyValue = "";
                }
                
                currName = currName.trim();
                origValue = origValue.trim();
                newValue = newValue.trim();
                keyValue = keyValue.trim();
                
                /** set values */
                tempUpdateObj.setParentName(currName);
                tempUpdateObj.setOldValue(origValue);
                tempUpdateObj.setNewValue(newValue);
                tempUpdateObj.setKeyValue(keyValue);
                
                /** add to vector list */
                updateObjects.add(tempUpdateObj);
                
                tempUpdateObj = null;
            }
        }
        
        /** Return the Vector */
        return updateObjects;
    }
    
    
    /**
     * FUNCTION [removeBables()]:
     *  - Remove all the EDXBABLE structures from the DOM...
     */
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
     * FUNCTION [updateBABLE()]:
     *  - Update the Bable file with new data from vector...
     */
    public Document updateBABLE(Document bableFile, Vector updateObjectList)
    {
        /** Create an Enumeration with the updateList Vector*/
        Enumeration upList = updateObjectList.elements();
        
        /** While there are more update objects */
        while (upList.hasMoreElements())
        {
            /** Get the current updateObject from the enumeration */
            UpdateObject currUpdateObj = (UpdateObject) upList.nextElement();
            
            /** Get the current Update Object's parent Name */
            String parentName = currUpdateObj.getParentName();
            
            /** Get a list of all the EDXBABLE objects in the bable file */
            NodeList edxBableElements = bableFile.getElementsByTagName("EDXBABLE");
            
            /** for all the EDXBABLE elements */
            for(int i = 0; i < edxBableElements.getLength(); i++)
            {
                /** Get the current EDXBABLE element */
                Node currEDXBABLE = edxBableElements.item(i);
                
                /** Get the current EDXBABLE structure's ORIGINATOR\NAME value */
                String currName = xmlInterface.getNodeValue(currEDXBABLE, "ORIGINATOR\\NAME");
                
                /** If this is the right EDXBABLE structure */
                if(currName.equalsIgnoreCase(currUpdateObj.getParentName()))
                {
                    /** Get all the PAIR objects */
                    NodeList children = currEDXBABLE.getChildNodes();
                    
                    /** get the VALUES node */
                    Node VALUES = xmlInterface.getNode(currEDXBABLE, "VALUES");
                    
                    if(VALUES != null)
                    {
                        /** get all the children of the VALUES Node */
                        NodeList PAIRNodes = VALUES.getChildNodes();
                        
                        /** For all the PAIR nodes*/
                        for(int k = 0; k < PAIRNodes.getLength(); k++)
                        {
                            /** get the current PAIR Node */
                            Node currPAIR = PAIRNodes.item(k);
                            
                            if(currPAIR.getNodeType() == Node.ELEMENT_NODE)
                            {
                                
                                /** Get the origValue and unique key values */
                                String ORIGValue = xmlInterface.getNodeValue(currPAIR, "ORIG\\VALUE");
                                String ORIGKey = xmlInterface.getNodeValue(currPAIR, "ORIG\\UNIQUEKEY");
                                
                                /** trim the returned values */
                                ORIGValue = ORIGValue.trim();
                                ORIGKey = ORIGKey.trim();
                                
                                /** IF this is the right PAIR object */
                                if (ORIGValue.equalsIgnoreCase(currUpdateObj.getOldValue())
                                && ORIGKey.equalsIgnoreCase(currUpdateObj.getKeyValue()))
                                {
                                    /** set the DEST value */
                                    xmlInterface.setElementValue(currPAIR, "DEST\\VALUE", currUpdateObj.getNewValue());
                                    
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return bableFile;
    }
    
    
    /*********************************** END GENERAL PROCESSING FUNCTIONS ************************************/
    
    
    
    
    
    
    
    
    
    /***************************************** ADD/REMOVE FUNCTIONS ******************************************/
    
    
    
    /**
     * FUNCTION [add_DOM_Elements_To_BableFile()]:
     *  - Update the Bable file with new data from a DOM...
     */
    public Document add_DOM_Elements_To_BableFile(Document bableFile, Document DOM) throws Exception
    {
        /** Get all the elements in the DOM */
        NodeList all_EDXBABLE_Structures = DOM.getElementsByTagName("EDXBABLE");
        
        /** for all the EDXBABLES in the DOM */
        for(int i = 0; i < all_EDXBABLE_Structures.getLength(); i++)
        {
            /** get the current EDXBABLE */
            Node currEDXBABLE = all_EDXBABLE_Structures.item(i);
            
            /** add the current node to the bableFile */
            try
            {
                add_EDXBABLE_to_bable_file(bableFile, currEDXBABLE);
            }
            catch(Exception e)
            {
                log.info("EXCEPTION 2");
                throw e;
            }
        }
        return bableFile;
    }
    
    
    
    
    
    
    /**
     * FUNCTION [add_EDXBABLE_to_bable_file()]:
     *  - Add a the passed in node to the bable file.
     *  - If the element exists, it is not replaced.
     */
    public void add_EDXBABLE_to_bable_file(Document bableFile, Node EDXBABLE) throws Exception
    {
        /** Clone the passed in EDXBABLE */
        Node cloneEDXBABLE = EDXBABLE.cloneNode(true);
        
        /** get the ORIGINATOR\NAME of the EDXBABLE passed in */
        String passedInOrigName = xmlInterface.getNodeValue(cloneEDXBABLE, "ORIGINATOR\\NAME");
        
        /** Get the actual value of the passed in Node: ORIGINATOR\\VALUE*/
        String passedInValue = xmlInterface.getNodeValue(cloneEDXBABLE, "ORIGINATOR\\VALUE");
        
        /** get all the EDXBABLEs in the bable file */
        NodeList all_EDXBABLES = bableFile.getElementsByTagName("EDXBABLE");
        
        /** set to true when child is replaced */
        boolean wasFound = false;
        
        String currOrigName = "";
        
        /** for all the EDXBABLES */
        for(int i = 0; i < all_EDXBABLES.getLength(); i++)
        {
            /** get the current EDXBABLE */
            Node currEDXBABLE = all_EDXBABLES.item(i);
            
            /** get the ORIG\NAME of the current EDXBABLE passed in */
            currOrigName = xmlInterface.getNodeValue(currEDXBABLE, "ORIGINATOR\\NAME");
            
            /** if this is the right bable to remove */
            if(currOrigName.equalsIgnoreCase(passedInOrigName))
            {
                /** Found the passed in NODE in the bable file */
                wasFound = true;
                
                /** Get the "ORIG\\VALUES" node for this bable file EDXBABLE structure */
                Node VALUES = xmlInterface.getNode(currEDXBABLE, "VALUES");
                
                if(VALUES != null)
                {
                    /** for all the PAIR objects */
                    NodeList valuesKids = VALUES.getChildNodes();
                    boolean foundPair = false;
                    
                    /** For all the PAIR nodes in this bable file EDXBABLE element */
                    for(int j = 0; j < valuesKids.getLength(); j++)
                    {
                        Node currPAIR = valuesKids.item(j);
                        
                        if(currPAIR.getNodeType() == Node.ELEMENT_NODE)
                        {
                            /** get the value of the current PAIR's ORIG\VALUE node */
                            String origBableValue = xmlInterface.getNodeValue(currPAIR, "ORIG\\VALUE");
                            
                            if(origBableValue.equalsIgnoreCase(passedInValue))
                            {
                                foundPair = true;
                            }
                        }
                    }
                    
                    /** if this current orignal value wasn't found*/
                    if(!foundPair)
                    {
                        /** Pair node was not found in bable file structure, add a new one */
                        Node PAIR = null;
                        try
                        {
                            PAIR = get_blank_PAIR_Node();
                        }
                        catch(Exception e)
                        {
                            log.info("[BableEngine]-add_EDXBABLE_to_bable_file-1 Exception: "+e.getMessage());
                            throw e;
                        }
                        
                        xmlInterface.setElementValue(PAIR, "ORIG\\VALUE", passedInValue);
                        xmlInterface.setElementValue(PAIR, "ORIG\\UNIQUEKEY", "");
                        xmlInterface.setElementValue(PAIR, "DEST\\VALUE", passedInValue);
                        
                        Document valuesOwner = VALUES.getOwnerDocument();
                        Node newPAIR = valuesOwner.importNode(PAIR, true);
                        VALUES.appendChild(newPAIR);
                    }
                    foundPair = false;
                }
            }
        }
        
        
        /** If the passed in node wasn't in the bableFile */
        if(!wasFound)
        {
            /** Append a VALUES structure to the passed in EDXBABLE Node */
            Node modifiedEDXBABLE = null;
            try
            {
                modifiedEDXBABLE = append_VALUES_Node(cloneEDXBABLE);
                
                /** set the ORIGINATOR\VALUE to blank, it is not used anywhere in the bable file*/
                xmlInterface.setElementValue(modifiedEDXBABLE, "ORIGINATOR\\VALUE", "");
            }
            catch(Exception e)
            {
                log.info("[BableEngine]-add_EDXBABLE_to_bable_file-2 Exception: "+e.getMessage());
                throw e;
            }
            
            /** Add the passed in Node to the BableFile */
            NodeList list = bableFile.getElementsByTagName("VALUEPAIRS");
            Node top = list.item(0);
            
            /** Append the modifiedEDXBABLE to the top node in the bable file */
            Node newEDXNode = bableFile.importNode(modifiedEDXBABLE, true);
            
            top.appendChild((Element)newEDXNode);
        }
        wasFound = false;
    }
    
    
    
    
    
    
    
    
    
    
    /**
     * FUNCTION [append_VALUES_Node()]:
     *  - Add a VALUES structure to the passed in Node
     */
    public Node append_VALUES_Node(Node EDXBABLE) throws Exception
    {
        /** read in the template */
        Document VALUESDOM = null;
        try
        {
            VALUESDOM = xmlInterface.readInXMLFile(localDirectoryList.getConfigValue("PROP_VALUES_STRUCTURE"));
        }
        catch(Exception e)
        {
            log.info("EXCEPTION 4");
            throw e;
        }
        /** get the VALUES Node */
        NodeList nl = VALUESDOM.getElementsByTagName("VALUES");
        Node valNode = nl.item(0);
        
        /** Add the ORIGVALUE to the ORIG\VALUE and DEST\VALUE */
        String origVal = xmlInterface.getNodeValue(EDXBABLE, "ORIGINATOR\\VALUE");
        
        xmlInterface.setElementValue(valNode, "PAIR\\ORIG\\VALUE", origVal);
        xmlInterface.setElementValue(valNode, "PAIR\\DEST\\VALUE", origVal);
        
        /** append the VALUES Node to the passed in EDXBABLE */
        Document owner = EDXBABLE.getOwnerDocument();
        Node importNewValNode = owner.importNode(valNode, true);
        EDXBABLE.appendChild(importNewValNode);
        
        return EDXBABLE;
    }
    
    
    
    
    /**
     * FUNCTION [get_blank_PAIR_Node()]:
     *  - Get a blank PAIR node from the template VALUES file.
     */
    public Node get_blank_PAIR_Node() throws Exception
    {
        /** read in the template */
        Document VALUESDOM = null;
        try
        {
            if(bableMethods == null)
            {
                bableMethods = new BableMethods();
            }
            
            if(bableMethods.xmlInterface == null)
            {
                bableMethods.xmlInterface = new XMLFunctions();
            }
            
            VALUESDOM = bableMethods.xmlInterface.readInXMLFile(localDirectoryList.getConfigValue("PROP_VALUES_STRUCTURE"));
        }
        catch(Exception e)
        {
            log.info("EXCEPTION 5");
            throw e;
        }
        /** get the VALUES Node */
        NodeList nl = VALUESDOM.getElementsByTagName("PAIR");
        Node pairNode = nl.item(0);
        
        /** return the PAIR Node */
        return pairNode;
    }
    
    
    
    /**
     * FUNCTION [remove_EDXBABLE_from_bable_file()]:
     *  - Remove a EDXBABLE from the bable file.
     */
    public void remove_EDXBABLE_from_bable_file(Document bableFile, Node EDXBABLE)
    {
        /** get the ORIG\NAME of the EDXBABLE passed in */
        String origName = xmlInterface.getNodeValue(EDXBABLE, "ORIGINATOR\\NAME");
        
        /** get all the EDXBABLEs in the bable file */
        NodeList all_EDXBABLES = bableFile.getElementsByTagName("EDXBABBLE");
        
        /** for all the EDXBABLES */
        for(int i = 0; i < all_EDXBABLES.getLength(); i++)
        {
            /** get the current EDXBABLE */
            Node currEDXBABLE = all_EDXBABLES.item(i);
            
            /** get the ORIG\NAME of the current EDXBABLE passed in */
            String currOrigName = xmlInterface.getNodeValue(currEDXBABLE, "ORIGINATOR\\NAME");
            
            /** if this is the right bable to remove */
            if(currOrigName.equalsIgnoreCase(origName))
            {
                /** get the parent node */
                Node parent = currEDXBABLE.getParentNode();
                
                /** remove the current EDXBABLE */
                parent.removeChild(currEDXBABLE);
            }
        }
    }
    
    
    /***************************************** END ADD/REMOVE FUNCTIONS **************************************/
    
    
    
    
    
    
    
    
    
    
    
    /******************************************* RESOLUTION FUNCTIONS *****************************************/
    
    
    
    /**
     * FUNCTION [resolveCurrentTransaction()]:
     *  - Get values from passed in DOM and put into EDXBable objects.
     *  - Put values back into Bable file and write out file.
     */
    public Document resolveCurrentTransaction(Document bableFile, Document DOM, String bableStructPath) throws Exception
    {
        Document retObj = null;
        
        /** Get all the values from the current DOM and put into EDXBable objects */
        NodeList all_EDXBABLE_Nodes = DOM.getElementsByTagName("EDXBABLE");
        
        /** for all the returned nodes */
        for(int i = 0; i < all_EDXBABLE_Nodes.getLength(); i++)
        {
            /** get the current node */
            Node curr_DOM_EDXBABLE = all_EDXBABLE_Nodes.item(i);
            
            /** get the values for this node */
            EDXBable currEDXBableObj = null;
            try
            {
                currEDXBableObj = resolveCurrentElement(bableFile, curr_DOM_EDXBABLE);
            }
            catch(Exception e)
            {
                log.info("[BableEngine]-resolveCurrentTransaction- Exception:"+e.getMessage());
                throw e;
            }
            
            /**
             * CASE 1:
             * The current value needs to be stored in the bable file but it did not
             * resolve.
             * There was no resolution value in the bable file for this element value.
             * - Set errorWithinDOM to true so that the transaction is sent to TradeRoute.
             */
            //			if (currEDXBableObj.DESTINATION.getRESOLVEDVALUE().equalsIgnoreCase(""))
            //			{
            //				currEDXBableObj.DESTINATION.setRESOLVETYPE("doConfirm");
            //			}
                        /*else
                        {
                                /**
                         * CASE 2:
                         * A resolution value was found for this element value. Check if this element needs to be resolved
                         * and set the errorWithinDOM flag accordingly.
                         */
            
            /** Don't confirm this element because it has been resolved...*/
                        /*	if (currEDXBableObj.DESTINATION.getRESOLVETYPE().equalsIgnoreCase("doResolve"))
                                        {
                                        currEDXBableObj.DESTINATION.setRESOLVETYPE("dontResolve");
                                }
                         
                                if (currEDXBableObj.DESTINATION.getSTOREVALUEINBABLE().equalsIgnoreCase("autoaddandconfirm"))
                                        {
                                        /** Set the error flag so that this gets sent to Tradeforms */
                        /*		errorWithinDOM = true;
                                }
                        }*/
            
            /** write the values from the bable file back into the current Node */
            bableMethods.writeEDXBableValuesToNode(curr_DOM_EDXBABLE, currEDXBableObj);
        }
        
        retObj = DOM;
        
        return retObj;
    }
    
    
    
    
    /**
     * FUNCTION [resolveCurrentElement()]:
     *  - Get all the values in the bable file for the passed in search element.
     */
    public EDXBable resolveCurrentElement(Document bableFile, Node passedInEDXBABLE) throws Exception
    {
        EDXBable retObj = new EDXBable();
        
        /** get the original name & value of the passed in Node */
        String passedInNodeName = xmlInterface.getNodeValue(passedInEDXBABLE, "ORIGINATOR\\NAME");
        String passedInNodeValue = xmlInterface.getNodeValue(passedInEDXBABLE, "ORIGINATOR\\VALUE");
        
        /** Get a list of all the EDXBABLE objects in the bable file */
        NodeList edxBableElements = bableFile.getElementsByTagName("EDXBABLE");
        
        /** for all the EDXBABLE elements in the bable file */
        for(int i = 0; i < edxBableElements.getLength(); i++)
        {
            /** get the current EDXBABLE node*/
            Node curr_bable_file_EDXBABLE = edxBableElements.item(i);
            
            /** get the ORIRG\NAME value */
            String OrigName = xmlInterface.getNodeValue(curr_bable_file_EDXBABLE, "ORIGINATOR\\NAME");
            
            /** If this is the right <EDXBABLE> node */
            if (OrigName.equalsIgnoreCase(passedInNodeName))
            {
                /** copy values from the bable file EDXBABLE structure Node into a EDXBable object */
                retObj = bableMethods.readEDXBableValuesFromNode(curr_bable_file_EDXBABLE, passedInNodeValue);
                
                /** Work out the current key Value using the key Rule */
                String currKeyRule = xmlInterface.getNodeValue(curr_bable_file_EDXBABLE, "KEYRULE");
                
                /** if the keyRule is not blank */
                if(!currKeyRule.equalsIgnoreCase(""))
                {
                    /** get the key value */
                    String keyValue = xmlInterface.getKeyRuleValue(curr_bable_file_EDXBABLE.getParentNode(), currKeyRule.trim());
                    
                    /** set the retObj keyValue */
                    retObj.DESTINATION.setCURRKEYVALUE(keyValue.trim());
                }
                
                NodeList children = curr_bable_file_EDXBABLE.getChildNodes();
                
                /** For all the currEDXBABLE's children */
                for(int j = 0; j < children.getLength(); j++)
                {
                    Node currChild = children.item(j);
                    
                    if(currChild.getNodeName().equalsIgnoreCase("VALUES"))
                    {
                        /** Set the RESOLVEDVALUE value */
                        try
                        {
                            retObj.DESTINATION.setRESOLVEDVALUE(processVALUES(bableFile, currChild, passedInNodeValue, retObj.DESTINATION.getCURRKEYVALUE()));
                            
                        }
                        catch(Exception e)
                        {
                            log.info("EXCEPTION 1: passedInNodeValue: "+passedInNodeValue+", curr key value: "+retObj.DESTINATION.getCURRKEYVALUE());
                            e.printStackTrace();
                            throw e;
                        }
                    }
                }
                break;
            }
        }
        return retObj;
    }
    
    
    
    /**
     * FUNCTION [processVALUES()]:
     *  - Deal with the Values nodes in a bable structure.
     */
    public String processVALUES(Document bableFile, Node values, String searchValue, String keyValue) throws Exception
    {
        /** Variable to return */
        String ret = "";
        
        /** If no search value was passed in */
        if (searchValue.equalsIgnoreCase(""))
        {
                return ret;
        }
        
        /** boolean to hold status */
        boolean foundRightValue = false;
        
        /** Get the children of the values Node */
        NodeList valuesChildren = values.getChildNodes();
        
        /** For all children of values */
        for (int i = 0; i < valuesChildren.getLength(); i++)
        {
            /** Get the current kid */
            Node currentValuesKid = valuesChildren.item(i);
            
            /** If this is the PAIR node */
            if (currentValuesKid.getNodeName().equalsIgnoreCase("PAIR"))
            {
                
                /** variables to hold orig value & key */
                String origValue = xmlInterface.getNodeValue(currentValuesKid, "ORIG\\VALUE");
                String origUniqueKey = xmlInterface.getNodeValue(currentValuesKid, "ORIG\\UNIQUEKEY");
                
                /** If this is the search value we are looking for */
                if ((searchValue.equalsIgnoreCase(origValue))
                && (keyValue.equalsIgnoreCase(origUniqueKey)))
                {
                    foundRightValue = true;
                    
                    /** get the destination value */
                    String resValue = xmlInterface.getNodeValue(currentValuesKid, "DEST\\VALUE");
                    
                    ret = resValue.trim();
                    
                    //					if(ret.equals(""))
                    //					{
                    //						ret = xmlInterface.getNodeValue(currentValuesKid, "ORIG\\VALUE");
                    //					}
                    
                }
            }
        }
        
        /** If we didn't find the search value in the bable file, add a new PAIR object
         *  with this new value. So it's there for next time.
         */
        if (!foundRightValue)
        {
            
            Node PAIR = null;
            try
            {
                PAIR = get_blank_PAIR_Node();
            }
            catch(Exception e)
            {
                log.info("EXCEPTION 7");
                throw e;
            }
            
            xmlInterface.setElementValue(PAIR, "ORIG\\VALUE", searchValue);
            xmlInterface.setElementValue(PAIR, "ORIG\\UNIQUEKEY", keyValue);
            xmlInterface.setElementValue(PAIR, "DEST\\VALUE", "");
            
            Document valuesOwner = values.getOwnerDocument();
            Node newPAIR = valuesOwner.importNode(PAIR, true);
            values.appendChild(newPAIR);
            
        }
        
        /** return the result */
        return ret;
    }
    
    
    /***************************************** END RESOLUTION FUNCTIONS **************************************/
    
    
    
    
    
    
    
    
    
    /**
     * FUNCTION [main()]:
     *  - Only used for debugging...
     */
    public static void main(String[] args)
    {
                /*String bable_struct_path = "C:\\OSCAR\\JavaProjects\\TradeRouteServer\\dataTradeRoute\\xml_structures\\Template_Bable_Structure.xml";
                 
                BableEngine bableEngine1 = new BableEngine();
                 
                Document XML = bableEngine1.bableMethods.xmlInterface.readInXMLFile("c:\\temp\\Test_Purchase_Order_1.xml");
                 
                /** Bableise the current transaction */
                /*Document bableisedTrans = bableEngine1.bableMethods.addBableStructuresToDOM(XML, bable_struct_path, "BUSOBJ");
                 
                /** write out the DOM */
        //bableEngine1.bableMethods.xmlInterface.writeFilledDOM(bableisedTrans, "c:\\temp\\Test_Purchase_Order_1_babled.xml");
        
                /*Vector list = bableEngine1.getNewValuesFromDocument(XML);
                 
                Enumeration li = list.elements();
                 
                while(li.hasMoreElements())
                {
                        UpdateObject uo = (UpdateObject) li.nextElement();
                        log.info(uo.print());
                 
                }*/
        
    }
    
}