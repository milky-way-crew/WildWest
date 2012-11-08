var ajax_url = "ajax";

var sender = function() {
	$.ajax({
		url : ajax_url,
		type : "POST",
		data : {
			type : "usermsg",
			data : $("#usermsg").val()
		},
		success : function(json) {
			// Adding own message to chat
			$("#chatBox").append("me: " + $("#usermsg").val() + "\n");
			// Clear chat
			$("#usermsg").val("");
		}
	});
};

var auto_refresh = setInterval(updater, 1000);

$("#send").click(sender);
