<html>
<head>
<title>Item Help Screen</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<%
	String note = request.getParameter("id");


%>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr> 
    <td colspan="3" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr bgcolor="#3399CC"> 
    <td colspan="3"><div align="center"><font color="#3399CC" face="Courier New, Courier, mono">|</font></div></td>
  </tr>
  <tr> 
    <td width="3%" rowspan="7">&nbsp;</td>
    <td width="94%" bgcolor="#FFFFFF"><div align="center"><font face="Courier New, Courier, mono"></font></div></td>
    <td width="3%" rowspan="7">&nbsp;</td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF"><div align="center"><font face="Courier New, Courier, mono"><strong>Item 
        Help: </strong></font></div></td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF"><%=note%></td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="3"><div align="center"></div></td>
  </tr>
  <tr> 
    <td rowspan="3" bgcolor="#FFFFFF">&nbsp;</td>
    <td bgcolor="#FFFFFF"><div align="center"><font size="-1" face="Courier New, Courier, mono"></font></div>
      <div align="center"> </div></td>
    <td rowspan="3">&nbsp;</td>
  </tr>
  <tr bgcolor="#CCCCCC"> 
    <td bgcolor="#FFFFFF"><div align="center"><img src="../images/Buttons/buttonAccept.jpg" width="130" height="31" onClick="window.close()"></div></td>
  </tr>
  <tr bgcolor="#CCCCCC"> 
    <td bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr bgcolor="#3399CC"> 
    <td colspan="3">&nbsp;</td>
  </tr>
</table>
</body>
</html>
