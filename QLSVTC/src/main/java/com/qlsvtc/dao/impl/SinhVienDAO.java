package com.qlsvtc.dao.impl;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.model.para.TaoLoginModel;

@Repository
public class SinhVienDAO extends AbstractDAO<TaoLoginModel> {

	public int taoSinhVien(HttpSession session, String maLop, String maCN, String ho, String ten, boolean phai,
			String diaChi, LocalDate ngaySinh, boolean daNghiHoc, String password) {
		String sql = "CALL SP_THEM_SV_2 ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int kp = spPM(session, sql, maLop, maCN, ho, ten, phai, diaChi, ngaySinh, daNghiHoc, password);

		return kp;
	}

}
