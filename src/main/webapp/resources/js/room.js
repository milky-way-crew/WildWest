ajax_room = "ajax_room";

function updateList() {
	$.ajax({
		url : ajax_room,
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

function createRoom() {
	$.ajax({
		url : ajax_room,
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
		url : ajax_room,
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

function closeModal() {
	document.getElementById('roomName').value = "";
}

document.getElementById('joinToRoom').onclick = joinToRoom;
document.getElementById('createRoom').onclick = createRoom;
document.getElementById('closeModal').onclick = closeModal;
setInterval(updateList, 4000);