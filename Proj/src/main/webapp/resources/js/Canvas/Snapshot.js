/* 
 Document   : Snapshot.js
 Created on : Nov 5, 2015
 Author     : IS204-5
 */

// Plays event to draw on canvas
var playing = false;
addList();
var img = new Image();
img.src = 'resources/img/filmed.png';
setTimeout(function () {
    img.onload = drawBackground();
}, 1);
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

// Adds lines to dropdown menu.
function addList() {
    var select = document.getElementById("dropDown");
    var clearButton = document.getElementsByClassName("enter")[1]; // 'Clear lines' button
    for (i = select.options.length - 1; i >= 0; i--) {
        select.remove(i);
        clearButton.disabled = true;
    }
    linesExist = false;
    for (i = 0; i < lines.length; i++) {
        var option = document.createElement('option');
        option.text = 'Line alert ' + (i + 1);
        select.add(option, i);
        linesExist = true;
        clearButton.disabled = false;
    }
    if (!linesExist) {
        addDefaultOptionList();
        clearButton.disabled = true;
    } else {
        select.disabled = false;
        clearButton.disabled = false;
    }
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

// starts the loop between start and end time
function play(s, e) {
    if (playing === false) {
        playing = true;
        enterButtonValue();
        playPersons(timeToMillis(s), timeToMillis(e));
    } else {
        playing = false; // Pauses the playPersons function.
        enterButtonValue();
    }
}

// reloads page and resets timestamp count in each person object
function stop() {
    resetPersonCount();
    location.reload();
}

/* iterates trough all person objects within start and end time
 * list = person array containing t timestamp, x Position, y Position
 * t = timestamp array
 * s = start time
 * e = end time
 * i, j = iterators within loops
 */
function playPersons(s, e) {
    if (s < e) {                // starttime has to be smaller than endtime
        function nextFrame(i) { // starts iterating trough frame
            drawPersons(s, i);  // draws frame with persons and background
            if (playing && s + i !== e)    // it its not starttime == endtime
                document.getElementById("time1").stepUp(1); // html input time++
            else
                return;
            setTimeout(function () {
                nextFrame(i + 1);
            }, playSpeed);
        }
        nextFrame(0);
    }
}

// Calculates if person has passed starttime and starts painting dots and stripes
function drawPersons(starttime, i) {
    // refers to Dotmap or Heatmap to determine if canvas needs cleaning
    cleanCanvas();
    for (j = 0; j < persons.length; j++) {
        if (persons[j].counter > 0) {
            // if iterator in person has started
            drawPerson(j);
            drawTrack(j);
        } else if (starttime + i == persons[j].t[0]) {
            // if iterator matches starttime
            drawPerson(j);
            drawTrack(j);
        } else if (starttime + i > persons[j].t[0]) {
            // if iterator has passed starttime
            for (k = 0; starttime + i > persons[j].t[k]; k++) {
                persons[j].count();
            }
            drawPerson(j);
            drawTrack(j);
        }
    }
}

// draws background
function drawBackground() {
    drawScene(document.getElementById('scenery').checked, img);
    drawLines(); // Draws user drawn lines.
    highlight(); // Highlights the selected line.
}

// draws movement web
function drawTrack(i) {
    if (document.getElementById('tracks').checked) {
        drawLine((-90 * persons[i].x[persons[j].counter - 2]) + centerX,
                (90 * -persons[i].y[persons[j].counter - 2]) + centerY,
                (-90 * persons[i].x[persons[j].counter - 1]) + centerX,
                (90 * -persons[i].y[persons[j].counter - 1]) + centerY
                , 3, '#606060');
    }
}

// highlights selected line
function highlight() {
    if (linesExist) {
        var ln = document.getElementById('dropDown').selectedIndex;
        if (ln !== -1)
            drawLine(lines[ln].x1, lines[ln].y1, lines[ln].x2, lines[ln].y2, 17, 'rgba(100, 0, 200, 0.2)');
    }
}

// resets timestamp position for every person
function resetPersonCount() {
    for (i = 0; i < persons.length; i++) {
        persons[i].resetCount();
    }
    playing = false;
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