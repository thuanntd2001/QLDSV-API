package com.qlsvtc.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DSTS {

	/*
	 * DBCHU_URL = jdbc:sqlserver://DESKTOP-DH2SJV4\MSSQLSERVER0; Database=QLSVTC
	 * DBCNTT_URL = jdbc:sqlserver://DESKTOP-DH2SJV4\MSSQLSERVER1; Database=QLSVTC
	 * DBVT_URL = jdbc:sqlserver://DESKTOP-DH2SJV4\MSSQLSERVER2; Database=QLSVTC
	 * 
	 * DBUSER_CHU = sa DBPASS_CHU = 1234 DBUSER_CNTT = sa DBPASS_CNTT = 1234
	 * DBUSER_VT = sa DBPASS_VT = 1234
	 */
	private String vdTLS = ";trustServerCertificate=true;sslProtocol=TLSv1.2";
	// @Value("${app.DBUSER_CHU}")
	private String appuserNameChu = System.getenv("DBUSER_CHU");
	// @Value("${app.DBPASS_CHU}")
	private String apppassWordChu = System.getenv("DBPASS_CHU");
	// @Value("${app.DBCHU_URL}")
	private String appurlChu = System.getenv("DBCHU_URL") + vdTLS;

	// @Value("${app.DBUSER_CNTT}")
	private String appuserNameCNTT = System.getenv("DBUSER_CNTT");
	// @Value("${app.DBPASS_CNTT}")
	private String apppassWordCNTT = System.getenv("DBPASS_CNTT");
	// @Value("${app.DBCNTT_URL}")
	private String appurlCNTT = System.getenv("DBCNTT_URL")+ vdTLS;

	// @Value("${app.DBUSER_VT}")
	private String appuserNameVT = System.getenv("DBUSER_VT");
	// @Value("${app.DBPASS_VT}")
	private String apppassWordVT = System.getenv("DBPASS_VT");
	// @Value("${app.DBVT_URL}")
	private String appurlVT = System.getenv("DBVT_URL")+ vdTLS;

	private static String userNameChu;

	private static String passWordChu;

	private static String urlChu;

	private static String userNameCNTT;
	private static String passWordCNTT;
	private static String urlCNTT;

	private static String userNameVT;
	private static String passWordVT;
	private static String urlVT;

	private static String driverChu = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String driverPM = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String tksv = "SV01";

	private static String pwsv = "1234";

	@PostConstruct
	public void init() {
		userNameChu = appuserNameChu;
		passWordChu = apppassWordChu;
		urlChu = appurlChu;

		userNameCNTT = appuserNameCNTT;
		passWordCNTT = apppassWordCNTT;
		urlCNTT = appurlCNTT;

		userNameVT = appuserNameVT;
		passWordVT = apppassWordVT;
		urlVT = appurlVT;

	}

	public static String getTksv() {
		return tksv;
	}

	public static String getPwsv() {
		return pwsv;
	}

	public static String getUserNameChu() {
		return userNameChu;
	}

	public static void setUserNameChu(String userNameChu) {
		DSTS.userNameChu = userNameChu;
	}

	public static String getPassWordChu() {
		return passWordChu;
	}

	public static void setPassWordChu(String passWordChu) {
		DSTS.passWordChu = passWordChu;
	}

	public static String getUrlChu() {
		return urlChu;
	}

	public static void setUrlChu(String urlChu) {
		DSTS.urlChu = urlChu;
	}

	public static String getDriverChu() {
		return driverChu;
	}

	public static void setDriverChu(String driverChu) {
		DSTS.driverChu = driverChu;
	}

	public static String getDriverPM() {
		return driverPM;
	}

	public static void setDriverPM(String driverPM) {
		DSTS.driverPM = driverPM;
	}

	public static String getUserNameCNTT() {
		return userNameCNTT;
	}

	public static void setUserNameCNTT(String userNameCNTT) {
		DSTS.userNameCNTT = userNameCNTT;
	}

	public static String getPassWordCNTT() {
		return passWordCNTT;
	}

	public static void setPassWordCNTT(String passWordCNTT) {
		DSTS.passWordCNTT = passWordCNTT;
	}

	public static String getUrlCNTT() {
		return urlCNTT;
	}

	public static void setUrlCNTT(String urlCNTT) {
		DSTS.urlCNTT = urlCNTT;
	}

	public static String getUserNameVT() {
		return userNameVT;
	}

	public static void setUserNameVT(String userNameVT) {
		DSTS.userNameVT = userNameVT;
	}

	public static String getPassWordVT() {
		return passWordVT;
	}

	public static void setPassWordVT(String passWordVT) {
		DSTS.passWordVT = passWordVT;
	}

	public static String getUrlVT() {
		return urlVT;
	}

	public static void setUrlVT(String urlVT) {
		DSTS.urlVT = urlVT;
	}

	public static void setTksv(String tksv) {
		DSTS.tksv = tksv;
	}

	public static void setPwsv(String pwsv) {
		DSTS.pwsv = pwsv;
	}

}
