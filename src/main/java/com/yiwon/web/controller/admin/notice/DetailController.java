package com.yiwon.web.controller.admin.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yiwon.web.entity.Notice;
import com.yiwon.web.service.NoticeService;

public class DetailController implements Controller {
	
	@Autowired
	private NoticeService noticeService;
		

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("admin.notice.detail");
		
		int id = Integer.parseInt(request.getParameter("id"));

		Notice notice = noticeService.getNotice(id);

		mv.addObject("n", notice);
		
		//이전글
		Notice preNotice = noticeService.getPrevNotice("title", "",  "%", id);
		mv.addObject("prevn", preNotice);

		
		//다음글 
		Notice nextNotice = noticeService.getNextNotice("title", "", "%", id);
		mv.addObject("nextn", nextNotice);

		return mv;
	}

}
