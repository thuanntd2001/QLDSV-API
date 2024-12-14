/*package com.qlsvtc.controller.pgv;

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
import com.qlsvtc.CNTT.repository.LTCRepositoryCNTT;
import com.qlsvtc.CNTT.repository.SinhVienRepositoryCNTT;
import com.qlsvtc.DTO.SinhVienDTO;
import com.qlsvtc.VT.repository.ChuyenNganhRepositoryVT;
import com.qlsvtc.VT.repository.KhoaRepositoryVT;
import com.qlsvtc.VT.repository.LTCRepositoryVT;
import com.qlsvtc.VT.repository.SinhVienRepositoryVT;
import com.qlsvtc.dao.impl.SinhVienDAO;
import com.qlsvtc.entity.ChuyenNganh;
import com.qlsvtc.entity.Khoa;
import com.qlsvtc.entity.LTC;
import com.qlsvtc.entity.SinhVien;
import com.qlsvtc.model.NhanVienLoginModel;

@Controller
@RequestMapping(value = "quanly/pgv")
public class QLDKSinhVienController {
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
	LTCRepositoryCNTT cnrepoltc;
	@Autowired
	LTCRepositoryVT vtrepoltc;

	NhanVienLoginModel login = null;
	ModelMapper modelMapper = new ModelMapper();
	String idLTC;

	@GetMapping("dangkysv")
	public String getVTCN(HttpServletRequest request, HttpSession session, ModelMap model) {
	
		idLTC = request.getParameter("idltc");
		List<ChuyenNganh> lstCN;
		session.setAttribute("MALTC", request.getParameter("idltc"));
		String message = request.getParameter("message");
		model.addAttribute("message", message);
		List<SinhVien> lstEntity;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			lstEntity = vtrepo.findAllByLTC_MaLTCAndDaNghiHoc(idLTC,false); 
			lstCN = vtrepochuyennganh.findAll();
		} else {
			lstEntity = cnrepo.findAllByLTC_MaLTCAndDaNghiHoc(idLTC,false); 
			lstCN = cnrepochuyennganh.findAll();

		}
		List<SinhVienDTO> lst = new ArrayList<SinhVienDTO>();
		for (SinhVien item : lstEntity) {
			SinhVienDTO dto = modelMapper.map(item, SinhVienDTO.class);
			dto.setTenCN(dto.getMaCN()+" - "+timTenChuyenNganhTheoMa(lstCN,dto.getMaCN()));
			lst.add(dto);
		}

		model.addAttribute("lst", lst);
		return "pgv/qldangkysv";
	}

	@GetMapping("dangkysv/add")
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



		return "pgv/form/dangkysv/fadd-dangkysv";
	}

	@PostMapping("dangkysv/add")
	public <R extends JpaRepository<SinhVien, String>> String addVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") SinhVienDTO item) {
		String message = "?message=";
		R repo;
		Khoa khoa;
		LTC ltc;
		ChuyenNganh chuyenNganh;

		int idltc =(Integer)session.getAttribute("MALTC");
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
			khoa = vtrepokhoa.findAll().get(0);
			ltc = vtrepoltc.findById(idltc).get();
			chuyenNganh = vtrepochuyennganh.findById(item.getMaCN()).get();


		} else {
			repo = (R) cnrepo; // Cast to the generic type
			khoa = cnrepokhoa.findAll().get(0);
			ltc = cnrepoltc.findById((Integer)session.getAttribute("MALTC")).get();
			chuyenNganh = cnrepochuyennganh.findById(item.getMaCN()).get();

		}

		if (item.getMaSV()== null || repo.findById(item.getMaSV()).isEmpty()) {
			SinhVien itemsave = modelMapper.map(item, SinhVien.class);
			itemsave.setCN(chuyenNganh);
			itemsave.setLTC(ltc);
			itemsave.setDaNghiHoc(false);
			SinhVien nvsave = null;

			try {
				if (itemsave.getMaSV() == null) {
				itemsave.setMaSV(dao.taoSV(session,idltc,item.getMaCN()).get(0).getStr());		
				}
				System.out.println(itemsave.getMaSV());

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

		return "redirect:/quanly/pgv/dangkysv/add"+ message;
	}
	
	@GetMapping(value = "dangkysv/edit")
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
			return "redirect:/quanly/pgv/dangkysv"+"?idltc="+(Integer)session.getAttribute("MALTC");
		}

		return "pgv/form/dangkysv/fedit-dangkysv";
	}

	@PostMapping("dangkysv/edit")
	public <R extends JpaRepository<SinhVien, String>> String editVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") SinhVienDTO item, HttpServletRequest request) {
		String message = "?message=";

		R repo;
		Khoa khoa;
		LTC ltc;
		ChuyenNganh chuyenNganh;
		int idltc =(Integer)session.getAttribute("MALTC");
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
 			ltc = vtrepoltc.findById(idltc).get();
			chuyenNganh = vtrepochuyennganh.findById(item.getMaCN()).get();


		} else {
			repo = (R) cnrepo; // Cast to the generic type
			ltc = cnrepoltc.findById((Integer)session.getAttribute("MALTC")).get();
			chuyenNganh = cnrepochuyennganh.findById(item.getMaCN()).get();

		}
		// System.out.println(vtsave.getTenVT());
		SinhVien itemsave = modelMapper.map(item, SinhVien.class);
		itemsave.setCN(chuyenNganh);
		itemsave.setLTC(ltc);
		itemsave.setDaNghiHoc(false);
		try {

			repo.save(itemsave);

		} catch (Exception e) {
			e.printStackTrace();
			message += "Sửa thất bại có thể ban dang sua du lieu cua khoa khac";
			return "redirect:/quanly/pgv/dangkysv" + message+"&idltc="+(Integer)session.getAttribute("MALTC");

		}

		message += "Sửa  thành công";
		model.addAttribute("message", "Sửa  thành công");
		System.out.println("Sửa thành công");

		return "redirect:/quanly/pgv/dangkysv" + message+"&idltc="+(Integer)session.getAttribute("MALTC");

	}

	@GetMapping(value = "dangkysv/xoa")
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "pgv/form/dangkysv/fxoa-dangkysv";

	}

	@RequestMapping(value = "dangkysv/xoa", method = RequestMethod.POST)
	public <R extends JpaRepository<SinhVien, String>> String xoaNVCN1P(HttpSession session, ModelMap model,
			HttpServletRequest request) {
		int idltc =(Integer)session.getAttribute("MALTC");
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
		return "redirect:/quanly/pgv/dangkysv" + message+"&idltc="+idltc;

	}


	public String timTenChuyenNganhTheoMa(List<ChuyenNganh> danhSachChuyenNganh, String maMH) {
        for (ChuyenNganh monHoc : danhSachChuyenNganh) {
            if (monHoc.getMaCN().equals(maMH)) {
                return monHoc.getTenCN(); // Tr? v? ??i t??ng ChuyenNganh tìm th?y
            }
        }
        return null; // Tr? v? null n?u không tìm th?y
    }
}
*/