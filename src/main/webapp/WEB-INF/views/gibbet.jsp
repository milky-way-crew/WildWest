<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet" href="resources/bootstrap-responsive.css"
	type="text/css">
	<link type="text/css" rel="stylesheet"
	href="resources/css/gibbet.css" />
		<link href='http://fonts.googleapis.com/css?family=Margarine'
	rel='stylesheet' type='text/css'>
<title>Gibbet</title>
</head>
<body>
<!-- top panel -->
	<jsp:include page="navbar.jsp" />
	<!-- top panel -->
	<div class="container " >
	<div id="game">
	<div class="hero-unit" >
		<h1>
			Welcome to gibbet 
			<c:out value="${user.login}" /> 
		</h1>
	</div>
	

	
		<div class="span6 well" align="center" id="control">
		<select name="list" id="list" onChange="javascript: selfSubmit();"> 
		<option value="random">random</option>
		<option value="mathematic">mathematic</option>
		<option value="physic">physic</option>
		<option value="names">names</option>
		<option value="developer">developer</option>
		</select>
		<a  class="btn btn-primary btn-medium"  id="select">OK!<i class="icon-white icon-ok"></i></a> 
			<label id="lab">Word</label>
					<form name="messages" >
						<input  type="text" id="word"> 
					</form>
					<input type="text" id="live" value="0">Your life:</input>
				<p id="controls">
				
				<p><a class="btn btn-primary btn-medium" id="q" >Q</a>
				<a class="btn btn-primary btn-medium" id="w" >W</a>
				<a class="btn btn-primary btn-medium" id="e" >E</a>
				<a class="btn btn-primary btn-medium" id="r" >R</a>
				<a class="btn btn-primary btn-medium" id="t" >T</a>
				<a class="btn btn-primary btn-medium" id="y" >Y</a>
				<a class="btn btn-primary btn-medium" id="u" >U</a>
				<a class="btn btn-primary btn-medium" id="i" >I</a>
				<a class="btn btn-primary btn-medium" id="o" >O</a>
				<a class="btn btn-primary btn-medium" id="p" >P</a>
				</p>
				<p><a class="btn btn-primary btn-medium" id="a" >A</a>
				<a class="btn btn-primary btn-medium" id="s" >S</a>
				<a class="btn btn-primary btn-medium" id="d" >D</a>
				<a class="btn btn-primary btn-medium" id="f" >F</a>
				<a class="btn btn-primary btn-medium" id="g" >G</a>
				<a class="btn btn-primary btn-medium" id="h" >H</a>
				<a class="btn btn-primary btn-medium" id="j" >J</a>
				<a class="btn btn-primary btn-medium" id="k" >K</a>
				<a class="btn btn-primary btn-medium" id="l" >L</a>
				</p>
				<p><a class="btn btn-primary btn-medium" id="z" >Z</a>
				<a class="btn btn-primary btn-medium" id="x" >X</a>
				<a class="btn btn-primary btn-medium" id="c" >C</a>
				<a class="btn btn-primary btn-medium" id="v" >V</a>
				<a class="btn btn-primary btn-medium" id="b" >B</a>
				<a class="btn btn-primary btn-medium" id="n" >N</a>
				<a class="btn btn-primary btn-medium" id="m" >M</a>
				</p>
				</p>
					<a href="./personal" class="btn btn-danger" id="exit">Exit<i class="icon-white icon-remove"></i></a>
		</div>
		
		<div class="span4">
							 <center> 
			<img id="image" src="resources/img/gibbet/gibbet0.jpg" >
		 </center> 
		</div>
	</div>
	</div>
	
	<div class="container">
		<div id="win" class="modal" style="display:none ;">
		<p>You win</p>
			<div class="modal-header">
				<img id="image" src="resources/img/gibbet/win.jpg" >
			<!-- </div>
			 <div class="modal-body">
				<h2></h2>
				<p>Enter room name:</p>
				<input type="text" id="roomName" size=100
					placeholder="Enter room name...">
			</div>  -->
			<!--  <div class="modal-footer">
				<a id="createRoom" href="#" class="btn btn-success">OK!</a> 
			</div> --> 
		</div>
	</div>
	
	<div class="container">
		<div id="lose" class="modal" style="display:none ;">
		<p>You lose</p>
			<div class="modal-header">
				<img id="image" src="resources/img/gibbet/lose.jpg" >
			</div>
			<!--  <div class="modal-body">
				<h2></h2>
				<p>Enter room name:</p>
				<input type="text" id="roomName" size=100
					placeholder="Enter room name...">
			</div> --> 
			 <!-- <div class="modal-footer">
				<a id="createRoom" href="#" class="btn btn-success">OK!</a> 
			</div> --> 
		</div>
	</div>
	<script type="text/javascript" src="resources/js/gibbet.js"></script>
	<script type="text/javascript" src="resources/js/jquery.js"></script>
	<script type="text/javascript"
		src="resources/js/bootstrap-transition.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-alert.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-modal.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-dropdown.js"></script>
	<script type="text/javascript"
		src="resources/js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-tab.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-tooltip.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-popover.js"></script>
	<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-button.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-collapse.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-carousel.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.js"></script>
	<script type="text/javascript"
		src="resources/js/bootstrap-typeahead.js"></script>
</body>
</html>
