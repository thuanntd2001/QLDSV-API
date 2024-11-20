package com.qlsvtc.dao.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.BCDsLTCMapper;
import com.qlsvtc.model.baocao.BCDsLTC;



@Repository
public class BCDsLTCDAO extends AbstractDAO<BCDsLTC> {

	public List<BCDsLTC> findAll(HttpSession session,int nk,int hk){
		String sql = "EXEC SP_REPORT_DS_LOPTINCHI ?, ?";
		List<BCDsLTC> bc =queryPM(session,sql,new BCDsLTCMapper(),nk,hk);
		
		return bc;
	}



}
