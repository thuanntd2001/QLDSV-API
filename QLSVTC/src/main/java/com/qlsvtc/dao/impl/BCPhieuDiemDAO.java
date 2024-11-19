package com.qlsvtc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.BCPhieuDiemMapper;
import com.qlsvtc.model.baocao.BCPhieuDiem;
import com.qlsvtc.statics.InfoConnection;



@Repository
public class BCPhieuDiemDAO extends AbstractDAO<BCPhieuDiem> {

	public List<BCPhieuDiem> findAll(String maSV){
		String sql = "EXEC SP_REPORT_PHIEU_DIEM_SV ?";
		List<BCPhieuDiem> bc =queryPM(InfoConnection.getUserNamePM(),InfoConnection.getPassWordPM(),sql,new BCPhieuDiemMapper(),maSV);
		
		return bc;
	}



}
