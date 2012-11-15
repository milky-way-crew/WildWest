<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=en>
<head>
<meta charset=utf-8>
<title>Guess, the game</title>
<style>
canvas {
	position: relative;
}
</style>
<link rel="stylesheet" type="text/css" media="all"
	href="resources/css/guess.css">
</head>
<body>

	<div id="wrapper">
		<div style="float: left; width: 30%">
			<h1 id="timer">Timer: 0</h1>
		</div>
		<div style="float: right; width: 70%;">
			<h1 id="title">Draw &amp; Guess</h1>
		</div>
		<section id="game">
			<canvas id="drawing-pad" width="500" height="400"></canvas>
			<div id="chat-header">
				Chat or Guess: <input type="text" id="chat-input"> <input
					type="button" value="Send" id="send"> <input type="button"
					value="Restart" id="restart"> <input type="button"
					value="Clear" id="clear">
			</div>
			<ul id="chat-history"></ul>
		</section>
	</div>


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