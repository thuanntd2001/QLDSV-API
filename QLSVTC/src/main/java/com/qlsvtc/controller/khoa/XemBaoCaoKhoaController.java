package com.qlsvtc.controller.khoa;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlsvtc.CNTT.repository.MonHocRepositoryCNTT;
import com.qlsvtc.VT.repository.MonHocRepositoryVT;
import com.qlsvtc.dao.impl.BCBangDiemMonHocLTCDAO;
import com.qlsvtc.dao.impl.BCBangDiemTongKetDAO;
import com.qlsvtc.dao.impl.BCDSSVDAO;
import com.qlsvtc.dao.impl.BCDsLTCDAO;
import com.qlsvtc.dao.impl.BCDssvDkLTCDAO;
import com.qlsvtc.dao.impl.BCPhieuDiemDAO;
import com.qlsvtc.entity.MonHoc;
import com.qlsvtc.model.NhanVienLoginModel;
import com.qlsvtc.model.baocao.BCBangDiemMonHocLTC;
import com.qlsvtc.model.baocao.BCBangDiemTongKet;
import com.qlsvtc.model.baocao.BCDsLTC;
import com.qlsvtc.model.baocao.BCDssvDkLTC;
import com.qlsvtc.model.baocao.BCPhieuDiem;
import com.qlsvtc.model.baocao.BCSinhVien;
import com.qlsvtc.model.para.ParaBCBangDiemMonHocLTC;
import com.qlsvtc.model.para.ParaBCDsLTC;
import com.qlsvtc.model.para.ParaBangDiemTongKet;
import com.qlsvtc.model.para.ParaLop;
import com.qlsvtc.model.para.ParaPhieuDiem;
import com.qlsvtc.utils.BangDiemUtil;

@Controller
@RequestMapping(value = "xembaocao")
public class XemBaoCaoKhoaController {

	@Autowired
	MonHocRepositoryCNTT cnrepo;
	@Autowired
	MonHocRepositoryVT vtrepo;

	BCBangDiemMonHocLTCDAO bcbangdiemmonhocltc = new BCBangDiemMonHocLTCDAO();
	BCDsLTCDAO bcdsltc = new BCDsLTCDAO();
	BCDssvDkLTCDAO bcdssvdkltc = new BCDssvDkLTCDAO();
	BCPhieuDiemDAO bcphieudiem = new BCPhieuDiemDAO();
	BCBangDiemTongKetDAO bcbangdiemtongket = new BCBangDiemTongKetDAO();
	BCDSSVDAO bcdssv = new BCDSSVDAO();
	NhanVienLoginModel login = null;

	@GetMapping("/bcbangdiemmonhocltc/khoa")
	public String getbcbangdiemmonhocltc(ModelMap model, HttpSession session) {

		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		List<MonHoc> lstMH;
		if ("VT".equals(login.getKhoa())) {
			lstMH = vtrepo.findAll(); // Cast to the generic type
		} else {
			lstMH = cnrepo.findAll(); // Cast to the generic type
		}

		ParaBCBangDiemMonHocLTC para = new ParaBCBangDiemMonHocLTC();
		model.addAttribute("para", para);
		model.addAttribute("lstMH", lstMH);
		return "khoa/form/fbcbangdiemmonhocltc";
	}

	@PostMapping("/bcbangdiemmonhocltc/khoa")
	public String bcbangdiemmonhocltc(HttpSession session, ParaBCBangDiemMonHocLTC para, ModelMap model) {

		MonHoc mh = null;
		if ("VT".equals(login.getKhoa())) {
			mh = vtrepo.findById(para.getMaMH()).get();// Cast to the generic type
		} else {
			mh = cnrepo.findById(para.getMaMH()).get(); // Cast to the generic type
		}
		model.addAttribute("mh", mh.getTenMH());

		List<BCBangDiemMonHocLTC> lst = bcbangdiemmonhocltc.findAll(session, para.getNk(), para.getHk(), para.getMaMH(),
				para.getNhom());

		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/baocao/bcbangdiemmonhocltc";
	}

