/* 
 Document   : Snapshot.js
 Created on : Nov 5, 2015
 Author     : Stefan */

window.onload = drawAxis();
var playing = false;

// Plays event to draw on canvas
function play(s, e) {
    if (playing === false) {
        playing = true;
        clear();
        init();
        playPersons(timeToMillis(s), timeToMillis(e));
    } else {
        window.confirm("Press 'OK' to continue playing.");
    }
}

function stop() {
    location.reload();
}

// shows corrensponding colors and numbers of the persons
function init() {
    var x = 565;
    var y = 375;
    for (i = 0; i < list.length; i++) {
        drawText(list[i].id, x, y, 10);
        drawDot(x + 25, y + 4, list[i].color);
        y -= 20;
    }
}

function showPersons(s, e) {
    function nextPerson(i)
    {
        function nextDot(j)
        {
            if (s <= list[i].t[j] && list[i].t[j] <= e) {
                list[i].dot(j);
                list[i].text(j);
            }
            if (i === list[i].t.length)
                return;
            setTimeout(function ()
            {
                nextDot(j + 1);
            }, 0);
        }
        nextDot(0);
        if (i === list.length)
            return;
        setTimeout(function ()
        {
            nextPerson(i + 1);
        }, 0);
    }
    nextPerson(0);
}


function playPersons(s, e) {
    function nextFrame(i)
    {
        if (s > e) {
            playing = false;
            return;
        }
        clear();
        init();
        showPersons(s + i, s + i);
        document.getElementById("time1").stepUp(1);
        if (s + i === e) {
            return;
        }
        setTimeout(function ()
        {
            nextFrame(i + 1);
        }, 1000);
    }
    nextFrame(1);
}





