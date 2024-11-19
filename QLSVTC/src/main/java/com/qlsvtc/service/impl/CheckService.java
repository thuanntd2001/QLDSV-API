package com.qlsvtc.service.impl;

import com.qlsvtc.dao.impl.AbstractDAO;
import com.qlsvtc.service.ICheckService;

public class CheckService implements ICheckService{

	@Override
	public boolean ckUserPassword(String user, String pass) {
		// TODO Auto-generated method stub
		if (new AbstractDAO().getConnectionPM(user, pass)==null)
		return false;
		else
			return true;
	}
	
}
