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
<style>
/* Center the form properly */
#product-form {
    width: 550px;
    margin: 40px auto;
}

/* Remove inherited weird alignment */
.product-container {
    width: 100%;
}

/* Row layout */
.form-row {
    display: flex;
    align-items: center;
    margin-bottom: 18px;
}

/* Label styling */
.form-row label {
    width: 180px;              /* smaller label column */
    text-align: right;
    padding-right: 20px;
    font-weight: normal;
}

/* Force consistent input width */
.form-row input {
    width: 250px;
    padding: 6px;
    box-sizing: border-box;
}

/* Buttons */
.form-row.buttons {
    margin-left: 200px;
}

.form-row.buttons input {
    width: auto;
    padding: 6px 14px;
    margin-right: 10px;
}
</style>
    </head>
    
    <body>
        <%
            String quotename = request.getParameter("quoteName");
            session.setAttribute("quoteName",quotename);
	    String comment = request.getParameter("comment");
            session.setAttribute("comment",comment);
            System.out.println("quoteName in Add Amcap Product="+quotename);
            System.out.println("comment in Add Amcap Product="+comment);
           %>
        <s:i18n name="resolution">
            <s:form name="form" id="product-form" theme="simple">
          
         <div class="product-container">

    <div class="form-row">
        <label>Product Item No:</label>
        <s:textfield id="productcode" name="productcode" required="true"/>
    </div>

    <div class="form-row">
        <label>Product Description:</label>
        <s:textfield id="description" name="description" required="true"/>
    </div>

    <div class="form-row">
        <label>Qty:</label>
        <s:textfield id="qty" name="qty" required="true"/>
    </div>

    <div class="form-row">
        <label>Price:</label>
        <s:textfield id="price" name="price" required="true"/>
    </div>

    <div class="form-row">
        <label>Lead Time(from date of order):</label>
        <s:textfield id="leadtime" name="leadtime" required="true"/>
    </div>

    <div class="form-row buttons">
                                 <input type="button" id="add" value="Add Product"/>
        <input type="button" id="close" value="Close"/>
    </div>

</div>
        </s:form>
        <p align="center"><font color="#000080" size="4"><s:actionmessage /></font></p>
        </s:i18n>
    </body>
</html>


