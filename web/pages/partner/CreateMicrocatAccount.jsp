<%-- 
    Document   : main
    Created on : 29/07/2008, 14:15:00
    Author     : User
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Partner List</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
        <!--[if !IE 6 ]>
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <![endif]-->
        <!--[if IE 6 ]>
        <link href="css/ie6.css" rel="stylesheet" type="text/css" media="screen" />
        <style type="text/css">img {behavior: url('css/iepngfix.htc');} </style>
        <![endif]-->
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <script type="text/javascript" src="javascript/createMicrocatAccount.js"></script>
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
                <s:form>
                    <s:hidden name="newMemberId" />
                    <s:hidden name="microcatAccountAmount"/>
                    <h3>Microcat Details for this member:</h3>
                    <table width="400" border="0">
                    <s:iterator value="amountList" var="number">
                        <tr><td><h4>Microcat Account <s:property/>:</h4></td><td></td></tr>
                        <tr><td>Account Number:</td><td><input class="textValue" type="text" size="35" maxlength="30" name="microcatAccountNumber<s:property/>"></input></td></tr>
                        <tr><td>Username:</td><td><input class="textValue" type="text" size="35"  maxlength="30" name="microcatUsername<s:property/>"></input></td></tr>
                        <tr><td>Password:</td><td><input class="textValue" type="text" size="35"  maxlength="30" name="microcatPassword<s:property/>"></input></td></tr>
                        <tr><td></td><td></td></tr>
                    </s:iterator>
                    </table>
                </s:form>
                <input type="button" value="Create"/>
            </div>
            <div id="footer">
                <div id="footer-content">
                    &nbsp;&copy; 2009 XML Yes &middot;
                </div>
            </div><!-- end #footer -->
        </div><!-- end div#container -->
        <div id="footer-shadow">
            <img src="images/bgFooter.gif" width="964px" height="9px" alt=""/>
        </div>
    </body>
</html>