package com.qlsvtc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qlsvtc.dao.impl.BCBangDiemMonHocLTCDAO;
import com.qlsvtc.dao.impl.BCDsLTCDAO;
import com.qlsvtc.dao.impl.BCDssvDkLTCDAO;
import com.qlsvtc.dao.impl.BCPhieuDiemDAO;
import com.qlsvtc.model.baocao.BCBangDiemMonHocLTC;
import com.qlsvtc.model.baocao.BCDsLTC;
import com.qlsvtc.model.baocao.BCDssvDkLTC;
import com.qlsvtc.model.baocao.BCPhieuDiem;
import com.qlsvtc.model.para.ParaBCBangDiemMonHocLTC;
import com.qlsvtc.model.para.ParaBCDsLTC;
import com.qlsvtc.model.para.ParaPhieuDiem;

@Controller
@RequestMapping(value = "xembaocao")
public class XemBaoCaoController {

//==============================khoa============================//
	@RequestMapping(value = { "khoa"}, method = RequestMethod.GET)
	public String bckhoa(ModelMap model) {
	
		return "khoa/Xembaocao";
	}

	// ==============================pgv============================//
	@RequestMapping(value = { "pgv"}, method = RequestMethod.GET)
	public String bcpgv(ModelMap model) {
		return "pgv/Xembaocao";
	}
	@RequestMapping(value = { "sv"}, method = RequestMethod.GET)
	public String bcsv(ModelMap model) {
		return "sv/Xembaocao";
	}

	BCBangDiemMonHocLTCDAO bcbangdiemmonhocltc = new BCBangDiemMonHocLTCDAO();
	BCDsLTCDAO bcdsltc = new BCDsLTCDAO();
	BCDssvDkLTCDAO bcdssvdkltc = new BCDssvDkLTCDAO();
	BCPhieuDiemDAO bcphieudiem = new BCPhieuDiemDAO();
	
	@GetMapping("/bcbangdiemmonhocltc/khoa")
	public String bcbangdiemmonhocltc(HttpSession session,ParaBCBangDiemMonHocLTC para,ModelMap model) {
		List<BCBangDiemMonHocLTC> lst= bcbangdiemmonhocltc.findAll(session, para.getNk(),para.getHk(),para.getMaMH(),para.getNhom());
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/baocao/bcbangdiemmonhocltc";
	}
	
	@GetMapping("/bcdsltc")
	public List<BCDsLTC> bcdsltc(HttpSession session,ParaBCDsLTC para) {
		// tao dspm set ra view
		return bcdsltc.findAll(session,para.getNk(),para.getHk());
	}
	
	@GetMapping("/bcdssvdkltc")
	public List<BCDssvDkLTC> bcdssvdkltc(HttpSession session,ParaBCBangDiemMonHocLTC para) {
		// tao dspm set ra view
		return bcdssvdkltc.findAll(session,para.getNk(),para.getHk(),para.getMaMH(),para.getNhom());
	}
	
	@GetMapping("/bcphieudiem")
	public List<BCPhieuDiem> bcphieudiem(HttpSession session,ParaPhieuDiem para) {
		// tao dspm set ra view
		return bcphieudiem.findAll(session,para.getMaSV());
	}
}