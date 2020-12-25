package com.yiwon.web.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/")
public class MemberController {
	

	@RequestMapping("home")
	public String home() {
				
		return "member.login";
	}
	
	@RequestMapping("agree")
	public String agree() {
				
		return "member.agree";
	}
	
	@RequestMapping("confirm")
	public String confirm() {
				
		return "member.confirm";
	}
	
	@RequestMapping("join")
	public String join() {
		
		
		return "member.join";
	}
	
	@RequestMapping("login")
	public String login() {
		
		
		return "member.login";
	}

}
