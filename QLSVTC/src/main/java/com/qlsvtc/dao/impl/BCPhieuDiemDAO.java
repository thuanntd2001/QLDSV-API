package com.qlsvtc.dao.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.BCPhieuDiemMapper;
import com.qlsvtc.model.baocao.BCPhieuDiem;



@Repository
public class BCPhieuDiemDAO extends AbstractDAO<BCPhieuDiem> {

	public List<BCPhieuDiem> findAll(HttpSession session,String maSV){
		String sql = "EXEC SP_REPORT_PHIEU_DIEM_SV ?";
		List<BCPhieuDiem> bc =queryPM(session,sql,new BCPhieuDiemMapper(),maSV);
		
		return bc;
	}



}
