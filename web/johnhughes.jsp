<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link href="css/newtown.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="javascript/jquery-1.3.min.js"></script>
        <script type="text/javascript">
            $(function(){
                $(":input:first").focus();
            });
        </script>
    </head>

    <body>
        <br/><br/>
       <div id="container">
            <div id="header">
                <img src="images/homepage/JohnHughes.jpg" />
            </div><br/><br/><br/><br/><br/>
            <div id="main">
                <s:actionerror/>
                <div id="level1">
                    <div id="leftlevel">
                        <h4>Log On</h4>
                        <s:form action="login" method="post">
                            <s:hidden name="errorlogin" value="prosser" />
                            <table width="200" border="0">
                                <tr>
                                    <td>User ID:</td>
                                    <td><input name="username" type="text"/></td>
                                </tr>
                                <tr>
                                    <td>Password:</td>
                                    <td><input name="pass" type="password"/></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td><input type="submit" value="Sign In"></td>
                                </tr>
                            </table>
                        </s:form>
                    </div>
                    <div id="rightlevel">
                        <h4>Need Help?</h4>
                        <p><a href="<s:url action="ptforgetpassword.action"/>">Forgotten password?</a></p>
                        <p><a href="http://www.johnhughes.com.au/">Home/Site Map</a></p>
                        <p>Contact Us: (08) 9415 0450</p>
                        <p class="secondPhoneNumber">(08) 9415 0026</p>
                    </div>
                </div>
                
            </div> <br/><br/>
            <div id="footer">
                Copyright 2013 © John Hughes. D/L 6061. All Rights Reserved | ABN: 30 008 905 477
				

            </div>
        </div>
    </body>
</html>