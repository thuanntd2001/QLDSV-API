package com.qlsvtc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.model.StringModel;


public class StringSVMapper implements RowMapper<StringModel>{
	@Override
	public StringModel mapRow(ResultSet resultSet) {
		try {
			StringModel nhanVien = new StringModel();
			nhanVien.setStr(resultSet.getString("MASV"));
			return nhanVien;
		} catch (SQLException e) {
			return null;
		}	
	}
}
