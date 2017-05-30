<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		String radioValue = request.getParameter("check");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String getChecks = null;
		String checksResult = null;
		int i=1;
		
		try{
			String url = "jdbc:mysql://localhost:3306/micon";
			String id = "root";
			String pw = "root";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "SELECT setCheck FROM checkbox";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checksResult = rs.getString("setCheck");
				getChecks = checksResult;
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
	<jsp:include page="laundry_power.jsp" flush="false">
		<jsp:param name="getCheckData" value="<%=getChecks%>"/>
	</jsp:include>
</body>
</html>