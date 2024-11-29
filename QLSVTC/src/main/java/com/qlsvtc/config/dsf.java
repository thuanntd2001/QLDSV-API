package com.qlsvtc.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Configuration
public class dsf {

	@Value("${app.DBuserchu}")
	private  String userNameChu;
	@Value("${app.DBpasschu}")
	private  String passWordChu;
	@Value("${app.DBChuUrl}")
	private  String urlChu;
	@Value("${app.driver}")
	private  String driverChu;
	@Value("${app.driver}")
	private  String driverPM;
	// PARA
	 @PostConstruct
	    public void init() {
	        System.out.println("DB User: " + userNameChu);
	        System.out.println("DB Password: " + passWordChu);
	        System.out.println("DB URL: " + urlChu);
	       
	    }
}
