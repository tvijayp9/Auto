/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nexus.web.DR;

import com.nexus.services.DataResolutionService;
import com.nexus.util.DR.commonsource.XMLFunctions;
import com.nexus.util.DR.edxbable.EDXBable;
import com.nexus.util.DR.edxbable.TreeView;
import com.nexus.util.DR.traderoute.Trans_Disp_Bean;
import com.nexus.util.DR.traderoute.TransactionCentre;
import com.nexus.util.DR.traderoute.Transaction_Manager;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.w3c.dom.Document;


/**
 *
 * @author Administrator
 */
public class DataResolutionAction extends ActionSupport implements ServletRequestAware {
    Logger log=Logger.getLogger(DataResolutionAction.class);
    private String fileName;
    private String type;
    private String newFileName;
    private String bottomSource;
    private String ack_file_name;
    private String documentType;
    private String documentCreator;
    private String xmlString;
    private String errorText;
    private String treeOutput;
    private String id;
    private String eid;
    private String question;
    private ArrayList data=new ArrayList();
    private boolean flag;
    HttpSession session;
     private Document currentTransactionDOM = null;
     private XMLFunctions xmlInterface = new XMLFunctions();;

    private HttpServletRequest request;
    private Transaction_Manager tManager=new Transaction_Manager();
    private TransactionCentre transCentre;
    private Trans_Disp_Bean tdBean;
    private DataResolutionService dataResolutionService;
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
    
      public String execute() throws Exception {
          session = request.getSession();
            id = request.getParameter("id");
    log.info("ID in DRAction = "+id);
    transCentre=new TransactionCentre();
	transCentre.initialise();
    transCentre.setTransKey(id);
Trans_Disp_Bean tr=new Trans_Disp_Bean();

//        fileName=transCentre.getTransaction();
//	    type=transCentre.getTransactionType();

        fileName=dataResolutionService.getTransactionId(id);
	    type=dataResolutionService.getTransactionType(id);


    log.info("fileName DRAction= "+fileName+"..type.."+type);
//    tdBean = tManager.createNewDisplayObject(getFileName(), getType(), null, null);
    tdBean = dataResolutionService.createNewDisplayObject(getFileName(), getType(), null, null);
    log.info("e1 value in Action -=="+tdBean.getTreeOutput());
    log.info("getXMLString in Action -=="+tdBean.getXMLString());
    currentTransactionDOM=tdBean.getCurrentTransactionDOM();
    session.setAttribute("currentTransactionDOM", currentTransactionDOM);
    session.setAttribute("button","");//after clicking on print button,the element id should be remain same..
    treeOutput=tdBean.getTreeOutput();
    xmlString=tdBean.getXMLString();
    errorText=tdBean.getErrorText();
   // log.info("xmlString in Action -=="+xmlString);
	    newFileName=tdBean.getLastFileInConversation();
	    documentType=tdBean.getDocumentType();
	    documentCreator=tdBean.getTransactionOriginator();
        session.setAttribute("tdbean", tdBean);
log.info("newFileName in DRAction = "+newFileName+"....errorText="+errorText+"...documentType="+documentType+"..documentCreator="+documentCreator);
        if(documentType.equalsIgnoreCase("PurchaseOrder"))
        {
            if(session.getAttribute("loginpage").equals("kalamunda"))
            {
                bottomSource = "pages/resolution/Trans_Disp_PO_Bottom_kalamunda.htm";
            }
            else
            {
                bottomSource = "pages/resolution/Trans_Disp_PO_Bottom.htm";
            }
        }
        else if(documentType.equalsIgnoreCase("ChangeOrder"))
        {
            bottomSource = "pages/resolution/Trans_Disp_CO_Bottom.htm";
        }
        else if(documentType.equalsIgnoreCase("ExportDocument"))
        {
            bottomSource = "pages/resolution/Trans_Disp_EXP_Bottom.htm";
        }

        if(!errorText.equals(""))
            tManager.removeDisplayObject(newFileName);
        session.setAttribute("errorText", errorText);
        session.setAttribute("bottomSource", bottomSource);
        session.setAttribute("newFileName", newFileName);
        session.setAttribute("xmlString", xmlString);
        return INPUT;
    }

