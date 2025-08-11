<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.*"%>
<%
        List mytabs = (List) session.getAttribute("mytabs");
%>
<div id="subMenu">
    
    <ul>
       
            <% if (mytabs.contains("CreateNewOrderForShoppingCart")) {%>
            <li><a href="<s:url action="CreateNewOrderForShoppingCart"/>" title="Catalogue" >SHOPPING CART</a></li>
            <%}%>
        
        
            <% if (mytabs.contains("showforwardorderlist")) {%>
            <li><a href="<s:url action="showforwardorderlist"/>" title="View Future Order" >FUTURE ORDERS</a></li>
            <%}%>
      
       
            <% if (mytabs.contains("showfavouritelist")) {%>
            <li><a href="<s:url action="showfavouritelist"/>" title="View Favourite Order" >FAVOURITE ORDERS</a></li>
            <%}%>
        
            <% if (mytabs.contains("ShowQuotesList")) {%>
            <li><a href="<s:url action="ShowQuotesList"/>" title="Quotes" >QUOTES</a></li>
            <%}%>
       
            <% if (mytabs.contains("ShowAmcapQuotesList")) {%>
            <li><a href="<s:url action="ShowAmcapQuotesList"/>" title="Quotes" >QUOTES</a></li>
            <%}%>
            
            <% if (mytabs.contains("showorderlist")) {%>
            <li><a href="<s:url action="showorderlist"/>" title="View Past Order" >ORDER HISTORY</a></li>
            <%}%>
        
            <% if (mytabs.contains("feedback")) {%>
            <li><a href="mailto:xmlyessales@xmlyes.com">FEED BACK</a></li>
            <%}%>
       
    </ul>
</div>