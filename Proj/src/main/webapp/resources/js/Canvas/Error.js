/* 
 Document   : Home.js
 Created on : Nov 5, 2015
 Author     : Stefan */

window.onload = function showLogo() {
    var xOffset = 700;
    var yOffset = -300;
    drawRectangle(100 + xOffset, 0 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(100 + xOffset, 0 + yOffset, 190, 50, 135, '#FF0000');
    drawRectangle(-100 + xOffset, 0 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(-100 + xOffset, 0 + yOffset, 190, 50, 135, '#FF0000');
    drawRectangle(-300 + xOffset, 0 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(-300 + xOffset, 0 + yOffset, 190, 50, 135, '#FF0000');
    drawText("404 Error", -650 + xOffset, 225 + yOffset, 80);
    drawText("This is not the page you are looking for", -700 + xOffset, 125 + yOffset, 40);
};

function play() {

}