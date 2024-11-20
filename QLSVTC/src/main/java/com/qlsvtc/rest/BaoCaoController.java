package com.qlsvtc.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qlsvtc.dao.impl.BCBangDiemMonHocLTCDAO;
import com.qlsvtc.dao.impl.BCDsLTCDAO;
import com.qlsvtc.dao.impl.BCDssvDkLTCDAO;
import com.qlsvtc.dao.impl.BCPhieuDiemDAO;
import com.qlsvtc.model.baocao.BCBangDiemMonHocLTC;
import com.qlsvtc.model.baocao.BCDsLTC;
import com.qlsvtc.model.baocao.BCDssvDkLTC;
import com.qlsvtc.model.baocao.BCPhieuDiem;
import com.qlsvtc.model.para.ParaBCBangDiemMonHocLTC;
import com.qlsvtc.model.para.ParaBCDsLTC;
import com.qlsvtc.model.para.ParaPhieuDiem;
import javax.servlet.http.HttpSession;



@RestController
public class BaoCaoController {

	
	BCBangDiemMonHocLTCDAO bcbangdiemmonhocltc = new BCBangDiemMonHocLTCDAO();
	BCDsLTCDAO bcdsltc = new BCDsLTCDAO();
	BCDssvDkLTCDAO bcdssvdkltc = new BCDssvDkLTCDAO();
	BCPhieuDiemDAO bcphieudiem = new BCPhieuDiemDAO();
	
	@GetMapping("/bcbangdiemmonhocltc")
	public List<BCBangDiemMonHocLTC> bcbangdiemmonhocltc(HttpSession session,ParaBCBangDiemMonHocLTC para) {
		// tao dspm set ra view
		return bcbangdiemmonhocltc.findAll(session,para.getNk(),para.getHk(),para.getMaMH(),para.getNhom());
	}
	
	@GetMapping("/bcdsltc")
	public List<BCDsLTC> bcdsltc(HttpSession session,ParaBCDsLTC para) {
		// tao dspm set ra view
		return bcdsltc.findAll(session,para.getNk(),para.getHk());
	}
	
	@GetMapping("/bcdssvdkltc")
	public List<BCDssvDkLTC> bcdssvdkltc(HttpSession session,ParaBCBangDiemMonHocLTC para) {
		// tao dspm set ra view
		return bcdssvdkltc.findAll(session,para.getNk(),para.getHk(),para.getMaMH(),para.getNhom());
	}
	
	@GetMapping("/bcphieudiem")
	public List<BCPhieuDiem> bcphieudiem(HttpSession session,ParaPhieuDiem para) {
		// tao dspm set ra view
		return bcphieudiem.findAll(session,para.getMaSV());
	}
	
}
