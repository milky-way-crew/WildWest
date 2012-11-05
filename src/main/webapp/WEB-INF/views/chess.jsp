<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chess</title>
<link rel="stylesheet" href="resources/css/chess.css" type="text/css">
<body>
	<div id="#container" >
		<h1 class="info">Information</h1>
		<table id="chess_board" cellpadding="0" cellspacing="0">
			<tbody>
			<tr>
				<td id="00"><img src="./resources/img/unknown.png" class="figure white"></td>
				<td id="01"><img src="./resources/img/unknown.png" class="figure white"></td>
				<td id="02"><img src="./resources/img/unknown.png" class="figure white"></td>
				<td id="03"><img src="./resources/img/unknown.png" class="figure white"></td>
				<td id="04"><img src="./resources/img/unknown.png" class="figure white"></td>
				<td id="05"><img src="./resources/img/unknown.png" class="figure white"></td>
				<td id="06"><img src="./resources/img/unknown.png" class="figure white"></td>
			</tr>
			<tr>
				<td id="10"><img src="./resources/img/unknown.png" class="figure white"></td>
				<td id="11"><img src="./resources/img/unknown.png" class="figure white"></td>
				<td id="12"><img src="./resources/img/unknown.png" class="figure white"></td>
				<td id="13"><img src="./resources/img/unknown.png" class="figure white"></td>
				<td id="14"><img src="./resources/img/unknown.png" class="figure white"></td>
				<td id="15"><img src="./resources/img/unknown.png" class="figure white"></td>
				<td id="16"><img src="./resources/img/unknown.png" class="figure white"></td>
			</tr>
			<tr>
				<td id="20"></td>
				<td id="21"></td>
				<td id="22"></td>
				<td id="23"></td>
				<td id="24"></td>
				<td id="25"></td>
				<td id="26"></td>
			</tr>
			<tr>
				<td id="30"></td>
				<td id="31"></td>
				<td id="32"></td>
				<td id="33"></td>
				<td id="34"></td>
				<td id="35"></td>
				<td id="36"></td>
			<tr>
				<td id="40"></td>
				<td id="41"></td>
				<td id="42"></td>
				<td id="43"></td>
				<td id="44"></td>
				<td id="45"></td>
				<td id="46"></td>
			</tr>
			<tr>
				<td id="50"><img src="./resources/img/unknown.png" class="figure black"></td>
				<td id="51"><img src="./resources/img/unknown.png" class="figure black"></td>
				<td id="52"><img src="./resources/img/unknown.png" class="figure black"></td>
				<td id="53"><img src="./resources/img/unknown.png" class="figure black"></td>
				<td id="54"><img src="./resources/img/unknown.png" class="figure black"></td>
				<td id="55"><img src="./resources/img/unknown.png" class="figure black"></td>
				<td id="56"><img src="./resources/img/unknown.png" class="figure black"></td>
			<tr>
				<td id="60"><img src="./resources/img/unknown.png" class="figure black"></td>
				<td id="61"><img src="./resources/img/unknown.png" class="figure black"></td>
				<td id="62"><img src="./resources/img/unknown.png" class="figure black"></td>
				<td id="63"><img src="./resources/img/unknown.png" class="figure black"></td>
				<td id="64"><img src="./resources/img/unknown.png" class="figure black"></td>
				<td id="65"><img src="./resources/img/unknown.png" class="figure black"></td>
				<td id="66"><img src="./resources/img/unknown.png" class="figure black"></td>
		</tbody></table>
	</div>
	<script type="text/javascript" src="resources/js/jquery.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="resources/js/chess.js"></script>
</body>
</html>