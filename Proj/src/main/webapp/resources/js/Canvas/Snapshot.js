/* 
 Document   : Snapshot.js
 Created on : Nov 5, 2015
 Author     : Stefan */

// Plays event to draw on canvas
var playing = false;
addList();
var img = new Image();
img.src = 'resources/img/filmed.png';
setTimeout(function () {
    img.onload = drawBackground();
}, 1);


// adds lines to dropdown menu
function addList() {
    var select = document.getElementById("dropDown");
    for (i = select.options.length - 1; i >= 0; i--) {
        select.remove(i);
    }
    for (i = 0; i < lines.length; i++) {
        var option = document.createElement('option');
        option.text = 'Line alert ' + (i + 1);
        select.add(option, i);
    }
}

// starts the loop between start and end time
function play(s, e) {
    if (playing === false) {
        playing = true;
        playPersons(timeToMillis(s), timeToMillis(e));
    }
}

// reloads page and resets timestamp count in each person object
function stop() {
    resetPersonCount();
    location.reload();
}

function clearLines() {
    localStorage.setItem('session', null);
    lines = new Array();
    clear();
    drawBackground();
    addList();
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
            if (s + i !== e)    // it its not starttime == endtime
                document.getElementById("time1").stepUp(1); // html input time++
            else
                return;
            setTimeout(function () {
                nextFrame(i + 1);
            }, playspeed);
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
    drawLines(); // user drawn lines
}

// draws movement web
function drawTrack(i) {
    if (document.getElementById('tracks').checked) {
        drawLine((-90 * persons[i].x[persons[j].counter - 2]) + centerX,
                (90 * -persons[i].y[persons[j].counter - 2]) + centerY,
                (-90 * persons[i].x[persons[j].counter-1]) + centerX,
                (90 * -persons[i].y[persons[j].counter-1]) + centerY
                , 3, '#606060');

    }
}

// resets timestamp position for every person
function resetPersonCount() {
    for (i = 0; i < persons.length; i++) {
        persons[i].resetCount();
    }
    playing = false;
}

// highlights selected line
function highlight() {
    var ln = document.getElementById('dropDown').selectedIndex;
    if (ln !== -1)
        drawLine(lines[ln].x1, lines[ln].y1, lines[ln].x2, lines[ln].y2, 17, 'rgba(100, 200, 0, 0.2)');
}