package com.qlsvtc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.model.baocao.BCSinhVien;

public class BCSinhVienMapper implements RowMapper<BCSinhVien> {
    @Override
    public BCSinhVien mapRow(ResultSet resultSet) {
        try {
            BCSinhVien sinhVien = new BCSinhVien();
            
            sinhVien.setMaSV(resultSet.getString("MASV"));
            sinhVien.setHo(resultSet.getString("HO"));
            sinhVien.setTen(resultSet.getString("TEN"));
            sinhVien.setPhai(resultSet.getBoolean("PHAI"));
            sinhVien.setDiaChi(resultSet.getString("DIACHI"));
            sinhVien.setNgaySinh(resultSet.getDate("NGAYSINH"));
            sinhVien.setLop(resultSet.getString("MALOP"));
            sinhVien.setCN(resultSet.getString("MACN"));
            sinhVien.setDaNghiHoc(resultSet.getBoolean("DANGHIHOC"));
            sinhVien.setPassword(resultSet.getString("PASSWORD"));
            
            return sinhVien;
        } catch (SQLException e) {
            return null;
        }
    }
}
