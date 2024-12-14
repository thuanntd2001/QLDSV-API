package com.qlsvtc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.model.DangKyModel;

public class DaDangKyMapper implements RowMapper<DangKyModel> {
    @Override
    public DangKyModel mapRow(ResultSet resultSet) {
        try {
            DangKyModel dangKyModel = new DangKyModel();
            
            dangKyModel.setMaLTC(resultSet.getInt("MALTC"));
            dangKyModel.setMaMH(resultSet.getString("MAMH"));
            dangKyModel.setTenMH(resultSet.getString("TENMONHOC"));
            dangKyModel.setNhom(resultSet.getInt("NHOM"));
            dangKyModel.setGV(resultSet.getString("GV"));
            //dangKyModel.setSoSVDK(resultSet.getInt("sosvdk"));
            dangKyModel.setTKB(resultSet.getString("TKB"));
            
            return dangKyModel;
        } catch (SQLException e) {
            return null; // Ho?c b?n có th? x? lý ngo?i l? theo cách khác
        }
    }
}
