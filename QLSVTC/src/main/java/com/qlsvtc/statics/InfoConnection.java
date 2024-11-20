package com.qlsvtc.statics;

public class InfoConnection {
	

	
	private static String userNameChu="sa";
	private static String passWordChu="1234";
	private static String urlChu="jdbc:sqlserver://DESKTOP-DH2SJV4\\MSSQLSERVER0; Database=QLSVTC";
	private static String driverChu="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	

	private static String driverPM="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	public static String getUserNameChu() {
		return userNameChu;
	}
	public static void setUserNameChu(String userNameChu) {
		InfoConnection.userNameChu = userNameChu;
	}
	public static String getPassWordChu() {
		return passWordChu;
	}
	public static void setPassWordChu(String passWordChu) {
		InfoConnection.passWordChu = passWordChu;
	}
	public static String getUrlChu() {
		return urlChu;
	}
	public static void setUrlChu(String urlChu) {
		InfoConnection.urlChu = urlChu;
	}
	public static String getDriverChu() {
		return driverChu;
	}
	public static void setDriverChu(String driverChu) {
		InfoConnection.driverChu = driverChu;
	}

	public static String getDriverPM() {
		return driverPM;
	}
	public static void setDriverPM(String driverPM) {
		InfoConnection.driverPM = driverPM;
	}

	
	

}
