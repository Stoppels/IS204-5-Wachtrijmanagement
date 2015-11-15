/* 
 Document   : Canvas.js
 Created on : Nov 3, 2015
 Author     : Stefan */


var canvas;
var context;
var centerX;
var centerY;

window.onload = init();

// Draws dot on canvas
function drawDot(xPos, yPos, color) {
    var dotSize = 7;
    context.beginPath();
    context.arc((xPos + centerX), (-yPos + centerY), dotSize, 0, 2 * Math.PI);

    context.save();
    context.stroke();
    context.shadowColor = '#000';
    context.shadowBlur = 0.2;
    context.fillStyle = color;
    context.fill();
    context.restore();
}

// Draws dot on canvas
function drawHeat(xPos, yPos) {
    var dotSize = 3;
    context.beginPath();
    context.arc((xPos + centerX), (-yPos + centerY), dotSize + 4, 0, 2 * Math.PI);

    context.save();
    context.shadowColor = '#FFFF00';
    context.shadowBlur = 40;
    context.fillStyle = 'rgba(250, 0, 0, 0.02)';
    context.fill();
    context.restore();

}

// Draws rectangle on canvas
function drawRectangle(xPos, yPos, width, height, degree, color) {
    context.save();
    context.translate(xPos + centerX, -yPos + centerY);
    context.rotate(degree * Math.PI / 180);
    context.beginPath();
    context.rect(-width / 2, -height / 2, width, height);
    context.fillStyle = color;
    context.fill();
    context.restore();
}

function drawScene(active) {
    if (active) {
        showImage('resources/img/filmed.png', 530, -330, 1400, 1400);
        drawRectangle(520, -25, 600, 50, 14, '#FF0000');
    }
}

// Draws text on canvas
function drawText(text, xPos, yPos, size) {
    context.font = 'italic ' + size + 'pt Ubuntu';
    context.fillStyle = '#000000';
    context.fillText(text, xPos + centerX, -yPos + centerY);
}

// Draws quick alert on canvas
function drawAlert(text) {
    context.fillStyle = '#000000';
    context.fillText(text, (0 + centerX), (-250 + centerY));
}

function showImage(src, xPos, yPos, width, height) {
    var imageObj = new Image();
    imageObj.src = src;
    imageObj.onload = function () {
        context.save();
        context.globalAlpha = 0.5;
        context.drawImage(imageObj, (xPos + centerX) - width / 2, (-yPos + centerY) - height / 2, width, height);
    };
}

// Clears canvas
function clear() {
    context.clearRect(0, 0, canvas.width, canvas.height);
}

// Initializes canvas
function init() {
    canvas = document.getElementById("myCanvas");
    context = canvas.getContext("2d");
    canvas.width = 1200;
    canvas.height = 770;
    //determination of center positions x and y axis
    centerX = 100;
    centerY = 100;
}