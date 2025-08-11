<%-- 
    Document   : Ausdrill Invoice
    Created on : 18/11/2015, 04:03:13 PM
    Author     : Vijay Thumma
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice</title>
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <script type="text/javascript">
            $(function(){
                $("#print").click(function(){
                    window.print();
                });
                $("tbody tr:nth-child(odd)").css("background-color", "#edf5ff");
            });
        </script>
        <link href="css/printorder.css" rel="stylesheet" type="text/css"/>
		<link href="css/reset1.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <!--[if !IE 6 ]>
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <![endif]-->
        <!--[if IE 6 ]>
        <link href="css/ie6.css" rel="stylesheet" type="text/css" media="screen" />
        <style type="text/css">img {behavior: url('css/iepngfix.htc');} </style>
        <![endif]-->
        <SCRIPT type="text/javascript" SRC="javascript/myscript.js">
        </SCRIPT>
        
       <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <script type="text/javascript" src="javascript/invoiceProductList.js"></script>
        <link href="css/InBoundMessageList.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/basic/grid.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/jqModal.css" />
        <link href="theme/jqueryUITheme/ui.all.css" rel="stylesheet" type="text/css" />
        <script src="javascript/jqGrid/jquery.jqGrid.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqModal.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqDnR.js" type="text/javascript"></script>
        <script type="text/javascript" src="javascript/jquery-ui-personalized-1.6rc6.min.js"></script>
        <script type="text/javascript" src="javascript/jquery.timers-1.1.2.js"></script>
        
    </head>
    <body>
	<form>
        <!--<p id="input"><input type="button" value="Print" /></p>-->
		 <p id="input"><input type="button" id="print" value="Print"/>  </p>
		 <table width="100px">
           <s:textfield label="invoiceNo" name="invoiceNo" />
		  <!-- <s:textfield key="messageId" />-->
		   <input type="hidden" id="messageId" value="<s:property value='messageId'/>"/>
		  <!-- <input type="hidden" id="var1" value="<s:property value='messageId'/>"/> -->
           <input type="hidden" name="orderNumber" value="<s:property value='orderNumber'/>"/> 
		   
        </table>
		 
		 <table width="100px">
            <tr>
                <td><p>Purchase Order No: <span class="general"><span id="orderNumber"><s:property value="orderNo"/></span></span></p></td>
                <td><h3>Buyer: <s:property value="company"/></h3></td>
            </tr>
           
        </table>
		 <table id="prodlist" class="scroll" cellpadding="0" cellspacing="0"></table>
		<!-- <table id="list" class="scroll" cellpadding="0" cellspacing="0"></table>-->
		 
        
         <div  align="centre">
        <input type="button" id="generateInvoice" value="Generate Invoice"/> 
		</div>
    </form>
	</body>
</html>
