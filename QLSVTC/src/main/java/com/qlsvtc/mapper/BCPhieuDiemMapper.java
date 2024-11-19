package com.qlsvtc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.model.baocao.BCPhieuDiem;

public class BCPhieuDiemMapper implements RowMapper<BCPhieuDiem> {
    @Override
    public BCPhieuDiem mapRow(ResultSet resultSet) {
        try {
            BCPhieuDiem phieuDiem = new BCPhieuDiem();
            phieuDiem.setStt(resultSet.getInt("STT"));
            phieuDiem.setTenMonHoc(resultSet.getString("TENMONHOC"));
            phieuDiem.setDiemHetMon(resultSet.getFloat("DIEM_HET_MON"));
            
            return phieuDiem;
        } catch (SQLException e) {
            return null;
        }
    }
}
