package spring.controller;

import java.io.IOException;

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
        
        // voegt de javascript file toe, met versienummer om caching tegen te gaan
        mapView.addObject("javascript", "/resources/js/canvas.js" + ("?version=" + Math.random() * 10));
        
        // voegt de personObjects uit een jsonfile toe
//        mapView.addObject("personObjects", personController.getList());
        
        
        return mapView;
        
    }
}
