/*jslint browser: true*/
/*global jQuery, $, WebSocket, BOARD, console, prompt, bootbox*/

$(document).ready(

function() {
	"use strict";

	var log = function(message) {
			console.log('[DEBUG] ' + message);
		},
		chat = {},
		MONO = {},
		ui = {};
	ui = {
		minOpacityToButtons: function() {
			$("#controls .btn").each(

			function(i, btn) {
				$(btn).animate({
					"opacity": "0.5"
				});
			});
		},
		refreshButtons: function(buttons) {
			ui.minOpacityToButtons();
			$.each(buttons, function(btnName) {
				var opacityValue = buttons[btnName] ? "1" : "0.5";
				$('#' + btnName).animate({
					"opacity": opacityValue
				}, 100);
				if(btnName === "buy" && buttons[btnName]) {
					$('#buy').show(100);
				}
				if(btnName === "pay" && buttons[btnName]) {
					$('#pay').show(100);
				}
			});
		}
	};
	chat = {
		$chat: null,
		init: function() {
			console.log('Initing chat');
			chat.$chat = $('#chat');
		},
		append: function(what) {
			chat.$chat.prepend('<li>' + what + '</li>');
		}
	};
	MONO = {
		config: {
			idGame: null,
			idUser: null,
			socketUrl: 'ws://' + location.hostname + ':8888/' || "ws://localhost:8888/",
			ajaxUrl: "mono-ajax",
			isCreator: false,
			money: 0,
			color: '',
			game_status: "",
			position: 0
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
						console.log('Inner id [idUser=', init_id.idUser, '] [idGame=', init_id.idGame, ']');
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
				'undefined': function(json) {
					console.log('Unknown response');
				},
				'roll': function(json) {
					console.log('[roll] event');
					var dice1 = json.dice1,
						dice2 = json.dice2,
						offset = parseInt(dice1, 10) + parseInt(dice2, 10),
						buttons = json.game_state.buttons,
						color = json.game_state.player,
						money = json.game_state.player_money,
						message = json.game_state.messages;
					// Think about go-card
					log('Was money=' + MONO.config.money);
					log('After move money=' + money);
					MONO.config.money = money;
					MONO.animate.money(money);

					MONO.config.position = json.was + offset;
					log('position on board [was] -> ' + json.was);
					log('position on board [now] -> ' + MONO.config.position);

					log('Starting animation of roll event');
					MONO.animate.move(color, dice1, dice2, json.was);

					// this player moves
					if(MONO.config.color === color) {
						ui.refreshButtons(buttons);
						if(message) {
							chat.append("server: " + message);
						}
					}
					// chat.append("player[" + color + "] is on cell: " + json.cell.name);
				},
				'buy': function(json) {
					var msg = 'player= ' + json.player + ' bought cell with position= ' + MONO.config.position 
							+ ' now player money= ' + json.player_money;
					chat.append(msg);
					log(msg);
					MONO.animate.buy(json.player, MONO.config.position);
					MONO.animate.money(json.player, json.player_money);
				},
				'pay': function(json) {
					log('Player= ' + json.player + ' payed money for go out from jail');
					MONO.animate.pay(json.Player, json.player_money);
				},
				'mortage': function(json) {
					console.log('[mortage] event');
					var pos, buttons;
					MONO.animate.mortage(json.mortage_list);
					pos = parseInt(
					prompt('Enter position to mortage. List=' + JSON.stringify(json.mortage_list)), 10);
					buttons = json.game_state.buttons;

					if(pos && json.player === MONO.config.color) {
						MONO.transport.send('mortage', {
							position: pos
						});
					}
					if(MONO.config.color === json.player) {
						ui.refreshButtons(buttons);
						if(json.message) {
							chat.append("server: " + json.message);
						}
					}
				},
				'unmortage': function(json) {
					console.log('[unmortage] event');
					var pos, buttons;
					MONO.animate.unmortage(json.unmortage_list);
					pos = parseInt(
					prompt('Enter position to unmortage. List=' + JSON.stringify(json.unmortage_list)), 10);
					buttons = json.game_state.buttons;
					if(pos && json.player === MONO.config.color) {
						MONO.transport.send('unmortage', {
							position: pos
						});
					}
					if(MONO.config.color === json.player) {
						ui.refreshButtons(buttons);
					}
				},
				'build': function(json) {
					var pos, buttons;
					MONO.animate.build(json.build_list);
					pos = parseInt(
					prompt('Enter position to build. List=' + JSON.stringify(json.build_list)), 10);
					buttons = json.game_state.buttons;
					if(pos && json.player === MONO.config.color) {
						MONO.transport.send('build', {
							position: pos
						});
					}
					if(MONO.config.color === json.player) {
						ui.refreshButtons(buttons);
					}
				},
				'sell': function(json) {
					console.log('[sell] event');
					var pos, buttons;
					MONO.animate.sell(json.sell_list);
					pos = parseInt(
					prompt('Enter position to sell. List=' + JSON.stringify(json.sell_list)), 10);
					buttons = json.game_state.buttons;
					if(pos && json.player === MONO.config.color) {
						MONO.transport.send('sell', {
							position: pos
						});
					}
					if(MONO.config.color === json.player) {
						ui.refreshButtons(buttons);
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
					chat.append(json.message);
				},
				'logic': function(json) {
					log('game-status:' + json.game_status);
					MONO.config.game_status = json.game_status;
					if(json.game_status === "start") {
						$('#ready').hide(100);
						$('#start').hide(100);
					}
				},
				'ready': function(json) {
					chat.append(json.player + ' is ready=' + json.ready);
				},
				'turn': function(json) {
					chat.append(json.player + ' turn=true');
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
			money: function(who, money) {
				log('Animating ~not supported~ [money] change');
			},
			move: function(who, d1, d2, was) {
				log('Animating [move]');
				console.log('Moving to: ' + (d1 + d2));
				chat.append('Moving ' + who + ' to offset: ' + (d1 + d2));
				BOARD.animate.stepOnBoard(who, d1, d2, was);
			},
			buy: function(who, position) {
				log('Animating ~not supported~ [buy]');
			},
			pay: function(player, money) {
				log('Animating ~not supported~ [pay]');
				MONO.animate.money(player, money);
			},
			mortage: function(argument) {
				log('Animating ~not supported~ [mortage]');
			},
			unmortage: function(argument) {
				log('Animating ~not supported~ [unmortage]');
			},
			sell: function(argument) {
				log('Animating ~not supported~ [sell]');
			},
			build: function(argument) {
				log('Animating ~not supported~ [build]');
			}
		},
		init: function() {
			MONO.transport.init();
			chat.init();

			var hideAfterClick = {
				pay: true,
				buy: true,
				start: true
				// done : true
			},
				initButton = function() {
					$(this).click(function() {
						var id = $(this).attr('id');
						console.log('sending [', id, '] message to server');
						if(hideAfterClick[id]) {
							$(this).hide(100);
						}
						MONO.transport.send(id, {});
					});
				};
			$('#controls button').each(initButton);
			$('#menu button').each(initButton);
		}
	};
	MONO.init();
});