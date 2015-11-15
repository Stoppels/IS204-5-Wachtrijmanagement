/* 
 Document   : Snapshot.js
 Created on : Nov 5, 2015
 Author     : Stefan */

// Plays event to draw on canvas
var playing = false;
function play(s, e) {
    if (playing === false) {
        playing = true;
        clear();
        playPersons(timeToMillis(s), timeToMillis(e));
    } else {
        window.confirm("Press 'OK' to continue playing.");
    }
}

function stop() {
    resetPersonCount();
    location.reload();
}

function playPersons(s, e) {
    if (s < e) {
        function nextFrame(i)
        {
            clear();
            drawScene(document.getElementById('scenery').checked);
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

function drawPerson(j) {
    list[j].dot(list[j].counter);
    list[j].text(list[j].counter);
    list[j].count();
}

function resetPersonCount() {
    for (i = 0; i < list.length; i++) {
        list[i].resetCount();
    }
    playing = false;
}





