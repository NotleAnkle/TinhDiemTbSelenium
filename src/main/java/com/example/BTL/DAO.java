package com.example.BTL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	public static Connection con;
	
	public DAO(){
		if(con == null){
			String dbUrl = "jdbc:mysql://localhost:3306/qldt?autoReconnect=true&useSSL=false";
			String dbClass = "com.mysql.cj.jdbc.Driver";

			try {
				Class.forName(dbClass);
				con = DriverManager.getConnection (dbUrl, "root", "12345678");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void SetDAOForTest() {
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void RollBack() {
		try {
			con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
