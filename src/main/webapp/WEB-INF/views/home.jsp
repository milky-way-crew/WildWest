<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
<title>KaBoom</title>
</head>
<body>
	<div class="hero-unit">
		<h1>Oh, hi doggy.</h1>
	</div>
	<div class="container">
			<p><%=new Date()%></p>
			<strong class="muted">Logined: <%=session.getAttribute("user") != null%>
			<!-- 
			<%=session.getAttribute("user").toString() %>
			 -->
			</strong>
			<p>
				<a href="./loginform.html" class="btn">login</a> <a
					href="./registration.html" class="btn btn-danger">register</a>
			</p>
			
			<p>
				<a href="./chatRooms.html" class="btn btn-primary">Link to game rooms</a>
			</p>

			<p>
				<a href="./chat.html" class="btn btn-primary">Chat</a>
			</p>
	</div>


</body>
</html>