package com.yiwon.web.service;

import java.sql.SQLException;
import java.util.List;

import com.yiwon.web.entity.EncJumin;

public interface CnvService {
	
	List<EncJumin> getList() throws ClassNotFoundException, SQLException; 
}
