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
        <table width="100px">
            <tr>
                <td><p>Purchase Order No: <span class="general"><s:property value="orderNumber"/></span></p></td>
                <td><h3>Buyer: <s:property value="customerCode"/></h3></td>
            </tr>
            <tr>
                <td ><h1>Ship To Address</h1></td>
                <td ><h1>Bill To Address</h1></td>
            </tr>
            <tr>
                <td >
					<s:property value="orderAddressData.shipToName"/></br>
                    <s:property value="orderAddressData.shipToStreet"/></br>
                    <s:property value="orderAddressData.shipToCity"/></br>
                    <s:property value="orderAddressData.shipToState"/></br>
                    <s:property value="orderAddressData.shipToPOCode"/></br>
                    <s:property value="orderAddressData.shipToCountry"/></br>
                    <s:property value="orderAddressData.shipToEmail"/></br>
                   LocationCode: <s:property value="orderAddressData.shipToAddressId"/></br>
                   Attn:<s:property value="orderAddressData.shipToDelivery"/></br>
				   Shipping Note:<s:property value="orderAddressData.shippingNote"/></br>
				</td>
                <td >
				    <s:property value="orderAddressData.billToName"/></br>
                    <s:property value="orderAddressData.billToDelivery"/></br>
                    <s:property value="orderAddressData.billToStreet"/></br>
                    <s:property value="orderAddressData.billToCity"/></br>
                    <s:property value="orderAddressData.billToState"/></br>
                    <s:property value="orderAddressData.billToPOCode"/></br>
                    <s:property value="orderAddressData.billToCountry"/></br>
                   Billing: <s:property value="orderAddressData.billingName"/></br>
				   Header Note: <s:property value="orderAddressData.headerNote"/></br>
				</td>
			</tr>
        </table>
        
        <table cellspacing="0" id="playlistTable">
            <colgroup>
                <col id="PlaylistCol" />
                <col id="trackCol" />
                <col id="trackCol" />
		<col id="trackCol" />
		<col id="trackCol" />
                <col id="artistCol" />
                <col id="albumCol" />
            </colgroup>
            <thead>
                <tr>
                    <th id="playlistPosHead" scope="col">Product Item No</th>
                    <th scope="col">Product Description</th>
                    <th scope="col">Delivery Date</th>
                    <th scope="col">Comment</th>
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
                        <td><s:property value="deliveryDate"/></td>
                        <td><s:property value="lineComment"/></td>
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
