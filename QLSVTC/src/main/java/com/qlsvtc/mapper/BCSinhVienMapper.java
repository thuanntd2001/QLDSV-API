package com.qlsvtc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.model.baocao.BCSinhVien;

public class BCSinhVienMapper implements RowMapper<BCSinhVien> {
    @Override
    public BCSinhVien mapRow(ResultSet resultSet) {
        try {
            BCSinhVien sinhVien = new BCSinhVien();
            
            sinhVien.setMasv(resultSet.getString("MASV"));
            sinhVien.setHo(resultSet.getString("HO"));
            sinhVien.setTen(resultSet.getString("TEN"));
            sinhVien.setPhai(resultSet.getBoolean("PHAI"));
            sinhVien.setDiachi(resultSet.getString("DIACHI"));
            sinhVien.setNgaysinh(resultSet.getString("NGAYSINH"));
            sinhVien.setLop(resultSet.getString("LOP"));
            sinhVien.setCN(resultSet.getString("CN"));
            sinhVien.setDanghihoc(resultSet.getBoolean("DANGHIHOC"));
            sinhVien.setPassword(resultSet.getString("PASSWORD"));
            
            return sinhVien;
        } catch (SQLException e) {
            return null;
        }
    }
}
