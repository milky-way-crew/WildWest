<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<h3>Registration Page</h3>
	<table align="center">
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
						<td><form:form action=".html" method="post"
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
</body>
</html>