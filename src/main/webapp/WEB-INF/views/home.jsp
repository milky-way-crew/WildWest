<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet" href="resources/bootstrap-responsive.css"
	type="text/css">
<title>KaBoom</title>
<style type="text/css">
body {
	background-image: url('resources/img/chess/chess-bg-1.png'); /*url(resources/img/home-bg.png)*/
}

.hero-unit {
	background-color: rgba(255, 255, 255, 0.7);
}
</style>
</head>
<body>
	<div class="container ">
		<div class="hero-unit">
			<!-- 
		<h1>Oh, hi doggy.</h1>
		 -->
			<h1>
				Oh, hi
				<c:out value="${user.login}" />
				;)
			</h1>
		</div>
		<div class="span6 well">
			<!-- <center> -->
			<img src="resources/img/404.png" alt="">
			<!-- </center> -->
		</div>
		<div class="span4 well">
			<div class="well" id="info">
				<h4>Information about user</h4>
				<table class="table table-hover">
					<tr>
						<td>login</td>
						<td><c:out value="${user.login}" /></td>
					</tr>
					<tr>
						<td>nickname</td>
						<td><c:out value="${user.nickname}" /></td>
					</tr>
					<tr>
						<td>email</td>
						<td><c:out value="${user.email}" /></td>
					</tr>
				</table>
			</div>

			<div class="well">
				<p><%=new Date()%></p>
				<c:choose>
					<c:when test="${user == null }">
						<p>
							<a href="./login" class="btn">login</a> <a href="./register"
								class="btn btn-danger">register</a>
						</p>
					</c:when>
					<c:otherwise>

						<p>
							<a href="./chatRooms" class="btn btn-primary">Link to game
								rooms</a>
						</p>
						<p>
							<a href="./logout" class="btn btn-danger">Logout</a>
						</p>
						<p>
							<a href="./test" class="btn btn-warning">&#9827; Link to
								buggy chat &#9827;</a>
						</p>
						<p>
							<a href="./chess" class="btn btn-primary">&#9829;
								Chess-servers</a>
						</p>
					</c:otherwise>
				</c:choose>
			</div>


		</div>

	</div>
</body>
</html>
