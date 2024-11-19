package com.qlsvtc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.model.baocao.BCBangDiemMonHocLTC;

public class BCBangDiemMonHocLTCMapper implements RowMapper<BCBangDiemMonHocLTC> {
    @Override
    public BCBangDiemMonHocLTC mapRow(ResultSet resultSet) {
        try {
            BCBangDiemMonHocLTC bangDiem = new BCBangDiemMonHocLTC();
            
            bangDiem.setMaSV(resultSet.getString("MASV"));
            bangDiem.setHo(resultSet.getString("HO"));
            bangDiem.setTen(resultSet.getString("TEN"));
            bangDiem.setDiemChuyenCan(resultSet.getFloat("DIEM_CC"));
            bangDiem.setDiemGiuaKy(resultSet.getFloat("DIEM_GK"));
            bangDiem.setDiemCuoiKy(resultSet.getFloat("DIEM_CK"));
            bangDiem.setDiemHetMon(resultSet.getFloat("DIEM_HET_MON"));
            
            return bangDiem;
        } catch (SQLException e) {
            return null;
        }
    }
}
