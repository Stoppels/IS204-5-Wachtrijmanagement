/* 
 Document   : Heatmap.js
 Created on : Nov 5, 2015
 Author     : Stefan */

var playing = false;
var img = new Image();
img.src = 'resources/img/filmed.png';
setTimeout(function () {
    img.onload = drawBackground();
}, 1);

// Plays event to draw on canvas
function play(s, e) {
    clear();
    drawBackground();
    showPersons(timeToMillis(s), timeToMillis(e));
    playing = true;
}

// reloads page
function stop() {
    localStorage.setItem('session', null);
    location.reload();
}

// loops trough heat dots within start and end time
function showPersons(s, e) {
    function nextPerson(i)
    {
        function nextDot(j)
        {
            if (s <= persons[i].t[j] && persons[i].t[j] <= e) {
                persons[i].heat(j);
            }
            if (i === persons[i].t.length)
                return;
            setTimeout(function ()
            {
                nextDot(j + 1);
            }, 1);
        }
        nextDot(0);
        if (i === persons.length)
            return;
        setTimeout(function ()
        {
            nextPerson(i + 1);
        }, 1);
    }
    nextPerson(0);
}

// draws background
function drawBackground() {
    drawScene(document.getElementById('scenery').checked, img);
    drawLines();
}