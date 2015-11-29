/* 
 * The MIT License
 *
 * Copyright 2015 IS204-5.
 * Application developed for Amsterdam University of Applied Sciences and
 * Gemeente Amsterdam.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
var totalIntersections;     // times crossed line
var totalReintersections;   // times crossed the same line

function checkLineIntersect() {
    if (lines.length >= 2) {
        var line = lines[lines.length - 1];
        var subj = lines[lines.length - 2];
        alert("" + linesIntersect(line.x1, line.y1, line.x2, line.y2,
                subj.x1, subj.y1, subj.x2, subj.y2));
    }
}

/*
 * Takes a line and checks if persons intersect using linesIntersect
 * Person[i].t[j-1] and Person[i].t[j] make up the second line
 */
function checkIntersections(x3, y3, x4, y4) {
    for (i = 0; i < persons.length; i++) {
        for (j = 1; j < persons[i].t.length; j++) {
            drawLine((-90 * persons[i].x[j - 1]) + centerX,
                    (90 * -persons[i].y[j - 1]) + centerY,
                    (-90 * persons[i].x[j]) + centerX,
                    (90 * -persons[i].y[j]) + centerY
                    , 3, '#606060');
            console.log(
                    linesIntersect(
                            persons[i].x[j - 1] + centerX,
                            -persons[i].y[j - 1] + centerY,
                            persons[i].x[j] + centerX,
                            -persons[i].y[j] + centerY,
                            x3, y3, x4, y4));
        }
    }
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

