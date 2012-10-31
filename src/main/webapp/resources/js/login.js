$(document).ready(function() {
	$('input').hover(function() {
		$(this).popover('show');
	}, function() {
		$(this).popover('hide');
	});

	$("#loginHere").validate({
		rules : {
			login : "required",
			password : {
				required : true,
				minlength : 1
			}
		},
		messages : {
			login : {
				required : "You forgot to enter your login here"
			},
			password : {
				required : "C'mon enter your password",
			}
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

	$('#myCarousel').carousel({interval: 2000});
});
//$(document).ready(function(){
    //Start carousel
//});