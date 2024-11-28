package com.qlsvtc.dao.impl;

import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.BCBangDiemMonHocLTCMapper;
import com.qlsvtc.model.baocao.BCBangDiemMonHocLTC;



@Repository
public class BCBangDiemTongKetDAO extends AbstractDAO<BCBangDiemMonHocLTC> {

	public ResultSet findAll(HttpSession session,int nk,int hk,String maMh, int nhom){
		String sql = "EXEC SP_REPORT_BANG_DIEM_LTC ?, ?, ?, ?";
		ResultSet bc =queryPMResultSet(session,sql,new BCBangDiemMonHocLTCMapper(),nk,hk,maMh,nhom);
		
		return bc;
	}



}
