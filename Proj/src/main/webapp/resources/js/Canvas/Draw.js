/* 
 Document   : Draw.js
 Created on : Nov 27, 2015
 Author     : IS204-5
 */

var canvas = document.getElementById('myCanvas');
var context = canvas.getContext('2d');
var newline = [];

// initiate drawing
canvas.onmousedown = function (e) {
    var pos = getMousePos(canvas, e);
    newline[0] = pos.x;
    newline[1] = pos.y;
};

// finish drawing (for continuous drawing use canvas.onmousemove)
canvas.onmouseup = function (e) {
    var pos = getMousePos(canvas, e);
    newline[2] = pos.x;
    newline[3] = pos.y;
    drawLine(newline[0] - centerX,
            newline[1] - centerY,
            newline[2] - centerX,
            newline[3] - centerY,
            15, 'rgba(250, 0, 0, 0.5)');
    saveLine();
};

// gets exact mouse position regardless of resized windows
function getMousePos(canvas, evt) {
    var rect = canvas.getBoundingClientRect();
    return {
        x: Math.round((evt.clientX - rect.left) / (rect.right - rect.left) * canvas.width),
        y: Math.round((evt.clientY - rect.top) / (rect.bottom - rect.top) * canvas.height)
    };
}

// saves line and creates statistic of that line
function saveLine() {
    var r = confirm('Do you wish to save this line?');
    if (r === true) {
        createLine(newline[0], newline[1], newline[2], newline[3]);
        addList();
        var select = document.getElementById("dropDown");
        checkIntersections(select.options[select.options.length - 1].value,
                newline[0], newline[1], newline[2], newline[3]);
    }
}