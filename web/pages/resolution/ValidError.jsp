<%@ page import="java.util.*, com.nexus.util.DR.traderoute.Trans_Disp_Bean"%>
<jsp:useBean id="tManager" scope="session" class="com.nexus.util.DR.traderoute.Transaction_Manager"/>
<html>
<head>
<title>Validation Error</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<script language="JavaScript">

function submitForm()
{
	var checkBOX = document.form1.checkbox;
	
	if (typeof checkBOX == "undefined")
	{
		window.close();
	}
	else
	{	
		if(document.form1.checkbox.checked)
		{
			window.opener.validateField.value = document.form1.typedValue.value;
			document.form1.submit();
		}
		else
		{		
			window.close();
		}
	}
}


</script>
<%
	String edxID = request.getParameter("id");
	String errorMessage = request.getParameter("error");
	String fileName = request.getParameter("fileName");
	String typedVal = request.getParameter("typedVal");
	String useSchema = request.getParameter("useSchema");
	Vector retList = null;
	//Trans_Disp_Bean tdBean = tManager.getObjectFromList(fileName);
	Trans_Disp_Bean tdBean =(Trans_Disp_Bean)session.getAttribute("tdbean");
	// enumeration error message, get the big list from the schema
	retList = tdBean.getEnumerationList(edxID);
	Enumeration values = retList.elements();
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="form1" method="get" action="ValidSave.jsp" target="_top">
  <input type="hidden" name="edxID" value="<%=edxID%>">
  <input type="hidden" name="fileName" value="<%=fileName%>">
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr> 
      <td colspan="3" bgcolor="#FF0000"><div align="center"><font color="#FF0000" face="Courier New, Courier, mono">|</font></div></td>
    </tr>
    <tr> 
      <td width="3%" rowspan="24">&nbsp;</td>
      <td width="94%" bgcolor="#FFFFFF">&nbsp;</td>
      <td width="3%" rowspan="24">&nbsp;</td>
    </tr>
    <tr> 
      <td bgcolor="#FFFFFF"><div align="center"><font color="#CC0000" face="Courier New, Courier, mono"><strong>Validation 
          Error</strong></font></div></td>
    </tr>
    <tr> 
      <td bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr> 
      <td bgcolor="#FFFFFF"><div align="center"><strong><font size="-1" face="Courier New, Courier, mono">The 
          following error occurred:</font></strong></div></td>
    </tr>
    <tr> 
      <td bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr> 
      <td bgcolor="#FFFFFF"><div align="center"><%=errorMessage%></div></td>
    </tr>
    <tr> 
      <td height="19" bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr> 
      <td height="19" bgcolor="#FFFFFF"><div align="center"><font size="-1" face="Courier New, Courier, mono"><strong>While 
          trying to validate this value:</strong></font></div></td>
    </tr>
    <tr> 
      <td bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr> 
      <td bgcolor="#FFFFFF"><div align="center"> 
          <input type="text" name="typedValue" value="<%=typedVal%>">
        </div></td>
    </tr>
    <tr> 
      <td bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <%
		if(useSchema.equalsIgnoreCase("true"))
		{
	%>
    <tr> 
      <td bgcolor="#FFFFFF"><div align="center"><font color="#CC0000" face="Courier New, Courier, mono"><strong>You 
          can either:</strong></font></div></td>
    </tr>
    <%
		}
	%>
    <tr> 
      <td bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr> 
      <td bgcolor="#FFFFFF"><div align="center"><font size="-1" face="Courier New, Courier, mono"><strong>Select 
          a Valid value from the following list.</strong></font></div></td>
    </tr>
    <%
	if(retList.size() != 0)
	{
	%>
    <tr> 
      <td bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr> 
      <td bgcolor="#FFFFFF"><div align="center"><font size="-1" face="Courier New, Courier, mono"> 
          <select name="select" size="5"  ONCHANGE="window.opener.validateField.value = this.value">
            <%
			  while(values.hasMoreElements())
			  {
				String currVal = (String)values.nextElement();
				%>
            <option value="<%=currVal%>"><%=currVal%></option>
            <%
			  }
			  %>
          </select>
          </font></div></td>
    </tr>
    <%
	}
	%>
    <tr> 
      <td bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <%
		if(useSchema.equalsIgnoreCase("true"))
		{
	%>
    <tr> 
      <td bgcolor="#FFFFFF"><div align="center"><font color="#CC0000" face="Courier New, Courier, mono"><strong>OR</strong></font></div></td>
    </tr>
    <tr> 
      <td bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr> 
      <td><div align="center"><font size="-1" face="Courier New, Courier, mono"><strong>Save 
          this value into the validation list : 
          <input type="checkbox" name="checkbox" value="checkbox">
          </strong></font></div></td>
    </tr>
    <%
		}
	%>
    <tr> 
      <td bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr bgcolor="#CCCCCC"> 
      <td bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr bgcolor="#CCCCCC"> 
      <td bgcolor="#FFFFFF"><div align="center"><img src="../../images/Buttons/buttonAccept.jpg" width="130" height="31" onClick="submitForm()"></div></td>
    </tr>
    <tr bgcolor="#CCCCCC"> 
      <td bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr> 
      <td colspan="3" bgcolor="#FF0000">&nbsp;</td>
    </tr>
  </table>
</form>
</body>
</html>
