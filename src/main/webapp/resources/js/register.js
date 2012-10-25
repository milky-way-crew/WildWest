$(document).ready(function() {
	$('input').hover(function() {
		$(this).popover('show');
	}, function() {
		$(this).popover('hide');
	});

	$("#registerHere").validate({
		rules : {
			login : {
				required : true,
				minlength : 6,
			},
			nickname : {
				required : true,
				minlength : 4,
			},
			email : {
				required : true,
				email : true,
			},
			password : {
				required : true,
				minlength : 6,
			},
			conf_password : {
				required : true,
				equalTo : "#password"
			}
		},
		messages : {
			login : {
				required : "You forgot to enter your login here.",
				minlength : "Login must be longer than 6 symbols."
			},
			nickname : {
				required : "You forgot to enter your nickname here.",
				minlength : "Nickname must be longer than 4 symbols."
			},
			email : {
				required : "You forgot to enter your email here.",
				email : "Incorrect email."
			},
			password : {
				required : "C'mon enter your password.",
				minlength : "Password must be longer than 6 symbols."
			},
			conf_password : {
				required : "Enter confirm password",
				equalTo : "Password and Confirm Password must match",
			},
		},
		errorClass : "help-inline",
		errorElement : "span",
		highlight : function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight : function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});

});