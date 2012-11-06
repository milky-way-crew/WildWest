//var myselect = document.getElementById('rooms');


function createOption(){
    var currentText = document.getElementById('roomName').value;
    var objOption = document.createElement("option");
    objOption.text = currentText ;
    objOption.value = currentText ;
    myselect.options.add(objOption);
}


//document.getElementById('createRoom').onclick = createOption;