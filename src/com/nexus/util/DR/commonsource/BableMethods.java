package com.nexus.util.DR.commonsource;

import org.w3c.dom.*;
import java.io.*;
import com.nexus.util.DR.edxbable.*;
import com.nexus.util.DR.misc.*;
import org.apache.log4j.Logger;

/**
 * Class BableMethods
 * - Holds all functions related to reading, writing, modifying bable structures
 */
public class BableMethods
{
    Logger log=Logger.getLogger(BableMethods.class);
    /** Variable to hold XMLFunctions object */
    XMLFunctions xmlInterface = null;
    
    /** Variable to hold name of current Bable file */
    String bableFileName = "";
    
    /** Variable to hold global logfile */
    public Debug logFile;
    
    
    
    
    
    /**
     * Constructor for BableMethods
     */
    public BableMethods()
    {
        xmlInterface = new XMLFunctions();
        xmlInterface.debug = true;
    }
    
    
    /**
     * FUNCTION [setLogFile()]:
     *  - Set the log file for debugging.
     */
    public void setLogFile(Debug origLogFile)
    {
        logFile = origLogFile;
    }
    
    
    /******************************************* LAOD/CREATE a Bable File ************************************/
    
    /**
     * FUNCTION [loadBableFileIntoDOM()]:
     *  - Load the correct bable file into a DOM object.
     *  - If it doesn't exist, build it.
     */
    public Document loadBableFileIntoDOM(String dir, String fileName, String templateFilePath) throws Exception
    {
        /** Create the bable file name */
        bableFileName = dir + "\\" + fileName;
        
        /** Get the current bable file */
        File currBableFile = new File(bableFileName);
        
        /** If the current bable file doesn't exist, create it.*/
        if (!currBableFile.exists())
        {
            //log.info("-------------> The bable File Didn't exist ."+bableFileName);
            
            /** The required Bable file didn't exist so create it...*/
            try
            {
                /** Get the template file */
                File templateFile = new File(templateFilePath);
                
                /** Copy the template file to new Bable file...*/
                FileReader fr = new FileReader(templateFile);
                BufferedReader br = new BufferedReader(fr);
                String contents = "";
                String line = null;
                
                while ((line = br.readLine()) != null)
                {
                    contents += line + "\n";
                }
                br.close();
                
                /** write the updated XML to the data_in directory*/
                FileWriter outWriter = new FileWriter(currBableFile);
                outWriter.write(contents);
                outWriter.close();
            }
            catch (IOException io)
            {
                logFile.write("loadBableFileIntoDOM IOException: " + io);
            }
        }
        
        /** Load the correct bable file into a DOM object */
        Document bableFile = null;
        try
        {
            bableFile = xmlInterface.readInXMLFile(bableFileName);
        }
        catch(Exception e)
        {
            throw e;
        }
        
        if (bableFile == null)
        {
            /** Error loading bable File */
            logFile.write("************* ERROR LOADING bable FILE *****************");
        }
        
        return bableFile;
    }
    
    /********************************************* END LAOD/CREATE a Bable File ****************************/
    
    
    
    
    
    
    /*********************************************** Bableising Functions ************************************/
    
    
    /**
     * FUNCTION [AddBableStructuresToDOM]:
     * - Adds the bable structre found in bableStructPath to all the elements under
     * - the passed in rootNodeName.
     */
    public Document addBableStructuresToDOM(Document DOM, String bableStructPath, String rootNodeName) throws Exception
    {
        Document bableisedIDDOM = null;
        Document bableisedDOM = null;
        
        try
        {
            
            /** Read in the bable structure */
            Document Structure = null;
            try
            {
                Structure = xmlInterface.readInXMLFile(bableStructPath);
            }
            catch(Exception e)
            {
                log.info("Excpetion: (addBableStructuresToDOM):  Couldn't read in bable structure file.");
                e.printStackTrace();
                throw e;
            }
            NodeList nl = Structure.getElementsByTagName("EDXBABLE");
            Node struct = nl.item(0);
            
            /** get all the children of the rootNode to bableise them*/
            NodeList nl2 = DOM.getElementsByTagName(rootNodeName);
            Node rootNode = nl2.item(0);
            NodeList nl3 = rootNode.getChildNodes();
            
            /** Bableise the passed in DOM */
            bableisedDOM = bableiseDOM(DOM, nl3, struct);
            
            /** Add unique ID values to all the EDXID attributes in the current Transaction */
            bableisedIDDOM = addUniqueIDToBables(bableisedDOM);
            
            
        }
        catch(Exception e2)
        {
            log.info("Major Excpetion: ");
            e2.printStackTrace();
        }
        /** return the bableised DOM*/
        return bableisedDOM;
        
    }
    
