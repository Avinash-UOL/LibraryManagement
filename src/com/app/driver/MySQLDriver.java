package com.app.driver;

import java.sql.*;

public class MySQLDriver {
	
	//SQL Connection String
	public static Connection connect() {
		String url = "jdbc:mysql://sql687.main-hosting.eu/u541335849_group12library";
		String username = "u541335849_group12user";
		String password = "Group12@123";
		Connection con;
		try {
			con = DriverManager.getConnection(url, username, password);
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//SQL Insert Query
	public static void insertToTable(Connection connection, String query) {
		try {
			Statement statement = connection.createStatement();
			int n = statement.executeUpdate(query);
			//System.out.println("Query OK," + n + " rows affected");
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
