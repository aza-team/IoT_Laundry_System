package jdbc_power;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import jdbc_member.MemberVO;

public class PowerDAO {
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

	public PowerDAO() {
		try {
			Class.forName(diverName);
			con = (Connection) DriverManager.getConnection(url, userName, userPwd);
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> load fail");
		} catch (SQLException e) {
			System.out.println(e + "=> conet fail");
		}
	}
	
	public ArrayList<PowerVO> getPowerlist() {
		ArrayList<PowerVO> list= new ArrayList<PowerVO>();
		
		try {
			stmt = (Statement) con.createStatement();
			rs = stmt.executeQuery("select * from checkbox");

			while (rs.next()) {
				PowerVO vo = new PowerVO();

				vo.setMacineNum(rs.getString(1));
				vo.setSetCheck(rs.getString(2));
			
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println(e + "=> getMemberlist fail");
		} finally {
			db_close();
		}
		return list;
	}
	
	
}
