package com.qlsvtc.controller.pgv;

import java.util.ArrayList;
import java.util.List;

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

import com.qlsvtc.CNTT.repository.LopTinChiRepositoryCNTT;
import com.qlsvtc.CNTT.repository.NKHKRepositoryCNTT;
import com.qlsvtc.CNTT.repository.KhoaRepositoryCNTT;
import com.qlsvtc.DTO.LopTinChiDTO;
import com.qlsvtc.VT.repository.LopTinChiRepositoryVT;
import com.qlsvtc.VT.repository.NKHKRepositoryVT;
import com.qlsvtc.VT.repository.KhoaRepositoryVT;
import com.qlsvtc.entity.LopTinChi;
import com.qlsvtc.entity.Khoa;
import com.qlsvtc.model.NhanVienLoginModel;

@Controller
@RequestMapping(value = "quanly/pgv")
public class QLLopTinChiController {
	@Autowired
	LopTinChiRepositoryCNTT cnrepo;
	@Autowired
	LopTinChiRepositoryVT vtrepo;

	@Autowired
	KhoaRepositoryCNTT cnrepokhoa;
	@Autowired
	KhoaRepositoryVT vtrepokhoa;
	@Autowired
	NKHKRepositoryCNTT cnreponkhk;
	@Autowired
	NKHKRepositoryVT vtreponkhk;

	NhanVienLoginModel login = null;
	ModelMapper modelMapper = new ModelMapper();

	@GetMapping("ltc")
	public String getVTCN(HttpServletRequest request, HttpSession session, ModelMap model) {
		/*
		 * Sort sort = new Sort(Sort.Direction.ASC, "maVT");;
		 * 
		 */
		int maNKHK = Integer.parseInt(request.getParameter("idnkhk"));
		String message = request.getParameter("message");
		model.addAttribute("message", message);
		List<LopTinChi> lstEntity;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			lstEntity = vtrepo.findAllByMaNKHK(maNKHK); // Cast to the generic type
		} else {
			lstEntity = cnrepo.findAllByMaNKHK(maNKHK); // Cast to the generic type
		}
		List<LopTinChiDTO> lst = new ArrayList<LopTinChiDTO>();
		for (LopTinChi item : lstEntity) {
			LopTinChiDTO dto = modelMapper.map(item, LopTinChiDTO.class);
			lst.add(dto);
		}

		model.addAttribute("lst", lst);
		return "pgv/qlltc";
	}

	@GetMapping("ltc/add")
	public String addVTCN(ModelMap model, HttpServletRequest request) {

		String message = request.getParameter("message");
		model.addAttribute("message", message);
		LopTinChiDTO item = new LopTinChiDTO();
		model.addAttribute("item", item);

		return "pgv/form/ltc/fadd-ltc";
	}

	@PostMapping("ltc/add")
	public <R extends JpaRepository<LopTinChi, Integer>> String addVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") LopTinChiDTO item) {
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
		System.out.println(item.getMaLTC());

		if (repo.findById(item.getMaLTC()).isEmpty()) {
			LopTinChi itemsave = modelMapper.map(item, LopTinChi.class);
			itemsave.setKhoa(khoa);
			
			LopTinChi nvsave = null;

			try {
				nvsave = repo.save(itemsave);
			} catch (Exception e) {
				e.printStackTrace();
				message = message + "thêm thất bại";
				model.addAttribute("message", "thêm thất bại");
				System.out.print("thêm thất bại");
			}
			if (nvsave != null) {
				message = message + "thêm thành công";
				model.addAttribute("message", "thêm thành công");
				System.out.print("thêm thành công");
			}
		} else {
			message = message + "thêm thất bại, đã tồn tại";
			model.addAttribute("message", "thêm thất bại, đã tồn tại");
			System.out.print("thêm thất bại đã tồn tại");
		}

		return "redirect:/quanly/pgv/ltc/add" + message;
	}

	@GetMapping(value = "ltc/edit")
	public String editVTCN1(HttpSession session, ModelMap model, HttpServletRequest request) {
		LopTinChi itemEntity;
		int id = Integer.parseInt(request.getParameter("id"));
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			itemEntity = vtrepo.findById(id).get(); // Cast to the generic type
		} else {
			itemEntity = cnrepo.findById(id).get(); // Cast to the generic type
		}

		if (itemEntity != null) {
			LopTinChiDTO item = modelMapper.map(itemEntity, LopTinChiDTO.class);
			model.addAttribute("item", item);
			System.out.print("tồn tại item");
		}

		else {
			System.out.print("không tồn tại item");
			return "redirect:/quanly/pgv/ltc";
		}

		return "pgv/form/ltc/fedit-ltc";
	}

	@PostMapping("ltc/edit")
	public <R extends JpaRepository<LopTinChi, Integer>> String editVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") LopTinChiDTO item, HttpServletRequest request) {
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
		LopTinChi itemsave = modelMapper.map(item, LopTinChi.class);
		itemsave.setKhoa(khoa);
		System.out.println(khoa.getMaKhoa());
		try {

			repo.save(itemsave);

		} catch (Exception e) {
			e.printStackTrace();
			message += "Sửa thất bại có thể ban dang sua du lieu cua khoa khac";
			return "redirect:/quanly/pgv/ltc" + message;

		}

		message += "Sửa  thành công";
		model.addAttribute("message", "Sửa  thành công");
		System.out.print("Sửa thành công");

		return "redirect:/quanly/pgv/ltc" + message;

	}

	@GetMapping(value = "ltc/xoa")
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "pgv/form/ltc/fxoa-ltc";

	}

	@RequestMapping(value = "ltc/xoa", method = RequestMethod.POST)
	public <R extends JpaRepository<LopTinChi, Integer>> String xoaNVCN1P(HttpSession session, ModelMap model,
			HttpServletRequest request) {
		String message = "?message=";

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
				message += "xoá thành công";
				model.addAttribute("message", "xoá thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message += "xoá thất bại";
			model.addAttribute("message", "xoá thất bại");
		}
		return "redirect:/quanly/pgv/ltc" + message;

	}

	// ===================================CONGTY==================================//

	// ===================================USER==================================//

}
