<%-- 
    Document   : main
    Created on : 29/07/2008, 14:15:00
    Author     : User
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.nexus.util.DR.traderoute.TransactionCentre, com.nexus.util.DR.traderoute.TransactionObject, java.util.Enumeration" %>
<jsp:useBean id="transCentre" scope="session" class="com.nexus.util.DR.traderoute.TransactionCentre" />
<jsp:setProperty name="transCentre" property="configFileLocation" value="C:\\TradeRoute\\config_files\\edxconfig.xml"/>
<jsp:setProperty name="transCentre" property="logPropFileLocation" value="C:\\TradeRoute\\config_files\\logging.properties"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Data Resolution List</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <!--[if !IE 6 ]>
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <![endif]-->
        <!--[if IE 6 ]>
        <link href="css/ie6.css" rel="stylesheet" type="text/css" media="screen" />
        <style type="text/css">img {behavior: url('css/iepngfix.htc');} </style>
        <![endif]-->
        <SCRIPT type="text/javascript" SRC="javascript/myscript.js">
        </SCRIPT>
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/basic/grid.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/jqModal.css" />
        <script src="javascript/jquery-1.3.min.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/jquery.jqGrid.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqModal.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqDnR.js" type="text/javascript"></script>
        <script src="javascript/memberlist.js" type="text/javascript"></script>
        <script language="JavaScript1.2">
            function openWin(app, fileName, transType, uName, uType)
            {
                var url = "pages/resolution/"+app+".jsp?name="+fileName+"&transType="+transType+"&userName="+uName+"&userType="+uType;
                //alert("Opening url: "+url);
                var r = window.open( url,'MyWindow','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,width=800,height=600');
                //var r = window.open( url,'MyWindow','fullscreen');
                //return false;
                window.location.reload();
            }

            function openHeldWindow(userName, note)
            {
                // construct the message string...

                alert("User's Name: "+userName+"\n\nNote: \n\n"+note);
            }
        </script>

    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include flush="true" page="..//logo.jsp"/>
                <div id="header-menu">
                    <jsp:include flush="true" page="Head.jsp"/>
                    <jsp:include flush="true" page="Sub.jsp"/>
                </div><!-- end #header-menu -->
            </div><!-- end #header -->
            <div id="body-wrap1">
             <!--   <table  border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr background="images/Title_Bar/Title_Background2.jpg">
                        <td  height="74" background="images/Title_Bar/Title_Background2.jpg"><div align="left"><img src="images/Title_Bar/Title_Logo2.jpg"  height="74" border="0"></div></td>
                        <td  background="images/Title_Bar/Title_Background2.jpg"><div align="center"><img src="images/Title_Bar/Title_Main2.jpg"  height="74"></div></td>
                        <td  background="images/Title_Bar/Title_Background2.jpg"><div align="right"><img src="images/Title_Bar/Title_Version2.jpg"  height="74"></div></td>
                    </tr>
                </table> -->
                
                            <%

        // initialise
        transCentre.initialise();
        String transType = request.getParameter("type");
        String uName = request.getParameter("userName");
        String uType = request.getParameter("userType");
        String UID = request.getSession().getAttribute("ID").toString();
        transCentre.setNexusID(UID);
       // System.out.println("UID = "+UID);
        String createNewDocVal = request.getParameter("createNewDocVal");
        transCentre.setErrorText("");
        transCentre.setTransType(transType);
        if (createNewDocVal != null) {
            if (createNewDocVal.equalsIgnoreCase("true")) {
            }
        }
        Enumeration pageBody = transCentre.getBody();
        String errorText = "";
                                    %>
                

                <table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#000000" >
                    <tr>
                        <td width="28%" bgcolor="#000000"> <b>Doc No</b></td>
                        <td width="22%" bgcolor="#000000"> <b>Partner</b></td>
                        <td width="22%" bgcolor="#000000"> <b>Doc Type</b></td>
                        <td width="12%" bgcolor="#000000"> <b>Date</b></td>
                        <td width="9%" bgcolor="#000000"> <b>Last User</b></td>
                        <td width="9%" bgcolor="#000000"> <b>State</b></td>
                    </tr>
                    <%
                         while (pageBody.hasMoreElements()) {
                         TransactionObject currObj = (TransactionObject) pageBody.nextElement();
                    %>
                    <tr>
                        <td width="28%" bgcolor="#FFFFFF">
                            <%
                        if (currObj.getLockedStatus().equalsIgnoreCase("locked")) {
                            %>
                            <%=currObj.getDocID()%>
                            <%
                            } else {
                            %>
                            <a href="#" onClick="openWin('<%=currObj.getTradeFormApp()%>', '<%=currObj.getFileID()%>', '<%=currObj.getTransType()%>', '<%=uName%>', '<%=uType%>')"><%=currObj.getDocID()%></a>
                            <%
                        }
                            %>
                        </td>
                        <td width="22%" bgcolor="#FFFFFF"><%=currObj.getPartnerName()%></td>
                        <td width="22%" bgcolor="#FFFFFF"><%=currObj.getTransType()%></td>
                        <td width="12%" bgcolor="#FFFFFF"><%=currObj.getCreationDate()%></td>
                        <td width="12%" bgcolor="#FFFFFF"><%=currObj.getUserName()%></td>
                        <td width="9%" bgcolor="#FFFFFF">
                            <%
                            if (currObj.getLockedStatus().equalsIgnoreCase("Open")) {
                            %>
                            <img src="images/openFolder.jpg" >
                            <%  } else {  %>
                            <img src="images/lockTick.jpg" >
                            <% } %>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
            <div id="footer">
                <div id="footer-content">
                    &nbsp;&copy; 2008 XML Yes &middot;
                </div>
            </div><!-- end #footer -->
        </div><!-- end div#container -->
        <div id="footer-shadow">
            <img src="images/bgFooter.gif" width="964px" height="9px" alt=""/>
        </div>
    </body>
</html>
