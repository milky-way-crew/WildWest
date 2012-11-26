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

<!-- Fancy Buttons & Tips -->
<link rel="stylesheet" href="resources/css/fancy-button.css" type="text/css">
<link rel="stylesheet" href="resources/css/fancy-tip.css" type="text/css">
<!-- Twitter Bootstrap -->
<link rel="stylesheet" href="resources/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="resources/css/bootstrap-responsive.css" type="text/css">
<!-- Game styles -->
<!-- <link rel="stylesheet" href="resources/css/chatroom_style.css" type="text/css"/> -->
<link rel="stylesheet" href="resources/css/gameBoard.css" type="text/css">

<link rel="stylesheet" href="resources/css/jquery-ui-1.9.1.custom.css" type="text/css">
<!-- Fonts DONT FORGER TO DOWNLOAD THEM TO USE LOCAL -->
<link href='http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Archivo+Narrow' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Bubblegum+Sans' rel='stylesheet' type='text/css'>
</head>
<!-- NO MORE GREEN BACKGROUND COLORS -->
<!-- <body bgcolor="#CCE7D0"> --> 
<body>
	<div id="main-container">
		<table id="game-table" border="1" rules="all">
			<tr>
				<td class="northwest" id="cell21"></td>
				<td id="cell22" class="north">
					<div class="north">
						<div class="owner"></div>
						<div class="house red">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell23" class="north">
				</td>
				<td id="cell24" class="north">
					<div class="north">
						<div class="owner"></div>
						<div class="house red">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell25" class="north">
					<div class="north">
						<div class="owner"></div>
						<div class="house red">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell26" class="north">
					<div class="north">
						<div class="owner"></div>
					</div>
				</td>
				<td id="cell27" class="north">
					<div class="north">
						<div class="owner"></div>
						<div class="house yellow">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell28" class="north">
					<div class="north">
						<div class="owner"></div>
						<div class="house yellow">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell29" class="north">
				</td>
				<td id="cell30" class="north">
					<div class="north">
						<div class="owner"></div>
						<div class="house yellow">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="northeast" id="cell31"></td>
			</tr>
			<tr>
				<td class="west" id="cell20">
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/fortress.svg">
					</div>
					<div class="house orange">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td id = "cell41" colspan="9" rowspan="9">
					<!-- ............ -->
					<!--  center cell -->
					<!-- ............ -->
					<div class="container well" id="center">
<!-- 						<div id="card" class="well">
							<h3 class="title">Chance card</h3>
							<div class="description">
								<blockquote class="">
									  <p>Goto jail!</p>
 									  <small>monopoly-guy</small>
								</blockquote>
							</div>
							<div class="image">
								<img src="resources/img/board/cards/tax.png" alt="Pay tax">
							</div>
						</div> -->
