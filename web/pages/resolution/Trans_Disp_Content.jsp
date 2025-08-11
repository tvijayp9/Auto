<%@page import="com.nexus.util.DR.edxbable.EDXBable, com.nexus.util.DR.misc.*, java.util.*, com.nexus.util.DR.traderoute.Trans_Disp_Bean,org.w3c.dom.Document;" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="tManager" scope="session" class="com.nexus.util.DR.traderoute.Transaction_Manager"/>
<%
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

%>
<html>
<head>
<meta http-equiv="expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<title>Content</title>
<!--<SCRIPT  language="JavaScript1.2" src="../Java_Script/JSFunctions.js">-->
<SCRIPT  language="JavaScript1.2">
document.onkeydown = function ()
{
	var kcode = event.keyCode;

	if(kcode > 111 && kcode < 124)
	{
		 alert("Function Keys Cannot be used in TradeRoute.");
		 event.keyCode = 0;
		event.returnValue = false;
		event.cancelBubble = true;
		return false;
	}
}


document.onhelp =  function ()
{
    //alert('Visit http://javascript.faqts.com/ for help');
    return false;
}


var validateField;
var currViewField;
var prevField;
function copyAllValues()
{
	var fields = document.forms[0];

	for (i = 0; i < fields.length; i++)
	{
		// if the current element is an input text field...
		if (fields.elements[i].type == "text")
		{
			curr = fields.elements[i];
			var name = curr.name;
			var pos = name.indexOf("origText");

			if(pos != -1)
			{
				/** this is the text field on the right */
				prevField.value = curr.value;
			}
			else
			{
				/** this is the text field on the left */
				prevField = curr;
			}
		}
	}
}

function acceptAllFields()
{
	for (var i=0; i<document.images.length; i++)
	{
		var imageSRC = document.images[i].src;

		var pos = imageSRC.indexOf("orange");

		/** if there is an orange tick */
		if(pos != -1)
		{
			document.images[i].src = "../../images/Trans_Disp/greenTick.jpg";
			document.images[i].focus();
		}
	}
}


function openViewField(boxId, value, fieldName, tType)
{

	var element;

	for (var j = 0; j < document.form1.elements.length; j++)
	{
		var elementName = document.form1.elements[j].name;
		if(elementName == boxId)
		{
			currViewField = document.form1.elements[j];
			break;
		}
	}


	var url = "ViewField.jsp?val="+value+"&fieldName="+fieldName+"&tickType="+tType;

	var r = window.open(url,'ViewField','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no,width=600,height=255');

}

function openHelpWin(fieldName, helpNote)
{
	var url = "Note.jsp?id="+helpNote+"&fieldName="+fieldName;
	var r = window.open(url,'HelpWindow','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no,width=400,height=245');
	//return false;
}


function cancelTransaction()
{
	window.close();
}

function saveTransaction()
{
	form1.submitType.value = "save";
	form1.submit();
	//submitTransaction("save");
}


function submitTransaction()
{
	var formOK = true;

	var counter = 0;
	//check that all the ticks are green or locks or greys..
	for (var i=0; i<document.images.length; i++)
	{
		var imageSRC = document.images[i].src;

		var pos = imageSRC.indexOf("orange");

		if(pos != -1)
		{
			counter++;
			if(counter > 1)
			{
				formOK = false;
			}
		}
	}

	if(formOK == true)
	{
		form1.submitType.value = "submit";
		form1.submit();
	}
	else
	{
		//There are still unresolved ticks...
		alert("Please Approve all elements (with a Orange tick) before submitting.");
	}
}

function textSelected(tickID)
{
	if(tickID == 0)
	{
	 	tickID = "0";
	}
	//change the arrow to grey...
	for (var i=0; i<document.images.length; i++)
    {
		if (document.images[i].name == tickID)
		{
			var origImage = document.images[i].src;
			var pos = origImage.indexOf("grey");

			if(pos == -1)
			{
				document.images[i].src = "../../images/Trans_Disp/orangeTick.jpg";
				document.images[i].focus();
			}
		}
	}
}
function submitValues(e,tickID,id,filename,transtype)
{
	var unicode=e.keyCode? e.keyCode : e.charCode;
	if(unicode==13) {
			//alert('tickID='+tickID+'..id...'+id+'...filename..'+filename+'...transtype..'+transtype);
			top.buttonCheckValues(id, filename,transtype);
	return false;
     } else {
    return true;
    }

}

