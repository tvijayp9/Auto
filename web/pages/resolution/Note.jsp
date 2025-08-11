<html>
<head>
<title>Item Help Screen</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<%
	String note = request.getParameter("id");
	String fName = request.getParameter("fieldName");
%>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr bgcolor="FFFF33"> 
    <td colspan="3"><div align="center"><font color="FFFF33" face="Courier New, Courier, mono">|</font></div></td>
  </tr>
  <tr> 
    <td width="3%" rowspan="11"><div align="center"></div></td>
    <td width="94%" bgcolor="#FFFFFF"><div align="center"><font face="Courier New, Courier, mono"></font></div></td>
    <td width="3%" rowspan="11">&nbsp;</td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF"><div align="center"><font face="Courier New, Courier, mono"><strong><font color="#CC0000"><%=fName%></font> Help: </strong></font></div></td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF"><div align="center"><%=note%></div></td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF"><div align="center"><font size="-1" face="Courier New, Courier, mono"></font></div>
      <div align="center"> </div></td>
  </tr>
  <tr bgcolor="#CCCCCC"> 
    <td bgcolor="#FFFFFF"><div align="center"><img src="../images/Buttons/buttonAccept.jpg" width="130" height="31" onClick="window.close()"></div></td>
  </tr>
  <tr bgcolor="#CCCCCC"> 
    <td bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr bgcolor="FFFF33"> 
    <td colspan="3"><div align="center"><font color="FFFF33" face="Courier New, Courier, mono">|</font></div></td>
  </tr>
</table>
</body>
</html>
