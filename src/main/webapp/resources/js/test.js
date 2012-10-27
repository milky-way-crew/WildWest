var ajaxUrl = "ajax";

$("#sender").click(function() {
	$.ajax({
		url : 'ajax',
		type : "POST",
		data : {
			type : "message",
			data : $("#message").val()
		},
		success : function(json) {
			// Adding own message to chat
			$("#chat").append("me: " + $("#message").val() + "\n");
			// Clear chat
			$("#message").val("");
		}
	});
});

$("#update").click(function() {
	$.ajax({
		url : 'ajax',
		type : "POST",
		data : {
			type : "update",
			data : {}
		},
		success : function(json) {
			// Hot stuff
			$("#chat").append("\n");
			$("#chat").append(json);
		}
	});
});
