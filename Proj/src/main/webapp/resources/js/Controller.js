/* 
 Document   : Controller.js
 Created on : Nov 4, 2015
 Author     : Stefan */

// list contains persons
var list = new Array();
var lines = new Array();
var stats = new Array();
var colors = [];

// function to create person
function createPerson(id, t, x, y) {
    var index = list.length;
    list[index] = new person();
    list[index].id = id;
    list[index].t = t;
    list[index].x = x;
    list[index].y = y;
    list[index].counter = 0;
}

// constructor of person
function person() {
    var scale = 90;
    this.id;
    this.t;
    this.x;
    this.y;
    this.color = '#' + (Math.random() * 0xFFFFFF << 0).toString(16);
    this.counter;

    this.count = function () {
        this.counter++;
    };
    this.resetCount = function () {
        this.counter = 0;
    };
    this.dot = function (i) {
        drawDot(-scale * this.x[i], scale * this.y[i], this.color);
    };
    this.heat = function (i) {
        drawHeat(-scale * this.x[i], scale * this.y[i], '#FF0000');
    };
    this.text = function (i) {
        drawInfo(this.id, -scale * this.x[i], scale * this.y[i], 15);
    };
    this.totalTime = function () {
        return timeToMillis(t[t.length - 1]) - timeToMillis(t[0]);
    };
}

function createLine(x1, y1, x2, y2) {
    var index = lines.length;
    lines[index] = new line();
    lines[index].x1 = x1;
    lines[index].y1 = y1;
    lines[index].x2 = x2;
    lines[index].y2 = y2;
}

function line() {
    var x1, x2, y1, y2;
    
    this.draw = function() {
        drawLine(this);
    };
}

// draws all lines in lines array
function drawLines() {
    for (i = 0; i < lines.length; i++) {
        drawLine(lines[i]);
    }
}

// function to create stats (Bar, data for every person)
function createStats(id, name, labels, data) {
    var index = stats.length;
    stats[index] = new statistic();
    stats[index].id = id;
    stats[index].name = name;
    stats[index].labels = labels;
    stats[index].data = data;
}

// constructor of stats
function statistic() {
    this.id;
    this.name;
    this.labels;
    this.data;
}

// takes time in format 00:00:00
function timeToMillis(time) {
    return time.substring(0, 2) * 60 * 60 + time.substring(3, 5) * 60 + time.substring(6, 8) * 1;
}