package com.fu.swp391.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    @GetMapping("/")
    public String renderAdminSide(){
        return "admin/utilities-animation";
    }

    //admin
    @GetMapping("/home")
    public String homeAdmin(){
        return "/admin/homeAdmin";
    }
    @GetMapping("/login")
    public String loginAdmin(){
        return "/admin/login";
    }
    @GetMapping("/register")
    public String registerAdmin(){
        return "/admin/register";
    }
    @GetMapping("/forgotPassword")
    public String forgetPasswordAdmin(){
        return "/admin/forgot-password";
    }


}
