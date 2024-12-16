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

import com.qlsvtc.CNTT.repository.GiangVienRepositoryCNTT;
import com.qlsvtc.CNTT.repository.KhoaRepositoryCNTT;
import com.qlsvtc.CNTT.repository.LopRepositoryCNTT;
import com.qlsvtc.CNTT.repository.GiangDayRepositoryCNTT;
import com.qlsvtc.DTO.GiangDayDTO;
import com.qlsvtc.VT.repository.GiangVienRepositoryVT;
import com.qlsvtc.VT.repository.KhoaRepositoryVT;
import com.qlsvtc.VT.repository.LopRepositoryVT;
import com.qlsvtc.VT.repository.GiangDayRepositoryVT;
import com.qlsvtc.entity.GiangVien;
import com.qlsvtc.entity.Khoa;
import com.qlsvtc.entity.Lop;
import com.qlsvtc.entity.GiangDay;
import com.qlsvtc.model.NhanVienLoginModel;

@Controller
@RequestMapping(value = "quanly/pgv")
public class QLGiangDayController {
	@Autowired
	GiangDayRepositoryCNTT cnrepo;
	@Autowired
	GiangDayRepositoryVT vtrepo;

	@Autowired
	GiangVienRepositoryCNTT cnrepogiangvien;
	@Autowired
	GiangVienRepositoryVT vtrepogiangvien;


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
	int idLTC;

	@GetMapping("giangday")
	public String getVTCN(HttpServletRequest request, HttpSession session, ModelMap model) {
	
		idLTC = Integer.parseInt(request.getParameter("idltc"));
		List<GiangVien> lstGV;
		session.setAttribute("MALTC", request.getParameter("idltc"));
		String message = request.getParameter("message");
		model.addAttribute("message", message);
		List<GiangDay> lstEntity;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			lstEntity = vtrepo.findAllByMaLTC(idLTC); 
			lstGV = vtrepogiangvien.findAll();
		} else {
			lstEntity = cnrepo.findAllByMaLTC(idLTC); 
			lstGV = cnrepogiangvien.findAll();

		}
		List<GiangDayDTO> lst = new ArrayList<GiangDayDTO>();
		for (GiangDay item : lstEntity) {
			GiangDayDTO dto = modelMapper.map(item, GiangDayDTO.class);
			dto.setTenGV(dto.getMaGV()+" - "+timTenGiangVienTheoMa(lstGV,dto.getMaGV()));
			lst.add(dto);
		}

		model.addAttribute("lst", lst);
		return "pgv/qlgiangday";
	}

	@GetMapping("giangday/add")
	public String addVTCN(ModelMap model, HttpServletRequest request) {
		List<GiangVien> lstGV;
		if ("VT".equals(login.getKhoa())) {
			lstGV = vtrepogiangvien.findAll();
		} else {
			lstGV = cnrepogiangvien.findAll();

		}
		model.addAttribute("lstGV", lstGV);
		String message = request.getParameter("message");
		model.addAttribute("message", message);
		GiangDayDTO item = new GiangDayDTO();
		model.addAttribute("item", item);



		return "pgv/form/giangday/fadd-giangday";
	}

	@PostMapping("giangday/add")
	public <R extends JpaRepository<GiangDay, Integer>> String addVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") GiangDayDTO item) {
		String message = "?message=";
		R repo;
		Khoa khoa;
		Lop lop;
		GiangVien chuyenNganh;

		String idltc =(String)session.getAttribute("MALTC");
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
			khoa = vtrepokhoa.findAll().get(0);
			lop = vtrepolop.findById(idltc).get();
			chuyenNganh = vtrepogiangvien.findById(item.getMaGV()).get();


		} else {
			repo = (R) cnrepo; // Cast to the generic type
			khoa = cnrepokhoa.findAll().get(0);
			lop = cnrepolop.findById((String)session.getAttribute("MALTC")).get();
			chuyenNganh = cnrepogiangvien.findById(item.getMaGV()).get();

		}

		if (item.getMaGD()== 0 || repo.findById(item.getMaGD()).isEmpty()) {
			GiangDay itemsave = modelMapper.map(item, GiangDay.class);
		//	itemsave.setCN(chuyenNganh);
		//	itemsave.setLop(lop);
		//	itemsave.setDaNghiHoc(false);
			GiangDay nvsave = null;

			try {
				if (itemsave.getMaGD() == 0) {
				//itemsave.setMaSV(dao.taoSV(session,idltc,item.getMaGV()).get(0).getStr());		
				}
				System.out.println(itemsave.getMaGD());

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

		return "redirect:/quanly/pgv/giangday/add"+ message;
	}
	
	
	@GetMapping(value = "giangday/xoa")
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "pgv/form/giangday/fxoa-giangday";

	}

	@RequestMapping(value = "giangday/xoa", method = RequestMethod.POST)
	public <R extends JpaRepository<GiangDay, Integer>> String xoaNVCN1P(HttpSession session, ModelMap model,
			HttpServletRequest request) {
		String idltc =(String)session.getAttribute("MALTC");
		String message = "?message=";
		GiangDay itemEntity;
		R repo;
		Integer id = Integer.parseInt(request.getParameter("id"));

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
			//	itemEntity.setDaNghiHoc(true);
				repo.save(itemEntity);
				message += "xoá thành công";
				model.addAttribute("message", "xoá thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message += "xoá thất bại";
			model.addAttribute("message", "xoá thất bại");
		}
		return "redirect:/quanly/pgv/giangday" + message+"&idltc="+idltc;

	}


	public String timTenGiangVienTheoMa(List<GiangVien> danhSachGiangVien, String maGV) {
        for (GiangVien gv : danhSachGiangVien) {
            if (gv.getMaGV().equals(maGV)) {
                return gv.getTen(); // Tr? v? ??i t??ng GiangVien tìm th?y
            }
        }
        return null; // Tr? v? null n?u không tìm th?y
    }
}
