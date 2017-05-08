package jdbc_member;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.*;

import jdbc_member.MemberVO;

public class MemberDAO {
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	// MySql
	String diverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/micon";

	private String userName = "root";
	private String userPwd = "root";

	public MemberDAO() {
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

	public ArrayList<MemberVO> getMemberlist() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();

		try {
			stmt = (Statement) con.createStatement();
			rs = stmt.executeQuery("select * from member_list");

			while (rs.next()) {
				MemberVO vo = new MemberVO();

				vo.setId(rs.getString(1));
				vo.setPwd(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setPhone(rs.getString(4));
				vo.setEmail(rs.getString(5));

				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println(e + "=> getMemberlist fail");
		} finally {
			db_close();
		}
		return list;
	}

	/**
	 * member���̺��� ID�� �ش��ϴ� ���ڵ� ����
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
