/* 
 Document   : Interface.js
 Created on : Dec 01, 2015
 Author     : IS204-5
 */

var DEFAULT_DOTS_SPEED = 1000; // Framespeed in ms (1000 = 1 FPS), used as if constant.
var DEFAULT_HEAT_SPEED = 50; // Framespeed in ms (20 = 50 FPS), used as if constant.
var playSpeed; // The used and adjusted speed variable.

// Adds default option to dropdown menu.
function addDefaultOptionList() {
    var select = document.getElementById("dropDown");
    var option = document.createElement('option');
    option.text = '-- Draw a line first --';
    select.add(option, 0);
    select.disabled = true;
}

// Adjust the speed depending on the selected button.
function adjustSpeed() {
    var path = window.location.pathname;
    if (path.indexOf('snapshot') > -1) {
        if (playSpeed === DEFAULT_DOTS_SPEED) { // If speed was set to normal, speed it up.
            playSpeed = 500;
            document.getElementById("slowSpeed").disabled = false;
            document.getElementById("highSpeed").disabled = true;
        } else { // Speed the animation down (default state).
            playSpeed = DEFAULT_DOTS_SPEED;
            document.getElementById("slowSpeed").disabled = true;
            document.getElementById("highSpeed").disabled = false;
        }
    } else if (path.indexOf('heatmap') > -1) {
        if (playSpeed === DEFAULT_HEAT_SPEED) { // If speed was set to normal, speed it up.
            playSpeed = 2;
            document.getElementById("slowSpeed").disabled = false;
            document.getElementById("highSpeed").disabled = true;
        } else { // Speed the animation down (default state).
            playSpeed = DEFAULT_HEAT_SPEED;
            document.getElementById("slowSpeed").disabled = true;
            document.getElementById("highSpeed").disabled = false;
        }
    }
}

// Adapt the 'Enter' button value to reflect animation state.
function enterButtonValue() {
    document.getElementsByClassName("enter")[0].value = playing ? "Pause" : "Play";
}

// draws background
function drawBackground() {
    drawScene(document.getElementById('scenery').checked, img);
    drawLines(); // Draws user drawn lines.
    highlight(); // Highlights the selected line.
}

// highlights selected line
function highlight() {
    if (linesExist) {
        var ln = document.getElementById('dropDown').selectedIndex;
        if (ln !== -1)
            drawLine(lines[ln].x1, lines[ln].y1, lines[ln].x2, lines[ln].y2, 17, 'rgba(100, 0, 200, 0.2)');
    }
}

// Sets the initial framerate speed for the snapshot and heatmap views.
function setInitialSpeed() {
    var path = window.location.pathname;
    if (path.indexOf('snapshot') > -1) {
        playSpeed = DEFAULT_DOTS_SPEED;
    } else if (path.indexOf('heatmap') > -1) {
        playSpeed = DEFAULT_HEAT_SPEED;
    }
}


