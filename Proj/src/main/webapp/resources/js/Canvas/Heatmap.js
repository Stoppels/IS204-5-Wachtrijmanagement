/* 
 Document   : Heatmap.js
 Created on : Nov 5, 2015
 Author     : IS204-5
 */

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