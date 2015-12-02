/* 
 Document   : Snapshot.js
 Created on : Nov 5, 2015
 Author     : IS204-5
 */

// Plays event to draw on canvas
addList();
var playing = false;
var img = new Image();
img.src = 'resources/img/filmed.png';
setTimeout(function () {
    img.onload = drawBackground();
}, 1);

// Adds lines to dropdown menu.
function addList() {
    var select = document.getElementById('dropDown');
    for (i = select.options.length; i >= 0; i--) {
        select.remove(i);
    }
    for (i = 0; i < lines.length; i++) {
        var option = document.createElement('option');
        option.text = 'Line alert ' + (i + 1);
        select.add(option, i);
    }
    disableClearButtonIf(lines.length <= 0, true);
}

// starts the loop between start and end time
function play(s, e) {
    if (playing === false) {
        playing = true;
        playPersons(timeToMillis(s), timeToMillis(e));
    } else {
        playing = false; // Pauses the playPersons function.
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
    drawBackground();
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
    enterButtonValue();
    cleanCanvas();
    for (j = 0; j < persons.length; j++) {
        if (persons[j].counter > 0) {
            // if iterator in person has started
            drawTrack(j);
            drawPerson(j);
        } else if (starttime + i == persons[j].t[0]) {
            // if iterator matches starttime
            drawTrack(j);
            drawPerson(j);
        } else if (starttime + i > persons[j].t[0]) {
            // if iterator has passed starttime
            for (k = 0; starttime + i > persons[j].t[k]; k++) {
                persons[j].count();
            }
            drawTrack(j);
            drawPerson(j);
        }
    }
}

// scene with added scenery
function drawBackground() {
    clear();
    if (document.getElementById('scenery').checked) {
        showImage(img, 0, 0, 728 * scale, 672 * scale);
        drawRectangle(-20, -260, 570, 30, 13, '#0099FF', 0.2);
    }
    drawLines(); // Draws user drawn lines.
}

// resets timestamp position for every person
function resetPersonCount() {
    for (i = 0; i < persons.length; i++) {
        persons[i].resetCount();
    }
    playing = false;
}