<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.*"%>
<%
        Properties mysection = (Properties) session.getAttribute("mysections");
%>
<div id="tabMenu">
        <s:i18n name="umg_logoHead">
    <ul>
            <li>
                <% if (mysection.containsKey("showpartnerlist")) {
         String act_value = mysection.getProperty("showpartnerlist");%>
                <a href="<%=act_value%>.action" title="Trading Partners" >
                <span class="twoWords" title="Trading Partners" ><s:text name="logoHead.trading"/><br /><s:text name="logoHead.partner"/></span></a>
                <%}%>
            </li>

            <li>
                <% if (mysection.containsKey("showmessagelist")) {
         String act_value = mysection.getProperty("showmessagelist");%>
                <a href="<%=act_value%>.action" title="Messages" class="activeTab"><span class="oneWord"><s:text name="logoHead.transaction"/></span></a>
                <%} 
                %>
            </li>
            <li>
                <% if (mysection.containsKey("admin")) {
         String act_value = mysection.getProperty("admin");%>
                <a href="<%=act_value%>.action" title="Administration"><span class="oneWord"><s:text name="logoHead.administration"/></span></a>
                <%} 
                %>
            </li>
            <li>
                <% if (mysection.containsKey("showtranasctionslist")) {
         String act_value = mysection.getProperty("showtranasctionslist");%>
                <a href="<%=act_value%>.action" title="Data Resolution"><span class="twoWords"><s:text name="logoHead.data"/><br /><s:text name="logoHead.resolution"/></span></a>
                <%}
                %>
            </li>
			<li>
                <% if (mysection.containsKey("productmanagement")) {
         String act_value = mysection.getProperty("productmanagement");%>
                <a href="<%=act_value%>.action" title="Product Managaement"><span class="twoWords"><s:text name="logoHead.product"/><br/><s:text name="logoHead.management"/></span></a>
                <%}%>
            </li>
        </ul>
      </s:i18n>
</div>



