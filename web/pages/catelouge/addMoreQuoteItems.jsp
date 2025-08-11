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
        <title>Add New Favourite Order Items</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default1.css" rel="stylesheet" type="text/css" media="screen" />
        <!--[if !IE 6 ]>
        <link href="css/default1.css" rel="stylesheet" type="text/css" media="screen" />
        <![endif]-->
        <!--[if IE 6 ]>
        <link href="css/ie6.css" rel="stylesheet" type="text/css" media="screen" />
        <style type="text/css">img {behavior: url('css/iepngfix.htc');} </style>
        <![endif]-->
        <SCRIPT type="text/javascript" SRC="javascript/myscript.js"></SCRIPT>
        <script type="text/javascript" src="javascript/disableRightClick.js"></script>
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="jsTree/tree_component.css" />
        <script type="text/javascript" src="jsTree/css.js"></script>
        <script type="text/javascript" src="jsTree/tree_component.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/basic/grid.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/jqModal.css" />
        <link href="theme/jqueryUITheme/ui.all.css" rel="stylesheet" type="text/css" />
        <link href="css/jquery.loadmask.css" rel="stylesheet" type="text/css" media="screen" />
        <script src="javascript/jqGrid/jquery.jqGrid.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqModal.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqDnR.js" type="text/javascript"></script>
        <script type="text/javascript" src="javascript/jquery-ui-personalized-1.6rc6.min.js"></script>
                        <script type="text/javascript" src="javascript/jquery.timers-1.1.3.js"></script>
        <script type="text/javascript" src="dwr/engine.js"></script>
        <script type="text/javascript" src="dwr/interface/catalogueService.js"></script>
        <script type="text/javascript" src="javascript/jquery.loadmask.min.js"></script>
        <script type="text/javascript" src="javascript/createNewQuote.js"></script>
    </head>
    <body>
         <%
            String supNexusId=session.getAttribute("supplierid").toString();
			String isMicrocat=session.getAttribute("isMicrocat").toString();
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
                <h1>Add New Quote Items</h1>
                <p>1. Please search products 
				<% if(!isMicrocat.equalsIgnoreCase("nomicrocat")){ %>
				, or select products by using <input type="button" id="partsCatalogue" value="Microcat Catalogue"/>
				<% } %>
				</p>
                <table width="550" border="0">
                    <tr>
                        <td>Search For:</td>
                        <td><s:textfield id="searchFor" name="searchFor" theme="simple" maxlength="20"/></td>
                        <td>Search In:</td>
                        <td><s:select theme="simple" id="searchIn" name="searchIn" headerKey="1"
                                          headerValue="Product Item No"
                                      list="#{'2':'Product Description'}"/></td>
                        <td><input type="button" id="search" value="Search"/><input type="button" id="clear" value="Clear"/></td>
                    </tr>
                </table>
                <p>2. Please select products and <input type="button" id="add" value="Add"/> them into quote</p>
                <table id="list" class="scroll" cellpadding="0" cellspacing="0"></table>
                <div id="pager" class="scroll" style="text-align:center;"></div>
                <p>3. Please check your quote</p>
                <input type="button" id="delete" value="Delete Quote Items"/>
                <table id="newQuote" class="scroll" cellpadding="0" cellspacing="0"></table>
                <div id="templatepager" class="scroll" style="text-align:center;"></div>
                <s:form name="form">
                    <input type='hidden' name=supnexusid value="<%=supNexusId%>" />
                    <s:hidden id="templateId" name="templateId"/>
                    <input type="button" id="save1" value="Save"/>
                </s:form>
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