function tickClick(tickID, errorMessage, fileName, useSchema)
{

	if(tickID == 0)
	{
	 	tickID = "0";
	}

	for (var i=0; i<document.images.length; i++)
    {
        if (document.images[i].name == tickID)
		{
			//which is the current image???
			var origImage = document.images[i].src;
			var pos = origImage.indexOf("orange");
			var pos2 = origImage.indexOf("cross");

			if(pos != -1)
			{
				//got an ornage tick...
				document.images[i].src = "../../images/Trans_Disp/greenTick.jpg";
			}

			if(pos2 != -1)
			{
				//got an cross tick...
				var element;

				for (var j = 0; j < document.form1.elements.length; j++)
				{
					var elementName = document.form1.elements[j].name;
                   // alert("tickID.."+tickID+"..elementName.."+elementName);
					//if(elementName == xpath)
                    if(elementName == tickID)
					{
						validateField = document.form1.elements[j];
						break;
					}
				}
				// alert("tickID.."+tickID+"..xpath.."+xpath);
                var typedVal = "";

				// go through all the form elements
				for(var j = 0; j < form1.elements.length; j++)
				{
					var currEl = form1.elements[j];
					//if(currEl.name == xpath)
                    if(currEl.name == tickID)
					{
                    //      alert("true..xpath.."+xpath);
						typedVal = currEl.value;
						break;
					}
				}
                // alert("typedVal.."+typedVal);
				var url = "ValidError.jsp?id="+tickID+"&typedVal="+typedVal+"&error="+errorMessage+"&fileName="+fileName+"&useSchema="+useSchema;

				var r = window.open(url,'SaveWindow','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no,width=600,height=570');
				//var r = window.open("ValidError.jsp?id="+tickID+"&typedVal="+typedVal+"&error="+errorMessage+"&fileName="+fileName, element, 'Width:500px;Height:500px;center:1;');

				/*if (typeof r != "undefined")
				{
					element.value = r;
				}*/
				document.images[i].src = "../../images/Trans_Disp/greenTick.jpg";
			}
		}
	}
	return false;
}

function copyValue(textName, value)
{
	//change the tick to grey...
	textSelected(textName);

	var unName = unescape(textName);
	var unValue = unescape(value);
	//alert("textName: "+unName+", value: "+unValue);

	var fields = document.forms[0];

	for (i = 0; i < fields.length; i++)
	{
		// if the current element is an input text field...
		if (fields.elements[i].type == "text")
		{
			curr = fields.elements[i];
			var id = curr.name;
			if(id == unName)
			{
				//alert("found the right one");
				curr.value = unValue;
			}
		}
	}
}

