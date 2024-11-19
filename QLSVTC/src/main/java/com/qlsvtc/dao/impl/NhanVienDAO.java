package com.qlsvtc.dao.impl;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.qlsvtc.dao.INhanVienDAO;
import com.qlsvtc.mapper.NhanVienLoginMapper;
import com.qlsvtc.model.NhanVienLoginModel;
import com.qlsvtc.statics.InfoConnection;



@Repository
public class NhanVienDAO extends AbstractDAO<NhanVienLoginModel> implements INhanVienDAO{

	public NhanVienLoginModel login(String userName,String passWord){
		String sql = "EXEC	sp_DangNhap ?";
		List<NhanVienLoginModel> nv =queryPM(InfoConnection.getUserNamePM(),InfoConnection.getPassWordPM(),sql,new NhanVienLoginMapper(),userName);
		System.out.println(nv);
		return nv==null? null:nv.get(0);
	}



}
