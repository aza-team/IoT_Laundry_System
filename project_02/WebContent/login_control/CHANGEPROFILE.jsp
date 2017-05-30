<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="java.sql.*"%>
<%
	request.setCharacterEncoding("euc-kr");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ChangeProfile</title>
</head>
<body>

	<h1>개인정보변경</h1>
	<%
		out.println(session.getAttribute("id") + " 님 개인정보");
		try {
			PreparedStatement pstmt = null;
			Connection conn = null;

			// 드라이버 로딩
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);

			// 관리자 Login
			String url = "jdbc:mysql://localhost:3306/micon";
			String id = "root";
			String pw = "root";

			// 연결
			conn = DriverManager.getConnection(url, id, pw);

			// sql 구사
			String sql = "SELECT * FROM listadmin WHERE id='" + session.getAttribute("id") + "'";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			System.out.println(sql);

			while (rs.next()) {
	%>
	<table border="1">
		<tr>
			<th>비밀번호</th>
			<td><%=rs.getString("PASSWORD")%></td>
			<td>
				<button onClick="location.href='CHANGEPROFILE_PW.jsp'">변경</button>
			</td>
		</tr>

		<tr>
			<th>이름</th>
			<td><%=rs.getString("NAME")%></td>
			<td>
				<button onClick="location.href='CHANGEPROFILE_NAME.jsp'">
					변경</button>
			</td>
		</tr>
	</table>
	<%
		}
		} catch (Exception e) {
			out.println("DB 연동 실패");
		}
	%>

	<br />
	<br />
	<button onClick="location.href='LOGIN.jsp'">메인으로 돌아가기</button>

</body>
</html>
