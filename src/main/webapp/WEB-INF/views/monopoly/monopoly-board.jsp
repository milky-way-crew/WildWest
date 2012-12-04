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
<link rel="stylesheet" href="resources/css/monopoly-board.css" type="text/css">

<link rel="stylesheet" href="resources/css/jquery-ui-1.9.1.custom.css" type="text/css">
<!-- Fonts DONT FORGER TO DOWNLOAD THEM TO USE LOCAL -->
<!-- <link href='http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Archivo+Narrow' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Bubblegum+Sans' rel='stylesheet' type='text/css'> -->
</head>
<!-- NO MORE GREEN BACKGROUND COLORS -->
<!-- <body bgcolor="#CCE7D0"> --> 
<body>
	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			<a class="brand" href="./home">#home</a>
			<ul class="nav">
				<li class="active"><a href="#"></a></li>
				<li><a href="./chess">Chess rooms</a></li>
				<li><a href="#">Blablabla</a></li>
			</ul>
		</div>
	</div>
	<div class="container" style=" width: 1230px; ">
		<table id="game-table" border="1" rules="all">
			<tr>
				<td class="cell northwest tip" id="cell21">
					<span class="label label-info" style=" top: 0px; text-align: center; ">Free Station</span>
					<center>
						<img src="resources/img/board/city/sign_left.svg">
					</center>
				</td>
				<td id="cell22" class="cell north">
					<div class="north tip">
						<span class="town-title">Fort</span>
						<span class="bottom">
							<img src="resources/img/board/city/fort.svg" width="300">
							<hr>
							<div class="tip-header">
								<p>Initial cost/rent: 400$ : 25$</p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : 25 $$</li>
								<li>2 houses : 45 $$</li>
								<li>3 houses : 75 $$</li>
								<li>4 houses : 185 $$</li>
								<li>Castle: 2500 $$</li>
							</ul>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
						<div class="owner"></div>
						<div class="town-image">
							<img src="resources/img/board/city/fort.svg">
						</div>
						<div class="house red">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell23" class="cell north tip">
					<span class="town-title">Chance</span>
					<span class="bottom bottom-small">
						<img src="resources/img/board/city/tree11.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>
					<img src="resources/img/board/city/tree11.svg" alt="" width="75">
				</td>
				<td id="cell24" class="cell north">
					<div class="north tip">
						<span class="town-title">City</span>
						<span class="bottom">
							<img src="resources/img/board/city/city.svg" width="300">
							<hr>
							<div class="tip-header">
								<p>Initial cost/rent: 400$ : 25$</p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : 25 $$</li>
								<li>2 houses : 45 $$</li>
								<li>3 houses : 75 $$</li>
								<li>4 houses : 185 $$</li>
								<li>Castle: 2500 $$</li>
							</ul>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
						<div class="owner"></div>
						<div class="town-image">
							<img src="resources/img/board/city/city.svg">
						</div>
						<div class="house red">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell25" class="cell north">
					<div class="north tip">
						<span class="town-title">Fortress</span>
						<span class="bottom">
							<img src="resources/img/board/city/fortress.svg" width="300">
							<hr>
							<div class="tip-header">
								<p>Initial cost/rent: 400$ : 25$</p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : 25 $$</li>
								<li>2 houses : 45 $$</li>
								<li>3 houses : 75 $$</li>
								<li>4 houses : 185 $$</li>
								<li>Castle: 2500 $$</li>
							</ul>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
						<div class="owner"></div>
						<div class="town-image">
							<img src="resources/img/board/city/fortress.svg">
						</div>
						<div class="house red">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell26" class="cell north">
					<div class="north tip">
					<span class="town-title">Stonehenge</span>
					<span class="bottom bottom-small">
						<img src="resources/img/board/city/magic_stones.svg" width="300">
						<p class="tip-header"><strong class="active">Stonehenge star</strong><hr></p>
						<ul class="tip-content">
							<li>Collect them all to get ultimate power.</li>
						</ul>
						<span class="nub"></span>
					</span> 
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/magic_stones.svg" alt=""></div>
					</div>
				</td>
				<td id="cell27" class="cell north">
					<div class="north tip">
						<span class="town-title">Obelisk</span>
						<span class="bottom">
							<img src="resources/img/board/city/obelisk.svg" width="300">
							<hr>
							<div class="tip-header">
								<p>Initial cost/rent: 400$ : 25$</p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : 25 $$</li>
								<li>2 houses : 45 $$</li>
								<li>3 houses : 75 $$</li>
								<li>4 houses : 185 $$</li>
								<li>Castle: 2500 $$</li>
							</ul>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/obelisk.svg" alt=""></div>
						<div class="house yellow">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell28" class="cell north tip">
					<div class="north">
						<span class="town-title">Arch</span>
						<span class="bottom">
							<img src="resources/img/board/city/arch.svg" width="300">
							<hr>
							<div class="tip-header">
								<p>Initial cost/rent: 400$ : 25$</p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : 25 $$</li>
								<li>2 houses : 45 $$</li>
								<li>3 houses : 75 $$</li>
								<li>4 houses : 185 $$</li>
								<li>Castle: 2500 $$</li>
							</ul>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/arch.svg" alt=""></div>
						<div class="house yellow">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell29" class="cell north tip">
					<span class="town-title">Mountains</span>
					<span class="bottom bottom-small">
						<img src="resources/img/board/city/mountain4.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>
					<img src="resources/img/board/city/mountain4.svg" alt="" width="75">
				</td>
				<td id="cell30" class="cell north">
					<div class="north tip">
 						<span class="town-title">Maze</span>
						<span class="bottom">
							<img src="resources/img/board/city/maze.svg" width="300">
							<hr>
							<div class="tip-header">
								<p>Initial cost/rent: 400$ : 25$</p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : 25 $$</li>
								<li>2 houses : 45 $$</li>
								<li>3 houses : 75 $$</li>
								<li>4 houses : 185 $$</li>
								<li>Castle: 2500 $$</li>
							</ul>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/maze.svg" alt=""></div>
						<div class="house yellow">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="cell northeast tip" id="cell31">
					<!-- <div class="town-image"> -->
					<span class="label label-info" style=" top: 0px; text-align: center; ">Go to Jail</span>
					<center>
						<img src="resources/img/board/city/sign_crossroad.svg">
					</center>
					<!-- </div> -->
				</td>
			</tr>
			<tr>
				<td class="cell west tip" id="cell20">
					<span class="town-title badge badge-info">Graveyard</span>
					<span class="right">
						<img src="resources/img/board/city/graveyard.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : 25 $$</li>
							<li>2 houses : 45 $$</li>
							<li>3 houses : 75 $$</li>
							<li>4 houses : 185 $$</li>
							<li>Castle: 2500 $$</li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>

					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/graveyard.svg">
					</div>
					<div class="house orange">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td id = "cell41" colspan="9" rowspan="9">
					<!-- ............ -->
					<!--  center cell -->
					<!-- ............ -->
					<div class="container" id="center">
						<div class="tabbable well">
							<ul id="myTab" class="nav nav-tabs">
								<li><a href="#minimap-tab" data-toggle="tab">Mini-map</a></li>
								<li><a href="#chat-tab" data-toggle="tab">Chat</a></li>
								<li><a href="#auction-tab" data-toggle="tab">Auction</a></li>
