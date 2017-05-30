<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="jdbc_waCount.WaVO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="washing" class="jdbc_waCount.WashingDAO" />
<%
	ArrayList<WaVO> wa_list = washing.getWAlist();
	int wm_count = Integer.parseInt(wa_list.get(0).getWaCount().toString());	// 세탁기 갯수.
	
	String[] wa_arr = new String[wm_count];
	for(int loop=0; loop < wm_count; loop++){
			wa_arr[loop] = request.getParameter("switch_"+loop);
	}
/* 	
	String radio_0 = request.getParameter("switch_0");	
	String radio_1 = request.getParameter("switch_1");
	String radio_2 = request.getParameter("switch_2"); ``
	String[] arr = {radio_0, radio_1, radio_2};
	
*/
	Connection conn = null;
	PreparedStatement pstmt = null;
	try{
		String url = "jdbc:mysql://localhost:3306/micon?autoReconnect=true&useSSL=false";
		String id = "root";
		String pw = "root";
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, id, pw);
		
		for(int loop=0; loop < wa_arr.length; loop++){
			String sql = "UPDATE checkbox set setCheck=? where machineNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, wa_arr[loop]);
			pstmt.setInt(2, loop);
			pstmt.executeUpdate();
			System.out.println(loop);
		}
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
<jsp:forward page="laundry_view.jsp"/>
</body>
</html>