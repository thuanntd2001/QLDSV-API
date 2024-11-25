package com.qlsvtc.controller.khoa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class KhoaHome {
	@GetMapping("/khoa")
    public String home(Model model) {


        model.addAttribute("newsStream", null);
        System.out.println("hello khoa");
        return "khoa/user";
    }

}
