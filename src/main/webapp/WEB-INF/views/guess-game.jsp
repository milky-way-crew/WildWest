<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang=en>
<head>
	<meta charset=utf-8>
	<title>WebSockets demo for HTML5 Games Development: A Beginner's Guide</title>
	<meta name="description" content="This is a WebSockets demo for the book HTML5 Games Development: A Beginner's Guide by Makzan">
	<meta name="author" content="Makzan">
	<style>
		canvas{position:relative;}
	</style>
	<link href='http://fonts.googleapis.com/css?family=Cabin+Sketch:bold' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" media="all" href="resources/css/guess.css">
</head>
<body>	
	<section id="game">
		<h1>Draw & Guess</h1>
		<canvas id='drawing-pad' width='500' height='400'>
		</canvas>
		
		<ul id="chat-history"></ul>
		Chat or Guess:
		<input type='text' id="chat-input">
		<input type='button' value="Send" id="send">
		<input type='button' value="Restart" id="restart">
	</section>
<script src="resources/js/jquery.js" type="text/javascript"></script>
<script src="resources/js/guess/guess.js" type="text/javascript"></script>
</body>
</html>