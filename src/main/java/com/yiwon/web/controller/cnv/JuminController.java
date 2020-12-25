package com.yiwon.web.controller.cnv;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yiwon.web.entity.EncJumin;
import com.yiwon.web.service.CnvService;

@RestController("cnvController")
@RequestMapping("/cnv/")
public class JuminController {
	
	@Autowired
	private CnvService cnvService;
	
	private String custno = ""; 
	private String jumin = "";
	
	@RequestMapping("list")
	public void aaa() throws ClassNotFoundException, SQLException {
		//public List<EncJumin> list() throws ClassNotFoundException, SQLException {
		
		List<EncJumin> list = cnvService.getList();
		
		System.out.println("Controller");
		
		for(EncJumin cust : list) { 
		    custno = cust.getCustno();
		    jumin = cust.getEncJumin();
		    System.out.println(custno);
		}

		
		//return list;
	}

	

}
