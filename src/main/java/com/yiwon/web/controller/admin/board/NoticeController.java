package com.yiwon.web.controller.admin.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController {
	
	@Autowired
	private ServletContext ctx;
	
	@RequestMapping("list")
	public String list() {
		
		return "admin.board.notice.list";
	}

	
	@GetMapping("reg")
	public String reg( ) {
		
		return "admin.board.notice.reg";  //forward ���
	}
	
	@PostMapping("reg")
	public String reg(String title, String content, MultipartFile[] files,  String category, String[] foods, String favorite) throws IllegalStateException, IOException { //form���� �Ѿ�� class name�� �޴´�
	
		String webPath =  "/static/upload";
		String realPath = ctx.getRealPath(webPath);
		
		//���ε��ϱ� ���� ��ΰ� ���� ���, ������ �������� ���� ��� ���� ����
		File savePath = new File(realPath);
		if(!savePath.exists())
			savePath.mkdirs();  //�������� ��� ���� mkdir:�ش������� ����
		
		for(MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			//
			long fsize = file.getSize();
			
			String fullPath;
			fullPath = realPath + File.separator + fileName;
			File saveFile = new File(fullPath);
			file.transferTo(saveFile);
				
			System.out.printf("realPath:%s\n", realPath);
		}
//		
//		System.out.println("title:" + title);
//		System.out.println("content:" + content);
//		System.out.println("category:" + category);
//		for(String food : foods)
//			System.out.println("food:" + food);
//		System.out.println("favorite:" + favorite);
//		
		return "redirect:list";
//		return String.format("title:%s<br>content:%s<br>category:%s", title, content, category);

	}
	
	@RequestMapping("detail")
	public String detail() {
		
		return "admin.board.notice.detail";
	}

	
}
