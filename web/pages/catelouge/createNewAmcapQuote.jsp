<%-- 
    Document   : main
    Created on : 29/08/2023, 14:15:00
    Author     : Vijay Thumma
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Quote</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default1.css" rel="stylesheet" type="text/css" media="screen" />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
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
        <script type="text/javascript" src="dwr/interface/catalogueService.js"></script>
        <script type="text/javascript" src="javascript/jquery.loadmask.min.js"></script>

        <script type="text/javascript" src="javascript/createNewAmcapQuote.js?01032027"></script>
    </head>
    <body>
        <%
            String supNexusId=session.getAttribute("supplierid").toString();
            String quoteName=session.getAttribute("quoteName").toString();
            String comment=session.getAttribute("comment").toString();
            System.out.println("quotename="+quoteName+"....comment="+comment);
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
                <h1>Create New Quote</h1>
               
                    <input type='hidden' name=supnexusid value="<%=supNexusId%>" />
               Quote Name: <s:textfield id="quoteName" name="quoteName"  theme="simple" maxlength="20"/>
               Comment: <s:textfield id="comment" name="comment"  theme="simple" maxlength="255"/>
                <p>1. Please search products </p>
                <table width="800" border="0">
                    <tr>
                        <td>Search For:</td>
                        <td><s:textfield id="searchFor" name="searchFor" theme="simple" maxlength="20"/></td>
                        <td>Search In:</td>
                        <td><s:select theme="simple" id="searchIn" name="searchIn" headerKey="1"
                                          headerValue="Location - Machine Type"
                                      list="#{'2':'Asset Name – Category','3':'Product Item No','4':'Product Description'}"/></td>
                        <td><input type="button" id="search" value="Search"/><input type="button" id="clear" value="Clear"/></td>
                </tr>
                </table>
                <p>2. Please select Site name and category name to search the products</p>
                <table width="900" border="0">
                    <tr>
                        <td><s:select label="Location - Machine Type" 
                                  name="sitename" 
                                  headerKey="0"
                                  headerValue="-- Please Select --"
                                  list="sitenames"/></td>
                        
                        <td>Asset Name – Category:</td>
                        <td><select id="categories" name="catName"></select></td>
                        <td></td>
                        <td><input type="button" id="search1" value="Search"/><input type="button" id="clearsite" value="Clear Location"/></td>
                </tr>
                </table>
                <div >
                    
                </div>
                <p>3. Please select products and <input type="button" id="add" value="Add"/> them into quote</p>
                <table id="list" class="scroll" cellpadding="0" cellspacing="0"></table>
                <div id="pager" class="scroll" style="text-align:center;"></div>
                <p>3. Please check your quote</p>
                 <s:form name="form">
                <input type="button" id="delete" value="Delete Items"/>
                <input type="button" id="addRecord" value="Add Record"/>
                
             <table id="newQuote" class="scroll" cellpadding="0" cellspacing="0"></table>
                <div id="templatepager" class="scroll" style="text-align:center;"></div>
                    <input type="button" id="checkout" value="Create Quote"/>
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