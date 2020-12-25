package com.yiwon.web.controller.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yiwon.web.entity.Notice;
import com.yiwon.web.service.NoticeService;
import com.yiwon.web.service.jdbc.JDBCNoticeService;

public class ListController implements Controller{
	
	private NoticeService noticeService;
	
	

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//DI객체를 활용하자
		
		ModelAndView mv = new ModelAndView("notice.list");
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title";
		if(field_ != null && !field_.equals("")) field = field_;
		
		String query = "";
		if(query_ != null && !query_.equals("")) query = query_;
		
		int page = 1;
		if(page_ != null && !page_.equals("")) page = Integer.parseInt(page_);
		
		List<Notice> list = noticeService.getNoticeList(field, query, page, "1");
		
		//전체 건수 가져오기
		int count = noticeService.getNoticeCount(field, query, "1");
		
		mv.addObject("list", list);
		mv.addObject("count", count);
		
		
		/*
		 * List<Notice> list = noticeService.getNoticeList("title", "", 1, "0");
		 * 
		 * mv.addObject("list", list);
		 */
				
		return mv;
	}

}
