package com.rev.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rev.dao.DAOUtils;

@WebServlet({"/test"})
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		Statement statement = null;
		
		try { //add sched to table
			System.out.println("here");
			connection = DAOUtils.getConnection();
			System.out.println("here");
			String sql = "select * from users";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString(1) + " : " + rs.getString(2) + " : " + rs.getString(3));
			}
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("This is the Test Servlet");
		} //try
		catch (SQLException e) {
			e.printStackTrace();
		} //catch
		finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} //try
			catch (SQLException e) {
				e.printStackTrace();
			} //catch
		} //finall
	}
}
