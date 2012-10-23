<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<title>Login form</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="span8">
				<form:form class="form-horizontal" id="registerHere" method='post'
					action='loginform.html' commandName="user">
					<fieldset>

						<legend>Sign in</legend>

						<div class="control-group">
							<label class="control-label">Login</label>
							<div class="controls">
								<form:input type="text" path="login" class="input-xlarge"
									id="login" name="login" rel="popover"
									data-content="What's your login, buddy?"
									data-original-title="Login field" />
							</div>
						</div>


						<div class="control-group">
							<label class="control-label">Password</label>
							<div class="controls">
								<form:input type="password" path="password" class="input-xlarge"
									id="password" name="password" rel="popover"
									data-content="What's your password?"
									data-original-title="Password field" />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								<button type="submit" class="btn btn-success">Create</button>
							</div>
						</div>

					</fieldset>
				</form:form>
			</div>
		</div>
	</div>

	<!-- Include Bootstrap Asserts JavaScript Files. -->
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
	<script type="text/javascript">
		$(document).ready(function() {
			$('input').hover(function() {
				$(this).popover('show');
			}, function() {
				$(this).popover('hide');
			});

			$("#registerHere").validate({
				rules : {
					login : "required",
					password : {
						required : true,
						minlength : 1
					}
				},
				messages : {
					login : {
						required : "You forgot to enter your login here."
					},
					password : {
						required : "Cmon enter your password.",
					}
				},
				errorClass : "help-inline",
				errorElement : "span",
				highlight : function(element, errorClass, validClass) {
					$(element).parents('.control-group').removeClass('success');
					$(element).parents('.control-group').addClass('error');
				},
				unhighlight : function(element, errorClass, validClass) {
					$(element).parents('.control-group').removeClass('error');
					$(element).parents('.control-group').addClass('success');
				}
			});
		});
	</script>






	<!-- 
	<div class="container">
		<div class="row">
			<div class="span8">
				<form class="form-horizontal" action="login.html" method="post"
								 id="loginHere" >
					<fieldset>

						<legend>Sing in</legend>

						<div class="control-group">
							<label class="control-label">Login</label>
							<div class="controls">
								<input type="text"  class="input-xlarge" id="login"
									name="login" rel="popover"
									data-content="Enter your login."
									data-original-title="Login">
							</div>
						</div>


						<div class="control-group">
							<label class="control-label">Password</label>
							<div class="controls">
								<input type="text"  class="input-xlarge" id="password"
									name="password" rel="popover"
									data-content="What’s your password?"
									data-original-title="Password"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								<button type="submit" class="btn btn-success">Sing in</button>
							</div>
						</div>

					</fieldset>
				</form>
			</div>
		</div>
	</div>
 -->
	<!-- 
	<table align="center" width="500">

		<tr align="center" height="300">
			<td><form:form action="login.html" method="post"
					commandName="user" class="form-horizontal">
					<div class="control-group">
							<label class="control-label" for="inputLogin">Login</label>
						<div class="controls">
							<form:input type="text" path="login" />
							<font color="red"> <form:errors path="login" /></font>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword">Password</label>
						<div class="controls">
							<form:input type="password" path="password" />
							<font color="red"> <form:errors path="password" />
								</font>
						</div>
					</div>
					<br />
					<div class="control-group">
						<div class="controls">
							<button type="submit" id="button" class="btn">Sign in</button>
						</div>
					</div>
				</form:form></td>
			<td align="justify"><a href="registration.html">Register</a></td>
		</tr>
	</table>
	 -->
</body>
</html>
