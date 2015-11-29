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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    final String random = "?version=" + Math.random() * 10;
    JsonController JC;
    PersonController PC;
    StatController SC;
    String filename;
    String file = "no file";
    String starttime;
    String endtime;

    @RequestMapping(value = "/")
    public ModelAndView home() throws IOException {
        ModelAndView view = new ModelAndView("index");
        init();

        view.addObject("filename", file);

        view.addObject("Controller", "/resources/js/Controller.js" + random);
        view.addObject("Canvas", "/resources/js/Canvas/Canvas.js" + random);
        view.addObject("Home", "/resources/js/Canvas/Home.js" + random);

        view.addObject("stylesheet", "/resources/css/style.css" + random);
        view.addObject("starttime", starttime);
        view.addObject("endtime", endtime);
        return view;
    }

    @RequestMapping(value = "/snapshot")
    public ModelAndView snapshot() throws IOException {
        ModelAndView view = new ModelAndView("snapshot");

        view.addObject("filename", file);
        view.addObject("list", PC.getList());

        view.addObject("Controller", "/resources/js/Controller.js" + random);
        view.addObject("Canvas", "/resources/js/Canvas/Canvas.js" + random);
        view.addObject("Snapshot", "/resources/js/Canvas/Snapshot.js" + random);
        view.addObject("Dotmap", "/resources/js/Canvas/Dotmap.js" + random);
        view.addObject("Draw", "/resources/js/Canvas/Draw.js" + random);
        view.addObject("LinesIntersect", "/resources/js/Canvas/LinesIntersect.js" + random);

        view.addObject("stylesheet", "/resources/css/style.css" + random);
        view.addObject("starttime", starttime);
        view.addObject("endtime", endtime);
        return view;
    }

    @RequestMapping(value = "/heatmap")
    public ModelAndView heatmap() throws IOException {
        ModelAndView view = new ModelAndView("heatmap");

        view.addObject("filename", file);
        view.addObject("list", PC.getList());

        view.addObject("Controller", "/resources/js/Controller.js" + random);
        view.addObject("Canvas", "/resources/js/Canvas/Canvas.js" + random);
        view.addObject("Snapshot", "/resources/js/Canvas/Snapshot.js" + random);
        view.addObject("Heatmap", "/resources/js/Canvas/Heatmap.js" + random);
        view.addObject("Draw", "/resources/js/Canvas/Draw.js" + random);
        view.addObject("LinesIntersect", "/resources/js/Canvas/LinesIntersect.js" + random);

        view.addObject("stylesheet", "/resources/css/style.css" + random);
        view.addObject("starttime", starttime);
        view.addObject("endtime", endtime);
        return view;
    }

    @RequestMapping(value = "/graph")
    public ModelAndView graph() throws IOException {
        ModelAndView view = new ModelAndView("graph");

        view.addObject("filename", file);
        view.addObject("stats", SC.getList());

        view.addObject("Controller", "/resources/js/Controller.js" + random);
        view.addObject("Chart", "/resources/js/Chart/Chart.js" + random);
        view.addObject("ChartBar", "/resources/js/Chart/Chart.Bar.js" + random);
        view.addObject("ChartCore", "/resources/js/Chart/Chart.Core.js" + random);
        view.addObject("Graph", "/resources/js/Canvas/Graph.js" + random);

        view.addObject("stylesheet", "/resources/css/style.css" + random);
        view.addObject("starttime", starttime);
        view.addObject("endtime", endtime);
        return view;
    }

    @RequestMapping(value = "/error")
    public ModelAndView error() throws IOException {
        ModelAndView view = new ModelAndView("error");

        view.addObject("Error", "/resources/js/Canvas/Error.js" + random);
        view.addObject("Canvas", "/resources/js/Canvas/Canvas.js" + random);
        view.addObject("Controller", "/resources/js/Controller.js" + random);

        view.addObject("stylesheet", "/resources/css/style.css" + random);
        return view;
    }

    // instantiates
    private void init() {
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
}
