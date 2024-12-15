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

	public int DangKy(HttpSession session, String maSV, int maLTC){
		String sql = "EXEC SP_SV_DANGKY ?, ?";
		int bc =spPM(session,sql,maSV,maLTC);
		return bc;
		
	}
	public int HuyDangKy(HttpSession session, String maSV, int maLTC){
		String sql = "UPDATE DANGKY set HUYDANGKY=1 where MASV= ? and  maLTC= ?";
		int bc =spPM(session,sql,maSV,maLTC);
		return bc;
		
	}
	public int DangKyLai(HttpSession session, String maSV, int maLTC){
		String sql = "UPDATE DANGKY set HUYDANGKY=0 where MASV= ? and  maLTC= ?";
		int bc =spPM(session,sql,maSV,maLTC);
		return bc;
		
	}

}
