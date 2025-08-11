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
        <title>User Management</title>
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
				<s:form action="assignpartner">
                <table border="0">
                    <tr>
                        <td colspan="2"><h4>You can assign the partner to user</h4>
                        </td>
                    </tr>
                    <tr>
                        <td width="290"><h4>Select the user from below drop down box</h4>
                            <s:select id="user"
                                              name="user"
                                              list="userList"
                                              headerKey="0"
                              headerValue="-- Please Select User--"
                                          listKey="id" listValue="name"/></td>
                        </td>
                        <td><h4>Selects the partners from this below list</h4>
                         <s:checkboxlist list="partnerList" name="partner" cssStyle="width:230px;background-color: #FCFCFC;"  />
						</td>
                    </tr>
					
                        <td align="left"><s:submit value="Assign" name="submit" /></td>
                    </tr>
                </table>
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
