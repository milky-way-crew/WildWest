<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>
			Chess
		</title>
		<link href="./resources/css/bootstrap.css" rel="stylesheet" type="text/css">
		<link href="./resources/css/chess.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<a href="./chess/exit" class="btn btn-large" style="position:absolute;">exit game&gt;</a>
		<div class="container" style="width:700px">
			<div id="info" class="well" align="center">
				<h4 class="info">
					Waiting for oponents
				</h4>
			</div>
			<div id="chess" class="well" align="center">
				<!-- <center> -->
				<table id="chess_board" cellpadding="0" cellspacing="0">
					<tbody>
						<c:forEach var="i" begin="0" end="6">  
							<tr>
	  							<c:forEach var="j" begin="0" end="6">
									<td id="<c:out value="${i}${j}"/>">
										<img class="figure">
									</td>
	  							</c:forEach>
							</tr>
						</c:forEach> 
					</tbody>
				</table>
			</div>
		</div>

		<script type="text/javascript" src="./resources/js/jquery.js"></script>
		<script type="text/javascript" src="./resources/js/jquery-ui.js"></script>
		<script type="text/javascript" src="./resources/js/bootstrap-modal.js"></script>
		<script type="text/javascript" src="./resources/js/bootbox.min.js"></script>
		<script type="text/javascript" src="./resources/js/chess.js"></script>
	</body>
</html/>