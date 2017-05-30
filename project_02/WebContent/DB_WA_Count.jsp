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
		String wa_count = request.getParameter("washingCount");
		String result = wa_count;	// 세탁기 개수 저장 
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			String url = "jdbc:mysql://localhost:3306/micon";
			String id = "root";
			String pw = "root";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "UPDATE test set WACount=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, result);
			pstmt.executeUpdate();
			
			String sql2 = "INSERT INTO test VALUES()";
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
	<jsp:forward page="laundry_view.jsp">
		<jsp:param name="result" value="<%=result%>"/>
	</jsp:forward>
</body>
</html>