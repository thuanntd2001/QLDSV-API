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

import com.qlsvtc.CNTT.repository.LTCRepositoryCNTT;
import com.qlsvtc.CNTT.repository.MonHocRepositoryCNTT;
import com.qlsvtc.CNTT.repository.NKHKRepositoryCNTT;
import com.qlsvtc.CNTT.repository.KhoaRepositoryCNTT;
import com.qlsvtc.DTO.LTCDTO;
import com.qlsvtc.VT.repository.LTCRepositoryVT;
import com.qlsvtc.VT.repository.MonHocRepositoryVT;
import com.qlsvtc.VT.repository.NKHKRepositoryVT;
import com.qlsvtc.VT.repository.KhoaRepositoryVT;
import com.qlsvtc.entity.LTC;
import com.qlsvtc.entity.MonHoc;
import com.qlsvtc.entity.Khoa;
import com.qlsvtc.model.NhanVienLoginModel;

@Controller
@RequestMapping(value = "quanly/pgv")
public class QLLopTinChiController {
	@Autowired
	LTCRepositoryCNTT cnrepo;
	@Autowired
	LTCRepositoryVT vtrepo;
	
	@Autowired
	MonHocRepositoryCNTT cnrepomonhoc;
	@Autowired
	MonHocRepositoryVT vtrepomonhoc;


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
	int maNKHK;

	@GetMapping("ltc")
	public String getVTCN(HttpServletRequest request, HttpSession session, ModelMap model) {
		/*
		 * Sort sort = new Sort(Sort.Direction.ASC, "maVT");;
		 * 
		 */
		if (request.getParameter("idnkhk")==null) 
			if (session.getAttribute("MANKHK")!=null) 
			{maNKHK=(Integer) session.getAttribute("MANKHK");
			//System.out.println((String) session.getAttribute("MANKHK"));
			}
			else return "redirect:dang-nhap?action=login";
		else maNKHK = Integer.parseInt(request.getParameter("idnkhk"));

		List<MonHoc> lstMH;
		session.setAttribute("MANKHK", maNKHK);
		String message = request.getParameter("message");
		model.addAttribute("message", message);
		List<LTC> lstEntity;
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			lstEntity = vtrepo.findAllByMaNKHKAndHuyLop(maNKHK,false); 
			lstMH = vtrepomonhoc.findAll();
		} else {
			lstEntity = cnrepo.findAllByMaNKHKAndHuyLop(maNKHK,false); 
			lstMH = cnrepomonhoc.findAll();

		}
		List<LTCDTO> lst = new ArrayList<LTCDTO>();
		for (LTC item : lstEntity) {
			LTCDTO dto = modelMapper.map(item, LTCDTO.class);
			dto.setTenMH(dto.getMaMH()+" - "+timTenMonHocTheoMa(lstMH,dto.getMaMH()));
			lst.add(dto);
		}

