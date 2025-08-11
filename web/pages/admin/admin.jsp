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
        <title>Memeber List</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <!--[if !IE 6 ]>
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <![endif]-->
        <!--[if IE 6 ]>
        <link href="css/ie6.css" rel="stylesheet" type="text/css" media="screen" /> 
        <style type="text/css">img {behavior: url('css/iepngfix.htc');} </style>
        <![endif]-->
        <SCRIPT type="text/javascript" SRC="javascript/myscript.js"></SCRIPT>
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        
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
