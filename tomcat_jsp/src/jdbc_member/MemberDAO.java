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
	 * member테이블의 ID에 해당하는 레코드 삭제
	 */

	public int delMemberlist(String id) {
		int result = 0;
		try {// 실행

			pstmt = (PreparedStatement) con.prepareStatement("delete from member_list where id = ?");
			// ?개수만큼 값 지정
			pstmt.setString(1, id.trim());
			result = pstmt.executeUpdate(); // 쿼리실행으로 삭제된 레코드 수 반환

		} catch (Exception e) {
			System.out.println(e + "=> delMemberlist fail");
		} finally {
			db_close();
		}

		return result;
	}
}
