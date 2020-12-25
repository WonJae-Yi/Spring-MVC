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
	//@ResponseBody  //--return ���� ���ڿ��� �ν��ϵ��� �� 
	public String index() throws ClassNotFoundException, SQLException {
		
		List<Notice> list = noticeService.getNoticeList(); 
		return "root.index"; //tiles�� name�� ����
	}
	
	@Autowired
	private NoticeService noticeService;
	

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

}
