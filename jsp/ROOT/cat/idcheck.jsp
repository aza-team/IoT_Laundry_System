<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import="java.sql.*" %>

<%
	Connection conn=null;
	String id = request.getParameter("id");
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
		if(id.equals("")){
			//아이디 입력안함
			out.print("0");
		}
		else if(result.next()){
			if(id.equals(result.getString("id"))){
				//사용중인 아이디
					out.print("1");
				}
		}
		else{
			out.print("2");

		//사용가능한아이디
		}
		
		conn.close();
	}catch(Exception e){
		connect=false;
		e.printStackTrace();
	}
%>