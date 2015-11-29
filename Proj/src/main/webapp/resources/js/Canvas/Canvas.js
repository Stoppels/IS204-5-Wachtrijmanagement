/* 
 Document   : Canvas.js
 Created on : Nov 3, 2015
 Author     : Stefan */


var canvas;
var context;
var centerX;
var centerY;

window.onload = init();

// colored dot for snapshot.js
function drawDot(xPos, yPos, color) {
    var dotSize = 7;
    context.beginPath();
    context.arc((xPos + centerX), (-yPos + centerY), dotSize, 0, 2 * Math.PI);
    context.closePath();
    context.stroke();

    context.save();
    context.fillStyle = color;
    context.strokeStyle = "rgba(0,153,255,1)";
    context.lineWidth = 2;
    context.stroke();
    context.fill();
    context.restore();
}



// heat dot for heatmap.js
function drawHeat(xPos, yPos) {
    var dotSize = 4;
    context.beginPath();
    context.arc((xPos + centerX), (-yPos + centerY), dotSize + 4, 0, 2 * Math.PI);
    context.closePath();

    context.save();
    context.shadowColor = '#FFFF00';
    context.shadowBlur = 45;
    context.fillStyle = 'rgba(250, 0, 0, 0.08)';
    context.fill();
    context.restore();
}

// rectangle
function drawRectangle(xPos, yPos, width, height, degree, color, opacity) {
    context.save();
    context.translate(xPos + centerX, -yPos + centerY);
    context.rotate(degree * Math.PI / 180);
    context.beginPath();
    context.rect(-width / 2, -height / 2, width, height);
    context.closePath();
    context.fillStyle = color;
    context.globalAlpha = opacity;
    context.fill();
    context.restore();
}

// text
function drawText(text, xPos, yPos, size) {
    context.font = 'normal ' + size + 'pt sans-serif';
    context.fillText(text, xPos + centerX, -yPos + centerY);
}

// line
function drawLine(line) {
    context.beginPath();
    context.save();
    context.moveTo(line.x1, line.y1);
    context.lineTo(line.x2, line.y2);
    context.lineWidth = 15;
    context.strokeStyle = 'rgba(250, 0, 0, 0.5)';
    context.stroke();
    context.restore();
}

//// line
//function drawLine(line) {
//    context.beginPath();
//    context.save();
//    context.moveTo(line[0], line[1]);
//    context.lineTo(line[2], line[3]);
//    context.lineWidth = 15;
//    context.strokeStyle = 'rgba(250, 0, 0, 0.5)';
//    context.stroke();
//    context.restore();
//    alert(line[0] + " " + line[1] + " " + line[2] + " " + line[3]);
//}

// info text
function drawInfo(text, xPos, yPos, size) {
    context.save();
    context.font = 'normal ' + size + 'pt Trebuchet MS';
    context.fillStyle = 'rgba(250, 250, 250, 0.8)';
    context.shadowBlur = 3;
    context.shadowColor = "black";
    context.fillText(text, xPos + centerX, -yPos + centerY);
    context.restore();
}

// quick text with only one parameter
function drawAlert(text) {
    context.fillStyle = '#000000';
    context.fillText(text, (0 + centerX), (-250 + centerY));
}

// image
function showImage(img, xPos, yPos, width, height) {
    var imageObj = img;
    context.save();
    context.drawImage(imageObj, (xPos + centerX) - width / 2, (-yPos + centerY) - height / 2, width, height);
}

// scene with added scenery
function drawScene(active, img) {
    if (active) {
        showImage(img, 530, -330, 1400, 1400);
        drawRectangle(555, -10, 650, 30, 14, '#0099FF', 0.6);
    }
}

// clears canvas
function clear() {
    context.clearRect(0, 0, canvas.width, canvas.height);
}

// initialises canvas
function init() {
    canvas = document.getElementById("myCanvas");
    context = canvas.getContext("2d");
    canvas.width = 1024;
    canvas.height = 768;
    centerX = 100;
    centerY = 100;
}