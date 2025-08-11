<%-- 
    Document   : printorder
    Created on : 13/02/2009, 3:21:13 PM
    Author     : Terry
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order</title>
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <script type="text/javascript">
            $(function(){
                $(":button").click(function(){
                    window.print();
                });
                $("tbody tr:nth-child(odd)").css("background-color", "#edf5ff");
            });
        </script>
        <link href="css/printorder.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <p id="input"><input type="button" value="Print" /></p>
        <h3>Buyer: <s:property value="customerCode"/></h3>
        <p>Purchase Order No: <span class="general"><s:property value="orderNumber"/></span></p>
        <p>Comment: <span class="general"><s:property value="comment"/></span></p>
        <p>Order Delivery Date: <span class="general"><s:property value="orderDate"/></span></p>
        <table cellspacing="0">
            <colgroup>
                <col/>
                <col/>
                <col/>
                <col/>
                <col/>
                <col/>
            </colgroup>
            <thead>
                <tr>
                    <th scope="col">SNO</th>
                    <th scope="col">Buyer Code</th>
                    <th scope="col">Supplier Code</th>
                    <th scope="col">GTIN</th>
                    <th scope="col">Product name</th>
                    <th scope="col">UOM</th>
                    <th scope="col">QTY</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="items" status="status">
                    <tr>
                        <td class="quantity"><s:property value="#status.count"/></td>
                        <td><s:property value="productid"/></td>
                        <td><s:property value="product_Code1"/></td>
                        <td><s:property value="gtin"/></td>
                        <td><s:property value="name"/></td>
                        <td><s:property value="uom"/></td>
                        <td class="quantity"><s:property value="qty"/></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </body>
</html>
