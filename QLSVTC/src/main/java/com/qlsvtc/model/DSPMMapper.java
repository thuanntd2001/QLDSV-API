package com.qlsvtc.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.mapper.RowMapper;



public class DSPMMapper implements RowMapper<DSPMModel>{
	@Override
	public DSPMModel mapRow(ResultSet resultSet) {
		try {
			DSPMModel DSPM = new DSPMModel();
		
			DSPM.setTenKhoa(resultSet.getString("TENKHOA"));
			DSPM.setTenServer(resultSet.getString("TENSERVER"));
		
			return DSPM;
		} catch (SQLException e) {
			return null;
		}	
	}
}
