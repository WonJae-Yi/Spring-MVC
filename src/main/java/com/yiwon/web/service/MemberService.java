package com.yiwon.web.service;

import java.sql.SQLException;

import com.yiwon.web.entity.Member;

public interface MemberService {
	
	int insertMember(Member member) throws ClassNotFoundException, SQLException;
	String getLogin(String argId, String argPwd) throws ClassNotFoundException, SQLException;
	int idChk(String argId) throws ClassNotFoundException, SQLException;

}
