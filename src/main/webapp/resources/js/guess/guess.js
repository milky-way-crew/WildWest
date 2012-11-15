// canvas context
var canvas = document.getElementById('drawing-pad');
var ctx = canvas.getContext('2d');
var guessGame = {
    // Contants
    LINE_SEGMENT: 0,
    CHAT_MESSAGE: 1,
    GAME_LOGIC: 2,

    // Constant for game logic state
    WAITING_TO_START: 0,
    GAME_START: 1,
    GAME_OVER: 2,
    GAME_RESTART: 3,

    // indictes if it is drawing now.
    isDrawing: false,

    isTurnToDraw: true,

    // the starting point of next line drawing.
    startX: 0,
    startY: 0,
    thickness: 3,

    init: function() {
        $.ajax({
          url: '/guess-bind',
          type: 'POST',
          success: function(data, textStatus, xhr) {
            data.
          },
          error: function(xhr, textStatus, errorThrown) {
            alert('error while binding websocket');
          }
        });
        
        console.log('Started initing guessGame');
        guessGame.initHandlers();
        guessGame.initWebSockets();
        console.log('Finished initing guessGame');
    }
}

guessGame.initHandlers = function() {
    console.log('Started initHandlers');

    $("#clear").click(function() {
        canvas.width = canvas.width;
    });

    $("#drawing-pad").mousedown(function(e) {
        var offset = $(this).offset();
        var mouseX = (e.pageX - offset.left) || 0;
        var mouseY = (e.pageY - offset.top) || 0;

        guessGame.startX = mouseX;
        guessGame.startY = mouseY;

        guessGame.isDrawing = true;
    });

    $("#drawing-pad").mousemove(function(e) {
        if(guessGame.isTurnToDraw && guessGame.isDrawing) {
            var offset = $(this).offset();
            var mouseX = (e.pageX - offset.left - 10) || 0; // 10px for border
            var mouseY = (e.pageY - offset.top - 10) || 0;

            if(!(mouseX == guessGame.startX && mouseY == guessGame.startY)) {
                drawLine(ctx, guessGame.startX, guessGame.startY, mouseX, mouseY, guessGame.thickness);

                var data = {};
                data.dataType = guessGame.LINE_SEGMENT;
                data.startX = guessGame.startX;
                data.startY = guessGame.startY;
                data.endX = mouseX;
                data.endY = mouseY;
                // guessGame.socket.send(JSON.stringify(data));
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
    if(window["WebSocket"]) {
        guessGame.socket = new WebSocket('ws://localhost:8889/');

        guessGame.socket.onopen = function(e) {
            console.log('Connected to ws-Server successfully.');
        };
        // on message event
        guessGame.socket.onmessage = function(e) {
            // check if the received data is chat message or line segment
            console.log("onmessage event:", e.data);
            var data = JSON.parse(e.data);
            if(data.dataType == guessGame.CHAT_MESSAGE) {
                $("#chat-history").append("<li>" + data.sender + " said: " + data.message + "</li>");
            } else if(data.dataType == guessGame.LINE_SEGMENT) {
                drawLine(ctx, data.startX, data.startY, data.endX, data.endY, guessGame.thickness);
            } else if(data.dataType == guessGame.GAME_LOGIC) {
                if(data.gameState == guessGame.GAME_OVER) {
                    guessGame.isTurnToDraw = false;
                    $("#chat-history").append("<li>" + data.winner + " wins! The answer is '" + data.answer + "'.</li>");
                    $("#restart").show();
                }
                console.log("game state: ", data.gameState, "GAME_START: ", guessGame.GAME_START);
                if(data.gameState == guessGame.GAME_START) {
                    // clear the canvas.
                    canvas.width = canvas.width;
                    // hide the restart button.
                    $("#restart").hide();
                    // clear the chat history
                    $("#chat-history").html("");
                    if(data.isPlayerTurn) {
                        guessGame.isTurnToDraw = true;
                        $("#chat-history").append("<li>Your turn to draw. Please draw '" + data.answer + "'.</li>");
                    } else {
                        $("#chat-history").append("<li>Game Started. Get Ready. You have one minute to guess.</li>");
                    }
                }
            }
        };
        // on close event
        guessGame.socket.onclose = function(e) {
            $('#game h1').html('WebSocket connection closed. <br>Please, refresh page.')
            console.log('WebSocket connection closed.');
        };
    } else {
        alert('Sorry, you have no websockets in your browser, so cant play this game.');
    }
}


function drawLine(ctx, x1, y1, x2, y2, thickness) {
    ctx.beginPath();
    ctx.moveTo(x1, y1);
    ctx.lineTo(x2, y2);
    // Experimental
    ctx.lineCap = 'round';
    ctx.lineWidth = thickness;
    ctx.strokeStyle = "#444";
    ctx.stroke();
}


$(guessGame.init());


// // init script when the DOM is ready.
// $(function(){
//  // check if existence of WebSockets in browser
// if(window["WebSocket"]) {
//     // create connection
//     guessGame.socket = new WebSocket("ws://127.0.0.1:8000");
//     // on open event
//     guessGame.socket.onopen = function(e) {
//         console.log('WebSocket connection established.');
//     };
//     // on message event
//     guessGame.socket.onmessage = function(e) {
//         // check if the received data is chat message or line segment
//         console.log("onmessage event:", e.data);
//         var data = JSON.parse(e.data);
//         if(data.dataType == guessGame.CHAT_MESSAGE) {
//             $("#chat-history").append("<li>" + data.sender + " said: " + data.message + "</li>");
//         } else if(data.dataType == guessGame.LINE_SEGMENT) {
//             drawLine(ctx, data.startX, data.startY, data.endX, data.endY, 1);
//         } else if(data.dataType == guessGame.GAME_LOGIC) {
//             if(data.gameState == guessGame.GAME_OVER) {
//                 guessGame.isTurnToDraw = false;
//                 $("#chat-history").append("<li>" + data.winner + " wins! The answer is '" + data.answer + "'.</li>");
//                 $("#restart").show();
//             }
//             console.log("game state: ", data.gameState, "GAME_START: ", guessGame.GAME_START);
//             if(data.gameState == guessGame.GAME_START) {
//                 // clear the canvas.
//                 canvas.width = canvas.width;
//                 // hide the restart button.
//                 $("#restart").hide();
//                 // clear the chat history
//                 $("#chat-history").html("");
//                 if(data.isPlayerTurn) {
//                     guessGame.isTurnToDraw = true;
//                     $("#chat-history").append("<li>Your turn to draw. Please draw '" + data.answer + "'.</li>");
//                 } else {
//                     $("#chat-history").append("<li>Game Started. Get Ready. You have one minute to guess.</li>");
//                 }
//             }
//         }
//     };
//     // on close event
//     guessGame.socket.onclose = function(e) {
//         console.log('WebSocket connection closed.');
//     };
    //  }
    //  $("#send").click(sendMessage);
    //  $("#chat-input").keypress(function(event) {  
    //      if (event.keyCode == '13') {  
    //          sendMessage();  
    //      }  
    //  });
    //  // restart button
    //  $("#restart").hide();
    //  $("#restart").click(function(){
    //      canvas.width = canvas.width;
    //      $("#chat-history").html("");
    //      $("#chat-history").append("<li>Restarting Game.</li>");
    //      // pack the restart message into an object.
    //      var data = {};
    //      data.dataType = guessGame.GAME_LOGIC;
    //      data.gameState = guessGame.GAME_RESTART;
    //      guessGame.socket.send(JSON.stringify(data));
    //      $("#restart").hide();
    //  });
    //  // the logic of drawing on canvas
    //  $("#drawing-pad").mousedown(function(e) {
    //      // get the mouse x and y reltaive to the canvas top-left point.
    //      var mouseX = e.layerX || 0;
    //      var mouseY = e.layerY || 0;     
    //      guessGame.startX = mouseX;
    //      guessGame.startY = mouseY;
    //      guessGame.isDrawing = true;
    //     });
    //     // we draw lines when the mouse is pressing
    //     $("#drawing-pad").mousemove(function(e) {
    //      // draw lines when is drawing
    //      if (guessGame.isTurnToDraw && guessGame.isDrawing) {
    //          // get the mouse x and y reltaive to the canvas top-left point.
    //          var mouseX = e.layerX || 0;
    //          var mouseY = e.layerY || 0; 
    //          if (!(mouseX == guessGame.startX && mouseY == guessGame.startY))
    //          {               
    //              drawLine(ctx,guessGame.startX,guessGame.startY,mouseX,mouseY,1);
    //              // send the line segment to server
    //              var data = {};
    //              data.dataType = guessGame.LINE_SEGMENT;
    //              data.startX = guessGame.startX;
    //              data.startY = guessGame.startY;
    //              data.endX = mouseX;
    //              data.endY = mouseY;
    //              guessGame.socket.send(JSON.stringify(data));
    //              guessGame.startX = mouseX;
    //              guessGame.startY = mouseY;
    //          }
    //      }
    //     });
    //     // We clear the drawing state when mouse up.
    //     $("#drawing-pad").mouseup(function(e) {
    //      guessGame.isDrawing = false;
    //     });
    // });
    // function drawLine(ctx, x1, y1, x2, y2, thickness) {      
    //  ctx.beginPath();
    //  ctx.moveTo(x1,y1);
    //  ctx.lineTo(x2,y2);
    //  ctx.lineWidth = thickness;
    //  ctx.strokeStyle = "#444";
    //  ctx.stroke();
    // }
    // function sendMessage()
    // {
    //  var message = $("#chat-input").val();
    //  // pack the message into an object.
    //  var data = {};
    //  data.dataType = guessGame.CHAT_MESSAGE;
    //  data.message = message;
    //  guessGame.socket.send(JSON.stringify(data));
    //  $("#chat-input").val("");
    // }