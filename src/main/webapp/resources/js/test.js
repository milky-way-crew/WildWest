$(document).ready(function() {
	var sender = function() {
		var message = $("#message").val();
		socket.send(message);
	};

	var updater = function(data) {
		$("#chat").append(data);
	};

	$("#sender").click(sender);

	var socket = new WebSocket("ws://localhost:8888/");
	socket.onopen = function() {
		console.log("Connection opened");
	};
	socket.onclose = function() {
		console.log("Connection closed");
	};
	socket.onmessage = function(event) {
		console.log("Message:", event.data);
		updater(event.data + "\n");
	};
});
