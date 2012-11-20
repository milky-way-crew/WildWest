ajax_chat = "ajax_chat";

function message() {
	if ($("#usermsg").val().length > 0) {
		$.ajax({
			url : ajax_chat,
			type : "POST",
			data : {
				type : "message",
				data : $("#usermsg").val()
			},
			error : function() {
				alert('Message not send');
			},
			success : function(json) {
				$("#chatBox").append(json.message);
				$("#usermsg").val("");
			}
		});
	}
};

function updateMessages() {
	$.ajax({
		url : ajax_chat,
		type : "POST",
		data : {
			type : "update",
			data : ""
		},
		error : function() {
		},
		success : function(json) {
			if (!$.isEmptyObject(json.update)) {
				$("#chatBox").append(json.update);
			}
		}
	});
};
document.getElementById('send').onclick = message;
setInterval(updateMessages, 3000);
