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
        <title>Create Product</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <link href="css/pricelist.css" rel="stylesheet" type="text/css"/>
        <!--[if !IE 6 ]>
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <![endif]-->
        <!--[if IE 6 ]>
        <link href="css/ie6.css" rel="stylesheet" type="text/css" media="screen" />
        <style type="text/css">img {behavior: url('css/iepngfix.htc');} </style>
        <![endif]-->
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <script type="text/javascript" src="javascript/Calender.js"></script>
        <link href="theme/jqueryUITheme/ui.all.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="javascript/jquery-ui-personalized-1.6rc6.min.js"></script>
        <link href="javascript/jqGrid/themes/basic/grid.css" rel="stylesheet" type="text/css" media="screen"  />
        <link href="javascript/jqGrid/themes/jqModal.css" rel="stylesheet" type="text/css" media="screen"  />
        <script type="text/javascript" src="javascript/createproduct.js"></script>
        <script src="javascript/jqGrid/jquery.jqGrid.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqModal.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqDnR.js" type="text/javascript"></script>

    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include flush="true" page="..//logo.jsp"/>
                <div id="header-menu">
                    <jsp:include flush="true" page="Head1.jsp"/>
                    <jsp:include flush="true" page="Sub1.jsp"/>
                </div><!-- end #header-menu -->
            </div><!-- end #header -->
            <div id="body-wrap1">
                <!--<table><tr>
                        <td><s:form action="CreateProduct">
                                <s:submit value="Create"/>
                            </s:form>
                        </td>
                        <td><s:form theme="simple">
                            <input id="productid" name="productid" type="hidden" value="" />
                        <input type="button" value="Edit"/></s:form></td>
                        <td><input type="button" value="Delete"/></td>
                </tr></table>-->
                <jsp:include flush="true" page="ProductsManagement.jsp"/>
                <table id="list" class="scroll" cellpadding="0" cellspacing="0"></table>
                <div id="pager" class="scroll" style="text-align:center;"></div>
                <h5>* Indicates Mandetory Fields.</h5>
                <s:form id="createProduct">
                    <input class="save" type="button" value="Save" />
                    <tr>
                        <td colspan="2">
                            <s:actionerror />
                        </td>
                    </tr>
                    <s:textfield  id="pid" label="* ProductID" name="pid" value="%{pid}" maxlength="20"></s:textfield>
                    <s:textfield  label="GTIN" name="gtin" maxlength="20"></s:textfield>
                    <s:textfield  id="pname" label="* Product_Name" name="pname" maxlength="100"></s:textfield>
                    <s:textfield  id="uom" label="* UOM" name="uom" maxlength="50"></s:textfield>
                    <s:textfield  label="Price"   name="price" maxlength="10"></s:textfield>
                    <s:textfield  label="BaseProductNumber"  name="bpNumber" maxlength="20"></s:textfield>
                    <s:textfield  label="ProductIDExtension"  name="piExt" maxlength="20"></s:textfield>
                    <s:textfield  label="ComparableUOM"  name="compUOM" maxlength="20"></s:textfield>
                    <s:textfield  label="ComparableUOMConversionFactor"  name="compUOMConFact" maxlength="20"></s:textfield>
                    <s:textfield  label="Manufacturer"  name="manufacturer" maxlength="20"></s:textfield>
                    <s:textfield  label="ManuPartNumber"  name="manuPartNumber" maxlength="20"></s:textfield>
                    <s:textfield  label="LeadTime"  name="leadTime" maxlength="20"></s:textfield>
                    <s:textfield  label="LeadTimeUOM"  name="leadTimeUOM" maxlength="20"></s:textfield>
                    <s:textfield  label="ValidFrom" id="ValidFrom" name ="ValidFrom"  size="10" value="%{ValidFrom}" readonly="true"></s:textfield>
                    <s:textfield  label="ValidUntil" id="ValidUntil" name ="ValidUntil"  size="10" value="%{ValidUntil}" readonly="true"></s:textfield>
                    <s:textfield  label="CountryOfOrigin"  name="country" maxlength="20"></s:textfield>
                    <s:textfield  label="MinOrder"  name="minOrder" maxlength="20"></s:textfield>
                    <s:textfield  label="LotSize"  name="lotSize" maxlength="20"></s:textfield>
                    <s:textfield  label="ShortDescription"  name="shortDesc" maxlength="20"></s:textfield>
                    <s:textfield  label="LongDescription"  name="longDesc" maxlength="20"></s:textfield>
                    <s:textfield  label="LongDescriptionPurpose"  name="longDescPurpose" maxlength="20"></s:textfield>
                    <s:textfield  label="CatalogContractID"  name="catContractID" maxlength="20"></s:textfield>
                    <s:textfield  label="CatalogContractItemID"  name="CataContractItemID" maxlength="20"></s:textfield>
                    <s:textfield  label="AttachmentURL"  name="url" maxlength="20"></s:textfield>
                    <s:textfield  label="AttachmentPurpose"  name="attachmentPurpose" maxlength="20"></s:textfield>
                    <s:textfield  label="AttachmentMIMEType"  name="mimeType" maxlength="20"></s:textfield>
                    <s:textfield  label="ProductAttachment ShortDescription"  name="pashortDesc" maxlength="20"></s:textfield>
                    <s:textfield  label="ProductAttachment LongDescription"  name="palongDesc" maxlength="20"></s:textfield>
                    <s:textfield  label="ProductAttachment LongDescriptionPurpose"  name="palongDescPurpose" maxlength="20"></s:textfield>
                    <s:textfield  label="RelatedProduct"  name="relProduct" maxlength="20"></s:textfield>
                    <s:textfield  label="AttributeID"  name="attrID" maxlength="20"></s:textfield>
                    <s:textfield  label="AttributeUnit"  name="attrUnit" maxlength="20"></s:textfield>
                    <s:textfield  label="AttributeValue"  name="attrValue" maxlength="20"></s:textfield> 
                </s:form>
                <input class="save" type="button" value="Save" />
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
    </body>
</html>
