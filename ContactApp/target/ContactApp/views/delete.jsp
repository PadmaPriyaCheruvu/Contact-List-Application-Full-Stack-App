<%@page import="java.sql.*"%>
<%@page import="java.sql.*"%>
<%
	String recordToDelete = request.getParameter("id");
	String sql1 = "delete * from Phone where contact_id="+recordToDelete;
	String sql2 = "delete * from DateTable where contact_id="+recordToDelete;
	String sql3 = "delete * from Address where contact_id="+recordToDelete;
	String sql4 = "delete * from Contact where contact_id="+recordToDelete;
    

    // Use PreparedStatements here instead of Statment
    try {Class.forName("com.mysql.jdbc.Driver");} catch (ClassNotFoundException e) {	e.printStackTrace();}
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys?characterEncoding=latin1","root","Padma@3296");
        PreparedStatement stmt=con.prepareStatement(sql1);  
        ResultSet result=stmt.executeQuery();
        stmt=con.prepareStatement(sql2);  
        result=stmt.executeQuery();
        stmt=con.prepareStatement(sql2);  
        result=stmt.executeQuery();
        stmt=con.prepareStatement(sql2);  
        result=stmt.executeQuery();        

     response.sendRedirect("home.jsp"); // redirect to JSP one, which will again reload.
%>