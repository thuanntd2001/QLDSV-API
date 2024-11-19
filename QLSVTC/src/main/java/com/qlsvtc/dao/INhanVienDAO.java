package com.qlsvtc.dao;

import com.qlsvtc.model.NhanVienLoginModel;



public interface INhanVienDAO {
	public NhanVienLoginModel login(String userName,String passWord);

}
