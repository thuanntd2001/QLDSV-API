package com.qlsvtc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.model.baocao.BCDssvDkLTC;

public class BCDssvDkLTCMapper implements RowMapper<BCDssvDkLTC> {
    @Override
    public BCDssvDkLTC mapRow(ResultSet resultSet) {
        try {
            BCDssvDkLTC bcdssvDkLTC = new BCDssvDkLTC();
            
            bcdssvDkLTC.setMaSV(resultSet.getString("MASV"));
            bcdssvDkLTC.setHo(resultSet.getString("HO"));
            bcdssvDkLTC.setTen(resultSet.getString("TEN"));
            bcdssvDkLTC.setPhai(resultSet.getBoolean("PHAI"));
            bcdssvDkLTC.setMaLop(resultSet.getString("MALOP"));
            
            return bcdssvDkLTC;
        } catch (SQLException e) {
            return null;
        }
    }
}
