package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	
	private static Connection conn;
	
	public static Connection getConn() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
  //                      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse_3","root","Root@123");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse_3","root","54321");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse_2","root","mish@219");

conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse_5","root","54321");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	
	
}