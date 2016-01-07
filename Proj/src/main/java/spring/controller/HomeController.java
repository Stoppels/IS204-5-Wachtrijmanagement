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
package spring.controller;

/**
 *
 * @author IS204-5
 * @version 1.0
 */
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.model.JsonObject;
import spring.model.PersonObject;

/**
 * Controls the POST and GET requests to serve views
 *
 * @author Schenk
 */
@Controller
public class HomeController {

    // random generates a String that appends to resources to prevent caching
    final String random = "?version=" + Math.random() * 10;
    JsonController JC;
    PersonController PC;
    StatController SC;
    String filename;
    String file = "no file";
    String starttime;
    String endtime;

    DBController dbCon = new DBController();
    ResultSet rs;

    /**
     * Homepage
     *
     * @return ModelAndView view
     * @throws IOException
     */
    @RequestMapping(value = "/")
    public ModelAndView home() throws IOException {
        ModelAndView view = new ModelAndView("index");
        addHomeResources(view);
        return view;
    }

    /**
     * Loadjson to load a json file
     *
     * @return ModelAndView view
     * @throws IOException
     */
    @RequestMapping(value = "/loadjson")
    public ModelAndView loadjson() throws IOException {
        ModelAndView view = new ModelAndView("index");
        addHomeResources(view);
        return view;
    }

    /**
     *
     * @return ModelAndView view by GET request
     * @throws IOException
     */
    @RequestMapping(value = "/dotmap", method = RequestMethod.GET)
    public ModelAndView getsnapshot() throws IOException {
        ModelAndView view = new ModelAndView("dotmap");
        addDotmapResources(view);

        view.addObject("list", new ArrayList());
        view.addObject("starttime", "00:00:00");
        view.addObject("endtime", "00:00:00");
        return view;
    }

    /**
     *
     * @param start
     * @param end
     * @return ModelAndView view by POST request
     * @throws IOException
     */
    @RequestMapping(value = "/dotmap", method = RequestMethod.POST)
    public ModelAndView postsnapshot(@RequestParam("time1") String start, @RequestParam("time2") String end) throws IOException, Exception {
        ModelAndView view = new ModelAndView("dotmap");
        addDotmapResources(view);

        view.addObject("list", doPersonObjectQueryTime(start, end));   // TODO add query result here (needs to be list of PersonObjects)
        view.addObject("starttime", start);     // TODO takes user starttime input
        view.addObject("endtime", end);         // TODO takes user endtime input
        return view;
    }

    /**
     *
     * @return ModelAndView view
     * @throws IOException
     */
    @RequestMapping(value = "/heatmap", method = RequestMethod.GET)
    public ModelAndView getheatmap() throws IOException {
        ModelAndView view = new ModelAndView("heatmap");
        addHeatmapResources(view);

        view.addObject("list", new ArrayList());
        view.addObject("starttime", "00:00:00");
        view.addObject("endtime", "00:00:00");
        return view;
    }

    /**
     *
     * @param start
     * @param end
     * @return ModelAndView view by POST request
     * @throws IOException
     */
    @RequestMapping(value = "/heatmap", method = RequestMethod.POST)
    public ModelAndView postheatmap(@RequestParam("time1") String start, @RequestParam("time2") String end) throws IOException, Exception {
        ModelAndView view = new ModelAndView("heatmap");
        addHeatmapResources(view);

        view.addObject("list", doPersonObjectQueryTime(start, end));   // TODO add query result here (needs to be list of PersonObjects)
        view.addObject("starttime", start);     // TODO takes user starttime input
        view.addObject("endtime", end);         // TODO takes user endtime input
        return view;
    }

    /**
     *
     * @return ModelAndView view
     * @throws IOException
     */
    @RequestMapping(value = "/graph", method = RequestMethod.GET)
    public ModelAndView getgraph() throws IOException {
        ModelAndView view = new ModelAndView("graph");
        addGraphResources(view);

        view.addObject("list", new ArrayList());
        view.addObject("starttime", "00:00:00");
        view.addObject("endtime", "00:00:00");
        return view;
    }

