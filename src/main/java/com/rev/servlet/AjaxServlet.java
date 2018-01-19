package com.rev.servlet;

import static com.rev.util.Constants.DOMAIN;
import static com.rev.util.Constants.FORWARD;
import static com.rev.util.Constants.REDIRECT;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rev.dao.DAOUtils;
import com.rev.model.*;
import com.rev.service.ReimbursementService;
import com.rev.service.ServiceResult;

import com.google.gson.*;

public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ServiceResult sr = AjaxDispatcher.process(request);
		User user = (User) request.getSession().getAttribute("user");
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		System.out.println("AJAX " + request.getMethod() + ": " + request.getRequestURI());
		switch(request.getRequestURI()) {
		case DOMAIN + "/manager/closed_reimbursements.ajax":
		case DOMAIN + "/employee/closed_reimbursements.ajax":
			reimbursements = DAOUtils.getReimbursementDAO().getResolved(user.getId());
			break;
		case DOMAIN + "/manager/open_reimbursements.ajax":
		case DOMAIN + "/employee/open_reimbursements.ajax":
			reimbursements = DAOUtils.getReimbursementDAO().getPending(user.getId());
			break;
		}
		
	
		Gson gson = new Gson();
		String json = gson.toJson(reimbursements);
		//System.out.println(json);
		
		response.setContentType("application/json");
		//response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
		PrintWriter pw = response.getWriter();
	    pw.write(json);		//Write response body, will send back to js.
		
	}


/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

}
