package com.qlsvtc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.model.baocao.BCBangDiemTongKet;

public class BCBangDiemTongKetMapper implements RowMapper<BCBangDiemTongKet> {
    @Override
    public BCBangDiemTongKet mapRow(ResultSet resultSet) {
       
        try {
        	 BCBangDiemTongKet bangDiem = new BCBangDiemTongKet();            

             bangDiem.setTenMH(resultSet.getString("TENMONHOC")); 
             bangDiem.setMaSVHoTen(resultSet.getString("MASV_HOTEN")); 
             bangDiem.setDiemSV(resultSet.getFloat("DIEMSV"));
             
             return bangDiem;
        } catch (SQLException e) {
            return null;
        }
        
    }
}