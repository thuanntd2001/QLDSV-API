package com.qlsvtc.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
@Component
public class DSTS {
	@Value("${app.DBuserchu}")
	private  String appuserNameChu;
	@Value("${app.DBpasschu}")
	private  String apppassWordChu;
	@Value("${app.DBChuUrl}")
	private  String appurlChu;
	@Value("${app.driver}")
	private  String appdriverChu;
	@Value("${app.driver}")
	private  String appdriverPM;
	
	
	private static String userNameChu;
	
	private static String passWordChu;
	
	private static String urlChu;

	private static String driverChu;
	
	private static String driverPM;
	
    @PostConstruct
    public void init() {
    	userNameChu = appuserNameChu;
    	passWordChu = apppassWordChu;
    	urlChu = appurlChu;
    	driverChu = appdriverChu;
    	driverPM = appdriverPM;
    	
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

	


}
