/* 
 Document   : LinesIntersect.js
 Created on : Nov 30, 2015
 Author     : IS204-5
 */


/*
 * Takes a line and checks if persons intersect using linesIntersect
 * Person[i].t[j-1] and Person[i].t[j] make up the second line
 */
function checkIntersections(name, x3, y3, x4, y4) {
    var labels = ['Total alerts'];
    var data = [0];
    for (i = 0; i < persons.length; i++) {
        var individual_person = 0;
        for (j = 1; j < persons[i].t.length; j++) {
            x1 = ((xscale * persons[i].x[j - 1]) + centerX) + xoffset;
            y1 = ((yscale * -persons[i].y[j - 1]) + centerY) - yoffset;
            x2 = ((xscale * persons[i].x[j]) + centerX) + xoffset;
            y2 = ((yscale * -persons[i].y[j]) + centerY) - yoffset;
            crossed = linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
            if (crossed) {
                data[0]++;
                individual_person++;
            }
        }
        if (individual_person !== 0) {
            labels.push('Person ' + persons[i].id);
            data.push(individual_person);
        }
    }
    nrImportedStats = 3;
    createStats(stats.length + nrImportedStats, name, labels, data);
}

// Calculates whether two lines intersect
function linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4) {
    // Return false if either of the lines have zero length
    if (x1 === x2 && y1 === y2 ||
            x3 === x4 && y3 === y4) {
        return false;
    }
// Fastest method, based on Franklin Antonio's "Faster Line Segment Intersection" topic "in Graphics Gems III" book (http://www.graphicsgems.org/)
    var ax = x2 - x1;
    var ay = y2 - y1;
    var bx = x3 - x4;
    var by = y3 - y4;
    var cx = x1 - x3;
    var cy = y1 - y3;
    var alphaNumerator = by * cx - bx * cy;
    var commonDenominator = ay * bx - ax * by;
    if (commonDenominator > 0) {
        if (alphaNumerator < 0 || alphaNumerator > commonDenominator) {
            return false;
        }
    } else if (commonDenominator < 0) {
        if (alphaNumerator > 0 || alphaNumerator < commonDenominator) {
            return false;
        }
    }
    var betaNumerator = ax * cy - ay * cx;
    if (commonDenominator > 0) {
        if (betaNumerator < 0 || betaNumerator > commonDenominator) {
            return false;
        }
    } else if (commonDenominator < 0) {
        if (betaNumerator > 0 || betaNumerator < commonDenominator) {
            return false;
        }
    }
    if (commonDenominator === 0) {
// This code wasn't in Franklin Antonio's method. It was added by Keith Woodward.
// The lines are parallel.
// Check if they're collinear.
        var y3LessY1 = y3 - y1;
        var collinearityTestForP3 = x1 * (y2 - y3) + x2 * (y3LessY1) + x3 * (y1 - y2); // see http://mathworld.wolfram.com/Collinear.html
        // If p3 is collinear with p1 and p2 then p4 will also be collinear, since p1-p2 is parallel with p3-p4
        if (collinearityTestForP3 === 0) {
// The lines are collinear. Now check if they overlap.
            if (x1 >= x3 && x1 <= x4 || x1 <= x3 && x1 >= x4 ||
                    x2 >= x3 && x2 <= x4 || x2 <= x3 && x2 >= x4 ||
                    x3 >= x1 && x3 <= x2 || x3 <= x1 && x3 >= x2) {
                if (y1 >= y3 && y1 <= y4 || y1 <= y3 && y1 >= y4 ||
                        y2 >= y3 && y2 <= y4 || y2 <= y3 && y2 >= y4 ||
                        y3 >= y1 && y3 <= y2 || y3 <= y1 && y3 >= y2) {
                    return true;
                }
            }
        }
        return false;
    }
    return true;
}

