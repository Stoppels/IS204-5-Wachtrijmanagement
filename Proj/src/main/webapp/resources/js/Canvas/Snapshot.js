/* 
 Document   : Snapshot.js
 Created on : Nov 5, 2015
 Author     : Stefan */

// Plays event to draw on canvas
var playing = false;
var img = new Image();
img.src = 'resources/img/filmed.png';
setTimeout(function () {
    img.onload = drawBackground();
}, 1);

// starts the loop between start and end time
function play(s, e) {
    if (playing === false) {
        playing = true;
        clear();
        playPersons(timeToMillis(s), timeToMillis(e));
    } else {
        window.confirm("Press 'OK' to continue playing.");
    }
}

// reloads page and resets timestamp count in each person object
function stop() {
    resetPersonCount();
    location.reload();
}


/* iterates trough all person objects within start and end time
 * list = person array containing t timestamp, x Position, y Position
 * t = timestamp array
 * s = start time
 * e = end time
 * i, j = iterators within loops
 */
function playPersons(s, e) {
    if (s < e) {
        function nextFrame(i)
        {
            clear();
            drawBackground();
            for (j = 0; j < list.length; j++) {
                if (list[j].counter > 0) {
                    drawPerson(j);
                } else
                if (s + i == list[j].t[0]) {
                    drawPerson(j);
                } else
                if (s + i > list[j].t[0]) {
                    for (k = 0; s + i > list[j].t[k]; k++) {
                        list[j].count();
                    }
                    drawPerson(j);
                }
            }
            if (s + i !== e)
                document.getElementById("time1").stepUp(1);
            if (s + i === e) {
                alert("End of file");
                stop();
                return;
            }
            setTimeout(function ()
            {
                nextFrame(i + 1);
            }, 1000);
        }
        nextFrame(0);
    }
}

// draws background
function drawBackground() {
    drawScene(document.getElementById('scenery').checked, img);
    drawLines();
}

// draws dot and text while counting timestamp position
function drawPerson(j) {
    list[j].dot(list[j].counter);
    list[j].text(list[j].counter);
    list[j].count();
}

// resets timestamp position for every person
function resetPersonCount() {
    for (i = 0; i < list.length; i++) {
        list[i].resetCount();
    }
    playing = false;
}





