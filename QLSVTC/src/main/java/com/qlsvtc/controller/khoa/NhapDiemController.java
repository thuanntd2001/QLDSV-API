package com.qlsvtc.controller.khoa;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlsvtc.CNTT.repository.MonHocRepositoryCNTT;
import com.qlsvtc.dao.impl.NhapDiemDAO;
import com.qlsvtc.model.NhapDiemModel;

import com.qlsvtc.model.para.ParaNhapDiem;


@Controller
@RequestMapping(value = "nhapdiem")
public class NhapDiemController {
	
	@Autowired
	MonHocRepositoryCNTT repocn;

	NhapDiemDAO nhapdiem = new NhapDiemDAO();

	
	@GetMapping("/formnhapdiem/khoa")
	public String getnhapdiem(ModelMap model) {
		ParaNhapDiem para = new ParaNhapDiem();
		model.addAttribute("para", para);
		model.addAttribute("lstMH", repocn.findAll());
		return "khoa/form/fformnhapdiem";
	}
	
	@PostMapping("/formnhapdiem/khoa")
	public String nhapdiem(HttpSession session,ParaNhapDiem para,ModelMap model) {
		List<NhapDiemModel> lst= nhapdiem.findAll(session, para.getNk(),para.getHk(),para.getMaMH(),para.getNhom());
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/nhapdiem/formnhapdiem";
	}
	

}