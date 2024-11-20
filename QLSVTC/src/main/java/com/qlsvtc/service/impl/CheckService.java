package com.qlsvtc.service.impl;

import javax.servlet.http.HttpSession;

import com.qlsvtc.dao.impl.AbstractDAO;

public class CheckService {

	
	public boolean ckUserPassword(HttpSession session) {
		// TODO Auto-generated method stub
		if (new AbstractDAO().getConnectionPM(session)==null)
		return false;
		else
			return true;
	}
	
}
