/* 
 Document   : Interface.js
 Created on : Dec 01, 2015
 Author     : IS204-5
 */

function slowDown() {
    if (playSpeed < 1950) {
        playSpeed += 100;
        document.getElementById("speed").innerHTML = playSpeed;
    }
}

function speedUp() {
    if (playSpeed > 50) {
        playSpeed -= 100;
        document.getElementById("speed").innerHTML = playSpeed;
    }
}

// Adapt the 'Enter' button value to reflect animation state.
function enterButtonValue() {
    var button = document.getElementById("Start");
    playing ? button.value = "Pause" : button.value = "Start";
}

function clearButtonValue(list) {
    var clearButton = document.getElementsByClassName("enter")[1];
    var select = document.getElementById('dropDown');
    var option = document.createElement('option');
    if (list.length <= 0) {
        clearButton.disabled = true;
        select.disabled = true;
        option.text = '-- Draw a line first --';
        select.add(option, 0);
    } else {
        clearButton.disabled = false;
        select.disabled = false;
    }
}