</SCRIPT>
</head>
<body>
<p> </p>
<%
		/** get the query string */
		//String qString = request.getQueryString();
		//out.println("Q String: "+qString);
		//if(qString != null)
		//{
			//if(!qString.equals(""))
		//	{
				// get the id and fileName
				String id = request.getParameter("id");
				String fileName =  request.getParameter("fileName");
				String buttonClicked = request.getParameter("buttonClicked");
				String transType = request.getParameter("transType");
				System.out.println("id.."+id+"...buttonClicked.."+buttonClicked);
                String reason=request.getParameter("rejectreason");
				String acceptInvNo=request.getParameter("acceptInvoiceNo");
				String supnexusid= session.getAttribute("supplierid").toString();
				System.out.println("tManager.fileName supnexusid."+supnexusid);
				String comment1=request.getParameter("doccomment");
                // get the right display object
				fileName =  (String)session.getAttribute("newFileName");
				System.out.println("tManager.fileName."+fileName);
				//Trans_Disp_Bean tdBean = tManager.getObjectFromList(fileName);
				Trans_Disp_Bean tdBean = (Trans_Disp_Bean)session.getAttribute("tdbean");
				System.out.println("tdBean.."+tdBean.getDocumentType());
				if(buttonClicked != null && buttonClicked.equals("print")){
					System.out.println("print clicked..");
					session.setAttribute("button","print");
					session.setAttribute("pid",id);
					System.out.println(".vvv..getparameter.."+session.getAttribute("button")+"..id...."+session.getAttribute("pid"));
				}
				if(id == null)
				{
					id = "";
				}
				if(fileName == null)
				{
					fileName = "";
				}
				if(buttonClicked == null)
				{
					buttonClicked = "";
				}
				if(transType == null)
				{
					transType = "";
				}
				

				// check for errors
				//String errorText = tdBean.getErrorText();
				String errorText =session.getAttribute("errorText").toString();

				if(errorText.equals(""))
				{
					// get the enumeration of parameters passed in
					int counter = 0;
					Enumeration requestParamNames = request.getParameterNames();
					while(requestParamNames.hasMoreElements())
					{
						counter++;
						requestParamNames.nextElement();
					}
					System.out.println("counter.."+counter);
					Enumeration pageBody = null;
					//tdBean.setCounter(counter);
					if(buttonClicked.equals("") || buttonClicked.equals(null)){
						System.out.println("no button clicked.."+id);
						if(session.getAttribute("button").equals("print")){
							id=(String)session.getAttribute("pid");
							//System.out.println("PRINT BUTTON CLICKED="+id);
							session.setAttribute("button","");
							session.setAttribute("pid","");
						}
					
						pageBody = tdBean.getTransactionObjects(id);
					}
					else{
					if(counter == 3)
					{
                         System.out.println("inside counter==3.."+id);
						pageBody = tdBean.getTransactionObjects(id);
					}
					 else
					{
                        
						// save the current objects
						Vector retList = new Vector();
						Enumeration pNames = request.getParameterNames();
						 System.out.println("not inside counter==3.."+id);
						while(pNames.hasMoreElements())
						{
							UpdateObject upObj = new UpdateObject();
							String pName = (String)pNames.nextElement();
							String pValue = request.getParameter(pName);
							System.out.println("one..not inside counter==3.pName=."+pName+"..pValue=."+pValue);
							if(pName.equals("reasonType"))
								session.setAttribute("reasonType",pValue);
							upObj.setParentName(pName);
							upObj.setNewValue(pValue);
                            upObj.setButtonClicked(buttonClicked);
                            retList.add(upObj);
						}
                        if(buttonClicked.equals("accept")){
                            tManager.saveUpdateObjects(tdBean, (Document)session.getAttribute("currentTransactionDOM"),pageBody,fileName, retList,id,acceptInvNo,supnexusid);
                        }else if(buttonClicked.equals("comment")){
						tManager.saveUpdateObjects(tdBean, (Document)session.getAttribute("currentTransactionDOM"),pageBody,fileName, retList,id,comment1,supnexusid);
						}
                         else{
						tManager.saveUpdateObjects(tdBean, (Document)session.getAttribute("currentTransactionDOM"),pageBody,fileName, retList,id,reason,supnexusid);
                        }
					}
				}

					if(pageBody != null)
					{
					int count=0;
					%>
<p align="center"><strong><font size="-1" face="Courier New, Courier, mono">Please
  resolve the following elements.</font></strong></p>
<form action="Trans_Disp_Content.jsp" method="post" name="form1" target="_self">
  <input type="hidden" name="id" value="<%=id%>">
  <input type="hidden" name="fileName" value="<%=fileName%>">
  <input type="hidden" name="buttonClicked" value="">
  <input type="hidden" name="rejectreason" value="">
  <input type="hidden" name="acceptInvoiceNo" value="">
  <input type="hidden" name="doccomment" value="">
  <input type="hidden" name="transType" value="<%=transType%>">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="29%" bgcolor="#000000"><div align="center"><font color="#FFFFFF" size="-1" face="Arial, Helvetica, sans-serif;"><strong>Element
          </strong></font></div></td>
	   <td width="30%" bgcolor="#000000"><div align="center"><font color="#FFFFFF" size="-1" face="Arial, Helvetica, sans-serif;"><strong>Original
          Value</strong></font></div></td>
	  <td width="30%" bgcolor="#000000"><div align="center"><font color="#FFFFFF" size="-1" face="Arial, Helvetica, sans-serif;"><strong>Resolved
          Value</strong></font></div></td>
      <td width="6%" bgcolor="#000000"><div align="center"><font color="#FFFFFF" size="-1" face="Arial, Helvetica, sans-serif;"><strong>Status</strong></font></div></td>	
    </tr>
    <%
						while(pageBody.hasMoreElements())
						{
							
							// Get the current returned object
							EDXBable currObj = (EDXBable) pageBody.nextElement();
							String xpath=currObj.ORIGINATOR.getNAME();
							// get the last validation error
							String validError = currObj.getValidationError();

							if(!validError.equals(""))
							{
								validError = tManager.escapeString(validError);

								if(validError.indexOf("enumeration") != -1)
								{
									validError = "[Enumeration Error] - The value you entered is not valid.";
								}
							}

							// do we display the values of this element?
							String dispDetails = currObj.DESTINATION.DISPLAYDETAILS.getDisplayInContent();


							if(dispDetails.equalsIgnoreCase("true"))
							{
								// Get all the required values
								String fieldName = currObj.DESTINATION.getENGLISHNAME();

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
								String resolvedValue = currObj.DESTINATION.getRESOLVEDVALUE();
								resolvedValue = tManager.escapeString(resolvedValue);

								//System.out.println("Bgcolor.."+currObj.DESTINATION.DISPLAYDETAILS.getBgColour());
								// get the original value
								String origValue = currObj.ORIGINATOR.getVALUE();
								origValue = tManager.escapeString(origValue);

								// get the schemaToUse value
								String schemaToUse = currObj.DESTINATION.getSCHEMATOUSE();
								//System.out.println("SchematoUse.."+schemaToUse);
								if(schemaToUse == "")
								{
									schemaToUse = "false";
								}
								else
								{
									schemaToUse = "true";
								}
                                // figure out the tick type
								String confirmType = currObj.DESTINATION.getRESOLVETYPE();
                                String bgcolor = currObj.DESTINATION.DISPLAYDETAILS.getBgColour();
								String tickType = "";
								confirmType = confirmType.trim();
									//System.out.println("confirmType.."+confirmType);
								if(confirmType.equalsIgnoreCase("confirmed"))
								{
									tickType = "lock";
									
								}
								else
								{
                                    //if(!validError.equals("")||bgcolor.equalsIgnoreCase("RED"))
                                    if(bgcolor.equalsIgnoreCase("RED"))
									{
										tickType = "cross";
										if(origValue==resolvedValue)
											tickType = "green";
									}
									else
									{
										if(confirmType.equalsIgnoreCase("doConfirm"))
										{
											tickType = "orange";
										}
										else
										{
											if(confirmType.equalsIgnoreCase("dontConfirm"))
											{
												tickType = "green";
											}
											else
											{
												tickType = "grey";
											}
										}
									}
								}

								// get the edxID of the current object
								String edxID = currObj.getEDXID();
								String question = currObj.DESTINATION.getHELPNOTE();
								question = tManager.escapeString(question);
								if(question.equals(""))
								{
									question = "There is no help available for this element";
								}
								count++;
							%>
    <tr>
      <td> <div align="left"><font size="-1" face="Arial, Helvetica, sans-serif;"><%=fieldName%></font></div></td>
      
      
      <%
										if(tickType.equals("lock"))
										{
									%>
      <td><div align="center"><font size="-1" face="Arial, Helvetica, sans-serif;">
          <input name="origText<%=edxID%>" type="text" value="<%=origValue%>" size="30" disabled>

          </font></div></td>
	  <td><div align="center"><font face="Arial, Helvetica, sans-serif;">
          <input name="<%=edxID%>" type="text" value="<%=resolvedValue%>" size="30" disabled>
          </font></div></td>
      
      
      <%
										}
										else 
										{
									%>
      <td><div align="center"><font size="-1" face="Arial, Helvetica, sans-serif;">
          <input name="origText<%=edxID%>" type="text" value="<%=origValue%>" size="30" disabled>
          </font></div></td>
	  <td><div align="center"><font face="Arial, Helvetica, sans-serif;">
		  <% if(count==1){ %>		
          <input name="<%=edxID%>" type="text" value="<%=resolvedValue%>" size="30" disabled >
		  <% } else { %>
		  <input name="<%=edxID%>" type="text" value="<%=resolvedValue%>" size="30" onkeyup="return submitValues(event,<%=edxID%>,'<%=id%>', '<%=fileName%>', '<%=transType%>'); this.select()" >
		  <% } %>
          </font></div></td>
      
      
      <%
										}
									%>
	<td><div align="center" style="height:22.5px;border:0.05px ;"><font size="-1" face="Arial, Helvetica, sans-serif;"><img src="../../images/Trans_Disp/<%=tickType%>Tick.jpg" name="<%=edxID%>" ></font></div></td>
    </tr>
    <%
			}
		}//end while   
		
		
    String title=request.getParameter("title");
	System.out.println("title=="+title);

	%>
	
  </table>
  <% if("Item Line".equals(title)&& supnexusid.equals("500005"))
		{
  %>
  <table>
  <tr>
  <s:select id="reasonType" label="Select reason type" name="reasonType" headerKey="0"
                              headerValue="-- Please Select --"
                              list="#{'1':'PRODUCT_OUT_OF_STOCK(BACK ORDER)', '2':'INVALID_PRODUCT_OR_ITEM_IDENTIFICATION'}"/>
							  </tr>
							 
								
							 <!-- <tr>
							  <td>Comment:</td>
	 <td><input name="comment" type="text" size="70" ></td>
	</tr> -->
	</table>
	<% } %>
	
	<table>
	<tr>
	<td width="200px"></td>
	<% if(supnexusid.equals("500005"))
		{
  %>
	<td width="50px">
	<input type="button" id="save" value="Save" onClick="javascript:top.button_save_kala('<%=id%>', '<%=fileName%>', '<%=transType%>','<%=title%>')"></td>
		<% } %>
	<td align="right"><input type="button" value="Check Values" onClick="javascript:top.buttonCheckValues('<%=id%>', '<%=fileName%>', '<%=transType%>','<%=title%>');"></td></tr>
	</table>
  </form>
<%
					}
				} // end errorText.equals("")
				else
				{
					// error happened
					out.println("ERROR: "+errorText);
				}
			//} // END if(!qString.equals(""))
		//} //END if(qString != null)

%>
</body>
</html>
