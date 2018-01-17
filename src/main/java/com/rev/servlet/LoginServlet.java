package com.rev.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rev.dao.*;
import com.rev.model.User;

import java.sql.*;
import java.util.Enumeration;



//@WebServlet({"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("login.html").forward(request, response);
		
/*		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("This is the Test Servlet");

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			out.print("<br/>Header Name: <em>" + headerName);
			String headerValue = request.getHeader(headerName);
			out.print("</em>, Header Value: <em>" + headerValue);
			out.println("</em>");
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("This is the login Servlet");*/
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//login authentication
		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		System.out.println(username + " " + password);
		
		try {
			User user = new UserDAOImpl().login(username, password);
			if (user != null) {
				HttpSession session = request.getSession(true);	//get current session, true=init one if none
				session.setAttribute("user", user);
				//log.debug("Login Successful : " + username);
				System.out.println("User logging in: " + username);
				//request.getSession().setAttribute("message", "User login successful");
				//request.getSession().setAttribute("messageClass", "alert-success");
				response.sendRedirect("employee/home.html");
			}
			else {
				System.out.println("Unable to login");
				request.getRequestDispatcher("login.html").forward(request, response);
			}

		} //try
		catch (SQLException e){
			e.printStackTrace();
			//request.getSession().setAttribute("message", e.getMessage());
			//request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("login.html").forward(request, response);
		} //catch
	} //doPost()

}
