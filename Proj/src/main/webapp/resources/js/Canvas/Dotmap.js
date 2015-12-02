/* 
 Document   : Dotmap.js
 Created on : Nov 5, 2015
 Author     : IS204-5
 */

// clean canvas?
function cleanCanvas() {
    clear();
    drawBackground();
}

// draws dot and text while counting timestamp position
function drawPerson(j) {
    persons[j].dot(persons[j].counter);
    persons[j].text(persons[j].counter);
    persons[j].count();
}

// draws movement web
function drawTrack(i) {
    if (document.getElementById('tracks').checked) {
        persons[i].track(i);
    }
}