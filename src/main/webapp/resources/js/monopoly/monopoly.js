/*global jQuery, $, WebSocket,
console, alert, document,
setInterval, clearInterval,
prompt, bootbox*/

// $(document).ready(function() {
"use strict";
var MONO = {};

MONO = {
	config: {
		idGame: null,
		idUser: null,
		socketUrl: "ws://localhost:8888/",
		ajaxUrl: "mono-ajax",
		isCreator: false,
		money: 0,
		color: '',
		game_status: ""
	},
	transport: {
		socket: null,
		send: function(type, json) {
			var jsonString = JSON.stringify({
				'type': type,
				'id': {
					'game-id': MONO.config.idGame,
					'user-id': MONO.config.idUser
				},
				'data': json
			});
			console.log('sending message: ' + jsonString);
			MONO.transport.socket.send(jsonString);
		},
		init: function() {
			$.ajax({
				url: MONO.config.ajaxUrl,
				type: 'post',
				success: function(init_id) {
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

	events: {
		onConnectEstablished: function() {
			console.log("Connection opened, bind-websocket request");
			MONO.transport.send('bind-websocket', {});
			MONO.transport.send('init', {});
		},
		onConnectClosed: function() {
			console.log("Connection closed");
		},
		onMessage: function(event) {
			console.log("Received message: ", event.data);
			var json = JSON.parse(event.data);
			if(typeof MONO.events.handle[json.type] === 'undefined') {
				console.error('No handlers for ' + json.type + ' defined');
			} else {
				MONO.events.handle[json.type](json);
			}
		},
		handle: {
			'roll': function(json) {
				console.log('[roll] event');
				var dice1 = json.dice1,
					dice2 = json.dice2,
					offset = parseInt(dice1, 10) + parseInt(dice2, 10),
					buttons = json.buttons,
					color = json.player,
					money = json.money;
				console.log('Current money is: ' + money);
				MONO.config.money = money;

				MONO.animate.move(offset);

				// this player moves
				if(MONO.config.color === color) {
					$("#controls .btn").each(function (i, btn) {
						$(btn).animate({"opacity": "0.5"});
					});

					$.each(buttons, function(btnName) {
						var opacityValue = buttons[btnName] ? "1" : "0.5";
						$('#' + btnName).animate({
							"opacity": opacityValue
						}, 100);
					});
				}
			},
			'init': function(json) {
				console.log('[init] event');
				MONO.config.color = json.color;
				MONO.config.money = json.money;
				MONO.config.isCreator = json.isCreator;

				if(MONO.config.isCreator) {
					$('#start').show(100);
				} else {
					$('#start').hide(100);
				}
			},
			'chat': function(json) {
				$('#chat').append('<li>' + json.message + '</li>');
			},
			'logic': function(json) {
				alert('game-status:' + json.game_status);
				MONO.config.game_status = json.game_status;
				if(json.game_status === "start") {
					$('#ready').hide(100);
					$('#start').hide(100);
				}
			},
			'ready': function(json) {
				$('#chat').append('<li>' + json.player + ' is ready=' + json.ready + '</li>');
			},
			'undefined': function(json) {
				console.log('Unknown response');
			},
			'turn': function(json) {
				$('#chat').append('<li>' + json.player + ' turn=true</li>');
				$('.btn').animate({
					"opacity": "0.5"
				}, 100);

				if(json.player === MONO.config.color) {
					$('#roll').animate({
						"opacity": "1"
					}, 100);
				}
			}
		}
	},
	animate: {
		move: function(offset) {
			$('#chat').append('<li>' + 'Moving offset: ' + offset + '</li>');
			console.log('Moving to: ' + offset);
		}
	},
	init: function() {
		MONO.transport.init();


		$('#roll').click(function() {
			console.log('sending **roll** message to server');
			MONO.transport.send('roll', {});
		});
		$('#buy').click(function() {
			console.log('sending **buy** message to server');
			MONO.transport.send('buy', {});
		});
		$('#pay').click(function() {
			console.log('sending **pay** message to server');
			MONO.transport.send('pay', {});
		});
		$('#ok').click(function() {
			console.log('sending **ok** message to server');
			MONO.transport.send('ok', {});
		});
		$('#done').click(function() {
			console.log('sending ***done*** message to server');
			$(".btn").animate({"opacity" : "0.5"});

			MONO.transport.send('done', {});
		});

		$('#start').click(function() {
			console.log('sending ***start*** message to server');
			MONO.transport.send('start', {});
		});

		$('#ready').click(function() {
			console.log('sending ***ready*** message to server');
			MONO.transport.send('ready', {});
		});
	}
};
MONO.init();
// });