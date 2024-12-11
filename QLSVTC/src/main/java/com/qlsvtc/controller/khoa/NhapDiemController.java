package com.qlsvtc.controller.khoa;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlsvtc.CNTT.repository.MonHocRepositoryCNTT;
import com.qlsvtc.config.DSTS;
import com.qlsvtc.dao.impl.NhapDiemDAO;
import com.qlsvtc.model.NhapDiemFormModel;
import com.qlsvtc.model.NhapDiemModel;

import com.qlsvtc.model.para.ParaNhapDiem;

@Controller
@RequestMapping(value = "nhapdiem")
public class NhapDiemController {

	@Autowired
	MonHocRepositoryCNTT repocn;

	NhapDiemDAO nhapdiem = new NhapDiemDAO();

	@GetMapping("/formnhapdiem/khoa")
	public String getnhapdiem(ModelMap model) {
		ParaNhapDiem para = new ParaNhapDiem();
		model.addAttribute("para", para);
		model.addAttribute("lstMH", repocn.findAll());
		return "khoa/form/fformnhapdiem";
	}

	@PostMapping("/formnhapdiem/khoa")
	public String get2nhapdiem(HttpSession session, ParaNhapDiem para, ModelMap model) {
		List<NhapDiemModel> lst = nhapdiem.findAll(session, para.getNk(), para.getHk(), para.getMaMH(), para.getNhom());
		NhapDiemFormModel nhapDiemForm = new NhapDiemFormModel();
		nhapDiemForm.setLst(lst);
		model.addAttribute("itemListWrapper", nhapDiemForm);
		model.addAttribute("para", para);
		return "khoa/nhapdiem/formnhapdiem";
	}

	@PostMapping("/nhapdiem/khoa")
	public String postnhapdiem(HttpSession session, @ModelAttribute("itemListWrapper") NhapDiemFormModel formDiem,
			ModelMap model) throws  SQLException {


		try (Connection conn = DriverManager.getConnection((String) session.getAttribute("url"),
				(String) session.getAttribute("username"), (String) session.getAttribute("password"))) {
			// T?o m?t SQLServerDataTable
			SQLServerDataTable table = new SQLServerDataTable();
			table.addColumnMetadata("MALTC", java.sql.Types.INTEGER);
			table.addColumnMetadata("MASV", java.sql.Types.NVARCHAR);
			table.addColumnMetadata("DIEM_CC", java.sql.Types.FLOAT);
			table.addColumnMetadata("DIEM_GK", java.sql.Types.FLOAT);
			table.addColumnMetadata("DIEM_CK", java.sql.Types.FLOAT);

			List<NhapDiemModel> dataList = formDiem.getLst();
			// Thêm d? li?u vào b?ng

			for (int i = 0; i < dataList.size(); i++) {
				table.addRow(dataList.get(i).getMaLTC(), dataList.get(i).getMaSV(), dataList.get(i).getDiemCC(),
						dataList.get(i).getDiemGK(), dataList.get(i).getDiemCK());
			}

			// T?o m?t PreparedStatement ?? g?i stored procedure
			String sql = "{CALL SP_CAPNHAT_DIEM (?)}";
			try (CallableStatement cs = conn.prepareCall(sql)) {
				((SQLServerCallableStatement) cs).setStructured(1, "dbo.TYPE_DANGKY", table);
				boolean resultSetReturned = cs.execute();
				if (resultSetReturned) {
					try (ResultSet rs = cs.getResultSet()) {
						rs.next();
						System.out.println(rs.getInt(1));
					}
				}
			}
			return "khoa/nhapdiem/formnhapdiem";

		}
	}
}