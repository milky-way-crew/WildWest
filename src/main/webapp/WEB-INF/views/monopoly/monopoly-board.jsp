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
<link rel="stylesheet" href="resources/css/monopoly-board.css" type="text/css">

<!-- Fonts DONT FORGER TO DOWNLOAD THEM TO USE LOCAL -->
<link href='http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Archivo+Narrow' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Bubblegum+Sans' rel='stylesheet' type='text/css'>
</head>
<!-- NO MORE GREEN BACKGROUND COLORS -->
<!-- <body bgcolor="#CCE7D0"> --> 
<body>
	<jsp:include page="../navbar.jsp" />
	<div class="container" style=" width: 1230px; ">
		<table id="game-table" border="1" rules="all">
			<tr>
				<td class="cell northwest tip" id="cell21">
					<span class="label label-info" style=" top: 0px; text-align: center; ">Free Station</span>
						<img src="resources/img/board/city/sign_left.svg">
				</td>
				<td id="cell22" class="cell north">
					<div class="north tip">
						<span class="bottom">
							<strong class="town-title">Fort</strong>
							<img src="resources/img/board/city/fort.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Initial cost: <strong class="label label-money">$220</strong></p>
								<p>rent: <strong class="label label-money">$150</strong> : <strong class="label label-money">$170</strong>: <strong class="label label-money">$200</strong></p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : <strong class="label label-money">$90</strong></li>
								<li>2 houses : <strong class="label label-money">$250</strong></li>
								<li>3 houses : <strong class="label label-money">$700</strong></li>
								<li>Big Castle: <strong class="label label-money">$1050</strong></li>
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
				<td id="cell23" class="cell north">
					<div class="north tip">
						<img class="town-image" src="resources/img/board/city/tree11.svg" alt="" width="90" style="position:initial;">
						<span class="bottom bottom-small">
							<strong class="town-title">Tree of chance</strong>
							<img src="resources/img/board/city/tree11.svg" width="170">
							<hr>
							<p>Old tree shows you the future way.</p>
							<p>Unless you're lucky you may move to jail</p>
							<hr>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
					</div>
				</td>
				<td id="cell24" class="cell north">
					<div class="north tip">
						<span class="bottom">
							<strong class="town-title">City</strong>
							<img src="resources/img/board/city/city.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Initial cost: <strong class="label label-money">$220</strong></p>
								<p>rent: <strong class="label label-money">$150</strong> : <strong class="label label-money">$170</strong> : <strong class="label label-money">$200</strong></p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : <strong class="label label-money">$90</strong></li>
								<li>2 houses : <strong class="label label-money">$250</strong></li>
								<li>3 houses : <strong class="label label-money">$700</strong></li>
								<li>Big Castle: <strong class="label label-money">$105</strong></li>
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
						<span class="bottom">
							<strong class="town-title">Fortress</strong>
							<img src="resources/img/board/city/fortress.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Initial cost: <strong class="label label-money">$240</strong></p>
								<p>rent: <strong class="label label-money">$170</strong> : <strong class="label label-money">$190</strong> : <strong class="label label-money">$210</strong></p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : <strong class="label label-money">$100</strong></li>
								<li>2 houses : <strong class="label label-money">$300</strong></li>
								<li>3 houses : <strong class="label label-money">$750</strong></li>
								<li>Big Castle: <strong class="label label-money">$1100</strong></li>
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
					<span class="bottom bottom-small">
						<strong class="town-title">Stonehenge star</strong>
						<img src="resources/img/board/city/magic_stones.svg" width="170">
						<p class="tip-header">Initial cost: <strong class="label label-money">$150</strong></p>
						<hr>
						<ul class="tip-content">
						<p>Rent:</p>
							<li>1 stonehenge: <strong class="label label-money">$50</strong></li>
							<li>2 stonehenge: <strong class="label label-money">$100</strong></li>
							<li>3 stonehenge: <strong class="label label-money">$150</strong></li>
							<li>4 stonehenge: <strong class="label label-money">$200</strong></li>
						</ul>
						<span class="nub"></span>
					</span> 
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/magic_stones.svg" alt=""></div>
					</div>
				</td>
				<td id="cell27" class="cell north">
					<div class="north tip">
						<span class="bottom">
							<strong class="town-title">Obelisk of honour</strong>
							<img src="resources/img/board/city/obelisk.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Initial cost: <strong class="label label-money">$260</strong></p>
								<p>rent: <strong class="label label-money">$200</strong> : <strong class="label label-money">$270</strong> : <strong class="label label-money">$310</strong></p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : <strong class="label label-money">$110</strong></li>
								<li>2 houses : <strong class="label label-money">$330</strong></li>
								<li>3 houses : <strong class="label label-money">$800</strong></li>
								<li>Big Castle: <strong class="label label-money">$1150</strong></li>
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
				<td id="cell28" class="cell north">
					<div class="north tip">
						<span class="bottom">
							<strong class="town-title">Arch of victory</strong>
							<img src="resources/img/board/city/arch.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Initial cost: <strong class="label label-money">$260</strong></p>
								<p>rent: <strong class="label label-money">$200</strong> : <strong class="label label-money">$270</strong> : <strong class="label label-money">$310</strong></p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : <strong class="label label-money">$110</strong></li>
								<li>2 houses : <strong class="label label-money">$330</strong></li>
								<li>3 houses : <strong class="label label-money">$800</strong></li>
								<li>Big Castle: <strong class="label label-money">$1150</strong></li>
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
				<td id="cell29" class="cell north">
					<div class="north tip">
						<img class="town-image" src="resources/img/board/city/mountain4.svg" alt="" width="100" style="position:initial;">
						<span class="bottom bottom-small">
							<strong class="town-title">Mountain of anger</strong>
							<img src="resources/img/board/city/mountain4.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Mountains where you can find valuable items or become a victim of robbery</p>
								<p>Try to win this competition</p>
							</div>
							<hr>
							<div class="tip-controls">
								<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
								<a href="javascript:void(0);" class="build btn btn-succes">build</a>
							</div>
							<span class="nub"></span>
						</span>
					</div>
				</td>
				<td id="cell30" class="cell north">
					<div class="north tip">
						<span class="bottom">
							<strong class="town-title">Maze of gold</strong>
							<img src="resources/img/board/city/maze.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Initial cost: <strong class="label label-money">$280</strong></p>
								<p>rent: <strong class="label label-money">$220</strong> : <strong class="label label-money">$300</strong> : <strong class="label label-money">$340</strong></p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : <strong class="label label-money">$120</strong></li>
								<li>2 houses : <strong class="label label-money">$360</strong></li>
								<li>3 houses : <strong class="label label-money">$850</strong></li>
								<li>Big Castle: <strong class="label label-money">$1200</strong></li>
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
						<img src="resources/img/board/city/sign_crossroad.svg">
					<!-- </div> -->
				</td>
			</tr>
			<tr>
				<td class="cell west" id="cell20">
					<div class="west tip">
					<span class="right">
						<strong class="town-title">Graveyard</strong>
						<img src="resources/img/board/city/graveyard.svg" width="170">
						<hr>
						<div class="tip-header">
							<p>Initial cost: <strong class="label label-money">$200</strong></p>
							<p>rent: <strong class="label label-money">$110</strong> : <strong class="label label-money">$190</strong> : <strong class="label label-money">$210</strong></p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : <strong class="label label-money">$80</strong></li>
							<li>2 houses : <strong class="label label-money">$240</strong></li>
							<li>3 houses : <strong class="label label-money">$600</strong></li>
							<li>Big Castle: <strong class="label label-money">$1000</strong></li>
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
				</div></td>
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
											<span class="label" style="
												display: inline;
												background-color: #095;
											">[money status]</span>
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
								<div class="span4 well">
									<div class="row">
										<h3>Action invoked by:</h3> 
											<strong class="label invoker">-</strong> 
									</div>
									<div class="row">
										<h3>Highest price is:</h3>
										<strong class="label label-warning price">0$</strong> by <small class="label price-caller">-</small>
									</div>
									<div class="row rates">
										<h3>rates &gt;&gt;</h3>
										<h3 style=""> 
										<small class="label color-player-1">0$</small>
										<small class="label color-player-2">0$</small>
										<small class="label color-player-3">0$</small>
										<small class="label color-player-4">0$</small>
										</h3>
									</div>
								</div>
								<div class="span3 well">
									<h3 class="lot-name">lot #13</h3>
									<span class="badge badge-info">60s</span>
									<img class="lot-image" src="resources/img/board/city/house.svg" alt="">
								</div> 
								<div class="row well auction-controls">
									<button id="up50" class="square-button">up by 50$</button>
									<button id="up10" class="square-button">up by 10$</button>
								</div>
							</div>
							</div>
						</div>

						<!-- ************* -->
						<!-- [Roll dices]  -->
						<!-- ************* -->
						<div id="roll-dice">
							<img id="diceImg1" class="dice" src="resources/img/board/die1.gif" width="50px" height="50px"/> 
							<img id="diceImg2" class="dice" src="resources/img/board/die1.gif" width="50px" height="50px"/>
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
				<td class="cell east" id="cell32">
					<div class="east tip">
					<span class="left">
						<strong class="town-title">Mine</strong>
						<img src="resources/img/board/city/mine.svg" width="170">
						<hr>
						<div class="tip-header">
							<p>Initial cost: <strong class="label label-money">$300</strong> </p>
							<p> rent: <strong class="label label-money">$260</strong> : <strong class="label label-money">$300</strong> : <strong class="label label-money">$350</strong></p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : <strong class="label label-money">$130</strong></li>
							<li>2 houses : <strong class="label label-money">$390</strong></li>
							<li>3 houses : <strong class="label label-money">$900</strong></li>
							<li>Big Castle: <strong class="label label-money">$1280</strong></li>
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
				</div>
			</td>
			</tr>
			<tr>
				<td class="cell west" id="cell19">
					<div class="west tip">
					<span class="right">
						<strong class="town-title">Monastery</strong>
						<img src="resources/img/board/city/monastery.svg" width="170">
						<hr>
						<div class="tip-header">
							<p>Initial cost: <strong class="label label-money">$180</strong> </p>
							<p>rent: <strong class="label label-money">$100</strong> : <strong class="label label-money">$170</strong> : <strong class="label label-money">$190</strong></p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : <strong class="label label-money">$70</strong></li>
							<li>2 houses : <strong class="label label-money">$200</strong></li>
							<li>3 houses : <strong class="label label-money">$550</strong></li>
							<li>Big Castle: <strong class="label label-money">$950</strong></li>
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
				</div></td>
				<td class="cell east" id="cell33">
					<div class="east tip">
					<span class="left">
						<strong class="town-title">Hunter's Lair</strong>
						<img src="resources/img/board/city/hunter.svg" width="170">
						<hr>
						<div class="tip-header">
							<p>Initial cost: 400$</p>
							<p>rent: <strong class="label label-money">$260</strong> : <strong class="label label-money">$300</strong> : <strong class="label label-money">$350</strong></p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : <strong class="label label-money">$130</strong></li>
							<li>2 houses : <strong class="label label-money">$390</strong></li>
							<li>3 houses : <strong class="label label-money">$900</strong></li>
							<li>Big Castle: <strong class="label label-money">$1280</strong></li>
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
				</div></td>
			</tr>
			<tr>
				<td class="cell west" id="cell18">
					<div class="west tip">
					<span class="right">
						<strong class="town-title">Caravan wreck</strong>
						<img src="resources/img/board/city/caravan_wreck.svg" width="170">
						<hr>
						<div class="tip-header">
							<p> Place where ghosts live. Introduction to fight with them and fight for victory
						</div>
						<hr>
						
						<span class="nub"></span>
					</span>
						<!-- <div class="town-image"> -->
							<img src="resources/img/board/city/caravan_wreck.svg" alt="" width="80">
						<!-- </div> -->
				</div></td>
				<td class="cell east" id="cell34">
					<div class="east tip">
					<span class="left">
						<strong class="town-title">Lucky tree</strong>
						<img src="resources/img/board/city/tree02.svg" width="170">
						<hr>
						<div class="tip-header">
						<p>Lucky tree gives an opportunity to win or lose money or get a release from prison</p>
						</div>
						<span class="nub"></span>
					</span>
					<!-- <div class="town-image"> -->
						<img src="resources/img/board/city/tree02.svg" alt="" width="80">
					<!-- </div> -->
				</div></td>
			</tr>
			<tr>
				<td class="cell west" id="cell17">
					<div class="west tip">
					<span class="right">
						<strong class="town-title">Cathedral</strong>
						<img src="resources/img/board/city/cathedral.svg" width="170">
						<hr>
						<div class="tip-header">
							<p>Initial cost: <strong class="label label-money">$180</strong></p>
							<p>rent: <strong class="label label-money">$100</strong> : <strong class="label label-money">$170</strong> : <strong class="label label-money">$190</strong></p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : <strong class="label label-money">$70</strong></li>
							<li>2 houses : <strong class="label label-money">$200</strong></li>
							<li>3 houses : <strong class="label label-money">$50</strong></li>
							<li>Big Castle: <strong class="label label-money">$950</strong></li>
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
				</div></td>
				<td class="cell east" id="cell35">
					<div class="east tip">
					<span class="left">
						<strong class="town-title">Blacksmith</strong>
						<img src="resources/img/board/city/blacksmith.svg" width="170">
						<hr>
						<div class="tip-header">
							<p>Initial cost: <strong class="label label-money">$330</strong></p>
							<p>rent: <strong class="label label-money">$270</strong> : <strong class="label label-money">$300</strong> : <strong class="label label-money">$360</strong></p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : <strong class="label label-money">$160</strong></li>
							<li>2 houses : <strong class="label label-money">$450</strong></li>
							<li>3 houses : <strong class="label label-money">$1000</strong></li>
							<li>Big Castle: <strong class="label label-money">$1300</strong></li>
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
				</div></td>
			</tr>
			<tr>
				<td class="cell west" id="cell16">
					<div class="west tip">
					<span class="right">
						<strong class="town-title">Stonehenge star</strong>
						<img src="resources/img/board/city/magic_stones.svg" width="170">
						<hr>
						<div class="tip-header">
							<p>Initial cost: <strong class="label label-money">$100</strong></p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
						<p>Rent:</p>
							<li>1 stonehenge: <strong class="label label-money">$50</strong></li>
							<li>2 stonehenge: <strong class="label label-money">$100</strong></li>
							<li>3 stonehenge: <strong class="label label-money">$150</strong></li>
							<li>4 stonehenge: <strong class="label label-money">$200</strong></li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/magic_stones.svg">
					</div>
				</div></td>
				<td class="cell east" id="cell36">
					<div class="east tip">
					<span class="left">
						<strong class="town-title">Stonehenge star</strong>
						<img src="resources/img/board/city/magic_stones.svg" width="170">
						<hr>
						<div class="tip-header">
							<p>Initial cost: <strong class="label label-money">$200</strong></p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
						<p>Rent:</p>
							<li>1 stonehenge: <strong class="label label-money">$50</strong></li>
							<li>2 stonehenge: <strong class="label label-money">$100</strong></li>
							<li>3 stonehenge: <strong class="label label-money">$150</strong></li>
							<li>4 stonehenge: <strong class="label label-money">$200</strong></li>
						</ul>
						<div class="tip-controls">
							<a href="javascript:void(0);" class="mortage btn btn-info">mortage</a>
							<a href="javascript:void(0);" class="build btn btn-succes">build</a>
						</div>
						<span class="nub"></span>
					</span>
					<div class="owner"></div>
					<div class="town-image"><img src="resources/img/board/city/magic_stones.svg" alt=""></div>
				</div></td>
			</tr>
			<tr>
				<td class="cell west" id="cell15" >
					<div class="west tip">
					<span class="right">
						<strong class="town-title">Little Farm</strong>
						<img src="resources/img/board/city/farm.svg" width="170">
						<hr>
						<div class="tip-header">
							<p>Initial cost <strong class="label label-money">$160</strong></p>
							<p>rent: <strong class="label label-money">$90</strong> : <strong class="label label-money">$100</strong> : <strong class="label label-money">$160</strong></p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : <strong class="label label-money">$60</strong></li>
							<li>2 houses : <strong class="label label-money">$180</strong></li>
							<li>3 houses : <strong class="label label-money">$500</strong></li>
							<li>Big Castle: <strong class="label label-money">$950</strong></li>
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
				</div></td>
				<td class="cell east" id="cell37">
					<div class="east tip">
					<span class="left">
						<strong class="town-title">Bridge stone</strong>
						<img src="resources/img/board/city/bridge_stone2.svg" width="170">
						<div class="tip-header">
						<p>Staying on this bridge you get the chance to travel in space</p>
						</div>
						<span class="nub"></span>
					</span> 
						<img src="resources/img/board/city/bridge_stone2.svg" width="80">
					<!-- </div> -->
				</div></td>
			</tr>
			<tr>
				<td class="cell west" id="cell14">
					<div class="west tip">
					<span class="right">
						<strong class="town-title">Fishery</strong>
						<img src="resources/img/board/city/fishery.svg" width="170">
						<hr>
						<div class="tip-header">
							<p>Initial cost: <strong class="label label-money">$140</strong></p>
							<p>rent: <strong class="label label-money">$50</strong> : <strong class="label label-money">$100</strong> : <strong class="label label-money">$150</strong></p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : <strong class="label label-money">$50</strong></li>
							<li>2 houses : <strong class="label label-money">$150</strong></li>
							<li>3 houses : <strong class="label label-money">$450</strong></li>
							<li>Big Castle: <strong class="label label-money">$750</strong></li>
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
				</div></td>
				<td class="cell east" id="cell38">
					<div class="east tip">
					<span class="left">
						<strong class="town-title">Tower square</strong>
						<img src="resources/img/board/city/tower_square.svg" width="170">
						<hr>
						<div class="tip-header">
							<p>Initial cost: <strong class="label label-money">$350</strong></p>
							<p>rent: <strong class="label label-money">$290</strong>: <strong class="label label-money">$310</strong> : <strong class="label label-money">$400</strong></p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : <strong class="label label-money">$180</strong></li>
							<li>2 houses : <strong class="label label-money">$500</strong></li>
							<li>3 houses : <strong class="label label-money">$1100</strong></li>
							<li>Big Castle: <strong class="label label-money">$1500</strong></li>
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
				</div></td>
			</tr>
			<tr>
				<td class="cell west" id="cell13">
					<div class="west tip">
					<span class="right">
					<strong class="town-title">Shipwreck</strong>
						<img src="resources/img/board/city/shipwreck.svg" width="170" height="300">
						<hr>
						<div class="tip-header">
						<p>After shipwreck you can find valuable items or become a victim of robbery of former sailors</p>
						</div>
						<span class="nub"></span>
					</span> 
						<!-- <div class="town-image"> -->
							<img src="resources/img/board/city/shipwreck.svg" width="80">
						<!-- </div> -->
				</div></td>
				<td class="cell east" id="cell39">
					<div class="east tip">
					<!-- <div class="town-image"> -->
					<span class="left">
						<strong class="town-title">Sword of King Arthur.</strong>
			<ul class="tip-content">
						</ul>
						<img src="resources/img/board/city/sword_in_the_stone.svg" width="170" height="300">
						<p class="tip-header">The legend states that only one who can pull the sword can go ahead. Otherwise you pay money for going <strong class="label label-money">$150</strong></p>
						<span class="nub"></span>
					</span> 
							<img src="resources/img/board/city/sword_in_the_stone.svg" alt="" width="75">
					<!-- </div> -->
				</div></td>
			</tr>
			<tr>
				<td class="cell west" id="cell12">
					<div class="west tip">
					<span class="right">
						<strong class="town-title">Windmill</strong>
						<img src="resources/img/board/city/windmill.svg" width="170">
						<hr>
						<div class="tip-header">
							<p>Initial cost: <strong class="label label-money">$140</strong></p>
							<p>rent: <strong class="label label-money">$50</strong> : <strong class="label label-money">$100</strong> : <strong class="label label-money">$150</strong>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						 
						<hr>
						<ul class="tip-content">
							<li>1 houses : <strong class="label label-money">$50</strong></li>
							<li>2 houses : <strong class="label label-money">$150</strong></li>
							<li>3 houses : <strong class="label label-money">$450</strong></li>
							<li>Big Castle: <strong class="label label-money">$750</strong></li>
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
				</div></td>
				<td class="cell east" id="cell40">
					<div class="east tip">
					<span class="left">
						<strong class="town-title">Tower round</strong>
						<img src="resources/img/board/city/tower_round.svg" width="170">
						<hr>
						<div class="tip-header">
							<p>Initial cost: <strong class="label label-money">$400</strong></p>
							<p>rent: <strong class="label label-money">$300</strong> : <strong class="label label-money">$330</strong> : <strong class="label label-money">$410</strong></p>
							<small class="tip-owner label label-info">No one property</small>
						</div>
						<hr>
						<ul class="tip-content">
							<li>1 houses : <strong class="label label-money">$200</strong></li>
							<li>2 houses : <strong class="label label-money">$600</strong></li>
							<li>3 houses : <strong class="label label-money">$1400</strong></li>
							<li>Big Castle: <strong class="label label-money">$2000</strong></li>
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
				</div></td>
			</tr>
			<tr>
				<td class="cell southwest" id="cell11">
							<img src="resources/img/board/city/jailhouse.svg" alt="">
							<span class="label label-info">Jail</span>
						<!-- </div> -->
				</td>
				<td class="cell south" id="cell10">
					<div class="south tip">
						<span class="top">
							<strong class="town-title">University</strong>
							<img src="resources/img/board/city/university.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Initial cost: <strong class="label label-money">$120</strong></p>
								<p>rent: <strong class="label label-money">$50</strong> : <strong class="label label-money">$70</strong> : <strong class="label label-money">$90</strong></p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : <strong class="label label-money">$40</strong></li>
								<li>2 houses : <strong class="label label-money">$100</strong></li>
								<li>3 houses : <strong class="label label-money">$300</strong></li>
								<li>Big Castle: <strong class="label label-money">$600</strong></li>
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
						<span class="top">
							<strong class="town-title">Fountain</strong>
							<img src="resources/img/board/city/fountain.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Initial cost: <strong class="label label-money">$100</strong></p>
								<p>rent: <strong class="label label-money">$40</strong> : <strong class="label label-money">$60</strong> : <strong class="label label-money">$90</strong></p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : <strong class="label label-money">$30</strong></li>
								<li>2 houses : <strong class="label label-money">$90</strong></li>
								<li>3 houses : <strong class="label label-money">$270</strong></li>
								<li>Big Castle: <strong class="label label-money">$550</strong></li>
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
				<td class="cell south" id="cell8">
					<div class="south tip">
					
						<span class="top top-small">
						<strong class="town-title">Well</strong>
							<img src="resources/img/board/city/wishing_well.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Well where live mermaids. They can point you the way. Be gentle with them, or they will send you in jail</p>
							</div>
							<hr>
							<span class="nub"></span>
						</span>
						<div class="town-image">
							<img src="resources/img/board/city/wishing_well.svg" alt="" width="100">
						</div>
					</div>
				</td>
				<td class="cell south" id="cell7">
					<div class="south tip">
						<span class="top">
							<strong class="town-title">Pond</strong>
							<img src="resources/img/board/city/pond.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Initial cost: <strong class="label label-money">$100</strong>:</p>
								<p>rent: <strong class="label label-money">$40</strong> : <strong class="label label-money">$60</strong> : <strong class="label label-money">$90</strong></p> 
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : <strong class="label label-money">$30</strong></li>
								<li>2 houses : <strong class="label label-money">$90</strong></li>
								<li>3 houses : <strong class="label label-money">$270</strong></li>
								<li>Big Castle: <strong class="label label-money">$550</strong></li>
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
						<span class="top">
							<strong class="town-title">Stonehenge star</strong>
							<img src="resources/img/board/city/magic_stones.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Initial cost: <strong class="label label-money">$50</strong></p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
							<p>Rent:</p>
							<li>1 stonehenge: <strong class="label label-money">$50</strong></li>
							<li>2 stonehenge: <strong class="label label-money">$100</strong></li>
							<li>3 stonehenge: <strong class="label label-money">$150</strong></li>
							<li>4 stonehenge: <strong class="label label-money">$200</strong></li>
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
				<td class="cell south" id="cell5">
					<div class="south tip">
						<span class="top top-small">
							<strong class="town-title">Donation</strong>
							<img src="resources/img/board/city/statue.svg" width="170">
							<hr>
							<div class="tip-header">
							<p>Donation price:
								<strong class="label label-money">$150</strong></p>
							</div>
							<hr>
								<p>Pay donation to God of War </p>
								<p>Paying it he can become your patron</p>
								<hr>
							<span class="nub"></span>
						</span>
						<div class="town-image">
							<img src="resources/img/board/city/statue.svg" alt="" width="90">
						</div>
					</div>
				</td>
				<td class="cell south" id="cell4">
					<div class="south tip">
						<span class="top">
							<strong class="town-title">Circus</strong>
							<img src="resources/img/board/city/circus.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Initial cost: <strong class="label label-money">$60</strong></p>
								<p>rent: <strong class="label label-money">$20</strong> : <strong class="label label-money">$30</strong> : <strong class="label label-money">$50</strong></p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : <strong class="label label-money">$30</strong></li>
								<li>2 houses : <strong class="label label-money">$80</strong></li>
								<li>3 houses : <strong class="label label-money">$190</strong></li>
								<li>Big Castle: <strong class="label label-money">$450</strong></li>
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
				<td class="cell south" id="cell3">
					<div class="south tip">
						<span class="top top-small">
							<strong class="town-title">Ruins</strong>
							<img src="resources/img/board/city/ruins.svg" width="170">
							<hr>
							<p>Ruins of a time machine, giving the chance to move</p>
							<p>If you are lucky you will be able to take ownership of stonehenge star </p>
							<hr>
							<span class="nub"></span>
						</span>
						<div class="town-image">
							<img src="resources/img/board/city/ruins.svg" alt="" width="90">
						</div>
					</div>
				</td>
				<td class="cell south" id="cell2">
					<div class="south tip">
						<span class="top">
							<strong class="town-title">Tent</strong>
							<img src="resources/img/board/city/tent.svg" width="170">
							<hr>
							<div class="tip-header">
								<p>Initial cost: <strong class="label label-money">$50</strong></p>
								<p>rent: <strong class="label label-money">$20</strong> : <strong class="label label-money">$30</strong> : <strong class="label label-money">$50</strong></p>
								<small class="tip-owner label label-info">No one property</small>
							</div>
							<hr>
							<ul class="tip-content">
								<li>1 houses : <strong class="label label-money">$20</strong></li>
								<li>2 houses : <strong class="label label-money">$60</strong></li>
								<li>3 houses : <strong class="label label-money">$180</strong></li>
								<li>Big Castle: <strong class="label label-money">$430</strong></li>
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
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
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
	$('#myTab [href=#chat-tab]').tab('show');
	if (!window.WebSocket) {
		alert('Sorry buddy, you cannot player this game without browser, taht supports web-sockets');
	}
</script>
</body>
</html>
