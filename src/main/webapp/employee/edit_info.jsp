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
	
<title>Edit Account Info</title>
</head>

<body>
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
				<li><a href="account.do">Account</a></li>
				<li><a href="../logout.do">Logout</a></li>
			</ul>
		</div>
	</div>
	
	<div class="jumbotron vertical-center">
	<form action="edit_info.do" method="post" class="form-horizontal">		
		<div class="form-group"> 
			<label for="firstname" class="col-sm-4">First Name</label>
			<div class="col-sm-4">
				<input id="firstname" class="form-control" name="new_firstname" type="text" value="${user.fname}" required="required"/>
			</div>
		</div>
	
		<div class="form-group"> 
			<label for="lastname" class="col-sm-4">Last Name</label>
			<div class="col-sm-4">
				<input id="lastname" class="form-control" name="new_lastname" type="text" value="${user.lname}" required="required"/>
			</div>
		</div>
	
		<div class="form-group"> 
			<label for="username" class="col-sm-4">Username</label>
			<div class="col-sm-4">
				<input id="username" class="form-control" name="new_username" type="text" value="${user.uname}" required="required"/>
			</div>
		</div>
	
		<div class="form-group"> 
			<label for="pw1" class="col-sm-4 ">Password (Optional)</label>
			<div class="col-sm-4">
				<input id="pw1" class="form-control" name="new_password1" type="password" value="${user.password}" placeholder="Type new Password" autocomplete="off" required="required"/>
			</div>
		</div>
		
		<div class="form-group"> 
			<label for="pw2" class="col-sm-4">Password (Again)</label>
			<div class="col-sm-4">
				<input id="pw2" class="form-control" name="new_password2" type="password" value="${user.password}" placeholder="Type Password Again" autocomplete="off" required="required"/>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12 vertical-center">
				Passwords Match<span id="pwmatch" class="glyphicon glyphicon-remove" ></span>
			</div>
		</div>

	  	<div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      	<button name="userid" type="submit" class="btn btn-primary" value="${user.id}">Update</button>
		    </div>
	  	</div>
	</form>
	</div>
	
	<script src="../resources/js/javascript.js"></script>
</body>
</html>