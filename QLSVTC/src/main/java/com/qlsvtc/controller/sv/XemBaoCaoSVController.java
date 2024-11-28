package com.qlsvtc.controller.sv;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlsvtc.dao.impl.BCPhieuDiemDAO;
import com.qlsvtc.model.NhanVienLoginModel;
import com.qlsvtc.model.baocao.BCPhieuDiem;
import com.qlsvtc.model.para.ParaPhieuDiem;

@Controller
@RequestMapping(value = "xembaocao")
public class XemBaoCaoSVController {

	
	BCPhieuDiemDAO bcphieudiem = new BCPhieuDiemDAO();
	

	
	@GetMapping("/bcphieudiem/sv")
	public String bcphieudiem(HttpSession session,ParaPhieuDiem para,ModelMap model) {
		if (!para.getMaSV().equals(((NhanVienLoginModel) session.getAttribute("USERMODEL")).getMaNV())){
			return null;
		}
		List<BCPhieuDiem> lst= bcphieudiem.findAll(session,para.getMaSV());
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/baocao/bcphieudiem";
	}
}