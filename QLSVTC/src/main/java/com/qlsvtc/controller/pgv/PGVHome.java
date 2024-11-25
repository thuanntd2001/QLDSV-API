package com.qlsvtc.controller.pgv;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PGVHome {
	@GetMapping("/pgv")
    public String home(Model model) {


        model.addAttribute("newsStream", null);
        System.out.println("hello pgv");
        return "pgv/user";
    }

}
