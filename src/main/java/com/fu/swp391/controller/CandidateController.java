package com.fu.swp391.controller;

import com.fu.swp391.common.enumConstants.GenderEnum;
import com.fu.swp391.entities.Company;
import com.fu.swp391.service.CompanyService;
import com.fu.swp391.service.RoleService;
import com.fu.swp391.service.UserService;
import com.fu.swp391.service.CandidateService;
import com.fu.swp391.service.candidate.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("candidate")
public class CandidateController {
    @Autowired
    RoleService roleService;

    @Autowired
    CandidateService candidateService;

    @Autowired
    GenderEnum genderEnum;

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;
    public CandidateController(CandidateService candidateService,CompanyService companyService) {
        this.candidateService = candidateService;
        this.companyService = companyService;
    }

//    @GetMapping("/listAllCV")
//    public String listAllCV(Model model){
//        model.addAttribute("listCandidateCV", candidateService.getAllCandidate());
//        return "candidate/listAllCV";
//    }


    @GetMapping("/home")
    public String home(){
        return "/candidate/HomeCandidate";
    }
    @GetMapping("/about-us")
    public String home1(){
        return "/candidate/about-us";
    }
    @GetMapping("/contact-us")
    public String home2(){
        return "/candidate/contact-us";
    }
    @GetMapping("/listAllCV")
    public String listAllCV(){
        return "/candidate/listAllCV";
    }
    @GetMapping("/detailOneCV")
    public String detailOneCV(){
        return "/candidate/detailOneCV";
    }
    @GetMapping("/createCV")
    public String createCV(){
        return "/candidate/CreateCV";
    }

    @GetMapping("/ListCompanyCandidate")
    public String ListCompanyCandidate(Model model) {

        List<Company> ListCompany = companyService.findAllCompany();
        model.addAttribute("ListCompany",ListCompany);
        return "candidate/listCompany";
    }

    @GetMapping("/candidatehome")
    public String homecandidate(Model model) {



        return "candidate/HomeCandidate.html";
    }

}
