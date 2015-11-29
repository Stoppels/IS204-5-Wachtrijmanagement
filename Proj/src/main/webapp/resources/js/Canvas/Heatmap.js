/* 
 Document   : Snapshot.js
 Created on : Nov 5, 2015
 Author     : Stefan */

// speed of frames in ms
var playspeed = 1;

// clean canvas?
function cleanCanvas() {
//    clear();
//    drawBackground();
}

// draws dot and text while counting timestamp position
function drawPerson(j) {
    persons[j].heat(persons[j].counter);
    persons[j].count();
}

// draws movement web
function drawMap(i) {
    for (j = 1; j < persons[i].t.length; j++) {
        drawLine((-90 * persons[i].x[j - 1]) + centerX,
                (90 * -persons[i].y[j - 1]) + centerY,
                (-90 * persons[i].x[j]) + centerX,
                (90 * -persons[i].y[j]) + centerY
                , 3, '#606060');
    }
}