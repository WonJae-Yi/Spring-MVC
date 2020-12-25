package com.yiwon.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yiwon.web.entity.Notice;
import com.yiwon.web.service.NoticeService;

public class IndexController implements Controller{
	
	private NoticeService noticeService;
	

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mv = new ModelAndView("root.index");
		
		List<Notice> list = noticeService.getNoticeList();		 
		mv.addObject("list", list);
						
		return mv;
	}

}
