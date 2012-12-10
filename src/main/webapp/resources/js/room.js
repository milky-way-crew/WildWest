ajax_room = "ajax_room";

var idRoom;
var typeRoom = "";
var idUser;

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
						generateRoom(json);

						$("#exit").click(function() {
							exitFromRoom();
						});

						$("#ready").click(function() {
							setReadyStatus();
						});

						$("#start").click(function() {
							startGame();
						});

						$("#room").click(
								function() {
									filter("filteron");
									$("#filter").html(
											'Room<span class="caret"></span>');
								});

						$("#all").click(
								function() {
									filter("filteroff");
									$("#filter").html(
											'All<span class="caret"></span>');
								});

						$('p').each(function(i, e) {
							$(e).click(function() {
								getInvitedUser(e);
								$(e).toggleClass('hilite');
							});
							$(e).tooltip('show');
						});

						$("#invBtn")
								.click(
										function() {
											invite(idUser);
											$("#invBtn").attr('disabled', true);
											$("#invBtn")
													.html(
															'Invite <i class="icon-white icon-flag"></i>');
										});
					}

					if (!$.isEmptyObject(json.roomList)) {
						generateMainRoom(json);

						$("#joinToRoom").click(function() {
							joinToRoom(idRoom);
						});

						if (json.inviteStatus) {
							createInviteWindow(json);

							$("#accept").click(function() {
								joinToRoom(json.room.roomId);
							});
						}

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

$("#createRoom").click(function() {
	createRoom();
	$("#roomName").val("");
});

$("#closeModal").click(function() {
	$("#roomName").val("");
});

$("#Monopoly").click(function() {
	typeRoom = 'Monopoly';
	$("#info").html('Monopoly <span class="caret"></span>');
});

$("#Chess").click(function() {
	typeRoom = 'Chess';
	$("#info").html('Chess <span class="caret"></span>');
});

$("#Gibbet").click(function() {
	typeRoom = 'Gibbet';
	$("#info").html('Gibbet <span class="caret"></span>');
});

$("#DrawAndGuess").click(function() {
	typeRoom = 'Guess';
	$("#info").html('Draw and Guess <span class="caret"></span>');
});

function generateMainRoom(json) {
	$("#invMenu").html("");
	$("#chatOption").html("");
	$("#listTitle").html("");
	$("#listTitle").append("<h2>Room list</h2>");
	$("#menu").html("");
	$("#menu").html(
			'<form name="roomAction" action="">'
					+ '<a data-toggle="modal" href="#createRoomModal" '
					+ 'class="btn btn-primary btn-medium">Create room '
					+ '<i class="icon-white icon-globe"></i></a>'
					+ '<a id="joinToRoom" '
					+ 'class="btn btn-primary btn-medium">Join to room '
					+ '<i class="icon-white icon-share-alt"></i></a>'
					+ '</form>');
	updateRoomList(json);
}

function updateRoomList(json) {
	$("#list").html("");
	$("#list").append("<h1></h1>");
	$("#list").append(
			"<thead><tr><td><b>Room name</b></td>" + "<td><b>Game</b></td>"
					+ "<td><b>Users</b></td></tr></thead>");
	for ( var i = 1; i < json.roomList.length; i++) {
		$("#list").append(
				'<tr id=' + json.roomList[i].roomId + ' ><td> '
						+ json.roomList[i].roomName + ' </td><td> '
						+ json.roomList[i].type + ' </td><td> '
						+ json.roomList[i].size + '/'
						+ json.roomList[i].maxSize + ' </td></tr>');
	}
}

function createInviteWindow(json) {
	$("#invite").modal();
	$("#invBody").html("<p>User " + json.invitator + " invite you</p>");
	$("#invBody").append("<p>to room by name " + json.room.roomName + "</p>");
	$("#invBody").append("<p>game type is " + json.room.type + "</p>");
}

function getInvitedUser(pElement) {
	idUser = $(pElement).attr('id');
	$("#invBtn").attr('disabled', false);
	userNickname = $(pElement).attr('name');
	$("#invBtn").html(
			'Invite <i class="icon-white icon-flag"></i> ' + userNickname);
}

function generateRoom(json) {
	$("#chatOption").html("");
	$("#chatOption")
			.html(
					'<a id="filter" class="btn btn-medium dropdown-toggle'
							+ ' btn-info" data-toggle="dropdown"'
							+ ' href="#"><span class="caret"></span></a>'
							+ ' <ul id="filterChoise" compact="compact" class="dropdown-menu">'
							+ '<li><a id="all" tabindex="-1" href="#">All</a></li>'
							+ '<li><a id="room" tabindex="-1" href="#">Room</a></li></ul>');
	if (json.filter) {
		$("#filter").html('Room <span class="caret"></span>');
	} else {
		$("#filter").html('All <span class="caret"></span>');
	}
	$("#listTitle").html("");
	$("#listTitle").append(
			"<h2>" + json.userRoom.type + "Room: " + json.userRoom.roomName
					+ "</h2>");
	createRoomMenu(json);
	updateUserList(json);
}

function createRoomMenu(json) {
	$("#menu").html("");
	$("#menu").append('<form name="roomAction" action=""><br>');
	if (json.isCreator) {
		getCreatorPanel(json);
	} else {
		getClientPanel(json);
	}
	$("#menu").append(
			'<a id="exit" ' + 'class="btn btn-warning btn-medium">Exit '
					+ '<i class="icon-white icon-share"></i></a>');
	$("#menu").append('</form>');
}

function getCreatorPanel(json) {
	$("#menu").append(
			'<a id="start" href="./create"'
					+ 'class="btn btn-success btn-medium">Start '
					+ '<i class="icon-white icon-ok"></i></a>');
	if (idUser == undefined) {
		$("#invMenu").html(
				'<a id="invBtn" href="#" class="btn btn-success btn-medium">'
						+ 'Invite <i class="icon-white icon-flag"></i></a>');
		$("#invBtn").attr('disabled', true);
	}
	if (!json.startStatus) {
		$("#start").attr('disabled', true);
	}
}

function getClientPanel(json) {
	$("#menu").append(
			'<a id="ready" ' + 'class="btn btn-success btn-medium">Ready '
					+ '<i class="icon-white icon-check"></i></a>');
	$("#menu").append(
			'<a id="connect" href="./connect"'
					+ 'class="btn btn-success btn-medium">Connect '
					+ '<i class="icon-white icon-share-alt"></i></a>');
	$("#connect").attr('disabled', true);
	if (json.creatorRedirect && json.myRedirect) {
		$("#ready").attr('disabled', true);
		$("#connect").attr('disabled', false);
		$("#connect").click();
	}
}

function updateUserList(json) {
	$("#list").html("");
	$("#list").append("<h1></h1>");
	$("#list").append(
			"<thead><tr><td><b>User name</b></td>"
					+ "<td><b>State</b></td></tr></thead>");
	for ( var i = 0; i < json.userList.length; i++) {
		$("#list").append(
				'<tr id=' + json.userList[i].id_participant + ' ><td> '
						+ json.userList[i].nickname + ' </td><td> '
						+ json.userList[i].status + ' </td></tr>');
	}
}

function invite(id) {
	$.ajax({
		url : ajax_room,
		type : "POST",
		data : {
			type : "invite",
			data : id,
		},
		error : function() {
		},
		success : function(json) {
			if (json.inviteError == 1) {
				alert("You can not invite Yourself!");
			}
			if (json.inviteError == 0) {
				alert("This user is already in room!");
			}
		}
	});
}

function filter(filter) {
	$.ajax({
		url : ajax_room,
		type : "POST",
		data : {
			type : filter,
			data : "",
		},
		error : function() {
		},
		success : function() {
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
		if ($("#roomName").val().length < 11) {
			if (typeRoom != "") {

				$.ajax({
					url : ajax_room,
					type : "POST",
					data : {
						type : "create",
						data : JSON.stringify({
							"name" : $("#roomName").val(),
							"type" : typeRoom
						}),
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
			} else {
				alert("Choose your Game");
			}
		} else {
			alert("Room name must be <= 10 symbols");
		}
	} else {
		alert("Enter room name");
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
			$("#chatOption").html("");
			$("#invMenu").html("");
		}
	});
}

function joinToRoom(id) {
	$.ajax({
		url : ajax_room,
		type : "POST",
		data : {
			type : "join",
			data : id,
		},
		error : function() {
			alert("Please choose room");
		},
		success : function(json) {
			if (json.joinStatus) {
				$("#listTitle").html("");
				$("#menu").html("");
				$("#list").html("");
			} else {
				alert("Room is full");
			}
		}
	});
};

setInterval(updateList, 2500);