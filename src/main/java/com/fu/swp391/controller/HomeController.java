package com.fu.swp391.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String goHome(){
        return "redirect:/candidate/home";
    }


    @GetMapping("/testHomie")
    public String testtt(){
        return "/common/test";
    }
}
