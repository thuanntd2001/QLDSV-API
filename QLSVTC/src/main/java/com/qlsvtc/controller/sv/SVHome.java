package com.qlsvtc.controller.sv;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SVHome {
	@GetMapping("/sv")
    public String home(Model model) {


        model.addAttribute("newsStream", null);
        System.out.println("hello sv");
        return "sv/user";
    }

}
