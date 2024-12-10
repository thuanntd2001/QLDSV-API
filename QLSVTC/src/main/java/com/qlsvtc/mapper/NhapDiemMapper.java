package com.qlsvtc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.model.NhapDiemModel;

public class NhapDiemMapper implements RowMapper<NhapDiemModel> {
    @Override
    public NhapDiemModel mapRow(ResultSet resultSet) {
        try {
            NhapDiemModel dsdkModel = new NhapDiemModel();
            
            dsdkModel.setMaLTC(resultSet.getInt("LOPTC")); // Assuming the column name in the ResultSet is "MALTC"
            dsdkModel.setMaSV(resultSet.getString("MASV"));   // Assuming the column name in the ResultSet is "MASV"
            dsdkModel.setHoTen(resultSet.getString("HOTEN")); // Assuming the column name in the ResultSet is "HO_TEN"
            dsdkModel.setDiemCC(resultSet.getFloat("DIEM_CC")); // Assuming the column name in the ResultSet is "DIEM_CC"
            dsdkModel.setDiemGK(resultSet.getFloat("DIEM_GK")); // Assuming the column name in the ResultSet is "DIEM_GK"
            dsdkModel.setDiemCK(resultSet.getFloat("DIEM_CK")); // Assuming the column name in the ResultSet is "DIEM_CK"
            dsdkModel.setDiemTK(resultSet.getFloat("DIEM_TK")); // Assuming the column name in the ResultSet is "DIEM_CK"

            
            return dsdkModel;
        } catch (SQLException e) {
            // Log the exception if needed
            e.printStackTrace();
            return null; // or throw a custom exception
        }
    }
}