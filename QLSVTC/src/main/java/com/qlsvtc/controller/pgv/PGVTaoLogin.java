package com.qlsvtc.controller.pgv;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.qlsvtc.dao.impl.TaoLoginDAO;
import com.qlsvtc.model.para.TaoLoginModel;

@Controller
public class PGVTaoLogin {
	TaoLoginDAO dao = new TaoLoginDAO();

	@GetMapping("taologin/pgv")
	public String gettaologin(Model model) {
		model.addAttribute("newLogin", new TaoLoginModel());
		return "pgv/taologin";
	}

	@PostMapping("taologin/pgv")
	public String posttaologin(HttpSession session, Model model,@ModelAttribute("newLogin") TaoLoginModel lg) {
		System.out.println(lg.getLgName());
		System.out.println(lg.getMaNV());
		System.out.println(lg.getPass());
		System.out.println(lg.getQuyen());
		try {
			int kq=dao.taologin(session, lg.getLgName(), lg.getPass(), lg.getMaNV(), "PGV");
			System.out.println("thành công");
		
			model.addAttribute("message", "thêm login thành  công");
		

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message1", "thêm login thất bại,bạn hãy kiểm tra lại nhân viên đã có account chưa ?");
		}

		return "pgv/taologin";
	}

}
