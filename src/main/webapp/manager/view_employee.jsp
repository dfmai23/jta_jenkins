<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
	<%@ page import="com.rev.model.User" %>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" 
	integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" 
	crossorigin="anonymous"></script>
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<link rel="stylesheet" href="../resources/css/style.css">
<title>Reimbursement System Home</title>

</head>

<body class="container loadajax" >
	<div class="navbar">
		<div class="container-fluid">
		    <div class="navbar-header">
		      	<a class="navbar-brand" href="../index.do">Reimbursement System - Manager</a>
		    </div>
			<ul class="nav navbar-nav navbar-right">
				<li><a class="navbar-brand" href="home.do">
					<span class="glyphicon glyphicon-home" aria-hidden="true"> </span>
					</a>
				</li>
				<li><a href="reimbursements.do">Reimbursements</a>
				<li><a href="account.do">Account</a></li>
				<li><a href="../logout.do">Logout</a></li>
			</ul>
		</div>
	</div>
	
	<div class="jumbotron vertical-center">
		<div class="content">
			<div class="row">
				<div class="col-sm-8">
					<h2>Employee Reimbursement</h2>
					<h3>${employee.fname} ${employee.lname}</h3>
				</div>
				<div class="col-sm-4">
					<a href="open_reimbursement.do" class="btn btn-primary" >New Reimbursement</a>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-3">
					<a href="reimbursements.do" class="btn btn-primary" >My Reimbursements</a>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3">
					<a href="all_pending_reimbursements.do" class="btn btn-primary">All Pending</a>
				</div>
				<div class="col-sm-3">
					<a href="all_resolved_reimbursements.do" class="btn btn-primary">All Resolved</a>
				</div>
				<div class="col-sm-3">
					<a href="view_employees.do" class="btn btn-primary">Employees</a>
				</div>
			</div>

			<table id="static_table" class="table table-striped table-hover table-responsive table-bordered">
				<caption>All Pending</caption>
				<thead>
					<tr>
						<th class="text-center">Reimbursement ID</th>
						<th class="text-center">Employee ID</th>
						<th class="text-center">Amount</th>
						<th class="text-center">Start Date</th>
						<th class="text-center">End Date</th>
						<th class="text-center">Status</th>
						<th class="text-center">Resolver ID</th>
						<th class="text-center">Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reimbursement" items="${reimbursements}" varStatus="status">> 
						<tr>
							<td><c:out value="${reimbursement.id}" /></td>
							<td><c:out value="${reimbursement.eid}" /></td>
							<td><fmt:formatNumber type="currency" value="${reimbursement.amount}" /></td>
							<td><c:out value="${reimbursement.startDate}" /></td>
							<td><c:out value="${reimbursement.endDate}" /></td>
							<td>
								<c:choose>
								    <c:when test="${reimbursement.status=='1'}">
								        Accepted
								    </c:when>    
								    <c:when test="${reimbursement.status=='2'}">
								        Rejected
								    </c:when>    
								    <c:otherwise>
								    	N/A
								    </c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
								    <c:when test="${reimbursement.resolverId=='-1'}">
								        None
								    </c:when>  
								    <c:otherwise>
								    	<c:out value="${reimbursement.resolverId}" />
								    </c:otherwise>
								</c:choose>
							</td>
							<td><c:out value="${reimbursement.description}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
	</div>
	<script src="../resources/js/ajax.js"></script>
	<script src="../resources/js/manager_ajax.js"></script>
</body>
</html>