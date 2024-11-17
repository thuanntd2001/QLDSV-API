package com.qlsvtc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.model.NhanVienLoginModel;


public class NhanVienLoginMapper implements RowMapper<NhanVienLoginModel>{
	@Override
	public NhanVienLoginModel mapRow(ResultSet resultSet) {
		try {
			NhanVienLoginModel nhanVien = new NhanVienLoginModel();
			nhanVien.setMaNV(resultSet.getString("MANV"));
			nhanVien.setHoTen(resultSet.getString("HOTEN"));
			nhanVien.setTenNhom(resultSet.getString("TENNHOM"));
			return nhanVien;
		} catch (SQLException e) {
			return null;
		}	
	}
}
