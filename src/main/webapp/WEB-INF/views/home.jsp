<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="resources/bootstrap-responsive.css" type="text/css">
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
		<c:choose>
			<c:when test="${user == null }">
				<p>
					<a href="./login" class="btn">login</a> <a
						href="./register" class="btn btn-danger">register</a>
				</p>
			</c:when>
			<c:otherwise>
				<p>
					<a href="./chatRooms" class="btn btn-primary">Link to
						game rooms</a>
				</p>
								<p>
					<a href="./logout" class="btn btn-danger">Logout</a>
				</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
