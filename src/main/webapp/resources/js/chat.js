ajax_url = "ajax";

function message() {
	$.ajax({
		url : ajax_url,
		type : "POST",
		data : {
			type : "message",
			data : document.getElementById('usermsg').value
		},
		error : function() {
			alert("Message not send");
		},
		success : function(json) {
			var date = new Date();
			$("#chatBox").append(
					"<p>[" + (date.getUTCHours() + 2) + ":"
							+ date.getUTCMinutes() + "] " + json
							+" : "+ $("#usermsg").val() + "</p>");
			$("#usermsg").val("");
		}
	});
};

function update() {
	$.ajax({
		url : ajax_url,
		type : "POST",
		data : {
			type : "update",
			data : ""
		},
		error : function() {
			alert("Connection error");
		},
		success : function(json) {
			$("#chatBox").append(
					"<p>[" + date.getUTCHours() + ":" + date.getUTCMinutes()
							+ "] " + json + "</p>");
			alert("Update success!!!" + json);
		}
	});
};

document.getElementById('send').onclick = message;

var auto_refresh = setInterval(updater, 1000);
