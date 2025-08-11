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
        <link href="css/pricelist.css" rel="stylesheet" type="text/css"/>
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
                    <jsp:include flush="true" page="Head1.jsp"/>
                    <jsp:include flush="true" page="Sub1.jsp"/>
                </div><!-- end #header-menu -->
            </div><!-- end #header -->
            <div id="body-wrap1">
                <s:set name="test" value="%{col.size()}"/>
                <s:if test="%{#test>0}">
                    <h1>Select a Supplier</h1>
                    <br></br>
                    <s:iterator value="col" var="supplier">
                        <s:if test="%{#supplier.type==1}">
                            <s:url id="viewmessage" action="Viewmyitemlist"><s:param name="supplierid" value="%{supplierId}" /></s:url>
                        </s:if>
                        <s:elseif test="%{#supplier.type==2}">
                            <s:url id="viewmessage" action="ViewCategoriesProducts"><s:param name="supplierid" value="%{supplierId}" /></s:url>
                        </s:elseif>
                        <s:a href="#" onclick="window.open('%{viewmessage}','createOrder','width=1000,height=700,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes')"><img src="images/logo/<s:property value="logoName"/>" width="163px" height="93px"/></s:a>
                    </s:iterator>
                </s:if>
                <s:else>
                    <h3>No supplier catalogues available, please contact with your suppliers</h3>
                </s:else>
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
