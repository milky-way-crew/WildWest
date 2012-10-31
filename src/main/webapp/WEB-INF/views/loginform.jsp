<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="resources/css/bootstrap.css" rel="stylesheet">
<link href="resources/css/bootstrap-responsive.css" rel="stylesheet">
<title>Login form</title>
</head>
<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#" name="top">MONOPOLY</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="#"><i class="icon-home"></i> Home</a></li>
						<li class="divider-vertical"></li>
						<li class="active"><a href="#"><i class="icon-user"></i>
								Profile</a></li>
						<li class="divider-vertical"></li>
						<li><a href="#"><i class="icon-globe"></i>TOP-100</a></li>
						<li class="divider-vertical"></li>
					</ul>
					<ul class="nav pull-right">
						<li class="divider-vertical"></li>
						<li class="dropdown"><a class="dropdown-toggle" href="#"
							data-toggle="dropdown">Sign In <strong class="caret"></strong></a>
							<div class="dropdown-menu"
								style="padding: 15px; padding-bottom: 0px;">
								<form:form id="loginHere" method='post' action='login'
									commandName="user">
									<fieldset>
										<h2>
											<legend>Sign in</legend>
										</h2>
										<div class="control-group">
											<label class="control-label">Login</label>
											<div class="controls">
												<div class="box transparent">
													<form:input style="margin-bottom: 15px;" type="text"
														path="login" class="input-xlarge" id="login" name="login"
														rel="popover" data-placement='left'
														data-content="What is your login, buddy?"
														data-original-title="Login field" />
													<form:errors path="login" cssClass="btn-mini btn-danger"></form:errors>
												</div>
											</div>
										</div>


										<div class="control-group">
											<h3>
												<label class="control-label">Password</label>
											</h3>
											<div class="controls">
												<div class="box transparent">
													<form:input style="margin-bottom: 15px;" type="password"
														path="password" class="input-xlarge" id="password"
														name="password" rel="popover" data-placement='left'
														data-content="What is your password?"
														data-original-title="Password field" />
													<form:errors path="password" cssClass="btn-mini btn-danger"></form:errors>
												</div>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label"></label>
											<div class="controls">
												<button type="submit" id="sign-in"
													class="btn btn-primary btn-block">
													<i class="icon-user icon-white"></i>Sign in
												</button>
												or <a class="btn btn-primary btn-success" href="register"><font
													color="white"><i class="icon-heart icon-white"></i>
														Register</font></a>
											</div>
										</div>

									</fieldset>
								</form:form>
							</div></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</div>
		<!--/.navbar-inner -->
	</div>
	<!--/.navbar -->
	<div class="container well">

		<div class="row" align="center">
			<div class="span8 offset2">
				<div id="myCarousel" class="carousel slide">
					<div class="carousel-inner">
						<div class="item active">
							<img src=resources/images/monopoly-for-ipad-02.jpg />
							<div class="carousel-caption">
								<h4>First label</h4>
							</div>
						</div>
						<div class="item">
							<img src="resources/images/monopoly-for-ipad-01.jpg" />
							<div class="carousel-caption">
								<h4>Second label</h4>
							</div>
						</div>
						<div class="item">
							<img src="resources/images/monopoly-for-ipad-03.jpg" />
							<div class="carousel-caption">
								<h4>Third label</h4>
							</div>
						</div>
					</div>
					<!-- Carousel nav -->
					<a class="carousel-control left" href="#myCarousel"
						data-slide="prev">&lsaquo;</a> <a class="carousel-control right"
						href="#myCarousel" data-slide="next">&rsaquo;</a>
				</div>
			</div>
		</div>
	</div>
	<script src="resources/js/jquery-1.7.2.js"></script>
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
	<script type="text/javascript" src="resources/js/login.js"></script>
	<script src="resources/js/bootstrap-carousel.js"></script>
</body>
</html>
