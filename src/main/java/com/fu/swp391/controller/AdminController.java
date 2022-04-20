package com.fu.swp391.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

//    @GetMapping("/utilities")
//    public String renderAdminSide(){
//        return "admin/utilities-animation";
//    }

    @GetMapping("/")
    public String renderAdminHome(){
        return "admin/homeAdmin";
    }

    @GetMapping("/company")
    public String renderCompanyManagement(){
        return "company/ListAllCompany";
    }


}
