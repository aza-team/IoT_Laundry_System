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

	<h1>������������</h1>
	<%
		out.println(session.getAttribute("id") + " �� ��������");
		try {
			PreparedStatement pstmt = null;
			Connection conn = null;

			// ����̹� �ε�
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);

			// ������ Login
			String url = "jdbc:mysql://localhost:3306/micon";
			String id = "root";
			String pw = "root";

			// ����
			conn = DriverManager.getConnection(url, id, pw);

			// sql ����
			String sql = "SELECT * FROM listadmin WHERE id='" + session.getAttribute("id") + "'";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			System.out.println(sql);

			while (rs.next()) {
	%>
	<table border="1">
		<tr>
			<th>��й�ȣ</th>
			<td><%=rs.getString("PASSWORD")%></td>
			<td>
				<button onClick="location.href='CHANGEPROFILE_PW.jsp'">����</button>
			</td>
		</tr>

		<tr>
			<th>�̸�</th>
			<td><%=rs.getString("NAME")%></td>
			<td>
				<button onClick="location.href='CHANGEPROFILE_NAME.jsp'">
					����</button>
			</td>
		</tr>
	</table>
	<%
		}
		} catch (Exception e) {
			out.println("DB ���� ����");
		}
	%>

	<br />
	<br />
	<button onClick="location.href='LOGIN.jsp'">�������� ���ư���</button>

</body>
</html>
