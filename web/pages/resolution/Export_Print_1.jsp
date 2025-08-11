<%@page import="server.exportprint.*, java.util.Enumeration, java.util.Vector, com.nexus.util.DR.traderoute.Trans_Disp_Bean" %>
<jsp:useBean id="expProg" scope="session" class="server.exportprint.ReadWriteExportPrintData" />
<jsp:useBean id="tManager" scope="session" class="com.nexus.util.DR.traderoute.Transaction_Manager"/>
<jsp:setProperty name="expProg" property="configFileLocation" value="C:\\TradeRoute\\config_files\\edxconfig.xml"/>
<html><!-- InstanceBegin template="/Templates/TRTemplate.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<!-- InstanceBeginEditable name="doctitle" -->
<title>Export Print Forms</title>
<!-- InstanceEndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr background="../images/Title_Bar/Title_Background2.jpg"> 
    <td width="11%" height="74" background="../images/Title_Bar/Title_Background2.jpg"><div align="left"><img src="../images/Title_Bar/Title_Logo2.jpg" width="116" height="74" border="0"></div></td>
    <td width="77%" background="../images/Title_Bar/Title_Background2.jpg"><div align="center"><img src="../images/Title_Bar/Title_Main2.jpg" width="289" height="74"></div></td>
    <td width="12%" background="../images/Title_Bar/Title_Background2.jpg"><div align="right"><img src="../images/Title_Bar/Title_Version2.jpg" width="90" height="74"></div></td>
  </tr>
</table>
<!-- InstanceBeginEditable name="Content" --> 
<script language="JavaScript">

function printForms()
{
	document.form1.printValues.value = "true";
	form1.submit();
}


</script>

<%
	//out.println("qString: "+request.getQueryString());
	expProg.initialise();
	Vector list = expProg.getExportFormList();
	int numOfFormTypes = list.size();
	Enumeration pageBody = list.elements();
	String printValues = request.getParameter("printValues");
	String thisFileName = request.getParameter("name");
	//out.println("thisFileName: "+thisFileName);
	if(thisFileName == null)
	{
		thisFileName = "";
	}
	
	
	if(printValues != null)
	{
		if(printValues.equalsIgnoreCase("true"))
		{
			Vector updateVector = new Vector();
			// save the screen values back into the file.
			Enumeration pageBody2 = list.elements();
			while(pageBody2.hasMoreElements())
			{
				ExportFormPrintObject currObj = (ExportFormPrintObject) pageBody2.nextElement();
				String currID = currObj.getFormID();
								
				String currCopies = request.getParameter("tf"+currID);				
				if(!currCopies.equals("") && currCopies != null)
				{
					currObj.setFormCopies(currCopies);				
				}
				String currCheckBox = request.getParameter("cb"+currID);
				if(currCheckBox != null)
				{
					currObj.setSelectStatus("true");				
				}
				else
				{
					currObj.setSelectStatus("false");
				}				
				updateVector.add(currObj);								
			}			
			expProg.updateExportPrintObject(updateVector);
					
			/** process the forms and send to dynex */
			tManager.processExportForms(thisFileName, updateVector);
			
			
		}
	}
%>


<form name="form1" method="get" action="">
  <input type="hidden" name="printValues" value="">
	<input type="hidden" name="name" value="<%=thisFileName%>">
  <p>&nbsp;</p>
  <table width="90%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr bgcolor="#000000"> 
      <td width="59%"><div align="center"><strong><font color="#FFFFFF" size="-1" face="Courier New, Courier, mono">Form 
          Type</font></strong></div></td>
      <td width="17%"><div align="center"><strong><font color="#FFFFFF" size="-1" face="Courier New, Courier, mono">Print</font></strong></div></td>
      <td width="24%"><div align="center"><strong><font color="#FFFFFF" size="-1" face="Courier New, Courier, mono">Quantity</font></strong></div></td>
    </tr>
	<%
	if(pageBody != null)
	{
		while(pageBody.hasMoreElements())
		{
			ExportFormPrintObject currObj = (ExportFormPrintObject) pageBody.nextElement();
		
	%>
    <tr> 
      <td><div align="left"><font size="-1" face="Courier New, Courier, mono"><%=currObj.getFormDesc()%></font></div></td>
      <td><div align="center"> <font size="-1" face="Courier New, Courier, mono"> 
	  	<%
			if(currObj.getSelectStatus().equalsIgnoreCase("true"))
			{
		%>
          <input type="checkbox" name="cb<%=currObj.getFormID()%>" value="checkbox" checked>
		  <%
		  	}else{
		  %>
		  <input type="checkbox" name="cb<%=currObj.getFormID()%>" value="checkbox">
		  <%
		  }
		  %>
          </font></div></td>
      <td><div align="center"> <font size="-1" face="Courier New, Courier, mono"> 
          <input name="tf<%=currObj.getFormID()%>" type="text" value="<%=currObj.getFormCopies()%>" size="5">
          </font></div></td>
    </tr>
	<%
			currObj = null;
		} //end while
	}
	%>
  </table>
  <table width="90%" height="36" border="0" align="center" cellpadding="1" cellspacing="5">
    <tr>
      <td width="50%"><div align="right">
          <input type="submit" name="Submit2" value="Cancel" onClick="window.close();return false;">
        </div></td>
      <td> <div align="left">
          <input type="submit" name="Submit" value="Print" onClick="printForms();return false;">
        </div>
        <div align="center"></div></td>
    </tr>
  </table>
  </form>
<!-- InstanceEndEditable --> 
</body>
<!-- InstanceEnd --></html>
