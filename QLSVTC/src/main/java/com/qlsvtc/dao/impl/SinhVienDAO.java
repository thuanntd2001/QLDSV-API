package com.qlsvtc.dao.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.StringSVMapper;
import com.qlsvtc.model.StringModel;
import com.qlsvtc.model.para.TaoLoginModel;

@Repository
public class SinhVienDAO extends AbstractDAO<TaoLoginModel> {

	public List<StringModel>taoSV (HttpSession session, String maLop, String maCN) {
		String sql = "EXEC SP_THEM_SV_2 ?, ?";
		List<StringModel> bc =queryPM(session,sql,new StringSVMapper(),maLop, maCN);

		return bc;
	}

}
