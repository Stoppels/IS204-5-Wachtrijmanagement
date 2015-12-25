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
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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
        loadJsonFile();
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

        view.addObject("list", PC.getList());   // TODO this will be deleted
        view.addObject("starttime", starttime); // TODO this will be deleted
        view.addObject("endtime", endtime);     // TODO this will be deleted
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
    public ModelAndView postsnapshot(@RequestParam("time1") String start, @RequestParam("time2") String end) throws IOException {
        ModelAndView view = new ModelAndView("dotmap");
        addDotmapResources(view);

        view.addObject("list", PC.getList());   // TODO add query result here (needs to be list of PersonObjects)
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

        view.addObject("list", PC.getList());   // TODO this will be deleted
        view.addObject("starttime", starttime); // TODO this will be deleted
        view.addObject("endtime", endtime);     // TODO this will be deleted
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
    public ModelAndView postheatmap(@RequestParam("time1") String start, @RequestParam("time2") String end) throws IOException {
        ModelAndView view = new ModelAndView("heatmap");
        addHeatmapResources(view);

        view.addObject("list", PC.getList());   // TODO add query result here (needs to be list of PersonObjects)
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

        view.addObject("stats", SC.getList());  // TODO this will be deleted
        view.addObject("starttime", starttime); // TODO this will be deleted
        view.addObject("endtime", endtime);     // TODO this will be deleted
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
    public ModelAndView postgraph(@RequestParam("time1") String start, @RequestParam("time2") String end) throws IOException {
        ModelAndView view = new ModelAndView("graph");
        addGraphResources(view);

        view.addObject("list", PC.getList());   // TODO add query result here (needs to be list of PersonObjects)
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

    /*
     * Deze methode moet alle PersonObjects tussen twee timestamps ophalen
     * We kunnen van String start en String end ook timestamps gebruiken,
     * dan moeten ze worden omgezet.
     */
    private ArrayList<PersonObject> doQuery(String start, String end) {
        // start en end in de App zien er uit als: 11:03:44
        // start en end in de DB zien er uit als: 20151126T080935992000
        // ik denk dat we van de DB versie enkel dit nodig hebben 080935
        
        //DB start en end opsplitsen in datum en tijd?
        //explode op T?
        //omdat er anders geen vergelijking gemaakt kan worden??
        //not sure of t zo moet tho

        // weer toevoegen indien nodig, hier bestaat geen functie voor:
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < split.length; i++) {
//            sb.append(split[i]);
//            if (i != split.length - 1) {
//                sb.append(" ");
//            }
//        }
//        String joined = sb.toString();
        
        String[] startSplit = start.split("T"); // nu heb je een array van de delen voor en na de 'T'
        String[] endSplit = end.split("T"); // nu heb je een array van de delen voor en na de 'T'


        return new ArrayList(); // dit moeten de PersonObjects uit de database zijn
    }
}
