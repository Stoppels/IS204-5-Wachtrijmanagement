/* 
 Document   : Home.js
 Created on : Nov 5, 2015
 Author     : IS204-5
 */

// shows error page content
window.onload = function showLogo() {
    var xOffset = 320;
    var yOffset = 0;
    drawRectangle(100 + xOffset, 0 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(100 + xOffset, 0 + yOffset, 190, 50, 135, '#FF0000');
    drawRectangle(-100 + xOffset, 0 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(-100 + xOffset, 0 + yOffset, 190, 50, 135, '#FF0000');
    drawRectangle(-300 + xOffset, 0 + yOffset, 190, 50, 45, '#FF0000');
    drawRectangle(-300 + xOffset, 0 + yOffset, 190, 50, 135, '#FF0000');
    drawText("404 Error", 0, 120, 80);
    drawText("This is not the page you are looking for.", -490, 280, 40);
};