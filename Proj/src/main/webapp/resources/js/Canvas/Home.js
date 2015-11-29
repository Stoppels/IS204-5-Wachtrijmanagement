/* 
 Document   : Home.js
 Created on : Nov 5, 2015
 Author     : Stefan */

var img = new Image();
img.src = 'resources/img/counterview.png';

// shows home page content
window.onload = function showLogo() {
    var xOffset = 840;
    var yOffset = -300;
    showImage(img, 300, -350, img.width/2, img.height/2);
    drawRectangle(0 + xOffset, 200 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(0 + xOffset, 200 + yOffset, 190, 50, 135, '#FF0000');
    drawRectangle(0 + xOffset, 0 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(0 + xOffset, 0 + yOffset, 190, 50, 135, '#FF0000');
    drawRectangle(0 + xOffset, -200 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(0 + xOffset, -200 + yOffset, 190, 50, 135, '#FF0000');
    drawText("Gemeente", -655 + xOffset, 100 + yOffset, 80);
    drawText("Amsterdam", -700 + xOffset, 0 + yOffset, 80);
    drawText("Queue", -895 + xOffset, 220 + yOffset, 60);
    drawText("Management", -625 + xOffset, 220 + yOffset, 60);
};