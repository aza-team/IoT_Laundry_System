<%@ page language = "java" contentType = "text/html; charset=EUC-KR"%>
<%@ page import = "java.sql.*" %>

<%
	Connection conn = null;
	String no = request.getParameter("no");
	String certification = request.getParameter("certification");
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cat";
	
	
	PreparedStatement stmt = null;
	
	String sql = "update laundry set certification = ? where no = ?";
	try{
		Class.forName(driver);
		conn=DriverManager.getConnection(url,"root","root");
		
		stmt = conn.prepareStatement(sql);
		stmt.setString(1,certification);
		stmt.setString(2,no);
		stmt.executeUpdate(); // 0 이면 인증 x 1 이면 인증 o
	}catch(Exception e){
		e.printStackTrace();
	}
%>