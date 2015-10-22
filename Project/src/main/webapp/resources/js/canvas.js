
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var centerX;
var centerY;
var canvas;


init();
drawAxis();

drawDot("test", 100, -20, "ff0000");
//drawDot("foo", -100, 10, "00ff00");
//drawDot("bar", 20, -30, "0000ff");
//drawDot("blaat", 80, 40, "f0f0f0");
//drawDot("asdf", 40, 200, "ff00ff");




// Initializes canvas
function init() {
    canvas = document.getElementById("myCanvas");
    canvas.width = 800;
    canvas.height = 800;

    //determination of center positions x and y axis
    centerX = canvas.width / 2;
    centerY = canvas.height / 2;
}

// Draws dot on canvas
function drawDot(dotname, xPos, yPos, color, stringName) {
    // create object
    var dotname = canvas.getContext("2d");
    var dotSize = 3;
    dotname.beginPath();
    dotname.arc((xPos + centerX), (-yPos + centerY), dotSize, 0, 2 * Math.PI);
    dotname.stroke();
    dotname.fillStyle = '#ff00ff';
    dotname.fillStyle = "#" + color;
    dotname.fill();

}

// Draws axis on canvas
function drawAxis() {
    //declare x and y axis
    var xAxis = canvas.getContext("2d");
    var yAxis = canvas.getContext("2d");

    yAxis.beginPath();
    yAxis.moveTo(centerX, 0);
    yAxis.lineTo(centerX, canvas.height);
    yAxis.stroke();

    xAxis.beginPath();
    xAxis.moveTo(0, centerY);
    xAxis.lineTo(canvas.width, centerY);
    xAxis.stroke();
}


