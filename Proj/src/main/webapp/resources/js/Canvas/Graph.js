/* 
 Document   : Graph.js
 Created on : Nov 6, 2015
 Author     : IS204-5
 */
var canvas;
var context;
var currentChart;

// loads statistics from first position in array
loadStats = function () {
    addList();
    loadData(0);
};

// adds statistics to dropdown menu
function addList() {
    var select = document.getElementById("dropDown");
    for (i = select.options.length; i >= 0; i--) {
        select.remove(i);
    }
    for (i = 0; i < stats.length; i++) {
        var option = document.createElement('option');
        option.text = stats[i].name;
        select.add(option, i);
    }
    disableClearButtonIf(stats.length <= 3, false);
}

function clear() {
    location.reload();
}

// plays selected statistic as chart
function play() {
    loadData(document.getElementById('dropDown').selectedIndex);
}

// retrieves data from statistic
function loadData(index) {
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
    if (currentChart)
        currentChart.destroy();
    addChart(labels, balken);
}

// initialises chart with retrieved data
function barChart(labels, data) {
    this.labels = labels, this.datasets = data;
}

// adds chart to window.myBar
addChart = function (labels, balken) {
    HORMARGIN = 510;
    VERMARGIN = 750;
    canvas = document.getElementById("myCanvas");
    context = canvas.getContext("2d");
    canvas.width = window.innerWidth - VERMARGIN;
    canvas.height = window.innerHeight - HORMARGIN;

    currentChart = new Chart(context).Bar(new barChart(labels, balken), {
        responsive: true
    });
};