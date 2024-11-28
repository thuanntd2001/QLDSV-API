package com.qlsvtc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "xembaocao")
public class ViewXemBaoCaoController {

//==============================khoa============================//
	@RequestMapping(value = { "khoa"}, method = RequestMethod.GET)
	public String bckhoa(ModelMap model) {
	
		return "khoa/Xembaocao";
	}

	// ==============================pgv============================//
	@RequestMapping(value = { "pgv"}, method = RequestMethod.GET)
	public String bcpgv(ModelMap model) {
		return "pgv/Xembaocao";
	}
	@RequestMapping(value = { "sv"}, method = RequestMethod.GET)
	public String bcsv(ModelMap model) {
		return "sv/Xembaocao";
	}

	
}