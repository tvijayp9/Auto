
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unlock a Transaction</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
		<script type="text/javascript">
			function closePage(){
			window.close();
			if (window.opener && !window.opener.closed) {
window.opener.location.reload();
} 
			}
			function unlock(){
				document.form.action="updatetransaction.action";
				document.form.submit();
			}
			function refreshParent(){
			if (window.opener && !window.opener.closed) {
window.opener.location.reload();
} 
			}
		</script>
        
    </head>
    
    <body onUnload="javascript:refreshParent()">
        <s:i18n name="resolution">
            <s:form method="post" name="form">
               
         <p align="center"><font color="#000080" size="4">Please Enter Transaction Number to Unlock :</font></p>
        <table><tr>
		<s:textfield id="transId" name="transId" key="Transaction Number"/></tr><tr><td>
		<input type="submit" value="Unlock" onClick="javascript:unlock()"/></td><td>
		<input type="submit" value="close" onClick="javascript:closePage()"/></td></tr></table>
        </s:form>
        <p align="center"><font color="#000080" size="4"><s:actionmessage /></font></p>
        </s:i18n>
    </body>
</html>


