/* 
 Document   : Graph.js
 Created on : Nov 6, 2015
 Author     : IS204-5
 */
var canvas;
var context;

window.onload = init();

// loads statistics from first position in array
loadStats = function () {
    personData(0);
    addList();
};

// adds statistics to dropdown menu
function addList() {
    var select = document.getElementById("dropDown");
    for (i = select.options.length - 1; i >= 0; i--) {
        select.remove(i);
    }
    for (i = 0; i < stats.length; i++) {
        var option = document.createElement('option');
        option.text = stats[i].name;
        select.add(option, i);
    }
}

function clear() {
    location.reload();
}

// plays selected statistic as chart
function play() {
    personData(document.getElementById('dropDown').selectedIndex);
}

// retrieves data from statistic
function personData(index) {
    var balken = [];
    var labels = [];
    var data = [];

    for (i = 0; i < stats[index].data.length; i++) {
        labels[i] = stats[index].labels[i];
        data[i] = stats[index].data[i];
    }
    for (i = 0; i < 1; i++) {
        balken[i] = {
            fillColor: "rgba(0,153,255,0.75)",
            strokeColor: "rgba(0,153,255,1)",
            highlightFill: "rgba(0,153,255,0.50)",
            highlightStroke: "rgba(0,153,255,0.75)",
            data: data
        };
    }
    if (window.myBar)
        window.myBar.destroy();
    addChart(new barChart(labels, balken));
}

// initialises chart with retrieved data
function barChart(labels, data) {
    this.labels = labels,
            this.datasets = data;
}

// adds chart to window.myBar
addChart = function (barChartData) {
    window.myBar = new Chart(context).Bar(barChartData, {
        responsive: true
    });

};

// Initializes canvas
function init() {
    canvas = document.getElementById("myCanvas");
    context = canvas.getContext("2d");
    canvas.width = 1200;
    canvas.height = 500;
}