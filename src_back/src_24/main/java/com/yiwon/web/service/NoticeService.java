package com.yiwon.web.service;

import java.sql.SQLException;
import java.util.List;

import com.yiwon.web.entity.Notice;

public interface NoticeService {

	List<Notice> getNoticeList() throws ClassNotFoundException, SQLException;

	List<Notice> getNoticeList(int page) throws ClassNotFoundException, SQLException;
	List<Notice> getNoticeList(String field, String query, int page, String argPub) throws ClassNotFoundException, SQLException ;

	int getNoticeCount() throws ClassNotFoundException, SQLException;
	int getNoticeCount(String field, String query, String argPub) throws ClassNotFoundException, SQLException;

	//상세내역 가져오기
	Notice getNotice(int id) throws ClassNotFoundException, SQLException;
	Notice getNextNotice(String field, String query, String argPub, int argId)  throws ClassNotFoundException, SQLException;
	
	Notice getPrevNotice(String field, String query, String argPub, int argId)  throws ClassNotFoundException, SQLException;

	int deleteNoticeAll(String[] ids)  throws ClassNotFoundException, SQLException;
	//id가 int 배열로넘어 온 것을 list형식으로 변환하여 호출
	int pubNoticeAll(int[] ids, int[] openIds)  throws ClassNotFoundException, SQLException;
	
	//id가 list로 넘어온것을  csv형식으로 변환하여 호출
	int pubNoticeAll(List<String> ids, List<String> openIds)  throws ClassNotFoundException, SQLException;
	//id가 String 배열로넘어 온 것을  csv형식으로 변환하여 호출
	int pubNoticeAll(String[] ids, String[] openIds)  throws ClassNotFoundException, SQLException;
	int pubNoticeAll(String idsParams, String openParams)  throws ClassNotFoundException, SQLException;

	int insertNotice(Notice notice) throws ClassNotFoundException, SQLException;


}
