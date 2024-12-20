package com.qlsvtc.controller.pgv;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.qlsvtc.CNTT.repository.NhanVienRepositoryCNTT;
import com.qlsvtc.VT.repository.NhanVienRepositoryVT;
import com.qlsvtc.dao.impl.TaoLoginDAO;
import com.qlsvtc.entity.NhanVien;
import com.qlsvtc.model.NhanVienLoginModel;
import com.qlsvtc.model.para.TaoLoginModel;

@Controller
public class PGVTaoLogin {
	TaoLoginDAO dao = new TaoLoginDAO();
	@Autowired
	NhanVienRepositoryCNTT cnrepo;
	@Autowired
	NhanVienRepositoryVT vtrepo;
	NhanVienLoginModel login = null;

	@GetMapping("taologin/pgv")
	public String gettaologin(Model model, HttpSession session) {
		List<NhanVien> lstNV;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			lstNV = vtrepo.findAll(); // Cast to the generic type
		} else {
			lstNV = cnrepo.findAll(); // Cast to the generic type
		}
		model.addAttribute("lstNV", lstNV);
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
		
			model.addAttribute("message", "Success");
		

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "error:"+e.getMessage()+" thêm login thất bại,bạn hãy kiểm tra lại Giảng viên đã có account chưa ?");
		}

		return "pgv/taologin";
	}

}
