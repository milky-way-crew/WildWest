<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<h3>Registration Page</h3>
	<form action="registration.html" method="post">
		<p>
			<label>Enter User Name <input type=text name="userName"
				size=20></label>
		</p>
		<p>
			<label>Enter NickName <input type=text name="nickName"
				size=20></label>
		</p>
		<p>
			<label>Enter Email <input type=text name="email" size=20></label>
		</p>
		<p>
			<label>Enter Password <input type=text name="password"
				size=20></label>
		</p>
		<p>
			<input type="submit" value="Register" name="register">
		</p>
	</form>
</body>
</html>