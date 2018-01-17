package com.rev.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rev.dao.*;
import com.rev.model.*;
import static com.rev.util.Constants.*;

public class ReimbursementService {
	private static Logger log = LogManager.getLogger(ReimbursementService.class.getName());
	
	public static ServiceResult reimbursements(HttpServletRequest request) {
		
		User user = (User) request.getSession().getAttribute("user");
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		reimbursements = DAOUtils.getReimbursementDAO().getPending(user.getId());
		request.getSession().setAttribute("reimbursements", reimbursements);
		return new ServiceResult("reimbursements.jsp", FORWARD);
		
	}
	
	public static ServiceResult closedReimbursements(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		reimbursements = DAOUtils.getReimbursementDAO().getResolved(user.getId());
		request.getSession().setAttribute("closed_reimbursements", reimbursements);
		
		return new ServiceResult("reimbursements.jsp", FORWARD);
	}
	
	public static ServiceResult openReimbursement(HttpServletRequest request) {
		if (request.getMethod().equals(GET)) {
			return new ServiceResult("open_reimbursement.jsp", FORWARD);
		}
		
		User user = (User) request.getSession().getAttribute("user");
		LocalDateTime timestamp = LocalDateTime.now();
		Reimbursement reimbursement = new Reimbursement(-1,
				user.getId(),
				timestamp,
				Double.parseDouble(request.getParameter("inp_amount")),
				request.getParameter("inp_description"));
		
		System.out.print(reimbursement.toString());
		DAOUtils.getReimbursementDAO().addReimbursement(reimbursement);
		return new ServiceResult("reimbursements.do", REDIRECT);
	}
	
	public static ServiceResult allPendingReimbursements(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		List<User> users = new ArrayList<User>();
		HashMap<Integer, User> usermap = new HashMap<Integer, User>();
		
		for (User u: users) {
			usermap.put(user.getId(), user);
		}
		
		
		try {
			reimbursements = DAOUtils.getReimbursementDAO().getAllPending();
			users = DAOUtils.getUserDAO().getAllUsers();
		} catch (SQLException e) {
			//log
			e.printStackTrace();
			log.debug(e);
		}
		request.getSession().setAttribute("reimbursements", reimbursements);
		request.getSession().setAttribute("usermap", usermap);
		//System.out.println("here");
		return new ServiceResult("all_open_reimbursements.jsp", FORWARD);
	}
	
	public static ServiceResult allResolvedReimbursements(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		List<User> users = new ArrayList<User>();
		HashMap<Integer, User> usermap = new HashMap<Integer, User>();
		
		for (User u: users) {
			usermap.put(user.getId(), user);
		}
		
		try {
			reimbursements = DAOUtils.getReimbursementDAO().getAllResolved();
			users = DAOUtils.getUserDAO().getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
			log.debug(e);
		}
		request.getSession().setAttribute("reimbursements", reimbursements);
		request.getSession().setAttribute("usermap", usermap);
		//System.out.println("here");
		return new ServiceResult("all_closed_reimbursements.jsp", FORWARD);
	}
	
	public static ServiceResult acceptReimbursement(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("rid"));
		int resolverid = ((User) request.getSession().getAttribute("user")).getId();
		Reimbursement re = DAOUtils.getReimbursementDAO().getOpenReimbursement(id);
		DAOUtils.getReimbursementDAO().moveReimbursement(re, ACCEPTED, resolverid);
		return new ServiceResult("all_pending_reimbursements.do", REDIRECT);
	}
	
	public static ServiceResult rejectReimbursement(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("rid"));
		int resolverid = ((User) request.getSession().getAttribute("user")).getId();
		Reimbursement re = DAOUtils.getReimbursementDAO().getOpenReimbursement(id);
		DAOUtils.getReimbursementDAO().moveReimbursement(re, REJECTED, resolverid);
		return new ServiceResult("all_pending_reimbursements.do", REDIRECT);
	}

}
