package com.yiwon.web.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiwon.web.entity.Notice;
import com.yiwon.web.service.NoticeService;

@Service  //@Controller, @Service, @Repository 모두를 포함하는것은  @Component
public class JDBCNoticeService implements NoticeService{
	
	private String sql = "";
	
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Notice> getNoticeList() throws ClassNotFoundException, SQLException {

		return getNoticeList("title", "", 1, "1");

	}

	public List<Notice> getNoticeList(int page) throws ClassNotFoundException, SQLException {

		return getNoticeList("title", "", page,"1");

	}

	public List<Notice> getNoticeList(String field, String query, int page, String argPub) throws ClassNotFoundException, SQLException {

		List<Notice> list = new ArrayList<>();

		sql = "select id, title, writer_id, regdate, hit, files, cmt_count, pub"
				+ " 	from (select row_number()over (order by id desc) no, id, title, writer_id, regdate, hit, files, pub,"
				+ "           (select count(*) from comments where notice_id = a.id) cmt_count"
				+ " 			from notice a "
				+ "				where pub like ?"
				+ " 			and " + field + " like '%'||?||'%')"
				+ " 	where no between ? and ? ";

		Connection con = dataSource.getConnection();		
		PreparedStatement pst = con.prepareStatement(sql);
		//pst.setString(1, field);
		pst.setString(1, argPub);
		pst.setString(2, query);
		pst.setInt(3, 1 + (page - 1) * 10);
		pst.setInt(4, page * 10);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("Title");
			String writerId = rs.getString("writer_id");
			Date regDate = rs.getDate("regDate");
			int hit = rs.getInt("hit");
			String files = rs.getString("files");
			int cmtCount = rs.getInt("cmt_count");
			String pub = rs.getString("pub");

			Notice notice = new Notice(id, title, writerId, regDate, hit, files, "", cmtCount,pub);
			list.add(notice);
		}
		rs.close();
		pst.close();
		con.close();


