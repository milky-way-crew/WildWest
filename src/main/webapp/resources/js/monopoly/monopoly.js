/*global jQuery, $, WebSocket,
console, alert, document,
setInterval, clearInterval,
prompt, bootbox*/

var MONO = {
	config : {
		idGame : null,
		idUser : null,
		socketUrl : "ws://localhost:8888/",
		ajaxUrl : "mono-ajax"
	},
	transport : {
		socket : null,
		send : function(type, json) {
			var jsonString = JSON.stringify({
				'type' : type,
				'id' : {
					'game-id' : MONO.config.idGame,
					'user-id' : MONO.config.idUser
				},
				'data' : json
			});
			console.log('sending message: ' + jsonString);

			MONO.transport.socket.send(jsonString);
		},
		init : function() {
			$.ajax({
					url : MONO.config.ajaxUrl,
					type : 'post',
					success : function(init_id) {
						console.log('Inner id received');
						console.log('my idUser is: ' + init_id.idUser);
						console.log('my idGame is: ' + init_id.idGame);
						
						MONO.config.idUser = init_id.idUser;
						MONO.config.idGame = init_id.idGame;

						console.log('Initing web-sockets');
						MONO.transport.socket = new WebSocket(MONO.config.socketUrl);
						MONO.transport.socket.onopen = MONO.events.onConnectEstablished;
						MONO.transport.socket.onclose = MONO.events.onConnectClosed;
						MONO.transport.socket.onmessage = MONO.events.onMessage;
					}
			});
		}
	},
	events : {
		onConnectEstablished : function() {
			console.log("Connection opened, bind-websocket request");
			MONO.transport.send('bind-websocket', {
				'user-id' : MONO.config.idUser
			});
		},
		onConnectClosed : function() {
			console.log("Connection closed");
		},
		onMessage : function(event) {
			console.log("Received message: ", event.data);
		}
	},
	animate : {
		move : function(offset) {
			// TARAS_MODULE.func.()
			console.log('Moving to: ' + offset);
		}
	}
};

$(document).ready(function() {
	MONO.transport.init();

	$('#roll').click(function() {
		console.log('sending **roll** message to server');
		MONO.transport.send('roll', {});
		MONO.transport.send('String message', $(this).val());
	});




});