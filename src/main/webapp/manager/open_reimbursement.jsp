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
	<link rel="stylesheet" href="../resources/css/style.css">
<title>Reimbursement System Home</title>

</head>

<body class="container">
	<div class="navbar">
		<div class="container-fluid">
		    <div class="navbar-header">
		      	<a class="navbar-brand" href="../index.do">Reimbursement System</a>
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
					<h2>Open a Reimbursement</h2>
				</div>
				
				<div class="col-sm-8">
					<form action="open_reimbursement.do" method="post" class="form-horizontal">		
						<div class="form-group"> 
							<label for="amount">Amount</label>
							<input id="amount" class="form-control" name="inp_amount" type="text" required="required"/>
						</div>
						<div class="form-group"> 
							<label for="firstname">Description</label>
							<input id="firstname" class="form-control" name="inp_description" type="text" required="required"/>
						</div>
						
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>