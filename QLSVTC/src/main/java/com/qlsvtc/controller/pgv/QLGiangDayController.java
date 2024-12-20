package com.qlsvtc.controller.pgv;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

import com.qlsvtc.CNTT.repository.GiangDayRepositoryCNTT;
import com.qlsvtc.CNTT.repository.GiangVienRepositoryCNTT;
import com.qlsvtc.CNTT.repository.KhoaRepositoryCNTT;
import com.qlsvtc.DTO.GiangDayDTO;
import com.qlsvtc.VT.repository.GiangDayRepositoryVT;
import com.qlsvtc.VT.repository.GiangVienRepositoryVT;
import com.qlsvtc.VT.repository.KhoaRepositoryVT;
import com.qlsvtc.entity.GiangDay;
import com.qlsvtc.entity.GiangVien;
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

	NhanVienLoginModel login = null;
	ModelMapper modelMapper = new ModelMapper();
	int idLTC;

	@GetMapping("giangday")
	public String getVTCN(HttpServletRequest request, HttpSession session, ModelMap model) {

	
		if (request.getParameter("idltc")==null) 
			if (session.getAttribute("MALTC")!=null) 
			{idLTC=Integer.parseInt((String) session.getAttribute("MALTC"));
			System.out.println((String) session.getAttribute("MALTC"));
			}
			else return "redirect:dang-nhap?action=login";
		else idLTC = Integer.parseInt(request.getParameter("idltc"));

		List<GiangVien> lstGV;
		session.setAttribute("MALTC", idLTC);
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
		int thu, buoi;
		for (GiangDay item : lstEntity) {
			GiangDayDTO dto = modelMapper.map(item, GiangDayDTO.class);
			dto.setTenGV(dto.getMaGV() + " - " + timTenGiangVienTheoMa(lstGV, dto.getMaGV()));
			thu = 2 + item.getMaTB() / 3;
			buoi = item.getMaTB() % 3;
			if (item.getMaTB() % 3 == 0) {
				thu -= 1;
				buoi = 3;
			}

			dto.setThu(thu);
			dto.setBuoi(buoi);

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

		int idltc = (Integer) session.getAttribute("MALTC");
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type

		} else {
			repo = (R) cnrepo; // Cast to the generic type

		}

		GiangDay itemsave = modelMapper.map(item, GiangDay.class);
		int maTB= (item.getThu() - 2) *3 +item.getBuoi();
		itemsave.setMaLTC(idltc);
		itemsave.setMaTB(maTB);
		GiangDay nvsave = null;

		try {
			
			nvsave = repo.save(itemsave);
		} catch (Exception e) {
			e.printStackTrace();
			message = message + "error: failure";
			model.addAttribute("message", message);
			System.out.println("thêm thất bại");
		}
		if (nvsave != null) {
			message = message + "Susscess";
			model.addAttribute("message", message);
			System.out.println("thêm thành công");
		}

		return "redirect:/quanly/pgv/giangday/add" + message;
	}

	@GetMapping(value = "giangday/xoa")
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "pgv/form/giangday/fxoa-giangday";

	}

	@RequestMapping(value = "giangday/xoa", method = RequestMethod.POST)
	public <R extends JpaRepository<GiangDay, Integer>> String xoaNVCN1P(HttpSession session, ModelMap model,
			HttpServletRequest request) {
		Integer idltc = (Integer) session.getAttribute("MALTC");
		String message = "?message=";
		R repo;
		Integer id = Integer.parseInt(request.getParameter("id"));

		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type

		} else {
			repo = (R) cnrepo; // Cast to the generic type

		}
		System.out.println(request.getParameter("xacNhan") + request.getParameter("id"));
		try {
			if (request.getParameter("xacNhan").equals("YES")) {

				repo.deleteById(id);
				message += "Success";
				model.addAttribute("message", "xoá thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message += "error: failure";
			model.addAttribute("message", "xoá thất bại");
		}
		return "redirect:/quanly/pgv/giangday" + message;

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
