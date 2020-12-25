package com.yiwon.web.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiwon.web.entity.Notice;
import com.yiwon.web.service.NoticeService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping("index")
	//@ResponseBody  //--return 값을 문자열로 인식하도록 함 
	public String index() throws ClassNotFoundException, SQLException {
		
		List<Notice> list = noticeService.getNoticeList(); 
		return "root.index"; //tiles의 name과 매핑
	}
	
	@Autowired
	private NoticeService noticeService;
	

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

}
