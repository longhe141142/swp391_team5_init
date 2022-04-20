package com.fu.swp391.controller.candidate;

import com.fu.swp391.entities.Company;
import com.fu.swp391.service.CompanyService;
import com.fu.swp391.service.candidate.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("candidate/cv")
public class CandidateController {
    @Autowired
    CandidateService candidateService ;

    @Autowired
    CompanyService companyService;
    public CandidateController(CandidateService candidateService,CompanyService companyService) {
        this.candidateService = candidateService;
        this.companyService = companyService;
    }

    @GetMapping("/listAllCV")
    public String listAllCV(Model model){
        model.addAttribute("listCandidateCV", candidateService.getAllCandidate());
        return "candidate/listAllCV";
    }
    @GetMapping("/ListCompanyCandidate")
    public String ListCompanyCandidate(Model model) {
        System.out.println("list comapny");
//        model.addAttribute("ListCompany",companyService.findAllCompany());
        return "candidate/listCompany";
    }

//    @PostMapping("createCV")
//    public String CreateCV(CompanyMajor companyMajor){
//
//        System.out.println(companyMajor.toString());
//
//        return "candidate/createCV";
//    }

}
