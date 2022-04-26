package com.fu.swp391.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(){
        return "/login/login";
    }

    @GetMapping("/fail_login")
    public String failLogin(){
        return "/404Page/account-denied";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "/404Page/404";
    }


}
