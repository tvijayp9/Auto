<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title><s:text name="Nexus Online B2B"/></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    </head>
    <body>
        <div id="container">
            <jsp:include flush="true" page="/pages/home_logo.jsp"/>
            <div id="body-wrap2">
                <s:i18n name="homePage">
                                        <TABLE width="464" border=0 cellPadding=2 cellSpacing=1>
                          <TBODY>
                            <TR>
                                <TD><P align=left><FONT size=2><B><s:text name="contactUs.localTel"/></B></FONT> </P></TD>

                              <TD></TD>
                              <TD><div align="left"><FONT size=2>1300 044 081</FONT></div></TD>
                            </TR>
                            <TR>
                              <TD>                                <P align=left><FONT size=2 face="Arial,Helvetica,Geneva,Swiss,SunSans-Regular"><B><s:text name="contactUs.internationalTel"/></B></FONT> </P></TD>
                              <TD></TD>
                              <TD><div align="left"><FONT size=2>+612 9420 5678</FONT></div></TD>

                            </TR>
                            <TR>
                              <TD><P align="left"><FONT size=2><B><s:text name="contactUs.sales"/></B></FONT></P></TD>
                              <TD>&nbsp;</TD>
                              <TD><div align="left">
							  xmlyessales@xmlyes.com
							  </div></TD>
                            </TR>
                            <TR>
                              <TD><P align="left"><FONT size=2><B><s:text name="contactUs.support"/></B></FONT></P></TD>
                              <TD></TD>
                               <TD><div align="left">
							 xmlyestech@xmlyes.com
							  </div></TD>
                            </TR>
                            <TR>
                                <TD><P align="left"><FONT size=2><B><s:text name="contactUs.account"/></B></FONT></P></TD>
                              <TD></TD>

                                  <TD><div align="left">
							 xmlyesadmin@xmlyes.com
							  </div></TD>
                            </TR>
                            <TR>
                              <TD><div align="left"><FONT face=Arial,Helvetica,Geneva,Swiss,SunSans-Regular
size=2><B><s:text name="contactUs.partner"/></B></FONT></div></TD>

                              <TD></TD>
                              <TD><div align="left">
							  xmlyespartner@xmlyes.com
							  </div></TD>
                            </TR>
                          </TBODY>
                      </TABLE>
</s:i18n>
            </div>
            <div id="footer">
                <div id="footer-content">
                    &nbsp;&copy; 2008 XML Yes &middot; 
                </div>
            </div>
        </div>
        <div id="footer-shadow">
           <img src="images/bgFooter.gif" width="964px" height="12px" alt=""/>
        </div>
    </body>
</html>