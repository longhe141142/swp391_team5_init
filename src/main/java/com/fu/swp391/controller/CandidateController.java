package com.fu.swp391.controller;

import com.fu.swp391.common.enumConstants.GenderEnum;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.JobPost;
import com.fu.swp391.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("candidate")
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
@Autowired
CompanyMajorService companyMajorService;
    public CandidateController(CandidateService candidateService,CompanyService companyService,CompanyMajorService companyMajorService) {
        this.candidateService = candidateService;
        this.companyService = companyService;
        this.companyMajorService = companyMajorService;
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

    @GetMapping("/DetailCompany/{id}")
    public String DetailCompany(Model model, @PathVariable long id) {
//
        List<JobPost> ListCompanyDetail = companyMajorService.findCompanyMajorsByCompanyId(id);
        model.addAttribute("ListCompanyDetail",ListCompanyDetail);

        return "candidate/detailCompany";
    }
}
