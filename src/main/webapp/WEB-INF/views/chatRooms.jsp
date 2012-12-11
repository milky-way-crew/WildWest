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
	<jsp:include page="navbar.jsp" />
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

	<!-- modal window create room -->
	<jsp:include page="modal.jsp" />
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