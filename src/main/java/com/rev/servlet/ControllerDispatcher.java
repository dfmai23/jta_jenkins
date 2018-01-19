package com.rev.servlet;

import javax.servlet.http.HttpServletRequest;

import com.rev.service.*;
import static com.rev.util.Constants.*;

/* Class that helps the FrontController with mappings to specific controllers */
public class ControllerDispatcher {
	public static ServiceResult process(HttpServletRequest request) {
		System.out.println(request.getMethod() + ": " + request.getRequestURI());
		
		switch(request.getRequestURI()) {
		case DOMAIN + "/login.do":
			return SessionService.login(request);	//login.html
		case DOMAIN + "/logout.do":
			return SessionService.logout(request);	//logout -> login.html
		case DOMAIN + "/manager/home.do":
		case DOMAIN + "/employee/home.do":
			return EmployeeService.home(request);	//employee/home.html
		case DOMAIN + "/manager/account.do":
		case DOMAIN + "/employee/account.do":
			return EmployeeService.account(request);	
		case DOMAIN + "/manager/edit_info.do":
		case DOMAIN + "/employee/edit_info.do":
			return EmployeeService.editInfo(request);
		case DOMAIN + "/manager/reimbursements.do":
		case DOMAIN + "/employee/reimbursements.do":
			return ReimbursementService.reimbursements(request);
		case DOMAIN + "/manager/open_reimbursement.do":
		case DOMAIN + "/employee/open_reimbursement.do":
			return ReimbursementService.openReimbursement(request);
		case DOMAIN + "/manager/all_pending_reimbursements.do":
			return ReimbursementService.allPendingReimbursements(request);
		case DOMAIN + "/manager/all_resolved_reimbursements.do":
			return ReimbursementService.allResolvedReimbursements(request);
		case DOMAIN + "/manager/accept_reimbursement.do":
			return ReimbursementService.acceptReimbursement(request);
		case DOMAIN + "/manager/reject_reimbursement.do":
			return ReimbursementService.rejectReimbursement(request);
		case DOMAIN + "/manager/view_employees.do":
			return EmployeeService.viewEmployees(request);
		case DOMAIN + "/manager/view_employee.do":
			return EmployeeService.viewEmployee(request);
		case DOMAIN + "/index.do":
			return IndexService.index(request);		//index.html
		default:
			return new ServiceResult(DOMAIN + "/404.html", REDIRECT);
		}
	}
}







