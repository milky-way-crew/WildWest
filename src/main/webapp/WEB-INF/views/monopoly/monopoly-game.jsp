<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Monopoly, the game</title>
	<link href="./resources/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body> 
	<div class="hero-unit">
		<h1 class="info">Web-socket [<c:out value="${user.login}" />]</h1>
	</div>

	<div class="container" align="center">
			<div class="span6 well">
				<button class="btn btn-primary" id="roll">roll</button>
				<button class="btn btn-primary" id="buy">buy</button>
				<button class="btn btn-primary" id="pay">pay</button>
				<button class="btn btn-primary" id="mortage">mortage</button>
				<button class="btn btn-primary" id="unmortage">unmortage</button>
				<button class="btn btn-primary" id="build">build</button>
				<button class="btn btn-primary" id="sell">sell</button>
			</div>
			<div class="span4 well">
				<button class="btn btn-primary" id="done">done</button>
				<button class="btn btn-primary" id="ok">ok</button>
				<button class="btn btn-primary" id="start">start</button>
				<button class="btn btn-primary" id="ready">ready</button>
			</div>
		</div>
	<ul id="chat">
	</ul>
</body>
<script src="resources/js/jquery.js" type="text/javascript"></script>
<script src="resources/js/monopoly/monopoly.js" type="text/javascript"></script>

</html>