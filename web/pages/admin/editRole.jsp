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
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/basic/grid.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/jqModal.css" />
        <link href="theme/jqueryUITheme/ui.all.css" rel="stylesheet" type="text/css" />
        <script src="javascript/jqGrid/jquery.jqGrid.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqModal.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqDnR.js" type="text/javascript"></script>
        <script type="text/javascript" src="javascript/jquery-ui-personalized-1.6rc6.min.js"></script>
        <script type="text/javascript" src="javascript/editRole.js"></script>
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
            <div id="body-wrapadmin">
                <s:hidden id="existingCount" name="existingCount"/>
                <table border="0">
                    <tr>
                        <td colspan="2"><h4>1. You can modify role name below</h4>
                            <s:form>
                                <s:textfield id="roleName" name="roleName" label="Role Name" size="30" maxlength="30"/>
                                <s:hidden id="roleId" name="roleId"/>
                                <s:hidden id="moreTabs" name="moreTabs"/>
                                <s:hidden id="existingTabs" name="existingTabs"/>
                            </s:form>
                        <br/></td>
                    </tr>
                    <tr>
                        <td><h4>2. Your can delete existing functions below for this role</h4>
                            <table id="list2" class="scroll" cellpadding="0" cellspacing="0"></table>
                            <div id="pager2" class="scroll" style="text-align:center;"></div>
                        <br/></td>
                        <td><h4>3. You can add more functions below for this role</h4>
                            <table id="list" class="scroll" cellpadding="0" cellspacing="0"></table>
                            <div id="pager" class="scroll" style="text-align:center;"></div>
                        <br/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><h4>4. Save all of modifications by clicking<input type="button" id="save" value="Save"/></h4></td>
                    </tr>
                </table>
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
