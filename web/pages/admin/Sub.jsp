<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.*"%>
<%
        List mytabs = (List) session.getAttribute("mytabs");
%>
<div id="subMenu">
    <ul>
        <% if (mytabs.contains("users")) {%>
        <li>
            <a href="<s:url action="users"/>" title="Users" >USERS</a>
        </li>
        <%}%>


        <% if (mytabs.contains("roles")) {%>
        <li>
            <a href="<s:url action="roles"/>" title="Roles" >ROLES</a>
        </li>
        <%}%>


        <% if (mytabs.contains("myaccount")) {%>
        <li>
            <a href="<s:url action="myaccount"/>" title="My Account" >MY ACCOUNT</a>
        </li>
        <%}%>
       
    </ul>    

</div>