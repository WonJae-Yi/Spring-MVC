package com.yiwon.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yiwon.web.entity.Notice;
import com.yiwon.web.service.NoticeService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50 * 5)
public class RegController implements Controller{
	
	@Autowired
	private NoticeService noticeService;
	
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mv = new ModelAndView("admin.notice.reg");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");  //üũ�Ǿ������� true�� �Ѿ��
		String pub = "0";
		if(isOpen != null)			
			pub = "1";	
				
		//���ϵ�� 
		Collection<Part> parts = request.getParts(); 
		StringBuilder builder =	new StringBuilder();
		  
		for(Part p : parts) {
		  
			  if(!p.getName().equals("file")) continue; 
			  if(p.getSize() == 0) continue;
			  
			  Part filePart = p; 
			  String fileName = filePart.getSubmittedFileName();
			  builder.append(fileName); 
			  builder.append(","); 
			  InputStream fis = filePart.getInputStream();			  
			  String realPath = request.getServletContext().getRealPath("/upload");			  
			  
			  //������  ���ϸ��� �����Ѵٸ� 
			  String filePath = realPath + File.separator + fileName;
			  
			  //System.out.println(filePath); 
			  File path = new File(realPath);
			  if(!path.exists()) path.mkdirs();
			  			  
			  FileOutputStream fos = new FileOutputStream(filePath);
			  
			  byte[] buf = new byte[1024]; 
			  int size = 0; 
			  while((size = fis.read(buf)) != -1) 
				  fos.write(buf,0,size);
			  
			  fos.close(); 
			  fis.close();
			  
		  }
		  
		  builder.delete(builder.length() - 1, builder.length()); //������ �޸� �ڸ���
		 		
		
		  //�������� ����� ���
		/*
		 * Part filePart = request.getPart("file"); //client���� ���� name�� String fileName
		 * = filePart.getSubmittedFileName(); InputStream fis =
		 * filePart.getInputStream(); String realPath =
		 * request.getServletContext().getRealPath("/upload"); //���� ������θ� ��´�.
		 * System.out.println(realPath);
		 * 
		 * 
		 * String filePath = realPath + File.separator + fileName; FileOutputStream fos
		 * = new FileOutputStream(filePath);
		 * 
		 * byte[] buf = new byte[1024]; int size = 0; while((size = fis.read(buf)) !=
		 * -1) fos.write(buf,0,size); fos.close(); fis.close();
		 */
		 	
		
		int result = 0;
		
		Notice notice = new Notice();
		
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		notice.setWriterid("yiwon");
		notice.setFiles(builder.toString());

		result = noticeService.insertNotice(notice);		

		
		return mv;
		
	}

}
