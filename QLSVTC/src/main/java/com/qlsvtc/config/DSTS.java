package com.qlsvtc.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DSTS {
	@Value("${app.DBuserChu}")
	private String appuserNameChu;
	@Value("${app.DBpassChu}")
	private String apppassWordChu;
	@Value("${app.DBChuUrl}")
	private String appurlChu;



	@Value("${app.DBuserCNTT}")
	private String appuserNameCNTT;
	@Value("${app.DBpassCNTT}")
	private String apppassWordCNTT;
	@Value("${app.DBCNTTUrl}")
	private String appurlCNTT;

	@Value("${app.DBuserVT}")
	private String appuserNameVT;
	@Value("${app.DBpassVT}")
	private String apppassWordVT;
	@Value("${app.DBVTUrl}")
	private String appurlVT;

	private static String userNameChu;

	private static String passWordChu;

	private static String urlChu;



	private static String userNameCNTT;
	private static String passWordCNTT;
	private static String urlCNTT;
	
	
	private static String userNameVT;
	private static String passWordVT;
	private static String urlVT;
	
	private static String driverChu ="com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String driverPM ="com.microsoft.sqlserver.jdbc.SQLServerDriver";

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
