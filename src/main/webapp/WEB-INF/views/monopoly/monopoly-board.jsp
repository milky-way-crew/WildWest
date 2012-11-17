<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Monopoly</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="apple-touch-icon" href="/img/apple-touch-icon.png">
<link href="resources/images/favicon.ico" rel="shortcut icon" />



<link rel="stylesheet" href="resources/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet" href="resources/css/bootstrap-responsive.css">

<link type="text/css" rel="stylesheet"
	href="resources/css/chatroom_style.css" />

<link rel="stylesheet" href="resources/css/gameBoard.css">
<link rel="stylesheet" href="resources/css/jquery-ui-1.9.1.custom.css">



<script type="text/javascript" src="resources/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui-1.9.1.custom.js"></script>
<script src="resources/js/monopoly/monopoly-animate.js" type="text/javascript"></script>
<script src="resources/js/monopoly/monopoly.js" type="text/javascript"></script>


</head>

<body bgcolor="#CCE7D0">

	<div id="board">

		<table id="table" border="1" rules="all">

			<tr>
				<td class="northwest" id="cell21"></td>
				<td class="south_north" id="cell22"><div class="cityPosition">
						<div id="ownerCell22" class="ownerNorth"></div>
					</div></td>
				<td class="south_north" id="cell23"></td>
				<td class="south_north" id="cell24"><div class="cityPosition">
						<div id="ownerCell24" class="ownerNorth"></div>
					</div></td>
				<td class="south_north" id="cell25"><div class="cityPosition">
						<div id="ownerCell25" class="ownerNorth"></div>
					</div></td>
				<td class="south_north" id="cell26"><div class="cityPosition">
						<div id="ownerCell26" class="ownerNorth"></div>
					</div></td>
				<td class="south_north" id="cell27"><div class="cityPosition">
						<div id="ownerCell27" class="ownerNorth"></div>
					</div></td>
				<td class="south_north" id="cell28"><div class="cityPosition">
						<div id="ownerCell28" class="ownerNorth"></div>
					</div></td>
				<td class="south_north" id="cell29"></td>
				<td class="south_north" id="cell30"><div class="cityPosition">
						<div id="ownerCell30" class="ownerNorth"></div>
					</div></td>
				<td class="northeast" id="cell31"></td>
			</tr>

			<tr>
				<td class="west_east" id="cell20"><div class="cityPosition">
						<div id="ownerCell20" class="ownerWest"></div>
					</div></td>
				<td colspan="9" rowspan="9">

					<div id="roolDice">
						<img id="diceImg1" src="resources/img/board/die1.gif" width="50px"
							height="50px"> </img> <img id="diceImg2"
							src="resources/img/board/die1.gif" width="50px" height="50px">
						</img>

					</div>


				</td>
				<td class="west_east" id="cell32"><div class="cityPosition">
						<div id="ownerCell32" class="ownerEast"></div>
					</div></td>
			</tr>

			<tr>
				<td class="west_east" id="cell19"><div class="cityPosition">
						<div id="ownerCell19" class="ownerWest"></div>
					</div></td>
				<td class="west_east" id="cell33"><div class="cityPosition">
						<div id="ownerCell33" class="ownerEast"></div>
					</div></td>
			</tr>

			<tr>
				<td class="west_east" id="cell18"></td>
				<td class="west_east" id="cell34"></td>
			</tr>

			<tr>
				<td class="west_east" id="cell17"><div class="cityPosition">
						<div id="ownerCell17" class="ownerWest"></div>
					</div></td>
				<td class="west_east" id="cell35"><div class="cityPosition">
						<div id="ownerCell35" class="ownerEast"></div>
					</div></td>
			</tr>

			<tr>
				<td class="west_east" id="cell16"><div class="cityPosition">
						<div id="ownerCell16" class="ownerWest"></div>
					</div></td>
				<td class="west_east" id="cell36"><div class="cityPosition">
						<div id="ownerCell36" class="ownerEast"></div>
					</div></td>
			</tr>

			<tr>
				<td class="west_east" id="cell15"><div class="cityPosition">
						<div id="ownerCell15" class="ownerWest"></div>
					</div></td>
				<td class="west_east" id="cell37"></td>
			</tr>

			<tr>
				<td class="west_east" id="cell14"><div class="cityPosition">
						<div id="ownerCell14" class="ownerWest"></div>
					</div></td>
				<td class="west_east" id="cell38"><div class="cityPosition">
						<div id="ownerCell38" class="ownerEast"></div>
					</div></td>
			</tr>

			<tr>
				<td class="west_east" id="cell13"></td>
				<td class="west_east" id="cell39"></td>
			</tr>

			<tr>
				<td class="west_east" id="cell12"><div class="cityPosition">
						<div id="ownerCell12" class="ownerWest"></div>
					</div></td>
				<td class="west_east" id="cell40"><div class="cityPosition">
						<div id="ownerCell40" class="ownerEast"></div>
					</div></td>
			</tr>

			<tr>
				<td class="southwest" id="cell11"></td>
				<td class="south_north" id="cell10"><div class="cityPosition">
						<div id="ownerCell10" class="ownerSouth"></div>
					</div></td>
				<td class="south_north" id="cell9"><div class="cityPosition">
						<div id="ownerCell9" class="ownerSouth"></div>
					</div></td>
				<td class="south_north" id="cell8"></td>
				<td class="south_north" id="cell7"><div class="cityPosition">
						<div id="ownerCell7" class="ownerSouth"></div>
					</div></td>
				<td class="south_north" id="cell6"><div class="cityPosition">
						<div id="ownerCell6" class="ownerSouth"></div>
					</div></td>
				<td class="south_north" id="cell5"></td>
				<td class="south_north" id="cell4"><div class="cityPosition">
						<div id="ownerCell4" class="ownerSouth"></div>
					</div></td>
				<td class="south_north" id="cell3"></td>
				<td class="south_north" id="cell2"><div class="cityPosition">
						<div id="ownerCell2" class="ownerSouth"></div>
					</div></td>

				<td class="southeast" id="cell1">

					<div id="divPlayer1" style="position: relative;">
						<img id="player1" src="resources/img/board/player-1.png"
							style="position: absolute; left: 20%; top: +30%;"></img>
					</div>
					<div id="divPlayer2" style="position: relative;">
						<img id="player2" src="resources/img/board/player-2.png"
							style="position: absolute; left: 40%; top: -65%;"></img>
					</div>
					<div id="divPlayer3" style="position: relative;">
						<img id="player3" src="resources/img/board/player-3.png"
							style="position: absolute; left: 20%; top: -20%;"></img>
					</div>
					<div id="divPlayer4" style="position: relative;">
						<img id="player4" src="resources/img/board/player-4.png"
							style="position: absolute; left: 40%; top: -120%;"></img>
					</div>
				</td>
			</tr>

		</table>

		<!-- Block about player's information -->
		<div id="playerInfo">
			<div id="accordion">
				<h3>Player1</h3>
				<div>

					<table id="firstInfoTable" width="60px" height="125px">
						<tr>
							<td class="city" id="brownCity1"></td>
							<td class="city" id="brownCity2"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="city" id="azureCity1"></td>
							<td class="city" id="azureCity2"></td>
							<td class="city" id="azureCity3"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="city" id="violetCity1"></td>
							<td class="city" id="violetCity2"></td>
							<td class="city" id="violetCity3"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="city" id="orangeCity1"></td>
							<td class="city" id="orangeCity2"></td>
							<td class="city" id="orangeCity3"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="city" id="redCity1"></td>
							<td class="city" id="redCity2"></td>
							<td class="city" id="redCity3"></td>
						</tr>
					</table>

					<table id="secondInfoTable" width="80px" height="100px">
						<tr>
							<td class="city" id="yellowCity1"></td>
							<td class="city" id="yellowCity2"></td>
							<td class="city" id="yellowCity3"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="city" id="greenCity1"></td>
							<td class="city" id="greenCity2"></td>
							<td class="city" id="greenCity3"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="city" id="blueCity1"></td>
							<td class="city" id="blueCity2"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="city" id="grayCity1"></td>
							<td class="city" id="grayCity2"></td>
							<td class="city" id="grayCity3"></td>
							<td class="city" id="grayCity4"></td>
						</tr>
					</table>

				</div>
				<h3>Player2</h3>
				<div>
					<p>Nothing...</p>
				</div>
				<h3>Player3</h3>
				<div>
					<p>Nothing...</p>
					<ul>
						<li>q</li>
						<li>List item two</li>
						<li>List item three</li>
					</ul>
				</div>
				<h3>Player4</h3>
				<div>

					<p>Nothing...</p>
				</div>
			</div>

		</div>


		<!-- Create buttons -->
		<div class="playerButton">
			<input id="buyButton" type="submit" value="BUY"> <input
				type="submit" value="MORTAGE" onClick="alert('Mortage this city!')">
			<input type="submit" value="UNMORTAGE"
				onClick="alert('Unmortage this city!')"> <input
				type="submit" value="BUILD" onClick="alert('Build!')"> <input
				type="submit" value="Sell" onClick="alert('Sell')"> <input
				id="diceButton" type="submit" value="RollDice">
		</div>


		<div id="upChat">
		
		<!-- TEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEST -->
			<div class="container" align="center">
			<div class="span6 well" id="controls">
				<button class="btn btn-primary" id="roll">roll</button>
				<button class="btn btn-primary" id="buy">buy</button>
				<button class="btn btn-primary" id="pay">pay</button>
				<button class="btn btn-primary" id="mortage">mortage</button>
				<button class="btn btn-primary" id="unmortage">unmortage</button>
				<button class="btn btn-primary" id="build">build</button>
				<button class="btn btn-primary" id="sell">sell</button>
			</div>
			<div class="span4 well">
				<button class="btn btn-primary" id="done">done</button>
				<button class="btn btn-primary" id="ok">ok</button>
				<button class="btn btn-primary" id="start">start</button>
				<button class="btn btn-primary" id="ready">ready</button>
			</div>
		</div>

	<ul id="chat">
	</ul>
		<!-- TEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEST -->
		
		
		
		
		
		
		
		
			<!-- world chat and room list -->
			<div class="container">
				<p></p>
				<div class="row-fluid">
					<div class="span8">
						<div class="well">
							<h1>Chat</h1>
							<div id="chatBox"></div>
							<form name="message" action="">
								<input type="text" id="usermsg" size=100
									placeholder="Enter your message here..."> <a
									class="btn btn-primary btn-medium" id="send">Send</a>
							</form>
						</div>
					</div>

				</div>
			</div>
			<!-- world chat and room list -->
		</div>

	</div>




</body>
</html>