package com.fu.swp391.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("company")
public class CompanyController {

    @GetMapping("/home")
    public String home(){
        return "/company/HomeCompany";
    }
}
