/* 
 Document   : Controller.js
 Created on : Nov 4, 2015
 Author     : Stefan */

/*
 * Arrays of all the differen objects that are used troughout views
 * @persons holds data of person objects sent from Java objects
 * @lines holds data of user drawn lines on the Snapshot map
 * @stats holds statistical information of person objects
 */
var persons = new Array();
var lines = new Array();
var stats = new Array();
var colors = [];

/*
 * Function to create a person object in Javascript used by Snapshot and Heatmap
 * @id int holds the track id of a person object converted from Java objects
 * @t int holds timestamp data in the following format 00:00:00, HH:MM:SS
 * @x int holds x[index] pos data aligned with timestamp of t[index]
 * @y int holds y[index] pos aligned with timestamp of t[index]
 * @counter int holds the current iterator position of t,x,y
 */
function createPerson(id, t, x, y) {
    var index = persons.length;
    persons[index] = new person();
    persons[index].id = id;
    persons[index].t = t;
    persons[index].x = x;
    persons[index].y = y;
    persons[index].counter = 0;
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

/* Function to create a line object in Javascript used by Snapshot and Graph
 * @x1 and @y1 int holding the starting position of the user drawn line
 * @x2 and @y2 int holding the end position of the user drawn line
 */
function createLine(x1, y1, x2, y2) {
    var index = lines.length;
    lines[index] = new line();
    lines[index].x1 = x1;
    lines[index].y1 = y1;
    lines[index].x2 = x2;
    lines[index].y2 = y2;
}

// constructor of line
function line() {
    this.x1; 
    this.x2; 
    this.y1;
    this.y2;
    
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

/* Function to create a stat object in Javascript used by Snapshot and Graph
 * @name String represents the name of the the statistic and information
 * @labels array holding Strings of corresponding names for each bar
 * @data array holding ints of the data associated with the label
 */
function createStats(id, name, labels, data) {
    var index = stats.length;
    stats[index] = new statistic();
    stats[index].id = id;
    stats[index].name = name;
    stats[index].labels = labels;
    stats[index].data = data;
}

// constructor of stat
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