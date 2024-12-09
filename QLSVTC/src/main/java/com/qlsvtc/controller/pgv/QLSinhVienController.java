package com.qlsvtc.controller.pgv;

import java.util.ArrayList;
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

import com.qlsvtc.CNTT.repository.ChuyenNganhRepositoryCNTT;
import com.qlsvtc.CNTT.repository.KhoaRepositoryCNTT;
import com.qlsvtc.CNTT.repository.LopRepositoryCNTT;
import com.qlsvtc.CNTT.repository.SinhVienRepositoryCNTT;
import com.qlsvtc.DTO.SinhVienDTO;
import com.qlsvtc.VT.repository.ChuyenNganhRepositoryVT;
import com.qlsvtc.VT.repository.KhoaRepositoryVT;
import com.qlsvtc.VT.repository.LopRepositoryVT;
import com.qlsvtc.VT.repository.SinhVienRepositoryVT;
import com.qlsvtc.dao.impl.SinhVienDAO;
import com.qlsvtc.entity.ChuyenNganh;
import com.qlsvtc.entity.Khoa;
import com.qlsvtc.entity.Lop;
import com.qlsvtc.entity.SinhVien;
import com.qlsvtc.model.NhanVienLoginModel;

@Controller
@RequestMapping(value = "quanly/pgv")
public class QLSinhVienController {
	@Autowired
	SinhVienRepositoryCNTT cnrepo;
	@Autowired
	SinhVienRepositoryVT vtrepo;
	SinhVienDAO dao = new SinhVienDAO();

	@Autowired
	ChuyenNganhRepositoryCNTT cnrepochuyennganh;
	@Autowired
	ChuyenNganhRepositoryVT vtrepochuyennganh;


	@Autowired
	KhoaRepositoryCNTT cnrepokhoa;
	@Autowired
	KhoaRepositoryVT vtrepokhoa;
	@Autowired
	LopRepositoryCNTT cnrepolop;
	@Autowired
	LopRepositoryVT vtrepolop;

	NhanVienLoginModel login = null;
	ModelMapper modelMapper = new ModelMapper();
	String idLop;

	@GetMapping("sinhvien")
	public String getVTCN(HttpServletRequest request, HttpSession session, ModelMap model) {
	
		idLop = request.getParameter("idlop");
		List<ChuyenNganh> lstCN;
		session.setAttribute("MALOP", request.getParameter("idlop"));
		String message = request.getParameter("message");
		model.addAttribute("message", message);
		List<SinhVien> lstEntity;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			lstEntity = vtrepo.findAllByLop_MaLopAndDaNghiHoc(idLop,false); 
			lstCN = vtrepochuyennganh.findAll();
		} else {
			lstEntity = cnrepo.findAllByLop_MaLopAndDaNghiHoc(idLop,false); 
			lstCN = cnrepochuyennganh.findAll();

		}
		List<SinhVienDTO> lst = new ArrayList<SinhVienDTO>();
		for (SinhVien item : lstEntity) {
			SinhVienDTO dto = modelMapper.map(item, SinhVienDTO.class);
			dto.setTenCN(dto.getMaCN()+" - "+timTenChuyenNganhTheoMa(lstCN,dto.getMaCN()));
			lst.add(dto);
		}

