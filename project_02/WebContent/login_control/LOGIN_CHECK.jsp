<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="java.sql.*"%>
<%
	request.setCharacterEncoding("euc-kr");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Login_Check</title>
</head>
<body>
	<%
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;

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
			// �� �������� LOGIN.jsp input�� �Է��� ������ ������ ��´�
			String user_id = request.getParameter("input_id");
			String user_pw = request.getParameter("input_pw");

			// Statement st = conn.createStatement();
			
			// ���� �Է��� id�� pw ���� DB�ȿ� �ִ��� Ȯ���Ѵ�
			String sql = "SELECT * FROM listadmin WHERE id='" + user_id + "' AND password='" + user_pw + "'";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			System.out.println(sql);

			// isLogin �� �α��� Ȯ�� ������ ���� ����
			Boolean isLogin = false;
			while (rs.next()) {
				// rs.next�� true ��� = ������ �ִ�
				isLogin = true;
			}

			// DB�� ���� ���� ������ �ִٸ�
			if (isLogin) {
				// ���� �α����� id�� pw�� session�� �����ϰ�
				session.setAttribute("id", user_id);
				session.setAttribute("pw", user_pw);
				// ù �������� ����������
				response.sendRedirect("/project_02/main_view.jsp");
			} else {
				// DB�� �������� ������ ���ٸ� ���â�� ����ش�
	%>
	<script>
		alert("�α��� ����");
		history.go(-1);
	</script>
	<%
		}

		} catch (Exception e) {
			out.println("DB ���� ����");
		}
	%>

</body>
</html>