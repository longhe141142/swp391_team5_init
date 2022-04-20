package com.fu.swp391.controller.candidate;

import com.fu.swp391.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("candidate/cv")
public class CandidateController {
    @Autowired
    CandidateService candidateService ;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/listAllCV")
    public String listAllCV(Model model){
        model.addAttribute("listCandidateCV", candidateService.getAllCandidate());
        return "candidate/listAllCV";
    }


//    @PostMapping("createCV")
//    public String CreateCV(CompanyMajor companyMajor){
//
//        System.out.println(companyMajor.toString());
//
//        return "candidate/createCV";
//    }

}