		model.addAttribute("lst", lst);
		return "pgv/qlsinhvien";
	}

	@GetMapping("sinhvien/add")
	public String addVTCN(ModelMap model, HttpServletRequest request) {
		List<ChuyenNganh> lstCN;
		if ("VT".equals(login.getKhoa())) {
			lstCN = vtrepochuyennganh.findAll();
		} else {
			lstCN = cnrepochuyennganh.findAll();

		}
		model.addAttribute("lstCN", lstCN);
		String message = request.getParameter("message");
		model.addAttribute("message", message);
		SinhVienDTO item = new SinhVienDTO();
		model.addAttribute("item", item);



		return "pgv/form/sinhvien/fadd-sinhvien";
	}

	/*@PostMapping("sinhvien/add")
	public <R extends JpaRepository<SinhVien, String>> String addVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") SinhVienDTO item) {
		String message = "?message=";
		R repo;
		Khoa khoa;
		Lop lop;
		ChuyenNganh chuyenNganh;

		String idlop =(String)session.getAttribute("MALOP");
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
			khoa = vtrepokhoa.findAll().get(0);
			lop = vtrepolop.findById(idlop).get();
			chuyenNganh = vtrepochuyennganh.findById(item.getMaCN()).get();


		} else {
			repo = (R) cnrepo; // Cast to the generic type
			khoa = cnrepokhoa.findAll().get(0);
			lop = cnrepolop.findById((String)session.getAttribute("MALOP")).get();
			chuyenNganh = cnrepochuyennganh.findById(item.getMaCN()).get();

		}
		System.out.println(item.getMaSV());

		if (item.getMaSV()== null || repo.findById(item.getMaSV()).isEmpty()) {
			SinhVien itemsave = modelMapper.map(item, SinhVien.class);
			itemsave.setCN(chuyenNganh);
			itemsave.setLop(lop);
			itemsave.setDaNghiHoc(false);
			SinhVien nvsave = null;

			try {
				nvsave = repo.save(itemsave);
			} catch (Exception e) {
				e.printStackTrace();
				message = message + "thêm thất bại";
				model.addAttribute("message", "thêm thất bại");
				System.out.println("thêm thất bại");
			}
			if (nvsave != null) {
				message = message + "thêm thành công";
				model.addAttribute("message", "thêm thành công");
				System.out.println("thêm thành công");
			}
		} else {
			message = message + "thêm thất bại, đã tồn tại";
			model.addAttribute("message", "thêm thất bại, đã tồn tại");
			System.out.println("thêm thất bại đã tồn tại");
		}

		return "redirect:/quanly/pgv/sinhvien/add"+ message;
	}*/
	@PostMapping("sinhvien/add")
	public <R extends JpaRepository<SinhVien, String>> String addVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") SinhVienDTO item) {
		String message = "?message=";
		R repo;

		String idlop =(String)session.getAttribute("MALOP");
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
			

		} else {
			repo = (R) cnrepo; // Cast to the generic type
			

		}
		System.out.println(item.getMaSV());

		if (item.getMaSV()== null || repo.findById(item.getMaSV()).isEmpty()) {
			SinhVien itemsave = modelMapper.map(item, SinhVien.class);
		
			itemsave.setDaNghiHoc(false);
			int nvsave ;

			try {
				dao.taoSinhVien(session,idlop,item.getMaCN(),item.getHo(),item.getTen(),item.getPhai(),item.getDiaChi(),item.getNgaySinh(),item.getDaNghiHoc(),item.getPassword());
			} catch (Exception e) {
				e.printStackTrace();
				message = message + "thêm thất bại";
				model.addAttribute("message", "thêm thất bại");
				System.out.println("thêm thất bại");
			}
		} else {
			message = message + "thêm thất bại, đã tồn tại";
			model.addAttribute("message", "thêm thất bại, đã tồn tại");
			System.out.println("thêm thất bại đã tồn tại");
		}

		return "redirect:/quanly/pgv/sinhvien/add"+ message;
	}
	@GetMapping(value = "sinhvien/edit")
	public String editVTCN1(HttpSession session, ModelMap model, HttpServletRequest request) {
		List<ChuyenNganh> lstCN;


		SinhVien itemEntity;
		String id = request.getParameter("id");
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			itemEntity = vtrepo.findById(id).get(); // Cast to the generic type
			lstCN = vtrepochuyennganh.findAll();

		} else {
			itemEntity = cnrepo.findById(id).get(); // Cast to the generic type
			lstCN = cnrepochuyennganh.findAll();

		}
		model.addAttribute("lstCN", lstCN);

		if (itemEntity != null) {
			SinhVienDTO item = modelMapper.map(itemEntity, SinhVienDTO.class);
			model.addAttribute("item", item);
			System.out.println("tồn tại item");
		}

		else {
			System.out.println("không tồn tại item");
			return "redirect:/quanly/pgv/sinhvien"+"?idlop="+(String)session.getAttribute("MALOP");
		}

		return "pgv/form/sinhvien/fedit-sinhvien";
	}

	@PostMapping("sinhvien/edit")
	public <R extends JpaRepository<SinhVien, String>> String editVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") SinhVienDTO item, HttpServletRequest request) {
		String message = "?message=";

		R repo;
		Khoa khoa;
		Lop lop;
		ChuyenNganh chuyenNganh;
		String idlop =(String)session.getAttribute("MALOP");
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
 			lop = vtrepolop.findById(idlop).get();
			chuyenNganh = vtrepochuyennganh.findById(item.getMaCN()).get();


		} else {
			repo = (R) cnrepo; // Cast to the generic type
			lop = cnrepolop.findById((String)session.getAttribute("MALOP")).get();
			chuyenNganh = cnrepochuyennganh.findById(item.getMaCN()).get();

		}
		// System.out.println(vtsave.getTenVT());
		SinhVien itemsave = modelMapper.map(item, SinhVien.class);
		itemsave.setCN(chuyenNganh);
		itemsave.setLop(lop);
		itemsave.setDaNghiHoc(false);
		try {

			repo.save(itemsave);

		} catch (Exception e) {
			e.printStackTrace();
			message += "Sửa thất bại có thể ban dang sua du lieu cua khoa khac";
			return "redirect:/quanly/pgv/sinhvien" + message+"&idlop="+(String)session.getAttribute("MALOP");

		}

		message += "Sửa  thành công";
		model.addAttribute("message", "Sửa  thành công");
		System.out.println("Sửa thành công");

		return "redirect:/quanly/pgv/sinhvien" + message+"&idlop="+(String)session.getAttribute("MALOP");

	}

	@GetMapping(value = "sinhvien/xoa")
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "pgv/form/sinhvien/fxoa-sinhvien";

	}

	@RequestMapping(value = "sinhvien/xoa", method = RequestMethod.POST)
	public <R extends JpaRepository<SinhVien, String>> String xoaNVCN1P(HttpSession session, ModelMap model,
			HttpServletRequest request) {
		String idlop =(String)session.getAttribute("MALOP");
		String message = "?message=";
		SinhVien itemEntity;
		R repo;
		String id = request.getParameter("id");

		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
			itemEntity = repo.findById(id).get(); // Cast to the generic type

		} else {
			repo = (R) cnrepo; // Cast to the generic type
			itemEntity = repo.findById(id).get(); // Cast to the generic type

		}
		System.out.println(request.getParameter("xacNhan") + request.getParameter("id"));
		try {
			if (request.getParameter("xacNhan").equals("YES")) {
//				VatTuEntity nvsave = vtrepo.findOne(id);
				itemEntity.setDaNghiHoc(true);
				repo.save(itemEntity);
				message += "xoá thành công";
				model.addAttribute("message", "xoá thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message += "xoá thất bại";
			model.addAttribute("message", "xoá thất bại");
		}
		return "redirect:/quanly/pgv/sinhvien" + message+"&idlop="+idlop;

	}

	// ===================================CONGTY==================================//

	// ===================================USER==================================//
	public String timTenChuyenNganhTheoMa(List<ChuyenNganh> danhSachChuyenNganh, String maMH) {
        for (ChuyenNganh monHoc : danhSachChuyenNganh) {
            if (monHoc.getMaCN().equals(maMH)) {
                return monHoc.getTenCN(); // Tr? v? ??i t??ng ChuyenNganh tìm th?y
            }
        }
        return null; // Tr? v? null n?u không tìm th?y
    }
}
