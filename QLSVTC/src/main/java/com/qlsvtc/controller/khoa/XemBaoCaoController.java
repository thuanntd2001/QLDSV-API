package com.qlsvtc.controller.khoa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "xembaocao")
public class XemBaoCaoController {

//==============================CHINHANH============================//
	@RequestMapping(value = { "khoa"}, method = RequestMethod.GET)
	public String getNVCN1(ModelMap model) {
	
		return "khoa/Xembaocao";
	}

	// ==============================CONGTY============================//
	@RequestMapping(value = { "pgv"}, method = RequestMethod.GET)
	public String getNVCTY(ModelMap model) {
		return "pgv/Xembaocao";
	}

	
}