		return list;
	}

	public int getNoticeCount() throws ClassNotFoundException, SQLException {
		return getNoticeCount("title", "", "1");
	}

	public int getNoticeCount(String field, String query, String argPub) throws ClassNotFoundException, SQLException {
		int cnt = 0;
		sql = "select count(*) cnt"
				+ " from notice"
				+ " where pub like ?"
				+ " and " + field + " like '%'||?||'%'";

		Connection con = dataSource.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, argPub);
		pst.setString(2, query);
		ResultSet rs = pst.executeQuery();
		
		if(rs.next())
			cnt = rs.getInt("cnt");

		rs.close();
		pst.close();
		con.close();	

		return cnt;
	}

	//상세내역 가져오기
	public Notice getNotice(int id) throws ClassNotFoundException, SQLException {

		Notice notice = null;
		sql = "select * from notice where id = ? ";

		Connection con = dataSource.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();

		if(rs.next()) {
			String title = rs.getString("Title");
			String writerId = rs.getString("writer_id");
			Date regDate = rs.getDate("regDate");
			int hit = rs.getInt("hit");
			String files = rs.getString("files");
			String content = rs.getString("content");
			String pub = rs.getString("pub");

			notice = new Notice(id, title, writerId, regDate, hit, files, content, 0, pub);
		}

		rs.close();
		pst.close();
		con.close();

		return notice;
	}

	public Notice getNextNotice(String field, String query, String argPub, int argId) throws ClassNotFoundException, SQLException {

		Notice notice = null;

		sql = "select /*+ index_desc(notice, notice_pk) */ id, title, writer_id, regdate, hit, content, files, pub"
			+ " from notice"
			+ " where id < ?"
			+ " and rownum <= 1"
			+ " and pub like ?"
			+ " and " + field + " like '%'||?||'%'";

		Connection con = dataSource.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, argId);
		pst.setString(2, argPub);
		pst.setString(3, query);
		ResultSet rs = pst.executeQuery();
		
		if (rs.next()) {

			int id = rs.getInt("id");
			String title = rs.getString("Title");
			String writerId = rs.getString("writer_id");
			Date regDate = rs.getDate("regDate");
			int hit = rs.getInt("hit");
			String files = rs.getString("files");
			String content = rs.getString("content");
			String pub = rs.getString("pub");

			notice = new Notice(id, title, writerId, regDate, hit, files, content, 0, pub);

		}

		rs.close();
		pst.close();
		con.close();

		return notice;
	}
	
	public Notice getPrevNotice(String field, String query, String argPub, int argId) throws ClassNotFoundException, SQLException {

		Notice notice = null;

		sql = "select /*+ index_asc(notice, notice_pk) */ id, title, writer_id, regdate, hit, content, files, pub"
			+ " from notice"
			+ " where id > ?"
			+ " and rownum <= 1"
			+ " and pub like ?"
			+ " and " + field + " like '%'||?||'%'";


		Connection con = dataSource.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, argId);
		pst.setString(2, argPub);
		pst.setString(3, query);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("Title");
			String writerId = rs.getString("writer_id");
			Date regDate = rs.getDate("regDate");
			int hit = rs.getInt("hit");
			String files = rs.getString("files");
			String content = rs.getString("content");
			String pub = rs.getString("pub");

			notice = new Notice(id, title, writerId, regDate, hit, files, content, 0, pub);

		}

		rs.close();
		pst.close();
		con.close();

		return notice;
	}	

	public int deleteNoticeAll(String[] ids) throws ClassNotFoundException, SQLException {
		
		int result = 0;
		
		String params = "";
			
		for(int i=0 ; i < ids.length ; i++) {
			params += ids[i];
			if(i < ids.length - 1)
				params += ",";		
		}
	
		sql = "delete from notice where id in (" + params + ")";

		Connection con = dataSource.getConnection();
		Statement st = con.createStatement();
		result = st.executeUpdate(sql);

		st.close();
		con.close();

		return result;
	}
	
	//id가 int 배열로넘어 온 것을 list형식으로 변환하여 호출
	public int pubNoticeAll(int[] ids, int[] openIds) throws ClassNotFoundException, SQLException {
		List<String> idsList = new ArrayList<>();
		for(int i=0 ; i < ids.length ; i++) 
			idsList.add(String.valueOf(ids[i]));
			
		List<String> openIdsList = new ArrayList<>();
		for(int i=0 ; i < openIds.length ; i++) 
			openIdsList.add(String.valueOf(openIds[i]));
		
		return pubNoticeAll(idsList, openIdsList);
	}
	
	//id가 list로 넘어온것을  csv형식으로 변환하여 호출
	public int pubNoticeAll(List<String> ids, List<String> openIds) throws ClassNotFoundException, SQLException {
		
		String idsCVS = String.join(",", ids);
		String openIdsCVS = String.join(",", openIds);
		
		return pubNoticeAll(idsCVS, openIdsCVS);
	}
	
	//id가 String 배열로넘어 온 것을  csv형식으로 변환하여 호출
	public int pubNoticeAll(String[] ids, String[] openIds) throws ClassNotFoundException, SQLException {
				
		String idsParams = "";
		String openParams = "";
		
		for(int i=0 ; i < ids.length ; i++) {
			idsParams += ids[i];
			if(i < ids.length - 1)
				idsParams += ",";		
		}
		for(int i=0 ; i < openIds.length ; i++) {
			openParams += openIds[i];
			if(i < openIds.length - 1)
				openParams += ",";		
		}
		
		return pubNoticeAll(idsParams, openParams);
	}

	//id가 csv형식으로 넘어온다.
	public int pubNoticeAll(String idsParams, String openParams) throws ClassNotFoundException, SQLException {
		int result = 0;

		sql = "update notice"
			+ " set pub = case when id in (" + openParams + ") then '1' else '0' end"
			+ " where id in (" + idsParams + ")";

		Connection con = dataSource.getConnection();
		Statement st = con.createStatement();
		result = st.executeUpdate(sql);
		
		st.close();
		con.close();
			
		return result;
	}

	public int insertNotice(Notice notice) throws ClassNotFoundException, SQLException{
		
		int result = 0;
		
		sql = "insert into notice (id, title, content, writer_id, pub, regdate, hit, files)"
				+ "values( SEQ_NOTICE_ID.nextval, ?, ?, ?, ?, sysdate, 0, ?)";

		Connection con = dataSource.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, notice.getTitle());
		pst.setString(2, notice.getContent());
		pst.setString(3, notice.getWriterid());
		pst.setString(4, notice.getPub());
		pst.setString(5, notice.getFiles());
		result =  pst.executeUpdate();

		pst.close();
		con.close();

		return result;
	}


}
