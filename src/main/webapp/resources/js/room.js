ajax_room = "ajax_room";

var idRoom;
function updateList() {
	$
			.ajax({
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
						$("#listTitle").html("");
						$("#listTitle").append(
								"<h2>" + json.userRoom.roomName + "</h2>");
						$("#menu").html("");
						$("#menu").append('<br><form name="roomAction" action="">');
						$("#menu")
								.append(
										'<a id="start" data-toggle="modal" class="btn btn-primary btn-medium">Start</a>'
												+ '<a id="ready" data-toggle="modal" class="btn btn-primary btn-medium">Ready</a>'
												+ '<a id="exit" data-toggle="modal" class="btn btn-primary btn-medium">Exit</a>');
						$("#menu").append('</form>');
						$("#list").html("");
						$("#list")
								.append(
										"<thead><tr><td width=175><b>User name</b></td>"
												+ "<td width=75><b>State</b></td></tr></thead>");
						for ( var i = 0; i < json.userList.length; i++) {
							$("#list").append(
									'<tr id=' + json.userList[i].id_participant
											+ ' ><td> '
											+ json.userList[i].nickname
											+ ' </td><td> '
											+ json.userList[i].status
											+ ' </td></tr>');
						}
					}
					if (!$.isEmptyObject(json.roomList)) {
						$("#listTitle").html("");
						$("#listTitle").append("<h2>Room list</h2>");
						$("#menu").html("");
						$("#menu").append('<br><form name="roomAction" action="">');
						$("#menu")
								.append(
										+'<a data-toggle="modal" href="#createRoomModal"'
												+ 'class="btn btn-primary btn-medium">Create room</a>'
												+ '<a id="joinToRoom" data-toggle="modal'
												+ 'class="btn btn-primary btn-medium">Join to room</a>');
						$("#menu").append('</form>');
						$("#list").html("");
						$("#list")
								.append(
										"<thead><tr><td width=175><b>Room name</b></td>"
												+ "<td width=75><b>Users</b></td></tr></thead>");
						for ( var i = 0; i < json.roomList.length; i++) {
							$("#list").append(
									'<tr id=' + json.roomList[i].roomId
											+ ' ><td> '
											+ json.roomList[i].roomName
											+ ' </td><td> '
											+ json.roomList[i].size
											+ ' </td></tr>');
						}
					}
					$('tr').each(function(i, e) {
						$(e).click(function() {
							idRoom = $(e).attr('id');
							$(e).toggleClass('hilite');
						});
					});
				}
			});

}

function createRoom() {
	if ($("#roomName").val().length > 0) {
		$.ajax({
			url : ajax_room,
			type : "POST",
			data : {
				type : "create",
				data : $("#roomName").val(),
			},
			error : function() {
				alert("Room not created");
			},
			success : function(json) {
			}
		});
	}
};

function joinToRoom() {
	$
			.ajax({
				url : ajax_room,
				type : "POST",
				data : {
					type : "join",
					data : idRoom,
				},
				error : function() {
					alert("Not join to room");
				},
				success : function(json) {
				}
			});
};

function closeModal() {
	document.getElementById('roomName').value = "";
}

document.getElementById('joinToRoom').onclick = joinToRoom;
document.getElementById('createRoom').onclick = createRoom;
setInterval(updateList, 3000);