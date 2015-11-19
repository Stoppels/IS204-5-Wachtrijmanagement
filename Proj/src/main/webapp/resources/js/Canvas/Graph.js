/* 
 Document   : Graph.js
 Created on : Nov 6, 2015
 Author     : Stefan */

var canvas;
var context;

window.onload = init();

loadStats = function () {
    personData(0);
    addList();
};

addList = function () {
    var select = document.getElementById("dropDown");
    for (i = 0; i < stats.length; i++) {
        var option = document.createElement('option');
        option.text = stats[i].name;
        select.add(option, i);
    }
};

function play() {
    personData(document.getElementById('dropDown').selectedIndex);
}

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
    document.getElementById("visitors").innerHTML = data.length;

    if (window.myBar)
        window.myBar.destroy();
    addChart(new barChart(labels, balken));

    // Hide time fields for graphs other than 'Time per visitor'.
    if (index !== 0) {
        document.getElementById("timeElements").style.display = 'none';
    } else {
        document.getElementById("timeElements").style.display = 'block';
    }
}

function barChart(labels, data) {
    this.labels = labels,
            this.datasets = data;
}

addChart = function (barChartData) {
    window.myBar = new Chart(context).Bar(barChartData, {
        responsive: true
    });

};
// Initializes canvas
function init() {
    canvas = document.getElementById("myCanvas");
    context = canvas.getContext("2d");
    canvas.width = 800;
    canvas.height = 300;
}