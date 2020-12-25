package com.yiwon.web.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiwon.web.entity.Member;
import com.yiwon.web.service.MemberService;

@Service 
public class JDBCMemberService implements MemberService{

	@Autowired
	private DataSource dataSource;

	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	private String sql = "";

	public int insertMember(Member member) throws ClassNotFoundException, SQLException {
		
		int result = 0;
		
		sql = "insert into member (id, 	pwd, 	name, 	gender, birthday, 	phone, 	regdate, email)"
				+ "         values( ?, 	?, 		?, 		?, 		?,			?,		sysdate, ?)";

		Connection con = dataSource.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, member.getId());
		pst.setString(2, member.getPwd());
		pst.setString(3, member.getName());
		pst.setString(4, member.getGender());
		pst.setString(5, member.getBirthday());
		pst.setString(6, member.getPhone());
		pst.setString(7, member.getEmail());
		
		result =  pst.executeUpdate();

		pst.close();
		con.close();


		return result;
	}
	
	public String getLogin(String argId, String argPwd) throws ClassNotFoundException, SQLException {
		
		String pwd = "";
		String ret = "0";
		
		sql = "select pwd"
			+ " from member"
			+ " where id = ?";

		Connection con = dataSource.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, argId);
		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) {
			pwd = rs.getString("pwd");
			if(pwd == null)
				ret = "존재하지않는 아이디 입니다!"	;
			else
			{
			
				if(!pwd.equals(argPwd))
					ret = "잘못된 비밀번호 입니다!";
			}
		}
		else
			ret = "존재하지않는 아이디 입니다!"	;

		rs.close();
		pst.close();
		con.close();
			
	

		return ret;
	}
	public int idChk(String argId) throws ClassNotFoundException, SQLException {
		
		int cnt = 0;
		
		sql = "select count(*) cnt"
			+ " from member"
			+ " where id = ?";
		
		Connection con = dataSource.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, argId);
		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) 
			cnt = rs.getInt("cnt");
		else
			cnt = 0;
		
		rs.close();
		pst.close();
		con.close();
			
			
		return cnt;
	}


}
