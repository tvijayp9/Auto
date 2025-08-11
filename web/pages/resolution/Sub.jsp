<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.*"%>
<%
        List mytabs = (List) session.getAttribute("mytabs");
%>
<script>
    function unlockWindow(){
        window.open('pages/resolution/UnlockTransaction.jsp','HelpWindow','toolbar=no,top=250,left=500,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=400,height=245');
    }
</script>
<s:i18n name="resolution">
    <div id="subMenu">
        <ul>
            <% if (mytabs.contains("showtranasctionslist")) {%>
                <li><a href="<s:url action="showtranasctionslist"/>" title="Transaction Center">Transaction Center</a></li>
            <% 
			}%>
            <% if (mytabs.contains("unlock")) {%>
                <li><a href="javascript:unlockWindow();">Unlock a Transaction</a></li>
            <%} if (mytabs.contains("usermanagement")) {%>
			<li><a href="<s:url action="usermanagement"/>">User Management</a></li>
			<%} %>
        </ul>
    </div>
</s:i18n>