    /**
     *
     * @param start
     * @param end
     * @return ModelAndView view by POST request
     * @throws IOException
     */
    @RequestMapping(value = "/graph", method = RequestMethod.POST)
    public ModelAndView postgraph(@RequestParam("time1") String start, @RequestParam("time2") String end) throws IOException, Exception {
        ModelAndView view = new ModelAndView("graph");
        addGraphResources(view);

        view.addObject("list", doPersonObjectQueryTime(start, end));   // TODO add query result here (needs to be list of PersonObjects)
        view.addObject("starttime", start);     // TODO takes user starttime input
        view.addObject("endtime", end);         // TODO takes user endtime input
        return view;
    }

    /**
     *
     * @return ModelAndView view
     * @throws IOException
     */
    @RequestMapping(value = "/error")
    public ModelAndView error() throws IOException {
        ModelAndView view = new ModelAndView("error");
        view.addObject("Error", "/resources/js/Canvas/Error.js" + random);
        view.addObject("Canvas", "/resources/js/Canvas/Canvas.js" + random);
        view.addObject("Controller", "/resources/js/Controller.js" + random);
        view.addObject("stylesheet", "/resources/css/style.css" + random);
        return view;
    }

    // Adds Dotmap Resources to the view
    private void addDotmapResources(ModelAndView view) {
        view.addObject("Controller", "/resources/js/Controller.js" + random);
        view.addObject("Canvas", "/resources/js/Canvas/Canvas.js" + random);
        view.addObject("Interface", "/resources/js/Canvas/Interface.js" + random);
        view.addObject("Player", "/resources/js/Canvas/Player.js" + random);
        view.addObject("Dotmap", "/resources/js/Canvas/Dotmap.js" + random);
        view.addObject("Draw", "/resources/js/Canvas/Draw.js" + random);
        view.addObject("LinesIntersect", "/resources/js/Canvas/LinesIntersect.js" + random);
        view.addObject("stylesheet", "/resources/css/style.css" + random);
    }

    // Adds Home Resources to the view
    private void addHomeResources(ModelAndView view) {
        view.addObject("Controller", "/resources/js/Controller.js" + random);
        view.addObject("Canvas", "/resources/js/Canvas/Canvas.js" + random);
        view.addObject("Home", "/resources/js/Canvas/Home.js" + random);
        view.addObject("stylesheet", "/resources/css/style.css" + random);
    }

    // Adds Heatmap Resources to the view
    private void addHeatmapResources(ModelAndView view) {
        view.addObject("Controller", "/resources/js/Controller.js" + random);
        view.addObject("Canvas", "/resources/js/Canvas/Canvas.js" + random);
        view.addObject("Interface", "/resources/js/Canvas/Interface.js" + random);
        view.addObject("Player", "/resources/js/Canvas/Player.js" + random);
        view.addObject("Heatmap", "/resources/js/Canvas/Heatmap.js" + random);
        view.addObject("Draw", "/resources/js/Canvas/Draw.js" + random);
        view.addObject("LinesIntersect", "/resources/js/Canvas/LinesIntersect.js" + random);
        view.addObject("stylesheet", "/resources/css/style.css" + random);
    }

    // Adds Graph Resources to the view
    private void addGraphResources(ModelAndView view) {
        view.addObject("Controller", "/resources/js/Controller.js" + random);
        view.addObject("Chart", "/resources/js/Chart/Chart.js" + random);
        view.addObject("ChartBar", "/resources/js/Chart/Chart.Bar.js" + random);
        view.addObject("ChartCore", "/resources/js/Chart/Chart.Core.js" + random);
        view.addObject("Graph", "/resources/js/Canvas/Graph.js" + random);
        view.addObject("Interface", "/resources/js/Canvas/Interface.js" + random);
        view.addObject("stylesheet", "/resources/css/style.css" + random);
    }

