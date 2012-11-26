ajax_room = "ajax_room";

var idRoom;
var typeRoom = "";

function connect() {
	$.ajax({
		url : "connect",
		type : "GET",
		data : "",
		error : function() {
		},
		success : function() {
		}
	});
}

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
						$("#chatOption").html("");
						$("#chatOption")
								.html(
										'<a id="filterOn" href="#" class="btn '
												+ 'btn-success btn-mini">Filter On '
												+ '<i class="icon-white icon-pencil"></i></a>'
												+ '<a id="filterOff" href="#" class="btn btn-danger btn-mini"'
												+ '>Filter Off <i class="icon-white '
												+ 'icon-pencil"></i></a>');
						$("#filterOn").attr('disabled', json.filter);
						$("#filterOff").attr('disabled', !json.filter);
						$("#listTitle").html("");
						$("#listTitle").append(
								"<h2>" + json.userRoom.type + "Room: "
										+ json.userRoom.roomName + "</h2>");
						$("#menu").html("");
						$("#menu").append(
								'<form name="roomAction" action=""><br>');

						if (json.isCreator) {
							$("#menu")
									.append(
											'<a id="start" href="./create"'
													+ 'class="btn btn-success btn-medium">Start '
													+ '<i class="icon-white icon-ok"></i></a>');
							$("#start").attr('disabled', true);
						} else {
							$("#menu")
									.append(
											'<a id="ready" '
													+ 'class="btn btn-success btn-medium">Ready '
													+ '<i class="icon-white icon-check"></i></a>');
						}
						if (json.startStatus) {
							$("#start").attr('disabled', false);
						}
						$("#menu")
								.append(
										'<a id="exit" '
												+ 'class="btn btn-danger btn-medium">Exit '
												+ '<i class="icon-white icon-share"></i></a>');
						$("#menu").append('</form>');
						$("#list").html("");
						$("#list").append(
								"<thead><tr><td><b>User name</b></td>"
										+ "<td><b>State</b></td></tr></thead>");
						for ( var i = 0; i < json.userList.length; i++) {
							$("#list").append(
									'<tr id=' + json.userList[i].id_participant
											+ ' ><td> '
											+ json.userList[i].nickname
											+ ' </td><td> '
											+ json.userList[i].status
											+ ' </td></tr>');
						}

						$("#exit").click(function() {
							exitFromRoom();
						});

						$("#ready").click(function() {
							setReadyStatus();
						});

						$("#start").click(function() {
							startGame();
						});

						$("#filterOn").click(function() {
							filterOn();
							$("#filterOn").attr('disabled', true);
							$("#filterOff").attr('disabled', false);
						});

						$("#filterOff").click(function() {
							filterOff();
							$("#filterOff").attr('disabled', true);
							$("#filterOn").attr('disabled', false);
						});

						if (json.creatorRedirect && json.myRedirect) {
							connect();
						}
					}

					if (!$.isEmptyObject(json.roomList)) {
						$("#chatOption").html("");
						$("#listTitle").html("");
						$("#listTitle").append("<h2>Room list</h2>");
						$("#menu").html("");
						$("#menu")
								.html(
										'<form name="roomAction" action="">'
												+ '<a data-toggle="modal" href="#createRoomModal" '
												+ 'class="btn btn-primary btn-medium">Create room '
												+ '<i class="icon-white icon-globe"></i></a>'
												+ '<a id="joinToRoom" '
												+ 'class="btn btn-primary btn-medium">Join to room '
												+ '<i class="icon-white icon-share-alt"></i></a>'
												+ '</form>');
						$("#list").html("");
						$("#list").append(
								"<thead><tr><td><b>Room name</b></td>"
										+ "<td><b>Game</b></td>"
										+ "<td><b>Users</b></td></tr></thead>");
						for ( var i = 1; i < json.roomList.length; i++) {
							$("#list").append(
									'<tr id=' + json.roomList[i].roomId
											+ ' ><td> '
											+ json.roomList[i].roomName
											+ ' </td><td> '
											+ json.roomList[i].type
											+ ' </td><td> '
											+ json.roomList[i].size
											+ ' </td></tr>');
						}

						$("#joinToRoom").click(function() {
							joinToRoom();
						});

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
	typeRoom = $(this).attr('id');
});

$("#Chess").click(function() {
	typeRoom = $(this).attr('id');
});

$("#Gibbet").click(function() {
	typeRoom = $(this).attr('id');
});

$("#DrawAndGuess").click(function() {
	typeRoom = $(this).attr('id');
});

function filterOn() {
	$.ajax({
		url : ajax_room,
		type : "POST",
		data : {
			type : "filteron",
			data : "",
		},
		error : function() {
		},
		success : function() {
		}
	});
}

function filterOff() {
	$.ajax({
		url : ajax_room,
		type : "POST",
		data : {
			type : "filteroff",
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
			alert("Please choose room");
		},
		success : function() {
			$("#listTitle").html("");
			$("#menu").html("");
			$("#list").html("");
			$("#start").disabled = true;
		}
	});
};

setInterval(updateList, 4000);