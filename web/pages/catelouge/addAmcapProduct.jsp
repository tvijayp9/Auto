<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Quote</title>
        <link href="../../css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="../../css/default1.css" rel="stylesheet" type="text/css" media="screen" />
        
        <script type="text/javascript" SRC="../../javascript/myscript.js"></script>
		<script type="text/javascript" src="../../javascript/disableRightClick.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="../../javascript/jqGrid-4.7.0/css/ui.jqgrid.css" />
        <script src="../../javascript/jqGrid-4.7.0/jquery.js" type="text/javascript"></script>
       
       
        <link href="../../theme/jqueryUITheme/ui.all.css" rel="stylesheet" type="text/css" />
		<link href="../../css/jquery.loadmask.css" rel="stylesheet" type="text/css" media="screen" />
        <script src="../../javascript/jqGrid-4.7.0/js/i18n/grid.locale-en.js" type="text/javascript"></script>
        <script src="../../javascript/jqGrid-4.7.0/js/jquery.jqGrid.js" type="text/javascript"></script>
        <script src="../../javascript/jqGrid-4.7.0/js/jqModal.js" type="text/javascript"></script>
        <script src="../../javascript/jqGrid-4.7.0/js/jqDnR.js" type="text/javascript"></script>
        
       
        <script type="text/javascript" src="../../javascript/jquery.timers-1.1.3.js"></script>
        <script type="text/javascript" src="dwr/engine.js"></script>
        <script type="text/javascript" src="dwr/interface/catalogueService.js"></script>
        <script type="text/javascript" src="../../javascript/jquery.loadmask.min.js"></script>
        <script type="text/javascript" src="../../javascript/addAmcapProduct.js"></script>
    </head>
    
    <body>
        <%
            String quotename = request.getParameter("quoteName");
            session.setAttribute("quoteName",quotename);
            System.out.println("quoteName in Add Amcap Product="+quotename);
           %>
        <s:i18n name="resolution">
            <s:form name="form" id="product-form">
          
         <table width="500" border="0">
                        <tr>
                            <td>Product Item No:</td>
                            <td><s:textfield id="productcode" name="productcode" size="20" maxlength="50" required="true"/></td>
                        </tr>
                        <tr>
                            <td>Product Description:</td>
                            <td><s:textfield id="description" name="description" size="20" maxlength="50" required="true"/></td>
                        </tr>
                        
                        <tr>
                            <td>Qty:</td>
                            <td><s:textfield id="qty" name="qty" size="20" maxlength="20" required="true"/></td>
                        </tr>
                        
                        <tr>
                            <td>Price:</td>
                            <td><s:textfield id="price" name="price" size="20" maxlength="40" required="true"/></td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td>
                                 <input type="button" id="add" value="Add Product"/>
                            </td>
                            <td>
                                 <input type="button" id="close" value="close"/>
                            </td>
                        </tr>
                    </table>
        </s:form>
        <p align="center"><font color="#000080" size="4"><s:actionmessage /></font></p>
        </s:i18n>
    </body>
</html>


