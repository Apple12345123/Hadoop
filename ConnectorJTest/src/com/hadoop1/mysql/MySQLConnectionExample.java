package com.hadoop1.mysql;

import java.sql.*;

public class MySQLConnectionExample {

	public static void main(String[] args) {

		try {
			// Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("load Driver class");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://10.100.203.58:3306/hadoop_test",
					"hadoop_test",
					"12345"
			);
			System.out.println(conn);
			
			String sql = "SELECT * FROM test";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String word = rs.getString(1);
				int count = rs.getInt(2);
				System.out.println(word+":"+count);
			} // end while
			
			rs.close();
			pstmt.close();
			conn.close();
			System.out.println("SELECT WORK END");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
