/* 
 Document   : Draw.js
 Created on : Nov 27, 2015
 Author     : Stefan */

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
    
    context.save();
    context.beginPath();
    context.moveTo(newline[0], newline[1]);
    context.lineTo(newline[2], newline[3]);
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
        createLine(newline[0], newline[1], newline[2], newline[3]);
        addList();
        checkIntersections(newline[0], newline[1], newline[2], newline[3]);
//        createStats(int id, String name,String[] labels, int[] data);
    }
}