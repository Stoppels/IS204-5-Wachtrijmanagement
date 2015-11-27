/* 
 Document   : Draw.js
 Created on : Nov 27, 2015
 Author     : Stefan */


var canvas = document.getElementById('myCanvas');
var context = canvas.getContext('2d');
var isDrawing;
var yOffset = 2;
var xOffset = -311;

canvas.onmousedown = function (e) {
    isDrawing = true;
    context.moveTo(e.clientX + xOffset, e.clientY + yOffset);
};
canvas.onmousemove = function (e) {
    if (isDrawing) {
        context.save();
        context.lineTo(e.clientX + xOffset, e.clientY + yOffset);
        context.stroke();
        context.restore();
    }
};
canvas.onmouseup = function () {
    isDrawing = false;
};