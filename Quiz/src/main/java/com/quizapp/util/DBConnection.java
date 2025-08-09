package com.quizapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	 private static String URL="jdbc:mysql://localhost:3306/testapplication";
	private static String USER="root";
	private static String PASSWORD ="";

	public static Connection getConnection() throws SQLException {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC Driver
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
}
