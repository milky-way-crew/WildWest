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
</head>
<body>
	<div id="navbar" class="navbar navbar-inverse">
		<div class="navbar-inner">
			<a class="brand" href="./home">#Home</a>
			<ul class="nav">
				<li><a href="./chess">Guess&amp;Draw rooms</a></li>
			</ul>
			<ul class="nav pull-right">
				<li><a href="./logout">Logout</a></li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<h1>Chat</h1>
				<ul id="chat-history">
					<li>No messages for now :(</li>
				</ul>
			</div>
			<div class="span7" id="game-container">
				<div id="wrapper">
					<div style="float: left; width: 30%">
						<h1 id="timer">Time: 0</h1>
					</div>
					<div style="float: right; width: 70%;">
						<h1 id="title">Draw &amp; Guess</h1>
					</div>

					<section id="game">
						<canvas id="drawing-pad" width="500" height="400"></canvas>
						<div id="drawing-pallete">
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
						</div>

						<div id="chat-header">
							<form class="form-inline">
								<h2>
									Chat or Guess: <input type="text" id="chat-input"
										placeholder="I guess it..">
									<button class="btn btn-primary" type="button" id="send">
										<i class="icon-envelope icon-white"></i> Send
									</button>
								</h2>
							</form>

						</div>
					</section>
				</div>
			</div>

			<div class="span3">
				<h1>Anybody there?</h1>
				<ul id="stats">
					<li>Only you :)</li>
				</ul>
			</div>
		</div>
	</div>

	<script src="resources/js/jquery.js" type="text/javascript"></script>
	<script src="resources/js/guess/guess-engine.js" type="text/javascript"></script>
</body>
</html>