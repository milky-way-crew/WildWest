/*global jQuery, $, WebSocket,
console, alert, document,
setInterval, clearInterval,
prompt, bootbox*/



var MSG = {
		ID : 'id',
		GID : 'game-id',
		UID : 'user-id'
		
		
};

var MONO = {
	config : {
		game_id : null,
		user_id : null,
		socketUrl : "ws://localhost:8888/",
		ajaxUrl : "mono-ajax"
	},
	transport : {
		socket : null,
		send : function(type, json) {
			var jsonString = JSON.stringify({
				'type' : type,
				MSG.ID : {
					MSG.GID : MONO.config.game_id,
					'user-id' : MONO.config.user_id
				},
				'data' : json,
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
						alert('my user_id is: ' + init_id.user_id);
						alert('my game_id is: ' + init_id.game_id);
						
						MONO.config.user_id = init_id.user_id;
						MONO.config.game_id = init_id.game_id;

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
				'user-id' : MONO.config.user_id
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
			alert('Moving to: ' + offset);
		}
	}
};

$(document).ready(function() {
	MONO.transport.init();

	$('#msg').click(function() {
		MONO.transport.send('bind-websocket', {
			'user-id' : MONO.config.user_id
		});
		MONO.transport.send('String message', $(this).val());
	});
});