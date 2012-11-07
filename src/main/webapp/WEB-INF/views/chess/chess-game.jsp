<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
		Chess
	</title>
	<link href="./resources/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="./resources/css/chess.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			<a class="brand" href="#">Blah-blah!</a>
			<ul class="nav">
				<li class="active"><a href="#">Blo-blo-blo</a></li>
				<li><a href="#">Blablabla</a></li>
				<li><a href="#">Blablabla</a></li>
			</ul>
		</div>
	</div>

	
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="offset1 span4 well">
				<div class="menu well" id="before_game" align="center">
					<h3 align="left">Before game:</h3>
					<button id="ready" class="btn btn-large btn-primary">I'm Ready</button>
					or 
					<button id="shuffle" class="btn btn-large btn-success">Shuffle figures</button>
				</div>
				<div id="info" class="well" align="center">
					<h3 align="left">Notification area:</h3>
					<h4 class="info muted">
						Waiting for oponents
					</h4>
				</div>
				<div id="chat_area" class="well">
					<!-- <h>Chat will be here:</h4> -->
					<textarea id="chat" rows="5" cols="20" style="width: 320px;"></textarea>
					<form class="form-search" style="margin-bottom: 0px;">
						<div class="input-append">
							<input class="input-large search-query" id="message"
							placeholder="write your message here.." type="text">
							<button class="btn btn-info" id="send" type="button">Send</button>
						</div>
					</form>
				</div>


				<a href="./chess/exit" class="btn btn-large btn-danger btn-block" >&ltexit game&gt;</a>
			</div>
			<div class="span6">
				<div id="chess" class="well" align="center">
					<table id="chess_board" cellpadding="0" cellspacing="0">
						<tbody>
							<c:forEach var="i" begin="0" end="${sizeX}">  
							<tr>
								<c:forEach var="j" begin="0" end="${sizeY}">
								<td id="<c:out value="${i}${j}"/>">
								<img class="figure">
							</td>
						</c:forEach>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>
</div>
</div>



<script type="text/javascript" src="./resources/js/jquery.js"></script>
<script type="text/javascript" src="./resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="./resources/js/bootstrap-modal.js"></script>
<script type="text/javascript" src="./resources/js/bootbox.min.js"></script>
<script type="text/javascript" src="./resources/js/chess.js"></script>
</body>
</html/>