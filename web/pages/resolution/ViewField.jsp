<html>
<head>
<title>Field View</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<script language="JavaScript">
function saveValue()
{
	window.opener.currViewField.value = document.form1.textarea.value;
	window.close();
}


</script>

<%
	String note = request.getParameter("val");
	String fName = request.getParameter("fieldName");
	String tType = request.getParameter("tickType");
%>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr> 
    <td bgcolor="#3399CC"><div align="center"><font color="#3399CC" face="Courier New, Courier, mono">|</font></div></td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td><div align="center"></div></td>
  </tr>
  <tr> 
    <td><div align="center"><font face="Courier New, Courier, mono"><strong><font color="#CC0000"><%=fName%></font> Value:</strong></font></div></td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td><div align="center"></div></td>
  </tr>
  <tr> 
    <td><div align="center"></div>
      <form name="form1" method="post" action="">
        <div align="center"> 
          <textarea name="textarea" cols="60" rows="6"><%=note%></textarea>
        </div>
      </form></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr> 
  <%
  	if(tType.indexOf("lock") != -1)
	{	
	%>
    <td><div align="center"><img src="../images/Buttons/buttonClose.jpg" onClick="window.close();"></div></td>
	<%
	}
	else
	{
	%>
	<td><div align="center"><img src="../images/Buttons/buttonAccept.jpg" onClick="saveValue();"></div></td>
	<%
	}
	%>
  </tr>
  <tr> 
    <td><div align="center"></div></td>
  </tr>
  <tr> 
    <td><div align="center"></div></td>
  </tr>
</table>
</body>
</html>
