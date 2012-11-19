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
			if (!$.isEmptyObject(json.message)) {
				$("#chatBox").append(
						"<p>" + json.sender + json.message + "</p>");
				$("#usermsg").val("");
			}
		}
	});
};

function updateList() {
	$.ajax({
		url : ajax_url,
		type : "POST",
		data : {
			type : "lists",
			data : ""
		},
		error : function() {
		},
		success : function(json) {
			if (!$.isEmptyObject(json.userList)) {
				$("#list").html("");
				$("#list").append(
						"<tr><td width=175><b>User name</b></td>"
								+ "<td width=75><b>State</b></td></tr>");
				for ( var i = 0; i < json.userList.length; i++) {
					$("#list").append(
							"<tr id=" + json.userList[i].id_participant
									+ " ><td> " + json.userList[i].nickname
									+ " </td><td> " + json.userList[i].status
									+ " </td></tr>");
				}
			}
			if (!$.isEmptyObject(json.roomList)) {
				$("#list").html("");
				$("#list").append(
						"<tr><td width=175><b>Room name</b></td>"
								+ "<td width=75><b>Users</b></td></tr>");
				for ( var i = 0; i < json.roomList.length; i++) {
					$("#list").append(
							"<tr id=" + json.roomList[i].roomId + " ><td> "
									+ json.roomList[i].roomName + " </td><td> "
									+ json.roomList[i].size + " </td></tr>");
				}
			}
		}
	});
}

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
			if (!$.isEmptyObject(json.update)) {
				$("#chatBox").append("<p>" + json.update + "</p>");
			}
		}
	});
};

function createRoom() {
	$.ajax({
		url : ajax_url,
		type : "POST",
		data : {
			type : "create",
			data : document.getElementById('roomName').value,
		},
		error : function() {
			alert("Room not created");
		},
		success : function(json) {
			
		}
	});
};

function joinToRoom() {
	$.ajax({
		url : ajax_url,
		type : "POST",
		data : {
			type : "join",
			data : "1",
		},
		error : function() {
			alert("NOT join to room");
		},
		success : function(json) {
			alert(json);
		}
	});
};

/*
 * $('#list').mouseover(function(event) { var tr =
 * $(event.target).closest('tr'); tr });
 */

function closeModal() {
	document.getElementById('roomName').value = "";
}

document.getElementById('joinToRoom').onclick = joinToRoom;
document.getElementById('createRoom').onclick = createRoom;
document.getElementById('send').onclick = message;
setInterval(update, 1000);
setInterval(updateList, 3000);
