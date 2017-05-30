package jdbc_admin;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.*;

import jdbc_member.MemberVO;

public class AdminDAO {
	Connection con = null;
	Statement stmt;
	PreparedStatement pstmt = null;
	ResultSet rs;
	
	// MySql
	String diverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/micon?autoReconnect=true&useSSL=false";

	private String userName = "root";
	private String userPwd = "root";

	public AdminDAO() {
		try {
			Class.forName(diverName);
			con = (Connection) DriverManager.getConnection(url, userName, userPwd);
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> load fail");
		} catch (SQLException e) {
			System.out.println(e + "=> conet fail");
		}
	}

	public void db_close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println(e + "=> close fial");
		}
	}

	/*
 		
	 */
	public int inAdminlist(String id, String pw) {
		try {
			String sql = "select * from listadmin where id =? and password=?";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery(); 
			
			if(rs.next())
				return 0;
			else 
				return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	/*
	 	member���̺��� ID�� �ش��ϴ� ���ڵ� ����
	 */

	public int delMemberlist(String id) {
		int result = 0;
		try {// ����

			pstmt = (PreparedStatement) con.prepareStatement("delete from member_list where id = ?");
			// ?������ŭ �� ����
			pstmt.setString(1, id.trim());
			result = pstmt.executeUpdate(); // ������������ ������ ���ڵ� �� ��ȯ

		} catch (Exception e) {
			System.out.println(e + "=> delMemberlist fail");
		} finally {
			db_close();
		}

		return result;
	}
}
