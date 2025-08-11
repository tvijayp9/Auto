<%-- 
    Document   : main
    Created on : 01/09/2023, 14:15:00
    Author     : Vijay Thumma
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Favourite Orders</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default1.css" rel="stylesheet" type="text/css" media="screen" />
       <script type="text/javascript" SRC="javascript/myscript.js"></script>
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <script type="text/javascript" src="javascript/amcapquoteslist.js?160920233"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/basic/grid.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/jqModal.css" />
        <link href="theme/jqueryUITheme/ui.all.css" rel="stylesheet" type="text/css" />
        <script src="javascript/jqGrid-4.7.0/js/i18n/grid.locale-en.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/jquery.jqGrid.js" type="text/javascript"></script>
        <script src="javascript/jqGrid-4.7.0/js/jqModal.js" type="text/javascript"></script>
        <script src="javascript/jqGrid-4.7.0/js/jqDnR.js" type="text/javascript"></script>
        <script type="text/javascript" src="javascript/jquery-ui-personalized-1.6rc6.min.js"></script>
    </head>
    <body>
        <%
            session.removeAttribute("quoteName");
            String isAdminUser=session.getAttribute("isSupplier").toString();
        %>
        <div id="container">
            <div id="header">
                <jsp:include flush="true" page="..//logoForOrder.jsp"/>
                <div id="header-menu">
                    <jsp:include flush="true" page="Head.jsp"/>
                    <jsp:include flush="true" page="Sub.jsp"/>
                </div><!-- end #header-menu -->
            </div><!-- end #header -->
            <div id="body-wrap">
                <h1>Quotes</h1>
                <% if(isAdminUser.equalsIgnoreCase("true")){ %>
                <input type="button" id="delete" value="Delete Quotes"/>
                <input type="button" id="create" value="Create New Quote"/>
                <table id="list" class="scroll" cellpadding="0" cellspacing="0"></table>
                <div id="pager" class="scroll" style="text-align:center;"></div>
                <% } else {%>
                <table id="list1" class="scroll" cellpadding="0" cellspacing="0"></table>
                <div id="pager1" class="scroll" style="text-align:center;"></div>
                <% } %>
            </div>
            <div id="footer">
                <div id="footer-content">
                    &nbsp;&copy; 2023 IVBPlus Pty Ltd &middot;
                </div>
            </div><!-- end #footer -->
        </div><!-- end div#container -->
        <div id="footer-shadow">
            <img src="images/bgFooter.gif" width="964px" height="9px" alt=""/>
        </div>
    </body>
</html>
