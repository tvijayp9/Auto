<%@ page import="java.util.*, com.nexus.util.DR.traderoute.Trans_Disp_Bean"%>
<jsp:useBean id="tManager" scope="session" class="com.nexus.util.DR.traderoute.Transaction_Manager"/>
<html>
<head>
<title>Validation Error</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<%
	String edxID = request.getParameter("edxID");
	String fileName = request.getParameter("fileName");
	String typedValue = request.getParameter("typedValue");
	Trans_Disp_Bean tdBean = tManager.getObjectFromList(fileName);
	
	tdBean.saveValueToSchema(edxID, typedValue);
%>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#3399CC"> 
    <td bgcolor="#3399CC"><div align="center"><font color="#3399CC">|</font></div></td>
  </tr>
  <tr> 
    <td><div align="center"></div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td><div align="center"></div></td>
  </tr>
  <tr> 
    <td><div align="center"></div>
      <div align="center"><font size="-1" face="Courier New, Courier, mono"><strong>The 
        value:( <%=typedValue%>) has been saved into the validation list.</strong></font></div></td>
  </tr>
  <tr> 
    <td><div align="center"></div>
      <div align="center"></div></td>
  </tr>
  <tr> 
    <td><div align="center"><img src="../../images/Buttons/buttonClose.jpg" width="129" height="44" onClick="window.close()"></div></td>
  </tr>
  <tr> 
    <td><div align="center"></div></td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
  </tr>
  <tr> 
    <td bgcolor="#3399CC"><div align="center"><font color="#3399CC">|</font></div></td>
  </tr>
</table>
</body>
</html>
