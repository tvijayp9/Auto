<%@page import="com.nexus.util.DR.edxbable.EDXBable, com.nexus.util.DR.misc.*, java.util.*, com.nexus.util.DR.traderoute.Trans_Disp_Bean" %>
<jsp:useBean id="tManager" scope="session" class="com.nexus.util.DR.traderoute.Transaction_Manager"/>
<html>
<head>
<title>Change Order Acceptance</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<script language="JavaScript">

function closeDoc(done)
{
	window.returnValue = done;
	window.close();
}

</script>


<body>
<form name="form1" method="get" action="">
  <table width="100%" border="0" cellpadding="0" cellspacing="1">
    <tr bgcolor="#3399CC"> 
      <td colspan="4"><div align="center"><font color="#3399CC">|</font></div></td>
    </tr>
    <tr> 
      <td width="5%" rowspan="16">&nbsp; </td>
      <td width="90%" colspan="2">&nbsp;</td>
      <td width="5%" rowspan="16">&nbsp; </td>
    </tr>
    <tr> 
      <td colspan="2"><div align="center"><strong>IMPORTANT</strong></div></td>
    </tr>
    <tr> 
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr> 
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr> 
      <td colspan="2"><div align="center">All <font color="#FF0000"><strong>Change 
          Orders</strong></font> must be entered into your host system manually. 
        </div></td>
    </tr>
    <tr> 
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr> 
      <td colspan="2"><div align="center">If you have allready done this you can 
          click the continue button below.</div></td>
    </tr>
    <tr> 
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr> 
      <td height="24" colspan="2"><div align="center"> 
          <p>Otherwise you can close this window and print the Change Order <br>
            using the &quot;Print Transaction&quot; button.</p>
          </div></td>
    </tr>
    <tr> 
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr> 
      <td colspan="2"><div align="center"> 
          <p><strong>Note</strong>: Once you click continue the Change Order will 
            be processed and<br>
            you will not be able to make any more changes to it.</p>
        </div></td>
    </tr>
    <tr> 
      <td height="19" colspan="2">&nbsp;</td>
    </tr>
    <tr> 
      <td height="19" colspan="2">&nbsp;</td>
    </tr>
    <tr> 
      <td height="19"><div align="right"><img src="../../images/Buttons/buttonClose.jpg" width="128" height="41"  onClick="closeDoc('false');"></div></td>
      <td><img src="../../images/Buttons/buttonContinue.jpg"  onClick="closeDoc('true');"></td>
    </tr>
    <tr> 
      <td height="19" colspan="2">&nbsp;</td>
    </tr>
    <tr> 
      <td height="19" colspan="2">&nbsp;</td>
    </tr>
    <tr bgcolor="#3399CC"> 
      <td colspan="4"><div align="center"><font color="#3399CC">|</font></div></td>
    </tr>
  </table>
</form>
<p>&nbsp;</p>
</body>
</html>
