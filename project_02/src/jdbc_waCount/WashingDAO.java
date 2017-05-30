package jdbc_waCount;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import jdbc_member.MemberVO;

public class WashingDAO {
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	String diverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/micon?autoReconnect=true&useSSL=false";
	
	private String userName = "root";
	private String userPwd = "root";

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

	public WashingDAO() {
		try {
			Class.forName(diverName);
			con = (Connection) DriverManager.getConnection(url, userName, userPwd);
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> load fail");
		} catch (SQLException e) {
			System.out.println(e + "=> conet fail");

		}
	}
	
	public ArrayList<WaVO> getWAlist() {
		ArrayList<WaVO> list2 = new ArrayList<WaVO>();
		
		try {
			stmt = (Statement) con.createStatement();
			rs = stmt.executeQuery("select * from test");

			while (rs.next()) {
				WaVO vo = new WaVO();

				vo.setSetTime(rs.getString(1));
				vo.setWaCount(rs.getString(2));
			
				list2.add(vo);
			}
		} catch (Exception e) {
			System.out.println(e + "=> getMemberlist fail");
		} finally {
			db_close();
		}
		return list2;
	}
	
	
}
