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
						$("#menu").append(
								'<form name="roomAction" action=""><br>');

						if (json.creator) {
							$("#menu")
									.append(
											'<a id="start" '
													+ 'class="btn btn-primary btn-medium">Start</a>');
							$("#start").attr('disabled', true);
						} else {
							$("#menu")
									.append(
											'<a id="ready" '
													+ 'class="btn btn-primary btn-medium">Ready</a>');
						}
						if (json.startStatus) {
							$("#start").attr('disabled', false);
						}
						$("#menu")
								.append(
										'<a id="exit" '
												+ 'class="btn btn-primary btn-medium">Exit</a>');
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
						document.getElementById('exit').onclick = exitFromRoom;
						document.getElementById('ready').onclick = setReadyStatus;
						document.getElementById('start').onclick = startGame;
					}

					if (!$.isEmptyObject(json.roomList)) {
						$("#listTitle").html("");
						$("#listTitle").append("<h2>Room list</h2>");
						$("#menu").html("");
						$("#menu")
								.html(
										'<form name="roomAction" action="">'
												+ '<a data-toggle="modal" href="#createRoomModal" '
												+ 'class="btn btn-primary btn-medium">Create room</a>'
												+ '<a id="joinToRoom" '
												+ 'class="btn btn-primary btn-medium">Join to room</a>'
												+ '</form>');
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
						document.getElementById('joinToRoom').onclick = joinToRoom;
						document.getElementById('createRoom').onclick = createRoom;
						$('tr').each(function(i, e) {
							$(e).click(function() {
								idRoom = $(e).attr('id');
								$(e).toggleClass('hilite');
							});
						});
					}
				}
			});

}

function startGame() {
	$.ajax({
		url : ajax_room,
		type : "POST",
		data : {
			type : "start",
			data : "",
		},
		error : function() {
			alert("Pizdec");
		},
		success : function() {
			alert("OK let's go!");
		}
	});
}

function setReadyStatus() {
	$.ajax({
		url : ajax_room,
		type : "POST",
		data : {
			type : "ready",
			data : "",
		},
		error : function() {
			alert("Don't click so fast)");
		},
		success : function() {
			$("#list").html("");
			$("#menu").html("");
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
			success : function() {
				$("#listTitle").html("");
				$("#menu").html("");
				$("#list").html("");
			}
		});
	}
};

function exitFromRoom() {
	$.ajax({
		url : ajax_room,
		type : "POST",
		data : {
			type : "exit",
			data : "",
		},
		error : function() {
			alert("Page not found");
		},
		success : function() {
			$("#listTitle").html("");
			$("#menu").html("");
			$("#list").html("");
		}
	});
}

function joinToRoom() {
	$.ajax({
		url : ajax_room,
		type : "POST",
		data : {
			type : "join",
			data : idRoom,
		},
		error : function() {
			alert("Not join to room");
		},
		success : function() {
			$("#listTitle").html("");
			$("#menu").html("");
			$("#list").html("");
			$("#start").disabled = true;
		}
	});
};

function closeModal() {
	document.getElementById('roomName').value = "";
}

setInterval(updateList, 2000);