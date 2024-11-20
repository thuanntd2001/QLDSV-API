package com.qlsvtc.dao.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.qlsvtc.mapper.NhanVienLoginMapper;
import com.qlsvtc.model.NhanVienLoginModel;



@Repository
public class NhanVienDAO extends AbstractDAO<NhanVienLoginModel>{

	public NhanVienLoginModel login(HttpSession session){
		String sql = "EXEC	sp_DangNhap ?";
		List<NhanVienLoginModel> nv =queryPM(session,sql,new NhanVienLoginMapper(),session.getAttribute("username"));
		System.out.println(nv);
		return nv==null? null:nv.get(0);
	}



}
