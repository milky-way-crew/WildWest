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
			$("#chatBox").append("<p>" + json.message + "</p>");
			$("#usermsg").val("");
		}
	});
};

function update() {
	$
			.ajax({
				url : ajax_url,
				type : "POST",
				data : {
					type : "update",
					data : ""
				},
				error : function() {
				},
				success : function(json) {
					if (!$.isEmptyObject(json.update)) {
						$("#chatBox").append("<p>" + json.update + "</p>");
					}
					if (!$.isEmptyObject(json.roomList)) {
						$("#list").html("");
						$("#list")
								.append(
										"<tr><td width=175><b>Room name</b></td><td width=75><b>Users</b></td></tr>");
						for ( var i = 0; i < json.roomList.length; i++) {
							$("#list").append(
									"<tr: id=" + json.roomList[i].roomId
											+ " ><td width=175>"
											+ json.roomList[i].roomName
											+ "</td><td width=75>"
											+ json.roomList[i].size
											+ "</td></tr>");
						}
					}
				}
			});
};

/*
 * function createRoom() { $.ajax({ url : ajax_url, type : "POST", data : { type :
 * "create", data : document.getElementById('roomName').value, }, error :
 * function() { alert("Room not created"); }, success : function(json) {
 * alert(json); } }); };
 */
/*
 * $('#list').mouseover(function(event) { var tr =
 * $(event.target).closest('tr'); tr });
 */

function closeModal() {
	document.getElementById('roomName').value = "";
}

document.getElementById('createRoom').onclick = createRoom;
document.getElementById('send').onclick = message;
setInterval(update, 1000);
