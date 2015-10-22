/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spring.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Nick Shayan
 * @version 1.0
 */
@Controller
public class PersonController {

	@RequestMapping(value = "/")
	public ModelAndView home() throws IOException {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/index")
	public ModelAndView index() throws IOException {
		return new ModelAndView("index");
	}

}