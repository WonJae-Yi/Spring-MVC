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

	//�󼼳��� ��������
	Notice getNotice(int id) throws ClassNotFoundException, SQLException;
	Notice getNextNotice(String field, String query, String argPub, int argId)  throws ClassNotFoundException, SQLException;
	
	Notice getPrevNotice(String field, String query, String argPub, int argId)  throws ClassNotFoundException, SQLException;

	int deleteNoticeAll(String[] ids)  throws ClassNotFoundException, SQLException;
	//id�� int �迭�γѾ� �� ���� list�������� ��ȯ�Ͽ� ȣ��
	int pubNoticeAll(int[] ids, int[] openIds)  throws ClassNotFoundException, SQLException;
	
	//id�� list�� �Ѿ�°���  csv�������� ��ȯ�Ͽ� ȣ��
	int pubNoticeAll(List<String> ids, List<String> openIds)  throws ClassNotFoundException, SQLException;
	//id�� String �迭�γѾ� �� ����  csv�������� ��ȯ�Ͽ� ȣ��
	int pubNoticeAll(String[] ids, String[] openIds)  throws ClassNotFoundException, SQLException;
	int pubNoticeAll(String idsParams, String openParams)  throws ClassNotFoundException, SQLException;

	int insertNotice(Notice notice) throws ClassNotFoundException, SQLException;


}
