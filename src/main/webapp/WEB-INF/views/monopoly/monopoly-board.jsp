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
<link href='http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Archivo+Narrow' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Bubblegum+Sans' rel='stylesheet' type='text/css'>
</head>
<!-- NO MORE GREEN BACKGROUND COLORS -->
<!-- <body bgcolor="#CCE7D0"> --> 
<body>
	<!-- <div id="main-container"> -->
	<div class="container" style=" width: 1230px; ">
		<table id="game-table" border="1" rules="all">
			<tr>
				<td class="northwest" id="cell21">
					<center>
						<img src="resources/img/board/city/sign_left.svg">
					</center>
				</td>
				<td id="cell22" class="north">
					<div class="north">
						<div class="owner"></div>
						<div class="town-image">
							<img src="resources/img/board/city/fort.svg">
						</div>
						<div class="house red">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell23" class="north">
					<img src="resources/img/board/city/tree11.svg" alt="" width="75">
				</td>
				<td id="cell24" class="north">
					<div class="north">
						<div class="owner"></div>
						<div class="town-image">
							<img src="resources/img/board/city/city.svg">
						</div>
						<div class="house red">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell25" class="north">
					<div class="north">
						<div class="owner"></div>
						<div class="town-image">
							<img src="resources/img/board/city/fortress.svg">
						</div>
						<div class="house red">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell26" class="north tip">
					<span class="bottom">
						<img src="resources/img/board/city/magic_stones.svg" width="300">
						<p class="tip-header"><strong class="active">Stonehenge star</strong><hr></p>
						<ul class="tip-content">
							<li>Collect them all to get ultimate power.</li>
						</ul>
						<span class="nub"></span>
					</span> 
					<div class="north">
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/magic_stones.svg" alt=""></div>
					</div>
				</td>
				<td id="cell27" class="north">
					<div class="north">
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/obelisk.svg" alt=""></div>
						<div class="house yellow">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell28" class="north">
					<div class="north">
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/arch.svg" alt=""></div>
						<div class="house yellow">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td id="cell29" class="north">
					<img src="resources/img/board/city/mountain4.svg" alt="" width="75">
				</td>
				<td id="cell30" class="north">
					<div class="north"> 
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/maze.svg" alt=""></div>
						<div class="house yellow">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="northeast" id="cell31">
					<!-- <div class="town-image"> -->
					<center>
						<img src="resources/img/board/city/sign_crossroad.svg">
					</center>
					<!-- </div> -->
				</td>
			</tr>
			<tr>
				<td class="west" id="cell20">
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
					<div class="container well" id="center">
						<div class="tabbable">
							<ul id="myTab" class="nav nav-tabs">
								<li><a href="#minimap-tab" data-toggle="tab">Mini-map</a></li>
								<li><a href="#chat-tab" data-toggle="tab">Chat</a></li>
								<!-- <li><a href="#settings" data-toggle="tab">Settings</a></li> -->
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
												<td class="mini brown" id="miniCell4"></td>
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
										<div id="money">
											<span class="label color-player-1" id="money-player-1" >0$</span>
											<span class="label color-player-2" id="money-player-2" >0$</span>
											<span class="label color-player-3" id="money-player-3" >0$</span>
											<span class="label color-player-4" id="money-player-4" >0$</span>							
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
					<div class="town-image">
						<img src="resources/img/board/city/mine.svg" alt="">
					</div>
					<div class="house green">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="west" id="cell19">
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/monastery.svg">
					</div>
					<div class="house orange">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td class="east" id="cell33">
					<div class="owner"></div>
					<div class="town-image"><img src="resources/img/board/city/hunter.svg" alt=""></div>
					<div class="house green">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="west" id="cell18">
					<center>
						<!-- <div class="town-image"> -->
							<img src="resources/img/board/city/caravan_wreck.svg" alt="" width="80">
						<!-- </div> -->
					</center>
				</td>
				<td class="east" id="cell34">
					<!-- <div class="town-image"> -->
					<center>
						<img src="resources/img/board/city/tree02.svg" alt="" width="80">
					</center>
					<!-- </div> -->
				</td>
			</tr>
			<tr>
				<td class="west" id="cell17">
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/cathedral.svg" 
							onclick="this.src='resources/img/board/city/cathedral_outline.svg'">
					</div>
					<div class="house orange">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td class="east" id="cell35">
					<div class="owner"></div>
					<div class="town-image"><img src="resources/img/board/city/blacksmith.svg" alt=""></div>
					<div class="house green">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="west" id="cell16">
					<div class="owner"></div>
					<div class="town-image">
						<img src="resources/img/board/city/magic_stones.svg">
					</div>
				</td>
				<td class="east" id="cell36">
					<div class="owner"></div>
					<div class="town-image"><img src="resources/img/board/city/magic_stones.svg" alt=""></div>
				</td>
			</tr>
			<tr>
				<td class="west tip" id="cell15" >
					<span class="right">
						<img src="resources/img/board/city/farm.svg" width="300">
						<p class="tip-header">Initial cost: 4 000$ <small class="label label-info">owner</small></p>

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
					<!-- I KNOW ITS BAD, BUT WANT CAN I DO AGAINST POWER OF HTML -->
					<div class="town-image" style=" margin-top: -10px; ">
						<img src="resources/img/board/city/farm.svg">
					</div>
					<div class="house lightred">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td class="east" id="cell37">
					<!-- <div class="town-image"> -->
					<center>
						<img src="resources/img/board/city/bridge_stone2.svg" width="80">
					</center>
					<!-- </div> -->
				</td>
			</tr>
			<tr>
				<td class="west tip" id="cell14">
					<span class="right">
						<p class="tip-header">Fishery house || Rent: 30$$: </p>
						<p class="tip-header">Cost: 40000$ -- Rent with: </p>
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
						<img src="resources/img/board/city/fishery.svg">
					</div>
					<div class="house lightred">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
				<td class="east" id="cell38">
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
				<td class="west tip" id="cell13">
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
				<td class="east" id="cell39">
					<!-- <div class="town-image"> -->
						<center>
							<img src="resources/img/board/city/sword_in_the_stone.svg" alt="" width="75">
						</center>
					<!-- </div> -->
				</td>
			</tr>
			<tr>
				<td class="west tip" id="cell12">
					<span class="right">
						<center>
							<img src="resources/img/board/city/windmill.svg" width="300">
							<p class="tip-header">Winmill || Rent: 30$$: </p>
						</center>
						<p class="tip-header">Cost: 40000$ -- Rent with: </p>
						<ul class="tip-content">
							25$ -> 45$ -> 75$ -> 185$
						</ul>
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
				<td class="east tip" id="cell40">
					<span class="left">
						<img src="resources/img/board/city/tower_round.svg" width="300">
						<p class="tip-header">Defence tower ;)</p>
						<p class="tip-header">Cost: 40000$ -- Rent with: </p>
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
						<img src="resources/img/board/city/tower_round.svg">
					</div>
					<div class="house blue">
						<img src="resources/img/board/emptyhouse.png">
					</div>
				</td>
			</tr>
			<tr>
				<td class="southwest" id="cell11">
					<center>
						<!-- <div class="town-image"> -->
							<img src="resources/img/board/city/jailhouse.svg" alt="">
						<!-- </div> -->
					</center>
				</td>
				<td class="south" id="cell10">
					<div class="south">
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/university.svg" alt=""></div>
						<div class="house azure">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="south" id="cell9">
					<div class="south">
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/fountain.svg" alt=""></div>
						<div class="house azure">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="south" id="cell8">
					<img src="resources/img/board/city/wishing_well.svg" alt="" width="75">
				</td>
				<td class="south" id="cell7">
					<div class="south">
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/pond.svg" alt=""></div>
						<div class="house azure">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="south" id="cell6">
					<div class="south">
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/magic_stones.svg" alt=""></div>
					</div>
				</td>
				<td class="south" id="cell5">
					<img src="resources/img/board/city/statue.svg" alt="" width="75">
				</td>
				<td class="south" id="cell4">
					<div class="south">
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/circus.svg" alt=""></div>
						<div class="house brown">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="south" id="cell3">
					<img src="resources/img/board/city/ruins.svg" alt="" width="75">
				</td>
				<td class="south" id="cell2">
					<div class="south tip">
						<span class="top">
							<img src="resources/img/board/city/tent.svg" alt="">
							<p class="tip-header">Cost: 40$</p>
							<ul class="tip-content">
								<li>1 houses : 25$</li>
								<li class="active">2 houses : 45$</li>
								<li>3 houses : 75$</li>
								<li>4 houses : 185$</li>
								<li>Hotel: 25$</li>
							</ul>
							<span class="nub"></span>
						</span> 
						<div class="owner"></div>
						<div class="town-image"><img src="resources/img/board/city/tent.svg" alt=""></div>
						<div class="house brown">
							<img class="north_position_house" src="resources/img/board/emptyhouse.png"></img>
						</div>
					</div>
				</td>
				<td class="southeast" id="cell1">
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