<!-- 						<div class="well">
							<div class="tabbable tabs-left">
							<ul class="nav nav-pills">
							  <li class="active">
							    <a href="#">Mortage</a>
							  </li>
							  <li><a href="#">Unmortage</a></li>
							  <li><a href="#">Build</a></li>
							  <li><a href="#">Sell</a></li>
							</ul>
							<ul class="nav nav-list span2">
								<li class="active"><a href="#overview">Mortage<i class="icon-chevron-right"></i></a></li>
								<li><a href="#">Unmortage<i class="icon-chevron-right"></i></a></li>
								<li><a href="#">Build<i class="icon-chevron-right"></i></a></li>
								<li><a href="#">Sell<i class="icon-chevron-right"></i></a></li>
								<li><a href="#">Auction<i class="icon-chevron-right"></i></a></li>
							</ul>
							</div>
						</div> -->
						<!-- ************** -->
						<!-- [Info seciton] -->
						<!-- ************** -->
							<div id="accordion">
								<h3>Mini-map</h3>
								<div id="info">
									<table id="info-table-1">
										<tr>
											<td class="mini brown" id="miniCell2"></td>
											<td class="mini brown" id="miniCell4"></td>
										</tr>
										<tr height="5px"></tr>
										<tr>
											<td class="mini azure" id="miniCell7"></td>
											<td class="mini azure" id="miniCell9"></td>
											<td class="mini azure" id="miniCell10"></td>
										</tr>
										<tr height="5px"></tr>
										<tr>
											<td class="mini lightred" id="miniCell12"></td>
											<td class="mini lightred" id="miniCell14"></td>
											<td class="mini lightred" id="miniCell15"></td>
										</tr>
										<tr height="5px"></tr>
										<tr>
											<td class="mini orange" id="miniCell17"></td>
											<td class="mini orange" id="miniCell19"></td>
											<td class="mini orange" id="miniCell20"></td>
										</tr>
										<tr height="5px"></tr>
										<tr>
											<td class="mini red" id="miniCell22"></td>
											<td class="mini red" id="miniCell24"></td>
											<td class="mini red" id="miniCell25"></td>
										</tr>
									</table>
									<table id="info-table-2">
										<tr>
											<td class="mini yellow" id="miniCell27"></td>
											<td class="mini yellow" id="miniCell28"></td>
											<td class="mini yellow" id="miniCell30"></td>
										</tr>
										<tr height="5px"></tr>
										<tr>
											<td class="mini green" id="miniCell32"></td>
											<td class="mini green" id="miniCell33"></td>
											<td class="mini green" id="miniCell35"></td>
										</tr>
										<tr height="5px"></tr>
										<tr>
											<td class="mini blue" id="miniCell38"></td>
											<td class="mini blue" id="miniCell40"></td>
										</tr>
										<tr height="5px"></tr>
										<tr>
											<td class="mini gray" id="miniCell6"></td>
											<td class="mini gray" id="miniCell16"></td>
											<td class="mini gray" id="miniCell26"></td>
											<td class="mini gray" id="miniCell36"></td>
										</tr>
									</table>
									<div id="money">
										<span class="label" id="money-player-1">1000$</span>
										<span class="label" id="money-player-2">1000$</span>
										<span class="label" id="money-player-3">1000$</span>
										<span class="label" id="money-player-4">1000$</span>										
									</div>
									<!-- ************* -->
									<!-- [Roll dices]  -->
									<!-- ************* -->
									<div id="roolDice">
										<img id="diceImg1" src="resources/img/board/die1.gif" width="50px"
											height="50px"> </img> <img id="diceImg2"
											src="resources/img/board/die1.gif" width="50px" height="50px">
										</img>
									</div>
								</div>			
							</div>



						<!-- ************** -->
						<!-- [Menu buttons] -->
						<!-- ************** -->
						<div class="" id="button-menu">
								<div class="well" id="controls">
									<button class="square-button" id="roll">roll</button>
									<button class="square-button" id="buy">buy</button>
									<button class="square-button" id="pay">pay</button>
									<button class="square-button" id="mortage">mortage</button>
									<button class="square-button" id="unmortage">unmortage</button>
									<button class="square-button" id="build">build</button>
									<button class="square-button" id="sell">sell</button>
									<button class="square-button" id="auction">auction</button>
								</div>
								<div class="well" id="menu">
								    <a href="javascript:void(0);" id="ready" class="button lightbg-green clearfix"><span>ready</span>
								    </a>
								    <a href="javascript:void(0);" id="start" class="button lightbg-orange clearfix"><span>start</span>
									    <div class="icon">
									      <div class="arrow"></div>
									    </div>
								    </a>
								    <a href="javascript:void(0);" id="done" class="button lightbg-blue clearfix"><span>done</span>
								    </a>
								</div>
						</div>
					</div>
					<!-- ............ -->
					<!-- /center cell -->
					<!-- ............ -->
				</td>
				<td class="east" id="cell32">
					<div class="owner"></div>
					<div class="house green">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="west" id="cell19">
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/fortress.svg">
					</div>
					<div class="house orange">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td class="east" id="cell33">
					<div class="owner"></div>
					<div class="house green">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="west" id="cell18">
				</td>
				<td class="east" id="cell34">
				</td>
			</tr>
			<tr>
				<td class="west" id="cell17">
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/cathedral.svg">
					</div>
					<div class="house orange">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td class="east" id="cell35">
					<div class="owner"></div>
					<div class="house green">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="west" id="cell16">
					<div class="owner"></div>
				</td>
				<td class="east" id="cell36">
					<div class="owner"></div>
				</td>
			</tr>
			<tr>
				<td class="west tip" id="cell15">
					<span class="right">
						<p class="tip-header">Cost: 40$</p>
						<ul class="tip-content">
							<li>1 houses : 25 $$</li>
							<li>2 houses : 45 $$</li>
							<li>3 houses : 75 $$</li>
							<li>4 houses : 185 $$</li>
							<li>Hotel: 25 $$</li>
						</ul>
						<center>
							<a href="#" class="btn btn-info">mortage</a>
							<a href="#" class="btn btn-succes">build</a>
						</center>
						<span class="nub"></span>
					</span> 
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/farm.svg">
					</div>
					<div class="house lightred">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td class="east" id="cell37">
				</td>
			</tr>
			<tr>
				<td class="west tip" id="cell14">
					<span class="right">
						<p class="tip-header">Cost: 40000$</p>
						<ul class="tip-content">
							<li>1 houses : 25$</li>
							<li>2 houses : 45$</li>
							<li>3 houses : 75$</li>
							<li>4 houses : 185$</li>
							<li>Hotel: 25$</li>
						</ul>
						<span class="nub"></span>
					</span> 
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/fo.svg">
					</div>
					<div class="house lightred">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td class="east" id="cell38">
					<div class="owner"></div>
					<div class="house blue">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="west" id="cell13">
				</td>
				<td class="east" id="cell39">
				</td>
			</tr>
			<tr>
				<td class="west" id="cell12">
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/fortress.svg">
					</div>
					<div class="house lightred">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td class="east" id="cell40">
					<div class="owner"></div>
					<div class="house blue">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="southwest" id="cell11">
				</td>
				<td class="south" id="cell10">
					<div class="south">
						<div class="owner"></div>
						<div class="house azure">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="south" id="cell9">
					<div class="south">
						<div class="owner"></div>
						<div class="house azure">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="south" id="cell8">
				</td>
				<td class="south" id="cell7">
					<div class="south">
						<div class="owner"></div>
						<div class="house azure">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="south" id="cell6">
					<div class="south">
						<div class="owner"></div>
					</div>
				</td>
				<td class="south" id="cell5">
				</td>
				<td class="south" id="cell4">
					<div class="south">
						<div class="owner"></div>
						<div class="house brown">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="south" id="cell3">
				</td>
				<td class="south" id="cell2">
					<div class="south tip">
						<span class="top">
							<p class="tip-header">Cost: 40$</p>
							<ul class="tip-content">
								<li>1 houses : 25$</li>
								<li>2 houses : 45$</li>
								<li>3 houses : 75$</li>
								<li>4 houses : 185$</li>
								<li>Hotel: 25$</li>
							</ul>
							<span class="nub"></span>
						</span> 
						<div class="owner"></div>
						<div class="house brown">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
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

		<div id="chat-log">
			<ul id="chat">
			</ul>
			<form name="message" action="" class="tip">
				<input type="text" id="usermsg" size=100 placeholder="Enter your message here..."> 
				<a class="btn btn-primary btn-medium tip" id="send">Send</a>
				<span class="left">redtube.com :3<span class="nub"></span></span>
			</form>
		</div>
	</div>

<!-- @herbion -->
<!-- JQuery + ui -->
<script type="text/javascript" src="resources/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui-1.9.1.custom.js"></script>
<!-- LESS -->
<script type="text/javascript" src="resources/js/less.js"></script>
<!-- Bootstrap JS -->
<script type="text/javascript" src="resources/js/bootstrap-transition.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-alert.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-modal.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-tooltip.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-popover.js"></script>
<!-- Game-js engine -->
<script src="resources/js/monopoly/monopoly.js" type="text/javascript"></script>
</body>
</html>