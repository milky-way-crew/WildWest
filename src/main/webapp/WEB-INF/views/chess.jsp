<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chess</title>
<link rel="stylesheet" href="resources/css/chess.css" type="text/css">

<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/chess.js"></script>
<!-- 
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
 -->


<body>
	<table id="chess_board" cellpadding="0" cellspacing="0">
		<tbody>
		<tr>
			<td id="00"><a href="#" class="figure rook black ui-draggable">♜</a></td>
			<td id="01"><a href="#" class="figure knight black ui-draggable">♞</a></td>
			<td id="02"><a href="#" class="figure bishop black ui-draggable">♝</a></td>
			<td id="03"><a href="#" class="figure king black ui-draggable">♛</a></td>
			<td id="04"><a href="#" class="figure queen black ui-draggable">♚</a></td>
			<td id="05"><a href="#" class="figure bishop black ui-draggable">♝</a></td>
			<td id="06"><a href="#" class="figure knight black ui-draggable">♞</a></td>
		</tr>
		<tr>
			<td id="10"><a href="#" class="figure pawn black ui-draggable">♟</a></td>
			<td id="11"><a href="#" class="figure pawn black ui-draggable">S</a></td>
			<td id="12"><a href="#" class="figure pawn black ui-draggable">R</a></td>
			<td id="13"><a href="#" class="figure pawn black ui-draggable">P</a></td>
			<td id="14"><a href="#" class="figure pawn black ui-draggable">♟</a></td>
			<td id="15"><a href="#" class="figure pawn black ui-draggable">♟</a></td>
			<td id="16"><a href="#" class="figure pawn black ui-draggable">♟</a></td>
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
			<td id="50"><a href="#" class="figure pawn white ui-draggable">♙</a></td>
			<td id="51"><a href="#" class="figure pawn white ui-draggable">♙</a></td>
			<td id="52"><a href="#" class="figure pawn white ui-draggable">♙</a></td>
			<td id="53"><a href="#" class="figure pawn white ui-draggable">♙</a></td>
			<td id="54"><a href="#" class="figure pawn white ui-draggable">♙</a></td>
			<td id="55"><a href="#" class="figure pawn white ui-draggable">♙</a></td>
			<td id="56"><a href="#" class="figure pawn white ui-draggable">♙</a></td>
		<tr>
			<td id="60"><a href="#" class="figure rook white ui-draggable">♖</a></td>
			<td id="61"><a href="#" class="figure knight white ui-draggable">♘</a></td>
			<td id="62"><a href="#" class="figure bishop white ui-draggable">♗</a></td>
			<td id="63"><a href="#" class="figure king white ui-draggable">♕</a></td>
			<td id="64"><a href="#" class="figure queen white ui-draggable">♔</a></td>
			<td id="65"><a href="#" class="figure bishop white ui-draggable">♗</a></td>
			<td id="66"><a href="#" class="figure knight white ui-draggable">♘</a></td>
	</tbody></table>
</body>
</html>