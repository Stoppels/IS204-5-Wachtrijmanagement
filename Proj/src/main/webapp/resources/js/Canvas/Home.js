/* 
Document   : Home.js
Created on : Nov 5, 2015
Author     : Stefan */

window.onload = function showLogo() {
    var xOffset  = 700;
    var yOffset  = -300;
    drawRectangle(0 + xOffset, 200 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(0 + xOffset, 200 + yOffset, 190, 50, 135, '#FF0000');
    drawRectangle(0 + xOffset, 0 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(0 + xOffset, 0 + yOffset, 190, 50, 135, '#FF0000');
    drawRectangle(0 + xOffset, -200 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(0 + xOffset, -200 + yOffset, 190, 50, 135, '#FF0000');
    drawText("Gemeente", -650 + xOffset, 225 + yOffset, 80);
    drawText("Amsterdam", -700 + xOffset, 125 + yOffset, 80);
};

function play() {
    
}