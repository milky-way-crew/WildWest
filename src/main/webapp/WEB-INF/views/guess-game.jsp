<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=en>
<head>
	<meta charset=utf-8>
	<title>Guess, the game</title>
	<style>
		canvas{position:relative;}
	</style>
	<!-- 
	<link href='http://fonts.googleapis.com/css?family=Cabin+Sketch:bold' rel='stylesheet' type='text/css'>
	 -->
	<link rel="stylesheet" type="text/css" media="all" href="resources/css/guess.css">
</head>
<body>	
	<section id="game">
		<h1>Draw & Guess</h1>
		<canvas id='drawing-pad' width='500' height='400'>
		</canvas>
		<div id="chat-header">
			Chat or Guess:
			<input type='text' id="chat-input">
			<input type='button' value="Send" id="send">
			<input type='button' value="Restart" id="restart">
			<input type='button' value="Clear" id="clear">
		</div>
		<ul id="chat-history"></ul>
	</section>
<script src="resources/js/jquery.js" type="text/javascript"></script>
<script src="resources/js/guess/guess-engine.js" type="text/javascript"></script>
</body>
</html>