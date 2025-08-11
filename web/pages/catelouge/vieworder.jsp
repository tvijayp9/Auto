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
        <title>Order Confirmation</title>
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
        <script type="text/javascript">
            $(function(){
                $(":button").click(function(){
                    window.open('<s:url action="printorder.action"/>','','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no');
                    });
                });
        </script>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <<jsp:include flush="true" page="..//logoForOrder.jsp"/>
                <div id="header-menu">
                    <jsp:include flush="true" page="Head.jsp"/>
                    <jsp:include flush="true" page="Sub.jsp"/>
                </div><!-- end #header-menu -->
            </div><!-- end #header -->
            <div id="body-wrap" > 
                <h1 align="cenetr">Order Details</h1>
                <div style=" width: 930px; height: 100px;overflow:auto">
                    <table  width="90%" border="2"  >
                        <thead>
                            <tr valign="top" bgcolor="lightblue">
                                <th><B><u>Order Number</u></B></th>
                                <th><B><u>Order Date</u></B></th>
                                <th><B><u>Delivery Date</u></B></th>
                                <th><B><u>Status</u></B></th>
                                <th><B><u>Comment</u></B></th>
                            </tr>
                        </thead>
                        <tbody id ="offTblBdy">
                            <s:iterator value="orders">
                                <tr><td>
                                        <s:url id="viewmessage" action="vieworder">   <s:param name="orderid" value="%{id}" /></s:url>
                                    <s:a href="%{viewmessage}"><s:property value="orderno"/></s:a></td>
                                    <td><s:property value="orderdate"/></td>
                                    <td><s:property value="deldate"/></td>
                                    <td><s:property value="status"/></td>
                                    <td><s:property value="comment"/></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>                
                </div>
                <br/>
                <div style=" width: 930px; height: 150px;overflow:auto">
                    <table width="90%">
                        <tbody >                            
                            <tr>
                                <td> <B>Purchase Order No. </B></td>
                                <td><s:property value="pono"/></td>
                                <td><input type="button" value="Print" /></td>
                             </tr>
                            <tr>
                                <td> <B>Order Delivery Date :</B></td>
                                <td><s:property value="orderDate"/></td>
                            </tr>
                            <tr>
                                <td><B>Status : </B></td>
                                <td><s:property value="orderstatus"/></td>
                            </tr>
                            <s:set name="test" value="%{orderstatus}"/>
                            <tr>
                                <s:if test="%{#test=='Cancelled By User'}">  
                                    <td> <B>Reason :</B></td>
                                    <td ><s:property value="cancelreason"/></td>
                                </s:if>
                            </tr>
                            <s:if test="%{#test in 'Cancelled By User'}">  
                            </s:if>
                            <s:elseif test="%{#test=='InProgress'}">  
                            </s:elseif>
                            <s:else>
                            <tr>
                                <th ><br/> &nbsp;&nbsp;&nbsp;&nbsp;<B><u>Product Code</u></B>&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th ><br/> &nbsp;&nbsp;&nbsp;&nbsp;<B><u>GTIN</u></B>&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th ><br/><B><u>Product name</u></B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th ><br/><B><u>UOM</u></B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th ><br/><B><u>Quantity</u></B>&nbsp;</th>
                            </tr>
                            <s:iterator value="items">
                                <tr >
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="productid"/></td>
                                    <td >&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="gtin"/></td>
                                    <td><s:property value="name"/>&nbsp;&nbsp;</td>
                                    <td> <s:property value="uom"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td align=right><s:property value="qty"/></td>
                                </tr>                                    
                            </s:iterator>
                            </s:else>
                        
                        </tbody>                        
                    </table>
                </div>
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