<%-- 
    Document   : umgLogin
    Created on : 07/08/2009, 4:43:21 PM
    Author     : Terry
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
<!--        <link href="css/umglogin.css" rel="stylesheet" type="text/css" />-->
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <script type="text/javascript">
            $(function(){
                $(":input:first").focus();
            });
        </script>
    </head>
    <body>
        <div id="container">
            <div id="header">
            </div>
            <div id="content">
               <table width="100%">
                    <tr>
                        <td>
                            <h4><br>Your session has expired.</br></h4>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <h4><br>Please login again to use the system.</br></h4>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>
