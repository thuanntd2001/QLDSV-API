package com.qlsvtc.controller.khoa;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class XemBaoCaoKhoaController {

	BCBangDiemMonHocLTCDAO bcbangdiemmonhocltc = new BCBangDiemMonHocLTCDAO();
	BCDsLTCDAO bcdsltc = new BCDsLTCDAO();
	BCDssvDkLTCDAO bcdssvdkltc = new BCDssvDkLTCDAO();
	BCPhieuDiemDAO bcphieudiem = new BCPhieuDiemDAO();
	
	@GetMapping("/bcbangdiemmonhocltc/khoa")
	public String getbcbangdiemmonhocltc(ModelMap model) {
		ParaBCBangDiemMonHocLTC para = new ParaBCBangDiemMonHocLTC();
		model.addAttribute("para", para);
		return "khoa/form/fbcbangdiemmonhocltc";
	}
	
	@PostMapping("/bcbangdiemmonhocltc/khoa")
	public String bcbangdiemmonhocltc(HttpSession session,ParaBCBangDiemMonHocLTC para,ModelMap model) {
		List<BCBangDiemMonHocLTC> lst= bcbangdiemmonhocltc.findAll(session, para.getNk(),para.getHk(),para.getMaMH(),para.getNhom());
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/baocao/bcbangdiemmonhocltc";
	}
	
	@GetMapping("/bcdsltc/khoa")
	public String getbcdsltc(ModelMap model) {
		ParaBCDsLTC para = new ParaBCDsLTC();
		model.addAttribute("para", para);
		return "khoa/form/fbcdsltc";
	}
	
	@PostMapping("/bcdsltc/khoa")
	public String bcdsltc(HttpSession session,ParaBCDsLTC para,ModelMap model) {
		List<BCDsLTC> lst= bcdsltc.findAll(session, para.getNk(),para.getHk());		
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/baocao/bcdsltc";
	}
	
	@GetMapping("/bcdssvdkltc/khoa")
	public String getbcdssvdkltc(ModelMap model) {
		ParaBCBangDiemMonHocLTC para = new ParaBCBangDiemMonHocLTC();
		model.addAttribute("para", para);
		return "khoa/form/fbcdssvdkltc";
	}
	@PostMapping("/bcdssvdkltc/khoa")
	public String bcdssvdkltc(HttpSession session,ParaBCBangDiemMonHocLTC para,ModelMap model) {
		List<BCDssvDkLTC> lst=  bcdssvdkltc.findAll(session,para.getNk(),para.getHk(),para.getMaMH(),para.getNhom());
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/baocao/bcdssvdkltc";
	}
	
	@GetMapping("/bcphieudiem/khoa")
	public String getbcphieudiem(ModelMap model) {
		ParaPhieuDiem para = new ParaPhieuDiem();
		model.addAttribute("para", para);
		return "khoa/form/fbcphieudiem";
		}
	
	@PostMapping("/bcphieudiem/khoa")
	public String bcphieudiem(HttpSession session,ParaPhieuDiem para,ModelMap model) {
		List<BCPhieuDiem> lst= bcphieudiem.findAll(session,para.getMaSV());
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/baocao/bcphieudiem";
	}
}