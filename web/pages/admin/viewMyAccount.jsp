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
        <title>Partner List</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
        <!--[if !IE 6 ]>
        <link href="css/default.css" rel="stylesheet" type="text/css" media="screen" />
        <![endif]-->
        <!--[if IE 6 ]>
        <link href="css/ie6.css" rel="stylesheet" type="text/css" media="screen" />
        <style type="text/css">img {behavior: url('css/iepngfix.htc');} </style>
        <![endif]-->
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <SCRIPT LANGUAGE=javascript>
            function checkEmail(email) {
                if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
                    return (true)
                }
                alert("Invalid E-mail Address! Please re-enter.")
                return (false);
            }
            function validateForm(formObj){

                if(formObj.userid.value.length==0){
                    alert("Please enter User ID!");
                    formObj.userid.focus();
                    return false;
                }

                if(formObj.password.value.length==0){
                    alert("Please enter password!");
                    formObj.password.focus();
                    return false;
                }

                if(formObj.company.value.length==0){
                    alert("Please enter company Name!");
                    formObj.company.focus();
                    return false;
                }
                if(formObj.contact.value.length==0){
                    alert("Please enter Contact Name!");
                    formObj.contact.focus();
                    return false;
                }
                if(formObj.email.value.length==0){
                    alert("Please enter Email!");
                    formObj.email.focus();
                    return false;
                }

                if(!checkEmail(formObj.email.value)){
                    formObj.email.focus();
                    return false;
                }

                if(formObj.phno1.value.length==0){
                    alert("Please enter Phone No.!");
                    formObj.phno1.focus();
                    return false;
                }
                if(formObj.address.value.length==0){
                    alert("Please enter address!");
                    formObj.address.focus();
                    return false;
                }
                if(formObj.city.value.length==0){
                    alert("Please enter city!");
                    formObj.city.focus();
                    return false;
                }
                if(formObj.state.value.length==0){
                    alert("Please enter state!");
                    formObj.state.focus();
                    return false;
                }
                if(formObj.postcode.value.length==0){
                    alert("Please enter postcode!");
                    formObj.postcode.focus();
                    return false;
                }
                if(formObj.country.value==0){
                    alert("Please Select Country!");
                    formObj.country.focus();
                    return false;
                }
                return true;
            }
            //-->
        </SCRIPT>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include flush="true" page="..//logo.jsp"/>
                <div id="header-menu">
                    <jsp:include flush="true" page="Head.jsp"/>
                    <jsp:include flush="true" page="Sub.jsp"/>
                </div><!-- end #header-menu -->
            </div><!-- end #header -->
            <div id="body-wrap1">
                <center>
                <s:actionmessage/>
                <s:form action="UpdateMyAccount" method="POST" validate="true" onsubmit="return validateForm(this)">
                    <s:hidden name="id" />
                    <s:hidden name="nexusId" />
                    <s:hidden name="action"/>
                    <s:hidden name="actionUpdateData"/>
                    <s:hidden name="priceType"/>
                    <s:hidden name="industryName"/>
                    <table width="90%" border="1" class="signup"  align="center" >
                        <tr>
                            <td colspan="2" >
                                <font size="4" color="#660099">Please fill in the Following Details</font><br>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                            <font color="red"></font>&nbsp;</td>
                        </tr>
                        <s:textfield name="userid" size="35" label="UserId" readonly="true"/>
                        <s:password name="password" size="35" label="Password"/>
                        <s:textfield name="company" size="35" label="Company Name"/>
                        <s:textfield name="contact" size="35" label="Contact"/>
                        <s:textfield name="company_url" size="35" label="Company Website"/>
                        <s:textfield name="email" size="35" label="Email"/>
                        <tr>
                            <td>Phone No.(Country - Area - Number)</td>
                            <td><s:textfield name="phno1" size="20"  theme="simple"/></td></tr>
                        <tr>
                            <td>Fax No.(Country - Area - Number) </td>
                            <td><s:textfield name="fxno1" size="20"  theme="simple"/></td></tr>

                        <s:textfield name="address" size="35" label="Address1"/>
                        <s:textfield name="address2" size="35" label="Address2"/>
                        <s:textfield name="city" size="35"label="City"/>
                        <s:textfield name="state" size="35" label="State/Province/Territory"/>
                        <s:textfield name="postcode" size="35" label="Postcode/Zipcode"/>
                        <s:select label="Country"
                                  name="country"
                                  headerKey="0"
                                  headerValue="-- Please Select --"
                                  list="countries"
                                  listKey="id"
                                  listValue="name"/>
                        <tr><td></td><td><br/><s:submit value="Update" theme="simple"></s:submit> </td></tr>
                    </table>
                </s:form>
            </div>
            <div id="footer">
                <div id="footer-content">
                    &nbsp;&copy; 2009 XML Yes &middot;
                </div>
            </div><!-- end #footer -->
        </div><!-- end div#container -->
        <div id="footer-shadow">
            <img src="images/bgFooter.gif" width="964px" height="9px" alt=""/>
        </div>
    </body>
</html>