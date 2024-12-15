package com.qlsvtc.dao.impl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.model.DangKyModel;



@Repository
public class ChuyenLopDAO extends AbstractDAO<DangKyModel> {


	public int ChuyenLop(HttpSession session, String maSV, String maLop,String maCN){
		String sql = "EXEC SP_CHUYENLOP_SV ?, ?, ?";
		int bc =spPM(session,sql,maSV,maLop,maCN);
		return bc;
		
	}


}
