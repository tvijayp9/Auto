<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.*"%>
<%
        List mytabs = (List) session.getAttribute("mytabs");
%>
<s:i18n name="umg_transactions">
    <div id="subMenu">
        <ul>
            
            <% if (mytabs.contains("ShowInBoundlist")) {%>
            <li>
                <a href="<s:url action="ShowInBoundlist"/>" title="View INBOUND Transactions" ><s:text name="transactions.inboundTransactions"/></a>
            </li>
            <%}%>

                
            <% if (mytabs.contains("ShowOutBoundlist")) {%>
            <li>
                <a href="<s:url action="ShowOutBoundlist"/>" title="View OUTBOUND Transactions" ><s:text name="transactions.outboundTransactions"/></a>
            </li>
            <%}%>
            
        </ul>
    </div>
</s:i18n>