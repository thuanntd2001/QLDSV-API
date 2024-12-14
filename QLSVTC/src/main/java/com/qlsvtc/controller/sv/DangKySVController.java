package com.qlsvtc.controller.sv;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlsvtc.CNTT.repository.MonHocRepositoryCNTT;
import com.qlsvtc.dao.impl.DangKyDAO;
import com.qlsvtc.model.DangKyModel;

@Controller
@RequestMapping
public class DangKySVController {

	@Autowired
	MonHocRepositoryCNTT repocn;

	DangKyDAO dangky = new DangKyDAO();

	@GetMapping("/dangky/sv")
	public String get2dangky(HttpSession session, ModelMap model) {
		List<DangKyModel> lst = dangky.findAllLTC(session);
		model.addAttribute("lst", lst);
		return "sv/dangky";
	}

	@GetMapping("/dadangky/sv")
	public String getdadangky(HttpSession session, ModelMap model) {
		List<DangKyModel> lst = dangky.findAllDaDAngKy(session, (String) session.getAttribute("MASV"));
		model.addAttribute("lst", lst);
		return "sv/dadangky";
	}

	@PostMapping("/dangky/sv")
	public String postdangky(HttpSession session, ModelMap model) {
		return null;

	}
}