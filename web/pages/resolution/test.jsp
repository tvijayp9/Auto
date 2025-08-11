<%@page language="java" import="java.sql.*" %> 

<% Driver DriverRecordset1 = 

(Driver)Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

Connection Conn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.45:1433/TradeRoute;tds=8.0;lastupdatecount=true", "admin", "in");

Statement StatementRecordset1 = Conn.createStatement();
ResultSet Recordset1 = StatementRecordset1.executeQuery("select count(*) from Transactions"); 
%> 
<table>
<tr>
<% while(Recordset1.next())

{

%> 
          <td>Total Count = </td>

		            <td><%= (Recordset1.getString (1))%></td>
        </tr>
<% } %>
</table>
<%
Recordset1 = null;
Recordset1 = StatementRecordset1.executeQuery("select * from Transactions order by PartnerName");
%> 
<table>
<tr> 

          <td>Transaction No</td>
		            <td>Partner ID</td>
					          <td>Partner Name</td>
							            <td>ProcessDate</td>

        </tr>
<% while(Recordset1.next())

{

%> 

        <tr> 
		 <td><%= (String)(Recordset1.getString (8)) %></td>
          <td><%= (String)(Recordset1.getString (3)) %></td>
		  <td><%= (String)(Recordset1.getString (2)) %></td>
		  <td><%= (String)(Recordset1.getString (14)) %></td>

        </tr>

        
<% } %>
</table>

      <%

Recordset1.close();

Conn.close();

%>