<!-- 								<li><a href="#settings" data-toggle="tab">Settings</a></li> -->
										<div id="money">
											<span class="label color-player-1" id="money-player-1" >0$</span>
											<span class="label color-player-2" id="money-player-2" >0$</span>
											<span class="label color-player-3" id="money-player-3" >0$</span>
											<span class="label color-player-4" id="money-player-4" >0$</span>							
										</div>
							</ul>
							<div class="tab-content">
								<div class="tab-pane fade in active" id="minimap-tab">
									<!-- ************** -->
									<!-- [Info seciton] -->
									<!-- ************** -->
									<!-- <h3>Mini-map</h3> -->
									<div id="info">
										<table id="info-table-1">
											<tr>
												<td class="mini brown" id="miniCell2"><img src="" alt=""/></td>
												<td class="mini brown" id="miniCell4"><img src="" alt=""/></td>
											</tr>
											<tr height="5px"></tr>
											<tr>
												<td class="mini azure" id="miniCell7"><img src="" alt=""/></td>
												<td class="mini azure" id="miniCell9"><img src="" alt=""/></td>
												<td class="mini azure" id="miniCell10"><img src="" alt=""/></td>
											</tr>
											<tr height="5px"></tr>
											<tr>
												<td class="mini lightred" id="miniCell12"><img src="" alt=""/></td>
												<td class="mini lightred" id="miniCell14"><img src="" alt=""/></td>
												<td class="mini lightred" id="miniCell15"><img src="" alt=""/></td>
											</tr>
											<tr height="5px"></tr>
											<tr>
												<td class="mini orange" id="miniCell17"><img src="" alt=""/></td>
												<td class="mini orange" id="miniCell19"><img src="" alt=""/></td>
												<td class="mini orange" id="miniCell20"><img src="" alt=""/></td>
											</tr>
											<tr height="5px"></tr>
											<tr>
												<td class="mini red" id="miniCell22"><img src="" alt=""/></td>
												<td class="mini red" id="miniCell24"><img src="" alt=""/></td>
												<td class="mini red" id="miniCell25"><img src="" alt=""/></td>
											</tr>
										</table>
										<table id="info-table-2">
											<tr>
												<td class="mini yellow" id="miniCell27"><img src="" alt=""/></td>
												<td class="mini yellow" id="miniCell28"><img src="" alt=""/></td>
												<td class="mini yellow" id="miniCell30"><img src="" alt=""/></td>
											</tr>
											<tr height="5px"></tr>
											<tr>
												<td class="mini green" id="miniCell32"><img src="" alt=""/></td>
												<td class="mini green" id="miniCell33"><img src="" alt=""/></td>
												<td class="mini green" id="miniCell35"><img src="" alt=""/></td>
											</tr>
											<tr height="5px"></tr>
											<tr>
												<td class="mini blue" id="miniCell38"><img src="" alt=""/></td>
												<td class="mini blue" id="miniCell40"><img src="" alt=""/></td>
											</tr>
											<tr height="5px"></tr>
											<tr>
												<td class="mini gray" id="miniCell6"><img src="" alt=""/></td>
												<td class="mini gray" id="miniCell16"><img src="" alt=""/></td>
												<td class="mini gray" id="miniCell26"><img src="" alt=""/></td>
												<td class="mini gray" id="miniCell36"><img src="" alt=""/></td>
											</tr>
										</table>
								</div>
							</div>
							<div class="tab-pane fade in " id="chat-tab">		
								<div id="chat-log">
									<ul id="chat">
									</ul>
									<form name="message" action="" class="tip">
										<input type="text" id="usermsg" size=100 placeholder="Enter your message here..."> 
										<a class="btn btn-primary btn-medium tip" id="send">Send</a>
									</form>
								</div>
							</div>
							<!-- <div class="tab-pane fade in " id="auction-tab">		 -->
							<div class="tab-pane in  active" id="auction-tab">		
								<div class="span6 well">
									<div class="row">
										<h3>Action invoked by:</h3> 
											<strong class="label invoker">GREEN</strong> 
									</div>
									<div class="row">
										<h3>Highest price is:</h3>
										<strong class="label label-warning price">1000$</strong> by <small class="label price-caller">GREEN</small>
									</div>
									<div class="row rates">
										<h3>rates &gt;&gt;</h3>
										<h3 style=""> 
										<small class="label color-player-1">10$</small>
										<small class="label color-player-2">0$</small>
										<small class="label color-player-3">0$</small>
										<small class="label color-player-4">0$</small>
										</h3>
									</div>
								</div>
								<div class="span3 well">
									<img src="resources/img/board/city/house.svg" alt="" >
								</div> 
								<div class="row auction-controls">
									<button id="up50" class="btn btn-primary">up by 50$</button>
									<button id="up10" class="btn btn-primary">up by 10$</button>

									<!-- <a href="javascript:void(0);" id="up10" class="button lightbg-pink clearfix">up by 10$</a> -->
									<!-- <a href="javascript:void(0);" id="up50" class="button lightbg-orange clearfix">up by 50$</a> -->
								</div>
							</div>
							</div>
						</div>

										<!-- ************* -->
										<!-- [Roll dices]  -->
										<!-- ************* -->
										<div id="roll-dice">
											<img id="diceImg1" class="dice" src="resources/img/board/die1.gif" width="50px"
											height="50px"> </img> <img id="diceImg2" class="dice"
											src="resources/img/board/die1.gif" width="50px" height="50px">
										</img>
									</div>

						<!-- ************** -->
						<!-- [Menu buttons] -->
						<!-- ************** -->
						<div class="" id="button-menu" >
							<div class="well" id="controls2">
								<button class="square-button" id="buy">buy</button>
								<button class="square-button" id="pay">pay</button>							
							</div>
							<div class="well" id="controls">
								<button class="square-button" id="roll">roll</button>
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
				<td class="cell east tip" id="cell32">
					<span class="town-title">Mine</span>
					<span class="left">
						<img src="resources/img/board/city/mine.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : 25 $$</li>
							<li>2 houses : 45 $$</li>
							<li>3 houses : 75 $$</li>
							<li>4 houses : 185 $$</li>
							<li>Castle: 2500 $$</li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/mine.svg" alt="">
					</div>
					<div class="house green">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="cell west tip" id="cell19">
					<span class="town-title badge badge-info">Monastery</span>
					<span class="right">
						<img src="resources/img/board/city/monastery.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : 25 $$</li>
							<li>2 houses : 45 $$</li>
							<li>3 houses : 75 $$</li>
							<li>4 houses : 185 $$</li>
							<li>Castle: 2500 $$</li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>

					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/monastery.svg">
					</div>
					<div class="house orange">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td class="cell east tip" id="cell33">
					<span class="town-title">Hunter</span>
					<span class="left">
						<img src="resources/img/board/city/hunter.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : 25 $$</li>
							<li>2 houses : 45 $$</li>
							<li>3 houses : 75 $$</li>
							<li>4 houses : 185 $$</li>
							<li>Castle: 2500 $$</li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>
					<div class="owner"></div>
					<div class="town-image"><img src="resources/img/board/city/hunter.svg" alt=""></div>
					<div class="house green">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="cell west tip" id="cell18">
					<span class="town-title badge badge-info">Caravan wreck</span>
					<span class="right">
						<img src="resources/img/board/city/caravan_wreck.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Chance card?</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>

						<span class="nub"></span>
					</span>
					<center>
						<!-- <div class="town-image"> -->
							<img src="resources/img/board/city/caravan_wreck.svg" alt="" width="80">
						<!-- </div> -->
					</center>
				</td>
				<td class="cell east tip" id="cell34">
					<span class="town-title">Tree?!</span>
					<span class="left">
						<img src="resources/img/board/city/tree02.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<span class="nub"></span>
					</span>
					<!-- <div class="town-image"> -->
					<center>
						<img src="resources/img/board/city/tree02.svg" alt="" width="80">
					</center>
					<!-- </div> -->
				</td>
			</tr>
			<tr>
				<td class="cell west tip" id="cell17">
					<span class="town-title badge badge-info">Cathedral</span>
					<span class="right">
						<img src="resources/img/board/city/cathedral.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : 25 $$</li>
							<li>2 houses : 45 $$</li>
							<li>3 houses : 75 $$</li>
							<li>4 houses : 185 $$</li>
							<li>Castle: 2500 $$</li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/cathedral.svg">
					</div>
					<div class="house orange">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td class="cell east tip" id="cell35">
					<span class="town-title badge badge-info">Blacksmith</span>
					<span class="left">
						<img src="resources/img/board/city/blacksmith.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : 25 $$</li>
							<li>2 houses : 45 $$</li>
							<li>3 houses : 75 $$</li>
							<li>4 houses : 185 $$</li>
							<li>Castle: 2500 $$</li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>
					<div class="owner"></div>
					<div class="town-image"><img src="resources/img/board/city/blacksmith.svg" alt=""></div>
					<div class="house green">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="cell west tip" id="cell16">
					<span class="town-title badge badge-info">Community chest</span>
					<span class="right">
						<img src="resources/img/board/city/magic_stones.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<span class="nub"></span>
					</span>
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/magic_stones.svg">
					</div>
				</td>
				<td class="cell east tip" id="cell36">
					<span class="town-title badge badge-info">Blacksmith</span>
					<span class="left">
						<img src="resources/img/board/city/magic_stones.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : 25 $$</li>
							<li>2 houses : 45 $$</li>
							<li>3 houses : 75 $$</li>
							<li>4 houses : 185 $$</li>
							<li>Castle: 2500 $$</li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>
					<div class="owner"></div>
					<div class="town-image"><img src="resources/img/board/city/magic_stones.svg" alt=""></div>
				</td>
			</tr>
			<tr>
				<td class="cell west tip" id="cell15" >
					<span class="town-title">Little Farm</span>
					<span class="right">
						<img src="resources/img/board/city/farm.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : 25 $$</li>
							<li>2 houses : 45 $$</li>
							<li>3 houses : 75 $$</li>
							<li>4 houses : 185 $$</li>
							<li>Castle: 2500 $$</li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
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
				<td class="cell east tip" id="cell37">
					<span class="town-title badge badge-info">Bridge stone</span>
					<span class="left">
						<img src="resources/img/board/city/bridge_stone2.svg" width="300" height="300">
						<p class="tip-header">Shipwreck, was here you can get some bonuses.</p>
						<span class="nub"></span>
					</span> 
					<center>
						<img src="resources/img/board/city/bridge_stone2.svg" width="80">
					</center>
					<!-- </div> -->
				</td>
			</tr>
			<tr>
				<td class="cell west tip" id="cell14">
					<span class="town-title ">Fishery</span>
					<span class="right">
						<img src="resources/img/board/city/fishery.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : 25 $$</li>
							<li>2 houses : 45 $$</li>
							<li>3 houses : 75 $$</li>
							<li>4 houses : 185 $$</li>
							<li>Castle: 2500 $$</li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>

					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/fishery.svg">
					</div>
					<div class="house lightred">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td class="cell east tip" id="cell38">
					<span class="town-title badge badge-info">Square Tower</span>
					<span class="left">
						<img src="resources/img/board/city/tower_square.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : 25 $$</li>
							<li>2 houses : 45 $$</li>
							<li>3 houses : 75 $$</li>
							<li>4 houses : 185 $$</li>
							<li>Castle: 2500 $$</li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/tower_square.svg">
					</div>
					<div class="house blue">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="cell west tip" id="cell13">
					<span class="town-title badge badge-info">some-name</span>
					<span class="right">
						<img src="resources/img/board/city/shipwreck.svg" width="300" height="300">
						<p class="tip-header">Shipwreck, was here you can get some bonuses.</p>
						<span class="nub"></span>
					</span> 
					<center>
						<!-- <div class="town-image"> -->
							<img src="resources/img/board/city/shipwreck.svg" width="80">
						<!-- </div> -->
					</center>
				</td>
				<td class="cell east tip" id="cell39">
					<!-- <div class="town-image"> -->
					<span class="town-title badge badge-info">Sword in the stone</span>
					<span class="left">
						<img src="resources/img/board/city/sword_in_the_stone.svg" width="300" height="300">
						<p class="tip-header">Shipwreck, was here you can get some bonuses.</p>
						<span class="nub"></span>
					</span> 
						<center>
							<img src="resources/img/board/city/sword_in_the_stone.svg" alt="" width="75">
						</center>
					<!-- </div> -->
				</td>
			</tr>
			<tr>
				<td class="cell west tip" id="cell12">
					<span class="town-title badge badge-info">Windmill</span>
					<span class="right">
						<img src="resources/img/board/city/windmill.svg" width="150" height="150">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						 
						<hr>
						<ul class="tip-content">
							<li>1 houses : 25 $$</li>
							<li>2 houses : 45 $$</li>
							<li>3 houses : 75 $$</li>
							<li>4 houses : 185 $$</li>
							<li>Castle: 2500 $$</li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>

					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/windmill.svg">
					</div>
					<div class="house lightred">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td class="cell east tip" id="cell40">
					<span class="town-title badge badge-info">Tower round</span>
					<span class="left">
						<img src="resources/img/board/city/tower_round.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : 25 $$</li>
							<li>2 houses : 45 $$</li>
							<li>3 houses : 75 $$</li>
							<li>4 houses : 185 $$</li>
							<li>Castle: 2500 $$</li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/tower_round.svg">
					</div>
					<div class="house blue">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="cell southwest" id="cell11">
					<center>
							<img src="resources/img/board/city/jailhouse.svg" alt="">
							<span class="label label-info">Jail</span>
						<!-- </div> -->
					</center>
				</td>
				<td class="cell south" id="cell10">
					<div class="south tip">
						<span class="town-title badge badge-info">University</span>
						<span class="top">
							<img src="resources/img/board/city/university.svg" width="300">
							<hr>
							<div class="tip-header">
								<p>Initial cost/rent: 400$ : 25$</p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : 25 $$</li>
								<li>2 houses : 45 $$</li>
								<li>3 houses : 75 $$</li>
								<li>4 houses : 185 $$</li>
								<li>Castle: 2500 $$</li>
							</ul>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/university.svg" alt=""></div>
						<div class="house azure">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="cell south" id="cell9">
					<div class="south tip">
						<span class="town-title badge badge-info">Fountain</span>
						<span class="top">
							<img src="resources/img/board/city/fountain.svg" width="300">
							<hr>
							<div class="tip-header">
								<p>Initial cost/rent: 400$ : 25$</p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : 25 $$</li>
								<li>2 houses : 45 $$</li>
								<li>3 houses : 75 $$</li>
								<li>4 houses : 185 $$</li>
								<li>Castle: 2500 $$</li>
							</ul>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/fountain.svg" alt=""></div>
						<div class="house azure">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="cell south tip" id="cell8">
					<span class="town-title badge badge-info">Wishing</span>
					<span class="top top-small">
						<img src="resources/img/board/city/pond.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<span class="nub"></span>
					</span>
					<img src="resources/img/board/city/wishing_well.svg" alt="" width="75">
				</td>
				<td class="cell south" id="cell7">
					<div class="south tip">
						<span class="town-title badge badge-info">Pond</span>
						<span class="top">
							<img src="resources/img/board/city/pond.svg" width="300">
							<hr>
							<div class="tip-header">
								<p>Initial cost/rent: 400$ : 25$</p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : 25 $$</li>
								<li>2 houses : 45 $$</li>
								<li>3 houses : 75 $$</li>
								<li>4 houses : 185 $$</li>
								<li>Castle: 2500 $$</li>
							</ul>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/pond.svg" alt=""></div>
						<div class="house azure">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="cell south" id="cell6">
					<div class="south tip">
						<span class="town-title badge badge-info">Stones</span>
						<span class="top">
							<img src="resources/img/board/city/magic_stones.svg" width="300">
							<hr>
							<div class="tip-header">
								<p>Initial cost/rent: 400$ : 25$</p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : 25 $$</li>
								<li>2 houses : 45 $$</li>
								<li>3 houses : 75 $$</li>
								<li>4 houses : 185 $$</li>
								<li>Castle: 2500 $$</li>
							</ul>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/magic_stones.svg" alt=""></div>
					</div>
				</td>
				<td class="cell south tip" id="cell5">
					<span class="town-title badge badge-info">Statue</span>
					<span class="top top-small">
						<img src="resources/img/board/city/statue.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<span class="nub"></span>
					</span>
					<img src="resources/img/board/city/statue.svg" alt="" width="75">
				</td>
				<td class="cell south" id="cell4">
					<div class="south tip">
						<span class="town-title badge badge-info">Circus</span>
						<span class="top">
							<img src="resources/img/board/city/circus.svg" width="300">
							<hr>
							<div class="tip-header">
								<p>Initial cost/rent: 400$ : 25$</p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : 25 $$</li>
								<li>2 houses : 45 $$</li>
								<li>3 houses : 75 $$</li>
								<li>4 houses : 185 $$</li>
								<li>Castle: 2500 $$</li>
							</ul>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/circus.svg" alt=""></div>
						<div class="house brown">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="cell south tip" id="cell3">
					<span class="town-title badge badge-info">Ruins</span>
					<span class="top top-small">
						<img src="resources/img/board/city/ruins.svg" width="300">
						<hr>
						<div class="tip-header">
							<p>Initial cost/rent: 400$ : 25$</p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<span class="nub"></span>
					</span>
					<img src="resources/img/board/city/ruins.svg" alt="" width="75">
				</td>
				<td class="cell south" id="cell2">
					<div class="south tip">
						<span class="town-title badge badge-info">Tent</span>
						<span class="top">
							<img src="resources/img/board/city/tent.svg" width="300">
							<hr>
							<div class="tip-header">
								<p>Initial cost/rent: 400$ : 25$</p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : 25 $$</li>
								<li>2 houses : 45 $$</li>
								<li>3 houses : 75 $$</li>
								<li>4 houses : 185 $$</li>
								<li>Castle: 2500 $$</li>
							</ul>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/tent.svg" alt=""></div>
						<div class="house brown">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="cell southeast" id="cell1">
					<span class="label label-info" style=" font-size: 65px;  ">Start</span>
					<img id="player1" src="resources/img/board/player-1.png"></img>
					<img id="player2" src="resources/img/board/player-2.png"></img>
					<img id="player3" src="resources/img/board/player-3.png"></img>
					<img id="player4" src="resources/img/board/player-4.png"></img>
				</td>
			</tr>
		</table>
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
<script type="text/javascript" src="resources/js/bootstrap-tab.js"></script>
<!-- Game-js engine -->
<script src="resources/js/monopoly/monopoly.js" type="text/javascript"></script>

<script type="text/javascript">
	$('#myTab a').click(function(e) {
	    e.preventDefault();
	    $(this).tab('show');
	});
	$('#myTab a:first').tab('show');
</script>
</body>
</html>