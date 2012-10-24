<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

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
		<!-- 
		<h1>Oh, hi doggy.</h1>
		 -->
		 <h1>Oh, hi <c:out value="${user.login}"/></h1>
	</div>
	<div class="container">
		<p><%=new Date()%></p>
		</strong>
		<c:choose>
			<c:when test="${user == null }">
				<p>
					<a href="./loginform.html" class="btn">login</a> <a
						href="./registration.html" class="btn btn-danger">register</a>
				</p>
			</c:when>
			<c:otherwise>
				<p>
					<a href="./monopoly-game.html" class="btn btn-primary">Link to
						game rooms</a>
				</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
