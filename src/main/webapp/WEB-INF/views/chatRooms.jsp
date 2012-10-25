<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="css/bootstrap-responsive.css">
<title>Welcome!</title>
</head>
<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> </a> <a class="brand" href="#">Monopoly
					Game</a>
				<div class="nav-collapse">
					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#">Profile</a></li>
						<li><a href="#">TOP-100</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle">
								Log out</a></li>
					</ul>
				</div>

			</div>
		</div>
	</div>
	<div class="container">
		<div class="row-fluid">
			<div class="span8">
				<div class="well">

					<div class="row">
						<h2>Article Title.</h2>
						<p>Article Description.</p>
						<p>
							<a class="btn" href="#">Read More »</a>
						</p>
						<form:form>
							<textarea class="input-xlarge" id="chatMessage"
								name="chatMessage" rows=20 cols=150></textarea>

						</form:form>
					</div>
				</div>
			</div>
			<div class="span4">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header">About Me</li>
						<li>Author Data</li>
						<li class="nav-header">Recent Posts</li>
						<li>link1</li>
						<li>link1</li>
						<li>link1</li>
						<li class="nav-header">Advertisements</li>
						<li>ad1</li>
						<li>ad2</li>
					</ul>
				</div>
			</div>

		</div>
		<div class="span16">
			<form:form>
				<textarea class="input-xlarge" id="message" name="message" rows=5
					cols=170>Enter your message</textarea>
				<button type="submit" class="btn">Send</button>
			</form:form>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap-transition.js"></script>
	<script type="text/javascript" src="js/bootstrap-alert.js"></script>
	<script type="text/javascript" src="js/bootstrap-modal.js"></script>
	<script type="text/javascript" src="js/bootstrap-dropdown.js"></script>
	<script type="text/javascript" src="js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="js/bootstrap-tab.js"></script>
	<script type="text/javascript" src="js/bootstrap-tooltip.js"></script>
	<script type="text/javascript" src="js/bootstrap-popover.js"></script>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	<script type="text/javascript" src="js/bootstrap-button.js"></script>
	<script type="text/javascript" src="js/bootstrap-collapse.js"></script>
	<script type="text/javascript" src="js/bootstrap-carousel.js"></script>
	<script type="text/javascript" src="js/bootstrap-typeahead.js"></script>
</body>
</html>