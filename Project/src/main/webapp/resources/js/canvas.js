/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// init
var canvas = document.getElementById("myCanvas");
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;
var object = canvas.getContext("2d");

// Function create new Object

// Function controller Object
var xPos = 100;
var yPos = 100;
// Function model Object
var size = 20;
object.beginPath();
object.arc(xPos, yPos, size, 0, 2 * Math.PI);
object.stroke();
object.fillStyle = '#FF0000';
object.fill();


// Create Object
function createPersonObject(x, y, name) {

    var personObject = {
        firstName: "John",
        lastName: "Doe",
        age: 50,
        eyeColor: "blue",
        xPos: x,
        yPos: y
    };
    
    personObject = canvas.getContext("2d");
}

