<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login form</title>
</head>
<body>
	<h3>Login Form</h3>
	<form action="loginform.html" method="post">
		<p>
			Enter NickName <input type=text name="nickName" size=20>
		</p>
		<p>
			Enter Password <input type=text name="password" size=20>
		</p>
		<p>
			<input type="submit" value="Login" name="login">
		</p>
	</form>
</body>
</html>