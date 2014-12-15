package com.paperuni.demo.web.custom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/admindashboard/**")
@Controller
public class AdminDashboardController {

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(){
		return "admindashboard/index";
	}	
}
