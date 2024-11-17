package com.qlsvtc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qlsvtc.model.DSPMModel;



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
