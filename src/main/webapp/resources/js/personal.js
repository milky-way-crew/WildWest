var image = 1;

function showEditIcon(){
	$('#editAvatar').modal();
}
function showEditInfo(){
	$('#editInf').modal();
}
function nextImage() {
	if(image>=65){
		image=1;
	}else{
		image=image+1;
	}
	     img = new Image();
	     img.src = "resources/img/avatar/emo"+image+".gif";
	     document.getElementById('image').src = img.src;
	     document.getElementById('number').value=image+"/65";
}

function previousImage() {
	if(image<=1){
		image=65;
	}else{
		image=image-1;
	}
    img = new Image();
    img.src = "resources/img/avatar/emo"+image+".gif";
    document.getElementById('image').src = img.src;
    document.getElementById('number').value=image+"/65";
}

function setAvatar(){
			$.ajax({
				url : "setAvatar",
				type : "POST",
				data : {
					type : "resources/img/avatar/emo"+image+".gif"
				},
				error : function() {
//					alert("Update loser  ");
				},
				success : function(text) {
					 document.getElementById('avatar').src = text;
				}
			});
		
}


function editLogin() {
	$.ajax({
		url : "edit",
		type : "POST",
		data : {
			type : "login",
			data : document.getElementById('login').value
		},
		error : function() {
//			alert("Message not send");
		},
		success : function(json) {
			document.getElementById('login').value = json;
		}
	});
}

function editNickname() {
	$.ajax({
		url : "edit",
		type : "POST",
		data : {
			type : "nickname",
			data : document.getElementById('nickname').value
		},
		error : function() {
//			alert("Message not send");
		},
		success : function(json) {
			document.getElementById('nickname').value = json;
		}
	});
}

function editEmail() {
	$.ajax({
		url : "edit",
		type : "POST",
		data : {
			type : "email",
			data : document.getElementById('email').value
		},
		error : function() {
//			alert("Message not send");
		},
		success : function(json) {
			document.getElementById('email').value = json;
		}
	});
}

function editPass() {
	if(document.getElementById('password').value==document.getElementById('confirmPassword').value){
	$.ajax({
		url : "edit",
		type : "POST",
		data : {
			type : "pass",
			data : document.getElementById('password').value
		},
		error : function() {
//			alert("Message not send");
		},
		success : function(json) {
			document.getElementById('password').value = "";
			document.getElementById('confirmPassword').value = "";
		}
	});
	}
	else{
		alert("Pasword is not correct");
		document.getElementById('password').value = "";
		document.getElementById('confirmPassword').value = "";
	}
}






document.getElementById('passwordBtn').style.visibility = "hidden";
document.getElementById('number').disabled = true;
document.getElementById('hope').onclick = showEditIcon;
document.getElementById('editInformation').onclick = showEditInfo;
document.getElementById('image').onclick = nextImage;
document.getElementById('previous').onclick = previousImage;
document.getElementById('next').onclick = nextImage;
document.getElementById('setAvatar').onclick = setAvatar;
document.getElementById('number').value = image+"/45";
document.getElementById('loginBtn').onclick = editLogin;
document.getElementById('nicknameBtn').onclick = editNickname;
document.getElementById('emailBtn').onclick = editEmail;
document.getElementById('passBtn').onclick = editPass;
//document.getElementById('edit').onclick = showEditIcon;