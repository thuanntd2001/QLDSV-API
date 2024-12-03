package com.qlsvtc.dao.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.BCSinhVienMapper;
import com.qlsvtc.model.baocao.BCSinhVien;



@Repository
public class BCDSSVDAO extends AbstractDAO<BCSinhVien> {

	public List<BCSinhVien> findAllKhoa(HttpSession session,String maLop){
		String sql = "Select * from SINHVIEN where maLop = ?";
		List<BCSinhVien> bc =queryPM(session,sql,new BCSinhVienMapper(),maLop);
		
		return bc;
	}

	public List<BCSinhVien> findAllPGV(HttpSession session,String maLop){
		String sql = "";
		List<BCSinhVien> bc =queryPM(session,sql,new BCSinhVienMapper(),maLop);
		
		return bc;
	}


}
