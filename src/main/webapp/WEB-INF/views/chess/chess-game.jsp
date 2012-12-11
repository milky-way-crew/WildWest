<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chess, the game</title>
<!-- <link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Anaheim">
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Lobster+Two&effect=shadow-multiple">
	-->
<link href="./resources/css/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="./resources/css/chess.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<div class="container" id="main">
		<div class="span4 menu" id="sidebar">
			<div class="row well">

				<div id="info" class="menu-entry well" align="center">
					<h3 align="left" class="font-effect-shadow-multiple">Notification
						area:</h3>
					<h4 class="info muted">Move : undefined</h4>
				</div>
				<a href="./chess/exit" class="btn btn-large btn-danger btn-block">leave
					game</a>
				<div class="menu-entry well" id="before_game" align="center">
					<div id="bf">
						<button id="ready" class="btn btn btn-primary">Ready</button>
						or
						<button id="shuffle" class="btn btn btn-success">Shuffle
							figures?</button>
					</div>
					<img src="./resources/img/chess/dazz2.gif"
						alt="Omg, failed to load image :(" style="margin-top: 20px">

				</div>
			</div>
		</div>

		<div class="span8 container-fluid" id="main-container">
			<div class="row-fluid">
				<div id="chess" class="" align="center">


					<table id="chess_board" cellpadding="0" cellspacing="0">
						<tbody>
							<c:forEach var="i" begin="0" end="${sizeX}">
								<tr>
									<c:forEach var="j" begin="0" end="${sizeY}">
										<td id="<c:out value="${i}${j}"/>"><img class="figure">
										</td>
									</c:forEach>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="span4 menu" id="chat">
			<div class="row well">
				<div id="chat-header" class="well" align="center">
					<h3 align="left" class="font-effect-shadow-multiple">Chat-history:</h3>
				</div>
				<div id="chat-history" class="menu-entry well">
					<ul>
						<li><h4 class="message">No messages for now</h4></li>
					</ul>
				</div>
				<form class="form-inline">
					<input id="chat-input" type="text" placeholder="Write a message..">
					<button id="send" class="btn btn-primary">
						<i class="icon-envelope icon-white"></i> Send
					</button>
				</form>
			</div>
		</div>
	</div>

	<div id="fin" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="fin-label">Congradulations, you win!</h3>
		</div>
		<div class="modal-body">
			<center>
				<!-- <p>Congradulations, you win!</p> -->
				<img src="" alt="win">
				<p>Redirecting to home in 7seconds</p>
			</center>
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary" data-dismiss="modal"
				aria-hidden="true">Close</button>
		</div>
	</div>

	<script type="text/javascript" src="./resources/js/jquery.js"></script>
	<script type="text/javascript" src="./resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="./resources/js/bootstrap-modal.js"></script>
	<script type="text/javascript" src="./resources/js/bootbox.min.js"></script>
	<script type="text/javascript" src="./resources/js/chat.js"></script>
	<script type="text/javascript" src="./resources/js/chess.js"></script>
</body>
</html>
