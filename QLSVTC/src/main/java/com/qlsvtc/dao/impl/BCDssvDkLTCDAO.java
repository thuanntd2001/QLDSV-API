package com.qlsvtc.dao.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.BCDssvDkLTCMapper;
import com.qlsvtc.model.baocao.BCDssvDkLTC;



@Repository
public class BCDssvDkLTCDAO extends AbstractDAO<BCDssvDkLTC> {

	public List<BCDssvDkLTC> findAll(HttpSession session,int nk,int hk,String maMh, int nhom){
		String sql = "EXEC SP_REPORT_DS_SINHVIEN_DANGKY ?, ?, ?, ?";
		List<BCDssvDkLTC> bc =queryPM(session,sql,new BCDssvDkLTCMapper(),nk,hk,maMh,nhom);
		
		return bc;
	}



}
