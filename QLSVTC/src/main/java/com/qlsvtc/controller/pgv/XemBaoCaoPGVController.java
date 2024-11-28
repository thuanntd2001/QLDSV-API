package com.qlsvtc.controller.pgv;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class XemBaoCaoPGVController {

	BCBangDiemMonHocLTCDAO bcbangdiemmonhocltc = new BCBangDiemMonHocLTCDAO();
	BCDsLTCDAO bcdsltc = new BCDsLTCDAO();
	BCDssvDkLTCDAO bcdssvdkltc = new BCDssvDkLTCDAO();
	BCPhieuDiemDAO bcphieudiem = new BCPhieuDiemDAO();
	
	@GetMapping("/bcbangdiemmonhocltc/pgv")
	public String getbcbangdiemmonhocltc(ModelMap model) {
		ParaBCBangDiemMonHocLTC para = new ParaBCBangDiemMonHocLTC();
		model.addAttribute("para", para);
		return "khoa/form/fbcbangdiemmonhocltc";
	}
	
	@PostMapping("/bcbangdiemmonhocltc/pgv")
	public String bcbangdiemmonhocltc(HttpSession session,ParaBCBangDiemMonHocLTC para,ModelMap model) {
		List<BCBangDiemMonHocLTC> lst= bcbangdiemmonhocltc.findAll(session, para.getNk(),para.getHk(),para.getMaMH(),para.getNhom());
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/baocao/bcbangdiemmonhocltc";
	}
	
	@GetMapping("/bcdsltc/pgv")
	public String getbcdsltc(ModelMap model) {
		ParaBCDsLTC para = new ParaBCDsLTC();
		model.addAttribute("para", para);
		return "khoa/form/fbcdsltc";
	}
	
	@PostMapping("/bcdsltc/pgv")
	public String bcdsltc(HttpSession session,ParaBCDsLTC para,ModelMap model) {
		List<BCDsLTC> lst= bcdsltc.findAll(session, para.getNk(),para.getHk());		
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/baocao/bcdsltc";
	}
	
	@GetMapping("/bcdssvdkltc/pgv")
	public String getbcdssvdkltc(ModelMap model) {
		ParaBCBangDiemMonHocLTC para = new ParaBCBangDiemMonHocLTC();
		model.addAttribute("para", para);
		return "khoa/form/fbcdssvdkltc";
	}
	@PostMapping("/bcdssvdkltc/pgv")
	public String bcdssvdkltc(HttpSession session,ParaBCBangDiemMonHocLTC para,ModelMap model) {
		List<BCDssvDkLTC> lst=  bcdssvdkltc.findAll(session,para.getNk(),para.getHk(),para.getMaMH(),para.getNhom());
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/baocao/bcdssvdkltc";
	}
	
	@GetMapping("/bcphieudiem/pgv")
	public String getbcphieudiem(ModelMap model) {
		ParaPhieuDiem para = new ParaPhieuDiem();
		model.addAttribute("para", para);
		return "khoa/form/fbcphieudiem";
		}
	
	@PostMapping("/bcphieudiem/pgv")
	public String bcphieudiem(HttpSession session,ParaPhieuDiem para,ModelMap model) {
		List<BCPhieuDiem> lst= bcphieudiem.findAll(session,para.getMaSV());
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/baocao/bcphieudiem";
	}
}