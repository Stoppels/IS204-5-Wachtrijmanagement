package spring.controller;

import Controller.PersonController;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MapController {
    
//    @Autowired
//    private PersonController personController;

    @RequestMapping(value = "/map")
    public ModelAndView mapDots() throws IOException {
        ModelAndView mapView = new ModelAndView("map");
//        mapView.addObject("personObjects", personController.getList());
        return new ModelAndView("map");
        
    }
}
