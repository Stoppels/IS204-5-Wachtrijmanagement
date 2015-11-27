/* 
 Document   : Draw.js
 Created on : Nov 27, 2015
 Author     : Stefan */

var canvas = document.getElementById('myCanvas');
var context = canvas.getContext('2d');

// initiate drawing
canvas.onmousedown = function (e) {
    var pos = getMousePos(canvas, e);
    context.beginPath();
    context.moveTo(pos.x, pos.y);
};

// finish drawing (for continuous drawing use canvas.onmousemove)
canvas.onmouseup = function (e) {
    var pos = getMousePos(canvas, e);
    context.save();
    context.lineTo(pos.x, pos.y);
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

// saves drawing
function saveLine() {
    // saves line to some array to display each frame
    // saves line to some file
}