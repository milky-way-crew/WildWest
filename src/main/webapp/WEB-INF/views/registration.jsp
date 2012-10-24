<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="css/bootstrap-responsive.css">

</head>
<body>
	<div class="hero-unit"><h1>Registration page</h1></div>
	<div class="container">
		<div class="row">
			<div class="span8">
				<form:form class="form-horizontal" id="registerHere"
					action="registration.html" method="post" commandName="user">
					<fieldset>

						<legend>Fill the form below</legend>

						<div class="control-group">
							<label class="control-label">Login</label>
							<div class="controls">
								<form:input type="text" path="login" class="input-xlarge"
									id="login" name="login" rel="popover"
									data-content="Enter your login" data-original-title="Login" />
							</div>
						</div>


						<div class="control-group">
							<label class="control-label">Nickname</label>
							<div class="controls">
								<form:input type="text" path="nickname" class="input-xlarge"
									id="nickname" name="nickname" rel="popover"
									data-content="Enter your nickname"
									data-original-title="Nickname" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Email</label>
							<div class="controls">
								<form:input type="text" path="email" class="input-xlarge"
									id="email" name="email" rel="popover"
									data-content="Enter your email" data-original-title="Email" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Password</label>
							<div class="controls">
								<form:input type="password" path="password" class="input-xlarge"
									id="password" name="password" rel="popover"
									data-content="Enter your password"
									data-original-title="Password" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Confirm password</label>
							<div class="controls">
								<form:input type="password" path="password" class="input-xlarge" id="conf_password"
									name="conf_password" rel="popover"
									data-content="Confirm your password"
									data-original-title="Password" />
							</div>
						</div>


						<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								<button type="submit" class="btn btn-success">Create my account</button>
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
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	<script type="text/javascript" src="js/register.js"></script>
</body>
</html>
