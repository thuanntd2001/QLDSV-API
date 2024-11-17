package com.qlsvtc.rest.chung;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qlsvtc.dao.impl.DSPMDAO;
import com.qlsvtc.dao.impl.NhanVienDAO;
import com.qlsvtc.model.DSPMModel;
import com.qlsvtc.model.NhanVienLoginModel;
import com.qlsvtc.model.UserModel;
import com.qlsvtc.service.ICheckService;
import com.qlsvtc.service.impl.CheckService;
import com.qlsvtc.statics.InfoConnection;



@RestController
public class LoginController {

	@Autowired
	ServletContext session;
	@Autowired
	ServletContext application;
	DSPMDAO dspmDAO = new DSPMDAO();
	NhanVienDAO nvdao = new NhanVienDAO();
	private ICheckService ck = new CheckService();

	@GetMapping("/dspm")
	public List<DSPMModel> addDspm() {
		// tao dspm set ra view
		return dspmDAO.findAll();
	}
	
	@PostMapping("/login")
	private NhanVienLoginModel doPost(UserModel model) {
		NhanVienLoginModel login;
		if (model != null) {
			// kt nv co tk trong sqlserver ko
			boolean flag = false;
			// set server ma nv chon de thu ket noi
			InfoConnection.setUrlPM(model.getKhoaURL());
			flag = ck.ckUserPassword(model.getUserName(), model.getPasswd());
			// neu ket noi dc setup thuoc tinh cho nhan vien sap dang nhap
			if (flag) {
				InfoConnection.setUrlPM(model.getKhoaURL());
				InfoConnection.setPassWordPM(model.getPasswd());
				InfoConnection.setUserNamePM(model.getUserName());
				
				

				login = nvdao.login(model.getUserName(), model.getPasswd());
				if (login!=null) {
				login.setKhoa(model.getMaKhoa());
				login.setMessage("thanh cong ket noi phan manh " + InfoConnection.getUrlPM());
				login.setStatus("200");}
				else {
					login = new NhanVienLoginModel();
					login.setMessage("ket noi phan manh that bai spdn that bai " + InfoConnection.getUrlPM());
					login.setStatus("403");}
				

				return login;
			}
			else {
				login = new NhanVienLoginModel();
				login.setMessage("ket noi phan manh that bai " + InfoConnection.getUrlPM());
				login.setStatus("403");
				return login;
			}

		}
		else {
			login = new NhanVienLoginModel();
			login.setMessage("ket noi phan manh that bai, khong co thong tin" );
			login.setStatus("403");
			return login;
		}

	}
}
