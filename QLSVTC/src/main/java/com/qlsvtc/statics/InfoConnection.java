package com.qlsvtc.statics;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InfoConnection {

/*	
	private static String userNameChu="sa";
	private static String passWordChu="1234";
	private static String urlChu="jdbc:sqlserver://DESKTOP-DH2SJV4\\MSSQLSERVER0; Database=QLSVTC";
	private static String driverChu="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	

	private static String driverPM="com.microsoft.sqlserver.jdbc.SQLServerDriver";*/
	
	@Value("${app.DBuserchu}")
	private  String userNameChu="sa";
	@Value("${app.DBpasschu}")
	private  String passWordChu="1234";
	@Value("${app.DBChuUrl}")
	private  String urlChu="jdbc:sqlserver://DESKTOP-DH2SJV4\\MSSQLSERVER0; Database=QLSVTC";
	@Value("${app.DBCNTTurl}")
	private  String driverChu="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	@Value("${app.driver}")
	private  String driverPM="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	public  String getUserNameChu() {
		return userNameChu;
	}

	public  String getPassWordChu() {
		return passWordChu;
	}

	public  String getUrlChu() {
		return urlChu;
	}

	public  String getDriverChu() {
		return driverChu;
	}


	public  String getDriverPM() {
		return driverPM;
	}

	
	

}
