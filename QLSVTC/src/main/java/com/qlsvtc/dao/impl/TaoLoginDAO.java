package com.qlsvtc.dao.impl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.model.para.TaoLoginModel;



@Repository
public class TaoLoginDAO extends AbstractDAO<TaoLoginModel> {

	public int taologin(HttpSession session,String tenLogin, String pasword, String username, String role){
		String sql = "EXEC SP_TAOLOGIN ?, ?, ?, ?";
		int kp =spPM(session,sql,tenLogin,pasword,username,role);
		
		return kp;
	}



}
