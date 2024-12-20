package com.qlsvtc.controller.pgv;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qlsvtc.CNTT.repository.NKHKRepositoryCNTT;
import com.qlsvtc.VT.repository.NKHKRepositoryVT;
import com.qlsvtc.entity.NKHK;
import com.qlsvtc.model.NhanVienLoginModel;

@Controller
@RequestMapping(value = "quanly/pgv")
public class QLNKHKController {
	@Autowired
	NKHKRepositoryCNTT cnrepo;
	@Autowired
	NKHKRepositoryVT vtrepo;

	NhanVienLoginModel login = null;
	ModelMapper modelMapper = new ModelMapper();
	@GetMapping("nkhk")
	public  String getVTCN(HttpServletRequest request,HttpSession session, ModelMap model) {
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");

		String message = request.getParameter("message");
		model.addAttribute("message",message);
		List<NKHK> lstEntity;

		if ("VT".equals(login.getKhoa())) {
			lstEntity = vtrepo.findAll(); // Cast to the generic type
		} else {
			lstEntity = cnrepo.findAll(); // Cast to the generic type
		}
		
		
		model.addAttribute("lst", lstEntity);
		return "pgv/qlnkhk";
	}

	@GetMapping("nkhk/add")
	public String addVTCN(ModelMap model,HttpServletRequest request) {
	
		String message = request.getParameter("message");
		model.addAttribute("message",message);
		NKHK item = new NKHK();
		model.addAttribute("item", item);
		
		
		return "pgv/form/nkhk/fadd-nkhk";
	}

	@PostMapping("nkhk/add")
	public <R extends JpaRepository<NKHK, Integer>> String addVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") NKHK item) {
		String message="?message=";
		R repo;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
		} else {
			repo = (R) cnrepo; // Cast to the generic type
		}
		System.out.println(item.getMaNKHK());

		if (repo.findById(item.getMaNKHK()).isEmpty()) {
			NKHK nvsave = null;
			item.setMaNV(login.getMaNV());

			try {
				nvsave = repo.save(item);
			} catch (Exception e) {
				e.printStackTrace();
				message=message+"insert failure";
				model.addAttribute("message", "insert failure");
				System.out.print("insert vật tư failure");
			}
			if (nvsave != null) {
				message=message+"insert success";
				model.addAttribute("message", "insert success");
				System.out.print("insert success");
			}
		} else {
			message=message+"insert failure, đã tồn tại";
			model.addAttribute("message", "insert failure, đã tồn tại");
			System.out.print("insert failure đã tồn tại");
		}

		return "redirect:/quanly/pgv/nkhk/add"+message;
	}

	@GetMapping(value = "nkhk/edit")
	public <R extends JpaRepository<NKHK, Integer>> String editVTCN1(HttpSession session, ModelMap model,
			HttpServletRequest request) {
		R repo;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
		} else {
			repo = (R) cnrepo; // Cast to the generic type
		}
		int id =Integer.parseInt(request.getParameter("id")) ;

		NKHK item = repo.findById(id).get();
		if (item != null) {
			model.addAttribute("item", item);
			System.out.print("tồn tại item");
		}

		else {
			System.out.print("không tồn tại item");
			return "redirect:/quanly/pgv/nkhk";
		}

		return "pgv/form/nkhk/fedit-nkhk";
	}

	@PostMapping("nkhk/edit")
	public <R extends JpaRepository<NKHK, Integer>> String editVTCN1(HttpSession session,ModelMap model, @ModelAttribute("item") NKHK item, HttpServletRequest request) {
		String message="?message=";

		R repo;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
		} else {
			repo = (R) cnrepo; // Cast to the generic type
		}
		NKHK vtsave = repo.findById(item.getMaNKHK()).get();

		// System.out.println(vtsave.getTenVT());

		try {
			vtsave = modelMapper.map(item, NKHK.class);
			vtsave.setMaNV(login.getMaNV());

			repo.save(vtsave);

		} catch (Exception e) {
			e.printStackTrace();
			message+="alter failure";
			return "redirect:/quanly/pgv/nkhk"+message;

		}

		if (vtsave != null) {
			message+="alter  success";
			model.addAttribute("message", "alter  success");
			System.out.print("alter success");
		}

		return "redirect:/quanly/pgv/nkhk"+message;

	}

	@GetMapping(value = "nkhk/xoa")
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "pgv/form/nkhk/fxoa-nkhk";

	}

	@RequestMapping(value = "nkhk/xoa", method = RequestMethod.POST)
	public <R extends JpaRepository<NKHK, Integer>> String xoaNVCN1P(HttpSession session,ModelMap model, HttpServletRequest request) {
		String message="?message=";

		R repo;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
		} else {
			repo = (R) cnrepo; // Cast to the generic type
		}
		int id = Integer.parseInt(request.getParameter("id"));
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
		return "redirect:/quanly/pgv/nkhk"+message;

	}

	// ===================================CONGTY==================================//

	
	// ===================================USER==================================//

	

}
