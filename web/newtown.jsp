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
        
       <div id="container">
            <div id="header">
                
            </div><br/><br/>
            <div id="main">
                <s:actionerror/>
                <div id="level1">
                    <div id="leftlevel">
                        <h4>Log On</h4>
                        <s:form action="login" method="post">
                            <s:hidden name="errorlogin" value="newtown" />
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
                        <p><a href="<s:url action="ntforgetpassword.action"/>">Forgotten password?</a></p>
                        <p><a href="http://www.newtowntoyota.com.au">Home/Site Map</a></p>
                        <p>Contact Us: 1800 016 646</p>
                        <p class="secondPhoneNumber">+61 8 9472 2600</p>
                    </div>
                </div>
                <div id="level2">
                    <h4>Still The State's Best Online Seller</h4>
                    <img src="images/homepage/toyotaservice.jpg" /><img src="images/homepage/ToyotaGenuineParts.gif" />
                </div>
                <div id="level3">
                    <h4>Preferred Supplier To</h4>
                    <img src="images/homepage/PrefSupplier_30mmx18mm_mono.jpg" /><img src="images/homepage/CC logo only hi res.jpg" /><img src="images/homepage/quadrem.jpg" />
                </div>
            </div>
            <div id="footer">
                © New Town Toyota  ABN 51 057 584 595  Welshpool Road (Cnr Swansea), East Victoria Park  Perth Western Australia  6101
            </div>
        </div>
    </body>
</html>