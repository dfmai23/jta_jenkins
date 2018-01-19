package com.rev.dao;

import java.sql.*;

public class DAOUtils {
	private static final String CONNECTION_USERNAME = "admin";
	private static final String CONNECTION_PASSWORD = "password";
	private static final String URL = "jdbc:oracle:thin:@reimbursementsystem.cpzoqntrfu8m.us-east-1.rds.amazonaws.com:1521:ORCL";
	
	private static Connection connection;
	private static ReimbursementDAOImpl reimbursementDAO;
	private static UserDAOImpl userDAO;
	
	public static synchronized ReimbursementDAO getReimbursementDAO() {
		if (reimbursementDAO == null) {
			reimbursementDAO = new ReimbursementDAOImpl();
		}
		return reimbursementDAO;
	}
	
	public static synchronized UserDAO getUserDAO() {
		if (userDAO == null) {
			userDAO = new UserDAOImpl();
		}
		return userDAO;
	}
	
	public static synchronized Connection getConnection() throws SQLException { // sets up connection to db server
		if (connection == null) {
			try {	// 1 - load drivers
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			// 2 - establish the connection
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
			//log.debug("Connection created");
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			//System.out.println("getting new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
		// 3 - create sql statements, 4- execute the query, 5 - Store the results, 6 - close connection
	}
}
