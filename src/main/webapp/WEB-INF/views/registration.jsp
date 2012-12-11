<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" href="resources/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/bootstrap-responsive.css">

</head>
<body>
	<!--/.navbar -->
	<div class="navbar navbar-fixed-top navbar-inverse">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">Milky-way games</a>
				<div class="nav-collapse">
					<ul class="nav">
						<li><a href="#about">About</a></li>
					</ul>
					<ul class="nav pull-right">
						<li class="divider-vertical"></li>
						<li class="dropdown"><a class="dropdown-toggle" href="#"
							data-toggle="dropdown">Sign In <strong class="caret"></strong></a>
							<div class="dropdown-menu"
								style="padding: 15px; padding-bottom: 0px;">
								<form:form id="loginHere" method='post' action='login'
									commandName="user">
									<fieldset>
										<h2>
											<legend>Sign in</legend>
										</h2>
										<div class="control-group">
											<label class="control-label">Login</label>
											<div class="controls">
												<div class="box transparent">
													<form:input style="margin-bottom: 15px;" type="text"
														path="login" class="input-xlarge" id="login" name="login"
														placeholder="What is your login, buddy?"/>
													<form:errors path="login" cssClass="btn-mini btn-danger"></form:errors>
												</div>
											</div>
										</div>


										<div class="control-group">
											<h3>
												<label class="control-label">Password</label>
											</h3>
											<div class="controls">
												<div class="box transparent">
													<form:input style="margin-bottom: 15px;" type="password"
														path="password" class="input-xlarge" id="password2"
														name="password" placeholder="keep your password in safe place" />
													<form:errors path="password" cssClass="btn-mini btn-danger"></form:errors>
												</div>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label"></label>
											<div class="controls">
												<button type="submit" id="sign-in"
													class="btn btn-primary btn-block">
													<i class="icon-user icon-white"></i> Sign in
												</button>
											</div>
										</div>

									</fieldset>
								</form:form>
							</div></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
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
									data-content="Minimal length of login is 6 symbols" data-original-title="Login" />
								<form:errors path="login" cssClass="btn-mini btn-danger"></form:errors>
							</div>
						</div>


						<div class="control-group">
							<label class="control-label">Nickname</label>
							<div class="controls">
								<form:input type="text" path="nickname" class="input-xlarge"
									id="nickname" name="nickname" rel="popover"
									data-content="Minimal length of nickname is 4 symbols"
									data-original-title="Nickname" />
								<form:errors path="nickname" cssClass="btn-mini btn-danger"></form:errors>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Email</label>
							<div class="controls">
								<form:input type="text" path="email" class="input-xlarge"
									id="email" name="email" rel="popover"
									data-content="Please enter your valid email" data-original-title="Email" />
								<form:errors path="email" cssClass="btn-mini btn-danger"></form:errors>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Password</label>
							<div class="controls">
								<form:input type="password" path="password" class="input-xlarge"
									id="password" name="password" rel="popover"
									data-content="Minimal length of password is 6 symbols"
									data-original-title="Password" />
								<form:errors path="password" cssClass="btn-mini btn-danger"></form:errors>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">Confirm password</label>
							<div class="controls">
								<input id="conf_password" type="password" 
									path="password" class="input-xlarge"
									name="conf_password" rel="popover"
									data-content="Confirm your password here"
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
	<script type="text/javascript" src="resources/js/bootstrap-transition.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-alert.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-modal.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-dropdown.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-tooltip.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-popover.js"></script>
	<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="resources/js/register.js"></script>
</body>
</html>
