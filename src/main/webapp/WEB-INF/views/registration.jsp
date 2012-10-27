<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" href="resources/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/bootstrap-responsive.css">

</head>
<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#" name="top">MONOPOLY</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="#"><i class="icon-home"></i> Home</a></li>
						<li class="divider-vertical"></li>
						<li class="active"><a href="#"><i class="icon-user"></i>
								Profile</a></li>
						<li class="divider-vertical"></li>
						<li><a href="#"><i class="icon-globe"></i>TOP-100</a></li>
						<li class="divider-vertical"></li>
					</ul>
					<ul class="nav pull-right">
						<li class="divider-vertical"></li>
							<li><a href="#"><i class="icon-question-sign"></i>Help</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</div>
		<!--/.navbar-inner -->
	</div>
	<!--/.navbar -->
	<div class="hero-unit">
		<h1>Registration page</h1>
	</div>
	<div class="container">
		<div class="row">
			<div class="span8">
				<form:form class="form-horizontal" id="registerHere"
					action="register" method="post" commandName="user">
					<fieldset>

						<legend>Fill the form below</legend>

						<div class="control-group">
							<label class="control-label">Login</label>
							<div class="controls">
								<form:input type="text" path="login" class="input-xlarge"
									id="login" name="login" rel="popover"
									data-content="Enter your login" data-original-title="Login" />
								<form:errors path="login" cssClass="btn-mini btn-danger"></form:errors>
							</div>
						</div>


						<div class="control-group">
							<label class="control-label">Nickname</label>
							<div class="controls">
								<form:input type="text" path="nickname" class="input-xlarge"
									id="nickname" name="nickname" rel="popover"
									data-content="Enter your nickname"
									data-original-title="Nickname" />
								<form:errors path="nickname" cssClass="btn-mini btn-danger"></form:errors>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Email</label>
							<div class="controls">
								<form:input type="text" path="email" class="input-xlarge"
									id="email" name="email" rel="popover"
									data-content="Enter your email" data-original-title="Email" />
								<form:errors path="email" cssClass="btn-mini btn-danger"></form:errors>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Password</label>
							<div class="controls">
								<form:input type="password" path="password" class="input-xlarge"
									id="password" name="password" rel="popover"
									data-content="Enter your password"
									data-original-title="Password" />
								<form:errors path="password" cssClass="btn-mini btn-danger"></form:errors>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Confirm password</label>
							<div class="controls">
								<input type="password" path="password" class="input-xlarge"
									id="conf_password" name="conf_password" rel="popover"
									data-content="Confirm your password"
									data-original-title="Password" />
							</div>
						</div>


						<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								<button type="submit" class="btn btn-success">Create my
									account</button>
							</div>
						</div>

					</fieldset>
				</form:form>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="resources/js/jquery.js"></script>
	<script type="text/javascript"
		src="resources/js/bootstrap-transition.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-alert.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-modal.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-dropdown.js"></script>
	<script type="text/javascript"
		src="resources/js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-tab.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-tooltip.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-popover.js"></script>
	<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="resources/js/register.js"></script>
</body>
</html>
