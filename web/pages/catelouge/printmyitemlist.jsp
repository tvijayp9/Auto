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
        <title>Category List</title>
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
        <script type="text/javascript" src="javascript/disableRightClick.js"></script>
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <script type="text/javascript">
            $(function(){
                $(":button").click(function(){
                    window.print();
                });
            });
        </script>
        <style type="text/css">

<!--
#input{
    text-align: center;
}
-->
</style>
    </head>
    <body>
        <p id="input"><input type="button" value="Print" /></p>
        <div id="subMenuLogo">
            <table width="850px" >
                <tr>
                    <td>
                    <img width="850" align="left" src="images/banner/${sessionScope.bannerName}" /></td>
                </tr>
            </table>
        <br/>
        </div>
        <table  width="850px">
            <tr>
                <td width="2%"></td>
                <td width="5%"><B><u>SNO</u></B></td>
                <td width="15%"><B><u>Product Code</u></B></td>
                <td width="15%"><B><u>GTIN</u></B></td>
                <td width="40%"><B><u>Product name</u></B></td>
                <td width="15%"><B><u>UOM</u></B></td>
                <td width="5%"><B><u>QTY</u></B></td>
            </tr>
        </table>
        <tr>
            <td>
                <div style=" width: 850px;">
                    <table width="850px">
                        <s:iterator value="items" status="status">
                            <tr>
                                <td width="3%"></td>
                                <td width="5%"><s:property value="#status.count"/></td>
                                <td width="15%"><s:property value="productid"/></td>
                                <td width="15%"><s:property value="gtin"/></td>
                                <td width="40%"><s:property value="name"/></td>
                                <td width="15%"><s:property value="uom"/></td>
                                <td width="5%"><s:textfield size="5" disabled="true" theme="simple"></s:textfield></td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </td>
        </tr>
    </body>
</html>