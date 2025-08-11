<%-- 
    Document   : fileupload
    Created on : 26/05/2023, 10:00:58 AM
    Author     : user
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <!--[if !IE 6 ]>
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <![endif]-->
        <!--[if IE 6 ]>
        <link href="css/ie6.css" rel="stylesheet" type="text/css" media="screen" /> 
        <style type="text/css">img {behavior: url('css/iepngfix.htc');} </style>
        <![endif]-->
        <SCRIPT type="text/javascript" SRC="javascript/myscript.js"></SCRIPT>
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <style type="text/css">
            .errors {
                background-color:#FFCCCC;
                width:400px;
                margin-bottom:8px;
            }
            .errors li{ 
                list-style: none; 
            }
            .info {
                background-color:#DDFFDD;
                width:400px;
            }
            .info li{ 
                list-style: none; 
            }
</style>
    </head>
    <div id="container">
        <div id="header">
            <jsp:include flush="true" page="..//logo.jsp"/>
            <div id="header-menu">
                <jsp:include flush="true" page="Head.jsp"/>
                <jsp:include flush="true" page="Sub.jsp"/>

            </div><!-- end #header-menu -->
        </div><!-- end #header -->
        <div id="body-wrapadmin">
            <s:form theme="simple" action="uploadpartsAction" method="POST" enctype="multipart/form-data">
                <s:if test="hasActionErrors()">
                <div class="errors">
                  <s:actionerror />
                </div>
                </s:if>
                <s:if test="hasActionMessages()">
                   <div class="info">
                     <s:actionmessage />
                   </div>
                </s:if>
                <table>
                    <tr>
                        <td>
                            <br />
                        </td>
                    </tr>
                    <tr>
                            <td>Partner:</td>
                            <td><s:select name="partnerId"
                                              list="partnerList"
                                              headerKey="0"
                              headerValue="-- Please Select A Partner--"
                                          listKey="partnerId" listValue="partnerName"/></td>
                        </tr>
                        <tr></tr>
                        <tr>
                            <td>Select a Price File to upload:</td>
                            <td><s:file name="fileUpload" size="40" /></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><s:submit value="Upload" name="submit" /></td>
                        </tr>
                </table>
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
