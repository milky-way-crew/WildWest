<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Monopoly</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="apple-touch-icon" href="/img/apple-touch-icon.png">
<link href="resources/images/favicon.ico" rel="shortcut icon" />



<link rel="stylesheet" href="resources/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="resources/css/bootstrap-responsive.css">
<link type="text/css" rel="stylesheet" href="resources/css/chatroom_style.css" />
<link rel="stylesheet" href="resources/css/gameBoard.css">
<link rel="stylesheet" href="resources/css/jquery-ui-1.9.1.custom.css">

<script type="text/javascript" src="resources/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui-1.9.1.custom.js"></script>
<script src="resources/js/board/gameBoard.js" type="text/javascript"></script>




</head>

<body bgcolor="#CCE7D0">

	<div id="board">

		<table id="table" border="1" rules="all">

			<tr>
				<td class="northwest" id="cell21"></td>
				
				<td class="south_north" id="cell22">
					<div style="position: absolute;">
						<img class="north_position_house" id="house_cell22" src="resources/img/board/emptyhouse.png" ></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell22" class="ownerNorth"></div>
					</div>
				</td>
				
				<td class="south_north" id="cell23"></td>
				
				<td class="south_north" id="cell24">
					<div style="position: absolute;">
						<img class="north_position_house" id="house_cell24" src="resources/img/board/emptyhouse.png" ></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell24" class="ownerNorth"></div>
					</div>
				</td>
				
				<td class="south_north" id="cell25">
					<div style="position: absolute;">
						<img class="north_position_house" id="house_cell25" src="resources/img/board/emptyhouse.png" ></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell25" class="ownerNorth"></div>
					</div></td>
					
				<td class="south_north" id="cell26">
					<div class="cityPosition">
						<div id="ownerCell26" class="ownerNorth"></div>
					</div>
				</td>
				
				<td class="south_north" id="cell27">
					<div style="position: absolute;">
						<img class="north_position_house" id="house_cell27" src="resources/img/board/emptyhouse.png" ></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell27" class="ownerNorth"></div>
					</div>
				</td>
				
				<td class="south_north" id="cell28">
					<div style="position: absolute;">
						<img class="north_position_house" id="house_cell28" src="resources/img/board/emptyhouse.png" ></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell28" class="ownerNorth"></div>
					</div>
				</td>
				
				<td class="south_north" id="cell29"></td>
				
				<td class="south_north" id="cell30">
					<div style="position: absolute;">
						<img class="north_position_house" id="house_cell30" src="resources/img/board/emptyhouse.png" ></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell30" class="ownerNorth"></div>
					</div>
				</td>
				
				<td class="northeast" id="cell31"></td>
			</tr>

			<tr>
				<td class="west_east" id="cell20">
					<div style="position:absolute;">
						<img class="west_position_house" id="house_cell20" src="resources/img/board/emptyhouse.png"></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell20" class="ownerWest"></div>
					</div>
				</td>
				<td colspan="9" rowspan="9">
					<div id="roolDice">
						<img id="diceImg1" src="resources/img/board/die1.gif" width="50px"
							height="50px">
						</img> 
						<img id="diceImg2"
							src="resources/img/board/die1.gif" width="50px" height="50px">
						</img>
					</div>
				</td>
				
				<td class="west_east" id="cell32">
					<div style="position:absolute;">
						<img class="east_position_house" id="house_cell32" src="resources/img/board/emptyhouse.png"></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell32" class="ownerEast"></div>
					</div>
				</td>
			</tr>

			<tr>
				<td class="west_east" id="cell19">
				<div style="position:absolute;">
						<img class="west_position_house" id="house_cell19" src="resources/img/board/emptyhouse.png"></img>
					</div>
				<div class="cityPosition">
						<div id="ownerCell19" class="ownerWest"></div>
				</div>
				</td>
				
				<td class="west_east" id="cell33">
					<div style="position:absolute;">
						<img class="east_position_house" id="house_cell33" src="resources/img/board/emptyhouse.png"></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell33" class="ownerEast"></div>
					</div>
				</td>
			</tr>

			<tr>
				<td class="west_east" id="cell18"></td>
				<td class="west_east" id="cell34"></td>
			</tr>

			<tr>
				<td class="west_east" id="cell17">
					<div style="position:absolute;">
						<img class="west_position_house" id="house_cell17" src="resources/img/board/emptyhouse.png"></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell17" class="ownerWest"></div>
					</div></td>
				<td class="west_east" id="cell35">
					<div style="position:absolute;">
						<img class="east_position_house" id="house_cell35" src="resources/img/board/emptyhouse.png"></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell35" class="ownerEast"></div>
					</div></td>
			</tr>

			<tr>
				<td class="west_east" id="cell16">
					<div class="cityPosition">
						<div id="ownerCell16" class="ownerWest"></div>
					</div></td>
				<td class="west_east" id="cell36">
					<div class="cityPosition">
						<div id="ownerCell36" class="ownerEast"></div>
					</div></td>
			</tr>

			<tr>
				<td class="west_east" id="cell15">
					<div style="position:absolute;">
						<img class="west_position_house" id="house_cell15" src="resources/img/board/emptyhouse.png"></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell15" class="ownerWest"></div>
					</div></td>
					
				<td class="west_east" id="cell37"></td>
			</tr>

			<tr>
				<td class="west_east" id="cell14">
					<div style="position:absolute;">
						<img class="west_position_house" id="house_cell14" src="resources/img/board/emptyhouse.png"></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell14" class="ownerWest"></div>
					</div></td>
					
				<td class="west_east" id="cell38">
					<div style="position:absolute;">
						<img class="east_position_house" id="house_cell38" src="resources/img/board/emptyhouse.png"></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell38" class="ownerEast"></div>
					</div></td>
			</tr>

			<tr>
				<td class="west_east" id="cell13"></td>
				<td class="west_east" id="cell39"></td>
			</tr>

			<tr>
				<td class="west_east" id="cell12">
					<div style="position:absolute;">
						<img class="west_position_house" id="house_cell12" src="resources/img/board/emptyhouse.png"></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell12" class="ownerWest"></div>
					</div></td>
					
				<td class="west_east" id="cell40">
					<div style="position:absolute;">
						<img class="east_position_house" id="house_cell40" src="resources/img/board/emptyhouse.png"></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell40" class="ownerEast"></div>
					</div></td>
			</tr>

			<tr>
				<td class="southwest" id="cell11"></td>
				
				<td class="south_north" id="cell10">
					<div style="position:absolute;">
						<img class="south_position_house" id = "house_cell10" src="resources/img/board/emptyhouse.png" ></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell10" class="ownerSouth"></div>
					</div></td>
					
				<td class="south_north" id="cell9">
					<div style="position:absolute;">
						<img class="south_position_house" id = "house_cell9" src="resources/img/board/emptyhouse.png" ></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell9" class="ownerSouth"></div>
					</div></td>
					
				<td class="south_north" id="cell8"></td>
				
				<td class="south_north" id="cell7">
					<div style="position:absolute;">
						<img class="south_position_house" id = "house_cell7" src="resources/img/board/emptyhouse.png" ></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell7" class="ownerSouth"></div>
					</div></td>
					
				<td class="south_north" id="cell6">
					
					<div class="cityPosition">
						<div id="ownerCell6" class="ownerSouth"></div>
					</div></td>
					
				<td class="south_north" id="cell5"></td>
				
				<td class="south_north" id="cell4">
					<div style="position:absolute;">
						<img class="south_position_house" id = "house_cell4" src="resources/img/board/emptyhouse.png"></img>
					</div>
					<div class="cityPosition">
						<div id="ownerCell4" class="ownerSouth"></div>
					</div></td>
					
				<td class="south_north" id="cell3"></td>
				
				<td class="south_north" id="cell2">
					
					<div style="position:absolute;">
						<img class="south_position_house" id = "house_cell2" src="resources/img/board/emptyhouse.png" ></img>
					</div>
					
					<div class="cityPosition">
						<div id="ownerCell2" class="ownerSouth"></div>
					</div>
				</td>

				<td class="southeast" id="cell1">

					<div id="divPlayer1" style="position: relative;">
						<img id="player1" src="resources/img/board/player-1.png" style="position: absolute; left: 20%; top: +30%;"></img>
					</div>
					<div id="divPlayer2" style="position: relative;">
						<img id="player2" src="resources/img/board/player-2.png" style="position: absolute; left: 40%; top: -65%;"></img>
					</div>
					<div id="divPlayer3" style="position: relative;">
						<img id="player3" src="resources/img/board/player-3.png" style="position: absolute; left: 20%; top: -20%;"></img>
					</div>
					<div id="divPlayer4" style="position: relative;">
						<img id="player4" src="resources/img/board/player-4.png" style="position: absolute; left: 40%; top: -120%;"></img>
					</div>
				</td>
			</tr>

		</table>

		<!-- Block about player's information -->
		<div id="playerInfo">
			<div id="accordion">
				<h3>Player1 </h3>
				<div id="infoPlayer1">

					<table id="firstInfoTable" width="100px" height="200px">
						<tr>
							<td class="brownMini" id="firstMiniCell2"></td>
							<td class="brownMini" id="firstMiniCell4"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="azureMini" id="firstMiniCell7"></td>
							<td class="azureMini" id="firstMiniCell9"></td>
							<td class="azureMini" id="firstMiniCell10"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="lightredMini" id="firstMiniCell12"></td>
							<td class="lightredMini" id="firstMiniCell14"></td>
							<td class="lightredMini" id="firstMiniCell15"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="orangeMini" id="firstMiniCell17"></td>
							<td class="orangeMini" id="firstMiniCell19"></td>
							<td class="orangeMini" id="firstMiniCell20"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="redMini" id="firstMiniCell22"></td>
							<td class="redMini" id="firstMiniCell24"></td>
							<td class="redMini" id="firstMiniCell25"></td>
						</tr>
					</table>

					<table id="secondInfoTable" width="130px" height="160px">
						<tr>
							<td class="yellowMini" id="firstMiniCell27"></td>
							<td class="yellowMini" id="firstMiniCell28"></td>
							<td class="yellowMini" id="firstMiniCell30"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="greenMini" id="firstMiniCell32"></td>
							<td class="greenMini" id="firstMiniCell33"></td>
							<td class="greenMini" id="firstMiniCell35"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="blueMini" id="firstMiniCell38"></td>
							<td class="blueMini" id="firstMiniCell40"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="grayMini" id="firstMiniCell6"></td>
							<td class="grayMini" id="firstMiniCell16"></td>
							<td class="grayMini" id="firstMiniCell26"></td>
							<td class="grayMini" id="firstMiniCell36"></td>
						</tr>
					</table>
					<h3> M 1000</h3>
				</div>
				<h3>Player2</h3>
				<div id="infoPlayer">
				<table id="firstInfoTable" width="100px" height="200px">
						<tr>
							<td class="brownMini" id="secondMiniCell2"></td>
							<td class="brownMini" id="secondMiniCell4"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="azureMini" id="secondMiniCell7"></td>
							<td class="azureMini" id="secondMiniCell9"></td>
							<td class="azureMini" id="secondMiniCell10"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="lightredMini" id="secondMiniCell12"></td>
							<td class="lightredMini" id="secondMiniCell14"></td>
							<td class="lightredMini" id="secondMiniCell15"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="orangeMini" id="secondMiniCell17"></td>
							<td class="orangeMini" id="secondMiniCell19"></td>
							<td class="orangeMini" id="secondMiniCell20"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="redMini" id="secondMiniCell22"></td>
							<td class="redMini" id="secondMiniCell24"></td>
							<td class="redMini" id="secondMiniCell25"></td>
						</tr>
					</table>

					<table id="secondInfoTable" width="130px" height="160px">
						<tr>
							<td class="yellowMini" id="secondMiniCell27"></td>
							<td class="yellowMini" id="secondMiniCell28"></td>
							<td class="yellowMini" id="secondMiniCell30"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="greenMini" id="secondMiniCell32"></td>
							<td class="greenMini" id="secondMiniCell33"></td>
							<td class="greenMini" id="secondMiniCell35"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="blueMini" id="secondMiniCell38"></td>
							<td class="blueMini" id="secondMiniCell40"></td>
						</tr>
						<tr height="5px"></tr>
						<tr>
							<td class="grayMini" id="secondMiniCell6"></td>
							<td class="grayMini" id="secondMiniCell16"></td>
							<td class="grayMini" id="secondMiniCell26"></td>
							<td class="grayMini" id="secondMiniCell36"></td>
						</tr>
					</table>
					<h3 id="secondMoney"> M 1000</h3>
				</div>
				<h3>Player3</h3>
				<div>
					
					
				</div>
				<h3>Player4</h3>
				<div>

					<p>Nothing...</p>
				</div>
			</div>

		</div>


		<!-- Create buttons -->
		<div class="playerButton">
			<input id="buyButton" type="submit" value="BUY" > 
			<input id="mortageButton" type="submit" value="MORTAGE">
			<input id="unmortageButton" type="submit" value="UNMORTAGE"> 
			<input id="buildButton" type="submit" value="BUILD"> 
			<input id="sellButton" type="submit" value="Sell"> 
			<input id="diceButton" type="submit" value="RollDice">
			<input id="doneButton" type="submit" value="Done">
		</div>


		<div id="upChat">

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
