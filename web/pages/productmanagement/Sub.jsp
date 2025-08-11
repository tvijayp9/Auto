<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.*"%>
<%
        List mytabs = (List) session.getAttribute("mytabs");
%>
<div id="subMenu">
    <ul>
        <% if (mytabs.contains("uploadparts")) {%>
        <li>
            <a href="<s:url action="uploadparts"/>" title="Upload" >Upload</a>
        </li>
        <%}%>
        <% if (mytabs.contains("products")) {%>
        <li>
            <a href="<s:url action="products"/>" title="Products" >BHP Products</a>
        </li>
        <%}%>
    </ul>    

</div>