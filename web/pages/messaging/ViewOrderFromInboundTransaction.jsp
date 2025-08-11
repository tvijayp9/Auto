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
        <title>Print Order</title>
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
        <p>Order Delivery Date: <span class="general"><s:property value="deliveryDate"/></span></p>
        <table cellspacing="0" id="playlistTable">
            <colgroup>
                <col id="PlaylistCol" />
                <col id="trackCol" />
                <col id="artistCol" />
                <col id="albumCol" />
            </colgroup>
            <thead>
                <tr>
                    <th id="playlistPosHead" scope="col">Product Item No</th>
                    <th scope="col">Product Description</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                    <th scope="col">Tax</th>
                    <th scope="col">Cost</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="items">
                    <tr>
                        <td><s:property value="productCode"/></td>
                        <td><s:property value="description"/></td>
                        <td><s:property value="quantity"/></td>
                        <td>$<s:property value="price"/></td>
                        <td>$<s:property value="tax"/></td>
                        <td>$<s:property value="cost"/></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
        <p>Total Price: $<s:property value="totalPrice"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Tax: $<s:property value="totalTax"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Cost: $<s:property value="totalCost"/></p>
    </body>
</html>
