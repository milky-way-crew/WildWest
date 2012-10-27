var ajax_url = "ajax";

var sender = function() {
	$.ajax({
		url : ajax_url,
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
};

var updater = function() {
	$.ajax({
		url : ajax_url,
		type : "POST",
		data : {
			type : "update",
			data : ""
		},
		success : function(json) {
			// Hot stuff
			$("#chat").append(json);
		}
	});
};

var auto_refresh = setInterval(updater, 1000);

$("#update").click(function() {
	updater();
	clearInterval(auto_refresh);
});

$("#sender").click(sender);