		model.addAttribute("lst", lst);
		return "pgv/qlltc";
	}

	@GetMapping("ltc/add")
	public String addVTCN(ModelMap model, HttpServletRequest request) {
		List<MonHoc> lstMH;
		if ("VT".equals(login.getKhoa())) {
			lstMH = vtrepomonhoc.findAll();
		} else {
			lstMH = cnrepomonhoc.findAll();

		}
		model.addAttribute("lstMH", lstMH);
		String message = request.getParameter("message");
		model.addAttribute("message", message);
		LTCDTO item = new LTCDTO();
		model.addAttribute("item", item);



		return "pgv/form/ltc/fadd-ltc";
	}

	@PostMapping("ltc/add")
	public <R extends JpaRepository<LTC, Integer>> String addVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") LTCDTO item) {
		String message = "?message=";
		R repo;
		Khoa khoa;
		int idnkhk = Integer.parseInt((String)session.getAttribute("MANKHK"));
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
			khoa = vtrepokhoa.findAll().get(0);
		} else {
			repo = (R) cnrepo; // Cast to the generic type
			khoa = cnrepokhoa.findAll().get(0);
		}
		System.out.println(item.getMaLTC());

		if (item.getMaLTC()== null || repo.findById(item.getMaLTC()).isEmpty()) {
			LTC itemsave = modelMapper.map(item, LTC.class);
			itemsave.setKhoa(khoa);
			itemsave.setMaNKHK(idnkhk);
			itemsave.setHuyLop(false);
			LTC nvsave = null;

			try {
				nvsave = repo.save(itemsave);
			} catch (Exception e) {
				e.printStackTrace();
				message = message + "insert failure";
				model.addAttribute("message", "insert failure");
				System.out.print("insert failure");
			}
			if (nvsave != null) {
				message = message + "insert success";
				model.addAttribute("message", "insert success");
				System.out.print("insert success");
			}
		} else {
			message = message + "insert failure, đã tồn tại";
			model.addAttribute("message", "insert failure, đã tồn tại");
			System.out.print("insert failure đã tồn tại");
		}

		return "redirect:/quanly/pgv/ltc/add"+ message;
	}

	@GetMapping(value = "ltc/edit")
	public String editVTCN1(HttpSession session, ModelMap model, HttpServletRequest request) {
		List<MonHoc> lstMH;


		LTC itemEntity;
		int id = Integer.parseInt(request.getParameter("id"));
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			itemEntity = vtrepo.findById(id).get(); // Cast to the generic type
			lstMH = vtrepomonhoc.findAll();

		} else {
			itemEntity = cnrepo.findById(id).get(); // Cast to the generic type
			lstMH = cnrepomonhoc.findAll();

		}
		model.addAttribute("lstMH", lstMH);

		if (itemEntity != null) {
			LTCDTO item = modelMapper.map(itemEntity, LTCDTO.class);
			model.addAttribute("item", item);
			System.out.print("tồn tại item");
		}

		else {
			System.out.print("không tồn tại item");
			return "redirect:/quanly/pgv/ltc"+"?idnkhk="+(String)session.getAttribute("MANKHK");
		}

		return "pgv/form/ltc/fedit-ltc";
	}

	@PostMapping("ltc/edit")
	public <R extends JpaRepository<LTC, Integer>> String editVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") LTCDTO item, HttpServletRequest request) {
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
		LTC itemsave = modelMapper.map(item, LTC.class);
		itemsave.setKhoa(khoa);
		itemsave.setMaNKHK(Integer.parseInt((String)session.getAttribute("MANKHK")));
		itemsave.setHuyLop(false);
		System.out.println(khoa.getMaKhoa());
		try {

			repo.save(itemsave);

		} catch (Exception e) {
			e.printStackTrace();
			message += "alter failure có thể ban dang sua du lieu cua khoa khac";
			return "redirect:/quanly/pgv/ltc" + message+"&idnkhk="+(String)session.getAttribute("MANKHK");

		}

		message += "alter  success";
		model.addAttribute("message", "alter  success");
		System.out.print("alter success");

		return "redirect:/quanly/pgv/ltc" + message+"&idnkhk="+(String)session.getAttribute("MANKHK");

	}

	@GetMapping(value = "ltc/xoa")
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "pgv/form/ltc/fxoa-ltc";

	}

	@RequestMapping(value = "ltc/xoa", method = RequestMethod.POST)
	public <R extends JpaRepository<LTC, Integer>> String xoaNVCN1P(HttpSession session, ModelMap model,
			HttpServletRequest request) {
		int idnkhk = Integer.parseInt((String)session.getAttribute("MANKHK"));
		String message = "?message=";
		LTC itemEntity;
		R repo;
		int id = Integer.parseInt(request.getParameter("id"));

		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		if ("VT".equals(login.getKhoa())) {
			repo = (R) vtrepo; // Cast to the generic type
			itemEntity = repo.findById(id).get(); // Cast to the generic type

		} else {
			repo = (R) cnrepo; // Cast to the generic type
			itemEntity = repo.findById(id).get(); // Cast to the generic type

		}
		System.out.print(request.getParameter("xacNhan") + request.getParameter("id"));
		try {
			if (request.getParameter("xacNhan").equals("YES")) {
//				VatTuEntity nvsave = vtrepo.findOne(id);
				itemEntity.setHuyLop(true);
				repo.save(itemEntity);
				message += "delete success";
				model.addAttribute("message", "delete success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message += "delete failure";
			model.addAttribute("message", "delete failure");
		}
		return "redirect:/quanly/pgv/ltc" + message+"&idnkhk="+idnkhk;

	}

	// ===================================CONGTY==================================//

	// ===================================USER==================================//
	public String timTenMonHocTheoMa(List<MonHoc> danhSachMonHoc, String maMH) {
        for (MonHoc monHoc : danhSachMonHoc) {
            if (monHoc.getMaMH().equals(maMH)) {
                return monHoc.getTenMH(); // Tr? v? ??i t??ng MonHoc tìm th?y
            }
        }
        return null; // Tr? v? null n?u không tìm th?y
    }
}
