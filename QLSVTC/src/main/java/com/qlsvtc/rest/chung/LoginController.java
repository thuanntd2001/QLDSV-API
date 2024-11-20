package com.qlsvtc.rest.chung;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qlsvtc.dao.impl.DSPMDAO;
import com.qlsvtc.dao.impl.NhanVienDAO;
import com.qlsvtc.model.DSPMModel;
import com.qlsvtc.model.NhanVienLoginModel;
import com.qlsvtc.model.UserModel;
import com.qlsvtc.service.impl.CheckService;



@RestController
@RequestMapping("/api")
public class LoginController {


	DSPMDAO dspmDAO = new DSPMDAO();
	NhanVienDAO nvdao = new NhanVienDAO();
	private CheckService ck = new CheckService();

	@GetMapping("/dspm")
	public List<DSPMModel> addDspm() {
		// tao dspm set ra view
		return dspmDAO.findAll();
	}
	@GetMapping("/logout")
	private String logout(HttpSession session){

		 session.invalidate();
		return "Da logout thanh cong";
	}
	
	@PostMapping("/login")
	private NhanVienLoginModel doPost(UserModel model, HttpSession session) {
		NhanVienLoginModel login;
		if (model != null) {
			// kt nv co tk trong sqlserver ko
			boolean flag = false;
			// set server ma nv chon de thu ket noi
	        session.setAttribute("url", "jdbc:sqlserver://"+model.getKhoaURL()+"; Database=QLSVTC");
	        session.setAttribute("password", model.getPasswd());
	        session.setAttribute("username", model.getUserName());

			flag = ck.ckUserPassword(session);
			// neu ket noi dc setup thuoc tinh cho nhan vien sap dang nhap
			if (flag) {
		
				login = nvdao.login(session);
				if (login!=null) {
				login.setKhoa(model.getMaKhoa());

		        session.setAttribute("khoa", login.getKhoa());
		        session.setAttribute("manv", login.getMaNV());
		        session.setAttribute("hoten", login.getHoTen());
		        session.setAttribute("quyen", login.getTenNhom());
		        
				login.setMessage("thanh cong ket noi phan manh " + (String)session.getAttribute("url"));
				login.setStatus("200");}
				else {
					session.invalidate();
					login = new NhanVienLoginModel();
					login.setMessage("ket noi phan manh that bai sp dangnhap that bai " + (String)session.getAttribute("url"));
					login.setStatus("403");}
				

				return login;
			}
			else {
				session.invalidate();
				login = new NhanVienLoginModel();
				login.setMessage("ket noi phan manh that bai " + (String)session.getAttribute("url"));
				login.setStatus("403");
				return login;
			}

		}
		else {
			session.invalidate();
			login = new NhanVienLoginModel();
			login.setMessage("ket noi phan manh that bai, khong co thong tin" );
			login.setStatus("403");
			return login;
		}

	}
}
