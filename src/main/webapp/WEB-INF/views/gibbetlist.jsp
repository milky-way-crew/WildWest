<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./resources/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet" href="./resources/bootstrap-responsive.css"
	type="text/css">
<title>KaBoom</title>
</head>
<body>
	<div class="hero-unit">
		<h1>
			<c:out value="${test}" />
		</h1>
		<p>
			<a href="./gibbet/create">Create your own server</a>
		</p>
	</div>
	<div class="container">
		<h2>Availiable servers:</h2>
		<c:forEach var="entry" items="${gibbet}">
			<c:out value="Id: ${entry.key}" />
			<p>
				<a
					href="<c:url value="gibbet/connect">  
					<c:param name="idServer" value="${entry.key}"/>  
				</c:url>">Connect</a>
			</p>
		</c:forEach>
		<c:if test="${user == null }">
			<h3>You are not logined</h3>
			<a href="./login" class="btn">login</a>
			<a href="./register" class="btn btn-danger">register</a>
		</c:if>
	</div>
</body>
</html>
