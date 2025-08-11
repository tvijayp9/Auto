<html>
<head>
<title>Content Frame</title>
</head>
<body bgcolor="#FFFFFF">
<div align="center"> 
  <p>&nbsp;</p>
  <form action="Trans_Disp_Content.jsp" method="get" name="form1" target="_self">
  <input type="hidden" name="buttonClicked" value="">
  <input type="hidden" name="fileName" value="<%=request.getParameter("fileName")%>">
  </form>
  <p>&nbsp;</p>
  <p><img src="../../images/Trans_Disp/dartBoard.jpg" width="140" height="95"></p>
  <p><strong><font face="Courier New, Courier, mono">Transaction Resolution Technology</font></strong></p>
  <table width="70%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><div align="center"><font face="Courier New, Courier, mono">The tree 
          structure on the left represents the transaction you are modifying. 
          Click the nodes to expand and collapse elements, you can modify and 
          make changes to elements once you have clicked on a node. </font></div></td>
    </tr>
  </table>
  <p><strong><font face="Courier New, Courier, mono"></font></strong></p>
  <p>&nbsp;</p>
</div>
</body>
</html>
