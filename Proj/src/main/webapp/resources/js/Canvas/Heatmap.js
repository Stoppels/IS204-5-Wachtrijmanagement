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

function stop() {
    location.reload();
}

function showPersons(s, e) {
    function nextPerson(i)
    {
        function nextDot(j)
        {
            if (s <= list[i].t[j] && list[i].t[j] <= e) {
                list[i].heat(j);
            }
            if (i === list[i].t.length)
                return;
            setTimeout(function ()
            {
                nextDot(j + 1);
            }, 1);
        }
        nextDot(0);
        if (i === list.length)
            return;
        setTimeout(function ()
        {
            nextPerson(i + 1);
        }, 1);
    }
    nextPerson(0);
}

function drawBackground() {
    drawScene(document.getElementById('scenery').checked, img);
}