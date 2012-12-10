ajax_url = "ajaxgibbet";
var leng;


function chngImage() {
	var num = document.getElementById('live').value;
	     img = new Image();
	     img.src = "resources/img/gibbet/gibbet"+num+".jpg";
	     document.getElementById('image').src = img.src;
	}




function select() {
	$.ajax({
		url : ajax_url,
		type : "POST",
		data : {
			type : "something",
			data : document.getElementById('list').value,  
			word : ""
		},
		error : function() {
			alert("Message not send random  "+ document.getElementById('list').value);
		},
		success : function(text) {
			document.getElementById('word').value=text;
			$("#usermsg").val("");
			document.getElementById('live').value=7;
			document.getElementById('select').style.visibility = "hidden";
			visible();
			leng=text.length;
		}
	});
	 document.getElementById('image').src = "resources/img/gibbet/gibbet7.jpg";
};



function button() {
	var idButton = $(this).attr('id');
	$.ajax({
		url : ajax_url,
		type : "POST",
		data : {
			type : "message",
			data : document.getElementById(idButton).id,
			word : document.getElementById('word').value
		},
		error : function() {
			alert("Message not send");
		},
		success : function(json) {
			if(json==""){
				$("#usermsg").val("");
				document.getElementById('live').value-=1;
				document.getElementById(idButton).style.visibility = "hidden";
				chngImage();
				if(document.getElementById('live').value==0){
					document.getElementById('select').style.visibility = "visible";
//					alert("you lose");
					hidden();
					$('#lose').modal();
				}
			}
			else{
				if(json.length>leng){
					document.getElementById('select').style.visibility = "visible";
//					alert("you win");
					hidden();
					json=json.substr(8,json.length);
					$('#win').modal();
				}
			document.getElementById('word').value=json;
			document.getElementById(idButton).style.visibility = "hidden";
			}
			
			
		},
	});
	
};

function visible(){
	document.getElementById('q').style.visibility = "visible";
	document.getElementById('w').style.visibility = "visible";
	document.getElementById('e').style.visibility = "visible";
	document.getElementById('r').style.visibility = "visible";
	document.getElementById('t').style.visibility = "visible";
	document.getElementById('y').style.visibility = "visible";
	document.getElementById('u').style.visibility = "visible";
	document.getElementById('i').style.visibility = "visible";
	document.getElementById('o').style.visibility = "visible";
	document.getElementById('p').style.visibility = "visible";
	document.getElementById('a').style.visibility = "visible";
	document.getElementById('s').style.visibility = "visible";
	document.getElementById('d').style.visibility = "visible";
	document.getElementById('f').style.visibility = "visible";
	document.getElementById('g').style.visibility = "visible";
	document.getElementById('h').style.visibility = "visible";
	document.getElementById('j').style.visibility = "visible";
	document.getElementById('k').style.visibility = "visible";
	document.getElementById('l').style.visibility = "visible";
	document.getElementById('z').style.visibility = "visible";
	document.getElementById('x').style.visibility = "visible";
	document.getElementById('c').style.visibility = "visible";
	document.getElementById('v').style.visibility = "visible";
	document.getElementById('b').style.visibility = "visible";
	document.getElementById('n').style.visibility = "visible";
	document.getElementById('m').style.visibility = "visible";
	
}



function hidden(){
	document.getElementById('q').style.visibility = "hidden";
	document.getElementById('w').style.visibility = "hidden";
	document.getElementById('e').style.visibility = "hidden";
	document.getElementById('r').style.visibility = "hidden";
	document.getElementById('t').style.visibility = "hidden";
	document.getElementById('y').style.visibility = "hidden";
	document.getElementById('u').style.visibility = "hidden";
	document.getElementById('i').style.visibility = "hidden";
	document.getElementById('o').style.visibility = "hidden";
	document.getElementById('p').style.visibility = "hidden";
	document.getElementById('a').style.visibility = "hidden";
	document.getElementById('s').style.visibility = "hidden";
	document.getElementById('d').style.visibility = "hidden";
	document.getElementById('f').style.visibility = "hidden";
	document.getElementById('g').style.visibility = "hidden";
	document.getElementById('h').style.visibility = "hidden";
	document.getElementById('j').style.visibility = "hidden";
	document.getElementById('k').style.visibility = "hidden";
	document.getElementById('l').style.visibility = "hidden";
	document.getElementById('z').style.visibility = "hidden";
	document.getElementById('x').style.visibility = "hidden";
	document.getElementById('c').style.visibility = "hidden";
	document.getElementById('v').style.visibility = "hidden";
	document.getElementById('b').style.visibility = "hidden";
	document.getElementById('n').style.visibility = "hidden";
	document.getElementById('m').style.visibility = "hidden";
}

hidden();
document.getElementById('select').onclick = select;
document.getElementById('word').disabled = true;
document.getElementById('live').disabled = true;
document.getElementById('q').onclick = button;
document.getElementById('w').onclick = button;
document.getElementById('e').onclick = button;
document.getElementById('r').onclick = button;
document.getElementById('t').onclick = button;
document.getElementById('y').onclick = button;
document.getElementById('u').onclick = button;
document.getElementById('i').onclick = button;
document.getElementById('o').onclick = button;
document.getElementById('p').onclick = button;
document.getElementById('a').onclick = button;
document.getElementById('s').onclick = button;
document.getElementById('d').onclick = button;
document.getElementById('f').onclick = button;
document.getElementById('g').onclick = button;
document.getElementById('h').onclick = button;
document.getElementById('j').onclick = button;
document.getElementById('k').onclick = button;
document.getElementById('l').onclick = button;
document.getElementById('z').onclick = button;
document.getElementById('x').onclick = button;
document.getElementById('c').onclick = button;
document.getElementById('v').onclick = button;
document.getElementById('b').onclick = button;
document.getElementById('n').onclick = button;
document.getElementById('m').onclick = button;


