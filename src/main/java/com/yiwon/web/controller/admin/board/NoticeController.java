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
		
		return "admin.board.notice.reg";  //forward 방식
	}
	
	@PostMapping("reg")
	public String reg(String title, String content, MultipartFile[] files,  String category, String[] foods, String favorite) throws IllegalStateException, IOException { //form에서 넘어온 class name을 받는다
	
		String webPath =  "/static/upload";
		String realPath = ctx.getRealPath(webPath);
		
		//업로드하기 위한 경로가 없을 경우, 폴더가 존재하지 않을 경우 폴더 생성
		File savePath = new File(realPath);
		if(!savePath.exists())
			savePath.mkdirs();  //하위폴더 모두 생성 mkdir:해당폴더만 생성
		
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
