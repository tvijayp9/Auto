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
        <title>Quote Confirmation</title>
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
        <script type="text/javascript" src="javascript/createNewQuote.js"></script>
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
                    <table width="100%">
                        <tr>
                            <td>
                                <table width="100%">
                                    <tr>
                                        <td width="100%"><b>You have selected the following items. Please fill in below details in order to send this quote.If you want to change the items click ModifyQuote.</b></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table width="100%">
									<tr>
										
										<td>
										 <b> Quote Name: </b>&nbsp;&nbsp;&nbsp;<s:textfield id="templateName" name="templateName" theme="simple" maxlength="20"/></td><td>
                                        
										<b> Email:</b>&nbsp;&nbsp;&nbsp; <s:textfield id="email" name ="email"  theme="simple"   size="50" maxlength="100"/></td>
                                    </tr>
									<tr>
                                      <td></td><td></td>
                                    </tr>
									<tr>
									<td>
										<b> Comment :</b> &nbsp;&nbsp;&nbsp;<s:textarea id="comment" name ="comment"   cols="40" rows="3" theme="simple"  /></td><td></td>
									</tr>
                                </table>
                        </td></tr>
                        <tr>
                            <td>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table id="newQuote" class="scroll" cellpadding="0" cellspacing="0"></table>
                                <div id="templatepager" class="scroll" style="text-align:center;"></div>
                                <p>Total Price: $<s:property value="totalPrice"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Tax: $<s:property value="totalTax"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Cost: $<s:property value="totalCost"/></p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table width="95%">
                                    <tr>
                                       <td width="40%"></td>
                                        <td width="60%">
										<input type="button" id="modify" value="Modify Quote"/>
                                        <input type="button" id="save" value="Send Quote"/></td>
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