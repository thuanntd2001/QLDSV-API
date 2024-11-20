package com.qlsvtc.dao.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.BCBangDiemMonHocLTCMapper;
import com.qlsvtc.model.baocao.BCBangDiemMonHocLTC;



@Repository
public class BCBangDiemMonHocLTCDAO extends AbstractDAO<BCBangDiemMonHocLTC> {

	public List<BCBangDiemMonHocLTC> findAll(HttpSession session,int nk,int hk,String maMh, int nhom){
		String sql = "EXEC SP_REPORT_BANG_DIEM_LTC ?, ?, ?, ?";
		List<BCBangDiemMonHocLTC> bc =queryPM(session,sql,new BCBangDiemMonHocLTCMapper(),nk,hk,maMh,nhom);
		
		return bc;
	}



}
