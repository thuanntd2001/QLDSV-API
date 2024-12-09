package com.qlsvtc.controller.pgv;

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

import com.qlsvtc.CNTT.repository.NhanVienRepositoryCNTT;
import com.qlsvtc.VT.repository.NhanVienRepositoryVT;
import com.qlsvtc.entity.NhanVien;
import com.qlsvtc.model.NhanVienLoginModel;

@Controller
@RequestMapping(value = "quanly/pgv")
public class QLNhanVienController {
	@Autowired
	NhanVienRepositoryCNTT cnrepo;
	@Autowired
	NhanVienRepositoryVT vtrepo;

	NhanVienLoginModel login = null;

	@GetMapping("nhanvien")
	public <R extends JpaRepository<NhanVien, String>> String getVTCN(HttpServletRequest request,HttpSession session, ModelMap model) {
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
		return "pgv/qlnhanvien";
	}

	@GetMapping("nhanvien/add")
	public String addVTCN(ModelMap model,HttpServletRequest request) {
	
		String message = request.getParameter("message");
		model.addAttribute("message",message);
		NhanVien item = new NhanVien();
		model.addAttribute("item", item);
		
		
		return "pgv/form/nhanvien/fadd-nhanvien";
	}

	@PostMapping("nhanvien/add")
	public <R extends JpaRepository<NhanVien, String>> String addVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") NhanVien item) {
		String message="?message=";
		R repo;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
		} else {
			repo = (R) cnrepo; // Cast to the generic type
		}
		System.out.println(item.getMaNV());

		if (repo.findById(item.getMaNV()).isEmpty()) {
			NhanVien nvsave = null;

			try {
				nvsave = repo.save(item);
			} catch (Exception e) {
				e.printStackTrace();
				message=message+"thêm thất bại";
				model.addAttribute("message", "thêm thất bại");
				System.out.print("thêm vật tư thất bại");
			}
			if (nvsave != null) {
				message=message+"thêm thành công";
				model.addAttribute("message", "thêm thành công");
				System.out.print("thêm thành công");
			}
		} else {
			message=message+"thêm thất bại, đã tồn tại";
			model.addAttribute("message", "thêm thất bại, đã tồn tại");
			System.out.print("thêm thất bại đã tồn tại");
		}

		return "redirect:/quanly/pgv/nhanvien/add"+message;
	}

	@GetMapping(value = "nhanvien/edit")
	public <R extends JpaRepository<NhanVien, String>> String editVTCN1(HttpSession session, ModelMap model,
			HttpServletRequest request) {
		R repo;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
		} else {
			repo = (R) cnrepo; // Cast to the generic type
		}
		String id = request.getParameter("id");

		NhanVien item = repo.findById(id).get();
		if (item != null) {
			model.addAttribute("item", item);
			System.out.print("tồn tại item");
		}

		else {
			System.out.print("không tồn tại item");
			return "redirect:/quanly/pgv/nhanvien";
		}

		return "pgv/form/nhanvien/fedit-nhanvien";
	}

	@PostMapping("nhanvien/edit")
	public <R extends JpaRepository<NhanVien, String>> String editVTCN1(HttpSession session,ModelMap model, @ModelAttribute("item") NhanVien item, HttpServletRequest request) {
		String message="?message=";

		R repo;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
		} else {
			repo = (R) cnrepo; // Cast to the generic type
		}
		NhanVien vtsave = repo.findById(item.getMaNV()).get();

		// System.out.println(vtsave.getTenVT());

		try {
			vtsave.setMaNV(item.getMaNV());

			vtsave.setTenNV(item.getTenNV());

			repo.save(vtsave);

		} catch (Exception e) {
			e.printStackTrace();
			message+="Sửa thất bại";
			return "redirect:/quanly/pgv/nhanvien"+message;

		}

		if (vtsave != null) {
			message+="Sửa  thành công";
			model.addAttribute("message", "Sửa  thành công");
			System.out.print("Sửa thành công");
		}

		return "redirect:/quanly/pgv/nhanvien"+message;

	}

	@GetMapping(value = "nhanvien/xoa")
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "pgv/form/nhanvien/fxoa-nhanvien";

	}

	@RequestMapping(value = "nhanvien/xoa", method = RequestMethod.POST)
	public <R extends JpaRepository<NhanVien, String>> String xoaNVCN1P(HttpSession session,ModelMap model, HttpServletRequest request) {
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
				message+="xoá thành công";
				model.addAttribute("message", "xoá thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message+="xoá thất bại";
			model.addAttribute("message", "xoá thất bại");
		}
		return "redirect:/quanly/pgv/nhanvien"+message;

	}

	// ===================================CONGTY==================================//

	
	// ===================================USER==================================//

	

}
