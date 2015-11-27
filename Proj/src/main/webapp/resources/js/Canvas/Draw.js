/* 
 Document   : Draw.js
 Created on : Nov 27, 2015
 Author     : Stefan */

var canvas = document.getElementById('myCanvas');
var context = canvas.getContext('2d');
var line = [];

// initiate drawing
canvas.onmousedown = function (e) {
    var pos = getMousePos(canvas, e);
    context.beginPath();
    context.moveTo(pos.x, pos.y);
    line[0] = pos.x;
    line[1] = pos.y;
};

// finish drawing (for continuous drawing use canvas.onmousemove)
canvas.onmouseup = function (e) {
    var pos = getMousePos(canvas, e);
    context.save();
    context.lineTo(pos.x, pos.y);
    line[2] = pos.x;
    line[3] = pos.y;
    context.lineWidth = 15;
    context.strokeStyle = 'rgba(250, 0, 0, 0.5)';
    context.stroke();
    context.restore();
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

// saves line
function saveLine() {
    var r = confirm('Do you wish to save this line?');
    if (r === true) {
        lines[lines.length] = line;
    }
}