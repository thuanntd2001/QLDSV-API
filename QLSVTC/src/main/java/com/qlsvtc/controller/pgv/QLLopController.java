package com.qlsvtc.controller.pgv;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.modelmapper.ModelMapper;

import com.qlsvtc.CNTT.repository.LopRepositoryCNTT;
import com.qlsvtc.CNTT.repository.KhoaRepositoryCNTT;
import com.qlsvtc.DTO.LopDTO;
import com.qlsvtc.VT.repository.LopRepositoryVT;
import com.qlsvtc.VT.repository.KhoaRepositoryVT;
import com.qlsvtc.entity.Lop;
import com.qlsvtc.entity.Khoa;
import com.qlsvtc.model.NhanVienLoginModel;

@Controller
@RequestMapping(value = "quanly/pgv")
public class QLLopController {
	@Autowired
	LopRepositoryCNTT cnrepo;
	@Autowired
	LopRepositoryVT vtrepo;

	@Autowired
	KhoaRepositoryCNTT cnrepokhoa;
	@Autowired
	KhoaRepositoryVT vtrepokhoa;

	NhanVienLoginModel login = null;
	ModelMapper modelMapper = new ModelMapper();

	@GetMapping("lop")
	public String getVTCN(HttpServletRequest request, HttpSession session, ModelMap model) {
		/*
		 * Sort sort = new Sort(Sort.Direction.ASC, "maVT");;
		 * 
		 */
		String message = request.getParameter("message");
		model.addAttribute("message", message);
		List<Lop> lstEntity;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			lstEntity = vtrepo.findAll(); // Cast to the generic type
		} else {
			lstEntity = cnrepo.findAll(); // Cast to the generic type
		}
		List<LopDTO> lst = new ArrayList<LopDTO>();
		for (Lop item : lstEntity) {
			LopDTO dto = modelMapper.map(item, LopDTO.class);
			lst.add(dto);
		}

		model.addAttribute("lst", lst);
		return "pgv/qllop";
	}

	@GetMapping("lop/add")
	public String addVTCN(ModelMap model, HttpServletRequest request) {

		String message = request.getParameter("message");
		model.addAttribute("message", message);
		LopDTO item = new LopDTO();
		model.addAttribute("item", item);

		return "pgv/form/lop/fadd-lop";
	}

	@PostMapping("lop/add")
	public <R extends JpaRepository<Lop, String>> String addVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") LopDTO item) throws UnsupportedEncodingException {
		String message = "?message=";
		R repo;
		Khoa khoa;
		Optional<Lop> lop2;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
			khoa = vtrepokhoa.findAll().get(0);
			lop2=cnrepo.findById(item.getMaLop());
		} else {
			repo = (R) cnrepo; // Cast to the generic type
			khoa = cnrepokhoa.findAll().get(0);
			lop2=vtrepo.findById(item.getMaLop());

		}
		System.out.println(item.getMaLop());

		if (repo.findById(item.getMaLop()).isEmpty() && lop2.isEmpty()) {
			Lop itemsave = modelMapper.map(item, Lop.class);
			itemsave.setKhoa(khoa);
			
			Lop nvsave = null;

			try {
				nvsave = repo.save(itemsave);
			} catch (Exception e) {
				e.printStackTrace();
			
				message = message + URLEncoder.encode("insert failure: "+e.getMessage(), "UTF-8");

				model.addAttribute("message", "insert failure");
				System.out.print("insert failure");
			}
			if (nvsave != null) {
				message = message + "insert success";
				model.addAttribute("message", "insert success");
				System.out.print("insert success");
			}
		} else {
			message = message + "insert failure, existed";
			model.addAttribute("message", "insert failure, existed");
			System.out.print("insert failure existed");
		}

		return "redirect:/quanly/pgv/lop/add" + message;
	}

	@GetMapping(value = "lop/edit")
	public String editVTCN1(HttpSession session, ModelMap model, HttpServletRequest request) {
		Lop itemEntity;
		String id = request.getParameter("id");
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			itemEntity = vtrepo.findById(id).get(); // Cast to the generic type
		} else {
			itemEntity = cnrepo.findById(id).get(); // Cast to the generic type
		}

		if (itemEntity != null) {
			LopDTO item = modelMapper.map(itemEntity, LopDTO.class);
			model.addAttribute("item", item);
			System.out.print("tồn tại item");
		}

		else {
			System.out.print("không tồn tại item");
			return "redirect:/quanly/pgv/lop";
		}

		return "pgv/form/lop/fedit-lop";
	}

	@PostMapping("lop/edit")
	public <R extends JpaRepository<Lop, String>> String editVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") LopDTO item, HttpServletRequest request) {
		String message = "?message=";

		R repo;
		Khoa khoa;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
			khoa = vtrepokhoa.findAll().get(0);
		} else {
			repo = (R) cnrepo; // Cast to the generic type
			khoa = cnrepokhoa.findAll().get(0);
		}

		// System.out.println(vtsave.getTenVT());
		Lop itemsave = modelMapper.map(item, Lop.class);
		itemsave.setKhoa(khoa);
		System.out.println(khoa.getMaKhoa());
		try {

			repo.save(itemsave);

		} catch (Exception e) {
			e.printStackTrace();
			message += "alter failure có thể ban dang sua du lieu cua khoa khac";
			return "redirect:/quanly/pgv/lop" + message;

		}

		message += "alter  success";
		model.addAttribute("message", "alter  success");
		System.out.print("alter success");

		return "redirect:/quanly/pgv/lop" + message;

	}

	@GetMapping(value = "lop/xoa")
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "pgv/form/lop/fxoa-lop";

	}

	@RequestMapping(value = "lop/xoa", method = RequestMethod.POST)
	public <R extends JpaRepository<Lop, String>> String xoaNVCN1P(HttpSession session, ModelMap model,
			HttpServletRequest request) {
		String message = "?message=";

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
				message += "delete success";
				model.addAttribute("message", "delete success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message += "delete failure";
			model.addAttribute("message", "delete failure");
		}
		return "redirect:/quanly/pgv/lop" + message;

	}

	// ===================================CONGTY==================================//

	// ===================================USER==================================//

}
