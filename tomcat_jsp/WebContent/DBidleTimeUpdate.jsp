<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title></title>
</head>
<body>
	<%
		String radioValue = request.getParameter("accessible-radio");
		String result = radioValue;	// 라디오 버튼 결과값 
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			String url = "jdbc:mysql://localhost:3306/micon";
			String id = "root";
			String pw = "root";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "UPDATE test set setTime=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, radioValue);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			out.println("error");
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {}
		}
	%>
	<jsp:forward page="idleTime.jsp">
		<jsp:param name="result" value="<%=result%>"/>
	</jsp:forward>
</body>
</html>