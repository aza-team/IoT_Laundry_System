<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import="java.sql.*" %>

<%
	Connection conn=null;
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String phone = request.getParameter("phone");
	String email = request.getParameter("email");
	//MySQL DBMS
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/cat";
	
	PreparedStatement stmt = null;
	
	String sql = "insert into member (id,password,phone,email) values(?,?,?,?)";
	Boolean connect=false;
	int result = 0 ;
	
	try{
		Class.forName(driver);
		conn=DriverManager.getConnection(url,"root","root");
		
		stmt =conn.prepareStatement(sql);
	if(id.equals("")){
		out.print("1");//�ߺ�üũ
	}
	else if(pwd.equals("")){
		out.print("2");//��й�ȣ
	}
	else if(phone.equals("")){
		out.print("3");//��
	}
	else if(email.equals("")){
		out.print("4");//�̸���
	}
	else {
			stmt.setString(1,id);
			stmt.setString(2,pwd);
			stmt.setString(3,phone);
			stmt.setString(4,email);
			result = stmt.executeUpdate();
			out.print("ȸ�����Լ����Ͽ����ϴ�.");//ȸ�����Լ�����1
	}
		
		
		conn.close();
		
	}catch(Exception e){
		out.print("ȸ�����Խ����Ͽ����ϴ�.");//ȸ�����Խ��н�2
		connect=false;
		e.printStackTrace();
	}
%>