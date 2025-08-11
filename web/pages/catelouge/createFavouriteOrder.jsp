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
        <title>Create Favourite Order</title>
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
        <script type="text/javascript" src="javascript/createFavouriteOrder.js"></script>
        <style type="text/css">
            <!--
            #container #body-wrap .notExist {
                color: #FF0000;
                font-size: 18px;
                font-weight: bold;
            }
            #container #body-wrap .indicate {
                color: #000000;
            }
            -->
        </style>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include flush="true" page="..//logoForOrder.jsp"/>
                <div id="header-menu">
                    <jsp:include flush="true" page="Head.jsp"/>
                    <jsp:include flush="true" page="Sub.jsp"/>
                </div><!-- end #header-menu -->
            </div><!-- end #header -->
            <div id="body-wrap">
                <h3>Fill in the quantity to order.     <span class="notExist">*</span><span class="indicate"> indicates this product is not in your current list. You can not order it.</span></h3>
                <table  width="870px">
                    <tr>
                        <td width="15%"><B><u>Product Code</u></B></td>
                        <td width="15%"><B><u>GTIN</u></B></td>
                        <td width="40%"><B><u>Product name</u></B></td>
                        <td width="15%"><B><u>UOM</u></B></td>
                        <td width="5%"><B><u>QTY</u></B></td>
                    </tr>
                </table>

                <s:form>
                    <s:hidden name="uname" value="%{uname}"></s:hidden>
                    <s:hidden name="orderDate" value="%{orderDate}"></s:hidden>
                    <s:hidden id="favouriteOrderId" name ="favouriteOrderId"  value="%{favouriteOrderId}"></s:hidden>
                    <s:actionerror />
                    <s:fielderror />
                    <tr>
                        <td>
                            <div style=" width: 900px; height: 230px;overflow:auto">
                                <table width="870px">
                                    <s:iterator value="favouriteOrderItems" var="item">
                                        <s:if test="#item.exist">
                                            <tr>
                                                <td width="15%"><s:property value="productid"/></td>
                                                <td width="15%"><s:property value="gtin"/></td>
                                                <td width="40%"><s:property value="name"/></td>
                                                <td width="15%"><s:property value="uom"/></td>
                                                <td width="5%"><s:textfield cssClass="quantity" name ="%{gtin}"  size="5" value="%{qty}" theme="simple"></s:textfield></td>
                                            </tr>
                                        </s:if>
                                        <s:else>
                                            <tr>
                                                <td width="15%"><span class="notExist">*</span><s:property value="productid"/></td>
                                                <td width="15%"><s:property value="gtin"/></td>
                                                <td width="40%"><s:property value="name"/></td>
                                                <td width="15%"><s:property value="uom"/></td>
                                                <td width="5%"><s:textfield size="5" disabled="true" theme="simple"></s:textfield></td>
                                            </tr>
                                        </s:else>
                                    </s:iterator>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="95%">
                                <tr>
                                    <td width="25%"></td>
                                    <td width="20%"></td>
                                    <td width="10%">
                                    <input type="button" value="Next"/></td>
                                    <td width="15%"><input type="button" value="Print Item List"/></td>
                                    <td width="30%"></td>
                                </tr>
                                <tr>
                                    <td width="50">

                                    </td>
                                    <td width="50">

                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
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