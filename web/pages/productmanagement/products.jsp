<%-- 
    Document   : products
    Created on : 31/05/2023, 1:02:53 PM
    Author     : user
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    
        <title>View Categories Products</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default1.css" rel="stylesheet" type="text/css" media="screen" />
        
        <script type="text/javascript" SRC="javascript/myscript.js"></script>
		<script type="text/javascript" src="javascript/disableRightClick.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid-4.7.0/css/ui.jqgrid.css" />
        <script src="javascript/jqGrid-4.7.0/jquery.js" type="text/javascript"></script>
       
       
        <link href="theme/jqueryUITheme/ui.all.css" rel="stylesheet" type="text/css" />
		<link href="css/jquery.loadmask.css" rel="stylesheet" type="text/css" media="screen" />
        <script src="javascript/jqGrid-4.7.0/js/i18n/grid.locale-en.js" type="text/javascript"></script>
        <script src="javascript/jqGrid-4.7.0/js/jquery.jqGrid.js" type="text/javascript"></script>
        <script src="javascript/jqGrid-4.7.0/js/jqModal.js" type="text/javascript"></script>
        <script src="javascript/jqGrid-4.7.0/js/jqDnR.js" type="text/javascript"></script>
        
       
        <script type="text/javascript" src="javascript/jquery.timers-1.1.3.js"></script>
        <script type="text/javascript" src="dwr/engine.js"></script>
        <script type="text/javascript" src="javascript/jquery.loadmask.min.js"></script>
        
        <script type="text/javascript" src="javascript/manageproducts.js"></script>
        
    </head>
    <body>
       
        <div id="container">
            <div id="header">
                <jsp:include flush="true" page="..//logoForOrder.jsp"/>
                <div id="header-menu">
                    <jsp:include flush="true" page="Head.jsp"/>
                    <jsp:include flush="true" page="Sub.jsp"/>
                </div><!-- end #header-menu -->
            </div><!-- end #header -->
            <div id="body-wrap">
                <s:form name="form">
                <h3>Manage Products</h3>
                <table width="550" border="0">
                    <tr>
                        <td>Search For:</td>
                        <td><s:textfield id="searchFor" name="searchFor" theme="simple" maxlength="150"/></td>
                        <td>Search In:</td>
                        <td><s:select theme="simple" id="searchIn" name="searchIn" headerKey="1"
                                          headerValue="Location - Machine Type"
                                      list="#{'2':'Asset Name – Category','3':'Product Item No','4':'Product Description'}"/></td>
                        <td><input type="button" id="search" value="Search"/><input type="button" id="clear" value="Clear"/></td>
                </tr>
                <tr>
                    <td colspan="4">
                        <input type="button" id="delete" value="Delete Product Item"/> 
                    </td>
                </tr>
                </table>
                <table id="productlist" class="scroll" cellpadding="0" cellspacing="0" rendered ="pro"></table>
                <div id="pager" class="scroll" style="text-align:center;"></div>
                </s:form>
            </div>
            <div id="footer">
                <div id="footer-content">
                    &nbsp;&copy; 2016 IVBPlus Pty Ltd &middot;
                </div>
            </div><!-- end #footer -->
        </div><!-- end div#container -->
        <div id="footer-shadow">
            <img src="images/bgFooter.gif" width="964px" height="9px" alt=""/>
        </div>
    </body>
</html>