var image = 1;

function showEditIcon(){
	$('#editAvatar').modal();
}
function showEditInfo(){
	$('#editInf').modal();
}
function nextImage() {
	if(image>=45){
		image=1;
	}else{
		image=image+1;
	}
	     img = new Image();
	     img.src = "resources/img/avatar/emo"+image+".gif";
	     document.getElementById('image').src = img.src;
	     document.getElementById('number').value=image+"/45";
}

function previousImage() {
	if(image<=1){
		image=45;
	}else{
		image=image-1;
	}
    img = new Image();
    img.src = "resources/img/avatar/emo"+image+".gif";
    document.getElementById('image').src = img.src;
    document.getElementById('number').value=image+"/45";
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




function table(){
	$.ajax({
		url : "table",
		type : "POST",
		data : {
			type : "create",
			data : JSON.stringify({
				"monoG" : "",
				"monoW" : "",
				"monoP" : "",
				"monoM" : "",
				"chess" : "",
				"chessW" : "",
				"gibbet" : "",
				"gibbetW" : "",
				"draw" : "",
				"drawW" : ""
			}),
		},
		error : function() {
			alert("table not create");
		},
		success : function(json) {
			$("#tableStat").html(
					'<tr>'+
					'<td width=100><b>Name</b></td>'+
					'<td width=150><b>Game</b></td>'+
					'<td width=150><b>GameWin</b></td>'+
					'<td width=150><b>Point</b></td>'+
					'<td width=150><b>Money</b></td>'+
				'</tr>'+
					'<tr>'+
						'<td width=100><b>Monopoly</b></td>'+
						'<td width=150><b>'+json.monoG+'</b></td>'+
						'<td width=150><b>'+json.monoW+'</b></td>'+
						'<td width=150><b>'+json.monoP+'</b></td>'+
						'<td width=150><b>'+json.monoM+'</b></td>'+
					'</tr>'+
					'<tr>'+
						'<td width=100><b>Chess</b></td>'+
						'<td width=150><b>'+json.chess+'</b></td>'+
						'<td width=150><b>'+json.chessW+'</b></td>'+
						'<td width=150><b></b></td>'+
						'<td width=150><b></b></td>'+
					'</tr>'+
					'<tr>'+
						'<td width=100><b>Gibbet</b></td>'+
						'<td width=150><b>'+json.gibbet+'</b></td>'+
						'<td width=150><b>'+json.gibbetW+'</b></td>'+
						'<td width=150><b></b></td>'+
						'<td width=150><b></b></td>'+
					'</tr>'+
					'<tr>'+
						'<td width=100><b>Draw</b></td>'+
						'<td width=150><b>'+json.draw+'</b></td>'+
						'<td width=150><b>'+json.drawW+'</b></td>'+
						'<td width=150><b></b></td>'+
						'<td width=150><b></b></td>'+
					'</tr>');
		}
	});
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
document.getElementById('edit').onclick = showEditIcon;
window.onload = table;