package com.qlsvtc.rest;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qlsvtc.dao.impl.BCBangDiemMonHocLTCDAO;
import com.qlsvtc.dao.impl.BCDsLTCDAO;
import com.qlsvtc.dao.impl.BCDssvDkLTCDAO;
import com.qlsvtc.dao.impl.BCPhieuDiemDAO;
import com.qlsvtc.dao.impl.DSPMDAO;
import com.qlsvtc.dao.impl.NhanVienDAO;
import com.qlsvtc.model.DSPMModel;
import com.qlsvtc.model.NhanVienLoginModel;
import com.qlsvtc.model.UserModel;
import com.qlsvtc.model.baocao.BCBangDiemMonHocLTC;
import com.qlsvtc.model.baocao.BCDsLTC;
import com.qlsvtc.model.baocao.BCDssvDkLTC;
import com.qlsvtc.model.baocao.BCPhieuDiem;
import com.qlsvtc.model.para.ParaBCBangDiemMonHocLTC;
import com.qlsvtc.model.para.ParaBCDsLTC;
import com.qlsvtc.model.para.ParaPhieuDiem;
import com.qlsvtc.service.ICheckService;
import com.qlsvtc.service.impl.CheckService;
import com.qlsvtc.statics.InfoConnection;



@RestController
public class BaoCaoController {

	
	BCBangDiemMonHocLTCDAO bcbangdiemmonhocltc = new BCBangDiemMonHocLTCDAO();
	BCDsLTCDAO bcdsltc = new BCDsLTCDAO();
	BCDssvDkLTCDAO bcdssvdkltc = new BCDssvDkLTCDAO();
	BCPhieuDiemDAO bcphieudiem = new BCPhieuDiemDAO();
	
	@GetMapping("/bcbangdiemmonhocltc")
	public List<BCBangDiemMonHocLTC> bcbangdiemmonhocltc(ParaBCBangDiemMonHocLTC para) {
		// tao dspm set ra view
		return bcbangdiemmonhocltc.findAll(para.getNk(),para.getHk(),para.getMaMH(),para.getNhom());
	}
	
	@GetMapping("/bcdsltc")
	public List<BCDsLTC> bcdsltc(ParaBCDsLTC para) {
		// tao dspm set ra view
		return bcdsltc.findAll(para.getNk(),para.getHk());
	}
	
	@GetMapping("/bcdssvdkltc")
	public List<BCDssvDkLTC> bcdssvdkltc(ParaBCBangDiemMonHocLTC para) {
		// tao dspm set ra view
		return bcdssvdkltc.findAll(para.getNk(),para.getHk(),para.getMaMH(),para.getNhom());
	}
	
	@GetMapping("/bcphieudiem")
	public List<BCPhieuDiem> bcphieudiem(ParaPhieuDiem para) {
		// tao dspm set ra view
		return bcphieudiem.findAll(para.getMaSV());
	}
	
}
