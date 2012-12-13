/*global document, window, alert, location, console, WebSocket, $ */

$(function() {
	"use strict";
	var timer = {},
		stats = {},
		chat = {},
		canvas = null,
		guessGame = {},
		ctx = null;
	timer = {
		config: {
			secondsLeft: 0,
			timerElement: document.getElementById('timer') || null,
			isStopped: false,
			interval: 1000
			// 1s
		},
		attach: function(id) {
			timer.config.timerElement = document.getElementById(id);
		},
		tick: function() {
			if(timer.config.secondsLeft >= 0 && !timer.config.isStopped) {
				timer.config.secondsLeft -= 1;
				timer.update();
				window.setTimeout(timer.tick, timer.config.interval);
			} else {
				timer.config.isStopped = true;
			}
		},
		update: function() {
			timer.config.timerElement.innerHTML = 'Time left: ' + timer.config.secondsLeft + 's';
		},
		start: function(secondsLeft) {
			timer.config.isStopped = false;
			timer.config.secondsLeft = secondsLeft;
			timer.update();
			window.setTimeout(timer.tick, timer.config.interval);
		},
		stop: function() {
			timer.config.isStopped = true;
		}
	};
	canvas = document.getElementById('drawing-pad');
	ctx = canvas.getContext('2d');
	chat = {
		MAX_MSG: 8,
		$chat: null,
		init: function() {
			chat.$chat = $('#chat-history');
		},
		append: function(what) {
			var msgCount = chat.$chat.find('li').size();
			if(msgCount > chat.MAX_MSG) {
				chat.$chat.find('li').first().fadeOut(100, function() {
					$(this).remove();
					chat.$chat.append('<li> ' + what + '</li>');
				});
			} else {
				chat.$chat.append('<li> ' + what + '</li>');
			}
		},
		clear: function() {
			chat.$chat.html('');
		}
	};
	function green() {
		$('#chat-history li').last().css({
			"color": "rgb(15, 99, 30)"
		});
	}
	stats = {
		$stats: null,
		init: function() {
			stats.$stats = $('#stats');
		},
		append: function(what) {
			stats.$stats.append('<li>' + what + '</li>');
		},
		remove: function(what) {
			console.error('#remove dont supported');
		},
		update: function(who, count) {
			var user = stats.$stats.find('li').filter(function(i, e) {
				return $(e).html().indexOf(who) != -1;
			}),
				message = who + " : " + count;
			if(user.size() > 0) {
				user.html(message);
			} else {
				stats.append(message);
			}
		}
	};
	guessGame = {
		// Contants
		LINE: 0,
		CHAT: 1,
		BIND_ME: 777,
		G_LOGIC: 2,

		// game state
		WAITING_TO_START: 0,
		G_START: 1,
		G_OVER: 2,
		G_RESTART: 3,

		isDrawing: false,
		isTurnToDraw: false,
		// FOR DEBUG -> SET TRUE
		// the starting point of next line drawing.
		startX: 0,
		startY: 0,
		thickness: 3,
		userId: null,
		gameId: null,
		send: function(data) {
			data.userId = guessGame.userId;
			data.gameId = guessGame.gameId;

			guessGame.socket.send(JSON.stringify(data));
		},
		init: function() {
			$.ajax({
				url: 'guess-bind',
				type: 'POST',
				success: function(data, textStatus, xhr) {
					if(data.dataType === "web-socket-bind") {
						console.log("received userId & gameId");
						console.log("userId", data.userId);
						console.log("gameId", data.gameId);

						guessGame.userId = data.userId;
						guessGame.gameId = data.gameId;

						// INIT REST
						console.log('Started initing guessGame');
						chat.init();
						stats.init();
						guessGame.initHandlers();
						guessGame.initWebSockets();
						console.log('Finished initing guessGame');
					} else {
						alert('refresh page');
					}
				},
				error: function(xhr, textStatus, errorThrown) {
					alert('error while binding websocket:', textStatus);
				}
			});
		},
		// Canvas drawing-style
		strokeStyle: '#444',

		tooglePencil: function() {
			guessGame.strokeStyle = '#444';
			guessGame.thickness = "3";
			$('#cp').css({
				"color": '#444'
			});
		},
		toogleEraser: function() {
			guessGame.strokeStyle = "#F1F3EF";
			guessGame.thickness = "10";
			$('#cp').css({
				"color": '#000'
			});
		},
		clearCanvas: function() {
			canvas.width = canvas.width;
		},
		drawLine: function(ctx, x1, y1, x2, y2, thickness, strokeStyle) {
			ctx.beginPath();
			ctx.moveTo(x1, y1);
			ctx.lineTo(x2, y2);
			ctx.lineCap = 'round';
			ctx.lineWidth = thickness;
			ctx.strokeStyle = strokeStyle;
			ctx.stroke();
		}
	};

	guessGame.initHandlers = function() {
		console.log('Started initHandlers');

		$('#cp').colorpicker({
			format: 'hex'
		});

		$('#cp').colorpicker().on('changeColor', function(ev) {
			$('#cp').css({
				"color": ev.color.toHex()
			});
			guessGame.strokeStyle = ev.color.toHex();
		});

		$('#eraser').click(function() {
			guessGame.toogleEraser();
		});

		$('#pencil').click(function() {
			guessGame.tooglePencil();
		});

		var sendMessage = function() {
				var message, data;
				message = $("#chat-input").val();
				if (message.trim().length > 0) {
					data = {};
					data.dataType = guessGame.CHAT;
					data.message = message;
					guessGame.send(data);
				}
				$("#chat-input").val("");					
				return false;
			};

		$("#send").click(sendMessage);

		$("#chat-input").keypress(function(event) {
			if(event.keyCode == '13') {
				return sendMessage();
			}
		});

		$("#clear").click(function() {
			guessGame.clearCanvas();
		});

		$('#restart').click(function() {
			chat.append("Sending [new-game] request to server.");
			guessGame.clearCanvas();
			var data = {};
			data.gameState = guessGame.G_RESTART;
			data.dataType = guessGame.G_LOGIC;
			guessGame.send(data);

			// $('#restart').hide(100);
		});

		$("#drawing-pad").mousedown(

		function(e) {
			var offset = $(this).offset(),
				mouseX = (e.pageX - offset.left - 10) || 0,
				mouseY = (e.pageY - offset.top - 10) || 0;

			guessGame.startX = mouseX;
			guessGame.startY = mouseY;

			guessGame.isDrawing = true;
		});

		$("#drawing-pad").mousemove(

		function(e) {
			if(guessGame.isTurnToDraw && guessGame.isDrawing) {
				var offset, mouseX, mouseY, data;
				offset = $(this).offset();
				mouseX = (e.pageX - offset.left - 10) || 0; // 10px for border
				mouseY = (e.pageY - offset.top - 10) || 0;

				if(!(mouseX === guessGame.startX && mouseY === guessGame.startY)) {
					guessGame.drawLine(ctx, guessGame.startX, guessGame.startY, mouseX, mouseY, guessGame.thickness, guessGame.strokeStyle);

					data = {};
					data.dataType = guessGame.LINE;
					data.startX = guessGame.startX;
					data.startY = guessGame.startY;
					data.endX = mouseX;
					data.endY = mouseY;
					// Experiment
					data.thickness = guessGame.thickness;
					data.strokeStyle = guessGame.strokeStyle;

					guessGame.send(data);
					guessGame.startX = mouseX;
					guessGame.startY = mouseY;
				}
			}
		});

		$("#drawing-pad").mouseup(function(e) {
			guessGame.isDrawing = false;
		});

		console.log('Finished initHandlers');
	};

	guessGame.initWebSockets = function() {
		if(window.WebSocket) {
			var ws = 'ws://' + location.hostname + ':8889/' || 'ws://localhost:8889/';
			guessGame.socket = new WebSocket(ws);

			guessGame.socket.onopen = function(e) {
				console.log('Connected to ws-Server successfully.');
				var data = {};
				data.dataType = guessGame.BIND_ME;

				guessGame.send(data);
			};
			// on message event
			guessGame.socket.onmessage = function(e) {
				// check if the received data is chat message or line segment
				// console.log("onmessage event:", e.data);
				var data, tokens;
				data = JSON.parse(e.data);
				if(data.dataType === guessGame.CHAT) {

					chat.append(data.sender + " : " + data.message);
					if(data.sender === "Server") {
						$('#chat-history li').last().css({
							"color": "rgb(15, 99, 30)"
						});
						tokens = data.message.split(" ");
						if(tokens[0].toLowerCase() === "welcome") {
							if (data.player && data.wins) {
								stats.update(data.player, data.wins);
							}
						}
					}
				} else if(data.dataType === guessGame.LINE) {
					guessGame.drawLine(ctx, data.startX, data.startY, data.endX, data.endY, data.thickness, data.strokeStyle);
				} else if(data.dataType === guessGame.G_LOGIC) {
					if(data.gameState === guessGame.G_OVER) {
						guessGame.isTurnToDraw = false;
						// Chat message who wins
						chat.append(data.winner + " wins! The answer was '" + data.answer + "'");
						// Stats update too
						stats.update(data.winner, data.wins);

						timer.stop();
						$("#drawing-pallete").show(100);
						$('#drawing-pallete .btn-info').hide(100);
						$("#restart").show(100);
					}
					console.log("game state: ", data.gameState, "G_START: ", guessGame.G_START);
					if(data.gameState === guessGame.G_START) {
						// clear the canvas.
						guessGame.clearCanvas();
						chat.clear();

						if(data.isPlayerTurn) {
							// alert('this player draw');
							$("#drawing-pallete").show(100);
							$('#drawing-pallete .btn-info').show(100);
							$("#restart").hide(100);

							guessGame.isTurnToDraw = true;
							// console.log('append answer to chat');
							chat.append("Your turn to draw. Please draw '" + data.answer + "'.");
							green();
							$('#drawing-pallete').show(100);
							timer.config.interval = 1000 * 2;
							timer.start(60);
						} else {
							chat.append("Game Started. Get Ready. You have one minute to guess.");
							green();
							$('#drawing-pallete').hide(100);
							timer.config.interval = 1000;
							timer.start(60);
						}
					}
				}
			};
			// on close event
			guessGame.socket.onclose = function(e) {
				$('#title').html('Oh, my.. Please, reconnect.');
				console.log('WebSocket connection closed.');
			};
		} else {
			alert('Sorry, you dont have websockets in your browser, so cant play this game.');
		}
	};



	guessGame.init();
});