package com.qlsvtc.dao.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.DaDangKyMapper;
import com.qlsvtc.mapper.DangKyMapper;
import com.qlsvtc.model.DangKyModel;



@Repository
public class DangKyDAO extends AbstractDAO<DangKyModel> {

	public List<DangKyModel> findAllLTC(HttpSession session){
		String sql = "EXEC SP_DS_LOPTINCHI_MODK";
		List<DangKyModel> bc =queryPM(session,sql,new DangKyMapper());
		
		return bc;
	}

	public List<DangKyModel> findAllDaDAngKy(HttpSession session, String maSV){
		String sql = "EXEC SP_DS_LOPTINCHI_SVDK ?";
		List<DangKyModel> bc =queryPM(session,sql,new DaDangKyMapper(),maSV);
		
		return bc;
	}



}
