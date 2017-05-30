<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="jdbc_admin.AdminVO" %>
<%@ page import="jdbc_admin.AdminDAO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
</head>
<body>
	<jsp:useBean id="admin_list" class="jdbc_admin.AdminDAO" />
	<%
	String id = request.getParameter("id_us");	
	String pw = request.getParameter("pass_us");
	boolean result = false;
	
	if(admin_list.inAdminlist(id, pw) == 0){
		result = true;
	}else 
		result = false;
	System.out.println(result);
	%>
	
<jsp:forward page="main_view.jsp"/>
</body>
</html>