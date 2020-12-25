package com.yiwon.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class IndexController {
	
	@RequestMapping("index")
	public String index() {
		
		return "admin.index";  //tiles name°ú ¸ÅÇÎ
	}

//	@Override
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		ModelAndView mv = new ModelAndView("admin.index");
////		mv.addObject("data", "Hellow Spring MVC~");
//		return mv;
//	}

}
