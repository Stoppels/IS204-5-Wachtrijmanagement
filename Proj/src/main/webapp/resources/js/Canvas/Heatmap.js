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
    persons[j].dot(persons[j].counter);
    persons[j].count();
}