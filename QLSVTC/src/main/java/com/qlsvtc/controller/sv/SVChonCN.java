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

	@GetMapping("/choncn")
	public String home(HttpSession session,Model model) {
		// List<WekaModel> testDataList = new ArrayList<>();

		/*
		 * testDataList.add( new WekaModel(3, "thap", "A", "D", "D","B", "cao", "cao",
		 * ""));
		 */
		List<BCPhieuDiem> lst= bcphieudiem.findAll(session,(String)session.getAttribute("MASV"));
		List<WekaModel> testDataList = WekaJ48Example.readMH();

		WekaJ48Example.J48(testDataList);
		model.addAttribute("newsStream", null);
		System.out.println("hello sv");
		return "sv/gycn";
	}

}
