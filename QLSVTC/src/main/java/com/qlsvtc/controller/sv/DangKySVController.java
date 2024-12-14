package com.qlsvtc.controller.sv;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		model.addAttribute("para", new DangKyModel());
		return "sv/dangky";
	}

	@PostMapping("/dangky/sv")
	public String postdangky(HttpSession session, ModelMap model, @ModelAttribute DangKyModel dangKyModel) {
		String message="";
		List<DangKyModel> lst = dangky.findAllLTC(session);
		System.out.println("Mã LTC: " + dangKyModel.getMaLTC());
		System.out.println("Mã MH: " + dangKyModel.getMaMH());
		System.out.println("Tên MH: " + dangKyModel.getTenMH());
		System.out.println("Nhóm: " + dangKyModel.getNhom());
		dangky.DangKy(session, (String) session.getAttribute("MASV"), dangKyModel.getMaLTC());
		model.addAttribute("para", new DangKyModel());
		model.addAttribute("message", message);
		model.addAttribute("lst", lst);

		return "sv/dangky";
	}

	@GetMapping("/dadangky/sv")
	public String getdadangky(HttpSession session, ModelMap model) {
		List<DangKyModel> lst = dangky.findAllDaDAngKy(session, (String) session.getAttribute("MASV"));
		/*
		 * System.out.println((String) session.getAttribute("MASV"));
		 * System.out.println(lst.get(0).getMaMH());
		 */
		model.addAttribute("lst", lst);
		model.addAttribute("para", new DangKyModel());

		return "sv/dadangky";
	}
	@PostMapping("/dadangky/sv")
	public String getdadangky(HttpSession session, ModelMap model, @ModelAttribute DangKyModel dangKyModel) {
		String message="";

		List<DangKyModel> lst = dangky.findAllDaDAngKy(session, (String) session.getAttribute("MASV"));
		System.out.println("Mã LTC: " + dangKyModel.getMaLTC());
		System.out.println("Mã MH: " + dangKyModel.getMaMH());
		System.out.println("Tên MH: " + dangKyModel.getTenMH());
		System.out.println("Nhóm: " + dangKyModel.getNhom());
		dangky.HuyDangKy(session, (String) session.getAttribute("MASV"), dangKyModel.getMaLTC());
		model.addAttribute("para", new DangKyModel());
		model.addAttribute("message", message);
		model.addAttribute("lst", lst);
		model.addAttribute("lst", lst);

		return "sv/dadangky";
	}
	

}