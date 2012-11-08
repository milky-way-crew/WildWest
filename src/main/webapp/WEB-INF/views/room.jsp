<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/bootstrap-responsive.css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-responsive.min.css">
<link type="text/css" rel="stylesheet"
	href="resources/css/chatroom_style.css" />
<title>Welcome!</title>
</head>
<body>

	<!-- top panel -->
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">MONOPOLY</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#">Profile</a></li>
						<li><a href="#">TOP-100</a></li>
						<li><a href="#">Sign out</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<!-- top panel -->

	<!-- world chat and room list -->
	<div class="container">
		<p></p>
		<div class="row-fluid">
			<div class="span8">
				<div class="well">
					<h1>Room</h1>
					<div id="chatBox"></div>
					<form name="message" action="">
						<input name="usermsg" type=text id="usermsg" size="100"> <a
							class="btn btn-primary btn-medium">Send</a>
					</form>
				</div>
			</div>

			<!-- room list -->
			<div class="span4">
				<div class="well sidebar-nav">
					<h2>User's list</h2>
					<table border=2 cellspacing=3 cellpadding=3 align="center">
						<tr>
							<td width=175><b>Nickname</b></td>
							<td width=75><b>Status</b></td>
						</tr>
					</table>
					<form name="userAction" action="">
						<a class="btn btn-primary btn-medium" id="start">Start</a> <br>
						<br> <a class="btn btn-primary btn-medium" id="ready">Ready</a>
						<br> <br> <a class="btn btn-primary btn-medium"
							id="exit">Exit</a>
					</form>
				</div>
			</div>
			<!-- room list -->

		</div>
	</div>
	<!-- world chat and room list -->

	<script type="text/javascript" src="resources/js/rooms/createRoom.js"></script>
	<script type="text/javascript" src="resources/js/jquery.js"></script>
	<script type="text/javascript"
		src="resources/js/bootstrap-transition.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-alert.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-modal.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-dropdown.js"></script>
	<script type="text/javascript"
		src="resources/js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-tab.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-tooltip.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-popover.js"></script>
	<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-button.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-collapse.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-carousel.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.js"></script>
	<script type="text/javascript"
		src="resources/js/bootstrap-typeahead.js"></script>
</body>
</html>