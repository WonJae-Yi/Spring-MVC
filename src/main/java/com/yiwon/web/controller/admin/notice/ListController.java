package com.yiwon.web.controller.admin.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yiwon.web.entity.Notice;
import com.yiwon.web.service.NoticeService;

public class ListController implements Controller{
	
	@Autowired
	private NoticeService noticeService;
	

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("admin.notice.list");

		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title";
		if(field_ != null && !field_.equals("")) field = field_;
		
		String query = "";
		if(query_ != null && !query_.equals("")) query = query_;
		
		int page = 1;
		if(page_ != null && !page_.equals("")) page = Integer.parseInt(page_);
		
		List<Notice> list = noticeService.getNoticeList(field, query, page, "%");
		
		//전체 건수 가져오기
		int count = noticeService.getNoticeCount(field, query, "%");

		
		
		mv.addObject("list", list);
		mv.addObject("count", count);
		return mv;
	}

}
