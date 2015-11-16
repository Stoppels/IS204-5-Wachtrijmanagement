package spring.controller;

/**
 *
 * @author Stefan
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
    String filename = "no file";
    String starttime;
    String endtime;

    @RequestMapping(value = "/")
    public ModelAndView home() throws IOException {
        ModelAndView view = new ModelAndView("index");
        init();

        view.addObject("filename", filename);

        view.addObject("Controller", "/resources/js/Controller.js" + random);
        view.addObject("Canvas", "/resources/js/Canvas/Canvas.js" + random);
        view.addObject("Home", "/resources/js/Canvas/Home.js" + random);

        view.addObject("stylesheet", "/resources/css/simple.css" + random);
        view.addObject("starttime", starttime);
        view.addObject("endtime", endtime);
        return view;
    }

    @RequestMapping(value = "/snapshot")
    public ModelAndView snapshotGet() throws IOException {
        ModelAndView view = new ModelAndView("snapshot");

        view.addObject("filename", filename);
        view.addObject("list", PC.getList());

        view.addObject("Controller", "/resources/js/Controller.js" + random);
        view.addObject("Canvas", "/resources/js/Canvas/Canvas.js" + random);
        view.addObject("Snapshot", "/resources/js/Canvas/Snapshot.js" + random);

        view.addObject("stylesheet", "/resources/css/simple.css" + random);
        view.addObject("starttime", starttime);
        view.addObject("endtime", endtime);
        return view;
    }

    @RequestMapping(value = "/heatmap")
    public ModelAndView heatmap() throws IOException {
        ModelAndView view = new ModelAndView("heatmap");

        view.addObject("filename", filename);
        view.addObject("list", PC.getList());

        view.addObject("Controller", "/resources/js/Controller.js" + random);
        view.addObject("Canvas", "/resources/js/Canvas/Canvas.js" + random);
        view.addObject("Heatmap", "/resources/js/Canvas/Heatmap.js" + random);

        view.addObject("stylesheet", "/resources/css/simple.css" + random);
        view.addObject("starttime", starttime);
        view.addObject("endtime", endtime);
        return view;
    }

    @RequestMapping(value = "/graph")
    public ModelAndView graph() throws IOException {
        ModelAndView view = new ModelAndView("graph");

        view.addObject("filename", filename);
        view.addObject("stats", SC.getList());

        view.addObject("Controller", "/resources/js/Controller.js" + random);
        view.addObject("Chart", "/resources/js/Chart/Chart.js" + random);
        view.addObject("ChartBar", "/resources/js/Chart/Chart.Bar.js" + random);
        view.addObject("ChartCore", "/resources/js/Chart/Chart.Core.js" + random);
        view.addObject("Graph", "/resources/js/Canvas/Graph.js" + random);

        view.addObject("stylesheet", "/resources/css/simple.css" + random);
        view.addObject("starttime", starttime);
        view.addObject("endtime", endtime);
        return view;
    }

    // instantiates
    private void init() {
        
        Main temporaryProblemSolverSeeTrello = new Main();
        this.filename = temporaryProblemSolverSeeTrello.getPath();
        
        JC = new JsonController(temporaryProblemSolverSeeTrello.getPath());
        PC = new PersonController();
        SC = new StatController();

        PC.convertJsonToPerson(JC.getList());
        SC.extractStatistics(PC.getList());

        starttime = PC.getStartTime().hourMinuteSecond();
        endtime = PC.getEndTime().hourMinuteSecond();
    }
}
