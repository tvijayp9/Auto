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
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/basic/grid.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/jqModal.css" />
        <link href="theme/jqueryUITheme/ui.all.css" rel="stylesheet" type="text/css" />
        <script src="javascript/jqGrid/jquery.jqGrid.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqModal.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqDnR.js" type="text/javascript"></script>
        <script type="text/javascript" src="javascript/jquery-ui-personalized-1.6rc6.min.js"></script>
        <script type="text/javascript" src="javascript/disableRightClick.js"></script>
        <script type="text/javascript" src="javascript/quotecheckout.js"></script>
        <link href="theme/jqueryUITheme/confirmorder.css" rel="stylesheet" type="text/css" />

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
                <s:form>
                    <s:hidden id="templateId" name="templateId"/>
                    <table width="100%">
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr>
                                        <td width="100%"><b>You have selected the following items. If you want to change the items click Modify Order.</b></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr>
                                        <td width="100%"><b> Order Delivery Date :</b> &nbsp;&nbsp;&nbsp;&nbsp;<s:textfield id="deliveryDate" name ="deliveryDate"  size="10" theme="simple" readonly="true"></s:textfield>(DD/MM/YYYY) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<B> Comment :</B> &nbsp;&nbsp;&nbsp;&nbsp;<s:textfield id="comment" name ="comment"  maxlength="40" size="30" theme="simple"></s:textfield></td>
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
                                        <td width="25%"></td>
                                        <td width="20%"></td>
                                        <td width="10%">
                                        <input type="button" value="Modify Order"/></td>
                                        <td width="15%"><input type="button" value="Next"/></td>
                                        <td width="30%"></td>
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