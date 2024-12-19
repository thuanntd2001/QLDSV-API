package com.qlsvtc.controller.khoa;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.qlsvtc.CNTT.repository.GiangVienRepositoryCNTT;
import com.qlsvtc.VT.repository.GiangVienRepositoryVT;
import com.qlsvtc.dao.impl.TaoLoginDAO;
import com.qlsvtc.entity.GiangVien;
import com.qlsvtc.model.NhanVienLoginModel;
import com.qlsvtc.model.para.TaoLoginModel;

@Controller
public class KhoaTaoLogin {
	TaoLoginDAO dao = new TaoLoginDAO();
	@Autowired
	GiangVienRepositoryCNTT cnrepo;
	@Autowired
	GiangVienRepositoryVT vtrepo;
	NhanVienLoginModel login = null;


	@GetMapping("taologin/khoa")
	public String gettaologin(Model model, HttpSession session) {
		List<GiangVien> lstGV;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			lstGV = vtrepo.findAll(); // Cast to the generic type
		} else {
			lstGV = cnrepo.findAll(); // Cast to the generic type
		}
		model.addAttribute("lstGV", lstGV);
		model.addAttribute("newLogin", new TaoLoginModel());
		return "khoa/taologin";
	}

	@PostMapping("taologin/khoa")
	public String posttaologin(HttpSession session, Model model,@ModelAttribute("newLogin") TaoLoginModel lg) {
		System.out.println(lg.getLgName());
		System.out.println(lg.getMaNV());
		System.out.println(lg.getPass());
		System.out.println(lg.getQuyen());
		try {
			int kq=dao.taologin(session, lg.getLgName(), lg.getPass(), lg.getMaNV(), "KHOA");
			System.out.println("thành công");
		
			model.addAttribute("message", "thêm login thành  công");
		

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "thêm login thất bại,bạn hãy kiểm tra lại nhân viên đã có account chưa ?");
		}

		return "khoa/taologin";
	}

}