      public String selectElement(){
                String buttonClicked = request.getParameter("buttonClicked");
				String transType = request.getParameter("transType");
				log.info(".2selectElement..buttonClicked.."+buttonClicked+"..transType.."+transType);
                session = request.getSession();
                // get the right display object
				//Trans_Disp_Bean tdBean = tManager.getObjectFromList(fileName);
                Trans_Disp_Bean tdBean=(Trans_Disp_Bean)session.getAttribute("tdbean");
                log.info(".2.selectElement.tdbean.fileName..."+tdBean.getFileName());
				// check for errors
				String errorText = tdBean.getErrorText();
                String reason="";
                
                TreeView treeView=null;
                String validError=null;
                String dispDetails =null;
                String fieldName = null;
                String resolvedValue =null;
                String origValue=null;
                String schemaToUse =null;
                String confirmType =null;
                String bgcolor = null;
                String tickType=null;
                String edxID =null;

                if(errorText.equals(""))
				{
					// get the enumeration of parameters passed in
					int counter = 0;
//					Enumeration requestParamNames = request.getParameterNames();
//					while(requestParamNames.hasMoreElements())
//					{
//						counter++;
//						requestParamNames.nextElement();
//					}
//					log.info("counter.."+counter);
					Enumeration pageBody = null;
//					if(counter == 3)
//					{
                         log.info("inside counter==3.."+eid);
						pageBody = tdBean.getTransactionObjects(eid);
//					}
//					else
//					{
//                         log.info("not inside counter==3..");
//						// save the current objects
//						Vector retList = new Vector();
//						Enumeration pNames = request.getParameterNames();
//
//						while(pNames.hasMoreElements())
//						{
//							UpdateObject upObj = new UpdateObject();
//							String pName = (String)pNames.nextElement();
//							String pValue = request.getParameter(pName);
//							upObj.setParentName(pName);
//							upObj.setNewValue(pValue);
//                            upObj.setButtonClicked(buttonClicked);
//                            retList.add(upObj);
//						}
//						tManager.saveUpdateObjects(fileName, retList,id,reason);
//					}


					if(pageBody != null)
					{
						while(pageBody.hasMoreElements())
						{
                            log.info("selectelement pagebody has more elements..");
                            treeView=new TreeView();
							// Get the current returned object
							EDXBable currObj = (EDXBable) pageBody.nextElement();
							// get the last validation error
							validError = currObj.getValidationError();
                            log.info("selectelement edxId.."+currObj.getEDXID()+"..validError="+validError);
							if(!validError.equals(""))
							{
								validError = tManager.escapeString(validError);

								if(validError.indexOf("enumeration") != -1)
								{
									validError = "[Enumeration Error] - The value you entered is not valid.";
								}
							}

							// do we display the values of this element?
							dispDetails = currObj.DESTINATION.DISPLAYDETAILS.getDisplayInContent();


							if(dispDetails.equalsIgnoreCase("true"))
							{
								// Get all the required values
								fieldName = currObj.DESTINATION.getENGLISHNAME();

								// If the english name is blank use the element name
								if(fieldName.equals(""))
								{
									fieldName = currObj.ORIGINATOR.getNAME();
									int pos = fieldName.lastIndexOf("\\");
									if(pos != 1)
									{
										fieldName = fieldName.substring(pos+1);
										fieldName = fieldName.trim();
									}
								}

								fieldName = tManager.escapeString(fieldName);

								// get the resolved value
								resolvedValue = currObj.DESTINATION.getRESOLVEDVALUE();
								resolvedValue = tManager.escapeString(resolvedValue);

								//log.info("Bgcolor.."+currObj.DESTINATION.DISPLAYDETAILS.getBgColour());
								// get the original value
								origValue = currObj.ORIGINATOR.getVALUE();
								origValue = tManager.escapeString(origValue);

                                 log.info("selectelement resolvedValue.."+resolvedValue+"..origValue="+origValue);

								// get the schemaToUse value
								schemaToUse = currObj.DESTINATION.getSCHEMATOUSE();
								//log.info("SchematoUse.."+schemaToUse);
								if(schemaToUse == "")
								{
									schemaToUse = "false";
								}
								else
								{
									schemaToUse = "true";
								}
                                // figure out the tick type
								confirmType = currObj.DESTINATION.getRESOLVETYPE();
                                bgcolor = currObj.DESTINATION.DISPLAYDETAILS.getBgColour();
								confirmType = confirmType.trim();
									//log.info("confirmType.."+confirmType);
								if(confirmType.equalsIgnoreCase("confirmed"))
								{
									tickType="lock";
								}
								else
								{
                                    //if(!validError.equals("")||bgcolor.equalsIgnoreCase("RED"))
                                    if(bgcolor.equalsIgnoreCase("RED"))
									{
										tickType="cross";
									}
									else
									{
										if(confirmType.equalsIgnoreCase("doConfirm"))
										{
											tickType="orange";										}
										else
										{
											if(confirmType.equalsIgnoreCase("dontConfirm"))
											{
												tickType="green";
											}
											else
											{
												tickType="grey";
											}
										}
									}
								}

								// get the edxID of the current object
								edxID = currObj.getEDXID();

								treeView.setDispDetails(dispDetails);
                                treeView.setEdxId(edxID);
                                treeView.setFieldName(fieldName);
                                treeView.setFileName(fileName);
                                treeView.setOrigValue(origValue);
                                treeView.setResolvedValue(resolvedValue);
                                treeView.setSchemaToUse(schemaToUse);
                                treeView.setTickType(tickType);
                                treeView.setValidError(validError);
                                data.add(treeView);
                            }
                        }
                        setData(data);
                    }
                }
                return INPUT;
      }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return type;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the Type
     */
    public String getXmlString() {
        return xmlString;
    }

