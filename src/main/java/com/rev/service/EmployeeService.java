package com.rev.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rev.dao.*;
import com.rev.model.Reimbursement;
import com.rev.model.User;

import static com.rev.util.Constants.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeService {
	private static Logger log = LogManager.getLogger(EmployeeService.class.getName());
	
	public static ServiceResult home(HttpServletRequest request) {
		//System.out.println(request.getSession(false));
		if(request.getSession(false) != null) {
			//user already stored in session
			//System.out.println(request.getSession().getAttribute("user"));
			return new ServiceResult("home.jsp", FORWARD);
		}
		else {
			//System.out.println("no session");	//not logged in
			return new ServiceResult(DOMAIN + "/login.do", REDIRECT);
		}
	}
	
	public static ServiceResult account(HttpServletRequest request) {
		if(request.getMethod().equals(GET) ) {
			return new ServiceResult("account.jsp", FORWARD);
		}
		return null;
	}

	public static ServiceResult editInfo(HttpServletRequest request) {
		if(request.getMethod().equals(GET) ) {
			return new ServiceResult("edit_info.jsp", FORWARD);
		}
		
		//POST
		String newFirstname = request.getParameter("new_firstname");
		String newLastname = request.getParameter("new_lastname");
		String newUsername = request.getParameter("new_username");
		String newPassword = request.getParameter("new_password1");
		//System.out.println("pws: " + request.getParameter("new_password1") + " " + request.getParameter("new_password2"));
		
		User oldUser = (User) request.getSession().getAttribute("user");	//should implement getuser in DAOImpl
		User newUser = new User(oldUser.getId(), 
				newFirstname, 
				newLastname, 
				newUsername,
				newPassword,
				oldUser.getRole());
		System.out.println(newUser.toString());
		
		try {
			DAOUtils.getUserDAO().updateUser(oldUser, newUser);
			if(!oldUser.getUname().equals(newUser.getUname())) {
				request.getSession().setAttribute("user", newUser);	//update current user logged in
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.debug(e);
		}
		
		return new ServiceResult("account.do", REDIRECT);
	}
	
	public static ServiceResult viewEmployees(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<User> users = new ArrayList<User>();
		try {
			users = DAOUtils.getUserDAO().getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
			log.debug(e);
		}
		request.getSession().setAttribute("users", users);
		return new ServiceResult("view_employees.jsp", FORWARD);
	}
	
	public static ServiceResult viewEmployee(HttpServletRequest request) {
		int userid = Integer.parseInt(request.getParameter("userid"));
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		reimbursements = DAOUtils.getReimbursementDAO().getEmployeeReimbursements(userid);
		User employee = null;
		try {
			employee = DAOUtils.getUserDAO().getUser(userid);
			request.getSession().setAttribute("reimbursements", reimbursements);
			request.getSession().setAttribute("employee", employee);
		} catch (SQLException e) {
			e.printStackTrace();
			log.debug(e);
		}
		
		return new ServiceResult("view_employee.jsp", FORWARD);
	}
}
