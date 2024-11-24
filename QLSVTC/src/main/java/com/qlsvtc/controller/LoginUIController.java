package com.qlsvtc.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qlsvtc.dao.impl.DSPMDAO;
import com.qlsvtc.dao.impl.NhanVienDAO;
import com.qlsvtc.model.DSPMModel;
import com.qlsvtc.model.UserModel;
import com.qlsvtc.service.impl.CheckService;
import com.qlsvtc.utils.FormUtil;
import com.qlsvtc.utils.SessionUtil;

@Controller
public class LoginUIController {

	@Autowired
	HttpRequest session;
	@Autowired
	ServletContext application;
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	DSPMDAO dspmDAO = new DSPMDAO();
	NhanVienDAO nvdao = new NhanVienDAO();
	private CheckService ck = new CheckService();

	@GetMapping("dang-nhap")
	private String doGet(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
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

			return "chung/login";
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			return "redirect:dang-nhap?action=login";
		} else {
			return "redirect:dang-nhap?action=login";
		}
	}
/*
	@RequestMapping(value = "dang-nhap", method = RequestMethod.POST)
	private String doPost(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");

		if (action != null && action.equals("login")) {

			UserModel model = FormUtil.toModel(UserModel.class, request);

			if (model != null) {
				// kt nv co tk trong sqlserver ko
				boolean flag = false;
				// set server ma nv chon de thu ket noi
				InfoConnection.setUrlPM(model.getChiNhanh());
				flag = ck.ckUserPassword(model.getUserName(), model.getPasswd());
				// neu ket noi dc setup thuoc tinh cho nhan vien sap dang nhap
				if (flag) {
					InfoConnection.setUrlPM(model.getChiNhanh());

					InfoConnection.setPassWordPM(model.getPasswd());
					InfoConnection.setUserNamePM(model.getUserName());

					System.out.print("thanh cong ket noi phan manh" + InfoConnection.getUrlPM());

					NhanVienLoginModel login = nvdao.login(model.getUserName(), model.getPasswd());

					model.setMaNV(login.getMaNV());
					model.setHoTen(login.getHoTen());
					model.setRoleID(login.getTenNhom());

					SessionUtil.getInstance().putValue(request, "USERMODEL", model);
					session.setAttribute("USERMODEL", model);

					if (model.getChiNhanh().equals("GHDBP-20210702Z\\SQLSV1")) {
						model.setMaChiNhanh("CN1");
						if (model.getRoleID().equals("CONGTY"))

							return "redirect:quanlynhanvien/cn1/congty.htm";
						if (model.getRoleID().equals("CHINHANH"))

							return "redirect:quanlynhanvien/cn1/chinhanh.htm";
						if (model.getRoleID().equals("USER"))

							return "redirect:quanlynhanvien/cn1/user.htm";
					}

					else if (model.getChiNhanh().equals("GHDBP-20210702Z\\SQLSV2"))
					{
						model.setMaChiNhanh("CN2");
						// tra ra view
						if (model.getRoleID().equals("CONGTY"))

							return "redirect:quanlynhanvien/cn2/congty.htm";
						if (model.getRoleID().equals("CHINHANH"))

							return "redirect:quanlynhanvien/cn2/chinhanh.htm";
						if (model.getRoleID().equals("USER"))

							return "redirect:quanlynhanvien/cn2/user.htm";
					}
				}

			}
			System.out.print("ket noi that bai " + "user " + model.getUserName() + "pass " + model.getPasswd());

		}

		return "redirect:dang-nhap.htm?action=login&message=username_password_invalid&alert=danger";

	}*/
}