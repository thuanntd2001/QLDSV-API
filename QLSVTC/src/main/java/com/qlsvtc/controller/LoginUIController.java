package com.qlsvtc.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.qlsvtc.dao.impl.DSPMDAO;
import com.qlsvtc.dao.impl.NhanVienDAO;
import com.qlsvtc.model.DSPMModel;
import com.qlsvtc.model.NhanVienLoginModel;
import com.qlsvtc.model.UserModel;
import com.qlsvtc.service.impl.CheckService;
import com.qlsvtc.utils.SessionUtil;

@Controller
public class LoginUIController {
	//InfoConnection infoConnection = new InfoConnection();
	
	@Autowired
	ServletContext application;
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	DSPMDAO dspmDAO = new DSPMDAO();
	NhanVienDAO nvdao = new NhanVienDAO();
	private CheckService ck = new CheckService();

	@GetMapping("dang-nhap")
	private String doGet(ModelMap model, HttpServletRequest request) {
		// tao dspm set ra view
		if (application.getAttribute("DSPM") == null) {
			List<DSPMModel> DSPMs = dspmDAO.findAll();
			if (DSPMs != null) {

				application.setAttribute("DSPMs", DSPMs);
			}
		}

		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			String alert = request.getParameter("alert");
			String message = request.getParameter("message");
			if (message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
		System.out.println("voo controler login r nhe");

			return "chung/login";
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			return "redirect:dang-nhap?action=login";
		} else {
			return "redirect:dang-nhap?action=login";
		}
	}
	@Value("${app.DBCNTTurl}")
    private String dbCNurl;
	@Value("${app.DBVTurl}")
    private String dbVTurl;
	
	@PostMapping("dang-nhap")
	private String doPost(UserModel model, HttpSession session) {
		NhanVienLoginModel login;
		if (model != null) {
			// kt nv co tk trong sqlserver ko
			boolean flag = false;
			// set server ma nv chon de thu ket noi
			session.setAttribute("url", "jdbc:sqlserver://" + model.getKhoaURL() + "; Database=QLSVTC");
			session.setAttribute("password", model.getPasswd());
			session.setAttribute("username", model.getUserName());

			flag = ck.ckUserPassword(session);
			if (flag) {
				login = nvdao.login(session);
				if (login!= null) {
					login.setKhoa(model.getMaKhoa());
					if (login.getKhoa()==null) {
						
						if (dbCNurl.contains((String) session.getAttribute("url"))){
							login.setKhoa("CNTT");
						}
						else if (dbVTurl.contains((String) session.getAttribute("url"))){
							login.setKhoa("VT");
						}
					}

					session.setAttribute("USERMODEL", login);

					if (login.getTenNhom().equals("SV"))
						return "redirect:sv";
					if (login.getTenNhom().equals("KHOA"))
						return "redirect:khoa";
					if (login.getTenNhom().equals("PGV"))
						return "redirect:pgv";

				}

			}

		}
		System.out.print("ket noi that bai " + "user " + model.getUserName() + "pass " + model.getPasswd());
		return "redirect:dang-nhap?action=login&message=username_password_invalid&alert=danger";

	}
}