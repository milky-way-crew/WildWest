var chat = {
	MAX_MSG : 8,
	$chat : null,
	init : function($chat) {
		console.log('Assigning chat element');
		chat.$chat = $chat;
	},
	append : function(what) {
		 var msgCount = chat.$chat.find('li').size();
		 if (msgCount > chat.MAX_MSG) {
		 	chat.$chat.find('li').first().fadeOut(100, function() {
		 		$(this).remove();
		 		chat.$chat.append('<li> ' + what + '</li>');
		 	});
		 } else {
		 	chat.$chat.append('<li> ' + what + '</li>');
		 }
	},
	prepend : function(what) {
		 var msgCount = chat.$chat.find('li').size();
		 if (msgCount > chat.MAX_MSG) {
		 	chat.$chat.find('li').first().fadeOut(100, function() {
		 		$(this).remove();
		 		chat.$chat.prepend('<li> ' + what + '</li>');
		 	});
		 } else {
		 	chat.$chat.prepend('<li> ' + what + '</li>');
		 }
	},
	add : function (what, fn) {
		var msgCount = chat.$chat.find('li').size();
		if (msgCount > chat.MAX_MSG) {
			chat.$chat.find('li').first().fadeOut(100, function() {
				$(this).remove();
				fn('<li> ' + what + '</li>');
			});
		} else {
			fn('<li> ' + what + '</li>');
		}
	},
	clear : function() {
		chat.$chat.html('');
	}
};
