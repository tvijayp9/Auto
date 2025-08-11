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
        <title>Create Order</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default2.css" rel="stylesheet" type="text/css" media="screen" />
        <!--[if !IE 6 ]>
        <link href="css/default2.css" rel="stylesheet" type="text/css" media="screen" />
        <![endif]-->
        <!--[if IE 6 ]>
        <link href="css/ie6.css" rel="stylesheet" type="text/css" media="screen" />
        <style type="text/css">img {behavior: url('css/iepngfix.htc');} </style>
        <![endif]-->
        <SCRIPT type="text/javascript" SRC="javascript/myscript.js"></SCRIPT>
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <script type="text/javascript" src="javascript/disableRightClick.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/basic/grid.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/jqModal.css" />
        <link href="theme/jqueryUITheme/ui.all.css" rel="stylesheet" type="text/css" />
        <script src="javascript/jqGrid/jquery.jqGrid.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqModal.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqDnR.js" type="text/javascript"></script>
        <script type="text/javascript" src="javascript/jquery-ui-personalized-1.6rc6.min.js"></script>
        <script type="text/javascript" src="javascript/createOrderFromShoppingCart.js"></script>
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
                <table width="100%">
                    <tr>
                        <td width="100%"><b>This is order Confirmation page. You have selected the following items please verify and create order.</b></td>
                    </tr>
                </table>
                <s:form>
                    <table width="100%">
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr>
                                        <td width="33%" ><B> Order No :</B> &nbsp;&nbsp;
                                            <s:textfield id="orderNumber" name ="orderNumber"   size="20" maxlength="20" theme="simple"></s:textfield>
                                            <s:hidden name ="autoOrderNumber" theme="simple"></s:hidden>
                                        </td>
                                        <td width="33%" ><B> Comment :</B> &nbsp;&nbsp;<s:property value="comment"/></td>
                                        <td width="33%"><b> Delivery Date :</b> &nbsp;&nbsp;<s:property value="deliveryDate"/>&nbsp;&nbsp;(DD/MM/YY)
                                        </td>
                                    </tr>
                                </table>
                        </td></tr>
                        <tr>
                            <td>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table id="shoppingcart" class="scroll" cellpadding="0" cellspacing="0"></table>
                                <div id="shoppingcartpager" class="scroll" style="text-align:center;"></div>
                                <p>Total Price: $<s:property value="totalPrice"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Tax: $<s:property value="totalTax"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Cost: $<s:property value="totalCost"/></p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table width="95%">

                                    <tr>
                                        <td width="35%"></td>
                                        <td width="20%"><br/><input type="button" value="Confirm & Send Order"/></td>
                                        <td width="10%"><br/><input type="button" value="Cancel Order"/></td>
                                        <td width="35%"><br/><input type="button" value="Confirm & Send & Add To Favourite order/Quote"/></td>
                                    </tr>
                                </table>
                            </td>
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