    /**
     * @param Type the Type to set
     */
    public void setXmlString(String xmlString) {
        this.xmlString = xmlString;
    }

    /**
     * @return the newFileName
     */
    public String getNewFileName() {
        return newFileName;
    }

    /**
     * @param newFileName the newFileName to set
     */
    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    /**
     * @return the bottomSource
     */
    public String getBottomSource() {
        return bottomSource;
    }

    /**
     * @param bottomSource the bottomSource to set
     */
    public void setBottomSource(String bottomSource) {
        this.bottomSource = bottomSource;
    }

    /**
     * @return the ack_file_name
     */
    public String getAck_file_name() {
        return ack_file_name;
    }

    /**
     * @param ack_file_name the ack_file_name to set
     */
    public void setAck_file_name(String ack_file_name) {
        this.ack_file_name = ack_file_name;
    }

    /**
     * @return the documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType the documentType to set
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * @return the documentCreator
     */
    public String getDocumentCreator() {
        return documentCreator;
    }

    /**
     * @param documentCreator the documentCreator to set
     */
    public void setDocumentCreator(String documentCreator) {
        this.documentCreator = documentCreator;
    }

    /**
     * @return the dataResolutionService
     */
    public DataResolutionService getDataResolutionService() {
        return dataResolutionService;
    }

    /**
     * @param dataResolutionService the dataResolutionService to set
     */
    public void setDataResolutionService(DataResolutionService dataResolutionService) {
        this.dataResolutionService = dataResolutionService;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the data
     */
    public ArrayList getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(ArrayList data) {
        this.data = data;
    }

    /**
     * @return the eid
     */
    public String getEid() {
        return eid;
    }

    /**
     * @param eid the eid to set
     */
    public void setEid(String eid) {
        this.eid = eid;
    }
}
