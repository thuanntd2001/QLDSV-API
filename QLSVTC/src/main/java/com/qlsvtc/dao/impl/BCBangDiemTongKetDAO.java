package com.qlsvtc.dao.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.BCBangDiemTongKetMapper;
import com.qlsvtc.model.baocao.BCBangDiemTongKet;



@Repository
public class BCBangDiemTongKetDAO extends AbstractDAO<BCBangDiemTongKet> {

	public List<BCBangDiemTongKet> findAll(HttpSession session,String maLop){

		String sql = "EXEC SP_REPORT_DIEMTONGKET ?";
		List<BCBangDiemTongKet> bc =queryPM(session,sql,new BCBangDiemTongKetMapper(),maLop);
		
		return bc;
	}



}
