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
			$("#chatBox").append(
					"<p>" + json + $("#usermsg").val() + "</p>");
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
		},
		success : function(json) {
			$("#chatBox").append("<p>" + json + "</p>");
		}
	});
};

document.getElementById('send').onclick = message;
setInterval(update, 1000);
