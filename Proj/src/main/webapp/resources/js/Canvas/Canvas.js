/* 
 Document   : Canvas.js
 Created on : Nov 3, 2015
 Author     : Stefan */

var canvas;
var context;
var centerX;
var centerY;
init();

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
//        context.globalAlpha = 0.5;
        
        context.drawImage(imageObj, (xPos + centerX), (-yPos + centerY), width, height);
    };
}

// Draws axis on canvas
function drawAxis() {
    //declare x and y axis
    var xAxis = canvas.getContext("2d");
    var yAxis = canvas.getContext("2d");

    yAxis.beginPath();
    yAxis.moveTo(centerX, 0);
    yAxis.lineTo(centerX, 1000);
    yAxis.lineWidth = 0.1;
    yAxis.stroke();

    xAxis.beginPath();
    xAxis.moveTo(0, centerY);
    xAxis.lineTo(1050, centerY);
    xAxis.lineWidth = 0.1;
    xAxis.stroke();

    // Draws numbers on the axis
    var a = 0;
    var b = 450;
    for (i = 0; i < 31; i++) {
        if (i !== 15)
            drawText(b, a, b - 9, 10);
            drawText(b, b, a - 9, 10);
        // spacing between numbers
        b -= 30;
    }
}

function drawBackground() {
    showImage("resources/img/counter.jpg", -500, 50);
}

// Clears canvas
function clear() {
    context.clearRect(0, 0, canvas.width, canvas.height);
    drawAxis();
}

// Initializes canvas
function init() {
    canvas = document.getElementById("myCanvas");
    context = canvas.getContext("2d");
    canvas.width = 1200;
    canvas.height = 770;

    //determination of center positions x and y axis
    centerX = canvas.width / 2;
    centerY = canvas.height / 2;
}