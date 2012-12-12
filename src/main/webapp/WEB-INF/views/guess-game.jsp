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
	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="./chatRooms">Milky-way</a>
				<ul class="nav">
					<li class="divider-vertical"></li>
					<li><a href="./goHome">Home</a></li>
					<li><a href="#">Profile</a></li>
					<li><a href="#">TOP-100</a></li>
				</ul>
				<ul class="nav pull-right">
					<li class="divider-vertical"></li>
					<li><a href="./logout">Sign out</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container" id="main">
		<div class="row-fluid">
			<div class="span3 well">
				<h1 id="timer">Time: 0</h1>
				<!-- <h1>Chat</h1> -->
				<ul id="chat-history">
					<li>No messages for now :(</li>
				</ul>
			</div>
			<div class="span7" id="game-container">
				<div id="wrapper">
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
						</div>

						<div id="chat-header">
							<form class="form-inline">
								<h3>
									<input type="text" id="chat-input"
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


			<div class="span3 well">
				<h1 id="title">Draw &amp; Guess</h1>
				<h4>Statistics</h4>
				<ul id="stats">
					<!-- <h3>Player - Total wins</h3> -->
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