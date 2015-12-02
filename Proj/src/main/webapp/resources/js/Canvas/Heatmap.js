/* 
 Document   : Heatmap.js
 Created on : Nov 5, 2015
 Author     : IS204-5
 */

var playSpeed = 100;

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
function drawTrack(i) {
    if (document.getElementById('tracks').checked) {
        persons[i].track(i, 10, 'rgba(250, 0, 0, 0.08)');
    }
}