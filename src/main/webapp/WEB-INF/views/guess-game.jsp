<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=en>
<head>
<meta charset=utf-8>
<title>Guess, the game</title>
<link href='http://fonts.googleapis.com/css?family=Neucha'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" media="all"
	href="resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" media="all"
	href="resources/css/guess.css">
<link href="resources/js/colorpicker/css/colorpicker.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container" id="main">
		<div class="row-fluid">
			<div class="span3">
				<h1>Chat</h1>
				<ul id="chat-history">
					<li>No messages for now :(</li>
				</ul>
			</div>
			<div class="span7" id="game-container">
				<div id="wrapper">
					<div style="float: left; width: 40%">
						<h1 id="timer">Time: 0</h1>
					</div>
					<div style="float: right; width: 60%;">
						<h1 id="title">Draw &amp; Guess</h1>
					</div>

					<section id="game">
						<canvas id="drawing-pad" width="500" height="400"></canvas>
						<div id="drawing-pallete">
							<!--  -->
							<button class="btn btn-small btn-info" type="button" id="pencil">
								<i class="icon-pencil"></i> Pencil
							</button>
							<button class="btn btn-small btn-info" type="button" id="eraser">
								<i class="icon-remove"></i> Eraser
							</button>

							<button class="btn btn-small btn-info" type="button" id="clear">
								<i class="icon-fast-backward"></i> Clear
							</button>

							<button class="btn btn-small btn-success" type="button"
								id="restart">
								<i class="icon-retweet"></i> I want new game!
							</button>
							<!-- color-picker -->
							<a href="#" class="btn btn-small btn-info " id="cp"
								data-color-format="hex" data-color="rgb(255, 255, 255)">Color</a>
							<!-- 
							
							<div class="input-append color" data-color="rgb(255, 146, 180)"
								data-color-format="hex" id="cp">
								<input class="span2" value="rgb(60,115,27)" readonly="readonly"
									type="text"> <span class="add-on">
									<i style="background-color: rgb(60, 115, 27);"></i>
									</span>
							</div>
							 -->

						</div>

						<div id="chat-header">
							<form class="form-inline">
								<h3>
									Chat or Guess: <input type="text" id="chat-input"
										placeholder="I guess it..">
									<button class="btn btn-primary" type="button" id="send">
										<i class="icon-envelope icon-white"></i> Send
									</button>
								</h3>
							</form>

						</div>
					</section>
				</div>
			</div>


			<div class="span3">
				<h1>Anybody there?</h1>
				<ul id="stats">
					<h3>Player - Total wins</h3>
				</ul>
			</div>
		</div>
	</div>


	<script src="resources/js/jquery.js" type="text/javascript"></script>
	<script src="resources/js/colorpicker/js/bootstrap-colorpicker.js"
		type="text/javascript"></script>
	<script src="resources/js/guess/guess-engine.js" type="text/javascript"></script>

</body>
</html>