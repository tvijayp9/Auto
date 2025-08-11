<%--
    Document   : main
    Created on : 29/07/2008, 14:15:00
    Author     : User
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My InBound Message List</title>
        <link href="css/reset1.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <!--[if !IE 6 ]>
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <![endif]-->
        <!--[if IE 6 ]>
        <link href="css/ie6.css" rel="stylesheet" type="text/css" media="screen" />
        <style type="text/css">img {behavior: url('css/iepngfix.htc');} </style>
        <![endif]-->
        <SCRIPT type="text/javascript" SRC="javascript/myscript.js">
        </SCRIPT>
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <script type="text/javascript" src="javascript/inBoundMessageList.js"></script>
        <link href="css/InBoundMessageList.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/basic/grid.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/jqModal.css" />
        <link href="theme/jqueryUITheme/ui.all.css" rel="stylesheet" type="text/css" />
        <script src="javascript/jqGrid/jquery.jqGrid.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqModal.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqDnR.js" type="text/javascript"></script>
        <script type="text/javascript" src="javascript/jquery-ui-personalized-1.6rc6.min.js"></script>
        <script type="text/javascript" src="javascript/jquery.timers-1.1.3.js"></script>
    </head>
    <body>
        <s:i18n name="umg_transactions">
        <div id="container">
            <div id="header">
                <jsp:include flush="true" page="..//logo.jsp"/>
                <div id="header-menu">
                    <jsp:include flush="true" page="Head.jsp"/>
                    <jsp:include flush="true" page="Sub.jsp"/>
                </div><!-- end #header-menu -->
            </div><!-- end #header -->
            <div id="body-wrap1"> 
                <!--<input type="button" id="viewNew" value="View All New"/>-->
                <div id="mysearch"></div>
                <table id="list" class="scroll" cellpadding="0" cellspacing="0"></table>
                <div id="pager" class="scroll" style="text-align:center;"></div>
                <!--
                <s:form action="ArchiveInBoundMessage" theme="simple">
                    <div id="archive"><s:submit value="Archive"/></div>
                <table id="messageList" cellspacing="0" >
                    <colgroup>
                <col/>
                <col/>
                <col/>
                <col/>
                <col/>
                <col/>
                <col id="albumCol"/>
            </colgroup>
                    <thead>
                        <tr valign="top" bgcolor="#CCCCCC">
                            <th scope="col"></th>
                            <th scope="col">Document Id</th>
                            <th scope="col">Transaction Type</th>
                            <th scope="col">From</th>
                            <th scope="col">Date Received</th>
                            <th scope="col">Download</th>
                            <th scope="col">View</th>
                        </tr>
                    </thead>
                    <tbody id="offTblBdy">
                        <s:iterator value="col2">
                            <s:set name="orderOnline" value="%{orderOnline}"/>
                            <s:set name="messageStatus" value="%{status}"/>
                            <tr <s:if test="%{#messageStatus==1}">class="rowWithColor"</s:if> cellpadding="4">
                                <td><s:checkbox cssClass="checkbox" name="documentId" fieldValue="%{docid}"/></td>
                                <td><s:property value="docid"/></td>
                                <td><s:property value="type"/></td>
                                <td><s:property value="from"/></td>
                                <td><s:property value="date"/></td>

                                    <s:if test="%{!orderOnline}">
                                        <td>
                                        <s:set name="test" value="%{download}"/>
                                        <s:if test="%{#test==1}">
                                            <s:url id="viewmessage" action="downloadFileINQ" >
                                                <s:param name="inqmessageid" value="%{id}" />
                                            </s:url>
                                            <s:a href="%{viewmessage}">Download</s:a>
                                        </s:if>
                                        </td>
                                        <td>
                                            <s:url id="viewmessage1" action="viewUploadFile" >
                                        <s:param name="inqmessageid" value="%{id}" />
                                    </s:url>
                                    <s:a href="%{viewmessage1}">View</s:a></td>
                                        </td>
                                    </s:if>
                                <s:else>
                                    <td></td>
                                <td>
                                    <s:url id="viewmessage2" action="viewOrderFromInboundTransaction">
                                        <s:param name="orderNumber" value="%{docid}" />

                                    </s:url>
                                    <s:a href="#" onclick="window.open('%{viewmessage2}','viewOrder','width=900,height=600,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=no')">View</s:a>
                                </td>
                                </s:else>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                </s:form>-->
            </div>
            <div id="footer">
                <div id="footer-content">
                    &nbsp;&copy; 2008 XML Yes &middot;
                </div>
            </div><!-- end #footer -->
        </div><!-- end div#container -->
        <div id="footer-shadow">
            <img src="images/bgFooter.gif" width="964px" height="9px" alt=""/>
        </div>
        </s:i18n>
    </body>
</html>