    /**
     * FUNCTION [bableiseDOM()]:
     *  - Recursive function to add ORIGINAL NAME & VALUE to the bable structure and then add
     *  - the structure to the element nodes.
     */
    public Document bableiseDOM(Document DOM, NodeList nl, Node Struct)
    {
        try
        {
            /** For all the nodes to bableise */
            for (int i = 0; i < nl.getLength(); i++)
            {
                
                /** Get the current node */
                Node currentNode = nl.item(i);
                
                /** If the current node is an element */
                if (currentNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    /** Call this function again if this node has children */
                    if (currentNode.hasChildNodes())
                    {
                        /** Get all the child nodes*/
                        NodeList children = currentNode.getChildNodes();
                        
                        /** This recursive call will explore all the child nodes...*/
                        bableiseDOM(DOM, children, Struct);
                    }
                    
                    /** Work out the full original Name of the node  */
                    String currName = currentNode.getNodeName();
                    Node tempParent = currentNode.getParentNode();
                    
                    /** Get the names of all the parent nodes up untill BUSOBJ */
                    while (tempParent.getNodeName() != "BUSOBJ")
                    {
                        String parentName = tempParent.getNodeName();
                        currName = parentName + "\\" + currName;
                        tempParent = tempParent.getParentNode();
                    }
                    
                    /** import the blank strucutre into the current DOM...*/
                    Node currStruct = DOM.importNode(Struct, true);
                    
                    /** Set the full name */
                    String fullName = currName;
                    
                    /** Add the orig value to the currStruct node */
                    String origVal = "";
                    if (currentNode.hasChildNodes())
                    {
                        origVal = currentNode.getFirstChild().getNodeValue();
                    }
                    
                    if (origVal == null)
                    {
                        origVal = "";
                    }
                    
                    /** Trim the original value */
                    origVal = origVal.trim();
                    
                    /** Set the original name */
                    xmlInterface.setElementValue(currStruct, "ORIGINATOR\\NAME", fullName);
                    /** Set the original value */
                    xmlInterface.setElementValue(currStruct, "ORIGINATOR\\VALUE", origVal);
                    
                    /** get the first ELEMENT kid of this node*/
                    Node firstKid = currentNode.getFirstChild();
                    
                    if(firstKid != null)
                    {
                        if(firstKid.getNodeType() == Node.TEXT_NODE)
                        {
                            /** check for next sibling */
                            Node nextSibling = firstKid.getNextSibling();
                            
                            if(nextSibling != null)
                            {
                                if(nextSibling.getNodeType() == Node.ELEMENT_NODE)
                                {
                                    currentNode.insertBefore(currStruct, firstKid);
                                }
                            }
                            else
                            {
                                currentNode.appendChild(currStruct);
                            }
                        }
                        else
                        {
                            if(firstKid.getNodeType() == Node.ELEMENT_NODE)
                            {
                                currentNode.insertBefore(currStruct, firstKid);
                            }
                        }
                    }
                    else
                    {
                        currentNode.appendChild(currStruct);
                    }
                } /** End if ELEMENT */
            } /** End for loop */
            
        }
        catch(Exception e)
        {
            log.info("Exception in BableiseDOM()");
            e.printStackTrace();
        }
        
        return DOM;
    }
    
    
    
