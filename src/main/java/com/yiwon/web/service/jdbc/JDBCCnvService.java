package com.yiwon.web.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiwon.web.entity.EncJumin;
import com.yiwon.web.service.CnvService;

@Service 
public class JDBCCnvService implements CnvService {
	
	private String sql = "";
	
	@Autowired
	private DataSource dataBP;


	public void setDataBP(DataSource dataBP) {
		this.dataBP = dataBP;
	}
	
	public List<EncJumin> getList() throws ClassNotFoundException, SQLException {

		List<EncJumin> list = new ArrayList<>();

		sql = "select jumin, custno"
				+ " 	from st_cust_mst"
				+ "		where jumin is not null"	;


		Connection con = dataBP.getConnection();		
		PreparedStatement pst = con.prepareStatement(sql);

		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			String jumin = rs.getString("jumin");
			String custno = rs.getString("custno");
			
			EncJumin encJumin = new EncJumin(custno, jumin);
			list.add(encJumin);
		}
		rs.close();
		pst.close();
		con.close();


		return list;
	}	

}
