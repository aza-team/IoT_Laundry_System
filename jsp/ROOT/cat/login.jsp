<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import="java.sql.*" %>

<%
	Connection conn=null;
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	//MySQL DBMS
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/cat";
	
	PreparedStatement stmt = null;
	
	String sql = "select * from member where id = ?";
	Boolean connect=false;
	ResultSet result = null;
	
	try{
		Class.forName(driver);
		conn=DriverManager.getConnection(url,"root","root");
		
		stmt =conn.prepareStatement(sql);
		stmt.setString(1,id);
		result = stmt.executeQuery();
		if(result.next()){
			if(pwd.equals(result.getString("password"))){
				out.print("1");
				//로그인성공			
				}
			else{
				out.print("2");
				//아이디 성공 패스워드 실패
			}
		}
		else{
			out.print("0");
			//아이디 없음
		}
		
		conn.close();
	}catch(Exception e){
		connect=false;
		e.printStackTrace();
	}
%>