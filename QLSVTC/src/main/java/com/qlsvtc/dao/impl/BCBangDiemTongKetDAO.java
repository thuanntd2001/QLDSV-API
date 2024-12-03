package com.qlsvtc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.model.baocao.BCBangDiemMonHocLTC;



@Repository
public class BCBangDiemTongKetDAO extends AbstractDAO<BCBangDiemMonHocLTC> {

	public List<Map<String, Object>> findAll(HttpSession session,String maLop){
		String sql = "EXEC SP_REPORT_DIEMTONGKET ?";
		List<Map<String, Object>> bc =queryPMResultSet(session,sql,maLop);
		
		return bc;
	}



}
