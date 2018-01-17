package com.rev.servlet;

import static com.rev.util.Constants.DOMAIN;
import static com.rev.util.Constants.REDIRECT;

import javax.servlet.http.HttpServletRequest;

import com.rev.service.EmployeeService;
import com.rev.service.IndexService;
import com.rev.service.ReimbursementService;
import com.rev.service.ServiceResult;
import com.rev.service.SessionService;

public class AjaxDispatcher {
	public static ServiceResult process(HttpServletRequest request) {
		System.out.println("ajax " + request.getMethod() + ": " + request.getRequestURI());
		
		switch(request.getRequestURI()) {
		case DOMAIN + "/employee/closed_reimbursements.ajax":
			return ReimbursementService.closedReimbursements(request);
		default:
			return new ServiceResult(DOMAIN + "/404.html", REDIRECT);
		}
	}
}