	@GetMapping("/bcdsltc/khoa")
	public String getbcdsltc(ModelMap model) {
		ParaBCDsLTC para = new ParaBCDsLTC();
		model.addAttribute("para", para);
		return "khoa/form/fbcdsltc";
	}

	@PostMapping("/bcdsltc/khoa")
	public String bcdsltc(HttpSession session, ParaBCDsLTC para, ModelMap model) {
		List<BCDsLTC> lst = bcdsltc.findAll(session, para.getNk(), para.getHk());
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/baocao/bcdsltc";
	}

	@GetMapping("/bcdssvdkltc/khoa")
	public String getbcdssvdkltc(ModelMap model, HttpSession session) {

		login = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		List<MonHoc> lstMH;
		if ("VT".equals(login.getKhoa())) {
			lstMH = vtrepo.findAll(); // Cast to the generic type
		} else {
			lstMH = cnrepo.findAll(); // Cast to the generic type
		}
		ParaBCBangDiemMonHocLTC para = new ParaBCBangDiemMonHocLTC();
		model.addAttribute("para", para);
		model.addAttribute("lstMH", lstMH);

		return "khoa/form/fbcdssvdkltc";
	}

	@PostMapping("/bcdssvdkltc/khoa")
	public String bcdssvdkltc(HttpSession session, ParaBCBangDiemMonHocLTC para, ModelMap model) {
		MonHoc mh = null;
		if ("VT".equals(login.getKhoa())) {
			mh = vtrepo.findById(para.getMaMH()).get();// Cast to the generic type
		} else {
			mh = cnrepo.findById(para.getMaMH()).get(); // Cast to the generic type
		}
		model.addAttribute("mh", mh.getTenMH());

		List<BCDssvDkLTC> lst = bcdssvdkltc.findAll(session, para.getNk(), para.getHk(), para.getMaMH(),
				para.getNhom());
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);

		return "khoa/baocao/bcdssvdkltc";
	}

	@GetMapping("/bcphieudiem/khoa")
	public String getbcphieudiem(ModelMap model) {
		ParaPhieuDiem para = new ParaPhieuDiem();
		model.addAttribute("para", para);
		return "khoa/form/fbcphieudiem";
	}

	@PostMapping("/bcphieudiem/khoa")
	public String bcphieudiem(HttpSession session, ParaPhieuDiem para, ModelMap model) {
		List<BCPhieuDiem> lst = bcphieudiem.findAll(session, para.getMaSV());
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);
		return "khoa/baocao/bcphieudiem";
	}

	@GetMapping("/bcbangdiemtongket/khoa")
	public String getbcbangdiemtongket(ModelMap model) {
		ParaBangDiemTongKet para = new ParaBangDiemTongKet();
		model.addAttribute("para", para);
		return "khoa/form/fbcbangdiemtongket";
	}

	@PostMapping("/bcbangdiemtongket/khoa")
	public String bcbangdiemtongket(HttpSession session, ParaBangDiemTongKet para, ModelMap model) throws SQLException {
		List<BCBangDiemTongKet> bangDiemList = bcbangdiemtongket.findAll(session, para.getMaLop());
		List<String> uniqueTenMonHoc = BangDiemUtil.getUniqueTenMonHoc(bangDiemList);
		Map<String, Map<String, Float>> entrySet = BangDiemUtil.convertToMap(bangDiemList);
		model.addAttribute("uniqueTenMonHoc", uniqueTenMonHoc);
		model.addAttribute("entrySet", entrySet);

		model.addAttribute("para", para);

		return "khoa/baocao/bcbangdiemtongket";
	}

	@GetMapping("/bcdssv/khoa")
	public String bcdssv(ModelMap model) {
		ParaLop para = new ParaLop();
		model.addAttribute("para", para);
		return "khoa/form/fbcdssv";
	}

	@PostMapping("/bcdssv/khoa")
	public String bcdssv(HttpSession session, ParaLop para, ModelMap model) throws SQLException {
		List<BCSinhVien> lst = bcdssv.findAllKhoa(session, para.getMaLop());
		model.addAttribute("lst", lst);
		model.addAttribute("para", para);

		return "khoa/baocao/bcdssv";
	}
}