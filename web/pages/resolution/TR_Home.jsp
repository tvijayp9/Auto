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
        <title>Member List</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css" media="all" />
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
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/basic/grid.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="javascript/jqGrid/themes/jqModal.css" />
        <script src="javascript/jquery-1.3.min.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/jquery.jqGrid.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqModal.js" type="text/javascript"></script>
        <script src="javascript/jqGrid/js/jqDnR.js" type="text/javascript"></script>
        <script src="javascript/memberlist.js" type="text/javascript"></script>
        <script language="JavaScript1.2">

            document.onkeydown = function ()
            {
                var kcode = event.keyCode;

                if(kcode == 13)
                {
                    if(form1.userName.value == "")
                    {
                        alert("Please Enter a User Name.");
                        return false;
                    }
                    if(form1.userPass.value == "")
                    {
                        alert("Please Enter a Password.");
                        return false;
                    }

                    form1.submit();
                    event.keyCode = 0;
                    event.returnValue = false;
                    event.cancelBubble = true;
                    return false;
                }
            }


            function sendForm()
            {
                var send = true;
                if(form1.userName.value == "")
                {
                    send = false;
                }
                if(form1.userPass.value == "")
                {
                    send = false;
                }

                if(send)
                {
                    form1.submit();
                }
                else
                {
                    alert("Please Enter a Valid User Name and Password.");
                }
            }
        </script>

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
                <!--<div id="mysearch"></div>
                <table id="list" class="scroll" cellpadding="0" cellspacing="0"></table>
                <div id="pager" class="scroll" style="text-align:center;"></div>-->
                <form name="form1" method="post" action="pages/resolution/Transaction_Centre.jsp">
                    <TABLE WIDTH=470 BORDER=0 align="center" CELLPADDING=0 CELLSPACING=0 bgcolor="#CCCCCC">
                        <!--<TR>
      <TD width="35"> <IMG SRC="images/logo/Logo_03.jpg" WIDTH=35 HEIGHT=35 ALT=""></TD>
      <TD colspan="2"> <IMG SRC="images/logo/Logo_04.jpg" WIDTH=400 HEIGHT=35 ALT=""></TD>
      <TD width="35"> <IMG SRC="images/logo/Logo_05.jpg" WIDTH=35 HEIGHT=35 ALT=""></TD>
                        </TR>-->
                        <TR>
                            <TD height="300" rowspan="7" background="images/logo/Logo_07.jpg">&nbsp; </TD>
                            <TD colspan="2" bgcolor="#CCCCCC"> <p><IMG SRC="images/logo/Logo_08.jpg" WIDTH=400 HEIGHT=300 ALT=""></p></TD>
                            <TD rowspan="7" background="images/logo/Logo_09.jpg">&nbsp; </TD>
                        </TR>
                        <!--    <TR>
      <TD colspan="2" bgcolor="#CCCCCC">&nbsp;</TD>
                        </TR>-->
                        <TR>
                            <TD width="160" height="19" bgcolor="#CCCCCC">
                                <div align="right"><strong><font size="-1" face="Courier New, Courier, mono">User
                            Name: </font></strong></div></TD>
                            <TD width="240" bgcolor="#CCCCCC">
                            <input name="userName" type="text" id="userName"></TD>
                        </TR>
                        <TR>
                            <TD height="19" bgcolor="#CCCCCC">
                                <div align="right"><strong><font size="-1" face="Courier New, Courier, mono">Password:
                            </font></strong></div></TD>
                            <TD bgcolor="#CCCCCC">
                            <input name="userPass" type="password" id="userPass"></TD>
                        </TR>
                        <!--<TR>
      <TD height="10" colspan="2" bgcolor="#CCCCCC">&nbsp;</TD>
    </TR>
    <TR>
      <TD height="10" colspan="2" bgcolor="#CCCCCC">&nbsp;</TD>
                        </TR>-->
                        <TR>
                            <TD colspan="2" bgcolor="#CCCCCC">
                            <div align="center"><img src="images/Buttons/buttonLogin.jpg" width="118" height="23" onClick="sendForm()"></div></TD>
                        </TR>
                        <!--<TR>
      <TD> <IMG SRC="images/logo/Logo_10.jpg" WIDTH=35 HEIGHT=35 ALT=""></TD>
      <TD colspan="2"> <IMG SRC="images/logo/Logo_11.jpg" WIDTH=400 HEIGHT=35 ALT=""></TD>
      <TD> <IMG SRC="images/logo/Logo_12.jpg" WIDTH=35 HEIGHT=35 ALT=""></TD>
                        </TR>-->
                    </TABLE>
                </form>
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