    /**
     * FUNCTION [addUniqueIDToBables()]:
     *  - Add unique ID values to all the EDXID attributes in the current Transaction.
     */
    public Document addUniqueIDToBables(Document DOM)
    {
        /** get all the EDXBABLE nodes */
        NodeList allEDXBABLES = DOM.getElementsByTagName("EDXBABLE");
        
        /** For all the EDXBABLE elements */
        for (int i = 0; i < allEDXBABLES.getLength(); i++)
        {
            /** Get the current EDXBABLE */
            Node currEDXBABLE = allEDXBABLES.item(i);
            
            /** Set the unique value */
            Integer counter = new Integer(i);
            
            xmlInterface.setElementValue(currEDXBABLE, "EDXID", counter.toString());
        }
        
        return DOM;
    }
    
    
    /*************************************** END Bableising Functions ***********************************/
    
    
    
    
    
    
    
    
    
    
    
    /************************************* READ/WRITE EDXBable Functions *********************************/
    /**
     * FUNCTION [readEDXBableValuesFromNode()]: RESOLVE A NODE
     *  - Read all the values from a curr_bable_file_EDXBABLE node into a EDXBable Object.
     */
    
    public EDXBable readEDXBableValuesFromNode(Node curr_bable_file_EDXBABLE, String passedInValue)
    {
        EDXBable EDXObj = new EDXBable();
        
        /** set the ORIGNATOR value of this object to that of the passedInNodeName */
        EDXObj.ORIGINATOR.setVALUE(passedInValue);
        
        /** Get the children of the bableised Node */
        NodeList children = curr_bable_file_EDXBABLE.getChildNodes();
        
        /** For all the child Nodes */
        for(int i = 0; i < children.getLength(); i++)
        {
            /** Get the current child */
            Node currChild = children.item(i);
            
            /** Get the current child's name */
            String currChildName = currChild.getNodeName();
            
            if(currChildName.equalsIgnoreCase("EDXID"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.setEDXID(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("KEYRULE"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.setKEYRULE(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("ORIGINATOR"))
            {
                EDXObj = readOriginatorNode(EDXObj, currChild);
            }
            
            if(currChildName.equalsIgnoreCase("DESTINATION"))
            {
                EDXObj = readDestinationNode(EDXObj, currChild);
            }
            
            if(currChildName.equalsIgnoreCase("VALUES"))
            {
                EDXObj = readValuesNode(EDXObj, currChild, passedInValue);
            }
        }
        
        return EDXObj;
    }
    
    
    
    /**
     * FUNCTION [writeEDXBableValuesToNode()]:
     *  - write the values from the updateObj to the passed in Node.
     */
    public void writeEDXBableValuesToNode(Node bableisedNode, EDXBable updateObj)
    {
        
        /** Get the children of the bableised Node */
        NodeList children = bableisedNode.getChildNodes();
        
        /** For all the child Nodes */
        for(int i = 0; i < children.getLength(); i++)
        {
            /** Get the current child */
            Node currChild = children.item(i);
            
            /** Get the current child's name */
            String currChildName = currChild.getNodeName();
            
                        /*if(currChildName.equalsIgnoreCase("EDXID"))
                        {
                                /** writing from EDXBable object into node */
                                /*if(currChild.hasChildNodes())
                                {
                                        currChild.getFirstChild().setNodeValue(updateObj.getEDXID());
                                }
                                else
                                {
                                        currChild.appendChild(bableisedNode.getOwnerDocument().createTextNode(updateObj.getEDXID()));
                                }
                        }*/
            
            if(currChildName.equalsIgnoreCase("KEYRULE"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.getKEYRULE());
                }
                else
                {
                    currChild.appendChild(bableisedNode.getOwnerDocument().createTextNode(updateObj.getKEYRULE()));
                }
            }
            
            if(currChildName.equalsIgnoreCase("ORIGINATOR"))
            {
                writeOriginatorNode(currChild, updateObj);
            }
            
            if(currChildName.equalsIgnoreCase("DESTINATION"))
            {
                writeDestinationNode(currChild, updateObj);
            }
        }
    }
    
    
    /**
     * FUNCTION [readOriginatorNode()]:
     *  - Read the Originator node into a EDXBable structure .
     */
    public EDXBable readOriginatorNode(EDXBable EDXObj, Node Orig)
    {
        NodeList children = Orig.getChildNodes();
        
        for(int i = 0; i < children.getLength(); i++)
        {
            /** Get the current child */
            Node currChild = children.item(i);
            
            /** Get the current child's name */
            String currChildName = currChild.getNodeName();
            
            /** only read the NAME and DATATYPE nodes, do not replace VALUE node*/
            if(currChildName.equalsIgnoreCase("NAME"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.ORIGINATOR.setNAME(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("DATATYPE"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.ORIGINATOR.setDATATYPE(currChild.getFirstChild().getNodeValue());
                }
            }
        }
        
        return EDXObj;
    }
    
    
    
    
    /**
     * FUNCTION [writeOriginatorNode()]:
     *  - Write from the updateObj into the Originator node.
     */
    public void writeOriginatorNode(Node Orig, EDXBable updateObj)
    {
        /** Get the kids of the Originator node passed in */
        NodeList children = Orig.getChildNodes();
        
        for(int i = 0; i < children.getLength(); i++)
        {
            /** Get the current child */
            Node currChild = children.item(i);
            
            /** Get the current child's name */
            String currChildName = currChild.getNodeName();
            
            if(currChildName.equalsIgnoreCase("NAME"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.ORIGINATOR.getNAME());
                }
                else
                {
                    currChild.appendChild(Orig.getOwnerDocument().createTextNode(updateObj.ORIGINATOR.getNAME()));
                }
            }
            
            if(currChildName.equalsIgnoreCase("DATATYPE"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.ORIGINATOR.getDATATYPE());
                }
                else
                {
                    currChild.appendChild(Orig.getOwnerDocument().createTextNode(updateObj.ORIGINATOR.getDATATYPE()));
                }
            }
        }
    }
    
    /**
     * FUNCTION [readDestinationNode()]:
     *  - Read the Dest node into a EDXBable structure .
     */
    public EDXBable readDestinationNode(EDXBable EDXObj, Node Dest)
    {
        /** Get the kids of the Originator node passed in */
        NodeList children = Dest.getChildNodes();
        
        for(int i = 0; i < children.getLength(); i++)
        {
            /** Get the current child */
            Node currChild = children.item(i);
            
            /** Get the current child's name */
            String currChildName = currChild.getNodeName();
            
            if(currChildName.equalsIgnoreCase("ENGLISHNAME"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.DESTINATION.setENGLISHNAME(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("HELPNOTE"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.DESTINATION.setHELPNOTE(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("DATATYPE"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.DESTINATION.setDATATYPE(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("REQUIRED"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.DESTINATION.setREQUIRED(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("STOREVALUEINBABLE"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.DESTINATION.setSTOREVALUEINBABLE(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("RESOLVETYPE"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.DESTINATION.setRESOLVETYPE(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("SCHEMATOUSE"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.DESTINATION.setSCHEMATOUSE(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("DISPLAYDETAILS"))
            {
                /** reading from node into EDXBable object */
                EDXObj = readDisplayDetailsNode(EDXObj, currChild);
            }
        }
        
        return EDXObj;
    }
    
    
    /**
     * FUNCTION [writeDestinationNode()]:
     *  - Write from the updateObj into the Dest node.
     */
    public void writeDestinationNode(Node Dest, EDXBable updateObj)
    {
        /** Get the kids of the Originator node passed in */
        NodeList children = Dest.getChildNodes();
        
        for(int i = 0; i < children.getLength(); i++)
        {
            /** Get the current child */
            Node currChild = children.item(i);
            
            /** Get the current child's name */
            String currChildName = currChild.getNodeName();
            
            if(currChildName.equalsIgnoreCase("ENGLISHNAME"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.getENGLISHNAME());
                }
                else
                {
                    currChild.appendChild(Dest.getOwnerDocument().createTextNode(updateObj.DESTINATION.getENGLISHNAME()));
                }
            }
            
            if(currChildName.equalsIgnoreCase("HELPNOTE"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.getHELPNOTE());
                }
                else
                {
                    currChild.appendChild(Dest.getOwnerDocument().createTextNode(updateObj.DESTINATION.getHELPNOTE()));
                }
            }
            
            if(currChildName.equalsIgnoreCase("DATATYPE"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.getDATATYPE());
                }
                else
                {
                    currChild.appendChild(Dest.getOwnerDocument().createTextNode(updateObj.DESTINATION.getDATATYPE()));
                }
            }
            
            if(currChildName.equalsIgnoreCase("REQUIRED"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.getREQUIRED());
                }
                else
                {
                    currChild.appendChild(Dest.getOwnerDocument().createTextNode(updateObj.DESTINATION.getREQUIRED()));
                }
            }
            
            if(currChildName.equalsIgnoreCase("STOREVALUEINBABLE"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.getSTOREVALUEINBABLE());
                }
                else
                {
                    currChild.appendChild(Dest.getOwnerDocument().createTextNode(updateObj.DESTINATION.getSTOREVALUEINBABLE()));
                }
            }
            
            if(currChildName.equalsIgnoreCase("SCHEMATOUSE"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.getSCHEMATOUSE());
                }
                else
                {
                    currChild.appendChild(Dest.getOwnerDocument().createTextNode(updateObj.DESTINATION.getSCHEMATOUSE()));
                }
            }
            
            
            if(currChildName.equalsIgnoreCase("RESOLVETYPE"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.getRESOLVETYPE());
                }
                else
                {
                    currChild.appendChild(Dest.getOwnerDocument().createTextNode(updateObj.DESTINATION.getRESOLVETYPE()));
                }
            }
            
            /** Only set the CURRKEYVALUE and RESOLVEDVALUE when writing to a node */
            if(currChildName.equalsIgnoreCase("CURRKEYVALUE"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.getCURRKEYVALUE());
                }
                else
                {
                    currChild.appendChild(Dest.getOwnerDocument().createTextNode(updateObj.DESTINATION.getCURRKEYVALUE()));
                }
            }
            
            if(currChildName.equalsIgnoreCase("RESOLVEDVALUE"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.getRESOLVEDVALUE());
                }
                else
                {
                    currChild.appendChild(Dest.getOwnerDocument().createTextNode(updateObj.DESTINATION.getRESOLVEDVALUE()));
                }
            }
            
            if(currChildName.equalsIgnoreCase("DISPLAYDETAILS"))
            {
                /** writing from EDXBable object into node */
                writeDisplayDetailsNode(currChild, updateObj);
            }
        }
    }
    
    
    
    
    /**
     * FUNCTION [writeDisplayDetailsNode()]:
     *  - Write from the updateObj into the Disp node.
     */
    public void writeDisplayDetailsNode(Node Disp, EDXBable updateObj)
    {
        /** Get the kids of the Originator node passed in */
        NodeList children = Disp.getChildNodes();
        
        for(int i = 0; i < children.getLength(); i++)
        {
            /** Get the current child */
            Node currChild = children.item(i);
            
            /** Get the current child's name */
            String currChildName = currChild.getNodeName();
            
            if(currChildName.equalsIgnoreCase("BGCOLOUR"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.DISPLAYDETAILS.getBgColour());
                }
                else
                {
                    currChild.appendChild(Disp.getOwnerDocument().createTextNode(updateObj.DESTINATION.DISPLAYDETAILS.getBgColour()));
                }
            }
            
            if(currChildName.equalsIgnoreCase("FONTCOLOUR"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.DISPLAYDETAILS.getFontColour());
                }
                else
                {
                    currChild.appendChild(Disp.getOwnerDocument().createTextNode(updateObj.DESTINATION.DISPLAYDETAILS.getFontColour()));
                }
            }
            
            if(currChildName.equalsIgnoreCase("FONTSIZE"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.DISPLAYDETAILS.getFontSize());
                }
                else
                {
                    currChild.appendChild(Disp.getOwnerDocument().createTextNode(updateObj.DESTINATION.DISPLAYDETAILS.getFontSize()));
                }
            }
            
            if(currChildName.equalsIgnoreCase("DISPLAYINTREE"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.DISPLAYDETAILS.getDisplayInTree());
                }
                else
                {
                    currChild.appendChild(Disp.getOwnerDocument().createTextNode(updateObj.DESTINATION.DISPLAYDETAILS.getDisplayInTree()));
                }
            }
            
            if(currChildName.equalsIgnoreCase("DISPLAYINCONTENT"))
            {
                /** writing from EDXBable object into node */
                if(currChild.hasChildNodes())
                {
                    currChild.getFirstChild().setNodeValue(updateObj.DESTINATION.DISPLAYDETAILS.getDisplayInContent());
                }
                else
                {
                    currChild.appendChild(Disp.getOwnerDocument().createTextNode(updateObj.DESTINATION.DISPLAYDETAILS.getDisplayInContent()));
                }
            }
        }
    }
    
    
    /**
     * FUNCTION [readDisplayDetailsNode()]:
     *  - Read the Disp node into a EDXBable structure.
     */
    public EDXBable readDisplayDetailsNode(EDXBable EDXObj, Node Disp)
    {
        /** Get the kids of the Originator node passed in */
        NodeList children = Disp.getChildNodes();
        
        for(int i = 0; i < children.getLength(); i++)
        {
            /** Get the current child */
            Node currChild = children.item(i);
            
            /** Get the current child's name */
            String currChildName = currChild.getNodeName();
            
            if(currChildName.equalsIgnoreCase("BGCOLOUR"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.DESTINATION.DISPLAYDETAILS.setBgColour(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("FONTCOLOUR"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.DESTINATION.DISPLAYDETAILS.setFontColour(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("FONTSIZE"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.DESTINATION.DISPLAYDETAILS.setFontSize(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("DISPLAYINTREE"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.DESTINATION.DISPLAYDETAILS.setDisplayInTree(currChild.getFirstChild().getNodeValue());
                }
            }
            
            if(currChildName.equalsIgnoreCase("DISPLAYINCONTENT"))
            {
                /** reading from node into EDXBable object */
                if(currChild.hasChildNodes())
                {
                    EDXObj.DESTINATION.DISPLAYDETAILS.setDisplayInContent(currChild.getFirstChild().getNodeValue());
                }
            }
        }
        
        return EDXObj;
    }
    
    
    
    
    
    /**
     * FUNCTION [readValuesNode()]:
     *  - Read the Originator node into a EDXBable structure .
     */
    public EDXBable readValuesNode(EDXBable EDXObj, Node Values, String passedInValue)
    {
        /** get all the children of the VALUES node */
        NodeList valuesChildren = Values.getChildNodes();
        
        /** for all the PAIR nodes under the passsed in values node */
        for(int i = 0; i < valuesChildren.getLength(); i++)
        {
            Node currPAIR = valuesChildren.item(i);
            
            if(currPAIR.getNodeType() == Node.ELEMENT_NODE)
            {
                String currPairValue = xmlInterface.getNodeValue(currPAIR, "ORIG\\VALUE");
                
                /** if this is the right PAIR */
                if(currPairValue.equalsIgnoreCase(passedInValue))
                {
                    /** get the dest value */
                    String currPairDestValue = xmlInterface.getNodeValue(currPAIR, "DEST\\VALUE");
                    
                    /** set it in the EDXBable object */
                    EDXObj.DESTINATION.setRESOLVEDVALUE(currPairDestValue.trim());
                }
            }
        }
        
        return EDXObj;
    }
    
    /********************************** END READ/WRITE EDXBable Functions ******************************/
    
    
        
    
    public static void main(String[] args) throws Exception
    {
        
        
        
        /** test getElementValue function */
                /*Document bableStruct = BM.xmlInterface.readInXMLFile(structPath);
                NodeList nl = bableStruct.getElementsByTagName("EDXBABLE");
                Node EDXBABLE = nl.item(0);
                log.info("Returned Value: "+BM.getElementValue(EDXBABLE, "KEYRULE"));*/
        
        
    }
}