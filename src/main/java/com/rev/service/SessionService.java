package com.rev.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rev.dao.DAOUtils;
import com.rev.dao.UserDAOImpl;
import com.rev.model.User;
import com.rev.service.ServiceResult;
import static com.rev.util.Constants.*;

public class SessionService {
	private static Logger log = LogManager.getLogger(SessionService.class.getName());
	
	public static ServiceResult login(HttpServletRequest request) {	//login authentication
		if(request.getMethod().equals(GET)) {
			return new ServiceResult("login.html", FORWARD);
		}
		
		try { //POST
			String username = request.getParameter("j_username");
			String password = request.getParameter("j_password");
			User user = DAOUtils.getUserDAO().login(username, password);
			//System.out.println(username + " " + password);
			if (user != null) {
				HttpSession session = request.getSession(true);	//get current session, true=init one if none
				session.setAttribute("user", user);
				//log.debug("Login Successful : " + username);
				System.out.println("User logging in: " + username);
				//response.sendRedirect("employee/home.html")
				if(user.getRole().equals(EMPLOYEE)) {
					return new ServiceResult("employee/account.do", REDIRECT);
				}
				else	{
					return new ServiceResult("manager/account.do", REDIRECT);
				}
					
			}
			else {
				System.out.println("Unable to login");
				return new ServiceResult("login.html", FORWARD);
				//request.getRequestDispatcher("login.html").forward(request, response);
			}
		} //try
		catch (SQLException e){
			e.printStackTrace();
			log.debug(e);
			//request.getSession().setAttribute("message", e.getMessage());
			//request.getSession().setAttribute("messageClass", "alert-danger");
			//request.getRequestDispatcher("login.html").forward(request, response);
			return new ServiceResult("login.html", FORWARD);
			
		} //catch
	}
	
	public static ServiceResult logout(HttpServletRequest request) {	
		HttpSession session = request.getSession(false);
		if (session != null) {
			System.out.println("Logging out");
			System.out.println(session.getAttribute("user").toString());
			session.invalidate();	//invalidate bug
  		}
		return new ServiceResult("login.do", REDIRECT);
	}
	
}
