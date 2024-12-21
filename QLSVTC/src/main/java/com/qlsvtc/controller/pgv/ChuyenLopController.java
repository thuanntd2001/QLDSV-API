package com.qlsvtc.controller.pgv;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
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

import com.qlsvtc.CNTT.repository.ChuyenNganhRepositoryCNTT;
import com.qlsvtc.CNTT.repository.KhoaRepositoryCNTT;
import com.qlsvtc.CNTT.repository.LopRepositoryCNTT;
import com.qlsvtc.CNTT.repository.SinhVienRepositoryCNTT;
import com.qlsvtc.DTO.SinhVienDTO;
import com.qlsvtc.VT.repository.ChuyenNganhRepositoryVT;
import com.qlsvtc.VT.repository.KhoaRepositoryVT;
import com.qlsvtc.VT.repository.LopRepositoryVT;
import com.qlsvtc.VT.repository.SinhVienRepositoryVT;
import com.qlsvtc.dao.impl.ChuyenLopDAO;
import com.qlsvtc.dao.impl.SinhVienDAO;
import com.qlsvtc.entity.ChuyenNganh;
import com.qlsvtc.entity.Khoa;
import com.qlsvtc.entity.Lop;
import com.qlsvtc.entity.SinhVien;
import com.qlsvtc.model.NhanVienLoginModel;
import com.qlsvtc.model.para.ParaChuyenLop;

@Controller
@RequestMapping(value = "quanly/pgv")
public class ChuyenLopController {
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


	@GetMapping("chuyenlop")
	public String addVTCN(ModelMap model, HttpServletRequest request) {
		String idsv = request.getParameter("id");

		List<ChuyenNganh> lstCN1;
		List<ChuyenNganh> lstCN2;
		lstCN1 = vtrepochuyennganh.findAll();
		lstCN2 = cnrepochuyennganh.findAll();
		List<ChuyenNganh> lstCN= new  ArrayList<>();
		lstCN.addAll(lstCN1);
		lstCN.addAll(lstCN2);
		
		List<Lop> lstLop1;
		List<Lop> lstLop2;
		lstLop1 = vtrepolop.findAll();
		lstLop2 = cnrepolop.findAll();
		List<Lop> lstLop=new  ArrayList<>();
		lstLop.addAll(lstLop1);
		lstLop.addAll(lstLop2);

		
		model.addAttribute("lstCN", lstCN);
		model.addAttribute("lstLop", lstLop);

		String message = request.getParameter("message");
		model.addAttribute("message", message);
		ParaChuyenLop item = new ParaChuyenLop();
		item.setMaSV(idsv);
		model.addAttribute("item", item);



		return "pgv/form/fchuyenlop";
	}
	
	
	
	ChuyenLopDAO cldao = new ChuyenLopDAO();
	@PostMapping("chuyenlop")
	public  String addVTCN1(HttpSession session, ModelMap model,
			@ModelAttribute("item") ParaChuyenLop item) throws UnsupportedEncodingException {
		String message = "?message=-";
		
		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		
		try {
			cldao.ChuyenLop(session, item.getMaSV(), item.getMaLop(), item.getMaCN());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = message + URLEncoder.encode("failure: "+e.getMessage(), "UTF-8");
			return "redirect:/quanly/pgv/sinhvien"+message+"&idlop="+(String)session.getAttribute("MALOP");

		}
		message+="Running SP_CHUYENLOP_SV";
		return "redirect:/quanly/pgv/sinhvien"+message+"&idlop="+(String)session.getAttribute("MALOP");
	}
	
}
