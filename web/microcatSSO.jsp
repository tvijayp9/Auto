<%-- 
    Document   : microcatSSO
    Created on : 1 Nov, 2019, 12:52:52 AM
    Author     : vasanth
--%>

<%@page import="com.nexus.domain.Microcat"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.nexus.saml.SAMLAttribute"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.nexus.saml.SamlIdp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    
        <title>Microcat SSO</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default1.css" rel="stylesheet" type="text/css" media="screen" />
        
        <script type="text/javascript" SRC="javascript/myscript.js"></script>
        <script type="text/javascript" src="javascript/disableRightClick.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid-4.7.0/css/ui.jqgrid.css" />
        <script src="javascript/jqGrid-4.7.0/jquery.js" type="text/javascript"></script>
       
        <link href="theme/jqueryUITheme/ui.all.css" rel="stylesheet" type="text/css" />
        <link href="css/jquery.loadmask.css" rel="stylesheet" type="text/css" media="screen" />
        <script src="javascript/jqGrid-4.7.0/js/i18n/grid.locale-en.js" type="text/javascript"></script>
        <script src="javascript/jqGrid-4.7.0/js/jquery.jqGrid.js" type="text/javascript"></script>
        <script src="javascript/jqGrid-4.7.0/js/jqModal.js" type="text/javascript"></script>
        <script src="javascript/jqGrid-4.7.0/js/jqDnR.js" type="text/javascript"></script>
        
        <script type="text/javascript" src="javascript/jquery.timers-1.1.3.js"></script>
        <script type="text/javascript" src="dwr/engine.js"></script>
        <script type="text/javascript" src="dwr/interface/catalogueService.js"></script>
        <script type="text/javascript" src="javascript/jquery.loadmask.min.js"></script>
    </head>
    <body>
         <%
	   
		Logger log=Logger.getLogger("microcatSSO.jsp");
	log.info("SamlAssertion Exist:" + (request.getParameter("samlAssertion") !=null ? "Yes": "No"));
	log.info("Company Exist:" + (request.getParameter("company") !=null ? "Yes": "No"));
log.info("Company Name:" + request.getParameter("company"));
		if(request.getParameter("samlAssertion")!=null && request.getParameter("company")!=null){
                SamlIdp idp = new SamlIdp(application);
                String samlResponse = request.getParameter("samlAssertion");
                String company = request.getParameter("company");
        %>
        <form id="sso-form" action="<%=idp.getDestinationUrl()%>" method="post">
            <input type="hidden" name="SAMLResponse" value="<%=samlResponse%>"/>
            <input type="hidden" name="relaySate" value="<%=idp.getRelayState()+ "&brand=" + company %>"/>
            <noscript><input type="submit"/></noscript>
        </form>
            <script type="text/javascript">
                console.log(jQuery("#sso-form"));
                jQuery("#sso-form").submit();
            </script>
        <% } else { %>
            <div>
                <div id="body-wrap">
                    <h1>Unauthorize</h1>
                </div>
            </div>
        <% } %>
    </body>
</html>
