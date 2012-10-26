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
<title>Welcome!</title>
</head>
<body>
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

	<div class="container">
		<h1>Chat</h1>
		<p></p>
		<div class="row-fluid">
			<div class="span8">
				<div class="well">
					<textarea id="message" name="message" class="input-xxlarge"
						rows="20">Enter your message</textarea>
				</div>
			</div>
			<div class="span4">
				<div class="well sidebar-nav">
					<h2>Rooms</h2>
					<div class="control-group select optional">
						<label class="select optional control-label"
							for="article_category"> Room's list</label>
						<div class="controls">
							<form name="roomsForm" action="chatRooms.html" method="post">
								<select id="rooms" name="rooms">
								</select> <br>Enter room's name <br> <input type='text'
									value='' name='roomName' id='roomName' class="input-xlarge" />
							</form>
						</div>
					</div>
					<textarea id="listUser" name="listUser" class="input-xlarge"
						rows="6"></textarea>
					<button type="submit" class="btn" name="createRoom" id="createRoom"
						onClick="createOption()">Create room</button>
					<button type="submit" class="btn">Join to room</button>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span16">
					<div class="well sidebar-nav">
						<textarea id="message" name="message" class="input-xxlarge"
							rows="3" cols="150">Enter your message</textarea>
						<button type="submit" class="btn">Send</button>
					</div>
				</div>
			</div>
		</div>

	</div>

	<script type="text/javascript"
		src="resources/js/rooma/createListrooms.js"></script>
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
	<script type="text/javascript"
		src="resources/js/bootstrap-typeahead.js">
		
	</script>

</body>
</html>