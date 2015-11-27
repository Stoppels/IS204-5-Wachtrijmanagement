/* 
 Document   : Draw.js
 Created on : Nov 27, 2015
 Author     : Stefan */


var canvas = document.getElementById('myCanvas');
var context = canvas.getContext('2d');
var isDrawing;

canvas.onmousedown = function (e) {
    var pos = getMousePos(canvas, e);

    isDrawing = true;
    context.moveTo(pos.x, pos.y);
};
canvas.onmousemove = function (e) {
    if (isDrawing) {
        var pos = getMousePos(canvas, e);

        context.save();
        context.lineTo(pos.x, pos.y);
        context.stroke();
        context.restore();
    }
};
canvas.onmouseup = function () {
    isDrawing = false;
};

function getMousePos(canvas, evt) {
    var rect = canvas.getBoundingClientRect();
    return {
        x: Math.round((evt.clientX - rect.left) / (rect.right - rect.left) * canvas.width),
        y: Math.round((evt.clientY - rect.top) / (rect.bottom - rect.top) * canvas.height)
    };
}