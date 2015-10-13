package spring.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MapController {

        @RequestMapping(value="/map")
	public ModelAndView mapDots() throws IOException{
		return new ModelAndView("map");
	}
        
        
}
