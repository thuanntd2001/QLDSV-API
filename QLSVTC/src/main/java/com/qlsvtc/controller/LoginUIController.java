package com.qlsvtc.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.qlsvtc.CNTT.repository.SinhVienRepositoryCNTT;
import com.qlsvtc.VT.repository.SinhVienRepositoryVT;
import com.qlsvtc.config.DSTS;
import com.qlsvtc.dao.impl.DSPMDAO;
import com.qlsvtc.dao.impl.NhanVienDAO;
import com.qlsvtc.entity.SinhVien;
import com.qlsvtc.model.DSPMModel;
import com.qlsvtc.model.NhanVienLoginModel;
import com.qlsvtc.model.UserModel;
import com.qlsvtc.service.impl.CheckService;
import com.qlsvtc.utils.SessionUtil;

@Controller
public class LoginUIController {
	// InfoConnection infoConnection = new InfoConnection();

	@Autowired
	ServletContext application;
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	DSPMDAO dspmDAO = new DSPMDAO();

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

	private String dbCNurl = DSTS.getUrlCNTT();
	private String dbVTurl = DSTS.getUrlVT();
	NhanVienDAO nvdao = new NhanVienDAO();
	private CheckService ck = new CheckService();
	@Autowired
	SinhVienRepositoryCNTT svrepocn;
	@Autowired
	SinhVienRepositoryVT svrepovt;
	
	@PostMapping("dang-nhap")
	private String doPost(UserModel model, HttpSession session) {
		NhanVienLoginModel login = null;
		String strKhoa = "";
		if (model != null) {
			// kt nv co tk trong sqlserver ko
			boolean flag = false;
			if (model.getKhoaURL().contains("Công nghệ")) {
				session.setAttribute("url", dbCNurl);
				strKhoa = "CNTT";
			} else if (model.getKhoaURL().contains("Viễn thông")) {
				session.setAttribute("url",dbVTurl );

				strKhoa = "VT";
			}
			// set server ma nv chon de thu ket noi
			session.setAttribute("password", model.getPasswd());
			session.setAttribute("username", model.getUserName());

			
			flag = ck.ckUserPassword(session);
			if (flag) {
				login = nvdao.login(session);
				if (login != null) {
					System.out.println("khoa hoac PGV");

					login.setKhoa(strKhoa);
				}

				session.setAttribute("USERMODEL", login);
				if (login.getTenNhom().equals("KHOA"))
					return "redirect:khoa";
				if (login.getTenNhom().equals("PGV"))
					return "redirect:pgv";

			}
			else {
				System.out.println(" Ko phai khoa va PGV");

				SinhVien svEntity=null;
				if (strKhoa.equals("CNTT")) {
					System.out.println(" tim sv cn");

					svEntity = svrepocn.findByMaSVAndPasswordAndDaNghiHoc(model.getUserName(), model.getPasswd(), false);
				}
				else if (strKhoa.equals("VT")) {
					System.out.println(" tim sv vt");

					svEntity = svrepovt.findByMaSVAndPasswordAndDaNghiHoc(model.getUserName(), model.getPasswd(), false);
				}
				if (svEntity!=null) {
					System.out.println("la SV");
					login = new NhanVienLoginModel();
					login.setHoTen(svEntity.getHo() + " " + svEntity.getTen());
					login.setTenNhom("SV");
					login.setMaNV(model.getUserName());
					login.setKhoa(strKhoa);
					
					session.setAttribute("password", DSTS.getPwsv());
					session.setAttribute("username", DSTS.getTksv());
					session.setAttribute("USERMODEL", login);
					session.setAttribute("MASV", model.getUserName());

					return "redirect:sv";
				}
			}

		}

		System.out.println("ket noi that bai " + "user " + model.getUserName() + " pass " + model.getPasswd());
		return "redirect:dang-nhap?action=login&message=username_password_invalid&alert=danger";

	}
}