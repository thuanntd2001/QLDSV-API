package com.qlsvtc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.model.baocao.BCPhieuDiem;

public class BCPhieuDiemMapper implements RowMapper<BCPhieuDiem> {
    @Override
    public BCPhieuDiem mapRow(ResultSet resultSet) {
        try {
            BCPhieuDiem phieuDiem = new BCPhieuDiem();
            //phieuDiem.setStt(resultSet.getInt("STT"));
            phieuDiem.setTenMonHoc(resultSet.getString("TENMONHOC"));
            //phieuDiem.setMaMH(resultSet.getString("MAMH"));
           // phieuDiem.setDiemCC(resultSet.getFloat("DIEM_CC"));

            phieuDiem.setDiemHetMon(resultSet.getFloat("DIEM"));
            
            return phieuDiem;
        } catch (SQLException e) {
            return null;
        }
    }
}
