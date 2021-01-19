package com.yiwon.web.controller.customer;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yiwon.web.entity.Notice;
import com.yiwon.web.service.NoticeService;

@Controller
@RequestMapping("/customer/notice/")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("list")
	public String list(@RequestParam(name="p", defaultValue = "1") int page, Model model) throws ClassNotFoundException, SQLException {
		
		System.out.println("page=" + page);
		String field = "title";
		String query = "";

		
		List<Notice> list = noticeService.getNoticeList(field, query, page, "1");
		model.addAttribute("list",list);
		
		
		/*
		 * ModelAndView mv = new ModelAndView("notice.list");
		 * 
		 * String field_ = request.getParameter("f"); String query_ =
		 * request.getParameter("q"); String page_ = request.getParameter("p");
		 * 
		 * String field = "title"; if(field_ != null && !field_.equals("")) field =
		 * field_;
		 * 
		 * String query = ""; if(query_ != null && !query_.equals("")) query = query_;
		 * 
		 * int page = 1; if(page_ != null && !page_.equals("")) page =
		 * Integer.parseInt(page_);
		 * 
		 * List<Notice> list = noticeService.getNoticeList(field, query, page, "1");
		 * 
		 * //전체 건수 가져오기 int count = noticeService.getNoticeCount(field, query, "1");
		 * 
		 * mv.addObject("list", list); mv.addObject("count", count);
		 */
		
		
		return "notice.list";
	}
	
	@RequestMapping("detail")
	public String detail() {
		
		/*
		 * ModelAndView mv = new ModelAndView("notice.detail");
		 * 
		 * int id = Integer.parseInt(request.getParameter("id"));
		 * 
		 * Notice notice = noticeService.getNotice(id); mv.addObject("n", notice);
		 * 
		 * // 이전글 Notice preNotice = noticeService.getPrevNotice("title", "", "1", id);
		 * mv.addObject("prevn", preNotice);
		 * 
		 * // 다음글 Notice nextNotice = noticeService.getNextNotice("title", "", "1", id);
		 * mv.addObject("nextn", nextNotice);
		 */	
		
		return "notice.detail";
	}

}
