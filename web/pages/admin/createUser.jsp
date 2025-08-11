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
        <script type="text/javascript" src="dwr/engine.js"></script>
        <script type="text/javascript" src="dwr/interface/administrationService.js"></script>
        <script type="text/javascript" src="javascript/createUser.js"></script>
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
                <h4>Create User</h4>
                <br/>
                <s:form theme="simple">
                    <table width="250" border="0">
                        <tr>
                            <td>User ID:</td>
                            <td><s:textfield id="userId" name="userId" size="20" maxlength="20"/></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><s:textfield id="password" name="password" size="20" maxlength="20"/></td>
                        </tr>
                        <tr>
                            <td>Name:</td>
                            <td><s:textfield id="name" name="name" size="20" maxlength="40"/></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><s:textfield id="email" name="email" size="20" maxlength="40"/></td>
                        </tr>
                        <tr>
                            <td>Role:</td>
                            <td><s:select id="roles"
                                              name="roles"
                                              list="roles"
                                              headerKey="0"
                              headerValue="-- Please Select Role--"
                                          listKey="id" listValue="name"/></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><input type="button" id="create" value="Create User"/></td>
                        </tr>
                    </table>
                </s:form>
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
