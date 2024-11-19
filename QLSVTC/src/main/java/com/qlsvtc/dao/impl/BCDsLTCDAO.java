package com.qlsvtc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.BCDsLTCMapper;
import com.qlsvtc.model.baocao.BCDsLTC;
import com.qlsvtc.statics.InfoConnection;



@Repository
public class BCDsLTCDAO extends AbstractDAO<BCDsLTC> {

	public List<BCDsLTC> findAll(int nk,int hk){
		String sql = "EXEC SP_REPORT_DS_LOPTINCHI ?, ?";
		List<BCDsLTC> bc =queryPM(InfoConnection.getUserNamePM(),InfoConnection.getPassWordPM(),sql,new BCDsLTCMapper(),nk,hk);
		
		return bc;
	}



}
