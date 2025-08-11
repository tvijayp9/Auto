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
        <SCRIPT type="text/javascript" SRC="javascript/myscript.js">
        </SCRIPT>       
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
            <div id="body-wrap1"> 
                <h1>User List</h1>
                <div style=" width: 900px; height: 190px;overflow:auto"> 
                    <table  width="80%" cellpadding="0" cellspacing="5" >
                        <thead>
                            <tr valign="top" >
                                <th><a>Name</a> </th>
                                <th><a>Username</a></th>
                                <th><a>Password</a></th>
                                <th><a>Email</a></th>
                            </tr>
                        </thead>
                        <tbody id="offTblBdy">
                            <s:iterator value="col1">
                                <tr>
                                    <td><s:property value="contact"/></td>
                                    <td><s:property value="loginid"/></td>
                                    <td><s:property value="company"/></td>
                                    <td><s:property value="email"/></td>
                                    <td>
                                        
                                        <s:url id="viewuser" action="edituser">   
                                        <s:param name="userid" value="%{id}" /></s:url>
                                        <s:a href="%{viewuser}">Edit</s:a>
                                    </td>
                                    <td>
                                        <s:url id="viewuser" action="deleteuser">
                                        <s:param name="userid" value="%{id}" /></s:url>
                                        <s:a href="%{viewuser}">Delete</s:a>
                                    </td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                <h1>Are you sure? Do you really want to delete this user?</h1>
                <s:form action="userdeleted" method="post" validate="true">                    
                    <tr>
                        <td colspan="2">
                            <s:actionerror />
                            <s:fielderror />
                        </td>
                    </tr>
                                        
                    <s:textfield name="firstname" label="FirstName"/>
                    <s:textfield name="lastname" label="LastName"/>
                    <s:textfield name="pass" label="Password"/>
                    <s:textfield size="40" name="email" label="Email"/>
                    <br/>                    
                    <s:submit value="Delete" align="right"/>
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
