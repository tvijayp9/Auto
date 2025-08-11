<%@ taglib prefix="s" uri="/struts-tags" %>

<?xml version="1.0" encoding="utf-8"?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<HEAD>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default2.css" rel="stylesheet" type="text/css" media="screen" />
        <!--[if !IE 6 ]>
        <link href="css/default2.css" rel="stylesheet" type="text/css" media="screen" />
        <![endif]-->
        <!--[if IE 6 ]>
        <link href="css/ie6.css" rel="stylesheet" type="text/css" media="screen" />
        <style type="text/css">img {behavior: url('css/iepngfix.htc');} </style>
        <![endif]-->
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/basic/grid.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/jqModal.css" />
        <link href="theme/jqueryUITheme/ui.all.css" rel="stylesheet" type="text/css" />
        <link href="theme/jqueryUITheme/confirmorder.css" rel="stylesheet" type="text/css" />
		<SCRIPT type="text/javascript" SRC="javascript/myscript.js"></SCRIPT>
		
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <script type="text/javascript">
		
            var hookUrl='<%=session.getAttribute("hookUrl")%>';
		
		$(document).ready(function(){
				$("#submit1").click(function(){
					$("#submit1").attr("disabled", "disabled");
					$("form").attr("action", hookUrl).submit();
					$("form").attr("action", "resetShoppingCart.action").submit();
				});
			});
		

        </script>
</HEAD>

<BODY>
<div id="container">
            <div id="header">
                <jsp:include flush="true" page="..//logoForOrder.jsp"/>
                <div id="header-menu">
                    <jsp:include flush="true" page="Head.jsp"/>
                    <jsp:include flush="true" page="Sub.jsp"/>
                </div><!-- end #header-menu -->
            </div><!-- end #header -->
			<div id="body-wrap">
<form method=post target=_top >

<!--For more information about the interface fields, refer to the catalog interface description-->

<!--Item number 1-->
<br/><br/>
<p>Your cart of  <s:property value="%{count}" />  items with a total value of $<s:property value="%{totalPrice}" /> (ex. gst)
has been assembled and is ready to be processed,<br/> please click the Transfer Items To SAP button below.</p></br>

<input type="hidden" name="~OkCode" value="ADDI" />
<input type="hidden" name="~caller" value="CTLG" />
<input type="hidden" name="~TARGET" value="_top" />
<input type="hidden" name="OCI_VERSION" value="4.0" />
<s:iterator value="result" status="stat">


<input type="hidden" name="NEW_ITEM-DESCRIPTION[<s:property value="#stat.count" />]" value = "<s:property value="description"/>" >
<input type="hidden" name="NEW_ITEM-UNIT[<s:property value="#stat.count" />]" value = "EA">
<input type="hidden" name="NEW_ITEM-VENDORMAT[<s:property value="#stat.count" />]" value = "<s:property value="productCode"/>">
<input type="hidden" name="NEW_ITEM-QUANTITY[<s:property value="#stat.count" />]" value = "<s:property value="quantity"/>" >
<input type="hidden" name="NEW_ITEM-PRICE[<s:property value="#stat.count" />]" value = "<s:property value="unitPrice"/>" >
<input type="hidden" name="NEW_ITEM-CURRENCY[<s:property value="#stat.count" />]" value = "AUD">
<input type="hidden" name="NEW_ITEM-LEADTIME[<s:property value="#stat.count" />]" value = "1">
<input type="hidden" name="NEW_ITEM-MANUFACTCODE[<s:property value="#stat.count" />]" value = "">
<input type="hidden" name="NEW_ITEM-MANUFACTMAT[<s:property value="#stat.count" />]" value = "">

</s:iterator>
<input type="submit" value="Transfer Items To SAP" id="submit1" name="submit1" /><br>
</form>

</div>
<div id="footer">
                <div id="footer-content">
                    &nbsp;&copy; 2016 IVBPlus Pty Ltd &middot;
                </div>
            </div><!-- end #footer -->
        </div><!-- end div#container -->
        <div id="footer-shadow">
            <img src="images/bgFooter.gif" width="964px" height="9px" alt=""/>
        </div>

</BODY>

</HTML>