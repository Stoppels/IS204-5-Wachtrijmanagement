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
// ######################### IF THIS DOESN'T WORK ##############################
// ####################### FIND SOMETHING TRANSPARENT ##########################
// ############################ THAT DOES WORK #################################
// ####################### DON'T REPLACE WITH '#xxxxxx' ########################
function drawTrack(i) {
    if (document.getElementById('tracks').checked) {
        persons[i].track(i, 1, 'rgba(255, 255, 255, 0.5)');
    }
}