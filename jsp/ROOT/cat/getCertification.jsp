<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import="java.sql.*" %>

<%
	Connection conn=null;
	String no = request.getParameter("no");
	//MySQL DBMS
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/cat";
	
	PreparedStatement stmt = null;
	
	String sql = "select * from laundry where no = ?";
	Boolean connect=false;
	ResultSet result = null;
	
	try{
		Class.forName(driver);
		conn=DriverManager.getConnection(url,"root","root");
		
		stmt =conn.prepareStatement(sql);
		stmt.setString(1,no);
		result = stmt.executeQuery();
		if(result.next()){
			out.print(result.getString("certification"));
		}
		
		conn.close();
	}catch(Exception e){
		connect=false;
		e.printStackTrace();
	}
%>	