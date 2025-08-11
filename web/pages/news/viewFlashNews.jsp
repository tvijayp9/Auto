<%-- 
    Document   : viewFlashNews
    Created on : 04/05/2009, 10:25:41 AM
    Author     : Terry
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:property value='title'/></title>
        <link href="css/viewFlashNews.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="container">
<div id="header">
<h2><s:property value='title'/></h2>
<p><s:property value="createDate"/><span class="print"><a href="#" onclick="window.print()">Print</a></span>
</p>
</div>
<p class="content">
<s:property value="description" escape="false"/>
</p>
</div>
    </body>
</html>
