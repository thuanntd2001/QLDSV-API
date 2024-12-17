package com.qlsvtc.controller.sv;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.qlsvtc.dao.impl.BCPhieuDiemDAO;
import com.qlsvtc.model.WekaModel;
import com.qlsvtc.model.baocao.BCPhieuDiem;
import com.qlsvtc.utils.WekaJ48Example;

@Controller
public class SVChonCN {
	BCPhieuDiemDAO bcphieudiem = new BCPhieuDiemDAO();

	@GetMapping("/svchoncn")
	public String home(HttpSession session, Model model) {
		// List<WekaModel> testDataList = new ArrayList<>();

		/*
		 * testDataList.add( new WekaModel(3, "thap", "A", "D", "D","B", "cao", "cao",
		 * ""));
		 */
		List<BCPhieuDiem> lstPD = bcphieudiem.findAll(session, (String) session.getAttribute("MASV"));
		List<WekaModel> testDataList = WekaJ48Example.readMH();
		List<WekaModel> lst = WekaJ48Example.capNhatWekaLst(testDataList, lstPD);
		WekaJ48Example.J48(lst);
		int sumCNPM = 0;
		int sumHTTT = 0;
		String nganh = null;
		for (WekaModel item : lst) {
			if(item.getChuyenNganh().equals("HTTT")) sumHTTT+=1;
			else sumCNPM+=1;
		}
		if (sumCNPM>sumHTTT) nganh="CÔNG NGHỆ PHẦN MỀM";
		if (sumCNPM<sumHTTT) nganh="HỆ THỐNG THÔNG TIN";
		if (sumCNPM==sumHTTT) nganh="CÔNG NGHỆ PHẦN MỀM, HỆ THỐNG THÔNG TIN";

		model.addAttribute("sumCNPM", sumCNPM);
		model.addAttribute("sumHTTT", sumHTTT);
		model.addAttribute("nganh", nganh);

		model.addAttribute("lst", lst);
		return "sv/gycn";
	}

}
