<%@ page language="java" import="com.nexus.util.DR.traderoute.Content_Bean,java.util.*, com.nexus.util.DR.misc.*" %>
<jsp:useBean id="content_bean" scope="session" class="com.nexus.util.DR.traderoute.Content_Bean"/>
<html>
<head>
<title>Save Form</title>
</head>
<body onLoad="forwardForm()">
<SCRIPT language="JavaScript" >
function forwardForm()
{
	document.form1.submit();
}	
</SCRIPT>
<%
	Vector returnList = new Vector();
	Enumeration paramNames = request.getParameterNames();

	while(paramNames.hasMoreElements())
	{
		String currName = (String)paramNames.nextElement();
		String currValue = request.getParameter(currName);		
		UpdateObject currUO = new UpdateObject();
		currUO.setParentName(currName);
		currUO.setNewValue(currValue);
		returnList.add(currUO);	
	}	
	content_bean.saveUpdateObjects(returnList);
%>
<form name="form1" method="post" action="">
<input type="hidden" name="fileId" value="<%=request.getParameter("fileId")%>">
<input type="hidden" name="fileName" value="<%=request.getParameter("fileName")%>">
</form>


</body>
</html>
