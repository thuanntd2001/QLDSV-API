package com.qlsvtc.dao.impl;

import java.util.List;

import com.qlsvtc.model.DSPMMapper;
import com.qlsvtc.model.DSPMModel;



public class DSPMDAO extends AbstractDAO<DSPMModel> {
	public List<DSPMModel> findAll() {
		String sql = "SELECT * from dbo.view_DanhSachPhanManh";
		return queryChu(sql, new DSPMMapper());
	}
}