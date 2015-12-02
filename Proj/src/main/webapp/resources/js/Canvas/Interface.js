/* 
 Document   : Interface.js
 Created on : Dec 01, 2015
 Author     : IS204-5
 */

function slowDown() {
    if (playSpeed < 1950) {
        playSpeed += 100;
        displaySpeed = Math.abs((playSpeed / 100) -20);
        document.getElementById("speed").innerHTML = displaySpeed;
    }
}

function speedUp() {
    if (playSpeed > 50) {
        playSpeed -= 100;
        displaySpeed = Math.abs((playSpeed / 100) -20);
        document.getElementById("speed").innerHTML = displaySpeed;
    }
}

// Adapt the 'Enter' button value to reflect animation state.
function enterButtonValue() {
    var button = document.getElementById("Start");
    playing ? button.value = "Pause" : button.value = "Start";
}

function disableClearButtonIf(argument, dropDownDisabled) {
    var clearButton = document.getElementsByClassName("enter")[1];
    var select = document.getElementById('dropDown');
    var option = document.createElement('option');
    if (argument) {
        clearButton.disabled = true;
        if (dropDownDisabled) {
            select.disabled = true;
            option.text = '-- Draw a line first --';
            select.add(option, 0);
        }
    } else {
        clearButton.disabled = false;
        select.disabled = false;
    }
}
