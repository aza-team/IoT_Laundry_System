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
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String getCount = null;
		try{
			String url = "jdbc:mysql://localhost:3306/micon?autoReconnect=true&useSSL=false";
			String id = "root";
			String pw = "root";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "SELECT * FROM TEST";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String wa_result = rs.getString("WACount");
				getCount = wa_result;
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			out.println("error");
		} finally {
			if(rs != null) 
				try{rs.close();}catch(SQLException sqle){}
			if(pstmt != null) 
				try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) 
				try{conn.close();}catch(SQLException sqle){}
		}
	%>
	<jsp:include page="washingCount.jsp" flush="false">
		<jsp:param name="getCount" value="<%=getCount%>"/>
	</jsp:include>
</body>
</html>