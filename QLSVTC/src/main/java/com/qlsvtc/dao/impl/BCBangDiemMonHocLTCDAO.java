package com.qlsvtc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.BCBangDiemMonHocLTCMapper;
import com.qlsvtc.model.baocao.BCBangDiemMonHocLTC;
import com.qlsvtc.statics.InfoConnection;



@Repository
public class BCBangDiemMonHocLTCDAO extends AbstractDAO<BCBangDiemMonHocLTC> {

	public List<BCBangDiemMonHocLTC> findAll(int nk,int hk,String maMh, int nhom){
		String sql = "EXEC SP_REPORT_BANG_DIEM_LTC ?, ?, ?, ?";
		List<BCBangDiemMonHocLTC> bc =queryPM(InfoConnection.getUserNamePM(),InfoConnection.getPassWordPM(),sql,new BCBangDiemMonHocLTCMapper(),nk,hk,maMh,nhom);
		
		return bc;
	}



}