    // instantiates
    private void loadJsonFile() {
        Main temporaryProblemSolverSeeTrello = new Main();
        this.filename = temporaryProblemSolverSeeTrello.getPath();
        this.file = temporaryProblemSolverSeeTrello.file;

        JC = new JsonController(temporaryProblemSolverSeeTrello.getPath());
        PC = new PersonController();
        SC = new StatController();

        PC.convertJsonToPerson(JC.getList());
        SC.extractStatistics(PC.getList());

        starttime = PC.getStartTime().hourMinuteSecond();
        endtime = PC.getEndTime().hourMinuteSecond();
    }

    /**
     * This metod executes a query to get all the data within the specified
     * timeframe
     *
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public ArrayList<PersonObject> doPersonObjectQueryTime(String start, String end) throws Exception {
        int startTimeRequest = Integer.valueOf(start.replace(":", "")); //Requested start time from app formatted to an int of 6 chars
        int endTimeRequest = Integer.valueOf(end.replace(":", "")); //Requested end time from app formatted to an int of 6 chars

        ArrayList<PersonObject> po = new ArrayList();
        String query = "SELECT * FROM `personObject` "
                + "WHERE (CAST(SUBSTR(`start`, 10,6) AS SIGNED) <= " + endTimeRequest
                + ") AND (CAST(SUBSTR(`eind`, 10,6) AS SIGNED) >= " + startTimeRequest
                + ");";

        dbCon.openConnection();
        try {
            rs = dbCon.doQuery(query);
            rs.beforeFirst();
            while (rs.next()) {
                ByteArrayInputStream in = new ByteArrayInputStream(rs.getBytes("file"));
                ObjectInputStream inp = new ObjectInputStream(in);
                po.add((PersonObject) inp.readObject());
            }

        } catch (SQLException | NullPointerException e) {
            System.err.println(e.getLocalizedMessage());
        } finally {
            dbCon.closeConnection();
            rs = null;
        }
        PC = new PersonController(po);
        return po; // personObjects from database
    }

    /**
     * This method can be used in the future when there will also be a date
     * field. This metod executes a query to get all the data within the
     * specified timeframe on the specified date
     *
     * @param start
     * @param end
     * @param date
     * @return
     * @throws Exception
     */
    private ArrayList<PersonObject> doPersonObjectQueryDateTime(String start, String end, String date) throws Exception {
        int startTimeRequest = Integer.valueOf(start.replace(":", "")); //Requested start time from app formatted to an int of 6 chars
        int endTimeRequest = Integer.valueOf(end.replace(":", "")); //Requested end time from app formatted to an int of 6 chars
        int dateRequest = Integer.valueOf(date.replace("-", "")); // graag als xx-xx-xxxx invoeren in de App

        ArrayList<PersonObject> po = new ArrayList();
        String query = "SELECT * FROM `personObject` "
                + "WHERE (CAST(SUBSTR(`start`, 10,6) AS SIGNED) >= " + startTimeRequest
                + " AND CAST(SUBSTR(`start`, 10,6) AS SIGNED) <= " + endTimeRequest
                + ") AND (CAST(SUBSTR(`eind`, 10,6) AS SIGNED) >= " + startTimeRequest
                + " AND (CAST(SUBSTR(`eind`, 10,6) AS SIGNED) <= " + endTimeRequest
                + "AND CAST(SUBSTR(`start`, 0,8) AS SIGNED) == " + dateRequest + "));";

        dbCon.openConnection();
        try {

            rs = dbCon.doQuery(query);
            rs.beforeFirst();
            while (rs.next()) {
                ByteArrayInputStream in = new ByteArrayInputStream(rs.getBytes("file"));
                ObjectInputStream inp = new ObjectInputStream(in);
                po.add((PersonObject) inp.readObject());
            }

        } catch (SQLException | NullPointerException e) {
            System.err.println(e.getLocalizedMessage());
        } finally {
            dbCon.closeConnection();
            rs = null;
        }

        return po;
    }
}
