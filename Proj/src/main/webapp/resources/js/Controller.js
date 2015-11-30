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
var stats;
var lines;
var colors = [];
getSessions();


/*
 * Gets all the line information and data if it exists
 * Otherwise this method will assign a new array
 * @returns void
 */
function getSessions() {
    var lineSsn = JSON.parse(localStorage.getItem('linesSession'));
    var statSsn = JSON.parse(localStorage.getItem('statsSession'));
    if (lineSsn !== null) {
        lines = lineSsn;
    } else {
        lines = new Array();
    }
    if (statSsn !== null) {
        stats = statSsn;
    } else {
        stats = new Array();
    }
}

/*
 * Clears the localStorage so that all data can be refilled. This means that
 * lines and stats are all gone. The canvas is empty and charts has basic
 * imported data from Java. The dropdown menu will also be reset
 * @returns void
 */
function clearSessions() {
    if (lines.length >= 1) {
        var r = confirm('Do you wish to delete all lines?');
        console.log('confirm' + r);
        if (r === true) {
            localStorage.setItem('linesSession', null);
            localStorage.setItem('statsSession', null);
            lines = new Array();
            stats = new Array();
            addList();
            clear();
            drawBackground();
        }
    }
}

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
    lines.push({'x1': x1, 'y1': y1, 'x2': x2, 'y2': y2});
    localStorage.setItem('linesSession', JSON.stringify(lines));
}

/*
 * draws all lines in lines array
 * @returns void
 */
function drawLines() {
    for (i = 0; i < lines.length; i++) {
        drawLine(lines[i].x1, lines[i].y1, lines[i].x2, lines[i].y2);
    }
}

/* Function to create a stat object in Javascript used by Snapshot and Graph
 * @name String represents the name of the the statistic and information
 * @labels array holding Strings of corresponding names for each bar
 * @data array holding ints of the data associated with the label
 */
function createStats(id, name, labels, data) {
    console.log(id);
    stats.push({
        'id': id,
        'name': name,
        'labels': labels,
        'data': data
    });
    localStorage.setItem('statsSession', JSON.stringify(stats));
}

/*
 * Only imports stats from Java if the stats list is not containing them already
 * @returns void
 */
function importStats(id, name, labels, data) {
    if (!statsContainsId(stats, [0, 1, 2])) {
        createStats(id, name, labels, data);
    }
}

/*
 * Checks if the stats array contains values id's to prevent the import of
 * duplicate statistics from Java. 
 */
function statsContainsId(array, values) {
    index = 0;
    for (i = 0; i < array.length; i++) {
        for (j = 0; j < values.length; j++) {
            if (array[i].id === values[j]) {
                index++;
            }
        }
    }
    return (index === values.length);
}

/*
 * Takes time in format 00:00:00
 */
function timeToMillis(time) {
    return time.substring(0, 2) * 60 * 60 + time.substring(3, 5) * 60 + time.substring(6, 8) * 1;
}