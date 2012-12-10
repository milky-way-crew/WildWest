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
<link href='http://fonts.googleapis.com/css?family=Scada'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Margarine'
	rel='stylesheet' type='text/css'>
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
				</a> <a class="brand" href="./chatRooms">Milky-way</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="./goHome">Home</a></li>
						<li><a href="#">Profile</a></li>
						<li><a href="#">TOP-100</a></li>
						<li><a href="./logout">Sign out</a></li>
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
				<div id="title" class="well">
					<h1>Chat</h1>
					<div id="chatBox"></div>
					<div id="invMenu"></div>
					<form id="chatAction" name="message" action="">
						<input type="text" id="usermsg"
							placeholder="Enter your message here..."> <a
							class="btn btn-primary btn-medium" id="send">Send <i
							class="icon-white icon-envelope"></i></a>
						<div id="chatOption" class="btn-group"></div>
					</form>
				</div>
			</div>

			<!-- list -->
			<div class="span4">
				<div id="listTitle" class="well"></div>
				<div id="listBox" class="well">
					<table id="list" class="table table-hover">
					</table>
				</div>
				<div id="menu" class="well"></div>
			</div>
			<!-- list -->

		</div>
	</div>
	<!-- world chat and room list -->

	<!-- invite -->
	<div class="container">
		<div id="invite" class="modal" style="display: none;">
			<img id="door" src="resources/img/door.gif">
			<div id="invHeader" class="modal-header">
				<h1>Invitation</h1>
			</div>
			<div id="invBody" class="modal-body">
				<p></p>
			</div>
			<div class="modal-footer">
				<a id="accept" href="#" class="btn btn-success" data-dismiss="modal">Accept
					<i class="icon-white icon-share-alt"></i>
				</a> <a id="reject" class="btn btn-medium btn-warning"
					data-dismiss="modal">Reject</a>
			</div>
		</div>
	</div>
	<!-- invite -->

	<!-- modal window create room -->
	<div class="container">
		<div id="createRoomModal" class="modal" style="display: none;">
			<div class="modal-header">
				<h1>Create room</h1>
			</div>
			<div class="modal-body">
				<p></p>
				<input type="text" id="roomName" size=80
					placeholder="Enter room name...">
				<div class="btn-group">
					<a id="info" class="btn dropdown-toggle btn-info"
						data-toggle="dropdown" href="#">Choose Game <span
						class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a id="Monopoly" tabindex="-1" href="#">Monopoly</a></li>
						<li><a id="Chess" tabindex="-1" href="#">Chess</a></li>
						<li><a id="DrawAndGuess" tabindex="-1" href="#">Draw and
								Guess</a></li>
						<li><a id="Gibbet" tabindex="-1" href="#">Gibbet</a></li>
					</ul>
				</div>
			</div>
			<div class="modal-footer">
				<a id="createRoom" href="#" class="btn btn-success"
					data-dismiss="modal">Create <i class="icon-white icon-edit"></i>
				</a> <a id="closeModal" class="btn btn-medium btn-warning"
					data-dismiss="modal">Close</a>
			</div>
		</div>
	</div>
	<!-- modal window create room -->
	<script type="text/javascript" src="resources/js/jquery.js"></script>
	<script type="text/javascript" src="resources/js/chat-rooms.js"></script>
	<script type="text/javascript" src="resources/js/room.js"></script>
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