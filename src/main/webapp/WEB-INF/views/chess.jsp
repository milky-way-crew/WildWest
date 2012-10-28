<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p id="content"></p>
<button id="sender"></button>


	<textarea class="input-xxlarge" id="chat" name="message" rows=5
			cols=170 ></textarea>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript">

var ajax_url = "chess2";

var sender = function() {
	$.ajax({
		url : ajax_url,
		type : "POST",
		data : {},
		success : function(json) {
			// Adding own message to chat
			alert(json);
			$("#chat").append(json);
		}
	});
};

$("#sender").click(sender);

</script>
</body>
</html>