<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=en>
<head>
<meta charset=utf-8>
<title>Guess, the game</title>
<style>
canvas { /*position: relative;*/
	
}
</style>
<link rel="stylesheet" type="text/css" media="all"
	href="resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" media="all"
	href="resources/css/guess.css">
</head>
<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<h1>Chat</h1>
				<ul id="chat-history">
					<li>Lorem Ipsum</li>
					<li>Lorem Ipsum</li>
					<li>Lorem Ipsum</li>
					<li>Lorem Ipsum</li>
				</ul>
			</div>
			<div class="span7" id="game-container">
				<div id="wrapper">
					<div style="float: left; width: 30%">
						<h1 id="timer">Timer: 0</h1>
					</div>
					<div style="float: right; width: 70%;">
						<h1 id="title">Draw &amp; Guess</h1>
					</div>

					<section id="game">
						<canvas id="drawing-pad" width="500" height="400"></canvas>
						<!-- 
						<div id="drawing-pallete" class="btn-group"
							data-toggle="buttons-radio">
							Draw pallete:
							<button class="btn btn-small btn-info" type="button" id="pencil">
								<i class="icon-pencil"></i> Pencil
							</button>
							<button class="btn btn-small btn-info" type="button" id="eraser">
								<i class="icon-remove"></i> Eraser
							</button>

							<button class="btn btn-small btn-info" type="button" id="clear">
								<i class="icon-fast-backward"></i> Clear
							</button>
						</div>
					 -->
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
								<i class="icon-retweet"></i> Start new game
							</button>
						</div>

						<div id="chat-header">
							<form class="form-inline">
								<h2>
									Chat or Guess: <input type="text" id="chat-input"
										placeholder="write funky messages here..">
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
				<h1>Statistics</h1>
				<ul id="stats">
					<li>Lorem Ipsum</li>
					<li>Lorem Ipsum</li>
					<li>Lorem Ipsum</li>
					<li>Lorem Ipsum</li>
					<li>Lorem Ipsum</li>
				</ul>
			</div>
		</div>
	</div>


	<!-- 
	<div class="btn-group" data-toggle="buttons-radio">
		<button type="button" class="btn btn-info" id="pencil">
			<i class="icon-pencil"></i> Pencil
		</button>
		<button type="button" class="btn btn-info" id="eraser">Eraser</button>
		<button class="btn btn-primary" type="button" id="clear">Clear</button>
		<button class="btn btn-info" type="button" id="restart">
			<i class="icon-retweet"></i> Restart
		</button>
	</div>
	 -->
	<!-- TEST  
	<section id="game">
		<h1>Draw & Guess</h1>
		<h2 id='timer'>Timer: 0</h2>
		<canvas id='drawing-pad' width='500' height='400'>
		</canvas>
		<div id="chat-header">
			Chat or Guess: <input type='text' id="chat-input"> <input
				type='button' value="Send" id="send"> <input type='button'
				value="Restart" id="restart"> <input type='button'
				value="Clear" id="clear">

		</div>
		<ul id="chat-history"></ul>
	</section>
	-->
	<script src="resources/js/jquery.js" type="text/javascript"></script>
	<script src="resources/js/guess/guess-engine.js" type="text/javascript"></script>
</body>
</html>