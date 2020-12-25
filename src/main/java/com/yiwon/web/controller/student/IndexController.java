package com.yiwon.web.controller.student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller("studentController")
@RequestMapping("/student/")
public class IndexController {
	
	@RequestMapping("index")
	public String index() {
		
		return "student.index";
	}

}