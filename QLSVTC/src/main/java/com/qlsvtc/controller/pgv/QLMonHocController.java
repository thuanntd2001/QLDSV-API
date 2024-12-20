package com.qlsvtc.controller.pgv;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qlsvtc.CNTT.repository.MonHocRepositoryCNTT;
import com.qlsvtc.VT.repository.MonHocRepositoryVT;
import com.qlsvtc.entity.MonHoc;
import com.qlsvtc.model.NhanVienLoginModel;

@Controller
@RequestMapping(value = "quanly/pgv")
public class QLMonHocController {
	@Autowired
	MonHocRepositoryCNTT cnrepo;
	@Autowired
	MonHocRepositoryVT vtrepo;

	NhanVienLoginModel login = null;

	@GetMapping("monhoc")
	public <R extends JpaRepository<MonHoc, String>> String getVTCN(HttpServletRequest request,HttpSession session, ModelMap model) {
		/*
		 * Sort sort = new Sort(Sort.Direction.ASC, "maVT");;
		 * 
		 */
		String message = request.getParameter("message");
		model.addAttribute("message",message);
		R repo;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
		} else {
			repo = (R) cnrepo; // Cast to the generic type
		}
		
		
		model.addAttribute("lst", repo.findAll());
		return "pgv/qlmonhoc";
	}

	@GetMapping("monhoc/add")
	public String addVTCN(ModelMap model,HttpServletRequest request) {
	
		String message = request.getParameter("message");
		model.addAttribute("message",message);
		MonHoc item = new MonHoc();
		model.addAttribute("item", item);
		
		
		return "pgv/form/monhoc/fadd-monhoc";
	}

	@PostMapping("monhoc/add")
	public <R extends JpaRepository<MonHoc, String>> String addVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") MonHoc item) throws UnsupportedEncodingException {
		String message="?message=";
		R repo;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
		} else {
			repo = (R) cnrepo; // Cast to the generic type
		}
		System.out.println(item.getMaMH());

		if (repo.findById(item.getMaMH()).isEmpty()) {
			MonHoc nvsave = null;

			try {
				nvsave = repo.save(item);
			} catch (Exception e) {
				e.printStackTrace();
				message = message + URLEncoder.encode("insert failure: "+e.getMessage(), "UTF-8");
				model.addAttribute("message", "insert failure");
				System.out.print("insert vật tư failure");
			}
			if (nvsave != null) {
				message=message+"insert success";
				model.addAttribute("message", "insert success");
				System.out.print("insert success");
			}
		} else {
			message=message+"insert failure, existed";
			model.addAttribute("message", "insert failure, existed");
			System.out.print("insert failure existed");
		}

		return "redirect:/quanly/pgv/monhoc/add"+message;
	}

	@GetMapping(value = "monhoc/edit")
	public <R extends JpaRepository<MonHoc, String>> String editVTCN1(HttpSession session, ModelMap model,
			HttpServletRequest request) {
		R repo;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
		} else {
			repo = (R) cnrepo; // Cast to the generic type
		}
		String id = request.getParameter("id");

		MonHoc item = repo.findById(id).get();
		if (item != null) {
			model.addAttribute("item", item);
			System.out.print("tồn tại item");
		}

		else {
			System.out.print("không tồn tại item");
			return "redirect:/quanly/pgv/monhoc";
		}

		return "pgv/form/monhoc/fedit-monhoc";
	}

	@PostMapping("monhoc/edit")
	public <R extends JpaRepository<MonHoc, String>> String editVTCN1(HttpSession session,ModelMap model, @ModelAttribute("item") MonHoc item, HttpServletRequest request) {
		String message="?message=";

		R repo;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
		} else {
			repo = (R) cnrepo; // Cast to the generic type
		}
		MonHoc vtsave = repo.findById(item.getMaMH()).get();

		// System.out.println(vtsave.getTenVT());

		try {
			vtsave.setMaMH(item.getMaMH());
			vtsave.setSotietLT(item.getSotietLT());
			vtsave.setSotietTH(item.getSotietTH());
			vtsave.setTenMH(item.getTenMH());

			repo.save(vtsave);

		} catch (Exception e) {
			e.printStackTrace();
			message+="alter failure";
			return "redirect:/quanly/pgv/monhoc"+message;

		}

		if (vtsave != null) {
			message+="alter  success";
			model.addAttribute("message", "alter  success");
			System.out.print("alter success");
		}

		return "redirect:/quanly/pgv/monhoc"+message;

	}

	@GetMapping(value = "monhoc/xoa")
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "pgv/form/monhoc/fxoa-monhoc";

	}

	@RequestMapping(value = "monhoc/xoa", method = RequestMethod.POST)
	public <R extends JpaRepository<MonHoc, String>> String xoaNVCN1P(HttpSession session,ModelMap model, HttpServletRequest request) {
		String message="?message=";

		R repo;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
		} else {
			repo = (R) cnrepo; // Cast to the generic type
		}
		String id = request.getParameter("id");
		System.out.print(request.getParameter("xacNhan") + request.getParameter("id"));
		try {
			if (request.getParameter("xacNhan").equals("YES")) {
//				VatTuEntity nvsave = vtrepo.findOne(id);
				repo.deleteById(id);
				message+="delete success";
				model.addAttribute("message", "delete success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message+="delete failure";
			model.addAttribute("message", "delete failure");
		}
		return "redirect:/quanly/pgv/monhoc"+message;

	}

	// ===================================CONGTY==================================//

	
	// ===================================USER==================================//

	

}
