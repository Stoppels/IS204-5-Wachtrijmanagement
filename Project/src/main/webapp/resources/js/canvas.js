
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// init
var canvas = document.getElementById("myCanvas");
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;
var dotSize = 5;

//determination of center positions x and y axis
var centerX = canvas.width / 2;
var centerY = canvas.height / 2;

// set the canvas origin (0,0) to center canvas
// All coordinates to the left of center canvas are negative
// All coordinates below center canvas are negative


//declare x and y axis
var xAxis = canvas.getContext("2d");
var yAxis = canvas.getContext("2d");

//draw x axis
xAxis.beginPath();
xAxis.moveTo(0,centerY);
xAxis.lineTo(canvas.width, centerY);
xAxis.stroke();

//draw y axis
yAxis.beginPath();
yAxis.moveTo(centerX,0);
yAxis.lineTo(centerX, canvas.height);
xAxis.stroke();

/**
 * 
 * @param {string} dotname
 * @param {int} x
 * @param {int} y
 * @returns {void}
 */
function drawDot(dotname, xPos, yPos, color, stringName){
    // create object
    var dotname = canvas.getContext("2d");
    dotname.beginPath();
    dotname.arc((xPos + centerX), (-yPos + centerY), dotSize, 0, 2 * Math.PI);
    dotname.stroke();
    dotname.fillStyle = '#ff00ff';
    dotname.fillStyle = "#"+color;
    dotname.fill();

}

drawDot("test", 100, -20, "ff0000");
drawDot("foo", -100, 10, "00ff00");
drawDot("bar", 20, -30, "0000ff");
drawDot("blaat", 80, 40, "f0f0f0");
drawDot("asdf", 40, 200, "ff00ff")
