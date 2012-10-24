<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 
<link rel="stylesheet" href="css/registration_login.css" type="text/css">
 -->
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="css/bootstrap-responsive.css"
	type="text/css">
<title>Login form</title>
</head>
<body>
	<div class="hero-unit"><h1>Monopoly</h1></div>
	<div class="container">
		<div class="row">
			<div class="span8">
				<form:form class="form-horizontal" id="loginHere" method='post'
					action='loginform.html' commandName="user">
					<fieldset>
							<h2>
								<legend>Sign in</legend>
							</h2>
						<div class="control-group">
							<label class="control-label">Login</label>
							<div class="controls">
								<div class="box transparent">
									<form:input type="text" path="login" class="input-xlarge"
										id="login" name="login" rel="popover"
										data-content="What is your login, buddy?"
										data-original-title="Login field" />
								</div>
							</div>
						</div>


						<div class="control-group">
							<h3>
								<label class="control-label">Password</label>
							</h3>
							<div class="controls">
								<div class="box transparent">
									<form:input type="password" path="password"
										class="input-xlarge" id="password" name="password"
										rel="popover" data-content="What is your password?"
										data-original-title="Password field" />
								</div>
							</div>
						</div>

							<div class="control-group">
								<label class="control-label"></label>
								<div class="controls">
									<button type="submit" class="btn">Sing in</button>
									<a href="registration.html"> Registration</a>
								</div>
							</div>
							
			</fieldset>
			</form:form>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap-transition.js"></script>
	<script type="text/javascript" src="js/bootstrap-alert.js"></script>
	<script type="text/javascript" src="js/bootstrap-modal.js"></script>
	<script type="text/javascript" src="js/bootstrap-dropdown.js"></script>
	<script type="text/javascript" src="js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="js/bootstrap-tab.js"></script>
	<script type="text/javascript" src="js/bootstrap-tooltip.js"></script>
	<script type="text/javascript" src="js/bootstrap-popover.js"></script>
<!-- 
	<script type="text/javascript" src="js/jquery.validate.js"></script>
 -->
	<!-- Include Bootstrap Asserts JavaScript Files. -->
	<!-- 
	<script src="http://twitter.github.com/bootstrap/assets/js/jquery.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-transition.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-alert.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-modal.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-dropdown.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-scrollspy.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-tab.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-tooltip.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-popover.js"></script>
	<script type="text/javascript"
		src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
 -->

	<script type="text/javascript" src="js/login.js"></script>
</body>
</html>
