<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.*"%>
<%
        List mytabs = (List) session.getAttribute("mytabs");
%>
<s:i18n name="umg_tradingPartners">
    <div id="subMenu">
        <ul>
            
            <% if (mytabs.contains("showpartnerlist")) {%>
            <li>
                <a href="<s:url action="showpartnerlist"/>" title="My Partner List"><s:text name="tradingPartners.myTradingPartners"/></a>
            </li>
            <%}%>


            <% if (mytabs.contains("CreatePartner")) {%>
            <li>
                <a href="<s:url action="BeforeCreatePartner"/>" title="Create Partner"><s:text name="tradingPartners.createMembers"/></a>
            </li>
            <%}%>


            <% if (mytabs.contains("ShowPriceType")) {%>
            <li>
                <a href="<s:url action="ShowPriceType"/>" title="My Partner List"><s:text name="tradingPartners.changepricetype"/></a>
            </li>
            <%}%>

        </ul>
    </div>
</s:i18n>