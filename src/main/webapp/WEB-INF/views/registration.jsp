<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" href="css/bootstarp.css" type="text/css">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span8">
				<form:form class="form-horizontal" id="registerHere"
					action="registration.html" method="post" commandName="user">
					<fieldset>

						<legend>Registration</legend>

						<div class="control-group">
							<label class="control-label">Login</label>
							<div class="controls">
								<form:input type="text" path="login" class="input-xlarge" id="login"
									name="login" rel="popover"
									data-content="Enter your login."
									data-original-title="Login"/>
							</div>
						</div>


						<div class="control-group">
							<label class="control-label">Nickname</label>
							<div class="controls">
								<form:input type="text" path="nickname" class="input-xlarge" id="nickname"
									name="nickname" rel="popover"
									data-content="What’s your nickName?"
									data-original-title="NickName"/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">Email</label>
							<div class="controls">
								<form:input type="text" path="email" class="input-xlarge" id="email"
									name="email" rel="popover"
									data-content="What’s your email?"
									data-original-title="Email"/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">Password</label>
							<div class="controls">
								<form:input type="password" path="password" class="input-xlarge" id="password"
									name="password" rel="popover"
									data-content="What’s your password?"
									data-original-title="Password"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								<button type="submit" class="btn btn-success">Yes</button>
							</div>
						</div>

					</fieldset>
					</form:form>
			</div>
		</div>
	</div>


	<!--  <table align="center">
		<tr>
			<td align="center"></td>
		</tr>
		<tr>
			<td>
				<table align="center" width="500">
					<tr>
						<td align="center"></td>
					</tr>
					<tr align="center" height="300">
						<td><form:form action="registration.html" method="post"
								commandName="user">
								<h2 align="center">Login</h2>
								<form:input type="text" path="login" />
								<font color="red"><br /> <form:errors path="login" /><br /></font>

								<h2 align="center">Email</h2>
								<form:input type="text" path="email" />
								<font color="red"><br /> <form:errors path="email" /><br /></font>

								<h2 align="center">Nickname</h2>
								<form:input type="text" path="nickname" />
								<font color="red"><br /> <form:errors path="nickname" /><br /></font>

								<h2 align="center">Password</h2>
								<form:input type="password" path="password" />
								<font color="red"><br /> <form:errors path="password" /><br /></font>

								<br />
								<button type="submit" id="button">Log in</button>
							</form:form></td>
					</tr>
					
				</table>
			</td>
		</tr>
	</table>
	-->
</body>
</html>
