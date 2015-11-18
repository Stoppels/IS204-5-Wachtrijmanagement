/* 
 Document   : Graph.js
 Created on : Nov 6, 2015
 Author     : Stefan */

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
    addChart(new barChart(labels, balken));
}

function barChart(labels, data) {
    this.labels = labels,
            this.datasets = data;
}

addChart = function (barChartData) {
    var ctx = document.getElementById("myCanvas").getContext("2d");
    window.myBar = new Chart(ctx).Bar(barChartData, {
        responsive: true
    });
};