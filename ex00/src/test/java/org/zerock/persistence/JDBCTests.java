package org.zerock.persistence;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	//JDBCTests 호출이 될 때 한 번만 실행- static
	static {
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			log.info("연결성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		Connection con =null;
		try {
			 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","java","java");
			log.info("연결성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
