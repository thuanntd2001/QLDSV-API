package com.qlsvtc.dao.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.NhapDiemMapper;
import com.qlsvtc.model.NhapDiemModel;



@Repository
public class NhapDiemDAO extends AbstractDAO<NhapDiemModel> {

	public List<NhapDiemModel> findAll(HttpSession session,int nk, int hk, String maMH, int nhom){
		String sql = "EXEC SP_LAY_DS_DKMH ?, ?, ?, ?";
		List<NhapDiemModel> bc =queryPM(session,sql,new NhapDiemMapper(),nk,hk,nhom,maMH);
		
		return bc;
	}



}
