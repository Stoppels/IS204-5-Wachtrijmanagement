/* 
Document   : Home.js
Created on : Nov 5, 2015
Author     : Stefan */

window.onload = function showLogo() {
    var xOffset  = 150;
    var yOffset  = 0;
    drawRectangle(0 + xOffset, 200 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(0 + xOffset, 200 + yOffset, 190, 50, 135, '#FF0000');
    drawRectangle(0 + xOffset, 0 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(0 + xOffset, 0 + yOffset, 190, 50, 135, '#FF0000');
    drawRectangle(0 + xOffset, -200 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(0 + xOffset, -200 + yOffset, 190, 50, 135, '#FF0000');
};

function play() {
    
}