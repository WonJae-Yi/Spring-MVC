package com.yiwon.web.controller.admin.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController {
	
	@RequestMapping("list")
	public String list() {
		
		return "admin.board.notice.list";
	}
	
	@RequestMapping("reg")
	//@ResponseBody
	public String reg() {
		
		return "admin.board.notice.reg";

	}
	
	@RequestMapping("detail")
	public String detail() {
		
		return "admin.board.notice.detail";
	}

	
}
