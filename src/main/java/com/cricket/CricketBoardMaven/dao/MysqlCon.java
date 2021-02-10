package com.cricket.CricketBoardMaven.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Get Connection from DriverManager
 * @author swapnilu
 *
 */
public class MysqlCon {
	private MysqlCon() {
	}
	/**
	 * Get Connection
	 * @return Connection object for database connection
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
			//Class.forName("com.mysql.jdbc.Driver");
//			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swapnil","root","root");
			Connection con=DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/","sa","");
		return con;
	}

}
