package com.qlsvtc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.model.baocao.BCDsLTC;

public class BCDsLTCMapper implements RowMapper<BCDsLTC> {
    @Override
    public BCDsLTC mapRow(ResultSet resultSet) {
        try {
            BCDsLTC bcdsLTC = new BCDsLTC();
            
            bcdsLTC.setTenMonHoc(resultSet.getString("TENMH"));
            bcdsLTC.setNhom(resultSet.getInt("NHOM"));
            bcdsLTC.setGV(resultSet.getString("GV"));
            bcdsLTC.setSoSVToiThieu(resultSet.getInt("SOSVTOITHIEU"));
            bcdsLTC.setSoSVDaDangKy(resultSet.getInt("SOSVDANGKY"));
            
            return bcdsLTC;
        } catch (SQLException e) {
            return null;
        }
    }
}
