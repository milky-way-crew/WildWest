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
	href="resources/css/personal.css" />
<title>KaBoom</title>
</head>
<body>

	<div class="container ">
	<div class="span10 well" id="top">
		<!-- 
		<h1>Oh, hi doggy.</h1>
		 -->
		<h1>
			My personal account <%-- <c:out value="${user.nickname}" />  --%>
		</h1>
		<p><%=new Date()%>  <a id="logout" href="./logout" class="btn btn-danger">Logout</a></p>
	</div>
	
	
	<!-- -----------------------------avatar---------------------------------- -->	
		<div id="avatarDiv" class="span4">
		<%-- <center> --%>
		<label>My avatar</label>
		<!-- 	<img id="avatar" src="resources/img/avatar/smile0.jpg" alt=""> -->
			<%--  <img id="avatar"  alt="" src=<c:out value="${user.avatar}" />>  --%>
			<a id="avatarka" target="_blank">
        <div ><img id="avatar"  alt="" src=<c:out value="${user.avatar}" />></div>
       <div id="hope">New Avatar</div>
    </a>
			 <p><a id="edit" class="btn btn-large">&#9829; Edit avatar</a></p> 
  <!--   	<p><a id="editInformation" class="btn btn-primary">&#9700; Edit My Information</a> </p> -->
  <div >
  	<label>Control Panel</label>
	<p><a id="editInformation" class="btn btn-primary">&#9700; Edit My Information</a> </p>
	<p><a id="chatRoom" href="./chatRooms" class="btn btn-primary">Link to game rooms</a></p>
	<p><a href="./gibbet" id="editInformation" class="btn btn-primary"> Gibbet solo</a> </p>
		</div>				
			
		 <%-- </center>  --%>
		</div>
<!-- -----------------------------end avatar---------------------------------- -->		
		
		
<!-- -----------------------------buttons---------------------------------- -->
		<div id="buttons"  class="span6">
	 <%-- 	<center>   --%>
	 	<div id="infoDiv" class="well">
		<label  id="labelMyInfo">My information</label>
		<table class="table3" border=2 cellspacing=3 cellpadding=3 id="tableInfo">
						<tbody>
						<tr>
							<td  width=100><b>Login</b></td>
							<td id="userlogin" width=150><b><c:out value="${user.login}" /></b></td>
						</tr>
						<tr>
							<td width=100><b>Nickname</b></td>
							<td id="usernickname" width=150><b><c:out value="${user.nickname}" /></b></td>
						</tr>
						<tr>
							<td width=100><b>e-mail</b></td>
							<td id="useremail" width=150><b><c:out value="${user.email}" /></b></td>
						</tr>
						<tr>
							<td width=100><b>Registration</b></td>
							<td width=150><b><c:out value="${user.userDate}" /></b></td>
						</tr>
						</tbody>
					</table>
					</div>
				<p></p>
				<div  id="stat">
				<label id="labelStat">My statistic</label>
		<table  class="table3" border=2 cellspacing=3 cellpadding=3 align="center" id="tableStat">
						<tr>
							<td width=100><b>Name</b></td>
							<td width=150><b>Game</b></td>
							<td width=150><b>GameWin</b></td>
							<td width=150><b>Point</b></td>
						</tr>
						<%-- <tr>
							<td width=100><b>Monopoly</b></td>
							<td width=150><b><c:out value="${user.login}" /></b></td>
							<td width=150><b><c:out value="${user.login}" /></b></td>
							<td width=150><b><c:out value="${user.login}" /></b></td>
						</tr>
						<tr>
							<td width=100><b>Chess</b></td>
							<td width=150><b><c:out value="${user.login}" /></b></td>
							<td width=150><b><c:out value="${user.login}" /></b></td>
							<td width=150><b>0</b></td>
						</tr>
						<tr>
							<td width=100><b>Gibbet</b></td>
							<td width=150><b><c:out value="${user.login}" /></b></td>
							<td width=150><b><c:out value="${user.login}" /></b></td>
							<td width=150><b>0</b></td>
						</tr>
						<tr>
							<td width=100><b>Drow</b></td>
							<td width=150><b><c:out value="${user.login}" /></b></td>
							<td width=150><b><c:out value="${user.login}" /></b></td>
							<td width=150><b>0</b></td>
						</tr> --%>
					</table>	
					</div>
 	<%-- 	</center> --%> 
		</div>
<!-- -----------------------------end buttons---------------------------------- -->


<!-- -----------------------------modal edit Avatar---------------------------------- -->
		<div id="editAvatar" class="modal" style="display:none ;">
	<center>
		<p> <input type="text" id="number"> </p>
			<div id="images" class="modal-header">
				<img id="image" src="resources/img/avatar/emo1.gif" >
			</div>
			<p><a class="btn btn-large" id="previous"><i class=" icon-arrow-left"></i></a>
			<a href="#" class="btn btn-large" id="setAvatar" data-dismiss="modal"><i class=" icon-ok"></i></a>
			<a class="btn btn-large" id="next"><i class=" icon-arrow-right"></i></a></p>
			<p><a href="#" class="btn btn-danger" data-dismiss="modal" id="back">Back
			<i class=" icon-circle-arrow-left"></i></a></p>
		</center>
		</div>
	<!-- -----------------------------end modal  edit Avatar---------------------------------- -->
	
	<!-- -----------------------------modal edit Info---------------------------------- -->
	<%--  <center>  --%>
		<div id="editInf" class="modal" style="display:none ;">
		<p></p>
		<label id="modal1" >Login</label>
		<p align="center">  <input type="text" id="login" value='<c:out value="${user.login}"></c:out>'>
		<a id="loginBtn" class="btn btn-large"> Edit</a> </p>
		
		<label id="modal1" >NickName</label>
		<p align="center">	 <input type="text" id="nickname" value='<c:out value="${user.nickname}"></c:out>'>
		<a id="nicknameBtn" class="btn btn-large"> Edit</a> </p>
		
		<label id="modal1" >Email</label>
		<p align="center"> <input type="text" id="email" value='<c:out value="${user.email}"></c:out>'>
		<a id="emailBtn" class="btn btn-large"> Edit</a> </p>
		
		 <label id="modal1" >Password</label>
		<p align="center"> <input type="text" id="password" >
		<a id="passwordBtn" class="btn btn-large" > Edit</a> </p>
		<p align="center"> <input type="text" id="confirmPassword" > 
		<a id="passBtn" class="btn btn-large"> Edit</a></p>
			<p align="center"><a href="#" class="btn btn-danger" data-dismiss="modal" id="back">Back
			<i class=" icon-circle-arrow-left"></i></a></p>
		</div>
	 	<%-- </center>  --%>
	<!-- -----------------------------end modal  edit Info---------------------------------- -->
	</div>
	<script type="text/javascript" src="resources/js/personal.js"></script>
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
