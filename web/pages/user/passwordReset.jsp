<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title><s:text name="Nexus Online B2B"/></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    </head>
    <body>
        <div id="container">
             <jsp:include flush="true" page="/pages/home_logo.jsp"/>
            <div id="body-wrap2"> 
                <p align="center"><b><h4>Your password has been reset.Please login again to use the system.</h4></b></p>
            </div>
            <% 
            session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }%>
            <div id="footer">
                <div id="footer-content">
                    &nbsp;&copy; 2008 XML Yes &middot; 
                </div>
            </div>
        </div>
        <div id="footer-shadow">
            <img src="images/bgFooter.gif" width="964px" height="12px" alt=""/>
        </div>
        
        
    </body>
</html>