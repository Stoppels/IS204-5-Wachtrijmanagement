/* 
 Document   : Graph.js
 Created on : Nov 6, 2015
 Author     : Stefan */

function play(s, e) {
    personData();
}

var randomScalingFactor = function () {
    return Math.round(Math.random() * 100);
};

function personData() {
    var balken = [];
    var labels = [];
    var data = [];
    
    for (i = 0; i < stats[0].data.length; i++) {
        labels[i] = stats[0].labels[i];
        data[i] = stats[0].data[i];
    }

    for (i = 0; i < 1; i++) {
        balken[i] = {
            fillColor: "rgba(220,220,220,0.5)",
            strokeColor: "rgba(220,220,220,0.8)",
            highlightFill: "rgba(220,220,220,0.75)",
            highlightStroke: "rgba(220,220,220,1)",
            data: data
        };
    }
    addChart(new barChart(labels, balken));
}

function barChart(labels, data) {
    this.labels = labels,
            this.datasets = data;
};

addChart = function (barChartData) {
    var ctx = document.getElementById("myCanvas").getContext("2d");
    window.myBar = new Chart(ctx).Bar(barChartData, {
        responsive: true
    });
};